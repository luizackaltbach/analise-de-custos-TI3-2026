# Dados de teste

Conjunto para cadastrar pelas telas, na ordem abaixo. Cada etapa depende da
anterior: os combos de uma tela só têm opção se a tela anterior já foi
preenchida.

Os números foram escolhidos para o cálculo do Resultado fechar com margem de
contribuição positiva — o lote de 10.000 unidades dilui o capital investido.

---

## 1. Matéria-prima

| Código | Nome | Unidade | Custo Reposição |
|---|---|---|---|
| MAD001 | Madeira pinus | Kg | 12.50 |
| PAR001 | Parafuso 4x40 | un | 0.30 |
| TIN001 | Tinta acrílica | L | 38.00 |

O custo é **por unidade**: R$ 12,50 por quilo de madeira.

## 2. Centro de custos

| Nome | Horas Efetivas |
|---|---|
| Corte | 160 |
| Montagem | 160 |
| Pintura | 120 |

Horas efetivas do mês, já descontados feriados, manutenção e setup.

## 3. Contas de gastos gerais

| Nome da conta |
|---|
| Aluguel |
| Energia elétrica |
| Salários da produção |

Só o nome — o valor é lançado mês a mês na previsão de custos fixos.

## 4. Investimento fixo

| Tipo | Valor |
|---|---|
| Máquina de corte | 45000 |
| Torno mecânico | 32000 |
| Veículo de entrega | 68000 |

Soma: R$ 145.000.

## 5. Despesas com vendas

| Nome da conta | Percentual |
|---|---|
| Comissão de vendedores | 5 |
| Frete sobre vendas | 3.5 |
| ICMS sobre faturamento | 18 |

Grava-se **5** para 5%, não 0.05.

## 6. Capital de giro

| Situação | Prazo Médio | Período | Investimento Variável |
|---|---|---|---|
| Cenário atual | 45 | 30 | 45000 |
| Venda à vista | 15 | 30 | 15000 |

Mais de um cenário permite simular no Resultado como o prazo de recebimento
afeta o preço.

---

## 7. Previsão de custos fixos

Mês **3** até **3**, ano **2026**:

| Centro de Custo | Conta de gastos gerais | Valor |
|---|---|---|
| Corte | Aluguel | 3000 |
| Corte | Energia elétrica | 1800 |
| Montagem | Aluguel | 2000 |
| Montagem | Salários da produção | 4800 |
| Pintura | Aluguel | 1200 |
| Pintura | Energia elétrica | 600 |

O custo por minuto que a tela calcula:

| Centro | Soma do mês | Custo/hora | Custo/minuto |
|---|---|---|---|
| Corte | 4.800 | 30,00 | 0,500000 |
| Montagem | 6.800 | 42,50 | 0,708333 |
| Pintura | 1.800 | 15,00 | 0,250000 |

## 8. Previsão de reposição de estoque

Competência **01/03/2026**, sequência **1**:

| Matéria-prima | Quantidade | Custo Reposição | Outros Gastos |
|---|---|---|---|
| Madeira pinus | 4000 | 12.50 | 800 |
| Parafuso 4x40 | 500 | 0.30 | 20 |
| Tinta acrílica | 120 | 38.00 | 150 |

---

## 9. Composição de produtos

**Produto:** código `CAD001` · Cadeira de madeira · unidade `un` · quantidade do
lote **10000**

Matérias-primas (o custo vem do cadastro, você digita só a quantidade):

| Matéria-prima | Quantidade | Total gerado |
|---|---|---|
| Madeira pinus | 4000 | 50.000,00 |
| Parafuso 4x40 | 5000 | 1.500,00 |
| Tinta acrílica | 500 | 19.000,00 |

Centros de custo (o custo por minuto vem da previsão de custos fixos):

| Centro | Tempo (min) | Total gerado |
|---|---|---|
| Corte | 9000 | 4.500,00 |
| Montagem | 8000 | 5.666,67 |
| Pintura | 8000 | 2.000,00 |

Ao salvar:

```
custo_materia_prima = 70.500,00
custo_transformacao = 12.166,67
custo_geral         = 82.666,67
custo_unitario      = 8,27
```

---

## 10. Resultado

Selecione: **Cadeira de madeira** · **Cenário atual** · **Comissão de
vendedores**

Digite: T.R.C. **12** · Desconto programado **10**

O esperado:

| Campo | Valor aproximado |
|---|---|
| Total Repo. Estoque | 70.500,00 |
| Total Custo Fixo | 12.166,67 |
| Valor do Inv. Fixo | 145.000,00 |
| Inv. Variável | 45.000,00 |
| Receita Líquida | 82.894,67 |
| Receita Bruta | 87.257,55 |
| Preço V. s/Desc | 8,73 |
| Preço V. c/Desc | 7,85 |

O retorno por unidade é R$ 2,28 — os R$ 190.000 de capital investido,
remunerados a 12%, divididos pelas 10.000 unidades do lote.

---

## Observação sobre a escala

Se o lote for pequeno (100 unidades, por exemplo) contra um capital de
R$ 190.000, o retorno por unidade fica maior que o custo do produto e a margem
de contribuição sai negativa — o cálculo funciona, mas o resultado não tem
sentido econômico. O tamanho do lote precisa ser compatível com a escala do
investimento cadastrado.
