# Cálculos — Composição de produtos e Resultado

Documenta o que está implementado nas telas `CadastroComposicaoProdutos` e
`Resultado`, na ordem em que os valores são calculados. Os nomes entre
crases são os campos das tabelas correspondentes.

---

## 1. Composição de produtos

**Tela:** `CadastroComposicaoProdutos`
**Tabelas:** `produtos_para_venda`, `composicao_materia_prima`, `composicao_centro_custo`

Monta a ficha técnica do produto: de que ele é feito (receita) e por quais
setores passa (roteiro). Do cruzamento dos dois sai o custo de produzir uma
unidade.

### 1.1 O lote

O produto é cadastrado com uma `quantidade`, que é o **tamanho do lote** a que
toda a composição se refere. Se a quantidade é 100, as quantidades da receita e
os tempos do roteiro são os necessários para produzir 100 unidades.

### 1.2 Receita — `composicao_materia_prima`

Uma linha por insumo. O usuário escolhe a matéria-prima e digita a quantidade;
o custo vem do cadastro.

```
custo_reposicao = materia_prima.custo_reposicao
total           = quantidade × custo_reposicao
```

O `custo_reposicao` é **copiado** no momento do cadastro, não buscado por chave
estrangeira. É uma cópia histórica: se o insumo mudar de preço amanhã, esta
composição continua mostrando o custo praticado quando foi montada. Buscar o
preço atual a cada consulta faria o custo do produto mudar retroativamente.

### 1.3 Roteiro — `composicao_centro_custo`

Uma linha por setor. O usuário escolhe o centro de custo e digita o tempo em
minutos; o custo por minuto é calculado.

```
custo_minuto = Σ previsao_custos_fixos.custo_minuto
                 WHERE id_centro_custo = <centro escolhido>
                   AND competencia = <competência mais recente do centro>

total        = tempo_minutos × custo_minuto
```

A soma existe porque cada centro recebe rateio de **várias contas** de gasto
geral. Se o Corte tem aluguel (R$ 0,3125/min) e energia (R$ 0,1875/min), o
custo por minuto do Corte é R$ 0,50 — a soma das duas linhas.

A competência usada é a **mais recente** lançada para aquele centro. O valor
também é copiado no momento do cadastro, pela mesma razão da receita.

### 1.4 Fechamento — ao salvar

```
custo_materia_prima = Σ composicao_materia_prima.total
custo_transformacao = Σ composicao_centro_custo.total

custo_geral    = custo_materia_prima + custo_transformacao
custo_unitario = custo_geral / quantidade
```

**A distinção que importa:** `custo_materia_prima` é **custo variável** — só
existe se o produto for feito. `custo_transformacao` é **custo fixo rateado** —
o aluguel é pago mesmo com a máquina parada. Essa separação é o que permite
calcular o ponto de equilíbrio depois; se os dois forem misturados, o cálculo
de quantas unidades vender para empatar sai errado.

### 1.5 Exemplo

Produto: Cadeira, lote de 100 unidades.

| Receita | Quantidade | Custo unitário | Total |
|---|---|---|---|
| Madeira | 400 kg | 12,50 | 5.000,00 |
| Parafuso | 50 un | 0,30 | 15,00 |
| | | | **5.015,00** |

| Roteiro | Tempo | Custo/min | Total |
|---|---|---|---|
| Corte | 2000 min | 0,50 | 1.000,00 |
| Pintura | 800 min | 0,30 | 240,00 |
| | | | **1.240,00** |

```
custo_geral    = 5.015,00 + 1.240,00 = 6.255,00
custo_unitario = 6.255,00 / 100       =    62,55
```

Cada cadeira custa R$ 62,55 para produzir.

---

## 2. Resultado — formação de preço

**Tela:** `Resultado`
**Tabela:** `resultado_administrativo`

Parte do custo do produto e chega ao preço de venda, passando pela remuneração
do capital e pelas despesas sobre a venda. O resultado é gravado como
**snapshot**: `codigo_produto`, `nome_produto` e `unidade` são copiados de
propósito, para que um fechamento antigo continue legível depois de o produto
ser renomeado.

A tela exige três entradas do usuário: a taxa de retorno do capital (T.R.C.),
o desconto programado, e a escolha do produto, do cenário de capital de giro e
da conta de despesa de venda.

### 2.1 Custos apurados

Vêm da composição do produto (seção 1).

```
total_reposicao  = Σ composicao_materia_prima.total    (custo variável)
total_custo_fixo = Σ composicao_centro_custo.total     (custo fixo rateado)
total            = total_reposicao + total_custo_fixo

percentual_fixo  = total_custo_fixo / total × 100
```

`percentual_fixo` é indicador de risco operacional: quanto maior a fatia fixa,
mais a empresa sofre com queda de volume, porque o custo não cai junto.

Se `total` é zero, o produto não tem composição cadastrada e o cálculo para —
formar preço sobre custo zero não tem significado.

### 2.2 Remuneração do capital

```
investimento_fixo     = Σ investimento_fixo.valor
investimento_variavel = capital_de_giro.investimento_variavel

capital_investido   = investimento_fixo + investimento_variavel
retorno_exigido     = capital_investido × (taxa_retorno_capital / 100)
retorno_por_unidade = retorno_exigido / quantidade
```

A T.R.C. é o custo de oportunidade: o mínimo que o capital precisa render para
valer a pena estar no negócio em vez de aplicado. Preço que cobre custo mas não
remunera capital dá empresa que "não dá prejuízo" e mesmo assim destrói valor.

O capital de giro entra aqui junto com o investimento fixo. A máquina de
R$ 45.000 e os R$ 45.000 parados no ciclo operacional são tratados igual: os
dois são capital investido que precisa render.

### 2.3 Receita necessária — o markup divisor

Este é o ponto mais delicado do cálculo.

```
receita_liquida = total + retorno_por_unidade

p               = despesas_com_vendas.percentual / 100
receita_bruta   = receita_liquida / (1 − p)
despesa_venda   = receita_bruta − receita_liquida
```

`receita_liquida` é o que precisa **sobrar** depois de pagas as despesas de
venda. Como essas despesas são um percentual da receita bruta, não se pode
somá-las ao custo: o percentual incide sobre um valor que ainda não se conhece.

**Por que dividir e não multiplicar.** Custo de R$ 100 e comissão de 20%:

| | Somando (errado) | Dividindo (correto) |
|---|---|---|
| Preço | 100 + 20% = 120,00 | 100 / 0,80 = 125,00 |
| Comissão paga | 20% de 120 = 24,00 | 20% de 125 = 25,00 |
| Sobra | 96,00 — **faltam 4,00** | 100,00 — exato |

### 2.4 Preço final

```
preco_final_unitario = receita_bruta / quantidade
preco_venda_desconto = preco_final_unitario × (1 − desconto_programado / 100)

aumento      = desconto_programado / (100 − desconto_programado) × 100
margem_lucro = (receita_liquida − total) / receita_bruta × 100
```

`aumento` responde: se eu quero poder dar X% de desconto e ainda assim chegar
no preço necessário, quanto tenho que subir a tabela antes? Para 10% de
desconto o aumento necessário é 11,11%, não 10% — porque o desconto incide
sobre o preço já aumentado.

A margem é medida **sobre a receita bruta**, não sobre o custo. É assim que se
compara com margem de mercado e de concorrentes.

### 2.5 Ponto de equilíbrio

```
custo_variavel_unitario   = (total_reposicao / quantidade) + (preco_final_unitario × p)
margem_contribuicao       = preco_final_unitario − custo_variavel_unitario

ponto_equilibrio_fixo     = total_custo_fixo / margem_contribuicao      (unidades)
ponto_equilibrio_variavel = ponto_equilibrio_fixo × preco_final_unitario (R$)
```

O custo variável unitário inclui a despesa de venda porque ela também só existe
se houver venda. A margem de contribuição é quanto cada unidade vendida
"contribui" para pagar o bolo de custo fixo; dividir o custo fixo por ela dá
quantas unidades são necessárias para empatar.

---

## 3. Ordem de execução

O fluxo é sequencial — cada etapa consome o resultado da anterior. Calcular o
Resultado sem ter passado pelas etapas anteriores não funciona.

```
1. Cadastros base
   matéria-prima · centro de custo · contas de gastos gerais
   investimento fixo · despesas com vendas · capital de giro
        ↓
2. Lançamentos do mês (competência)
   previsão de reposição de estoque
   previsão de custos fixos  → gera custo_hora e custo_minuto por centro
        ↓
3. Composição do produto
   receita (quantidade × custo reposição)  → custo variável
   roteiro (minutos × custo minuto)        → custo fixo
        ↓
   custo_geral e custo_unitario do produto
        ↓
4. Formação de preço
   + retorno sobre o capital (fixo + giro, pela T.R.C.)
   ÷ (1 − % despesas de venda)
        ↓
   preço final · margem · ponto de equilíbrio
```

---

## 4. Decisões de implementação

**Custo por hora vive na previsão de custos fixos.** O schema original tinha uma
tabela `custo_por_hora` separada, com uma linha por par (centro, conta). Como
ela não tinha coluna de competência, o valor era sobrescrito a cada recálculo e
não havia histórico: se o aluguel subisse em maio, o custo por minuto de abril
se perdia. As colunas `custo_hora` e `custo_minuto` foram movidas para
`previsao_custos_fixos`, que já é por competência — o histórico passou a ser
consequência da estrutura, e a tabela separada foi removida.

**Um lançamento de custo fixo por mês.** A tela de previsão de custos fixos
aceita um intervalo de meses e grava uma linha por mês. Cada mês é um fato
contábil próprio: o aluguel de março é um custo, o de abril é outro, e o
cálculo do custo por minuto filtra por uma competência. O intervalo na tela é
só um atalho de digitação.

**Fora do escopo.** O controle de estoque por custo médio ponderado e a
simulação gráfica do ponto de equilíbrio não foram implementados; as tabelas
`cadastro_estoque` e `resultado_grafico` foram removidas do schema.
