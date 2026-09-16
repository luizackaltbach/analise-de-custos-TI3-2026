# Fluxo do Sistema — Análise de Custos

Documento de referência do fluxo operacional e financeiro do sistema, derivado do
schema (`sql/analise_custos.sql`) e das telas existentes (`src/view/`).

O sistema responde a uma pergunta central: **por quanto eu preciso vender este
produto para cobrir todos os custos e ainda remunerar o capital investido?**
Todo o resto é preparação para chegar nessa resposta. O fluxo tem quatro camadas,
e cada uma só pode ser preenchida depois da anterior.

---

## Camada 1 — Cadastros base

Dados estruturais que mudam pouco. São a fundação: sem eles nenhum lançamento
nem cálculo é possível. Nenhuma tabela desta camada depende de outra.

### 1.1 Matéria-prima — `materia_prima` · tela `CadastroProdutosPrimarios`

Catálogo de insumos. Cada insumo é cadastrado **uma única vez** e serve tanto para
a composição dos produtos quanto para a previsão de reposição de estoque (foi a
unificação que eliminou a antiga `produtos_primarios`).

| Campo | Significado |
|---|---|
| `codigo`, `nome`, `unidade` | identificação do insumo |
| `quantidade_estoque` | saldo atual em estoque |
| `custo_reposicao` | **custo unitário de reposição** — quanto custa comprar 1 unidade hoje |

O `custo_reposicao` é a peça-chave: o sistema não custeia pelo preço histórico de
compra, e sim pelo custo de **repor** o insumo. É a decisão financeira correta —
se o aço subiu 20%, o produto tem que ser reprecificado com o aço a 20% mais,
senão a empresa vende, recebe, e não consegue recomprar o mesmo insumo.

### 1.2 Investimento fixo — `investimento_fixo` · tela `CadastroInvestimentoFixo`

Bens duráveis: máquinas, imóveis, móveis, veículos. Apenas `tipo` e `valor`.

Este cadastro **não se liga a centro de custo**, e isso é intencional. O
investimento fixo não entra no fluxo como custo operacional — ele entra como
**base de capital** sobre a qual se exige um retorno (ver 4.2, T.R.C.). Se a
intenção for jogar o desgaste do maquinário no custo por hora, o caminho certo é
outro: cadastrar uma conta "Depreciação" em `gastos_gerais` e ratear o valor
mensal em `previsao_custos_fixos`. Assim a depreciação entra como custo (afeta o
preço mínimo) e o investimento entra como capital (afeta o retorno exigido) —
duas coisas diferentes, que é exatamente como a contabilidade as trata.

### 1.3 Contas de gastos gerais — `gastos_gerais` · tela `CadastroContasGastosGerais`

Plano de contas dos custos fixos: "Aluguel", "Energia", "Salários
administrativos", "Depreciação". Só o nome da conta — **sem valor**. O valor é
lançado mês a mês na Camada 2, porque o aluguel de março não é o de abril.

### 1.4 Centros de custo — `centro_custo` · tela `CadastroCentroCustos`

Setores que consomem recursos e transformam insumos: "Corte", "Montagem",
"Pintura", "Administrativo".

| Campo | Significado |
|---|---|
| `nome` | identificação do setor |
| `horas_efetivas` | horas produtivas disponíveis no período |

`horas_efetivas` é um dado de entrada, não um cálculo, e é o **denominador** que
transforma custo mensal em custo por hora. "Efetivas" significa horas realmente
produtivas — descontados feriados, paradas de manutenção e setup. Usar as horas
nominais (220/mês) em vez das efetivas subestima o custo por hora e leva a
produto subprecificado.

### 1.5 Despesas com vendas — `despesas_com_vendas` · tela `CadastroDespesasComVendas`

Custos que incidem **sobre a venda**, não sobre a produção: comissão, frete,
propaganda, taxa de cartão, impostos sobre faturamento.

| Campo | Significado |
|---|---|
| `nome_conta` | identificação da despesa |
| `percentual` | % que incide sobre a **receita bruta** |

São percentuais, não valores fixos, porque crescem junto com a venda: 5% de
comissão são 5% tanto em R$ 1.000 quanto em R$ 100.000. Essa natureza
proporcional é o que obriga o uso do markup divisor em 4.3.

### 1.6 Capital de giro — `capital_de_giro` · tela `CadastroCapitalDeGiro`

Dinheiro parado no ciclo operacional: estoque comprado e ainda não vendido, venda
já feita e ainda não recebida.

| Campo | Significado |
|---|---|
| `situacao` | cenário/identificação do cálculo |
| `prazo_medio_dias` | ciclo financeiro em dias |
| `periodo` | período de referência em dias (ex.: 30) |
| `investimento_variavel` | necessidade de capital de giro apurada |

**Cálculo:**

```
custo_operacional_diario = custo_operacional_do_periodo / periodo
investimento_variavel    = custo_operacional_diario × prazo_medio_dias
```

A lógica: se a empresa gasta R$ 1.000/dia e leva 45 dias entre pagar o insumo e
receber do cliente, há permanentemente R$ 45.000 imobilizados no ciclo. Esse
dinheiro é investimento tanto quanto uma máquina, e também precisa ser remunerado
— por isso vai para `resultado_administrativo.investimento_variavel` e entra na
base do T.R.C. junto com o investimento fixo.

---

## Camada 2 — Lançamentos periódicos

Aqui entram os valores com data (`competencia`). É o que muda todo mês e o que
mantém o custeio atualizado.

### 2.1 Previsão de reposição de estoque — `previsao_reposicao_estoque` · tela `CadastroPrevisaoReposicaoEstoque`

Quanto de cada insumo será comprado na competência, e a que custo.

**Cálculo:**

```
gastos_totais = (quantidade × custo_reposicao) + outros_gastos
```

`outros_gastos` cobre frete, seguro e impostos de compra não recuperáveis — o
custo de colocar o insumo dentro da fábrica, que é o custo real de reposição.
O `custo_reposicao` é gravado aqui como **cópia histórica**, não como referência:
se o preço do insumo mudar amanhã, o lançamento de março deve continuar mostrando
o custo praticado em março.

### 2.2 Previsão de custos fixos — `previsao_custos_fixos` · tela `CadastroPrevisaoCustosFixos`

O rateio: quanto de cada conta de gasto geral pertence a cada centro de custo, na
competência. É a tabela que cruza `gastos_gerais` × `centro_custo` × valor × mês.

Exemplo de uma competência:

| Conta | Centro de custo | Valor |
|---|---|---|
| Aluguel | Corte | 3.000,00 |
| Aluguel | Montagem | 2.000,00 |
| Energia | Corte | 1.800,00 |
| Energia | Montagem | 400,00 |

O rateio é decisão gerencial (por área ocupada, por consumo medido, por número de
funcionários) e por isso é digitado, não calculado. Mas há uma regra que o sistema
deveria respeitar: **a soma do rateio de uma conta tem que fechar com o valor
total da conta no mês** — senão sobra ou falta custo no produto.

### 2.3 Custo por hora — `custo_por_hora` · tela `CadastroCustoPorHora`

Converte custo mensal em custo por tempo de operação. Uma linha por par
(centro de custo, conta de gasto geral).

**Cálculo:**

```
valor        = Σ previsao_custos_fixos.valor
                 WHERE id_centro_custo = ? AND id_gasto_geral = ?
                   AND competencia = ?

custo_minuto = valor / (centro_custo.horas_efetivas × 60)
```

E o custo total de operar o centro por minuto:

```
custo_minuto_do_centro = Σ custo_por_hora.custo_minuto
                           WHERE id_centro_custo = ?
```

Exemplo: o Corte recebeu R$ 4.800 de rateio no mês (3.000 aluguel + 1.800 energia)
e tem 160 horas efetivas. Custo por hora = 4.800 / 160 = R$ 30,00/h; custo por
minuto = R$ 0,50. É esse R$ 0,50 que será multiplicado pelo tempo que cada produto
passa no Corte.

Repare que `custo_minuto` tem 6 casas decimais no schema: é um valor pequeno que
depois é multiplicado por centenas de minutos e por milhares de unidades, então
arredondar em 2 casas propagaria erro relevante no preço final.

---

## Camada 3 — Composição dos produtos

Aqui o custo deixa de ser "da empresa" e passa a ser "do produto". É onde se
descobre quanto custa fazer uma unidade.

### 3.1 Produto para venda — `produtos_para_venda`

| Campo | Significado |
|---|---|
| `codigo`, `nome`, `unidade` | identificação |
| `quantidade` | tamanho do lote de produção a que a composição se refere |
| `custo_geral` | custo total do lote (calculado) |
| `custo_unitario` | custo de uma unidade (calculado) |

### 3.2 Composição de matéria-prima — `composicao_materia_prima` · tela `CadastroComposicaoProdutos`

A receita do produto: quais insumos, em que quantidade.

**Cálculo por linha:**

```
custo_reposicao = materia_prima.custo_reposicao   (copiado no momento do cadastro)
total           = quantidade × custo_reposicao
```

### 3.3 Composição de centro de custo — `composicao_centro_custo` · tela `CadastroComposicaoProdutos`

O roteiro de produção: por quais setores o produto passa e quanto tempo fica em
cada um.

**Cálculo por linha:**

```
custo_minuto = custo_minuto_do_centro          (copiado no momento do cadastro)
total        = tempo_minutos × custo_minuto
```

### 3.4 Fechamento do custo do produto

```
custo_materia_prima = Σ composicao_materia_prima.total   WHERE id_produto_venda = ?
custo_transformacao = Σ composicao_centro_custo.total     WHERE id_produto_venda = ?

custo_geral    = custo_materia_prima + custo_transformacao
custo_unitario = custo_geral / quantidade
```

Esta é a separação que sustenta todo o resto: `custo_materia_prima` é **custo
variável** (existe só se o produto for feito) e `custo_transformacao` é **custo
fixo rateado** (o aluguel é pago mesmo com a máquina parada). A análise de ponto
de equilíbrio em 4.5 depende inteiramente dessa distinção estar correta.

### 3.5 Estoque — `cadastro_estoque`

Controle de saldo por custo médio ponderado, alimentado pelo botão "Atualizar no
Estoque" da tela `Resultado`.

**Cálculo:**

```
custo_medio = (quantidade_anterior × custo_anterior + quantidade × custo)
              / (quantidade_anterior + quantidade)

total       = (quantidade_anterior + quantidade) × custo_medio
```

O custo médio ponderado evita que uma compra pequena a preço atípico distorça o
valor de todo o estoque. `venda` guarda o preço de venda praticado, permitindo
comparar valor de estoque a custo contra valor a preço de venda.

---

## Camada 4 — Formação de preço · tela `Resultado`

O cálculo final, gravado como **snapshot** em `resultado_administrativo`. Snapshot
significa foto datada: `codigo_produto`, `nome_produto` e `unidade` são copiados
de propósito, para que um fechamento de março continue legível depois de o produto
ser renomeado em junho.

### 4.1 Custos apurados (vêm da Camada 3)

```
total_reposicao  = custo_materia_prima        -- "T. Rep. Estoque" na tela
total_custo_fixo = custo_transformacao        -- "T. Custo Fixo"
total            = total_reposicao + total_custo_fixo

percentual_fixo  = total_custo_fixo / total × 100
```

`percentual_fixo` é indicador de risco operacional: quanto maior a fatia fixa,
mais a empresa sofre com queda de volume, porque o custo não cai junto.

### 4.2 Remuneração do capital

```
investimento_fixo     = Σ investimento_fixo.valor        -- "Valor Inv. Fixo"
investimento_variavel = capital_de_giro.investimento_variavel   -- "Inv. Variável"

capital_investido = investimento_fixo + investimento_variavel

retorno_exigido   = capital_investido × (taxa_retorno_capital / 100)
retorno_por_unidade = retorno_exigido / quantidade
```

`taxa_retorno_capital` ("T.R.C." na tela) é o custo de oportunidade: o mínimo que
o capital precisa render para valer a pena estar no negócio em vez de aplicado.
Preço que cobre custo mas não remunera capital dá empresa que "não dá prejuízo" e
mesmo assim destrói valor.

### 4.3 Receita necessária — o markup divisor

Aqui está o ponto mais delicado do cálculo, e o erro mais comum.

```
receita_liquida = total + retorno_por_unidade
```

`receita_liquida` é o que precisa **sobrar** depois de pagas as despesas de venda.
Como essas despesas são um percentual da receita bruta, não se pode simplesmente
somá-las ao custo — o percentual incide sobre um valor que ainda não se conhece.
A forma correta é dividir:

```
p = Σ despesas_com_vendas.percentual (das contas aplicáveis) / 100

receita_bruta = receita_liquida / (1 − p)
despesa_venda = receita_bruta − receita_liquida
```

Por que dividir e não multiplicar: com custo de R$ 100 e comissão de 20%, somar dá
preço R$ 120 — mas 20% de 120 são R$ 24, sobram R$ 96 e falta R$ 4. Dividindo:
100 / 0,80 = R$ 125; 20% de 125 são R$ 25, sobram exatamente os R$ 100
necessários.

`tipo_despesa` grava qual conta foi usada ("Origem desp. Venda" na tela). O schema
atual suporta **uma conta por cálculo**; se um produto precisar acumular frete +
comissão + cartão simultaneamente, seria necessária uma tabela de composição de
despesas de venda.

### 4.4 Preço final

```
preco_final_unitario = receita_bruta / quantidade         -- "Pço V. s/Desc"

preco_venda_desconto = preco_final_unitario × (1 − desconto_programado / 100)
                                                          -- "Pço V. c/Desc"

aumento = desconto_programado / (100 − desconto_programado) × 100
```

`aumento` responde: se eu quero poder dar X% de desconto e ainda assim chegar no
preço necessário, quanto tenho que subir a tabela antes? Para 10% de desconto,
o aumento necessário é 11,11% — não 10%, porque o desconto incide sobre o preço
já aumentado.

```
margem_lucro = (receita_liquida − total) / receita_bruta × 100
```

A margem é medida **sobre a receita bruta**, não sobre o custo — é assim que se
compara com margem de mercado e com a margem de concorrentes.

### 4.5 Ponto de equilíbrio

```
custo_variavel_unitario = (total_reposicao / quantidade)
                          + (preco_final_unitario × p)      -- "C. Un. Var."

margem_contribuicao = preco_final_unitario − custo_variavel_unitario

ponto_equilibrio_fixo     = total_custo_fixo / margem_contribuicao   -- em unidades
ponto_equilibrio_variavel = ponto_equilibrio_fixo × preco_final_unitario  -- em R$
```

O custo variável unitário inclui as despesas de venda porque elas também só
existem se houver venda. A margem de contribuição é o quanto cada unidade vendida
"contribui" para pagar o bolo de custo fixo; dividir o custo fixo por ela dá
quantas unidades são necessárias para empatar.

### 4.6 Simulação gráfica — `resultado_grafico`

Uma linha por quantidade simulada, pendurada no snapshot pai (`id_resultado`).

**Cálculo por ponto:**

```
receita_total  = quantidade_venda × preco_final_unitario
custo_total    = total_custo_fixo + (quantidade_venda × custo_variavel_unitario)
lucro_prejuizo = receita_total − custo_total
```

Gerando pontos em torno do ponto de equilíbrio (por exemplo de 0 a 2× o PE), o
gráfico mostra as duas retas se cruzando exatamente em
`ponto_equilibrio_fixo` — onde `lucro_prejuizo = 0`. Esse cruzamento é a
verificação visual de que toda a cadeia de cálculo está coerente: se as retas não
cruzarem no ponto calculado em 4.5, há erro em algum lugar do fluxo.

---

## Ordem de execução

O fluxo é estritamente sequencial — cada etapa consome resultado da anterior:

```
1. Cadastros base
   matéria-prima · investimento fixo · contas de gastos gerais
   centros de custo · despesas com vendas
        ↓
2. Lançamentos do mês (competência)
   previsão de reposição de estoque
   previsão de custos fixos (rateio conta × centro)
        ↓
   custo por hora   ← previsão de custos fixos ÷ horas efetivas
   capital de giro  ← custo operacional × prazo médio
        ↓
3. Composição do produto
   matéria-prima (quantidade × custo reposição)   → custo variável
   centros de custo (minutos × custo minuto)      → custo fixo
        ↓
   custo geral e custo unitário do produto
        ↓
4. Formação de preço (snapshot)
   + retorno sobre capital (fixo + giro × T.R.C.)
   ÷ (1 − % despesas de venda)
        ↓
   preço final · margem · ponto de equilíbrio
        ↓
   simulação gráfica · atualização do estoque
```

## Pontos abertos no schema atual

Três lacunas que o fluxo acima expõe e que valem decisão antes da implementação
dos cálculos:

O **rateio não é conferido**: nada garante que a soma de `previsao_custos_fixos`
para uma conta feche com o total real da conta no mês. Sem essa conferência, custo
fixo pode ficar sobrando ou faltando no produto sem ninguém perceber.

A **despesa de venda é única por cálculo**: `resultado_administrativo` guarda um
`tipo_despesa` e um `despesa_venda`. Acumular várias contas (frete + comissão +
cartão) exigiria uma tabela de composição, ou somar os percentuais aplicáveis
antes de calcular e gravar a soma com um rótulo agregado.

A **competência não chega ao snapshot**: `resultado_administrativo` tem
`data_calculo`, mas não registra de qual competência vieram os custos fixos
usados. Dois fechamentos do mesmo produto em datas próximas podem ter usado meses
de rateio diferentes sem que isso fique rastreável.
