/*==============================================================*/
/* DBMS name:      Mercadona - PostgreSQL 14.x                  */
/* Created on:     26/03/2024 13:31:13                          */
/*==============================================================*/


/*==============================================================*/
/* Table: monk                                                  */
/*==============================================================*/
CREATE TABLE monk
(
  monk_id                   bigserial      NOT NULL,
  monk_public_id            bigint    not null,
  monk_dictionary_id        bigint not null,
  week_number               INTEGER        null,
  monk_competitor_public_id bigserial   not null,
  monk_date                 DATE           null,
  number_of_units           NUMERIC(15, 2) null,
  amount                    NUMERIC(15, 2) null,
  price_per_unit            NUMERIC(15, 2) null,
  currency_iso3_alpha_code  CHAR(3)        not null,
  currency_id               VARCHAR(5)     null,
  currency_iso_numeric_code CHAR(3)        null,
  monk_public_geography_id  INTEGER        not null,
  region                    VARCHAR(512)   null,
  ticket_date               DATE           null,

  CONSTRAINT pk_monk PRIMARY KEY (monk_id)
);

COMMENT ON TABLE monk IS
  '(ES) MONK: Monk.';

COMMENT ON COLUMN monk.monk_public_id IS
  '(ES) IDENTIFICADOR PÚBLICO MONK: Identificador público de monk.';

COMMENT ON COLUMN monk.monk_dictionary_id IS
  '(ES) IDENTIFICADOR DICCIONARIO MONK: Identificador diccionario monk.';

COMMENT ON COLUMN monk.week_number IS
  '(ES) NÚMERO SEMANA: Número de semana.';

COMMENT ON COLUMN monk.monk_competitor_public_id IS
  '(ES) IDENTIFICADOR COMPETENCIA: Identificador público competencia monk.';

COMMENT ON COLUMN monk.monk_date IS
  '(ES) FECHA MONK: Fecha Monk.';

COMMENT ON COLUMN monk.number_of_units IS
  '(ES) NÚMERO UNIDADES COMPRADAS: Número de unidades compradas.';

COMMENT ON COLUMN monk.amount IS
  '(ES) NÚMERO PRECIO PAGADO: Número de precio pagado.';

COMMENT ON COLUMN monk.price_per_unit IS
  '(ES) NÚMERO PRECIO UNIDAD: Número de precio por unidad.';

COMMENT ON COLUMN monk.currency_iso3_alpha_code IS
  '(ES) CÓDIGO ISO DIVISA ALFA3: Código ISO (Alfanumérico)';

COMMENT ON COLUMN monk.currency_id IS
  '(ES) DIVISA_CÓDIGO DIVISA: Identificador de la divisa';

COMMENT ON COLUMN monk.currency_iso_numeric_code IS
  '(ES) DIVISA_CÓDIGO ISO NUMÉRICO: Código ISO Numérico';

COMMENT ON COLUMN monk.monk_public_geography_id IS
  '(ES) IDENTIFICADOR LÍMITE GEOGRÁFICO: Identificador límite geográfico.';

COMMENT ON COLUMN monk.region IS
  '(ES) NOMBRE PROVINCIA: Nombre de la provincia.';

COMMENT ON COLUMN monk.ticket_date IS
  '(ES) FECHA RECIBO COMPRA: Fecha recibo compra.';

/*==============================================================*/
/* Table: monk_competitor                                       */
/*==============================================================*/
CREATE TABLE monk_competitor
(
  monk_competitor_public_id               bigserial   not null,
  monk_competitor_sampling_type_public_id bigint null,
  public_competitor_name                  VARCHAR(510) null,
  public_competitor_report_name           VARCHAR(510) null,
  sampling_reports_competitor_order       NUMERIC(38) null,
  monks_reports_competitor_order          NUMERIC(38) null,
  version_number                          NUMERIC(38) null,
  competitor_color                        VARCHAR(40)  null,
  source_module_public_id                 VARCHAR(2)   null,
  CONSTRAINT pk_monk_competitor PRIMARY KEY (monk_competitor_public_id)
);

COMMENT ON TABLE monk_competitor IS
  '(ES) COMPETENCIA MONK: Competencia Monk.';

COMMENT ON COLUMN monk_competitor.monk_competitor_public_id IS
  '(ES) IDENTIFICADOR COMPETENCIA: Identificador público competencia.';

COMMENT ON COLUMN monk_competitor.monk_competitor_sampling_type_public_id IS
  '(ES) IDENTIFICADOR PÚBLICO MUESTREO COMPETENCIA MONK: Identificador público muestreo competencia monk.';

COMMENT ON COLUMN monk_competitor.public_competitor_name IS
  '(ES) NOMBRE COMPETENCIA PÚBLICO: Nombre público competencia.';

COMMENT ON COLUMN monk_competitor.public_competitor_report_name IS
  '(ES) NOMBRE COMPETENCIA REPORTE PÚBLICO: Nombre público reporte competencia.';

COMMENT ON COLUMN monk_competitor.sampling_reports_competitor_order IS
  '(ES) ORDEN INFORMES MUESTREO COMPETENCIA: Orden informe muestreo competencia.';

COMMENT ON COLUMN monk_competitor.monks_reports_competitor_order IS
  '(ES) ORDEN INFORMES MONKS COMPETENCIA: Orden informes monks competencia.';

COMMENT ON COLUMN monk_competitor.version_number IS
  '(ES) NÚMERO VERSIÓN: Número versión.';

COMMENT ON COLUMN monk_competitor.competitor_color IS
  '(ES) COLOR COMPETENCIA: Color competencia.';

COMMENT ON COLUMN monk_competitor.source_module_public_id IS
  '(ES) IDENTIFICADOR PÚBLICO MODULO ORIGEN: Identificador público módulo origen.';

/*==============================================================*/
/* Table: monk_competitor_sampling_type                         */
/*==============================================================*/
CREATE TABLE monk_competitor_sampling_type
(
  monk_competitor_sampling_type_public_id bigserial not null,
  CONSTRAINT pk_monk_competitor_sampling_type PRIMARY KEY (monk_competitor_sampling_type_public_id)
);

COMMENT ON TABLE monk_competitor_sampling_type IS
  '(ES) TIPO DE MUESTRO COMPETENCIA MONK: Tipo de muestreo competencia Monk.';

COMMENT ON COLUMN monk_competitor_sampling_type.monk_competitor_sampling_type_public_id IS
  '(ES) IDENTIFICADOR PÚBLICO MUESTREO COMPETENCIA MONK: Identificador público muetreo competencia monk.';

/*==============================================================*/
/* Table: monk_competitor_sampling_type_names                   */
/*==============================================================*/
CREATE TABLE monk_competitor_sampling_type_names
(
  monk_competitor_sampling_type_public_id bigserial not null,
  locale_language_code                    VARCHAR(6)    not null,
  name                                    VARCHAR(3999) null,
  CONSTRAINT pk_monk_competitor_sampling_type_names PRIMARY KEY (monk_competitor_sampling_type_public_id, locale_language_code)
);

COMMENT ON TABLE monk_competitor_sampling_type_names IS
  '(ES) TIPO DE MUESTREO COMPETENCIA MONK TRADUCCIÓN: Traducción tipo de muestreo competencia Monk.';

COMMENT ON COLUMN monk_competitor_sampling_type_names.monk_competitor_sampling_type_public_id IS
  '(ES) IDENTIFICADOR PÚBLICO MUESTREO COMPETENCIA MONK: Identificador público muestro competencia monk.';

COMMENT ON COLUMN monk_competitor_sampling_type_names.locale_language_code IS
  '(ES) CÓDIGO LOCALE: Codigo I18N de Idioma';

COMMENT ON COLUMN monk_competitor_sampling_type_names.name IS
  '(ES) NOMBRE TIPO MUESTREO COMPETENCIA MONK: Nombre tipo muestreo competencia monk.';

/*==============================================================*/
/* Table: monk_dictionary                                       */
/*==============================================================*/
CREATE TABLE monk_dictionary
(
  monk_dictionary_id      bigserial not null,
  monk_dictionary_type_id bigserial not null,
  monk_public_id                            bigint   not null,
  public_brand_name                         VARCHAR(510)  null,
  is_visible_on_reports                     BOOL          null,
  version_number          NUMERIC(38) null,
  gtin_code                                 VARCHAR(24)   null,
  promotion_id                              bigint   null,
  public_promotion_id                       VARCHAR(19)   null,
  public_category                           VARCHAR(3999) null,
  article_hierarchy_business_report_name    VARCHAR(510)  null,
  article_hierarchy_category_report_name    VARCHAR(510)  null,
  article_hierarchy_subcategory_report_name VARCHAR(510)  null,
  article_hierarchy_variety_report_name     VARCHAR(510)  null,
  article_hierarchy_subariety_report_name   VARCHAR(510)  null,
  has_been_hierarchy_changed_by_user        BOOL          null,
  monk_quantity                             INTEGER       null,
  CONSTRAINT pk_monk_dictionary PRIMARY KEY (monk_dictionary_id)
);

COMMENT ON TABLE monk_dictionary IS
  '(ES) DICCIONARIO MONK: Diccionario Monk,';

COMMENT ON COLUMN monk_dictionary.monk_dictionary_id IS
  '(ES) IDENTIFICADOR DICCIONARIO MONK: Identificador diccionario monk.';

COMMENT ON COLUMN monk_dictionary.monk_dictionary_type_id IS
  '(ES) IDENTIFICADOR TIPO DICCIONARIO MONK: Identificador tipo diccionario Monk.
  ';

COMMENT ON COLUMN monk_dictionary.public_brand_name IS
  '(ES) NOMBRE PÚBLICO MARCA: Nombre público marca.';

COMMENT ON COLUMN monk_dictionary.is_visible_on_reports IS
  '(ES) INDICADOR VISIBLE REPORTES: Marca asignada por el usuario para indicar si el producto debe aparecer en los informes.';

COMMENT ON COLUMN monk_dictionary.version_number IS
  '(ES) NÚMERO VERSIÓN: Número de versión.';

COMMENT ON COLUMN monk_dictionary.gtin_code IS
  '(ES) CÓDIGO GTIN: Código GTIN.';

COMMENT ON COLUMN monk_dictionary.promotion_id IS
  '(ES) IDENTIFICADOR PROMOCIÓN: Identificador promoción.';

COMMENT ON COLUMN monk_dictionary.public_promotion_id IS
  '(ES) PROMOCIÓN MONK_IDENTIFICADOR PÚBLICO PROMOCIÓN: Identificador público promoción.';

COMMENT ON COLUMN monk_dictionary.public_category IS
  '(ES) CATEGORÍA PÚBLICA: Nombre categoría pública.';

COMMENT ON COLUMN monk_dictionary.article_hierarchy_business_report_name IS
  '(ES) NOMBRE NEGOCIO: Nombre del negocio.';

COMMENT ON COLUMN monk_dictionary.article_hierarchy_category_report_name IS
  '(ES) NOMBRE CATEGORÍA: Nombre de la categoría.';

COMMENT ON COLUMN monk_dictionary.article_hierarchy_subcategory_report_name IS
  '(ES) NOMBRE SUBCATEGORÍA: Nombre de la subcategoría.';

COMMENT ON COLUMN monk_dictionary.article_hierarchy_variety_report_name IS
  '(ES) NOMBRE VARIEDAD: Nombre de la variedad.';

COMMENT ON COLUMN monk_dictionary.article_hierarchy_subariety_report_name IS
  '(ES) NOMBRE SUBVARIEDAD: Nombre de la subvariedad.';

COMMENT ON COLUMN monk_dictionary.has_been_hierarchy_changed_by_user IS
  '(ES) INDICADOR ESTRUCTURA MODIFICADA USUARIO: Indicador estructura modificada por el usuario.';

COMMENT ON COLUMN monk_dictionary.monk_quantity IS
  '(ES) NÚMERO MONKS: Número de monks.';

/*==============================================================*/
/* Table: monk_dictionary_public_names                          */
/*==============================================================*/
CREATE TABLE monk_dictionary_public_names
(
  locale_language_code VARCHAR(6)    not null,
  monk_dictionary_id bigserial not null,
  public_name          VARCHAR(3999) null,
  public_report_name   VARCHAR(3999) null,
  CONSTRAINT pk_monk_dictionary_public_names PRIMARY KEY (locale_language_code, monk_dictionary_id)
);

COMMENT ON TABLE monk_dictionary_public_names IS
  '(ES) DICCIONARIO MONK TRADUCCIÓN: Traducción Diccionario Monk.';

COMMENT ON COLUMN monk_dictionary_public_names.locale_language_code IS
  '(ES) CÓDIGO LOCALE: Codigo I18N de Idioma';

COMMENT ON COLUMN monk_dictionary_public_names.monk_dictionary_id IS
  '(ES) IDENTIFICADOR DICCIONARIO MONK: Identificador de diccionario monk.';

COMMENT ON COLUMN monk_dictionary_public_names.public_name IS
  '(ES) NOMBRE PÚBLICO DICCIONARIO MONK: Nombre público diccionario monk.';

COMMENT ON COLUMN monk_dictionary_public_names.public_report_name IS
  '(ES) NOMBRE PÚBLICO REPORTE  DICCIONARIO MONK: Nombre público reporte diccionario monk.';

/*==============================================================*/
/* Table: monk_dictionary_type                                  */
/*==============================================================*/
CREATE TABLE monk_dictionary_type
(
  monk_dictionary_type_id bigserial not null,
  CONSTRAINT pk_monk_dictionary_type PRIMARY KEY (monk_dictionary_type_id)
);

COMMENT ON TABLE monk_dictionary_type IS
  '(ES) TIPO DICCIONARIO MONK: Tipo diccionario Monk.';

COMMENT ON COLUMN monk_dictionary_type.monk_dictionary_type_id IS
  '(ES) IDENTIFICADOR TIPO DICCIONARIO MONK: Identificador tipo diccionario monk.';

/*==============================================================*/
/* Table: monk_dictionary_type_names                            */
/*==============================================================*/
CREATE TABLE monk_dictionary_type_names
(
  locale_language_code    VARCHAR(6)   not null,
  monk_dictionary_type_id bigserial not null,
  name                    VARCHAR(510) null,
  CONSTRAINT pk_monk_dictionary_type_names PRIMARY KEY (locale_language_code, monk_dictionary_type_id)
);

COMMENT ON TABLE monk_dictionary_type_names IS
  '(ES) TIPO DICCIONARIO MONK TRADUCCIÓN: Traducción tipo diccionario Monk.';

COMMENT ON COLUMN monk_dictionary_type_names.locale_language_code IS
  '(ES) CÓDIGO LOCALE: Codigo I18N de Idioma';

COMMENT ON COLUMN monk_dictionary_type_names.monk_dictionary_type_id IS
  '(ES) IDENTIFICADOR TIPO DICCIONARIO MONK: Identificador tipo diccionario monk.';

COMMENT ON COLUMN monk_dictionary_type_names.name IS
  '(ES) NOMBRE TIPO DICCIONARIO MONK: Nombre tipo diccionario monk.';

/*==============================================================*/
/* Table: monk_promotion                                        */
/*==============================================================*/
CREATE TABLE monk_promotion
(
  promotion_id        bigserial not null,
  public_promotion_id VARCHAR(19) null,
  is_active           BOOL        null,
  CONSTRAINT pk_monk_promotion PRIMARY KEY (promotion_id),
  CONSTRAINT ak_monk_promotion UNIQUE (public_promotion_id)
);

COMMENT ON TABLE monk_promotion IS
  '(ES) PROMOCIÓN MONK: Promoción Monk.';

COMMENT ON COLUMN monk_promotion.promotion_id IS
  '(ES) IDENTIFICADOR PROMOCIÓN: Identificador promoción.';

COMMENT ON COLUMN monk_promotion.public_promotion_id IS
  '(ES) IDENTIFICADOR PÚBLICO PROMOCIÓN: Identificador público promoción.';

/*==============================================================*/
/* Table: monk_promotion_names                                  */
/*==============================================================*/
CREATE TABLE monk_promotion_names
(
  locale_language_code VARCHAR(6)   not null,
  promotion_id         bigserial  not null,
  public_promotion_id  VARCHAR(19)  null,
  name                 VARCHAR(512) null,
  report_name          VARCHAR(512) null,
  CONSTRAINT pk_monk_promotion_names PRIMARY KEY (promotion_id, locale_language_code)
);

COMMENT ON TABLE monk_promotion_names IS
  '(ES) PROMOCIÓN MONK TRADUCCIÓN: Traducción Promoción Monk.';

COMMENT ON COLUMN monk_promotion_names.locale_language_code IS
  '(ES) CÓDIGO LOCALE: Codigo I18N de Idioma';

COMMENT ON COLUMN monk_promotion_names.promotion_id IS
  '(ES) IDENTIFICADOR PROMOCIÓN: Identificador promoción.';

COMMENT ON COLUMN monk_promotion_names.public_promotion_id IS
  '(ES) PROMOCIÓN MONK_IDENTIFICADOR PÚBLICO PROMOCIÓN: Identificador público promoción.';

COMMENT ON COLUMN monk_promotion_names.name IS
  '(ES) NOMBRE PROMOCIÓN MONK: Nombre promoción monk.';

COMMENT ON COLUMN monk_promotion_names.report_name IS
  '(ES) NOMBRE REPORTE PROMOCIÓN MONK: Nombre reporte promoción monk.';

/*==============================================================*/
/* Table: monk_public_geographical_boundaries                   */
/*==============================================================*/
CREATE TABLE monk_public_geographical_boundaries
(
  monk_public_geography_id INTEGER not null,
  CONSTRAINT pk_monk_public_geographical_boundaries PRIMARY KEY (monk_public_geography_id)
);

COMMENT ON TABLE monk_public_geographical_boundaries IS
  '(ES) LÍMITES GEOGRÁFICOS MONKS: Límite geográfico de un monk.';

COMMENT ON COLUMN monk_public_geographical_boundaries.monk_public_geography_id IS
  '(ES) IDENTIFICADOR LÍMITE GEOGRÁFICO: Identificador límite geográfico.';

/*==============================================================*/
/* Table: monk_public_geographical_boundaries_names             */
/*==============================================================*/
CREATE TABLE monk_public_geographical_boundaries_names
(
  monk_public_geography_id INTEGER      not null,
  locale_language_code     VARCHAR(6)   not null,
  name                     VARCHAR(510) null,
  CONSTRAINT pk_monk_public_geographical_boundaries_names PRIMARY KEY (monk_public_geography_id, locale_language_code)
);

COMMENT ON TABLE monk_public_geographical_boundaries_names IS
  '(ES) LÍMITES GEOGRÁFICOS MONKS TRADUCCIÓN: Traducción límite geográfico de un monk.';

COMMENT ON COLUMN monk_public_geographical_boundaries_names.monk_public_geography_id IS
  '(ES) IDENTIFICADOR LÍMITE GEOGRÁFICO: Identificador límite geográfico.';

COMMENT ON COLUMN monk_public_geographical_boundaries_names.locale_language_code IS
  '(ES) CÓDIGO LOCALE: Codigo I18N de Idioma';

COMMENT ON COLUMN monk_public_geographical_boundaries_names.name IS
  '(ES) NOMBRE LÍMITE GEOGRÁFICO: Traducción límite geográfico monk.';

ALTER TABLE monk
  ADD CONSTRAINT fk_monk_competitor_monk FOREIGN KEY (monk_competitor_public_id)
    REFERENCES monk_competitor (monk_competitor_public_id)
    ON DELETE restrict ON UPDATE restrict;

ALTER TABLE monk
  ADD CONSTRAINT fk_monk_dictionary_monk FOREIGN KEY (monk_dictionary_id)
    REFERENCES monk_dictionary (monk_dictionary_id)
    ON DELETE restrict ON UPDATE restrict;

ALTER TABLE monk
  ADD CONSTRAINT fk_monk_public_geographical_boundaries_monk FOREIGN KEY (monk_public_geography_id)
    REFERENCES monk_public_geographical_boundaries (monk_public_geography_id)
    ON DELETE restrict ON UPDATE restrict;

ALTER TABLE monk_competitor
  ADD CONSTRAINT fk_mc_sampling_type_monk_competitor FOREIGN KEY (monk_competitor_sampling_type_public_id)
    REFERENCES monk_competitor_sampling_type (monk_competitor_sampling_type_public_id)
    ON DELETE restrict ON UPDATE restrict;

ALTER TABLE monk_competitor_sampling_type_names
  ADD CONSTRAINT fk_monk_competitor_sampling_type_mcst_names FOREIGN KEY (monk_competitor_sampling_type_public_id)
    REFERENCES monk_competitor_sampling_type (monk_competitor_sampling_type_public_id)
    ON DELETE restrict ON UPDATE restrict;

ALTER TABLE monk_dictionary
  ADD CONSTRAINT fk_monk_dictionary_type_monk_dictionary FOREIGN KEY (monk_dictionary_type_id)
    REFERENCES monk_dictionary_type (monk_dictionary_type_id)
    ON DELETE restrict ON UPDATE restrict;

ALTER TABLE monk_dictionary
  ADD CONSTRAINT fk_monk_promotion_monk_dictionary FOREIGN KEY (promotion_id)
    REFERENCES monk_promotion (promotion_id)
    ON DELETE restrict ON UPDATE restrict;

ALTER TABLE monk_dictionary_public_names
  ADD CONSTRAINT fk_monk_dictionary_md_public_names FOREIGN KEY (monk_dictionary_id)
    REFERENCES monk_dictionary (monk_dictionary_id)
    ON DELETE restrict ON UPDATE restrict;

ALTER TABLE monk_dictionary_type_names
  ADD CONSTRAINT fk_monk_dictionary_type_mdt_names FOREIGN KEY (monk_dictionary_type_id)
    REFERENCES monk_dictionary_type (monk_dictionary_type_id)
    ON DELETE restrict ON UPDATE restrict;

ALTER TABLE monk_promotion_names
  ADD CONSTRAINT fk_monk_promotion_monk_promotion_names FOREIGN KEY (promotion_id)
    REFERENCES monk_promotion (promotion_id)
    ON DELETE restrict ON UPDATE restrict;

ALTER TABLE monk_public_geographical_boundaries_names
  ADD CONSTRAINT fk_monk_public_geographical_boundaries_mpgb_names FOREIGN KEY (monk_public_geography_id)
    REFERENCES monk_public_geographical_boundaries (monk_public_geography_id)
    ON DELETE restrict ON UPDATE restrict;

-- Table: BATCH_JOB_INSTANCE
-- DROP TABLE IF EXISTS BATCH_JOB_INSTANCE CASCADE;
CREATE TABLE IF NOT EXISTS BATCH_JOB_INSTANCE  (
                                                 JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,
                                                 VERSION BIGINT ,
                                                 JOB_NAME VARCHAR(100) NOT NULL,
  JOB_KEY VARCHAR(32) NOT NULL,
  constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
  ) ;

CREATE INDEX IF NOT EXISTS IX_BJI_JOB_NAME ON  BATCH_JOB_INSTANCE (JOB_NAME);
CREATE INDEX IF NOT EXISTS IX_BJI_JOB_KEY  ON  BATCH_JOB_INSTANCE (JOB_KEY);

-- Table: BATCH_JOB_EXECUTION
-- DROP TABLE IF EXISTS BATCH_JOB_EXECUTION CASCADE;
CREATE TABLE IF NOT EXISTS BATCH_JOB_EXECUTION  (
                                                  JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
                                                  VERSION BIGINT  ,
                                                  JOB_INSTANCE_ID BIGINT NOT NULL,
                                                  CREATE_TIME TIMESTAMP NOT NULL,
                                                  START_TIME TIMESTAMP DEFAULT NULL ,
                                                  END_TIME TIMESTAMP DEFAULT NULL ,
                                                  STATUS VARCHAR(10) ,
  EXIT_CODE VARCHAR(2500) ,
  EXIT_MESSAGE VARCHAR(2500) ,
  LAST_UPDATED TIMESTAMP,
  JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL,
  constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
  references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
  ) ;


CREATE INDEX IF NOT EXISTS IX_BJE_CREATE_TIME  ON  BATCH_JOB_EXECUTION (CREATE_TIME);
CREATE INDEX IF NOT EXISTS IX_BJE_JOB_INSTANCE_ID ON  BATCH_JOB_EXECUTION (JOB_INSTANCE_ID);

-- Table: BATCH_JOB_EXECUTION_PARAMS
-- DROP TABLE IF EXISTS BATCH_JOB_EXECUTION_PARAMS CASCADE;
CREATE TABLE IF NOT EXISTS BATCH_JOB_EXECUTION_PARAMS  (
                                                         JOB_EXECUTION_ID BIGINT NOT NULL ,
                                                         TYPE_CD VARCHAR(6) NOT NULL ,
  KEY_NAME VARCHAR(100) NOT NULL ,
  STRING_VAL VARCHAR(250) ,
  DATE_VAL TIMESTAMP DEFAULT NULL ,
  LONG_VAL BIGINT ,
  DOUBLE_VAL DOUBLE PRECISION ,
  IDENTIFYING CHAR(1) NOT NULL ,
  constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
  references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
  ) ;

-- Table: BATCH_STEP_EXECUTION
-- DROP TABLE IF EXISTS BATCH_STEP_EXECUTION CASCADE;
CREATE TABLE IF NOT EXISTS BATCH_STEP_EXECUTION  (
                                                   STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
                                                   VERSION BIGINT NOT NULL,
                                                   STEP_NAME VARCHAR(100) NOT NULL,
  JOB_EXECUTION_ID BIGINT NOT NULL,
  START_TIME TIMESTAMP NOT NULL ,
  END_TIME TIMESTAMP DEFAULT NULL ,
  STATUS VARCHAR(10) ,
  COMMIT_COUNT BIGINT ,
  READ_COUNT BIGINT ,
  FILTER_COUNT BIGINT ,
  WRITE_COUNT BIGINT ,
  READ_SKIP_COUNT BIGINT ,
  WRITE_SKIP_COUNT BIGINT ,
  PROCESS_SKIP_COUNT BIGINT ,
  ROLLBACK_COUNT BIGINT ,
  EXIT_CODE VARCHAR(2500) ,
  EXIT_MESSAGE VARCHAR(2500) ,
  LAST_UPDATED TIMESTAMP,
  constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
  references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
  ) ;

CREATE INDEX IF NOT EXISTS IX_BSE_VERSION ON   BATCH_STEP_EXECUTION (VERSION);
CREATE INDEX IF NOT EXISTS IX_BSE_STEP_NAME ON   BATCH_STEP_EXECUTION (STEP_NAME);
CREATE INDEX IF NOT EXISTS IX_BSE_JOB_EXECUTION_ID ON   BATCH_STEP_EXECUTION (JOB_EXECUTION_ID);

-- Table: BATCH_JOB_EXECUTION_CONTEXT
-- DROP TABLE IF EXISTS BATCH_STEP_EXECUTION_CONTEXT CASCADE;
CREATE TABLE IF NOT EXISTS BATCH_STEP_EXECUTION_CONTEXT  (
                                                           STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                                           SHORT_CONTEXT VARCHAR(2500) NOT NULL,
  SERIALIZED_CONTEXT TEXT ,
  constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
  references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
  ) ;

-- Table: BATCH_JOB_EXECUTION_CONTEXT
-- DROP TABLE IF EXISTS BATCH_JOB_EXECUTION_CONTEXT CASCADE;
CREATE TABLE IF NOT EXISTS BATCH_JOB_EXECUTION_CONTEXT  (
                                                          JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                                          SHORT_CONTEXT VARCHAR(2500) NOT NULL,
  SERIALIZED_CONTEXT TEXT ,
  constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
  references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
  ) ;

-- SEQUENCE: BATCH_STEP_EXECUTION_SEQ
-- DROP SEQUENCE IF EXISTS BATCH_STEP_EXECUTION_SEQ;
CREATE SEQUENCE IF NOT EXISTS BATCH_STEP_EXECUTION_SEQ MAXVALUE 9223372036854775807 NO CYCLE;

-- SEQUENCE: BATCH_JOB_EXECUTION_SEQ
-- DROP SEQUENCE IF EXISTS BATCH_JOB_EXECUTION_SEQ;
CREATE SEQUENCE IF NOT EXISTS BATCH_JOB_EXECUTION_SEQ MAXVALUE 9223372036854775807 NO CYCLE;

-- SEQUENCE: BATCH_JOB_SEQ
-- DROP SEQUENCE IF EXISTS BATCH_JOB_SEQ;
CREATE SEQUENCE IF NOT EXISTS BATCH_JOB_SEQ MAXVALUE 9223372036854775807 NO CYCLE;


CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE IF NOT EXISTS anmerc_files
(
  id                bigserial PRIMARY KEY,
  creation_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  upload_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  name              VARCHAR(255) NOT NULL,
  status            VARCHAR(20),
  amount_of_records INTEGER,
  file_type         VARCHAR(20),
  origin_type       VARCHAR(1),
  interval_type     VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS anmerc_files
(
  id                bigserial PRIMARY KEY,
  creation_date     TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  upload_date       TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  name              VARCHAR(255) NOT NULL,
  status            VARCHAR(20),
  amount_of_records INTEGER,
  file_type         VARCHAR(20),
  origin_type       VARCHAR(1),
  interval_type     VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS monk_files_work
(
  id                         bigserial PRIMARY KEY,
  monk_public_id             bigint,
  monk_date                  DATE,
  public_competitor_name     VARCHAR(255),
  product_name_pc            VARCHAR(255),
  product_name               VARCHAR(255),
  public_brand_name          VARCHAR(255),
  public_promotion_id        VARCHAR(255),
  gtin_code                  VARCHAR(255),
  number_of_units            INTEGER,
  amount                     DECIMAL(19, 2),
  price_per_unit             DECIMAL(19, 2),
  region_name                VARCHAR(255),
  monk_public_geography_name VARCHAR(255)
);

