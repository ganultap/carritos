package geniar.siscar.view.accidents;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.logic.accidents.services.AccidentActsService;
import geniar.siscar.logic.accidents.services.DataAccidentsService;
import geniar.siscar.logic.accidents.servicesImpl.AccidentActsServiceImpl;
import geniar.siscar.model.Accidents;
import geniar.siscar.model.Acts;
import geniar.siscar.model.ActsTypes;
import geniar.siscar.model.Assistants;
import geniar.siscar.model.Users;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.RowSelectorEvent;

public class AccidentActsPage extends AccidentGeneralsPage {

	private Long actCodigo;
	private ActsTypes actsTypes;
	private Date actFechaReunion;
	private String actDescripcion;
	private String actObservaciones;
	private String actSanciones;
	private String accRecomendaciones;
	private String actAprobacion;
	private Date actFechaAprobacion;
	private boolean aprobacionActa = true;
	private boolean fechaAprobacion = true;
	private boolean showActaConsulta = false;
	private HtmlCommandButton btnCrear;
	private HtmlCommandButton btnModificar;
	private HtmlCommandButton btnNotificacion;

	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private boolean showAsistentes;
	private boolean showAccidentsTable;
	private boolean showModificarAccidente;
	public boolean showOrdenes = false;

	private List<Assistants> listaAsistentes = new ArrayList<Assistants>();
	private List<Users> listaAsistentesConsulta;
	private List<Accidents> listaAccidentes = new ArrayList<Accidents>();
	private List<Accidents> listaAccidentesConsulta;
	private List<Object[]> listaOrdenes;

	private List<Acts> listaActas;
	private Long filtroActa;
	private Date filtroFechaIni;
	private Date filtroFechaFin;
	private String filtroAsistente;
	private HtmlOutputText txtIdAsistente;
	private HtmlOutputText txtIdAsistenteConsulta;
	private HtmlOutputText txtIdAccidenteConsulta;
	private HtmlOutputText txtNumOrden;

	private Integer opcModificarAcc;
	private static Integer MODIFICARACC = 1;

	private Long numAccidente;
	private Date fechaAccidente;
	private Long estadoAccidente;
	private String deduciblesPesos;
	private String deduciblesDolar;
	private Long responsibility;
	private Long severity;
	private String ordenTrabajo;
	private String sanciones;
	private String observaciones;
	private String descripcion;
	private static Integer EnviarNotificacion = 2;

	private HtmlInputTextarea txtOrdenTrabajo;

	private Long filtroEstadoAccidente;
	private Date fechaIniAccidente;
	private Date fechaFinAccidente;
	private String filtroPlaca;
	private String filtroNumSiniestro;
	private String ordenesParametro;

	private HtmlOutputText txtIdAccidente;

	private ConsultsService consultsService;
	private DataAccidentsService dataAccidentsService;

	private HtmlOutputText txtIdacta;
	private AccidentActsService accidentActsService;

	public AccidentActsService getAccidentActsService() {
		return accidentActsService;
	}

	public void setAccidentActsService(AccidentActsService accidentActsService) {
		this.accidentActsService = accidentActsService;
	}

	public Long getActCodigo() {
		return actCodigo;
	}

	public void setActCodigo(Long actCodigo) {
		this.actCodigo = actCodigo;
	}

	public ActsTypes getActsTypes() {
		return actsTypes;
	}

	public void setActsTypes(ActsTypes actsTypes) {
		this.actsTypes = actsTypes;
	}

	public Date getActFechaReunion() {
		return actFechaReunion;
	}

	public void setActFechaReunion(Date actFechaReunion) {
		this.actFechaReunion = actFechaReunion;
	}

	public String getActDescripcion() {
		return actDescripcion;
	}

	public void setActDescripcion(String actDescripcion) {
		this.actDescripcion = actDescripcion;
	}

	public String getActObservaciones() {
		return actObservaciones;
	}

	public void setActObservaciones(String actObservaciones) {
		this.actObservaciones = actObservaciones;
	}

	public String getActSanciones() {
		return actSanciones;
	}

	public void setActSanciones(String actSanciones) {
		this.actSanciones = actSanciones;
	}

	public String getActAprobacion() {
		return actAprobacion;
	}

	public void setActAprobacion(String actAprobacion) {
		this.actAprobacion = actAprobacion;
	}

	public Date getActFechaAprobacion() {
		return actFechaAprobacion;
	}

	public void setActFechaAprobacion(Date actFechaAprobacion) {
		this.actFechaAprobacion = actFechaAprobacion;
	}

	void validarDatosNulo(Date actFechaReunion, String actDescripcion,
			String actObservaciones) throws GWorkException {
		if (actFechaReunion == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHAREUNION.NULO"));
		if (actDescripcion == null || actDescripcion.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("DESCRIPCIONACTA.NULO"));

		if (actDescripcion.trim().length() < 2
				|| actDescripcion.trim().length() > 250)
			throw new GWorkException(Util
					.loadErrorMessageValue("TAMANHODESCRIPCIONACTA"));

		if (actObservaciones != null
				&& actObservaciones.trim().length() != 0
				&& (actObservaciones.trim().length() < 2 || actObservaciones
						.trim().length() > 250))
			throw new GWorkException(Util
					.loadErrorMessageValue("TAMANHOOBSERVACIONACTA"));
	}

	public boolean isAprobacionActa() {
		return aprobacionActa;
	}

	public void setAprobacionActa(boolean aprobacionActa) {
		this.aprobacionActa = aprobacionActa;
	}

	public boolean isFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(boolean fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public void action_crearActa(ActionEvent actionEvent) {

		try {
			validarDatosNulo(actFechaReunion, actDescripcion, actObservaciones);

			accidentActsService.generarActaSubcomite(actFechaReunion,
					actDescripcion, actObservaciones, listaAccidentes,
					listaAsistentes);
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_modificarActa(ActionEvent actionEvent) {
		try {
			if (actCodigo == null || actCodigo.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACTA.CONSULTA"));
			if (actAprobacion == null
					|| actAprobacion.equalsIgnoreCase(Util
							.loadMessageValue("SELECCIONAR")))
				throw new GWorkException(Util
						.loadErrorMessageValue("APROBACIONACT.SEL"));

			validarDatosNulo(actFechaReunion, actDescripcion, actObservaciones);

			if (actFechaAprobacion != null
					&& actFechaAprobacion.compareTo(actFechaReunion) < 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAACTAAPROBACION"));

			if (actFechaAprobacion == null) {
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAACTAAPROBACION2"));
			}

			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void modicarActa() {

		try {

			if (actCodigo != null && actCodigo.longValue() != 0L) {
				accidentActsService.modificarActaSubcomite(actCodigo,
						actFechaReunion, actDescripcion, actObservaciones,
						actAprobacion, actFechaAprobacion, listaAccidentes,
						listaAsistentes);
				mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
				limpiar();
			}
		} catch (GWorkException e) {
			e.printStackTrace();
		}
	}

	public void limpiar() {
		try {

			actCodigo = null;
			actsTypes = null;
			actFechaReunion = null;
			actDescripcion = null;
			actObservaciones = null;
			actSanciones = null;
			actAprobacion = Util.loadMessageValue("SELECCIONAR");
			actFechaAprobacion = null;
			aprobacionActa = true;
			fechaAprobacion = true;
			btnCrear.setDisabled(false);
			btnModificar.setDisabled(false);
			if (listaAccidentes != null)
				listaAccidentes.clear();
			if (listaAsistentes != null)
				listaAsistentes.clear();
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
	}

	public void action_limpiar(ActionEvent actionEvent) {
		limpiar();
	}

	public boolean isShowActaConsulta() {
		return showActaConsulta;
	}

	public void setShowActaConsulta(boolean showActaConsulta) {
		this.showActaConsulta = showActaConsulta;
	}

	public void action_closeActaConsulta(ActionEvent actionEvent) {

		showActaConsulta = false;
		limpiarConsulta();
	}

	public void action_showActaConsulta(ActionEvent actionEvent) {
		showActaConsulta = true;
	}

	public Long getFiltroActa() {
		return filtroActa;
	}

	public void setFiltroActa(Long filtroActa) {
		this.filtroActa = filtroActa;
	}

	public Date getFiltroFechaIni() {
		return filtroFechaIni;
	}

	public void setFiltroFechaIni(Date filtroFechaIni) {
		this.filtroFechaIni = filtroFechaIni;
	}

	public Date getFiltroFechaFin() {
		return filtroFechaFin;
	}

	public void setFiltroFechaFin(Date filtroFechaFin) {
		this.filtroFechaFin = filtroFechaFin;
	}

	public List<Acts> getListaActas() {
		return listaActas;
	}

	public void setListaActas(List<Acts> listaActas) {
		this.listaActas = listaActas;
	}

	public void listarActas() {

		try {
			setListaActas(null);
			setListaActas(accidentActsService.consultarActa(filtroActa,
					filtroFechaIni, filtroFechaFin));
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void action_consultarActa(ActionEvent actionEvent) {
		try {

			if ((filtroActa == null || filtroActa.longValue() == 0L)
					&& (filtroFechaFin == null && filtroFechaIni == null))
				throw new GWorkException(Util
						.loadErrorMessageValue("PARAMETROBUSQUEDAD"));
			else if ((filtroActa == null || filtroActa.longValue() == 0L)
					&& (filtroFechaFin == null || filtroFechaIni == null))
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAS.CONSULTA.NULO"));
			else if (filtroFechaFin != null && filtroFechaIni != null
					&& filtroFechaFin.compareTo(filtroFechaIni) < 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("FCH_INI_FCH_FIN"));

			listarActas();

		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}

	}

	public void limpiarConsulta() {
		filtroActa = null;
		filtroFechaFin = null;
		filtroFechaIni = null;
		setListaActas(null);
	}

	public void action_limpiarConsulta(ActionEvent actionEvent) {
		limpiarConsulta();
	}

	public void rowselectorActa(RowSelectorEvent rowSelectorEvent) {

		Long idActa = (Long) txtIdacta.getValue();

		for (Acts acts : listaActas) {

			if (acts.getActCodigo().longValue() == idActa.longValue()) {
				limpiar();
				actCodigo = acts.getActCodigo();
				actFechaReunion = acts.getActFechaReunion();
				actDescripcion = acts.getActDescripcion();
				actObservaciones = acts.getActObservaciones();
				actAprobacion = acts.getActAprobacion();
				actFechaAprobacion = acts.getActFechaAprobacion();
				try {
					setListaAccidentes(accidentActsService
							.accidentesByActa(acts.getActCodigo().longValue()));

				} catch (GWorkException e) {
					System.out.println(e.getMessage());
				}

				try {
					setListaAsistentes(accidentActsService
							.cosultarAssistenteActa(actCodigo.longValue()));

				} catch (GWorkException e) {
					System.out.println(e.getMessage());
				}
				fechaAprobacion = false;
				aprobacionActa = false;
				showActaConsulta = false;
				btnCrear.setDisabled(true);
				btnModificar.setDisabled(false);
				break;
			}
		}
		limpiarConsulta();

	}

	public HtmlOutputText getTxtIdacta() {
		return txtIdacta;
	}

	public void setTxtIdacta(HtmlOutputText txtIdacta) {
		this.txtIdacta = txtIdacta;
	}

	public HtmlCommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(HtmlCommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public HtmlCommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(HtmlCommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public boolean isShowAsistentes() {
		return showAsistentes;
	}

	public void setShowAsistentes(boolean showAsistentes) {
		this.showAsistentes = showAsistentes;
	}

	public void action_closeAsistentes(ActionEvent actionEvent) {

		showAsistentes = false;

	}

	public void action_showAsistentes(ActionEvent actionEvent) {

		showAsistentes = true;
	}

	public String getFiltroAsistente() {
		return filtroAsistente;
	}

	public void setFiltroAsistente(String filtroAsistente) {
		this.filtroAsistente = filtroAsistente;
	}

	public void action_filtrarAsistente(ActionEvent actionEvent) {

		try {
			setListaAsistentesConsulta(consultsService.employeesCIAT(
					filtroAsistente.toUpperCase(), filtroAsistente
							.toUpperCase(), filtroAsistente));
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void rowSelectionEmployee(RowSelectorEvent rowSelectorEvent) {

		String idAsistente = (String) txtIdAsistenteConsulta.getValue();
		try {
			for (Users users : listaAsistentesConsulta) {
				if (idAsistente.equalsIgnoreCase(users.getUsrIdentificacion())) {

					validarAsistente(idAsistente);
					Assistants assistants = new Assistants();
					assistants.setAsiCodigoCiat(users.getUsrIdentificacion());
					assistants.setAsiNombre(users.getUsrNombre());
					assistants.setAsiEmail(users.getUsrEmail());

					listaAsistentes.add(assistants);
					showAsistentes = false;
					setListaAsistentesConsulta(null);
					filtroAsistente = "";
					activarBtnNotificacion();
					break;
				}
			}

		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void action_eliminarAsistente(ActionEvent actionEvent) {

		txtIdAsistente.getValue();
		String idAsistente = (String) txtIdAsistente.getValue().toString();
		try {
			if (idAsistente != null) {
				for (Assistants assistants : listaAsistentes) {

					if (idAsistente.equals(assistants.getAsiCodigoCiat())) {
						listaAsistentes.remove(assistants);
						mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"),
								false);
						break;
					}
				}
			}
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public ConsultsService getConsultsService() {
		return consultsService;
	}

	public void setConsultsService(ConsultsService consultsService) {
		this.consultsService = consultsService;
	}

	public List<Accidents> getListaAccidentes() {
		return listaAccidentes;
	}

	public void setListaAccidentes(List<Accidents> listaAccidentes) {
		this.listaAccidentes = listaAccidentes;
	}

	public HtmlOutputText getTxtIdAccidente() {
		return txtIdAccidente;
	}

	public void setTxtIdAccidente(HtmlOutputText txtIdAccidente) {
		this.txtIdAccidente = txtIdAccidente;
	}

	public Long getFiltroEstadoAccidente() {
		return filtroEstadoAccidente;
	}

	public void setFiltroEstadoAccidente(Long filtroEstadoAccidente) {
		this.filtroEstadoAccidente = filtroEstadoAccidente;
	}

	public Date getFechaIniAccidente() {
		return fechaIniAccidente;
	}

	public void setFechaIniAccidente(Date fechaIniAccidente) {
		this.fechaIniAccidente = fechaIniAccidente;
	}

	public Date getFechaFinAccidente() {
		return fechaFinAccidente;
	}

	public void setFechaFinAccidente(Date fechaFinAccidente) {
		this.fechaFinAccidente = fechaFinAccidente;
	}

	public String getFiltroPlaca() {
		return filtroPlaca;
	}

	public void setFiltroPlaca(String filtroPlaca) {
		this.filtroPlaca = filtroPlaca;
	}

	public String getFiltroNumSiniestro() {
		return filtroNumSiniestro;
	}

	public void setFiltroNumSiniestro(String filtroNumSiniestro) {
		this.filtroNumSiniestro = filtroNumSiniestro;
	}

	public void action_consultarAccidente(ActionEvent actionEvent)
			throws GWorkException {
		try {
			if ((filtroEstadoAccidente == null
					|| filtroEstadoAccidente.longValue() == 0 || filtroEstadoAccidente
					.longValue() == -1L)
					&& fechaIniAccidente == null
					&& fechaFinAccidente == null
					&& (filtroNumSiniestro == null || filtroNumSiniestro.trim()
							.length() == 0)
					&& (filtroPlaca == null || filtroPlaca.trim().length() == 0))
				throw new GWorkException(Util
						.loadErrorMessageValue("CONSULTAR.ACCIDENTE.NULO"));

			if (!Util.validarNumerosParametros(filtroNumSiniestro))
				throw new GWorkException(
						Util
								.loadErrorMessageValue("CARACTERESPECIAL.FILTROSINIESTRO"));

			if (!Util
					.validarCadenaCaracteresEspecialesNumLetrasGuion(filtroPlaca))
				throw new GWorkException(Util
						.loadErrorMessageValue("PLACA.CARACTER"));

			if ((fechaFinAccidente != null && fechaIniAccidente == null)
					|| (fechaFinAccidente == null && fechaIniAccidente != null))
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAS.CONSULTA.NULO"));

			if (fechaFinAccidente != null && fechaIniAccidente != null
					&& fechaFinAccidente.compareTo(fechaIniAccidente) < 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("FCH_INI_FCH_FIN"));

			AccidentActsService accidentActsService = new AccidentActsServiceImpl();

			Long numeroSiniestro = (filtroNumSiniestro == null || filtroNumSiniestro
					.trim().length() == 0) ? null : Long
					.parseLong(filtroNumSiniestro);

			setListaAccidentesConsulta(accidentActsService.filtroActaAccidente(
					filtroPlaca, numeroSiniestro, filtroEstadoAccidente,
					fechaIniAccidente, fechaFinAccidente));

		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public DataAccidentsService getDataAccidentsService() {
		return dataAccidentsService;
	}

	public void setDataAccidentsService(
			DataAccidentsService dataAccidentsService) {
		this.dataAccidentsService = dataAccidentsService;
	}

	public void limpiarConsultaAccidente() {
		filtroPlaca = null;
		fechaFinAccidente = null;
		fechaIniAccidente = null;
		filtroNumSiniestro = null;
		filtroEstadoAccidente = new Long("-1");
		setListaAccidentesConsulta(null);
	}

	public void action_limpiarConsultaAccidente(ActionEvent actionEvent) {
		limpiarConsultaAccidente();
	}

	public List<Accidents> getListaAccidentesConsulta() {
		return listaAccidentesConsulta;
	}

	public void setListaAccidentesConsulta(
			List<Accidents> listaAccidentesConsulta) {
		this.listaAccidentesConsulta = listaAccidentesConsulta;
	}

	public void action_selectAccident(ActionEvent event) {

		Long idAccidente = (Long) txtIdAccidenteConsulta.getValue();
		try {
			for (Accidents accidents : listaAccidentesConsulta) {
				if (idAccidente.longValue() == accidents.getAccCodigo()
						.longValue()) {
					validarAccidente(accidents.getAccCodigo().longValue());
					Accidents accidentsObj = new Accidents();
					accidentsObj = accidents;

					if (listaAccidentes == null)
						listaAccidentes = new ArrayList<Accidents>();

					listaAccidentes.add(accidentsObj);
					break;
				}
			}

		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}

	}

	public void validarAccidente(Long idAccidente) throws GWorkException {

		if (listaAccidentes != null && listaAccidentes.size() > 0) {
			for (Accidents accidents : listaAccidentes) {
				if (idAccidente.longValue() == accidents.getAccCodigo()
						.longValue())
					throw new GWorkException(Util
							.loadErrorMessageValue("ACCIDENTE.REGISTRADO"));
			}
		}
	}

	public void validarAsistente(String idAsistente) throws GWorkException {

		if (listaAsistentes != null && listaAsistentes.size() > 0) {
			for (Assistants assistants : listaAsistentes) {
				if (idAsistente.equalsIgnoreCase(assistants.getAsiCodigoCiat()))
					throw new GWorkException(Util
							.loadErrorMessageValue("ASISTENTE.REGISTRADO"));
			}
		}
	}

	public HtmlOutputText getTxtIdAccidenteConsulta() {
		return txtIdAccidenteConsulta;
	}

	public void setTxtIdAccidenteConsulta(HtmlOutputText txtIdAccidenteConsulta) {
		this.txtIdAccidenteConsulta = txtIdAccidenteConsulta;
	}

	public boolean isShowAccidentsTable() {
		return showAccidentsTable;
	}

	public void setShowAccidentsTable(boolean showAccidentsTable) {
		this.showAccidentsTable = showAccidentsTable;
	}

	public void action_closeAccidentsTable(ActionEvent actionEvent) {
		showAccidentsTable = false;
		showAccidentsTable = false;
		limpiarConsultaAccidente();
	}

	public void action_showAccidentsTable(ActionEvent actionEvent) {

		showAccidentsTable = true;
	}

	public void action_eliminarAccidenteActa(ActionEvent actionEvent) {

		Long idAccidente = (Long) txtIdAccidente.getValue();

		try {

			if (idAccidente != null) {
				for (Accidents accidents : listaAccidentes) {
					if (idAccidente.longValue() == accidents.getAccCodigo()
							.longValue()) {
						listaAccidentes.remove(accidents);

						mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"),
								false);
						break;
					}
				}
			}
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Users> getListaAsistentesConsulta() {
		return listaAsistentesConsulta;
	}

	public void setListaAsistentesConsulta(List<Users> listaAsistentesConsulta) {
		this.listaAsistentesConsulta = listaAsistentesConsulta;
	}

	public HtmlOutputText getTxtIdAsistenteConsulta() {
		return txtIdAsistenteConsulta;
	}

	public void setTxtIdAsistenteConsulta(HtmlOutputText txtIdAsistenteConsulta) {
		this.txtIdAsistenteConsulta = txtIdAsistenteConsulta;
	}

	public HtmlOutputText getTxtIdAsistente() {
		return txtIdAsistente;
	}

	public void setTxtIdAsistente(HtmlOutputText txtIdAsistente) {
		this.txtIdAsistente = txtIdAsistente;
	}

	public void action_showModificarAccidente(ActionEvent actionEvent) {

		Long idAccidente = (Long) txtIdAccidente.getValue();
		Accidents accidentsObj = null;
		if (idAccidente != null) {

			for (Accidents accidents : listaAccidentes) {
				if (idAccidente.longValue() == accidents.getAccCodigo()
						.longValue()) {

					try {
						accidentsObj = dataAccidentsService
								.consultarAccidente(accidents.getAccCodigo()
										.longValue());
						numAccidente = accidentsObj.getAccCodigo();
						fechaAccidente = accidentsObj.getAccFechaAccidente();
						estadoAccidente = accidentsObj.getAccidentsResults()
								.getAclCodigo().longValue();
						
						if (accidentsObj.getAccDeduciblesPesos() != null)
							deduciblesPesos = accidentsObj.getAccDeduciblesPesos()
									.toString();
	
						if (accidentsObj.getAccDeduciblesDolar() != null)
							deduciblesDolar = accidentsObj.getAccDeduciblesDolar()
									.toString();
						if (accidentsObj.getResponsibility() != null)
							responsibility = accidentsObj.getResponsibility()
									.getResCodigo();
						if (accidentsObj.getSeverity() != null)
							severity = accidentsObj.getSeverity().getSevCodigo();
						sanciones = accidentsObj.getAccSancInterActa();
	
						if (accidentsObj.getAccOrdenTrabajoActa() != null)
							setOrdenTrabajo(accidentsObj.getAccOrdenTrabajoActa());
						txtOrdenTrabajo.setValue(accidentsObj
								.getAccOrdenTrabajoActa());
	
						observaciones = accidentsObj.getAccObservaciones();
						descripcion = accidentsObj.getAccDescripcion();
						accRecomendaciones = accidentsObj.getAccRecomendaciones();
						showModificarAccidente = true;
						
						break;
					} catch (GWorkException e) {
						mostrarMensaje(e.getMessage(), false);
					}
				}
			}
		}

	}

	public void limpiarFormaAccidente() {

		numAccidente = null;
		fechaAccidente = null;
		estadoAccidente = new Long("-1");
		deduciblesPesos = null;
		deduciblesDolar = null;
		responsibility = new Long("-1");
		severity = new Long("-1");
		sanciones = null;
		accRecomendaciones = null;
		observaciones = null;
		descripcion = null;
		ordenTrabajo = null;
		txtOrdenTrabajo.setValue("");
	}

	public void action_limpiarFormaAccidente(ActionEvent actionEvent) {

		limpiarFormaAccidente();
	}

	public boolean isShowModificarAccidente() {
		return showModificarAccidente;
	}

	public void setShowModificarAccidente(boolean showModificarAccidente) {
		this.showModificarAccidente = showModificarAccidente;
	}

	public void action_closeModificarAccidente(ActionEvent actionEvent) {

		showModificarAccidente = false;
		limpiarConsultaAccidente();
	}

	public Long getNumAccidente() {
		return numAccidente;
	}

	public void setNumAccidente(Long numAccidente) {
		this.numAccidente = numAccidente;
	}

	public Date getFechaAccidente() {
		return fechaAccidente;
	}

	public void setFechaAccidente(Date fechaAccidente) {
		this.fechaAccidente = fechaAccidente;
	}

	public Long getEstadoAccidente() {
		return estadoAccidente;
	}

	public void setEstadoAccidente(Long estadoAccidente) {
		this.estadoAccidente = estadoAccidente;
	}

	public Long getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(Long responsibility) {
		this.responsibility = responsibility;
	}

	public Long getSeverity() {
		return severity;
	}

	public void setSeverity(Long severity) {
		this.severity = severity;
	}

	public String getSanciones() {
		return sanciones;
	}

	public void setSanciones(String sanciones) {
		this.sanciones = sanciones;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void validarDatosAccidente(String deduciblesPesos,
			String deduciblesDolar, String observaciones, String descripcion)
			throws GWorkException {

		if (deduciblesPesos == null || deduciblesPesos.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("DEDUCIBLEPESOS.NULO"));

		if (deduciblesDolar == null || deduciblesDolar.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("DEDUCIBLEDOLAR.NULO"));

		if (observaciones == null || observaciones.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("OBSERVACIONES_CLIENTE"));

		if (descripcion == null || descripcion.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("DESCRIPCION.VACIA"));
	}

	public void action_modificarAccidente(ActionEvent actionEvent) {
		try {
			if (numAccidente == null || numAccidente.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			validarDatosAccidente(deduciblesPesos, deduciblesDolar,
					observaciones, descripcion);

			if (!Util.validarNumerosParametros(deduciblesPesos.toString()))// validarNumerosDecimales
				throw new GWorkException(Util
						.loadErrorMessageValue("DEDUCIBLEPESOS.CARACTERES"));

			if (!Util.validarNumerosParametros(deduciblesDolar.toString()))
				throw new GWorkException(Util
						.loadErrorMessageValue("DEDUCIBLEDOLAR.CARACTERES"));

			/*
			 * if (sanciones!=null && sanciones.trim().length()>250) throw new
			 * GWorkException(Util.loadErrorMessageValue("SANCIONES.MINMAX"));
			 */

			if (observaciones != null
					&& (observaciones.trim().length() < 2 || observaciones
							.trim().length() > 2000))
				throw new GWorkException(Util
						.loadErrorMessageValue("OBSERVACION.MINMAX"));

			if (descripcion != null
					&& (descripcion.trim().length() < 2 || descripcion.trim()
							.length() > 2000))
				throw new GWorkException(Util
						.loadErrorMessageValue("DESCRIPCION.MINMAX"));

			if (accRecomendaciones == null
					|| accRecomendaciones.trim().length() == 0)
				throw new GWorkException("Debe ingresar las recomendaciones");

			if (accRecomendaciones != null
					&& (accRecomendaciones.trim().length() < 2 || accRecomendaciones
							.trim().length() > 250))
				throw new GWorkException(
						"El tamaño del campo recomendaciones no es el adecuado");
			activarConfirmacion = true;
			setOpcModificarAcc(MODIFICARACC);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public String getDeduciblesPesos() {
		return deduciblesPesos;
	}

	public void setDeduciblesPesos(String deduciblesPesos) {
		this.deduciblesPesos = deduciblesPesos;
	}

	public String getDeduciblesDolar() {
		return deduciblesDolar;
	}

	public void setDeduciblesDolar(String deduciblesDolar) {
		this.deduciblesDolar = deduciblesDolar;
	}

	public Integer getOpcModificarAcc() {
		return opcModificarAcc;
	}

	public void setOpcModificarAcc(Integer opcModificarAcc) {
		this.opcModificarAcc = opcModificarAcc;
	}

	public void modificarAccidente() {
		try {
			if (numAccidente != null) {

				accidentActsService.modificarActaAccidente(numAccidente,
						deduciblesPesos, deduciblesDolar, responsibility,
						severity, ordenTrabajo, sanciones, observaciones,
						descripcion, accRecomendaciones);

				limpiarFormaAccidente();
				mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
				showModificarAccidente = false;
			}

		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public String getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(String ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public String getAccRecomendaciones() {
		return accRecomendaciones;
	}

	public void setAccRecomendaciones(String accRecomendaciones) {
		this.accRecomendaciones = accRecomendaciones;
	}

	public List<Assistants> getListaAsistentes() {
		return listaAsistentes;
	}

	public void setListaAsistentes(List<Assistants> listaAsistentes) {
		this.listaAsistentes = listaAsistentes;
	}

	public List<Object[]> getListaOrdenes() {
		return listaOrdenes;
	}

	public void setListaOrdenes(List<Object[]> listaOrdenes) {
		this.listaOrdenes = listaOrdenes;
	}

	public void setShowOrdenes(boolean showOrdenes) {
		this.showOrdenes = showOrdenes;
	}

	public void rowSelection_ordenTrabajo(RowSelectorEvent event) {

		BigDecimal numOrden = (BigDecimal) txtNumOrden.getValue();

		if (numOrden != null) {
			for (Object[] ordenes : listaOrdenes) {
				if (numOrden.toString().equalsIgnoreCase(ordenes[0].toString())) {

					ordenTrabajo = ordenes[1].toString();
					txtOrdenTrabajo.setValue(ordenes[1].toString());
					showOrdenes = false;
					break;
				}
			}
		}
	}

	public void action_ocultarOrdenes(ActionEvent actionEvent) {
		showOrdenes = false;
	}

	public void action_mostrarOrdenes(ActionEvent actionEvent) {

		showOrdenes = true;
	}

	public void action_filtrarOrdenes(ActionEvent actionEvent) {

		try {
			setListaOrdenes(consultsService.ordenesTrabajo(ordenesParametro
					.toUpperCase()));
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public boolean isShowOrdenes() {
		return showOrdenes;
	}

	public HtmlOutputText getTxtNumOrden() {
		return txtNumOrden;
	}

	public void setTxtNumOrden(HtmlOutputText txtNumOrden) {
		this.txtNumOrden = txtNumOrden;
	}

	public String getOrdenesParametro() {
		return ordenesParametro;
	}

	public void setOrdenesParametro(String ordenesParametro) {
		this.ordenesParametro = ordenesParametro;
	}

	public HtmlInputTextarea getTxtOrdenTrabajo() {
		return txtOrdenTrabajo;
	}

	public void setTxtOrdenTrabajo(HtmlInputTextarea txtOrdenTrabajo) {
		this.txtOrdenTrabajo = txtOrdenTrabajo;
	}

	/**
	 * Metodo encargado de mostrar el mensaje de confirmacion para la volver a
	 * renovar los datos de la tabla FlatFile
	 * 
	 * @param event
	 * @throws GWorkException
	 */
	public void mostrarEnviarNotificacion(ActionEvent event)
			throws GWorkException {
		try {
			if (listaAsistentes != null && listaAsistentes.size() > 0) {
				activarConfirmacion = true;
				setOpcModificarAcc(EnviarNotificacion);
				mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
						activarConfirmacion);
			} else
				throw new GWorkException(Util
						.loadErrorMessageValue("SIN.ASISTENTES"));
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void enviarNotificacion() throws GWorkException {
		String Email = null;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"dd - MMM - yyyy");
		String CuerpoMensaje = "Fecha de la reunión: "
				+ simpleDateFormat.format(actFechaReunion).toString()
				+ "<br /><br /><table border='1' ><tr><td>Codigo Accidente</td>"
				+ "<td>Placa Vehiculo</td><td>Fecha Accidente</td>"
				+ "<td>Ciudad Accidente</td><td>Numero Siniestro</td>"
				+ "<td>Nombre Conductor</td>" + "<td>Estado del Accidente</td>"
				+ "<td>Descripci&oacute;n</td></tr>";
		for (Accidents accidents : listaAccidentes) {
			CuerpoMensaje += "<tr><td>" + accidents.getAccCodigo()
					+ "</td><td>"
					+ accidents.getVehicles().getVhcPlacaDiplomatica()
					+ "</td><td>" + accidents.getAccFechaAccidente()
					+ "</td><td>" + accidents.getCities().getCtsNombre();
			if (accidents.getAccNumeroSiniestro() != null)
				CuerpoMensaje += "</td><td>"
						+ accidents.getAccNumeroSiniestro();
			else
				CuerpoMensaje += "</td><td></td>";

			CuerpoMensaje += "</td><td>" + accidents.getAccNombreConduct()
					+ "</td><td>"
					+ accidents.getAccidentsStates().getAcsNombre()
					+ "</td><td>" + accidents.getAccDescripcion()
					+ "</td></tr>";
		}

		CuerpoMensaje += "</table>";

		for (Assistants Asistentes : listaAsistentes) {
			Email = Asistentes.getAsiEmail().toLowerCase();
			try {
				accidentActsService.enviarNotificacionAsistente(Email,
						CuerpoMensaje);
			} catch (GWorkException e) {
				mostrarMensaje(e.getMessage(), false);
			}
		}
		limpiar();
		limpiarConsulta();
		limpiarConsultaAccidente();
		limpiarFormaAccidente();
	}

	public static Integer getEnviarNotificacion() {
		return EnviarNotificacion;
	}

	public static void setEnviarNotificacion(Integer enviarNotificacion) {
		EnviarNotificacion = enviarNotificacion;
	}

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	public void activarBtnNotificacion() {

		getBtnNotificacion().setDisabled(false);
	}

	public void desActivarBtnNotificacion() {

		getBtnNotificacion().setDisabled(true);
	}

	public HtmlCommandButton getBtnNotificacion() {
		return btnNotificacion;
	}

	public void setBtnNotificacion(HtmlCommandButton btnNotificacion) {
		this.btnNotificacion = btnNotificacion;
	}
}