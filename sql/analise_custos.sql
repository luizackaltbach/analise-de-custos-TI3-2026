
DROP DATABASE IF EXISTS analise_custos;
CREATE DATABASE analise_custos
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE analise_custos;

CREATE TABLE materia_prima (
  id                 INT           AUTO_INCREMENT,
  codigo             VARCHAR(15),
  nome               VARCHAR(50),
  unidade            VARCHAR(15),
  custo_reposicao    DECIMAL(13,4) DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE investimento_fixo (
  id    INT           AUTO_INCREMENT,
  tipo  VARCHAR(50),
  valor DECIMAL(13,2) DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE gastos_gerais (
  id         INT         AUTO_INCREMENT,
  nome_conta VARCHAR(50),
  PRIMARY KEY (id)
);

CREATE TABLE centro_custo (
  id             INT         AUTO_INCREMENT,
  nome           VARCHAR(50),
  horas_efetivas INT         DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE despesas_com_vendas (
  id         INT           AUTO_INCREMENT,
  nome_conta VARCHAR(50),
  percentual DECIMAL(9,4)  DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE capital_de_giro (
  id                    INT           AUTO_INCREMENT,
  situacao              VARCHAR(50),
  prazo_medio_dias      DECIMAL(9,2)  DEFAULT 0,
  investimento_variavel DECIMAL(13,2) DEFAULT 0,
  periodo               DECIMAL(9,2)  DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE previsao_reposicao_estoque (
  id               INT           AUTO_INCREMENT,
  competencia      DATE,
  sequencia        INT           DEFAULT 1,
  id_materia_prima INT REFERENCES materia_prima(id),
  quantidade       DECIMAL(12,3) DEFAULT 0,
  custo_reposicao  DECIMAL(13,4) DEFAULT 0,
  outros_gastos    DECIMAL(13,2) DEFAULT 0,
  gastos_totais    DECIMAL(13,2),
  PRIMARY KEY (id)
);

CREATE TABLE previsao_custos_fixos (
  id              INT           AUTO_INCREMENT,
  competencia     DATE,
  sequencia       INT           DEFAULT 1,
  id_gasto_geral  INT REFERENCES gastos_gerais(id),
  id_centro_custo INT REFERENCES centro_custo(id),
  valor           DECIMAL(13,2) DEFAULT 0,
  custo_hora      DECIMAL(13,4) DEFAULT 0,
  custo_minuto    DECIMAL(13,6) DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE produtos_para_venda (
  id             INT           AUTO_INCREMENT,
  codigo         VARCHAR(10),
  nome           VARCHAR(50),
  unidade        VARCHAR(15),
  quantidade     DECIMAL(12,3) DEFAULT 0,
  custo_geral    DECIMAL(13,2) DEFAULT 0,
  custo_unitario DECIMAL(13,4) DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE composicao_materia_prima (
  id               INT           AUTO_INCREMENT,
  id_produto_venda INT REFERENCES produtos_para_venda(id),
  id_materia_prima INT REFERENCES materia_prima(id),
  quantidade       DECIMAL(12,3) DEFAULT 0,
  custo_reposicao  DECIMAL(13,4) DEFAULT 0,
  total DECIMAL(13,2),
  PRIMARY KEY (id)
);

CREATE TABLE composicao_centro_custo (
  id               INT           AUTO_INCREMENT,
  id_produto_venda INT REFERENCES produtos_para_venda(id),
  id_centro_custo  INT REFERENCES centro_custo(id),
  tempo_minutos    DECIMAL(9,2)  DEFAULT 0,
  custo_minuto     DECIMAL(13,6) DEFAULT 0,
  total DECIMAL(13,2),
  PRIMARY KEY (id)
);

CREATE TABLE resultado_administrativo (
  id               INT      AUTO_INCREMENT,
  data_calculo     DATETIME DEFAULT CURRENT_TIMESTAMP,
  id_produto_venda INT REFERENCES produtos_para_venda(id),

  codigo_produto VARCHAR(30),
  nome_produto   VARCHAR(50),
  unidade        VARCHAR(15),

  total                 DECIMAL(13,2) DEFAULT 0,
  investimento_fixo     DECIMAL(13,2) DEFAULT 0,
  tipo_investimento     VARCHAR(50)   NULL,
  investimento_variavel DECIMAL(13,2) DEFAULT 0,
  total_reposicao       DECIMAL(13,2) DEFAULT 0,
  total_custo_fixo      DECIMAL(13,2) DEFAULT 0,
  percentual_fixo       DECIMAL(9,4)  DEFAULT 0,
  taxa_retorno_capital  DECIMAL(9,4)  DEFAULT 0,

  receita_liquida       DECIMAL(13,2) DEFAULT 0,
  tipo_despesa          VARCHAR(50)   NULL,
  despesa_venda         DECIMAL(13,2) DEFAULT 0,
  receita_bruta         DECIMAL(13,2) DEFAULT 0,
  preco_final_unitario  DECIMAL(13,4) DEFAULT 0,
  preco_venda_desconto  DECIMAL(13,4) DEFAULT 0,
  desconto_programado   DECIMAL(9,4)  DEFAULT 0,
  aumento               DECIMAL(9,4)  DEFAULT 0,

  ponto_equilibrio_variavel DECIMAL(13,2) DEFAULT 0,
  ponto_equilibrio_fixo     DECIMAL(13,2) DEFAULT 0,
  margem_lucro              DECIMAL(9,4)  DEFAULT 0,

  PRIMARY KEY (id)
);

