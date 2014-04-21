//package geniar.siscar.view.policies;
//
//import geniar.siscar.logic.policies.services.SoatService;
//import geniar.siscar.util.FacesUtils;
//import geniar.siscar.util.ManipulacionFechas;
//import geniar.siscar.util.PopupMessagePage;
//import geniar.siscar.util.Util;
//import gwork.exception.GWorkException;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.faces.event.ActionEvent;
//import javax.faces.event.ValueChangeEvent;
//
//import com.icesoft.faces.component.ext.HtmlDataTable;
//import com.icesoft.faces.component.ext.HtmlInputText;
//
//public class SoatPage {
//
//	private Long soaCodigo;
//	private Date soaFechaInicio;
//	private Date soaFechaTerminacion;
//	private Float soaValorPrima;
//	private Float soaValorContribucion;
//	private Float soaValorTotal;
//	private String soaDocumento1;
//	private String soaDocumento2;
//	private String soaNumero;
//	private String soaNuevoNumero;
//	private String placaVehiculo;
//
//	private HtmlInputText txtPlacaVehiculo;
//	private HtmlInputText txtNumeroSoat;
//	private HtmlInputText txtNuevoSoat;
//	private HtmlInputText txtFechaIni;
//	private HtmlInputText txtFechaFin;
//	private HtmlInputText txtValorPrima;
//	private HtmlInputText txtValorContrib;
//	private HtmlInputText txtDoc1;
//	private HtmlInputText txtDoc2;
//	private HtmlInputText txtValorTotal;
//	private HtmlDataTable tblInactiveSoats;
//	
//	private boolean disableCrear;
//	private boolean disableModificar;
//	private boolean disableReemplazar;
//	
//	private boolean showTipoPoliza;
//	private boolean showNumeroPoliza;
//	private boolean fechaFinAble;
//	private boolean showTableSoats;
//
//	private SoatService soatService;
//
//	private List<Soat> listaSoats;
//
//	private boolean flagPlaca;
//	private boolean flagNumSoat;
//	
//	private boolean activarConfirmacion;
//	private Integer opcConfirmacion;
//	private static Integer MODIFICAR = 1;
//	private static Integer REEMPLAZAR = 3;
//
//	/**
//	 * Construtor.
//	 */
//	public SoatPage() {
//		fechaFinAble = true;
//		soaFechaInicio = new Date();
//		soaValorTotal = 0.00F;
//		disableCrear = false;
//		disableModificar = true;
//		disableReemplazar = true;
//	}
//
//	public void action_crearSoat(ActionEvent event) {
//		try {
//			Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");
//			ManipulacionFechas.validarAnhoFecha(soaFechaInicio,"");
//			ManipulacionFechas.validarAnhoFecha(soaFechaTerminacion,"");
//			Util.validarLimite(soaNumero, 38, 2, "ERROR.LIMSOAT");
//			validarDatos();
//
//			soatService.crearSoat(placaVehiculo.toUpperCase(), 0L, soaFechaInicio, 
//					soaFechaTerminacion, soaValorPrima, soaValorContribucion,
//					soaValorTotal, soaDocumento1, soaDocumento2, Long.valueOf(soaNumero));
//			
//			mostrarMensaje(Util.loadMessageValue("EXITO"),false);
//			limpiar();
//		} catch (GWorkException e) {
//			FacesUtils.addErrorMessage(e.getMessage());
//		}
//	}
//
//	public void action_modificarSoat(ActionEvent event) {
//		try {
//			activarConfirmacion = true;
//			validarDatos();
//			if (soaNuevoNumero == null) 
//				throw new GWorkException(Util.loadErrorMessageValue("ERROR.NUMEROSOAT"));
//
//			if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(""+soaNuevoNumero)) 
//				throw new GWorkException(Util.loadErrorMessageValue("CARACTER.ESPECIAL"));
//			
//			if (!Util.validarNumerosConsulta(""+soaNuevoNumero)) {
//				throw new GWorkException(Util.loadErrorMessageValue("CARACTER.NUMERO"));
//			}
//			Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");
//			Util.validarLimite(soaNumero, 38, 2, "ERROR.LIMSOAT");
//			Util.validarLimite(soaNuevoNumero, 38, 2, "ERROR.LIMSOAT");
//			ManipulacionFechas.validarAnhoFecha(soaFechaInicio,"");
//			setActivarConfirmacion(true);
//			setOpcConfirmacion(MODIFICAR);
//			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);
//			
//		} catch (GWorkException e) {
//			FacesUtils.addErrorMessage(e.getMessage());
//			
//		}
//	}
//
//	public void action_modificarSoat() {
//		try {
//			Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");
//			Util.validarLimite(soaNumero, 38, 2, "ERROR.LIMSOAT");
//			ManipulacionFechas.validarAnhoFecha(soaFechaInicio,"");
//			validarDatos();
//
//			soatService.modificarSoat(
//					placaVehiculo, soaCodigo, soaFechaInicio, 
//					soaFechaTerminacion, soaValorPrima, soaValorContribucion,
//					soaValorTotal, soaDocumento1, soaDocumento2,
//					Long.valueOf(soaNumero), Long.valueOf(soaNuevoNumero));
//
//			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"),false);
//			limpiar();
//
//		} catch (GWorkException e) {
//			FacesUtils.addErrorMessage(e.getMessage());
//		}
//	}
//	
//	public void action_consultarSoat(ActionEvent event) {
//		try {	
//			boolean condicion1 = false;
//			boolean condicion2 = false;
//			
//			if (placaVehiculo.trim().length() > 0) {
//				Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");
//				condicion1 = true;
//			}
//			
//			if (soaNumero.trim().length() > 0) {
//				Util.validarLimite(soaNumero, 38, 2, "ERROR.LIMSOAT");
//				condicion2 = true;
//			}
//			
//			if (!condicion1 && !condicion2) {
//				throw new GWorkException(Util.loadErrorMessageValue("DATOSVACIOS"));
//			}
//			
//			listaSoats = soatService.consultarTodosSoatVehiculo(
//					placaVehiculo.toUpperCase(), Long.valueOf("0"+soaNumero));
//
//			if (listaSoats != null && listaSoats.size() > 0) {
//				Soat soat = null;
//				for (Soat soat1 : listaSoats) {
//					if (soat1.getSoaEstado() == 1) { //Es el soat activo
//						soat = soat1;
//						listaSoats.remove(soat1);
//						break;
//					}
//				}
//				if (soat != null) {
//					desplegarSoat(soat);
//				} else {
//					limpiar();
//					throw new GWorkException(Util.loadErrorMessageValue("ERROR.SOATNOENCONTRADO"));
//				}
//			} else {
//				limpiar();
//				throw new GWorkException(Util.loadErrorMessageValue("ERROR.SOATNOENCONTRADO"));
//			}
//			disableModificar = false;
//			disableReemplazar = false;
//		} catch (GWorkException e) {
//			FacesUtils.addErrorMessage(e.getMessage());
//		}
//	}
//	
//	public void action_reemplazarSOAT(ActionEvent event) {		
//		try {
//			Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");
//			Util.validarLimite(soaNumero, 38, 2, "ERROR.LIMSOAT");
//			ManipulacionFechas.validarAnhoFecha(soaFechaInicio,"");
//			validarDatos();
//			setOpcConfirmacion(REEMPLAZAR);
//			setActivarConfirmacion(true);
//			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), isActivarConfirmacion());
//		} catch (GWorkException e) {
//			FacesUtils.addErrorMessage(e.getMessage());
//		}		
//	}
//	
//	public void reemplazarSoat() {
//		try {
//			Util.validarLimite(placaVehiculo, 20, 2, "ERROR.LIMPLACA");
//			Util.validarLimite(soaNumero, 38, 2, "ERROR.LIMSOAT");
//			ManipulacionFechas.validarAnhoFecha(soaFechaInicio,"");
//			validarDatos();
//			soatService.reemplazarSoat(
//					placaVehiculo, Long.valueOf(soaNumero), soaFechaInicio, soaFechaTerminacion,
//					soaValorPrima, soaValorContribucion, soaDocumento1, soaDocumento2);
//			mostrarMensaje(Util.loadMessageValue("EXITO.REEMPLAZAR"), false);
//			limpiar();
//		} catch (GWorkException e) {
//			FacesUtils.addErrorMessage(e.getMessage());
//		}
//	}
//
//	public void action_limpiarForma(ActionEvent event) {
//		limpiar();
//	}
//
//	public void listener_valorPrima(ValueChangeEvent changeEvent) {
//		if (changeEvent.getNewValue() != null) {
//			setSoaValorPrima(Util.convertirDecimal(
//				changeEvent.getNewValue().toString()));
//			actualizarTotal();
//		}
//	}
//
//	public void listener_valorContrib(ValueChangeEvent changeEvent) {
//		if (changeEvent.getNewValue() != null) {
//			setSoaValorContribucion(Util.convertirDecimal(
//					changeEvent.getNewValue().toString()));
//			actualizarTotal();
//		}
//	}
//
//	public void actualizarTotal() {
//		
//		if (getSoaValorContribucion() == null) {
//			setSoaValorContribucion(0F);
//		}
//		if (getSoaValorPrima() == null) {
//			setSoaValorPrima(0F);
//		}
//		Float soaValorTotal = getSoaValorPrima() + getSoaValorContribucion();
//		setSoaValorTotal(soaValorTotal);
//		txtValorTotal.setValue(getSoaValorTotal());
//	}
//
//	@SuppressWarnings("deprecation")
//	public void listener_fechaIni(ValueChangeEvent changeEvent) {
//		if (changeEvent.getNewValue() != null) {
//			setSoaFechaInicio((Date) changeEvent.getNewValue() );
//			Date date = Util.obtenerFechaAnhoAdelante(getSoaFechaInicio(), false);
//			setSoaFechaTerminacion(date);
//			txtFechaFin.setValue(soaFechaTerminacion);
//		}
//	}
//
//	private void desplegarSoat(Soat soat) {
//		txtPlacaVehiculo.setValue(soat.getVehicles().getVhcPlacaDiplomatica());
//		txtNumeroSoat.setValue(soat.getSoaNumero());
//		txtNuevoSoat.setValue(soat.getSoaNumero());
//		txtFechaIni.setValue(soat.getSoaFechaInicio());
//		txtFechaFin.setValue(soat.getSoaFechaTerminacion());
//		txtValorPrima.setValue(soat.getSoaValorPrima());
//		txtValorContrib.setValue(soat.getSoaValorContribucion());
//		txtValorTotal.setValue(soat.getSoaValorTotal());
//		txtDoc1.setValue(soat.getSoaDocumento1());
//		txtDoc2.setValue(soat.getSoaDocumento2());
//		disableCrear = true;
//		showTableSoats = true;
//	}
//
//	private void limpiar() {
//		if (listaSoats != null) {
//			listaSoats.clear();			
//		}
//		
//		flagNumSoat = false;
//		flagPlaca = false;
//		showTableSoats = false;
//		
//		disableCrear = false;
//		disableModificar = true;
//		disableReemplazar = true;
//		
//		txtPlacaVehiculo.setDisabled(false);
//		txtPlacaVehiculo.setValue("");
//		txtDoc1.setDisabled(false);
//		txtDoc1.setValue("");
//		txtDoc2.setDisabled(false);
//		txtDoc2.setValue("");
//		txtFechaIni.setDisabled(false);
//		txtFechaIni.setValue(new Date());
//		txtFechaFin.setValue(new Date());
//		txtNumeroSoat.setDisabled(false);
//		txtNumeroSoat.setValue("");
//		txtNuevoSoat.setValue("");
//		txtValorContrib.setDisabled(false);
//		txtValorContrib.setValue("");
//		txtValorPrima.setDisabled(false);
//		txtValorPrima.setValue("");
//		txtValorTotal.setValue("");
//	}
//
//	private void validarDatos() throws GWorkException {
//
//		if (placaVehiculo != null && placaVehiculo.trim().length() == 0)
//			throw new GWorkException(Util.loadErrorMessageValue("ERROR.PLACA"));
//
//		if (!Util.validarPlaca(placaVehiculo)) {
//			throw new GWorkException(Util.loadErrorMessageValue("ERROR.PLACA"));
//		}
//
//		if (soaFechaInicio == null || soaFechaTerminacion == null) 
//			throw new GWorkException(Util.loadErrorMessageValue("date.empty"));
//
//		if ( soaValorPrima == 0)
//			throw new GWorkException(Util.loadErrorMessageValue("ERROR.VALORPRIMA"));
//
//		if ( soaValorContribucion == 0)
//			throw new GWorkException(Util.loadErrorMessageValue("ERROR.VALORCONTR"));
//
//		if ( soaValorTotal == 0) 
//			throw new GWorkException(Util.loadErrorMessageValue("field.empty"));			
//
//		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(""+soaDocumento1)) 
//			throw new GWorkException(Util.loadErrorMessageValue("CARACTER.ESPECIAL"));
//
//		if (!Util.validacionSintaxis(""+soaDocumento1, "expresion.caracterEspeciales.soloNumero")) 
//			throw new GWorkException(Util.loadErrorMessageValue("CARACTER.ESPECIAL"));
//
//		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(""+soaDocumento2)) 
//			throw new GWorkException(Util.loadErrorMessageValue("CARACTER.ESPECIAL"));
//
//		if (!Util.validacionSintaxis(""+soaDocumento2, "expresion.caracterEspeciales.soloNumero")) 
//			throw new GWorkException(Util.loadErrorMessageValue("CARACTER.ESPECIAL"));
//
//		if (soaNumero == null) 
//			throw new GWorkException(Util.loadErrorMessageValue("ERROR.NUMEROSOAT"));
//
//		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(""+soaNumero)) 
//			throw new GWorkException(Util.loadErrorMessageValue("CARACTER.ESPECIAL"));
//		
//		if (!Util.validarNumerosConsulta(""+soaNumero)) {
//			throw new GWorkException(Util.loadErrorMessageValue("CARACTER.NUMERO"));
//		}
//
//		if (!soaFechaTerminacion.after(soaFechaInicio)) {
//			throw new GWorkException(Util.loadErrorMessageValue("ERROR.FECHAS"));
//		}
//	}
//
//	public void mostrarMensaje(String mensaje,boolean buttonCancel) {
//		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
//		if (message != null)
//			message.showPopup(mensaje, buttonCancel);
//	}
//	
//	public SoatService getSoatService() {
//		return soatService;
//	}
//
//	public void setSoatService(
//			SoatService soatService) {
//		this.soatService = soatService;
//	}
//
//	public Long getSoaCodigo() {
//		return soaCodigo;
//	}
//
//	public void setSoaCodigo(Long soaCodigo) {
//		this.soaCodigo = soaCodigo;
//	}
//
//	public Date getSoaFechaInicio() {
//		return soaFechaInicio;
//	}
//
//	public void setSoaFechaInicio(Date soaFechaInicio) {
//		this.soaFechaInicio = soaFechaInicio;
//	}
//
//	public Date getSoaFechaTerminacion() {
//		return soaFechaTerminacion;
//	}
//
//	public void setSoaFechaTerminacion(Date soaFechaTerminacion) {
//		this.soaFechaTerminacion = soaFechaTerminacion;
//	}
//
//	public Float getSoaValorPrima() {
//		return soaValorPrima;
//	}
//
//	public void setSoaValorPrima(Float soaValorPrima) {
//		this.soaValorPrima = Util.validarDecimales(soaValorPrima);
//	}
//
//	public Float getSoaValorContribucion() {
//		return soaValorContribucion;
//	}
//
//	public void setSoaValorContribucion(Float soaValorContribucion) {
//		this.soaValorContribucion = Util.validarDecimales(soaValorContribucion);
//	}
//
//	public Float getSoaValorTotal() {
//		return soaValorTotal;
//	}
//
//	public void setSoaValorTotal(Float soaValorTotal) {
//		this.soaValorTotal = Util.validarDecimales(soaValorTotal);
//	}
//
//	public String getSoaDocumento1() {
//		return soaDocumento1;
//	}
//
//	public void setSoaDocumento1(String soaDocumento1) {
//		this.soaDocumento1 = soaDocumento1;
//	}
//
//	public String getSoaDocumento2() {
//		return soaDocumento2;
//	}
//
//	public void setSoaDocumento2(String soaDocumento2) {
//		this.soaDocumento2 = soaDocumento2;
//	}
//
//	public String getSoaNumero() {
//		return soaNumero;
//	}
//
//	public void setSoaNumero(String soaNumero) {
//		this.soaNumero = soaNumero;
//	}
//
//	public HtmlInputText getTxtPlacaVehiculo() {
//		return txtPlacaVehiculo;
//	}
//
//	public void setTxtPlacaVehiculo(HtmlInputText txtPlacaVehiculo) {
//		this.txtPlacaVehiculo = txtPlacaVehiculo;
//	}
//
//	public String getPlacaVehiculo() {
//		return placaVehiculo;
//	}
//
//	public void setPlacaVehiculo(String placaVehiculo) {
//		this.placaVehiculo = placaVehiculo.toUpperCase();
//	}
//
//	public HtmlInputText getTxtNumeroSoat() {
//		return txtNumeroSoat;
//	}
//
//	public void setTxtNumeroSoat(HtmlInputText txtNumeroSoat) {
//		this.txtNumeroSoat = txtNumeroSoat;
//	}
//
//	public HtmlInputText getTxtFechaIni() {
//		return txtFechaIni;
//	}
//
//	public void setTxtFechaIni(HtmlInputText txtFechaIni) {
//		this.txtFechaIni = txtFechaIni;
//	}
//
//	public HtmlInputText getTxtFechaFin() {
//		return txtFechaFin;
//	}
//
//	public void setTxtFechaFin(HtmlInputText txtFechaFin) {
//		this.txtFechaFin = txtFechaFin;
//	}
//
//	public HtmlInputText getTxtValorPrima() {
//		return txtValorPrima;
//	}
//
//	public void setTxtValorPrima(HtmlInputText txtValorPrima) {
//		this.txtValorPrima = txtValorPrima;
//	}
//
//	public HtmlInputText getTxtValorContrib() {
//		return txtValorContrib;
//	}
//
//	public void setTxtValorContrib(HtmlInputText txtValorContrib) {
//		this.txtValorContrib = txtValorContrib;
//	}
//
//	public HtmlInputText getTxtDoc1() {
//		return txtDoc1;
//	}
//
//	public void setTxtDoc1(HtmlInputText txtDoc1) {
//		this.txtDoc1 = txtDoc1;
//	}
//
//	public HtmlInputText getTxtDoc2() {
//		return txtDoc2;
//	}
//
//	public void setTxtDoc2(HtmlInputText txtDoc2) {
//		this.txtDoc2 = txtDoc2;
//	}
//
//	public boolean isFechaFinAble() {
//		return fechaFinAble;
//	}
//
//	public void setFechaFinAble(boolean fechaFinAble) {
//		this.fechaFinAble = fechaFinAble;
//	}
//
//	public HtmlInputText getTxtValorTotal() {
//		return txtValorTotal;
//	}
//
//	public void setTxtValorTotal(HtmlInputText txtValorTotal) {
//		this.txtValorTotal = txtValorTotal;
//	}
//
//	public boolean isShowCrearBtn() {
//		return disableCrear;
//	}
//
//	public void setShowCrearBtn(boolean showCrearBtn) {
//		this.disableCrear = showCrearBtn;
//	}
//
//	public boolean isDisableCrear() {
//		return disableCrear;
//	}
//
//	public void setDisableCrear(boolean disableCrear) {
//		this.disableCrear = disableCrear;
//	}
//
//	public boolean isShowTipoPoliza() {
//		return showTipoPoliza;
//	}
//
//	public void setShowTipoPoliza(boolean showTipoPoliza) {
//		this.showTipoPoliza = showTipoPoliza;
//	}
//
//	public boolean isShowNumeroPoliza() {
//		return showNumeroPoliza;
//	}
//
//	public void setShowNumeroPoliza(boolean showNumeroPoliza) {
//		this.showNumeroPoliza = showNumeroPoliza;
//	}
//
//	public List<Soat> getListaSoats() {
//		return listaSoats;
//	}
//
//	public void setListaSoats(List<Soat> listaSoats) {
//		this.listaSoats = listaSoats;
//	}
//
//	public HtmlDataTable getTblInactiveSoats() {
//		return tblInactiveSoats;
//	}
//
//	public void setTblInactiveSoats(HtmlDataTable tblInactiveSoats) {
//		this.tblInactiveSoats = tblInactiveSoats;
//	}
//
//	public boolean isShowTableSoats() {
//		return showTableSoats;
//	}
//
//	public void setShowTableSoats(boolean showTableSoats) {
//		this.showTableSoats = showTableSoats;
//	}
//
//	public boolean isFlagPlaca() {
//		return flagPlaca;
//	}
//
//	public void setFlagPlaca(boolean flagPlaca) {
//		this.flagPlaca = flagPlaca;
//	}
//
//	public boolean isFlagNumSoat() {
//		return flagNumSoat;
//	}
//
//	public void setFlagNumSoat(boolean flagNumSoat) {
//		this.flagNumSoat = flagNumSoat;
//	}
//
//	public boolean isActivarConfirmacion() {
//		return activarConfirmacion;
//	}
//
//	public void setActivarConfirmacion(boolean activarConfirmacion) {
//		this.activarConfirmacion = activarConfirmacion;
//	}
//
//	public Integer getOpcConfirmacion() {
//		return opcConfirmacion;
//	}
//
//	public void setOpcConfirmacion(Integer opcConfirmacion) {
//		this.opcConfirmacion = opcConfirmacion;
//	}
//
//	public boolean isDisableModificar() {
//		return disableModificar;
//	}
//
//	public void setDisableModificar(boolean disableModificar) {
//		this.disableModificar = disableModificar;
//	}
//
//	public boolean isDisableReemplazar() {
//		return disableReemplazar;
//	}
//
//	public void setDisableReemplazar(boolean disableReemplazar) {
//		this.disableReemplazar = disableReemplazar;
//	}
//
//	public String getSoaNuevoNumero() {
//		return soaNuevoNumero;
//	}
//
//	public void setSoaNuevoNumero(String soaNuevoNumero) {
//		this.soaNuevoNumero = soaNuevoNumero;
//	}
//
//	public HtmlInputText getTxtNuevoSoat() {
//		return txtNuevoSoat;
//	}
//
//	public void setTxtNuevoSoat(HtmlInputText txtNuevoSoat) {
//		this.txtNuevoSoat = txtNuevoSoat;
//	}
//}
