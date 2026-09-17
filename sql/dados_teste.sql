USE analise_custos;

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE resultado_administrativo;
TRUNCATE TABLE composicao_centro_custo;
TRUNCATE TABLE composicao_materia_prima;
TRUNCATE TABLE produtos_para_venda;
TRUNCATE TABLE previsao_custos_fixos;
TRUNCATE TABLE previsao_reposicao_estoque;
TRUNCATE TABLE capital_de_giro;
TRUNCATE TABLE despesas_com_vendas;
TRUNCATE TABLE centro_custo;
TRUNCATE TABLE gastos_gerais;
TRUNCATE TABLE investimento_fixo;
TRUNCATE TABLE materia_prima;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO materia_prima (codigo, nome, unidade, custo_reposicao) VALUES
('MAD001', 'Madeira pinus', 'Kg',  12.5000),
('PAR001', 'Parafuso 4x40', 'un',   0.3000),
('TIN001', 'Tinta acrilica', 'L',  38.0000),
('COL001', 'Cola branca',    'L',  22.5000);

INSERT INTO investimento_fixo (tipo, valor) VALUES
('Maquina de corte',      45000.00),
('Torno mecanico',        32000.00),
('Compressor industrial',  8500.00),
('Veiculo de entrega',    68000.00),
('Moveis do escritorio',  12000.00);

INSERT INTO gastos_gerais (nome_conta) VALUES
('Aluguel'),
('Energia eletrica'),
('Salarios da producao'),
('Manutencao');

INSERT INTO centro_custo (nome, horas_efetivas) VALUES
('Corte',     160),
('Montagem',  160),
('Pintura',   120);

INSERT INTO despesas_com_vendas (nome_conta, percentual) VALUES
('Comissao de vendedores',  5.0000),
('Frete sobre vendas',      3.5000),
('Taxa de cartao',          2.5000),
('ICMS sobre faturamento', 18.0000);

INSERT INTO capital_de_giro (situacao, prazo_medio_dias, investimento_variavel, periodo) VALUES
('Cenario atual',           45.00, 45000.00, 30.00),
('Venda a vista',           15.00, 15000.00, 30.00),
('Prazo estendido 60 dias', 60.00, 60000.00, 30.00);

-- Rateio de marco/2026. custo_hora = valor / horas_efetivas, custo_minuto = custo_hora / 60
INSERT INTO previsao_custos_fixos
  (competencia, sequencia, id_gasto_geral, id_centro_custo, valor, custo_hora, custo_minuto) VALUES
('2026-03-01', 1, 1, 1, 3000.00, 18.7500, 0.312500),
('2026-03-01', 1, 2, 1, 1800.00, 11.2500, 0.187500),
('2026-03-01', 1, 1, 2, 2000.00, 12.5000, 0.208333),
('2026-03-01', 1, 2, 2,  400.00,  2.5000, 0.041667),
('2026-03-01', 1, 3, 2, 4800.00, 30.0000, 0.500000),
('2026-03-01', 1, 1, 3, 1200.00, 10.0000, 0.166667),
('2026-03-01', 1, 2, 3,  600.00,  5.0000, 0.083333);

-- gastos_totais = (quantidade x custo_reposicao) + outros_gastos
INSERT INTO previsao_reposicao_estoque
  (competencia, sequencia, id_materia_prima, quantidade, custo_reposicao, outros_gastos, gastos_totais) VALUES
('2026-03-01', 1, 1, 4000.000, 12.5000, 800.00, 50800.00),
('2026-03-01', 1, 2,  500.000,  0.3000,  20.00,   170.00),
('2026-03-01', 1, 3,  120.000, 38.0000, 150.00,  4710.00);

-- Cadeira: lote de 10000 unidades
-- custo_geral = 71250 (materia prima) + 12500 (transformacao) = 83750
-- custo_unitario = 83750 / 10000 = 8.375
INSERT INTO produtos_para_venda (codigo, nome, unidade, quantidade, custo_geral, custo_unitario) VALUES
('CAD001', 'Cadeira de madeira', 'un', 10000.000, 83750.00, 8.3750);

SET @produto = (SELECT id FROM produtos_para_venda WHERE codigo = 'CAD001');
SET @madeira  = (SELECT id FROM materia_prima WHERE codigo = 'MAD001');
SET @parafuso = (SELECT id FROM materia_prima WHERE codigo = 'PAR001');
SET @tinta    = (SELECT id FROM materia_prima WHERE codigo = 'TIN001');
SET @corte    = (SELECT id FROM centro_custo WHERE nome = 'Corte');
SET @montagem = (SELECT id FROM centro_custo WHERE nome = 'Montagem');
SET @pintura  = (SELECT id FROM centro_custo WHERE nome = 'Pintura');

-- Receita: total = quantidade x custo_reposicao
INSERT INTO composicao_materia_prima
  (id_produto_venda, id_materia_prima, quantidade, custo_reposicao, total) VALUES
(@produto, @madeira,  4000.000, 12.5000, 50000.00),
(@produto, @parafuso, 5000.000,  0.3000,  1500.00),
(@produto, @tinta,     500.000, 38.0000, 19000.00);

-- Roteiro: custo_minuto = soma das contas do centro; total = tempo_minutos x custo_minuto
-- Corte 0.3125 + 0.1875 = 0.50 | Montagem 0.208333 + 0.041667 + 0.50 = 0.75 | Pintura 0.166667 + 0.083333 = 0.25
INSERT INTO composicao_centro_custo
  (id_produto_venda, id_centro_custo, tempo_minutos, custo_minuto, total) VALUES
(@produto, @corte,    9000.00, 0.500000, 4500.00),
(@produto, @montagem, 8000.00, 0.750000, 6000.00),
(@produto, @pintura,  8000.00, 0.250000, 2000.00);

SELECT 'materia_prima' AS tabela, COUNT(*) AS linhas FROM materia_prima
UNION ALL SELECT 'investimento_fixo', COUNT(*) FROM investimento_fixo
UNION ALL SELECT 'gastos_gerais', COUNT(*) FROM gastos_gerais
UNION ALL SELECT 'centro_custo', COUNT(*) FROM centro_custo
UNION ALL SELECT 'despesas_com_vendas', COUNT(*) FROM despesas_com_vendas
UNION ALL SELECT 'capital_de_giro', COUNT(*) FROM capital_de_giro
UNION ALL SELECT 'previsao_custos_fixos', COUNT(*) FROM previsao_custos_fixos
UNION ALL SELECT 'previsao_reposicao_estoque', COUNT(*) FROM previsao_reposicao_estoque
UNION ALL SELECT 'produtos_para_venda', COUNT(*) FROM produtos_para_venda
UNION ALL SELECT 'composicao_materia_prima', COUNT(*) FROM composicao_materia_prima
UNION ALL SELECT 'composicao_centro_custo', COUNT(*) FROM composicao_centro_custo;
