package geniar.siscar.view.newness;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.consultas.SearchNewness;
import geniar.siscar.logic.newness.services.RegistryNewnessCostCenterService;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import geniar.siscar.view.autenticate.LoginPage;
import geniar.siscar.view.beanSession.VehicleAssignationBean;
import gwork.exception.GWorkException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIData;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.selectinputdate.SelectInputDate;

public class RegistryNewnessCostCenterPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HtmlCommandButton buttonGuardar;
	private HtmlCommandButton buttonConsultar;
	private HtmlCommandButton buttonLimpiar;
	private HtmlCommandButton ingresarBtn;
	private String placa;
	private String fechaFinalAsignacion;
	private SelectInputDate fechaFinalAsiActual;
	private Date fechaFinal;
	private Long idCentroCostos;
	private List<CostsCentersVehicles> valoresCentroCostosOld;
	private List<CostsCentersVehicles> valoresCentroCostosNew;
	private String usrLogin;
	private String descripcionNovedad;
	private String asignatario;
	private String tipoAsignacion;
	private String fechaInicialAsignacion;
	private String tipoVehiculo;
	private String nombreCentro;
	private String porcentaje;
	private String mjsCentroActual;
	private String mjsCentroAnterior;
	private boolean notEmpty;
	private boolean isFechaDisabled;
	private boolean isActiveConsult;
	private boolean visibleLimpiar;
	private boolean isActiveSave;
	private boolean isActiveLabel1;
	private boolean isActiveLabel2;
	private boolean showCostCentersTable;
	private ConsultsService consultsService;

	private RegistryNewnessCostCenterService registryNewnessCostCenterService;
	private VehicleAssignationBean vehicleAssignationBean = (VehicleAssignationBean) FacesUtils.getManagedBean("vehicleAssignationBean");
	
	/*nueva variable*/
	private UIData table;

	public void consultarCentroCostos_action(ActionEvent event) throws GWorkException {
		try {
			boolean esValido = true;
			Vehicles vehicles = null;

			if (placa != null && placa.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("PLACA"));

			if (placa != null)
				esValido = Util.validarPlaca(placa);

			if (!esValido)
				throw new GWorkException(Util.loadErrorMessageValue("PLACA.INCORRECTO"));

			if (placa != null && placa.trim().length() < 2)
				throw new GWorkException(Util.loadErrorMessageValue("PLACA.MINIMO"));

			removeIdSessionValues();

			String idVehiculo = null;
			String idAsignacion = null;
			VehiclesAssignation assignation = registryNewnessCostCenterService.consultarDatosAsignacion(placa.trim()
					.toUpperCase());
			List<Vehicles> listVehicles = new VehiclesDAO().findByVhcPlacaDiplomatica(placa.trim().toUpperCase());
			if (listVehicles != null && listVehicles.size() > 0)
				vehicles = listVehicles.get(0);

			if (vehicles == null)
				throw new GWorkException(Util.loadErrorMessageValue("PLACA.NOEXISTE"));

			if (assignation == null && vehicles != null) {
				limpiarDatosNovedadCentroCosto();
				throw new GWorkException(Util.loadErrorMessageValue("DATOS.NULL"));
			}

			asignatario = assignation.getRequests().getUsersByRqsUser().getUsrNombre();
			if (assignation.getRequests().getLegateesTypes() != null)
				tipoAsignacion = assignation.getRequests().getLegateesTypes().getLgtNombre();

			fechaInicialAsignacion = assignation.getVhaFechaInicio().toString();
			fechaFinalAsignacion = assignation.getVhaFechaTermina().toString();
			tipoVehiculo = assignation.getVehicles().getVehiclesTypes().getVhtNombre();

			idVehiculo = assignation.getVehicles().getVhcCodigo().toString();
			idAsignacion = assignation.getVhaCodigo().toString();

			valoresCentroCostosOld = SearchNewness.ConsultarCentrosDeCostosActuales(new Long(idVehiculo), new Long(
					idAsignacion));

			if (valoresCentroCostosOld != null && valoresCentroCostosOld.size() > 0)
				setNotEmpty(true);

			setValoresCentroCostosOld(valoresCentroCostosOld);

			FacesUtils.getSession().setAttribute("idVehiculoCostCenter", idVehiculo);
			FacesUtils.getSession().setAttribute("idAsignacionCostCenter", idAsignacion);
			FacesUtils.getSession().setAttribute("fechaInicial", assignation.getVhaFechaInicio());
			FacesUtils.getSession().setAttribute("fechaFinalAsignacion", assignation.getVhaFechaTermina());
			activarLabel1();
			desActivarLabel2();

		} catch (GWorkException e) {
			removeIdSessionValues();
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_consultar_costCenter(ActionEvent event) throws GWorkException {
		setShowCostCentersTable(true);
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void guardarCentroCostos_action(ActionEvent event) throws GWorkException {
		Date fechaTermina = (Date) FacesUtils.getSession().getAttribute("fechaFinalAsignacion");		
		consultsService = new ConsultsServiceImpl();
		
		boolean esValido = true;
		try {
			if (nombreCentro != null && nombreCentro.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("NOMBRE.CENTRO"));

			if (nombreCentro != null && nombreCentro.trim().length() != 0)
				esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(nombreCentro);

			if (!esValido)
				throw new GWorkException(Util.loadErrorMessageValue("NOMBRE.CENTRO.CARACTER"));

			if (porcentaje != null && porcentaje.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("PORCENTAJE.CENTRO"));

			if (porcentaje != null && porcentaje.trim().length() != 0)
				esValido = Util.validarNumerosParametros(porcentaje);

			if (!esValido)
				throw new GWorkException(Util.loadErrorMessageValue("PORCENTAJE.CENTRO.CARACTER"));

			int porcentaj = Integer.parseInt(porcentaje);

			if (porcentaj > 100 || porcentaj <= 0)
				throw new GWorkException(Util.loadErrorMessageValue("PORCENTAJE.ERROR"));
	
			if (fechaFinal != null)
				FacesUtils.getSession().setAttribute("fechaFinalAsig", fechaFinal);
			
			Date fechaFinalTemp = (Date) FacesUtils.getSession().getAttribute("fechaFinalAsig");
						
			if (fechaFinal == null && fechaFinalTemp == null)
				throw new GWorkException(Util.loadErrorMessageValue("FECHA.CENTRO"));
			
			Date fechaInicial = (Date) FacesUtils.getSession().getAttribute("fechaInicial");

			/*Se hace la validación de que la fecha de finalización de los cc actuales
			 *se encuentre dentro de los límites de la asignación del vehículo, pero para
			 *efectos de validar también de que la fecha de inicio de los nuevos CC se encuentre
			 *dentro de esos límites. Es por eso que se valida la fecha final más 1 día*/
			if (fechaFinal != null && fechaInicial != null)
				registryNewnessCostCenterService.validarFechaFinalAsignacion(Util.traerFechaMasDias(1, fechaFinal), fechaInicial, fechaTermina);
			
			String idVehiculo=(String)FacesUtils.getSession().getAttribute("idVehiculoCostCenter");
			String idAsignacion=(String)FacesUtils.getSession().getAttribute("idAsignacionCostCenter");
			
			if (fechaFinal != null)
				 registryNewnessCostCenterService.
				 ValidarCentrosDeCostosActuales(new Long(idVehiculo), new Long(idAsignacion), fechaFinalTemp);

			//consultsService.consultCostCenter(nombreCentro.trim().toUpperCase());

			removePorcentaje();
			FacesUtils.getSession().setAttribute("porcentaje", porcentaje);
			desActivarLabel1();
			activarLabel2();

			if (valoresCentroCostosNew == null || valoresCentroCostosNew != null && valoresCentroCostosNew.size() == 0) {
				datosCentroCosto(nombreCentro, porcentaje);
				desActivarFechas();
				desactivarLimpiar();
				
			} else if (valoresCentroCostosNew != null && valoresCentroCostosNew.size() > 0) {
				if (validarCampos(valoresCentroCostosNew, Util.convertirCadena(porcentaje))){
					datosCentroCosto(nombreCentro, porcentaje);
				}
			}
			activarGuardado(true);
			
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void datosCentroCosto(String centroCosto, String percent) throws GWorkException{
		List<CostsCentersVehicles> valoresCentroCostosNew = getValoresCentroCostosNew();
		if(valoresCentroCostosNew == null)
			valoresCentroCostosNew = new ArrayList<CostsCentersVehicles>();
		
		/*Adicionar el nuevo centro de costo ingresado*/
		CostsCentersVehicles objCCV = new CostsCentersVehicles();
		List<CostsCenters> objCC;
		CostsCenters costCenter = null;
		
		try{
			objCC = registryNewnessCostCenterService.consultarCentroCostos(centroCosto);
			costCenter = (CostsCenters)objCC.get(0);
		}
		catch(Exception e){
			throw new GWorkException(e.getMessage());
		}
		
		//verificar que no exista el cc dentro de la lista
		boolean existe = false;
		for(CostsCentersVehicles ccv:valoresCentroCostosNew){
			if(ccv.getCostsCenters().getCocNumero().equals(centroCosto))
				existe = true;
		}
		
		if(!existe){
			objCCV.setCcrPorcentaje(percent);
			objCCV.setCostsCenters(costCenter);
			
			valoresCentroCostosNew.add(objCCV);
			setValoresCentroCostosNew(valoresCentroCostosNew);
			
			setNombreCentro(null);
	        setPorcentaje(null);
		}
		else
			throw new GWorkException("No se pueden agregar el mismo AEC");
	}
	
	public void guardarListaCC(ActionEvent event) throws GWorkException {
		String login = null;
		
		try{
			LoginPage loginPage = (LoginPage) FacesUtils.getManagedBean("loginPage");
			if (loginPage != null)
				login = loginPage.getLogin();
			else
				login = "no.login";
			
		 	String idVehiculo=(String)FacesUtils.getSession().getAttribute("idVehiculoCostCenter");
			String idAsignacion=(String)FacesUtils.getSession().getAttribute("idAsignacionCostCenter");
			
			guardarCentrosCostos(nombreCentro, idVehiculo, idAsignacion, login, fechaFinal);
		}
		catch(GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void desActivarFechas() {
		fechaFinalAsiActual.setDisabled(true);
		fechaFinalAsiActual.setReadonly(true);
	}

	public void activarFechas() {
		fechaFinalAsiActual.setDisabled(false);
		fechaFinalAsiActual.setReadonly(false);
	}

	public void guardarCentrosCostos(String nombreCentro, String idVehiculo, String idAsignacion, String login,
			Date fechaFinal) throws GWorkException {
		if (validarCampos(valoresCentroCostosNew, -1)){
			
			java.text.SimpleDateFormat objFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
			Date fechaInicial = null;
			Date fechaTermina = null;
	
			try {
				fechaInicial = objFormat.parse(getFechaInicialAsignacion());
				fechaTermina = objFormat.parse(getFechaFinalAsignacion());
			} catch (ParseException e) {
				FacesUtils.addErrorMessage(e.getMessage());
			}
			
			List<CostsCentersVehicles> valoresCentroCostosNew = getValoresCentroCostosNew();
		
			/*Modificar el estado de los CCV anteriores*/
			registryNewnessCostCenterService.actualizarCentrosDeCostos(valoresCentroCostosOld, login, 
											new Long(idVehiculo),fechaInicial, fechaFinal, 0);
			
			Integer conteo=0;
			for(CostsCentersVehicles ccv: valoresCentroCostosNew){
				conteo += registryNewnessCostCenterService.guardarCentroCostos(ccv.getCostsCenters(), 
																	login, ccv.getCcrPorcentaje(), idVehiculo, idAsignacion,
																	fechaFinal,fechaTermina);
				
			}
			
			/*Si no se ingresa por lo menos un nuevo AEC, debe deshacer los cambios*/
			if(conteo<=0){
				registryNewnessCostCenterService.actualizarCentrosDeCostos(valoresCentroCostosOld, login, 
												new Long(idVehiculo),fechaInicial, fechaFinal, 1);
			}
			else
				mostrarMensaje("Cambios de los AEC realizado con éxito.", false);
			
			activarGuardado(false);
			limpiarCamposGuardar();
			limpiarDatosNovedadCentroCosto();
		}
	}
	
	public void deleteRow() throws GWorkException{
		List<CostsCentersVehicles> valoresCCNew = getValoresCentroCostosNew();
		
		if (valoresCCNew==null || valoresCCNew.size()<=0)
			throw new GWorkException("lista inicial nula: " + valoresCCNew.size());
		
        int index = getTable().getRowIndex();
        valoresCCNew.remove(index);
        
        setNombreCentro(null);
        setPorcentaje(null);
        
        if (valoresCCNew != null && valoresCCNew.size() == 0){	
			activarFechas();
			activarGuardado(false);
			FacesUtils.getSession().removeAttribute("fechaFinalAsig");
		}
    }

	public void removeIdSessionValues() {
		FacesUtils.getSession().removeAttribute("idAsignacionCostCenter");
		FacesUtils.getSession().removeAttribute("idVehiculoCostCenter");
		FacesUtils.getSession().removeAttribute("fechaInicial");
		FacesUtils.getSession().removeAttribute("fechaFinalAsignacion");
		FacesUtils.getSession().removeAttribute("fechaFinalAsig");
	}

	public void removePorcentaje() {
		FacesUtils.getSession().removeAttribute("porcentaje");
	}

	public void activarGuardado(boolean guardar) {
		buttonConsultar.setDisabled(guardar);
		buttonGuardar.setRendered(guardar);
		setActiveSave(guardar);
	}

	public void activarLimpiar() {
		buttonLimpiar.setDisabled(false);
	}

	public void activarFecha() {
		setFechaDisabled(false);
		fechaFinalAsiActual.setDisabled(false);
	}

	
	public void desActivarFecha() {
		setFechaDisabled(true);
		fechaFinalAsiActual.setDisabled(true);
	}

	public void desactivarLimpiar() {
		buttonLimpiar.setDisabled(true);
	}

	public void limpiar_action(ActionEvent event) throws GWorkException {
		if (validarCampos(valoresCentroCostosNew, -1))
			limpiarDatosNovedadCentroCosto();
	}

	public void limpiarDatosNovedadCentroCosto() {

		setPlaca(null);
		setAsignatario(null);
		setFechaFinalAsignacion(null);
		setFechaInicialAsignacion(null);
		setTipoAsignacion(null);
		setTipoVehiculo(null);
		setNotEmpty(false);
		setActiveSave(false);
		setValoresCentroCostosNew(null);
		setValoresCentroCostosOld(null);
		if (buttonConsultar != null)
			buttonConsultar.setDisabled(false);

		if (fechaFinalAsiActual != null) {
			fechaFinalAsiActual.setValue(null);
			activarFecha();
		}

		setFechaFinal(null);
		if (buttonLimpiar != null)
			activarLimpiar();
		removeIdSessionValues();
		removePorcentaje();
	}

	public boolean validarCampos(List<CostsCentersVehicles> list, int porcentaje) throws GWorkException {
		int suma = 0;
		if (list != null) {
			for (CostsCentersVehicles costsCentersVehicles : list) {
				suma += Util.convertirCadena(costsCentersVehicles.getCcrPorcentaje());
			}

			if (porcentaje == -1) {
				if (suma < 100) {
					mostrarMensaje(Util.loadErrorMessageValue("PORCENTAJE.ERROR"), false);
					return false;
				} else
					return true;
			}

			if (suma + porcentaje > 100) {
				mostrarMensaje(Util.loadErrorMessageValue("PORCENTAJE.ERROR"), false);
				return false;
			}
			return true;
		}
		return true;
	}

	public void validarCamposCentroCosto(ActionEvent event) throws GWorkException {
		try {

			if (validarCampos(valoresCentroCostosNew, -1)) {
				limpiarDatosNovedadCentroCosto();
				mostrarMensaje(Util.loadMessageValue("EXITO"), false);
				activarFechas();
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void activarLabel1() {
		setActiveLabel1(true);
	}

	public void activarLabel2() {
		setActiveLabel2(true);
	}

	public void desActivarLabel1() {
		setActiveLabel1(false);
	}

	public void desActivarLabel2() {
		setActiveLabel2(false);
	}

	public void limpiarCamposGuardar() {
		setNombreCentro(null);
		setPorcentaje(null);
		setFechaFinal(null);

		if (fechaFinalAsiActual != null)
			fechaFinalAsiActual.setValue(null);
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getFechaFinalAsignacion() {
		return fechaFinalAsignacion;
	}

	public void setFechaFinalAsignacion(String fechaFinalAsignacion) {
		this.fechaFinalAsignacion = fechaFinalAsignacion;
	}

	public Long getIdCentroCostos() {
		return idCentroCostos;
	}

	public void setIdCentroCostos(Long idCentroCostos) {
		this.idCentroCostos = idCentroCostos;
	}

	public String getUsrLogin() {
		return usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	public String getDescripcionNovedad() {
		return descripcionNovedad;
	}

	public void setDescripcionNovedad(String descripcionNovedad) {
		this.descripcionNovedad = descripcionNovedad;
	}

	public String getAsignatario() {
		return asignatario;
	}

	public void setAsignatario(String asignatario) {
		this.asignatario = asignatario;
	}

	public String getTipoAsignacion() {
		return tipoAsignacion;
	}

	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}

	public String getFechaInicialAsignacion() {
		return fechaInicialAsignacion;
	}

	public void setFechaInicialAsignacion(String fechaInicialAsignacion) {
		this.fechaInicialAsignacion = fechaInicialAsignacion;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public RegistryNewnessCostCenterService getRegistryNewnessCostCenterService() {
		return registryNewnessCostCenterService;
	}

	public void setRegistryNewnessCostCenterService(RegistryNewnessCostCenterService registryNewnessCostCenterService) {
		this.registryNewnessCostCenterService = registryNewnessCostCenterService;
	}

	public String getNombreCentro() {
		return nombreCentro;
	}

	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	public boolean isNotEmpty() {
		return notEmpty;
	}

	public void setNotEmpty(boolean notEmpty) {
		this.notEmpty = notEmpty;
	}

	public List<CostsCentersVehicles> getValoresCentroCostosOld() {
		return valoresCentroCostosOld;
	}

	public void setValoresCentroCostosOld(List<CostsCentersVehicles> valoresCentroCostosOld) {
		this.valoresCentroCostosOld = valoresCentroCostosOld;
	}

	public List<CostsCentersVehicles> getValoresCentroCostosNew() {
		return valoresCentroCostosNew;
	}

	public void setValoresCentroCostosNew(List<CostsCentersVehicles> valoresCentroCostosNew) {
		this.valoresCentroCostosNew = valoresCentroCostosNew;
	}

	public HtmlCommandButton getButtonGuardar() {
		return buttonGuardar;
	}

	public void setButtonGuardar(HtmlCommandButton buttonGuardar) {
		this.buttonGuardar = buttonGuardar;
	}

	public HtmlCommandButton getButtonConsultar() {
		return buttonConsultar;
	}

	public void setButtonConsultar(HtmlCommandButton buttonConsultar) {
		this.buttonConsultar = buttonConsultar;
	}

	public boolean isActiveConsult() {
		return isActiveConsult;
	}

	public void setActiveConsult(boolean isActiveConsult) {
		this.isActiveConsult = isActiveConsult;
	}

	public boolean isActiveSave() {
		return isActiveSave;
	}

	public void setActiveSave(boolean isActiveSave) {
		this.isActiveSave = isActiveSave;
	}

	public String getMjsCentroActual() throws GWorkException {
		return Util.loadMessageValue("CENTRO_ACTUAL");
	}

	public void setMjsCentroActual(String mjsCentroActual) {
		this.mjsCentroActual = mjsCentroActual;
	}

	public String getMjsCentroAnterior() throws GWorkException {
		return Util.loadMessageValue("CENTRO_ANTERIOR");
	}

	public void setMjsCentroAnterior(String mjsCentroAnterior) {
		this.mjsCentroAnterior = mjsCentroAnterior;
	}

	public boolean isActiveLabel1() {
		return isActiveLabel1;
	}

	public void setActiveLabel1(boolean isActiveLabel1) {
		this.isActiveLabel1 = isActiveLabel1;
	}

	public boolean isActiveLabel2() {
		return isActiveLabel2;
	}

	public void setActiveLabel2(boolean isActiveLabel2) {
		this.isActiveLabel2 = isActiveLabel2;
	}

	public ConsultsService getConsultsService() {
		return consultsService = new ConsultsServiceImpl();
	}

	public void setConsultsService(ConsultsService consultsService) {
		this.consultsService = consultsService;
	}

	public SelectInputDate getFechaFinalAsiActual() {
		return fechaFinalAsiActual;
	}

	public void setFechaFinalAsiActual(SelectInputDate fechaFinalAsiActual) {
		this.fechaFinalAsiActual = fechaFinalAsiActual;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public boolean isShowCostCentersTable() {
		return showCostCentersTable;
	}

	public void setShowCostCentersTable(boolean showCostCentersTable) {
		this.showCostCentersTable = showCostCentersTable;
	}

	public HtmlCommandButton getButtonLimpiar() {
		return buttonLimpiar;
	}

	public void setButtonLimpiar(HtmlCommandButton buttonLimpiar) {
		this.buttonLimpiar = buttonLimpiar;
	}

	public boolean isVisibleLimpiar() {
		return visibleLimpiar;
	}

	public void setVisibleLimpiar(boolean visibleLimpiar) {
		this.visibleLimpiar = visibleLimpiar;
	}

	public boolean isFechaDisabled() {
		return isFechaDisabled;
	}

	public void setFechaDisabled(boolean isFechaDisabled) {
		this.isFechaDisabled = isFechaDisabled;
	}

	public HtmlCommandButton getIngresarBtn() {
		return ingresarBtn;
	}

	public void setIngresarBtn(HtmlCommandButton ingresarBtn) {
		this.ingresarBtn = ingresarBtn;
	}

	public UIData getTable() {
		return table;
	}

	public void setTable(UIData table) {
		this.table = table;
	}
}
