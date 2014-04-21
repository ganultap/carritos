package geniar.siscar.util;

/**
 * The Class ParametersUtil.
 */
public class ParametersUtil {

	/* Comprobantes */
	/** The Constant PROOF_TYPE_ASIGNACION. */
	public static final Long PROOF_TYPE_ASIGNACION = 1L;
	
	/** The Constant PROOF_TYPE_COMBUSTIBLE. */
	public static final Long PROOF_TYPE_COMBUSTIBLE = 3L;
	
	/** The Constant ACTIVO. */
	public static final Long ACTIVO = 1L;
	
	/** The fechas constantes. */
	public UtilidadesFechasConstantes fechasConstantes;

	/** The Constant DOLAR. */
	public static final Long DOLAR = 2L;
	
	/** The Constant PESOS. */
	public static final Long PESOS = 1L;
	
	/** The Constant NOVEDAD_COMB. */
	public static final Long NOVEDAD_COMB = 2L;
	
	/** The Constant NOVEDAD_KM_PERSONAL. */
	public static final Long NOVEDAD_KM_PERSONAL = 5L;
	
	/** The Constant NOVEDAD_ALQU. */
	public static final Long NOVEDAD_ALQU = 6L;
	
	/** The Constant NOVEDAD_AUTOSEGURO. */
	public static final Long NOVEDAD_AUTOSEGURO = 1L;
	
	/** The Constant NOVEDAD_ASIG. */
	public static final Long NOVEDAD_ASIG = 3L;
	
	/** The Constant CARGO_CARNE. */
	public static final Long CARGO_CARNE = 2L;
	/*
	 * Tipo de solicitud PDA
	 */
	/** The Constant SOLICITUD_COMB_MOTO_CIAT. */
	public static final Long SOLICITUD_COMB_MOTO_CIAT = 3L;// Tipo solicitante
	// MOTO_CIAT
	/** The Constant SOLICITUD_COMB_OTROS. */
	public static final Long SOLICITUD_COMB_OTROS = 5L;// Tipo solicitante
	// "OTROS" PDA
	/** The Constant SOLICITUD_COMB_PARQUE. */
	public static final Long SOLICITUD_COMB_PARQUE = 6L;// Tipo solicitante
	// "PARQUE CIENTIFICO"
	// PDA

	/** The Constant DEBITO. */
	public static final Long DEBITO = 1L;
	
	/** The Constant CREDITO. */
	public static final Long CREDITO = 2L;
	
	/** The Constant TRASACCTION_TYPE_RENT. */
	public static final Long TRASACCTION_TYPE_RENT = 2L;
	
	/** The Constant TRASACCTION_TYPE_ASSIGNATION. */
	public static final Long TRASACCTION_TYPE_ASSIGNATION = 1L;
	
	/** The Constant TRASACCTION_TYPE. */
	public static final Long TRASACCTION_TYPE = 3L;
	
	/** The Constant TRASACCTION_TYPE_CIAT_CASA_CIAT. */
	public static final Long TRASACCTION_TYPE_CIAT_CASA_CIAT = 4L;
	
	/** The Constant TRASACCTION_TYPE_AUTOSEGURO. */
	public static final Long TRASACCTION_TYPE_AUTOSEGURO = 1L;
	
	/** The Constant COBRO_AUTOSEGURO. */
	public static final Long COBRO_AUTOSEGURO = -2L;
	
	/** The Constant USO_PREPAGO. */
	public static final Long USO_PREPAGO = 1L;
	
	/** The Constant USO_PRESUPUESTO. */
	public static final Long USO_PRESUPUESTO = 2L;
	
	/** The Constant COMPRANTE_COMBUSTIBLE. */
	public static final Long COMPRANTE_COMBUSTIBLE = 3L;
	
	/** The Constant COMPRANTE_ALQUILER. */
	public static final Long COMPRANTE_ALQUILER = 2L;
	
	/** The Constant COMPRANTE_ASIGNACION. */
	public static final Long COMPRANTE_ASIGNACION = 1L;
	
	/** The Constant ESTADO_ACTIVO. */
	public static final String ESTADO_ACTIVO = "ACTIVO";
	
	/** The Constant ASIG_ALQUILER. */
	public static final Long ASIG_ALQUILER = 3L;
	
	/** The Constant CLASE_ALQUILER. */
	public static final Long CLASE_ALQUILER = 2L;
	
	/** The Constant CLASE_ASIGNACION. */
	public static final Long CLASE_ASIGNACION = 1L;
	
	/** The Constant CLASE_ALQUILER_TERCEROS. */
	public static final Long CLASE_ALQUILER_TERCEROS = 3L;
	
	/** The Constant CARGO_CENTRO_COSTO. */
	public static final Long CARGO_CENTRO_COSTO = 2L;
	
	/** The Constant CARGO_TERCEROS. */
	public static final Long CARGO_TERCEROS = 1L;
	
	/** The Constant CARGO_NO_APLICA. */
	public static final Long CARGO_NO_APLICA = 6L;
	
	/** The Constant CARGO_AUTOSEGURO. */
	public static final Long CARGO_AUTOSEGURO = 7L;
	
	/** The Constant CARGO_DEPRECIACION. */
	public static final Long CARGO_DEPRECIACION = 8L;
	
	/** The Constant CARGO_MANTENIMIENTO. */
	public static final Long CARGO_MANTENIMIENTO = 9L;
	
	public static final Long CARGO_ESPACIO_FISICO = 15L;
	
	/** The Constant CARGO_CC. */
	public static final Long CARGO_CC = 4L;
	
	/** The Constant TRASACCTION_TYPE_SEGUROS. */
	public static final Long TRASACCTION_TYPE_SEGUROS = 4L;
	
	/** The Constant CARGO_RECUPERACION_IVA. */
	public static final Long CARGO_RECUPERACION_IVA = 13L;
	
	/** The Constant CARGO_IVA. */
	public static final Long CARGO_IVA = 14L;
	
	/** The Constant CARGO_PROYECTOS. */
	public static final Long CARGO_PROYECTOS = 11L;
	
	/** The Constant CARGO_FULL_AB_SOAT. */
	public static final Long CARGO_FULL_AB_SOAT = 12L;
	
	/** The Constant CARGO_PERSONAL. */
	public static final Long CARGO_PERSONAL = 6L;
	
	/** The Constant TRASACCTION_CIAT_KMPERSONAL. */
	public static final Long TRASACCTION_CIAT_KMPERSONAL = 5L;

	/* Tipos de asignatario */
	/** The Constant LGT_OF. */
	public static final Long LGT_OF = 1L;
	
	/** The Constant LGT_OFS. */
	public static final Long LGT_OFS = 2L;
	
	/** The Constant LGT_PROGRAMAS. */
	public static final Long LGT_PROGRAMAS = 3L;
	
	/** The Constant LGT_PROYECTO. */
	public static final Long LGT_PROYECTO = 4L;
	
	/** The Constant LGT_CONVENIO. */
	public static final Long LGT_CONVENIO = 5L;//PE SIN NOMINA
	
	/** The Constant LGT_NO_APLICA. */
	public static final Long LGT_NO_APLICA = 8L;
	
	/** The Constant LGT_TERCEROS. */
	public static final Long LGT_TERCEROS = 9L;
	
	/** The Constant TIPO_ASIG_PERSONAL. */
	public static final Long LGT_PERSONAL = 6L;

	/* Tipo de ubicación */

	/** The Constant EXTERIORES. */
	public static final Long EXTERIORES = 3L;
	
	/** The Constant ESTACIONES. */
	public static final Long ESTACIONES = 2L;
	
	/** The Constant SEDE. */
	public static final Long SEDE = 1L;

	/* Tarifas */

	/** The Constant TARIFA_DEPRECIACION. */
	public static final Long TARIFA_DEPRECIACION = 1L;
	
	/** The Constant TARIFA_MANTENIMIENTO. */
	public static final Long TARIFA_MANTENIMIENTO = 2L;
	
	/** The Constant TARIFA_AUTOSEGURO. */
	public static final Long TARIFA_AUTOSEGURO = 3L;
	
	/** The Constant TARIFA_ALQUILER. */
	public static final Long TARIFA_ALQUILER = 4L;
	
	/** The Constant TARIFA_ASIGNACION_ESPACIO_FISICO. */
	public static final Long TARIFA_ASIGNACION_ESPACIO_FISICO = 8L;
	
	/** The Constant TARIFA_ALQUILER_DEPRECIACION. */
	public static final Long TARIFA_ALQUILER_DEPRECIACION = 9L;
	
	/** The Constant TARIFA_ALQUILER_MANTENIMIENTO. */
	public static final Long TARIFA_ALQUILER_MANTENIMIENTO = 10L;
	
	/** The Constant TARIFA_ALQUILER_AUTOSEGURO. */
	public static final Long TARIFA_ALQUILER_AUTOSEGURO = 11L;
	
	/** The Constant TARIFA_ALQUILER_ESPACIO_FISICO. */
	public static final Long TARIFA_ALQUILER_ESPACIO_FISICO = 12L;
	
	/* Solicitud */
	/** The Constant RQS_CREADA. */
	public static final Long RQS_CREADA = 1L;
	
	/** The Constant RQS_ENVIADA. */
	public static final Long RQS_ENVIADA = 2L;
	
	/** The Constant RQS_RESERVADO. */
	public static final Long RQS_RESERVADO = 3L;
	
	/** The Constant RQS_ALQUILADO. */
	public static final Long RQS_ALQUILADO = 4L;
	
	/** The Constant RQS_CUMPLIDA. */
	public static final Long RQS_CUMPLIDA = 5L;
	
	/** The Constant RQS_DEVUELTA. */
	public static final Long RQS_DEVUELTA = 6L;
	
	/** The Constant RQS_CANCELADA. */
	public static final Long RQS_CANCELADA = 7L;
	
	/** The Constant RQS_ASIGNADO. */
	public static final Long RQS_ASIGNADO = 8L;

	/* Solicitud consulta */
	/** The Constant RQS_PENDIENTES. */
	public static final Long RQS_PENDIENTES = 1L;
	
	/** The Constant RQS_CUMPLIDAS. */
	public static final Long RQS_CUMPLIDAS = 2L;

	/* Cargo A */
	/** The Constant CARGO_A_CC. */
	public static final Long CARGO_A_CC = 1L;
	
	/** The Constant CARGO_A_CARNE. */
	public static final Long CARGO_A_CARNE = 2L;
	
	/** The Constant CARGO_A_TERCEROS. */
	public static final Long CARGO_A_TERCEROS = 3L;

	/* Tipo vehiculo */
	/** The Constant TIPO_VHC_PICK_UP. */
	public static final Long TIPO_VHC_PICK_UP = 1L;
	
	/** The Constant TIPO_VHC_SEDAN. */
	public static final Long TIPO_VHC_SEDAN = 2L;
	
	/** The Constant TIPO_VHC_WAGON. */
	public static final Long TIPO_VHC_WAGON = 3L;
	
	/** The Constant TIPO_VHC_CAMION. */
	public static final Long TIPO_VHC_CAMION = 4L;
	
	/** The Constant TIPO_VHC_BUS. */
	public static final Long TIPO_VHC_BUS = 5L;
	
	/** The Constant TIPO_VHC_VAN. */
	public static final Long TIPO_VHC_VAN = 161L;

	/* Estados del vehículo */
	/** The Constant VHS_POOL. */
	public static final Long VHS_POOL = 6L;

	/* Tipo de combustible */
	/** The Constant GASOLINA. */
	public static final Long GASOLINA = 1L;
	
	/** The Constant DIESEL. */
	public static final Long DIESEL = 2L;

	/* seguros */
	/** The Constant TipoAuxDelima. */
	public static final Long TipoAuxDelima = 6L;
	
	/** The Constant TipoAuxAseguradora. */
	public static final Long TipoAuxAseguradora = 7L;
	
	/** The Constant TipoAuxAsignatario. */
	public static final Long TipoAuxAsignatario = 9L;
	
	/** The Constant FacturaEnviadaAP. */
	public static final Long FacturaEnviadaAP = 2L;

	/* Accidentes */
	/** The Constant ESTADO_ACIDENTE_EN_PROCESO. */
	public static final Long ESTADO_ACIDENTE_EN_PROCESO = 1L;

	/* Roles */
	/** The Constant ROL_RECURSOS_HUMANOS. */
	public static final Long ROL_RECURSOS_HUMANOS = 5L;

	/* Tipo de Notificaciones */
	/** The Constant NOTIFICACIONRESERVPROXIMASEFECTIVAS. */
	public static final Long NOTIFICACIONRESERVPROXIMASEFECTIVAS = 1L;
	
	/** The Constant NOTIFICACIONFECHADEVOLUCIONASIGNACION. */
	public static final Long NOTIFICACIONFECHADEVOLUCIONASIGNACION = 2L;
	
	/** The Constant NOTIFICACIONFECHADEVOLUCIONALQUILER. */
	public static final Long NOTIFICACIONFECHADEVOLUCIONALQUILER = 3L;
	
	/** The Constant NOTIFICACIONVEHICULORESERVADONORECOGIDO. */
	public static final Long NOTIFICACIONVEHICULORESERVADONORECOGIDO = 4L;

	/**
	 * The Enum UtilidadesFechasConstantes.
	 */
	enum UtilidadesFechasConstantes {
		
		/** The PRIME r_ di a_ mes. */
		PRIMER_DIA_MES("01", "01");

		/** The mes. */
		private final String mes;
		
		/** The dia. */
		private final String dia;

		/**
		 * Instantiates a new utilidades fechas constantes.
		 *
		 * @param dia the dia
		 * @param mes the mes
		 */
		UtilidadesFechasConstantes(String dia, String mes) {
			this.dia = dia;
			this.mes = mes;
		}

		/**
		 * Gets the mes.
		 *
		 * @return the mes
		 */
		public String getMes() {
			return mes;
		}

		/**
		 * Gets the dia.
		 *
		 * @return the dia
		 */
		public String getDia() {
			return dia;
		}

	}

	// Tipo de polizas
	/** The Constant SOAT. */
	public static final Long SOAT = 10L;
	
	/** The Constant AMPAROS_BASICOS. */
	public static final Long AMPAROS_BASICOS = 12L;
	
	/** The Constant FULL_COBERTURA. */
	public static final Long FULL_COBERTURA = 11L;

	// Kilometraje Personal
	/** The Constant KmPersonalOF. */
	public static final Long KmPersonalOF = 7L;
	
	/** The Constant KmPersonalOFS. */
	public static final Long KmPersonalOFS = 8L;
	
	/** The Constant valorKmPersonalOF. */
	public static final Long valorKmPersonalOF = 9L;
	
	/** The Constant valorKmPersonalOFS. */
	public static final Long valorKmPersonalOFS = 10L;

	/** The Constant ADICION_POLIZA. */
	public static final Long ADICION_POLIZA = -1L;
	
	/** The Constant NOVEDAD_POLIZA. */
	public static final Long NOVEDAD_POLIZA = 1L;
	
	//PAR_PARAMETROS
	/** The Constant PARAM_GEN_NOTIFICACIONES_AUTOMATICAS. */
	public static final Long PARAM_GEN_NOTIFICACIONES_AUTOMATICAS = 11L;	
	
}
