# Análise de Custos — Lógica de Negócio

Documento de referência do domínio. Explica **o que o sistema calcula e por quê**,
usando as telas (`src/view`) e as tabelas (`sql/analise_custos.sql`) como apoio.

Origem: porte do sistema Delphi/dBASE do livro *"Análise de Custos em Delphi"*
(Paulo Roberto Munhoz). As referências de página apontam para o livro.

> **Sem código por enquanto.** Aqui só o raciocínio de negócio. As fórmulas
> aparecem em notação matemática, não em Java.

---

## 1. Para que serve o sistema

O sistema responde a **uma única pergunta**:

> *Por quanto eu preciso vender este produto?*

Um preço de venda honesto tem que cobrir tudo: a matéria-prima que entrou no
produto, o tempo de fábrica que ele consumiu, a fatia dos custos fixos da
empresa, o dinheiro parado em capital de giro, as despesas de vender e ainda
sobrar lucro.

O sistema faz isso em **quatro camadas**, e cada camada só depende da anterior:

```
CAMADA 1  Cadastros base          o que existe (produtos, centros, contas)
    ↓
CAMADA 2  Lançamentos periódicos  quanto se gastou neste mês
    ↓
CAMADA 3  Composição              quanto custa UM produto
    ↓
CAMADA 4  Formação de preço       por quanto vender
```

Essa é exatamente a divisão dos comentários do `sql/analise_custos.sql`.

---

## 2. Os dois tipos de custo

Toda a lógica gira em torno de uma distinção contábil clássica:

| | **Custo direto (variável)** | **Custo indireto (fixo)** |
|---|---|---|
| Exemplo | a argila do tijolo | o aluguel do galpão |
| Varia com a produção? | sim | não |
| Como chega no produto? | medindo | **rateando** |
| Tabela | `composicao_materia_prima` | `composicao_centro_custo` |

O custo direto é fácil: se o tijolo leva 2 kg de argila a R$ 3,00/kg, custou
R$ 6,00. Mede-se.

O custo indireto é o problema real. **O aluguel não sabe quantos tijolos você
fez.** É para resolver isso que existe o centro de custo.

---

## 3. Centro de custo — o coração do sistema

### O que é

> *"Este cadastro tem por objetivo controlar o tempo gasto por um produto em
> determinado centro de produção, como por exemplo o tempo gasto por um tijolo
> no forno de tijolos."* — livro, pág. 36

Um **centro de custo** é um setor da fábrica que consome recursos e processa
produtos: o forno, a prensa, a linha de montagem, a expedição.

### A ideia central: transformar dinheiro em tempo

O aluguel não sabe quantos tijolos você fez — **mas o forno sabe quantos minutos
ficou ligado.** Então:

1. Joga-se o custo fixo do mês dentro do centro de custo (R$)
2. Divide-se pelas horas que o centro trabalha (h) → **custo por hora** (R$/h)
3. Converte-se para minuto → **custo por minuto** (R$/min)
4. O produto que ficou 30 min no forno paga 30 × custo/min

**O tempo vira a régua que converte custo fixo em custo unitário.** Essa é a
única ideia realmente importante do sistema; o resto é contabilidade em volta.

### Horas efetivas

O campo `centro_custo.horas_efetivas` (`HEFE` no livro) é o **denominador de
todo o rateio**. Não são as horas do calendário — são as horas em que o centro
*efetivamente produz*, já descontadas paradas, manutenção e ociosidade.

Isso tem duas consequências práticas:

- **Nunca pode ser zero.** É divisor. Um centro com 0 horas quebra o cálculo.
  O schema tem `DEFAULT 0`, o que é uma armadilha a tratar na aplicação.
- **Mexer nele reprecifica tudo.** Alterar as horas efetivas muda o custo/hora,
  que muda o custo/minuto, que muda o custo de todo produto que passa por
  aquele centro. Não é um edit inocente.

**Tela:** `Cadastro_CentroCusto` — Código, Centro de custos, Horas Efetivas.
**Tabela:** `centro_custo` (`id`, `nome`, `horas_efetivas`).

---

## 4. A cadeia do custo, passo a passo

Esta é a espinha dorsal. Cada seta é uma tela e uma tabela.

```
   Contas de gastos gerais          "aluguel", "energia", "salários"
              │                      (só o NOME da conta)
              ▼
   Previsão de custos fixos         R$ 8.000 de aluguel,
              │                      no centro FORNO, em março
              ▼
   Custo por hora / Custo de Fábrica  ÷ horas efetivas
              │                        → R$/hora → R$/minuto
              ▼
   Composição de produtos            o tijolo ficou 30 min no forno
              │
              ▼
   Resultado                         preço de venda
```

### Passo 1 — Contas de gastos gerais

Apenas o **catálogo de nomes** de despesa. Sem valor: aqui só se diz que
"aluguel" existe como categoria.

**Tela:** `Cadastros_contasGerais` · **Tabela:** `gastos_gerais` (`nome_conta`)

> *Nota:* a tela tem o label errado — mostra "Tipo Investimento" onde deveria
> ser "Nome da Conta". O erro veio do livro (pág. 36) e foi reproduzido no
> porte. Vale corrigir.

### Passo 2 — Previsão de custos fixos

Onde o **valor em reais** entra no sistema. Cada lançamento diz:
*tal conta*, em *tal centro de custo*, em *tal mês*, custou *tanto*.

O botão **"Generalizar"** é a conveniência da tela: informando um mês inicial,
um mês final e um valor, ele replica o lançamento mês a mês — evita digitar
doze vezes o mesmo aluguel.

**Tela:** `PrevisaoCustosFixos` — Centro de Custo, Conta, Nº Mês, Até, Ano,
Valor, Generalizar
**Tabela:** `previsao_custos_fixos` (`competencia`, `id_gasto_geral`,
`id_centro_custo`, `valor`)

### Passo 3 — Custo por hora ("Custo de Fábrica")

O passo que faz a conversão. Cruza o dinheiro acumulado no centro com as horas
efetivas dele:

```
custo_por_hora.valor  =  Σ (custos fixos do centro no período)  ÷  horas_efetivas

custo_por_hora.custo_minuto  =  custo_por_hora.valor  ÷  60
```

**Por que 6 casas decimais.** `custo_minuto` é `DECIMAL(13,6)`, e no livro era
apenas 2 casas. A divisão por 60 quase sempre gera dízima — R$ 100,00/h ÷ 60 =
R$ 1,666666…/min. Como esse número depois é **multiplicado** pelo tempo de cada
produto, arredondar cedo espalha o erro. É a nota (13) do schema.

**Tela:** `CustoFabrica` · **Tabela:** `custo_por_hora` (`id_centro_custo`,
`id_gasto_geral`, `valor`, `custo_minuto`)

> **Premissa a confirmar:** o período do somatório. As fotos do livro não trazem
> a fórmula explícita, então ela foi derivada das unidades e dos tipos das
> colunas. A leitura adotada é **mensal** — horas efetivas por mês, dividindo os
> custos fixos daquela competência. Sustentam essa leitura: `previsao_custos_fixos`
> é mensal (mês/ano/sequência) e o botão "Generalizar" replica mês a mês.
> Se o negócio real usar base anual, muda o divisor — e só isso.

### Passo 4 — Composição do produto

Onde as duas pontas se encontram. Um produto para venda é montado com:

- **Matérias-primas** — quantidade × custo de reposição
- **Passagens por centros de custo** — minutos × custo por minuto

```
composicao_materia_prima.total  =  quantidade     × custo_reposicao
composicao_centro_custo.total   =  tempo_minutos  × custo_minuto
```

As duas colunas `total` são **colunas geradas** (`STORED`) — o banco calcula
sozinho, não há como gravar um total inconsistente com suas parcelas.

E daí sai o custo do produto:

```
produtos_para_venda.custo_geral     =  Σ(materia_prima.total) + Σ(centro_custo.total)
produtos_para_venda.custo_unitario  =  custo_geral ÷ quantidade
```

**Tabelas:** `composicao_materia_prima`, `composicao_centro_custo`,
`produtos_para_venda`
**Tela:** ainda **não existe** (ver seção 7)

### Passo 5 — Formação do preço

Com o custo do produto na mão, a tela `Resultado` acrescenta as camadas que não
são de fábrica — investimento fixo, capital de giro, previsão de reposição de
estoque, despesas com vendas, taxa de retorno, desconto programado — e chega ao
preço de venda com e sem desconto.

**Tela:** `Resultado` · **Tabelas:** `resultado_administrativo`, `resultado_grafico`

---

## 5. Cópias históricas: por que alguns dados são duplicados de propósito

Há colunas que parecem redundantes e **não são um erro**:

| Tabela | Coluna copiada | Por quê |
|---|---|---|
| `composicao_materia_prima` | `custo_reposicao` | congela o preço da época |
| `composicao_centro_custo` | `custo_minuto` | congela a tarifa da época |
| `previsao_reposicao_estoque` | `custo_reposicao` | congela o preço da época |
| `resultado_administrativo` | `nome_produto`, `codigo_produto`, `unidade` | snapshot datado |

A regra: **um cálculo já fechado não pode mudar sozinho.** Se a argila subir de
preço amanhã, a composição fechada mês passado tem que continuar mostrando o
custo daquele mês. Por isso o valor é copiado no momento do lançamento, em vez
de vir por JOIN.

Isso é o oposto da duplicação que o porte *eliminou* — `horas_efetivas`, que no
livro aparecia repetida dentro de `CustoHora.DBF` e agora vive só em
`centro_custo` (nota 14 do schema). A diferença: horas efetivas é um **atributo
atual do centro**, não uma foto de um cálculo passado.

---

## 6. Regras de integridade

### Não se apaga o que está em uso

`centro_custo` é referenciado por `custo_por_hora`, `previsao_custos_fixos` e
`composicao_centro_custo`. Nenhuma dessas FKs tem `ON DELETE CASCADE` — de
propósito. Excluir um centro em uso levaria junto histórico de custo já apurado.
O comportamento correto é **bloquear e explicar onde ele está sendo usado**.

### O que apaga em cascata

Só o que é *parte* de algo, e não faz sentido sozinho:

- `composicao_materia_prima` e `composicao_centro_custo` → morrem com o produto
- `resultado_grafico` → morre com o resultado que o gerou

No livro, isso era feito na unha, percorrendo as tabelas registro a registro
(pág. ~124). No porte, o banco faz.

### Nomes não se repetem

`centro_custo.nome`, `gastos_gerais.nome_conta`, `despesas_com_vendas.nome_conta`
e os códigos de produto têm `UNIQUE`. Nome duplicado é **erro de negócio
esperado** — precisa virar mensagem legível na tela, não stack trace.

### Um item por composição

`composicao_materia_prima` e `composicao_centro_custo` têm chave única por
(produto, item). O mesmo produto não pode listar a mesma matéria-prima duas
vezes; se a quantidade mudou, altera-se a linha existente.

---

## 7. Estado atual do porte

### Telas × tabelas

| Item de menu | Tela | Tabela | Situação |
|---|---|---|---|
| Produtos primários | `Cadastro_de_ProdutosPrimarios` | `produtos_primarios` | tela crua |
| Investimento fixo | `Investimento_fixo` | `investimento_fixo` | tela crua |
| Contas de gastos gerais | `Cadastros_contasGerais` | `gastos_gerais` | tela crua |
| **Centro de custos** | `Cadastro_CentroCusto` | `centro_custo` | **em andamento** |
| Previsão de reposição | `Previsão_ReposicaoEstoque` | `previsao_reposicao_estoque` | tela crua |
| Custo por hora | `CustoFabrica` | `custo_por_hora` | tela crua |
| Despesas com vendas | `FormdespVenda` | `despesas_com_vendas` | tela crua |
| Previsão de custos fixos | `PrevisaoCustosFixos` | `previsao_custos_fixos` | tela crua |
| Capital de giro | `CapitaldeGiro` | `capital_de_giro` | tela crua |
| Resultado | `Resultado` | `resultado_administrativo` | tela crua |
| **Composição de produtos** | — | `composicao_*` | **tela não existe** |

"Tela crua" = layout do NetBeans pronto, componentes com nome genérico
(`jTextField1`), handlers vazios, sem ligação com DAO.

Os 16 models e 16 DAOs estão completos e compilando.

### Pendências conhecidas

1. **Tela de Composição de Produtos não existe** — é a tela onde a cadeia toda
   se fecha, e o menu não a abre. Sem ela não há como calcular custo de produto.
2. **Itens de menu sem destino próprio** — os relatórios ("Listagem de produtos
   acabados", "Listagem de preços", "Composição de produtos acabados") ainda
   apontam para telas de cadastro, porque as telas de relatório não existem.
   A barra de ferramentas está ligada corretamente.
3. **Tabela `Qresult` do livro não foi portada** — era cópia temporária de
   `Result` para filtrar relatório sem SQL. Provavelmente desnecessária: com
   MySQL, isso é um `WHERE`.
4. **`horas_efetivas` aceita zero** — o `DEFAULT 0` do schema permite gravar um
   centro que quebra a divisão.
5. **Recálculo em cascata não definido** — ao mudar `horas_efetivas`, o
   `custo_por_hora` já gravado fica desatualizado. Falta decidir entre recalcular
   na hora, avisar, ou calcular sempre na leitura.
6. **Label errado** em `Cadastros_contasGerais` ("Tipo Investimento").

---

## 8. Diferenças em relação ao livro

O porte é fiel na estrutura e deliberado nos desvios. Todos estão comentados e
numerados no `sql/analise_custos.sql`.

| Livro (dBASE) | Porte (MySQL) | Motivo |
|---|---|---|
| `AUTOREG` como chave | `id INT AUTO_INCREMENT` | o banco gera, não a aplicação |
| `CENTROCUST C(50)` digitado | FK `id_centro_custo` | texto digitado não relaciona |
| `HEFE` repetido em `CustoHora` | só em `centro_custo` | evita horas divergentes |
| `MES C(20)` + `ANO N(4)` | `competencia DATE` | data é data |
| nome/unidade copiados na composição | vêm por JOIN | não é foto histórica |
| `CMIN F(13,2)` | `DECIMAL(13,6)` | dízima do ÷60 se propaga |
| totais calculados na aplicação | colunas `STORED` | não dá para gravar inconsistente |

Equivalência de tabelas:

| Livro | Porte |
|---|---|
| `ProdPrim.DBF` | `produtos_primarios` |
| `InvFixo.DBF` | `investimento_fixo` |
| `ContaCf.DBF` | `gastos_gerais` |
| `CentroCusto.dbf` | `centro_custo` |
| `PREST.dbf` | `previsao_reposicao_estoque` |
| `CustoHora.DBF` | `custo_por_hora` |
| `PCFIXO.dbf` | `previsao_custos_fixos` |
| `DespVenda.dbf` | `despesas_com_vendas` |
| `CapGiro.DBF` | `capital_de_giro` |
| `Estoque.DBF` | `cadastro_estoque` |
| `MatPrim.DBF` | `composicao_materia_prima` |
| `TABCC.dbf` | `composicao_centro_custo` |
| `ProdVen.DBF` | `produtos_para_venda` |
| `Result` | `resultado_administrativo` |
| `ResGraf` | `resultado_grafico` |
| `Qresult` | *(não portada)* |

---

## 9. Glossário

| Termo | Significado |
|---|---|
| **Centro de custo** | setor da fábrica que consome recursos e processa produtos |
| **Horas efetivas (HEFE)** | horas em que o centro realmente produz; divisor do rateio |
| **Custo por hora** | custo fixo do centro ÷ horas efetivas |
| **Custo por minuto (CMIN)** | custo por hora ÷ 60; a régua que precifica o tempo |
| **Rateio** | distribuir um custo indireto entre produtos por um critério — aqui, tempo |
| **Custo de reposição (CREP)** | quanto custa repor a matéria-prima hoje |
| **Cópia histórica** | valor duplicado de propósito para congelar um cálculo fechado |
| **Competência** | mês/ano a que um lançamento se refere |
| **Custo de fábrica** | outro nome para custo por hora (usado no livro e na tela) |
