-- =====================================================================
--  ANÁLISE DE CUSTOS — schema MySQL
--  Porte do sistema Delphi/dBASE de Paulo Roberto Munhoz
-- =====================================================================
-- (*) Schema simplificado: restrições de NOT NULL e UNIQUE KEY, e os
--     cálculos (colunas antes GENERATED) foram removidos daqui e serão
--     reimplementados manualmente em Java futuramente. As referências
--     entre tabelas ficam como REFERENCES inline na própria coluna,
--     sem CONSTRAINT nomeada e sem ON DELETE CASCADE.

DROP DATABASE IF EXISTS analise_custos;
CREATE DATABASE analise_custos
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;          -- (1) charset explícito, acentuação previsível
USE analise_custos;


-- =====================================================================
--  CAMADA 1 — CADASTROS BASE (sem dependências)
-- =====================================================================

CREATE TABLE materia_prima (            -- (2) era "mateira_prima"
  id                 INT           AUTO_INCREMENT,
  codigo             VARCHAR(15),
  nome               VARCHAR(50),
  unidade            VARCHAR(15),
  quantidade_estoque DECIMAL(12,3) DEFAULT 0,
  custo_reposicao    DECIMAL(13,4) DEFAULT 0,   -- (3) 4 casas: valor unitário
  PRIMARY KEY (id)                                       -- (4) PK sempre "id"
) ENGINE=InnoDB;                                         -- (6) InnoDB: FK + transação


CREATE TABLE investimento_fixo (
  id    INT           AUTO_INCREMENT,
  tipo  VARCHAR(50),
  valor DECIMAL(13,2) DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB;


CREATE TABLE gastos_gerais (
  id         INT         AUTO_INCREMENT,
  nome_conta VARCHAR(50),
  PRIMARY KEY (id)
) ENGINE=InnoDB;


CREATE TABLE centro_custo (
  id             INT         AUTO_INCREMENT,
  nome           VARCHAR(50),
  horas_efetivas INT         DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB;


CREATE TABLE despesas_com_vendas (
  id         INT           AUTO_INCREMENT,       -- (7) era "autoreg" (herança dBASE)
  nome_conta VARCHAR(50),
  percentual DECIMAL(9,4)  DEFAULT 0,            -- (8) % sobre receita, 4 casas
  PRIMARY KEY (id)
) ENGINE=InnoDB;


CREATE TABLE capital_de_giro (
  id                    INT           AUTO_INCREMENT,
  situacao              VARCHAR(50),
  prazo_medio_dias      DECIMAL(9,2)  DEFAULT 0,
  investimento_variavel DECIMAL(13,2) DEFAULT 0,
  periodo               DECIMAL(9,2)  DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB;


-- =====================================================================
--  CAMADA 2 — LANÇAMENTOS PERIÓDICOS
-- =====================================================================

CREATE TABLE previsao_reposicao_estoque (
  id               INT           AUTO_INCREMENT,
  competencia      DATE,                -- (9) substitui mes+ano
  sequencia        INT           DEFAULT 1,
  id_materia_prima INT REFERENCES materia_prima(id),
  quantidade       DECIMAL(12,3) DEFAULT 0,
  custo_reposicao  DECIMAL(13,4) DEFAULT 0,      -- (10) cópia histórica, não FK
  outros_gastos    DECIMAL(13,2) DEFAULT 0,
  gastos_totais    DECIMAL(13,2),                -- (11) antes calculada (GENERATED); agora gravada pela aplicação
  PRIMARY KEY (id),
  KEY idx_pre_competencia (competencia)
) ENGINE=InnoDB;


CREATE TABLE custo_por_hora (
  id              INT           AUTO_INCREMENT,
  id_centro_custo INT REFERENCES centro_custo(id),   -- (12) era VARCHAR digitado, virou coluna int
  id_gasto_geral  INT REFERENCES gastos_gerais(id),  -- (12) era VARCHAR digitado, virou coluna int
  valor           DECIMAL(13,2) DEFAULT 0,
  custo_minuto    DECIMAL(13,6) DEFAULT 0,       -- (13) 6 casas: erro se multiplica
  PRIMARY KEY (id)
) ENGINE=InnoDB;
-- (14) horas_efetivas saiu daqui: já vive em centro_custo, não se copia


CREATE TABLE previsao_custos_fixos (
  id              INT           AUTO_INCREMENT,
  competencia     DATE,
  sequencia       INT           DEFAULT 1,
  id_gasto_geral  INT REFERENCES gastos_gerais(id),
  id_centro_custo INT REFERENCES centro_custo(id),
  valor           DECIMAL(13,2) DEFAULT 0,
  PRIMARY KEY (id),
  KEY idx_pcf_competencia (competencia)
) ENGINE=InnoDB;


-- =====================================================================
--  CAMADA 3 — COMPOSIÇÃO DE PRODUTOS
-- =====================================================================
-- (15) `produtos_primarios` foi removida: duplicava `materia_prima`
--      (mesmo codigo/nome/unidade/custo_reposicao, só "quantidade" no
--      lugar de "quantidade_estoque"). A composição do produto e a
--      previsão de reposição de estoque agora apontam para a mesma
--      tabela — um insumo só é cadastrado uma vez.

CREATE TABLE produtos_para_venda (
  id             INT           AUTO_INCREMENT,
  codigo         VARCHAR(10),
  nome           VARCHAR(50),
  unidade        VARCHAR(15),
  quantidade     DECIMAL(12,3) DEFAULT 0,
  custo_geral    DECIMAL(13,2) DEFAULT 0,
  custo_unitario DECIMAL(13,4) DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB;
-- (16) removidos produto_primario, codigo_custo e tempo (BIGINT soltos):
--      eram FKs disfarçadas. A relação agora vive nas duas tabelas abaixo.


CREATE TABLE composicao_materia_prima (
  id               INT           AUTO_INCREMENT,
  id_produto_venda INT REFERENCES produtos_para_venda(id),
  id_materia_prima INT REFERENCES materia_prima(id),  -- era id_produto_primario (nota 15)
  quantidade       DECIMAL(12,3) DEFAULT 0,
  custo_reposicao  DECIMAL(13,4) DEFAULT 0,       -- cópia histórica
  total DECIMAL(13,2),                            -- antes calculada (GENERATED); agora gravada pela aplicação
  PRIMARY KEY (id)
) ENGINE=InnoDB;
-- (19) unidade e nome da matéria-prima saíram: vinham copiados, agora vêm por JOIN


CREATE TABLE composicao_centro_custo (
  id               INT           AUTO_INCREMENT,
  id_produto_venda INT REFERENCES produtos_para_venda(id),
  id_centro_custo  INT REFERENCES centro_custo(id),
  tempo_minutos    DECIMAL(9,2)  DEFAULT 0,      -- (20) unidade explícita no nome
  custo_minuto     DECIMAL(13,6) DEFAULT 0,      -- cópia histórica
  total DECIMAL(13,2),                           -- antes calculada (GENERATED); agora gravada pela aplicação
  PRIMARY KEY (id)
) ENGINE=InnoDB;


CREATE TABLE cadastro_estoque (
  id                  INT           AUTO_INCREMENT,  -- (21) PK era o código VARCHAR
  codigo              VARCHAR(30),
  produto             VARCHAR(50),
  unidade             VARCHAR(20),
  quantidade_anterior DECIMAL(12,3) DEFAULT 0,
  quantidade          DECIMAL(12,3) DEFAULT 0,
  custo_anterior      DECIMAL(13,4) DEFAULT 0,
  custo               DECIMAL(13,4) DEFAULT 0,
  custo_medio         DECIMAL(13,4) DEFAULT 0,
  venda               DECIMAL(13,2) DEFAULT 0,
  total               DECIMAL(13,2) DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB;


-- =====================================================================
--  CAMADA 4 — FORMAÇÃO DE PREÇOS (snapshot)
-- =====================================================================

CREATE TABLE resultado_administrativo (
  id               INT      AUTO_INCREMENT,
  data_calculo     DATETIME DEFAULT CURRENT_TIMESTAMP,  -- (22) foto datada
  id_produto_venda INT REFERENCES produtos_para_venda(id),

  -- (23) nome/código/unidade ficam DUPLICADOS de propósito.
  --      Num snapshot isso é correto: se o produto for renomeado depois,
  --      o fechamento antigo deve continuar mostrando o nome da época.
  codigo_produto VARCHAR(30),
  nome_produto   VARCHAR(50),
  unidade        VARCHAR(15),

  -- custos apurados
  total                 DECIMAL(13,2) DEFAULT 0,
  investimento_fixo     DECIMAL(13,2) DEFAULT 0,
  tipo_investimento     VARCHAR(50)   NULL,
  investimento_variavel DECIMAL(13,2) DEFAULT 0,
  total_reposicao       DECIMAL(13,2) DEFAULT 0,
  total_custo_fixo      DECIMAL(13,2) DEFAULT 0,
  percentual_fixo       DECIMAL(9,4)  DEFAULT 0,   -- (24) percentuais em (9,4)
  taxa_retorno_capital  DECIMAL(9,4)  DEFAULT 0,

  -- receita e preço
  receita_liquida       DECIMAL(13,2) DEFAULT 0,
  tipo_despesa          VARCHAR(50)   NULL,
  despesa_venda         DECIMAL(13,2) DEFAULT 0,
  receita_bruta         DECIMAL(13,2) DEFAULT 0,
  preco_final_unitario  DECIMAL(13,4) DEFAULT 0,
  preco_venda_desconto  DECIMAL(13,4) DEFAULT 0,
  desconto_programado   DECIMAL(9,4)  DEFAULT 0,
  aumento               DECIMAL(9,4)  DEFAULT 0,

  -- indicadores
  ponto_equilibrio_variavel DECIMAL(13,2) DEFAULT 0,
  ponto_equilibrio_fixo     DECIMAL(13,2) DEFAULT 0,
  margem_lucro              DECIMAL(9,4)  DEFAULT 0,

  PRIMARY KEY (id),
  KEY idx_resadm_produto_data (id_produto_venda, data_calculo)   -- (25)
) ENGINE=InnoDB;
-- (26) removida a coluna "tipo" (VARCHAR sem semântica definida no original).
--      Se ela classificava o resultado, reintroduza com valores fechados.


CREATE TABLE resultado_grafico (
  id              INT           AUTO_INCREMENT,
  id_resultado    INT REFERENCES resultado_administrativo(id),  -- (27) pendura no snapshot pai
  quantidade_venda DECIMAL(13,2) DEFAULT 0,
  receita_total   DECIMAL(13,2) DEFAULT 0,
  custo_total     DECIMAL(13,2) DEFAULT 0,
  lucro_prejuizo  DECIMAL(13,2),        -- (28) antes calculada (GENERATED); agora gravada pela aplicação
  PRIMARY KEY (id)
) ENGINE=InnoDB;
-- (29) codigo/nome do produto saíram: vêm do snapshot pai por JOIN
