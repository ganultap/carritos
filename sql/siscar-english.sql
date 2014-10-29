--
--ER/Studio 7.0 SQL Code Generation
-- Company :      geniar
-- Project :      Ciat2.dm1
-- Author :       rodrigo
--
-- Date Created : Friday, July 04, 2008 09:02:05
-- Target DBMS : Oracle 9i
--

-- 
-- TABLE: Accidents 
--

CREATE TABLE Accidents(
    acc_codigo              NUMBER(38, 0)    NOT NULL,
    acc_descripcion         VARCHAR2(250)    NOT NULL,
    acc_fecha_accidente     DATE             NOT NULL,
    acc_sitio_accidente     VARCHAR2(50)     NOT NULL,
    acc_numero_siniestro    NUMBER(38, 0),
    acc_total_muertos       VARCHAR2(18),
    acc_total_heridos       VARCHAR2(30),
    acc_cedula_conduc       NUMBER(38, 0)    NOT NULL,
    acc_nombre_conduct      VARCHAR2(50)     NOT NULL,
    acc_cargo_conduct       VARCHAR2(30)     NOT NULL,
    acc_valor_dano          NUMBER(38, 0)    NOT NULL,
    acc_deducibles_pesos    NUMBER(38, 0)    NOT NULL,
    acc_deducibles_dolar    NUMBER(38, 0),
    tpac_codigo             NUMBER(38, 0)    NOT NULL,
    accst_code              NUMBER(38, 0)    NOT NULL,
    cdd_id                  NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK55 PRIMARY KEY (acc_codigo)
)
;



-- 
-- TABLE: Accidents_Classes 
--

CREATE TABLE Accidents_Classes(
    clsacd_codigo    NUMBER(38, 0)    NOT NULL,
    clsacd_nombre    VARCHAR2(30)     NOT NULL,
    CONSTRAINT PK86 PRIMARY KEY (clsacd_codigo)
)
;



-- 
-- TABLE: Accidents_States 
--

CREATE TABLE Accidents_States(
    accst_code      NUMBER(38, 0)    NOT NULL,
    estac_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK42 PRIMARY KEY (accst_code)
)
;



-- 
-- TABLE: Accidents_Types 
--

CREATE TABLE Accidents_Types(
    tpac_codigo         NUMBER(38, 0)    NOT NULL,
    tpac_descripcion    VARCHAR2(50)     NOT NULL,
    tpac_nombre         VARCHAR2(30)     NOT NULL,
    CONSTRAINT PK56 PRIMARY KEY (tpac_codigo)
)
;



-- 
-- TABLE: Acts 
--

CREATE TABLE Acts(
    act_codigo              NUMBER(38, 0)    NOT NULL,
    act_fecha_reunion       DATE             NOT NULL,
    act_descripcion         VARCHAR2(100)    NOT NULL,
    act_observaciones       VARCHAR2(100),
    act_sanciones           VARCHAR2(60),
    act_aprobacion          VARCHAR2(30)     NOT NULL,
    act_fecha_aprobacion    DATE,
    act_numero_orden        NUMBER(38, 0),
    acc_codigo              NUMBER(38, 0),
    tpscts_codigo           NUMBER(38, 0)    NOT NULL,
    clsacd_codigo           NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK70 PRIMARY KEY (act_codigo)
)
;



-- 
-- TABLE: Acts_Types 
--

CREATE TABLE Acts_Types(
    tpscts_codigo    NUMBER(38, 0)    NOT NULL,
    tpscts_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK87 PRIMARY KEY (tpscts_codigo)
)
;



-- 
-- TABLE: Allocation_Types 
--

CREATE TABLE Allocation_Types(
    tpsi_codigo    NUMBER(38, 0)    NOT NULL,
    tpsi_nombre    VARCHAR2(50),
    CONSTRAINT PK75 PRIMARY KEY (tpsi_codigo)
)
;



-- 
-- TABLE: Allocations_Costs_Centers 
--

CREATE TABLE Allocations_Costs_Centers(
    cca_codigo    NUMBER(38, 0)    NOT NULL,
    cca_numero    NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK79 PRIMARY KEY (cca_codigo)
)
;



-- 
-- TABLE: Assign_Vehicles 
--

CREATE TABLE Assign_Vehicles(
    asn_codigo          NUMBER(38, 0)    NOT NULL,
    asn_numero_carne    VARCHAR2(30)     NOT NULL,
    slc_codigo          NUMBER(38, 0),
    CONSTRAINT PK72 PRIMARY KEY (asn_codigo)
)
;



-- 
-- TABLE: Brands 
--

CREATE TABLE Brands(
    mrc_id        NUMBER(10, 0)    NOT NULL,
    mrc_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK1 PRIMARY KEY (mrc_id)
)
;



-- 
-- TABLE: Cars_Rents 
--

CREATE TABLE Cars_Rents(
    alv_codigo          NUMBER(38, 0)    NOT NULL,
    alv_numero_carne    VARCHAR2(30)     NOT NULL,
    slc_codigo          NUMBER(38, 0),
    CONSTRAINT PK78 PRIMARY KEY (alv_codigo)
)
;



-- 
-- TABLE: Cities 
--

CREATE TABLE Cities(
    cdd_id        NUMBER(38, 0)    NOT NULL,
    pss_id        NUMBER(38, 0)    NOT NULL,
    cdd_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK5 PRIMARY KEY (cdd_id)
)
;



-- 
-- TABLE: Clients_Sales_Vehicles 
--

CREATE TABLE Clients_Sales_Vehicles(
    cvv_identificacacion     NUMBER(38, 0)       NOT NULL,
    cvv_nombre               VARCHAR2(20)        NOT NULL,
    cvv_telefono             VARCHAR2(20)        NOT NULL,
    cvv_direccion            VARCHAR2(20)        NOT NULL,
    cvv_mail                 VARCHAR2(20)        NOT NULL,
    cvv_valor_venta          DOUBLE PRECISION    NOT NULL,
    cvv_at_final             INTERVAL DAY (0) TO SECOND (0) NOT NULL,
    cvv_numero_licitacion    NUMBER(38, 0)       NOT NULL,
    cvv_placa_intra          NUMBER(38, 0)       NOT NULL,
    cvv_observaciones        VARCHAR2(100)       NOT NULL,
    vhc_codigo               NUMBER(38, 0)       NOT NULL,
    CONSTRAINT PK76 PRIMARY KEY (cvv_identificacacion)
)
;



-- 
-- TABLE: Cobros 
--

CREATE TABLE Cobros(
    cbr_Codigo    NUMBER(38, 0)       NOT NULL,
    cbr_valor     DOUBLE PRECISION    NOT NULL,
    cbr_fecha     DATE                NOT NULL,
    vhc_codigo    NUMBER(38, 0)       NOT NULL,
    lct_codigo    NUMBER(38, 0)       NOT NULL,
    tql_id        NUMBER(38, 0)       NOT NULL,
    CONSTRAINT PK170 PRIMARY KEY (cbr_Codigo)
)
;



-- 
-- TABLE: Costs_Centers_Vehicles 
--

CREATE TABLE Costs_Centers_Vehicles(
    vcc_codigo        NUMBER(38, 0)    NOT NULL,
    cca_codigo        NUMBER(38, 0),
    vcc_porcentaje    VARCHAR2(18)     NOT NULL,
    vcc_valor         NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK85 PRIMARY KEY (vcc_codigo)
)
;



-- 
-- TABLE: Countries 
--

CREATE TABLE Countries(
    pss_id        NUMBER(38, 0)    NOT NULL,
    pss_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK4 PRIMARY KEY (pss_id)
)
;



-- 
-- TABLE: Delivery_Vehicles 
--

CREATE TABLE Delivery_Vehicles(
    ntr_vh_              CHAR(10)         NOT NULL,
    ntr_kilome_actual    NUMBER(38, 0),
    ntr_fecha_inicio     DATE,
    ntr_fecha_termina    DATE,
    asn_codigo           NUMBER(38, 0)    NOT NULL,
    vhc_codigo           NUMBER(38, 0)    NOT NULL,
    alv_codigo           NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK71 PRIMARY KEY (ntr_vh_)
)
;



-- 
-- TABLE: Devolutions 
--

CREATE TABLE Devolutions(
    dvl_codigo              NUMBER(38, 0)       NOT NULL,
    dvl_fecha_entrega       DATE                NOT NULL,
    dvl_kilometraje         VARCHAR2(10)        NOT NULL,
    dvl_estado_veh          VARCHAR2(30)        NOT NULL,
    dvl_dev_tarif_asig      DOUBLE PRECISION,
    dvl_cobro_tarif_asig    DOUBLE PRECISION,
    tpsi_codigo             NUMBER(38, 0)       NOT NULL,
    asn_codigo              NUMBER(38, 0)       NOT NULL,
    alv_codigo              NUMBER(38, 0)       NOT NULL,
    vhc_codigo              NUMBER(38, 0),
    CONSTRAINT PK172 PRIMARY KEY (dvl_codigo)
)
;



-- 
-- TABLE: Fuels_Controls 
--

CREATE TABLE Fuels_Controls(
    cnt_cmb_codigo           NUMBER(38, 0)    NOT NULL,
    cntcmb_numero_galones    NUMBER(38, 0)    NOT NULL,
    cntcmb_numero_carne      VARCHAR2(50),
    cntcmb_nombre            VARCHAR2(50),
    cntcmb_placa             VARCHAR2(50),
    cntcmb_numero_tl         VARCHAR2(50),
    srt_codigo               NUMBER           NOT NULL,
    CONSTRAINT PK81 PRIMARY KEY (cnt_cmb_codigo)
)
;



-- 
-- TABLE: Fuels_Types 
--

CREATE TABLE Fuels_Types(
    tc_codigo    NUMBER(38, 0)    NOT NULL,
    tc_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK21 PRIMARY KEY (tc_codigo)
)
;



-- 
-- TABLE: Insurance_Car 
--

CREATE TABLE Insurance_Car(
    ase_codigo    NUMBER(38, 0)    NOT NULL,
    ase_nombre    VARCHAR2(50),
    CONSTRAINT PK83 PRIMARY KEY (ase_codigo)
)
;



-- 
-- TABLE: Involved_Vehicles 
--

CREATE TABLE Involved_Vehicles(
    vhinv_placa                 VARCHAR2(30)     NOT NULL,
    vhinv_marca                 VARCHAR2(30)     NOT NULL,
    vhinv_modelo                DATE             NOT NULL,
    vhinv_conductor             VARCHAR2(50)     NOT NULL,
    vhinv_identif_conduc        VARCHAR2(25)     NOT NULL,
    vhinv_telef_conduc          VARCHAR2(20)     NOT NULL,
    vhinv_identif_prop          VARCHAR2(20)     NOT NULL,
    vhinv_propietario           VARCHAR2(20)     NOT NULL,
    vhinv_direccionconductor    VARCHAR2(50)     NOT NULL,
    cdd_id                      NUMBER(38, 0)    NOT NULL,
    acc_codigo                  NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK58 PRIMARY KEY (vhinv_placa)
)
;



-- 
-- TABLE: Legatees_Types_Tariffs_Tipes 
--

CREATE TABLE Legatees_Types_Tariffs_Tipes(
    tpsi_codigo    NUMBER(38, 0)    NOT NULL,
    trf_id         NUMBER(10, 0)    NOT NULL,
    CONSTRAINT PK171 PRIMARY KEY (tpsi_codigo, trf_id)
)
;



-- 
-- TABLE: Lines 
--

CREATE TABLE Lines(
    lns_id        NUMBER(38, 0)    NOT NULL,
    mrc_id        NUMBER(10, 0)    NOT NULL,
    lns_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK9 PRIMARY KEY (lns_id)
)
;



-- 
-- TABLE: Locations 
--

CREATE TABLE Locations(
    plc_codigo    NUMBER(38, 0)    NOT NULL,
    plc_nombre    VARCHAR2(50)     NOT NULL,
    lct_codigo    NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK168 PRIMARY KEY (plc_codigo)
)
;



-- 
-- TABLE: Locations_Types 
--

CREATE TABLE Locations_Types(
    lct_codigo    NUMBER(38, 0)    NOT NULL,
    lct_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK164 PRIMARY KEY (lct_codigo)
)
;



-- 
-- TABLE: Modules 
--

CREATE TABLE Modules(
    mns_codigo    NUMBER(38, 0)    NOT NULL,
    mns_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK51 PRIMARY KEY (mns_codigo)
)
;



-- 
-- TABLE: Options 
--

CREATE TABLE Options(
    pcn_codigo    NUMBER(38, 0)    NOT NULL,
    mns_codigo    NUMBER(38, 0)    NOT NULL,
    pcn_nombre    VARCHAR2(100),
    CONSTRAINT PK24 PRIMARY KEY (pcn_codigo)
)
;



-- 
-- TABLE: Options_Rolls 
--

CREATE TABLE Options_Rolls(
    rls_codigo    NUMBER(38, 0)    NOT NULL,
    pcn_codigo    NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK27 PRIMARY KEY (rls_codigo, pcn_codigo)
)
;



-- 
-- TABLE: Person_Involved_Ciat 
--

CREATE TABLE Person_Involved_Ciat(
    lsdct_identificacion    NUMBER(38, 0)    NOT NULL,
    lsdct_nombre            VARCHAR2(50)     NOT NULL,
    lsdct_direccion         VARCHAR2(100)    NOT NULL,
    lsdct_telefono          VARCHAR2(30)     NOT NULL,
    lsdct_edad              NUMBER(38, 0)    NOT NULL,
    lsdct_sitio_atencion    VARCHAR2(50)     NOT NULL,
    vhc_codigo              NUMBER(38, 0),
    cdd_id                  NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK111 PRIMARY KEY (lsdct_identificacion)
)
;



-- 
-- TABLE: Person_Involved_Vehicles 
--

CREATE TABLE Person_Involved_Vehicles(
    dlv_identificacion    NUMBER(38, 0)    NOT NULL,
    dlv_nombre            VARCHAR2(30)     NOT NULL,
    dlv_direccion         VARCHAR2(100)    NOT NULL,
    dlv_telefono          VARCHAR2(20)     NOT NULL,
    dlv_edad              NUMBER(38, 0)    NOT NULL,
    dlv_sitioatencion     VARCHAR2(30)     NOT NULL,
    cdd_id                NUMBER(38, 0)    NOT NULL,
    vhinv_placa           VARCHAR2(30)     NOT NULL,
    CONSTRAINT PK59 PRIMARY KEY (dlv_identificacion)
)
;



-- 
-- TABLE: Polices_Types 
--

CREATE TABLE Polices_Types(
    tpplz_codigo    NUMBER(38, 0)    NOT NULL,
    tpplz_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK61 PRIMARY KEY (tpplz_codigo)
)
;



-- 
-- TABLE: Policies 
--

CREATE TABLE Policies(
    plz_codigo                   NUMBER(38, 0)       NOT NULL,
    plz_numero                   NUMBER(38, 0)       NOT NULL,
    plz_numero_factura           NUMBER(38, 0)       NOT NULL,
    plz_fecha_factura            DATE                NOT NULL,
    plz_fechaInicio_cobertura    DATE                NOT NULL,
    plz_fechafin_cobertura       DATE                NOT NULL,
    plz_concepto_factura         VARCHAR2(100)       NOT NULL,
    plz_valor_Asegurado          DOUBLE PRECISION    NOT NULL,
    plz_valor_prima              DOUBLE PRECISION    NOT NULL,
    plz_valor_comercial_ciat     DOUBLE PRECISION    NOT NULL,
    tpplz_codigo                 NUMBER(38, 0)       NOT NULL,
    CONSTRAINT PK62 PRIMARY KEY (plz_codigo)
)
;



-- 
-- TABLE: Policies_Vehicles 
--

CREATE TABLE Policies_Vehicles(
    vhc_codigo    NUMBER(38, 0)    NOT NULL,
    plz_codigo    NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK84 PRIMARY KEY (vhc_codigo, plz_codigo)
)
;



-- 
-- TABLE: Pumps 
--

CREATE TABLE Pumps(
    srt_codigo             NUMBER           NOT NULL,
    srt_nombre             VARCHAR2(30)     NOT NULL,
    srt_veces_utilizado    NUMBER(38, 0),
    tnq_codigo             NUMBER(38, 0),
    CONSTRAINT PK69 PRIMARY KEY (srt_codigo)
)
;



-- 
-- TABLE: Requests 
--

CREATE TABLE Requests(
    slc_codigo                NUMBER(38, 0)    NOT NULL,
    slc_fecha_solicitud       DATE             NOT NULL,
    slc_placa                 VARCHAR2(50),
    slc_descripcion           VARCHAR2(100)    NOT NULL,
    slc_carnet_empleado       VARCHAR2(100)    NOT NULL,
    slc_carnet_asignatario    VARCHAR2(100)    NOT NULL,
    slc_fecha_inicial         DATE             NOT NULL,
    slc_fecha_final           DATE             NOT NULL,
    cls_codigo                NUMBER(38, 0)    NOT NULL,
    tpslt_codigo              NUMBER(38, 0)    NOT NULL,
    tpsi_codigo               NUMBER(38, 0)    NOT NULL,
    ests_codigo               NUMBER(38, 0)    NOT NULL,
    usr_codigo                NUMBER(38, 0),
    vcc_codigo                NUMBER(38, 0),
    slc_nit                   VARCHAR2(50),
    tvch_codigo               NUMBER(38, 0),
    CONSTRAINT PK74 PRIMARY KEY (slc_codigo)
)
;



-- 
-- TABLE: Requests_Classes 
--

CREATE TABLE Requests_Classes(
    cls_codigo    NUMBER(38, 0)    NOT NULL,
    cls_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK38 PRIMARY KEY (cls_codigo)
)
;



-- 
-- TABLE: Requests_States 
--

CREATE TABLE Requests_States(
    ests_codigo    NUMBER(38, 0)    NOT NULL,
    ests_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK35 PRIMARY KEY (ests_codigo)
)
;



-- 
-- TABLE: Requests_Types 
--

CREATE TABLE Requests_Types(
    tpslt_codigo    NUMBER(38, 0)    NOT NULL,
    tpslt_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK39 PRIMARY KEY (tpslt_codigo)
)
;



-- 
-- TABLE: Retirements_Reasons 
--

CREATE TABLE Retirements_Reasons(
    mtrt_codigo    NUMBER(38, 0)    NOT NULL,
    mtrt_nombre    VARCHAR2(50)     NOT NULL,
    vhc_codigo     NUMBER(38, 0),
    CONSTRAINT PK47 PRIMARY KEY (mtrt_codigo)
)
;



-- 
-- TABLE: Rolls 
--

CREATE TABLE Rolls(
    rls_codigo    NUMBER(38, 0)    NOT NULL,
    rls_nombre    VARCHAR2(30)     NOT NULL,
    CONSTRAINT PK22 PRIMARY KEY (rls_codigo)
)
;



-- 
-- TABLE: Soat 
--

CREATE TABLE Soat(
    soa_codigo                NUMBER(38, 0)    NOT NULL,
    soa_fecha_inicio          DATE             NOT NULL,
    soa_fecha_terminacion     DATE             NOT NULL,
    soa_valor_prima           NUMBER(38, 0)    NOT NULL,
    soa_valor_contribucion    NUMBER(38, 0)    NOT NULL,
    soa_valor_total           NUMBER(38, 0)    NOT NULL,
    soa_documento1            VARCHAR2(100),
    soa_documento2            VARCHAR2(100),
    soa_numero                NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK63 PRIMARY KEY (soa_codigo)
)
;



-- 
-- TABLE: Tanks 
--

CREATE TABLE Tanks(
    tnq_codigo                   NUMBER(38, 0)    NOT NULL,
    tnq_nombre                   VARCHAR2(30)     NOT NULL,
    tnq_capacidad_maxima         NUMBER(38, 0)    NOT NULL,
    tnq_galones_suministrados    NUMBER(38, 0),
    tnq_total_combustible        NUMBER(38, 0)    NOT NULL,
    tc_codigo                    NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK67 PRIMARY KEY (tnq_codigo)
)
;



-- 
-- TABLE: Tapestries_Types 
--

CREATE TABLE Tapestries_Types(
    tptpc_codigo    NUMBER(38, 0)    NOT NULL,
    tptpc_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK46 PRIMARY KEY (tptpc_codigo)
)
;



-- 
-- TABLE: Tariff_Types 
--

CREATE TABLE Tariff_Types(
    tql_id        NUMBER(38, 0)    NOT NULL,
    tql_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK8 PRIMARY KEY (tql_id)
)
;



-- 
-- TABLE: Tariffs 
--

CREATE TABLE Tariffs(
    trf_id              NUMBER(10, 0)       NOT NULL,
    tql_id              NUMBER(38, 0)       NOT NULL,
    trf_valor           DOUBLE PRECISION    NOT NULL,
    trf_año_vigencia    DATE,
    trf_fecha_inicio    DATE                NOT NULL,
    trf_fecha_fin       DATE                NOT NULL,
    tptcr_codigo        NUMBER(38, 0),
    tptr_codigo         NUMBER(38, 0),
    zn_id               NUMBER(38, 0),
    tvch_codigo         NUMBER(38, 0),
    lns_id              NUMBER(38, 0),
    plc_codigo          NUMBER(38, 0),
    tc_codigo           NUMBER(38, 0),
    
    CONSTRAINT PK2 PRIMARY KEY (trf_id)
)
;



-- 
-- TABLE: Tractions_Types 
--

CREATE TABLE Tractions_Types(
    tptcr_codigo    NUMBER(38, 0)    NOT NULL,
    tptcr_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK45 PRIMARY KEY (tptcr_codigo)
)
;



-- 
-- TABLE: Transmissions_Types 
--

CREATE TABLE Transmissions_Types(
    tptr_codigo    NUMBER(38, 0)    NOT NULL,
    tptr_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK44 PRIMARY KEY (tptr_codigo)
)
;



-- 
-- TABLE: Users 
--

CREATE TABLE Users(
    usr_codigo            NUMBER(38, 0)    NOT NULL,
    rls_codigo            NUMBER(38, 0)    NOT NULL,
    usr_identificacion    VARCHAR2(20)     NOT NULL,
    usr_nombre            VARCHAR2(50)     NOT NULL,
    usr_apellido          VARCHAR2(50)     NOT NULL,
    usr_telefono          VARCHAR2(50)     NOT NULL,
    usr_direccion         VARCHAR2(50)     NOT NULL,
    usr_email             VARCHAR2(50)     NOT NULL,
    usr_login             VARCHAR2(30)     NOT NULL,
    usr_password          VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK13 PRIMARY KEY (usr_codigo)
)
;



-- 
-- TABLE: Vehicles 
--

CREATE TABLE Vehicles(
    vhc_codigo                   NUMBER(38, 0)       NOT NULL,
    vhc_ciu_aduan                VARCHAR2(30),
    vhc_unidad_taquim            VARCHAR2(10),
    vhc_valor_asignacion         NUMBER(38, 0),
    vhc_catalogado               VARCHAR2(30),
    vhc_placa_diplomatica        VARCHAR2(18),
    vhc_placa_activo_fijo        VARCHAR2(18),
    tvch_codigo                  NUMBER(38, 0),
    lns_id                       NUMBER(38, 0)       NOT NULL,
    est_codigo                   NUMBER(38, 0)       NOT NULL,
    tc_codigo                    NUMBER(38, 0)       NOT NULL,
    vhc_fecha_levante            DATE                NOT NULL,
    vhc_document_trans           VARCHAR2(20)        NOT NULL,
    vhc_numero_factura           VARCHAR2(20)        NOT NULL,
    vhc_order_compra             VARCHAR2(20),
    vhc_proveedor                VARCHAR2(50),
    vhc_capacidad                NUMBER(38, 0)       NOT NULL,
    vhc_valor_comercial          NUMBER(18, 0)       NOT NULL,
    vhc_ano_val_com              NUMBER(38, 0)       NOT NULL,
    vhc_fecha_protocolo          DATE                NOT NULL,
    vhc_numero_manifiesto        VARCHAR2(30)        NOT NULL,
    vhc_fecha_manifiesto         DATE                NOT NULL,
    vhc_num_decl_impor           VARCHAR2(50)        NOT NULL,
    vhc_numero_tl                VARCHAR2(20)        NOT NULL,
    vhc_remplaza_a               VARCHAR2(30)        NOT NULL,
    vhc_pl_rem_vehi              VARCHAR2(50),
    vhc_at_inicial               VARCHAR2(30),
    vhc_valor_cif                DOUBLE PRECISION,
    vhc_vida_util                NUMBER(38, 0),
    vhc_valor_fob                DOUBLE PRECISION,
    vhc_cargos_import            VARCHAR2(30),
    vhc_observaciones            VARCHAR2(200),
    vhc_ano                      DATE,
    vhc_numero_levante           VARCHAR2(20)        NOT NULL,
    vhc_odometro                 VARCHAR2(30)        NOT NULL,
    vhc_cilindraje               NUMBER(38, 0)       NOT NULL,
    vhc_numero_motor             VARCHAR2(20)        NOT NULL,
    vhc_numero_serie             VARCHAR2(20)        NOT NULL,
    vhc_modelo                   NUMBER(38, 0)       NOT NULL,
    vhc_color                    VARCHAR2(20)        NOT NULL,
    acc_codigo                   NUMBER(38, 0),
    soa_codigo                   NUMBER(38, 0),
    tptr_codigo                  NUMBER(38, 0)       NOT NULL,
    tptcr_codigo                 NUMBER(38, 0)       NOT NULL,
    tptpc_codigo                 NUMBER(38, 0)       NOT NULL,
    vhc_kilometraje_historico    NUMBER(38, 0),
    vhc_promedio_tanqueo         NUMBER(38, 0),
    vhc_kilometraje_actual       NUMBER(38, 0),
    ase_codigo                   NUMBER(38, 0),
    plc_codigo                   NUMBER(38, 0),
    cdd_id                       NUMBER(38, 0),
    CONSTRAINT PK12 PRIMARY KEY (vhc_codigo)
)
;



-- 
-- TABLE: Vehicles_States 
--

CREATE TABLE Vehicles_States(
    est_codigo    NUMBER(38, 0)    NOT NULL,
    est_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK33 PRIMARY KEY (est_codigo)
)
;



-- 
-- TABLE: Vehicles_Types 
--

CREATE TABLE Vehicles_Types(
    tvch_codigo    NUMBER(38, 0)    NOT NULL,
    tvch_nombre    VARCHAR2(50)     NOT NULL,
    CONSTRAINT PK16 PRIMARY KEY (tvch_codigo)
)
;



-- 
-- TABLE: Witnesses 
--

CREATE TABLE Witnesses(
    ttg_identificacion    VARCHAR2(20)     NOT NULL,
    ttg_nombre            VARCHAR2(30)     NOT NULL,
    ttg_direccion         VARCHAR2(50)     NOT NULL,
    ttg_telefono          VARCHAR2(20)     NOT NULL,
    cdd_id                NUMBER(38, 0)    NOT NULL,
    acc_codigo            NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK60 PRIMARY KEY (ttg_identificacion)
)
;



-- 
-- TABLE: Zones 
--

CREATE TABLE Zones(
    zn_id             NUMBER(38, 0)    NOT NULL,
    zn_nombre         VARCHAR2(50),
    zn_descripcion    VARCHAR2(100)    NOT NULL,
    zn_kilometraje    NUMBER(38, 0)    NOT NULL,
    CONSTRAINT PK7 PRIMARY KEY (zn_id)
)
;



-- 
-- INDEX: AK_activo_fijo 
--

CREATE UNIQUE INDEX AK_activo_fijo ON Vehicles(vhc_placa_activo_fijo)
;
-- 
-- TABLE: Accidents 
--

ALTER TABLE Accidents ADD CONSTRAINT RefAccidents_Types341 
    FOREIGN KEY (tpac_codigo)
    REFERENCES Accidents_Types(tpac_codigo)
;

ALTER TABLE Accidents ADD CONSTRAINT RefAccidents_States342 
    FOREIGN KEY (accst_code)
    REFERENCES Accidents_States(accst_code)
;

ALTER TABLE Accidents ADD CONSTRAINT RefCities218 
    FOREIGN KEY (cdd_id)
    REFERENCES Cities(cdd_id)
;


-- 
-- TABLE: Acts 
--

ALTER TABLE Acts ADD CONSTRAINT RefActs_Types343 
    FOREIGN KEY (tpscts_codigo)
    REFERENCES Acts_Types(tpscts_codigo)
;

ALTER TABLE Acts ADD CONSTRAINT RefAccidents_Classes344 
    FOREIGN KEY (clsacd_codigo)
    REFERENCES Accidents_Classes(clsacd_codigo)
;

ALTER TABLE Acts ADD CONSTRAINT RefAccidents222 
    FOREIGN KEY (acc_codigo)
    REFERENCES Accidents(acc_codigo)
;


-- 
-- TABLE: Assign_Vehicles 
--

ALTER TABLE Assign_Vehicles ADD CONSTRAINT RefRequests223 
    FOREIGN KEY (slc_codigo)
    REFERENCES Requests(slc_codigo)
;


-- 
-- TABLE: Cars_Rents 
--

ALTER TABLE Cars_Rents ADD CONSTRAINT RefRequests345 
    FOREIGN KEY (slc_codigo)
    REFERENCES Requests(slc_codigo)
;


-- 
-- TABLE: Cities 
--

ALTER TABLE Cities ADD CONSTRAINT RefCountries224 
    FOREIGN KEY (pss_id)
    REFERENCES Countries(pss_id)
;


-- 
-- TABLE: Clients_Sales_Vehicles 
--

ALTER TABLE Clients_Sales_Vehicles ADD CONSTRAINT RefVehicles225 
    FOREIGN KEY (vhc_codigo)
    REFERENCES Vehicles(vhc_codigo)
;


-- 
-- TABLE: Cobros 
--

ALTER TABLE Cobros ADD CONSTRAINT RefVehicles315 
    FOREIGN KEY (vhc_codigo)
    REFERENCES Vehicles(vhc_codigo)
;

ALTER TABLE Cobros ADD CONSTRAINT RefTariff_Types347 
    FOREIGN KEY (tql_id)
    REFERENCES Tariff_Types(tql_id)
;

ALTER TABLE Cobros ADD CONSTRAINT RefLocations_Types386 
    FOREIGN KEY (lct_codigo)
    REFERENCES Locations_Types(lct_codigo)
;


-- 
-- TABLE: Costs_Centers_Vehicles 
--

ALTER TABLE Costs_Centers_Vehicles ADD CONSTRAINT RefAllocations_Costs_Center348 
    FOREIGN KEY (cca_codigo)
    REFERENCES Allocations_Costs_Centers(cca_codigo)
;


-- 
-- TABLE: Delivery_Vehicles 
--

ALTER TABLE Delivery_Vehicles ADD CONSTRAINT RefCars_Rents349 
    FOREIGN KEY (alv_codigo)
    REFERENCES Cars_Rents(alv_codigo)
;

ALTER TABLE Delivery_Vehicles ADD CONSTRAINT RefAssign_Vehicles227 
    FOREIGN KEY (asn_codigo)
    REFERENCES Assign_Vehicles(asn_codigo)
;

ALTER TABLE Delivery_Vehicles ADD CONSTRAINT RefVehicles229 
    FOREIGN KEY (vhc_codigo)
    REFERENCES Vehicles(vhc_codigo)
;


-- 
-- TABLE: Devolutions 
--

ALTER TABLE Devolutions ADD CONSTRAINT RefAssign_Vehicles337 
    FOREIGN KEY (asn_codigo)
    REFERENCES Assign_Vehicles(asn_codigo)
;

ALTER TABLE Devolutions ADD CONSTRAINT RefVehicles339 
    FOREIGN KEY (vhc_codigo)
    REFERENCES Vehicles(vhc_codigo)
;

ALTER TABLE Devolutions ADD CONSTRAINT RefCars_Rents350 
    FOREIGN KEY (alv_codigo)
    REFERENCES Cars_Rents(alv_codigo)
;

ALTER TABLE Devolutions ADD CONSTRAINT RefAllocation_Types351 
    FOREIGN KEY (tpsi_codigo)
    REFERENCES Allocation_Types(tpsi_codigo)
;


-- 
-- TABLE: Fuels_Controls 
--

ALTER TABLE Fuels_Controls ADD CONSTRAINT RefPumps352 
    FOREIGN KEY (srt_codigo)
    REFERENCES Pumps(srt_codigo)
;


-- 
-- TABLE: Involved_Vehicles 
--

ALTER TABLE Involved_Vehicles ADD CONSTRAINT RefCities354 
    FOREIGN KEY (cdd_id)
    REFERENCES Cities(cdd_id)
;

ALTER TABLE Involved_Vehicles ADD CONSTRAINT RefAccidents355 
    FOREIGN KEY (acc_codigo)
    REFERENCES Accidents(acc_codigo)
;


-- 
-- TABLE: Legatees_Types_Tariffs_Tipes 
--

ALTER TABLE Legatees_Types_Tariffs_Tipes ADD CONSTRAINT RefTariffs356 
    FOREIGN KEY (trf_id)
    REFERENCES Tariffs(trf_id)
;

ALTER TABLE Legatees_Types_Tariffs_Tipes ADD CONSTRAINT RefAllocation_Types357 
    FOREIGN KEY (tpsi_codigo)
    REFERENCES Allocation_Types(tpsi_codigo)
;


-- 
-- TABLE: Lines 
--

ALTER TABLE Lines ADD CONSTRAINT RefBrands358 
    FOREIGN KEY (mrc_id)
    REFERENCES Brands(mrc_id)
;


-- 
-- TABLE: Locations 
--

ALTER TABLE Locations ADD CONSTRAINT RefLocations_Types387 
    FOREIGN KEY (lct_codigo)
    REFERENCES Locations_Types(lct_codigo)
;


-- 
-- TABLE: Options 
--

ALTER TABLE Options ADD CONSTRAINT RefModules232 
    FOREIGN KEY (mns_codigo)
    REFERENCES Modules(mns_codigo)
;


-- 
-- TABLE: Options_Rolls 
--

ALTER TABLE Options_Rolls ADD CONSTRAINT RefOptions359 
    FOREIGN KEY (pcn_codigo)
    REFERENCES Options(pcn_codigo)
;

ALTER TABLE Options_Rolls ADD CONSTRAINT RefRolls360 
    FOREIGN KEY (rls_codigo)
    REFERENCES Rolls(rls_codigo)
;


-- 
-- TABLE: Person_Involved_Ciat 
--

ALTER TABLE Person_Involved_Ciat ADD CONSTRAINT RefCities233 
    FOREIGN KEY (cdd_id)
    REFERENCES Cities(cdd_id)
;

ALTER TABLE Person_Involved_Ciat ADD CONSTRAINT RefVehicles234 
    FOREIGN KEY (vhc_codigo)
    REFERENCES Vehicles(vhc_codigo)
;


-- 
-- TABLE: Person_Involved_Vehicles 
--

ALTER TABLE Person_Involved_Vehicles ADD CONSTRAINT RefCities236 
    FOREIGN KEY (cdd_id)
    REFERENCES Cities(cdd_id)
;

ALTER TABLE Person_Involved_Vehicles ADD CONSTRAINT RefInvolved_Vehicles361 
    FOREIGN KEY (vhinv_placa)
    REFERENCES Involved_Vehicles(vhinv_placa)
;


-- 
-- TABLE: Policies 
--

ALTER TABLE Policies ADD CONSTRAINT RefPolices_Types362 
    FOREIGN KEY (tpplz_codigo)
    REFERENCES Polices_Types(tpplz_codigo)
;


-- 
-- TABLE: Policies_Vehicles 
--

ALTER TABLE Policies_Vehicles ADD CONSTRAINT RefPolicies238 
    FOREIGN KEY (plz_codigo)
    REFERENCES Policies(plz_codigo)
;

ALTER TABLE Policies_Vehicles ADD CONSTRAINT RefVehicles239 
    FOREIGN KEY (vhc_codigo)
    REFERENCES Vehicles(vhc_codigo)
;


-- 
-- TABLE: Pumps 
--

ALTER TABLE Pumps ADD CONSTRAINT RefTanks363 
    FOREIGN KEY (tnq_codigo)
    REFERENCES Tanks(tnq_codigo)
;


-- 
-- TABLE: Requests 
--

ALTER TABLE Requests ADD CONSTRAINT RefUsers318 
    FOREIGN KEY (usr_codigo)
    REFERENCES Users(usr_codigo)
;

ALTER TABLE Requests ADD CONSTRAINT RefAllocation_Types364 
    FOREIGN KEY (tpsi_codigo)
    REFERENCES Allocation_Types(tpsi_codigo)
;

ALTER TABLE Requests ADD CONSTRAINT RefRequests_States365 
    FOREIGN KEY (ests_codigo)
    REFERENCES Requests_States(ests_codigo)
;

ALTER TABLE Requests ADD CONSTRAINT RefCosts_Centers_Vehicles366 
    FOREIGN KEY (vcc_codigo)
    REFERENCES Costs_Centers_Vehicles(vcc_codigo)
;

ALTER TABLE Requests ADD CONSTRAINT RefRequests_Types367 
    FOREIGN KEY (tpslt_codigo)
    REFERENCES Requests_Types(tpslt_codigo)
;

ALTER TABLE Requests ADD CONSTRAINT RefRequests_Classes383 
    FOREIGN KEY (cls_codigo)
    REFERENCES Requests_Classes(cls_codigo)
;

ALTER TABLE Requests ADD CONSTRAINT RefVehicles_Types388 
    FOREIGN KEY (tvch_codigo)
    REFERENCES Vehicles_Types(tvch_codigo)
;


-- 
-- TABLE: Retirements_Reasons 
--

ALTER TABLE Retirements_Reasons ADD CONSTRAINT RefVehicles368 
    FOREIGN KEY (vhc_codigo)
    REFERENCES Vehicles(vhc_codigo)
;


-- 
-- TABLE: Tanks 
--

ALTER TABLE Tanks ADD CONSTRAINT RefFuels_Types369 
    FOREIGN KEY (tc_codigo)
    REFERENCES Fuels_Types(tc_codigo)
;


-- 
-- TABLE: Tariffs 
--

ALTER TABLE Tariffs ADD CONSTRAINT RefZones322 
    FOREIGN KEY (zn_id)
    REFERENCES Zones(zn_id)
;

ALTER TABLE Tariffs ADD CONSTRAINT RefLines324 
    FOREIGN KEY (lns_id)
    REFERENCES Lines(lns_id)
;

ALTER TABLE Tariffs ADD CONSTRAINT RefVehicles_Types370 
    FOREIGN KEY (tvch_codigo)
    REFERENCES Vehicles_Types(tvch_codigo)
;

ALTER TABLE Tariffs ADD CONSTRAINT RefTariff_Types371 
    FOREIGN KEY (tql_id)
    REFERENCES Tariff_Types(tql_id)
;

ALTER TABLE Tariffs ADD CONSTRAINT RefTractions_Types372 
    FOREIGN KEY (tptcr_codigo)
    REFERENCES Tractions_Types(tptcr_codigo)
;

ALTER TABLE Tariffs ADD CONSTRAINT RefTransmissions_Types373 
    FOREIGN KEY (tptr_codigo)
    REFERENCES Transmissions_Types(tptr_codigo)
;

ALTER TABLE Tariffs ADD CONSTRAINT RefFuels_Types384 
    FOREIGN KEY (tc_codigo)
    REFERENCES Fuels_Types(tc_codigo)
;

ALTER TABLE Tariffs ADD CONSTRAINT RefLocations389 
    FOREIGN KEY (plc_codigo)
    REFERENCES Locations(plc_codigo)
;


-- 
-- TABLE: Users 
--

ALTER TABLE Users ADD CONSTRAINT RefRolls255 
    FOREIGN KEY (rls_codigo)
    REFERENCES Rolls(rls_codigo)
;


-- 
-- TABLE: Vehicles 
--

ALTER TABLE Vehicles ADD CONSTRAINT RefAccidents257 
    FOREIGN KEY (acc_codigo)
    REFERENCES Accidents(acc_codigo)
;

ALTER TABLE Vehicles ADD CONSTRAINT RefSoat258 
    FOREIGN KEY (soa_codigo)
    REFERENCES Soat(soa_codigo)
;

ALTER TABLE Vehicles ADD CONSTRAINT RefLines263 
    FOREIGN KEY (lns_id)
    REFERENCES Lines(lns_id)
;

ALTER TABLE Vehicles ADD CONSTRAINT RefCities340 
    FOREIGN KEY (cdd_id)
    REFERENCES Cities(cdd_id)
;

ALTER TABLE Vehicles ADD CONSTRAINT RefInsurance_Car375 
    FOREIGN KEY (ase_codigo)
    REFERENCES Insurance_Car(ase_codigo)
;

ALTER TABLE Vehicles ADD CONSTRAINT RefVehicles_States376 
    FOREIGN KEY (est_codigo)
    REFERENCES Vehicles_States(est_codigo)
;

ALTER TABLE Vehicles ADD CONSTRAINT RefTransmissions_Types377 
    FOREIGN KEY (tptr_codigo)
    REFERENCES Transmissions_Types(tptr_codigo)
;

ALTER TABLE Vehicles ADD CONSTRAINT RefTractions_Types378 
    FOREIGN KEY (tptcr_codigo)
    REFERENCES Tractions_Types(tptcr_codigo)
;

ALTER TABLE Vehicles ADD CONSTRAINT RefTapestries_Types379 
    FOREIGN KEY (tptpc_codigo)
    REFERENCES Tapestries_Types(tptpc_codigo)
;

ALTER TABLE Vehicles ADD CONSTRAINT RefVehicles_Types380 
    FOREIGN KEY (tvch_codigo)
    REFERENCES Vehicles_Types(tvch_codigo)
;

ALTER TABLE Vehicles ADD CONSTRAINT RefFuels_Types381 
    FOREIGN KEY (tc_codigo)
    REFERENCES Fuels_Types(tc_codigo)
;

ALTER TABLE Vehicles ADD CONSTRAINT RefLocations390 
    FOREIGN KEY (plc_codigo)
    REFERENCES Locations(plc_codigo)
;


-- 
-- TABLE: Witnesses 
--

ALTER TABLE Witnesses ADD CONSTRAINT RefAccidents273 
    FOREIGN KEY (acc_codigo)
    REFERENCES Accidents(acc_codigo)
;

ALTER TABLE Witnesses ADD CONSTRAINT RefCities274 
    FOREIGN KEY (cdd_id)
    REFERENCES Cities(cdd_id)
;


