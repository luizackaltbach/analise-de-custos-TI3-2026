-- =====================================================================
--  ANÁLISE DE CUSTOS — schema MySQL
--  Porte do sistema Delphi/dBASE de Paulo Roberto Munhoz
-- =====================================================================

DROP DATABASE IF EXISTS analise_custos;
CREATE DATABASE analise_custos
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;          -- (1) charset explícito, acentuação previsível
USE analise_custos;


-- =====================================================================
--  CAMADA 1 — CADASTROS BASE (sem dependências)
-- =====================================================================

CREATE TABLE materia_prima (            -- (2) era "mateira_prima"
  id                 INT           NOT NULL AUTO_INCREMENT,
  codigo             VARCHAR(15)   NOT NULL,
  nome               VARCHAR(50)   NOT NULL,
  unidade            VARCHAR(15)   NOT NULL,
  quantidade_estoque DECIMAL(12,3) NOT NULL DEFAULT 0,
  custo_reposicao    DECIMAL(13,4) NOT NULL DEFAULT 0,   -- (3) 4 casas: valor unitário
  PRIMARY KEY (id),                                      -- (4) PK sempre "id"
  UNIQUE KEY uk_materia_prima_codigo (codigo)            -- (5) código digitado não duplica
) ENGINE=InnoDB;                                         -- (6) InnoDB: FK + transação


CREATE TABLE investimento_fixo (
  id    INT           NOT NULL AUTO_INCREMENT,
  tipo  VARCHAR(50)   NOT NULL,
  valor DECIMAL(13,2) NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB;


CREATE TABLE gastos_gerais (
  id         INT         NOT NULL AUTO_INCREMENT,
  nome_conta VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_gastos_gerais_nome (nome_conta)
) ENGINE=InnoDB;


CREATE TABLE centro_custo (
  id             INT         NOT NULL AUTO_INCREMENT,
  nome           VARCHAR(50) NOT NULL,
  horas_efetivas INT         NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE KEY uk_centro_custo_nome (nome)
) ENGINE=InnoDB;


CREATE TABLE despesas_com_vendas (
  id         INT           NOT NULL AUTO_INCREMENT,       -- (7) era "autoreg" (herança dBASE)
  nome_conta VARCHAR(50)   NOT NULL,
  percentual DECIMAL(9,4)  NOT NULL DEFAULT 0,            -- (8) % sobre receita, 4 casas
  PRIMARY KEY (id),
  UNIQUE KEY uk_despesas_vendas_conta (nome_conta)
) ENGINE=InnoDB;


CREATE TABLE capital_de_giro (
  id                    INT           NOT NULL AUTO_INCREMENT,
  situacao              VARCHAR(50)   NOT NULL,
  prazo_medio_dias      DECIMAL(9,2)  NOT NULL DEFAULT 0,
  investimento_variavel DECIMAL(13,2) NOT NULL DEFAULT 0,
  periodo               DECIMAL(9,2)  NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB;


-- =====================================================================
--  CAMADA 2 — LANÇAMENTOS PERIÓDICOS
-- =====================================================================

CREATE TABLE previsao_reposicao_estoque (
  id               INT           NOT NULL AUTO_INCREMENT,
  competencia      DATE          NOT NULL,                -- (9) substitui mes+ano
  sequencia        INT           NOT NULL DEFAULT 1,
  id_materia_prima INT           NOT NULL,
  quantidade       DECIMAL(12,3) NOT NULL DEFAULT 0,
  custo_reposicao  DECIMAL(13,4) NOT NULL DEFAULT 0,      -- (10) cópia histórica, não FK
  outros_gastos    DECIMAL(13,2) NOT NULL DEFAULT 0,
  gastos_totais    DECIMAL(13,2)
      AS (ROUND(quantidade * custo_reposicao, 2) + outros_gastos) STORED,  -- (11)
  PRIMARY KEY (id),
  KEY idx_pre_competencia (competencia),
  CONSTRAINT fk_pre_materia_prima
    FOREIGN KEY (id_materia_prima) REFERENCES materia_prima(id)
) ENGINE=InnoDB;


CREATE TABLE custo_por_hora (
  id              INT           NOT NULL AUTO_INCREMENT,
  id_centro_custo INT           NOT NULL,                 -- (12) era VARCHAR digitado, virou FK
  id_gasto_geral  INT           NOT NULL,                 -- (12) era VARCHAR digitado, virou FK
  valor           DECIMAL(13,2) NOT NULL DEFAULT 0,
  custo_minuto    DECIMAL(13,6) NOT NULL DEFAULT 0,       -- (13) 6 casas: erro se multiplica
  PRIMARY KEY (id),
  CONSTRAINT fk_cph_centro_custo
    FOREIGN KEY (id_centro_custo) REFERENCES centro_custo(id),
  CONSTRAINT fk_cph_gasto_geral
    FOREIGN KEY (id_gasto_geral) REFERENCES gastos_gerais(id)
) ENGINE=InnoDB;
-- (14) horas_efetivas saiu daqui: já vive em centro_custo, não se copia


CREATE TABLE previsao_custos_fixos (
  id              INT           NOT NULL AUTO_INCREMENT,
  competencia     DATE          NOT NULL,
  sequencia       INT           NOT NULL DEFAULT 1,
  id_gasto_geral  INT           NOT NULL,
  id_centro_custo INT           NOT NULL,
  valor           DECIMAL(13,2) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  KEY idx_pcf_competencia (competencia),
  CONSTRAINT fk_pcf_gasto_geral
    FOREIGN KEY (id_gasto_geral) REFERENCES gastos_gerais(id),
  CONSTRAINT fk_pcf_centro_custo
    FOREIGN KEY (id_centro_custo) REFERENCES centro_custo(id)
) ENGINE=InnoDB;


-- =====================================================================
--  CAMADA 3 — COMPOSIÇÃO DE PRODUTOS
-- =====================================================================

CREATE TABLE produtos_primarios (
  id              INT           NOT NULL AUTO_INCREMENT,
  codigo          VARCHAR(10)   NOT NULL,
  nome            VARCHAR(50)   NOT NULL,
  unidade         VARCHAR(15)   NOT NULL,
  quantidade      DECIMAL(12,3) NOT NULL DEFAULT 0,
  custo_reposicao DECIMAL(13,4) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE KEY uk_prod_primario_codigo (codigo)
) ENGINE=InnoDB;


CREATE TABLE produtos_para_venda (
  id             INT           NOT NULL AUTO_INCREMENT,
  codigo         VARCHAR(10)   NOT NULL,
  nome           VARCHAR(50)   NOT NULL,
  unidade        VARCHAR(15)   NOT NULL,
  quantidade     DECIMAL(12,3) NOT NULL DEFAULT 0,
  custo_geral    DECIMAL(13,2) NOT NULL DEFAULT 0,
  custo_unitario DECIMAL(13,4) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE KEY uk_prod_venda_codigo (codigo)
) ENGINE=InnoDB;
-- (15) removidos produto_primario, codigo_custo e tempo (BIGINT soltos):
--      eram FKs disfarçadas. A relação agora vive nas duas tabelas abaixo.


CREATE TABLE composicao_materia_prima (
  id                  INT           NOT NULL AUTO_INCREMENT,
  id_produto_venda    INT           NOT NULL,
  id_produto_primario INT           NOT NULL,
  quantidade          DECIMAL(12,3) NOT NULL DEFAULT 0,
  custo_reposicao     DECIMAL(13,4) NOT NULL DEFAULT 0,   -- cópia histórica
  total DECIMAL(13,2) AS (ROUND(quantidade * custo_reposicao, 2)) STORED,
  PRIMARY KEY (id),
  UNIQUE KEY uk_cmp_item (id_produto_venda, id_produto_primario),  -- (16) sem item repetido
  CONSTRAINT fk_cmp_produto_venda
    FOREIGN KEY (id_produto_venda) REFERENCES produtos_para_venda(id)
    ON DELETE CASCADE,                                    -- (17) apagou produto, some a composição
  CONSTRAINT fk_cmp_produto_primario
    FOREIGN KEY (id_produto_primario) REFERENCES produtos_primarios(id)
) ENGINE=InnoDB;
-- (18) unidade e nome do produto primário saíram: vinham copiados, agora vêm por JOIN


CREATE TABLE composicao_centro_custo (
  id               INT           NOT NULL AUTO_INCREMENT,
  id_produto_venda INT           NOT NULL,
  id_centro_custo  INT           NOT NULL,
  tempo_minutos    DECIMAL(9,2)  NOT NULL DEFAULT 0,      -- (19) unidade explícita no nome
  custo_minuto     DECIMAL(13,6) NOT NULL DEFAULT 0,      -- cópia histórica
  total DECIMAL(13,2) AS (ROUND(tempo_minutos * custo_minuto, 2)) STORED,
  PRIMARY KEY (id),
  UNIQUE KEY uk_ccc_item (id_produto_venda, id_centro_custo),
  CONSTRAINT fk_ccc_produto_venda
    FOREIGN KEY (id_produto_venda) REFERENCES produtos_para_venda(id)
    ON DELETE CASCADE,
  CONSTRAINT fk_ccc_centro_custo
    FOREIGN KEY (id_centro_custo) REFERENCES centro_custo(id)
) ENGINE=InnoDB;


CREATE TABLE cadastro_estoque (
  id                  INT           NOT NULL AUTO_INCREMENT,  -- (20) PK era o código VARCHAR
  codigo              VARCHAR(30)   NOT NULL,
  produto             VARCHAR(50)   NOT NULL,
  unidade             VARCHAR(20)   NOT NULL,
  quantidade_anterior DECIMAL(12,3) NOT NULL DEFAULT 0,
  quantidade          DECIMAL(12,3) NOT NULL DEFAULT 0,
  custo_anterior      DECIMAL(13,4) NOT NULL DEFAULT 0,
  custo               DECIMAL(13,4) NOT NULL DEFAULT 0,
  custo_medio         DECIMAL(13,4) NOT NULL DEFAULT 0,
  venda               DECIMAL(13,2) NOT NULL DEFAULT 0,
  total               DECIMAL(13,2) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE KEY uk_estoque_codigo (codigo)
) ENGINE=InnoDB;


-- =====================================================================
--  CAMADA 4 — FORMAÇÃO DE PREÇOS (snapshot)
-- =====================================================================

CREATE TABLE resultado_administrativo (
  id               INT      NOT NULL AUTO_INCREMENT,
  data_calculo     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- (21) foto datada
  id_produto_venda INT      NOT NULL,

  -- (22) nome/código/unidade ficam DUPLICADOS de propósito.
  --      Num snapshot isso é correto: se o produto for renomeado depois,
  --      o fechamento antigo deve continuar mostrando o nome da época.
  codigo_produto VARCHAR(30) NOT NULL,
  nome_produto   VARCHAR(50) NOT NULL,
  unidade        VARCHAR(15) NOT NULL,

  -- custos apurados
  total                 DECIMAL(13,2) NOT NULL DEFAULT 0,
  investimento_fixo     DECIMAL(13,2) NOT NULL DEFAULT 0,
  tipo_investimento     VARCHAR(50)   NULL,
  investimento_variavel DECIMAL(13,2) NOT NULL DEFAULT 0,
  total_reposicao       DECIMAL(13,2) NOT NULL DEFAULT 0,
  total_custo_fixo      DECIMAL(13,2) NOT NULL DEFAULT 0,
  percentual_fixo       DECIMAL(9,4)  NOT NULL DEFAULT 0,   -- (23) percentuais em (9,4)
  taxa_retorno_capital  DECIMAL(9,4)  NOT NULL DEFAULT 0,

  -- receita e preço
  receita_liquida       DECIMAL(13,2) NOT NULL DEFAULT 0,
  tipo_despesa          VARCHAR(50)   NULL,
  despesa_venda         DECIMAL(13,2) NOT NULL DEFAULT 0,
  receita_bruta         DECIMAL(13,2) NOT NULL DEFAULT 0,
  preco_final_unitario  DECIMAL(13,4) NOT NULL DEFAULT 0,
  preco_venda_desconto  DECIMAL(13,4) NOT NULL DEFAULT 0,
  desconto_programado   DECIMAL(9,4)  NOT NULL DEFAULT 0,
  aumento               DECIMAL(9,4)  NOT NULL DEFAULT 0,

  -- indicadores
  ponto_equilibrio_variavel DECIMAL(13,2) NOT NULL DEFAULT 0,
  ponto_equilibrio_fixo     DECIMAL(13,2) NOT NULL DEFAULT 0,
  margem_lucro              DECIMAL(9,4)  NOT NULL DEFAULT 0,

  PRIMARY KEY (id),
  KEY idx_resadm_produto_data (id_produto_venda, data_calculo),   -- (24)
  CONSTRAINT fk_resadm_produto
    FOREIGN KEY (id_produto_venda) REFERENCES produtos_para_venda(id)
) ENGINE=InnoDB;
-- (25) removida a coluna "tipo" (VARCHAR sem semântica definida no original).
--      Se ela classificava o resultado, reintroduza com valores fechados.


CREATE TABLE resultado_grafico (
  id              INT           NOT NULL AUTO_INCREMENT,
  id_resultado    INT           NOT NULL,                 -- (26) pendura no snapshot pai
  quantidade_venda DECIMAL(13,2) NOT NULL DEFAULT 0,
  receita_total   DECIMAL(13,2) NOT NULL DEFAULT 0,
  custo_total     DECIMAL(13,2) NOT NULL DEFAULT 0,
  lucro_prejuizo  DECIMAL(13,2) AS (receita_total - custo_total) STORED,  -- (27)
  PRIMARY KEY (id),
  CONSTRAINT fk_resgraf_resultado
    FOREIGN KEY (id_resultado) REFERENCES resultado_administrativo(id)
    ON DELETE CASCADE
) ENGINE=InnoDB;
-- (28) codigo/nome do produto saíram: vêm do snapshot pai por JOIN
