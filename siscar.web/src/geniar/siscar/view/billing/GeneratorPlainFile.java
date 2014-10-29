package geniar.siscar.view.billing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

import geniar.siscar.logic.billing.services.BillingAccountAutoInsuranceService;
import geniar.siscar.logic.billing.services.BillingAccountService;
import geniar.siscar.logic.billing.services.GenerateFlatFileSer;
import geniar.siscar.logic.billing.services.GeneratorPlainFileSer;
import geniar.siscar.logic.billing.services.impl.BillingAccountAutoInsuranceServiceImpl;
import geniar.siscar.logic.billing.services.impl.BillingAccountServiceImpl;
import geniar.siscar.logic.billing.services.impl.GenerateFlatFileImpl;
import geniar.siscar.logic.consultas.SearchNoveltyTypes;
import geniar.siscar.logic.consultas.SearchUser;
import geniar.siscar.model.BillingAccountAutoInsuranceVO;
import geniar.siscar.model.FlatFile;
import geniar.siscar.model.FlatFileVO;
import geniar.siscar.model.NoveltyTypes;
import geniar.siscar.model.Period;
import geniar.siscar.model.Users;

import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;

import gwork.exception.GWorkException;

/**
 * @author Diego Humberto Bocanegra
 */
public class GeneratorPlainFile {

	// private BrandService brandService;

	private Long idTipoNovedad;

	private HtmlSelectOneMenu cbxGenerator;
	private Integer opcConfirmacion;
	private boolean activarConfirmacion;
	private boolean showListVehicles;
	private boolean showListFlatFile;
	private boolean showPaginatorListFlatFile;
	private boolean showPaginatorListVehicles;

	private List<FlatFileVO> listFlatFile;
	private List<BillingAccountAutoInsuranceVO> listVehicles;
	private NoveltyTypes listNovedad;
	private String NombreNovedad = null;
	private static int GENERAR_ARCHIVO_PLANO = 1;
	private static int REGENERAR_ARCHIVO_PLANO = 2;

	private HtmlCommandButton btnGenerar;
	private HtmlCommandButton btnReGenerarDatos;

	private GeneratorPlainFileSer generatorPlainFileService;
	private BillingAccountService billingAccountService;
	private GenerateFlatFileSer generateFlatFileSer;
	private BillingAccountAutoInsuranceService billingAccountAutoInsuranceService;
	private String annoPeriodo;
	private Period Periodo;
	private List<FlatFile> listff;

	private LoginPage loginPage = (LoginPage) FacesUtils
			.getManagedBean("loginPage");

	public GeneratorPlainFile() {
		showListFlatFile = true;
		showPaginatorListFlatFile = true;
		showListVehicles = false;
		showPaginatorListVehicles = false;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	public HtmlSelectOneMenu getCbxGenerator() {
		return cbxGenerator;
	}

	public void setCbxBrand(HtmlSelectOneMenu cbxGenerator) {
		this.cbxGenerator = cbxGenerator;
	}

	public void Validar() throws GWorkException {

		if (idTipoNovedad == -1) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.TIPONOVEDAD"));
		}

		if (idTipoNovedad != 4)
			desActivarBtnReGenerarDatos();
	}

	public void action_consultar(ActionEvent actionEvent) {
		try {
			Validar();
			if (idTipoNovedad.longValue() != new Long(
					ParametersUtil.COBRO_AUTOSEGURO)) {
				listFlatFile = consultarFlatFile(idTipoNovedad);

				if (listFlatFile == null || listFlatFile.size() <= 0) {
					if (idTipoNovedad == 4) {
						Long FechaInicio = null;
						Long FechaFin = null;

						int anho = Integer.parseInt(ManipulacionFechas
								.getAgno(new Date()));
						int mes = ManipulacionFechas.getNumeroMes(new Date());
						int CantidadDias = ManipulacionFechas.maximoDiasMes(
								anho, mes);

						FechaInicio = new Long(anho + "0" + mes + "0" + 1);
						FechaFin = new Long(anho + "0" + mes + CantidadDias);
						if (new BillingAccountServiceImpl()
								.ConsultarFlatFileCiatCasaCiat(FechaInicio,
										FechaFin)) {
							desActivarBtnReGenerarDatos();
							desActivarBtnGenerar();
							throw new GWorkException(
									Util
											.loadErrorMessageValue("FLAT.FILE.CIAT.CASA.CIAT.EXISTE")
											+ " "
											+ ManipulacionFechas
													.getNombreMesCompleto(new Date()));
						} else
							activarBtnReGenerarDatos();
					}
					desActivarBtnGenerar();
					throw new GWorkException(
							"No se encontraron registros pendientes para generar");
				}
				showListFlatFile = true;
				showPaginatorListFlatFile = true;
				showListVehicles = false;
				showPaginatorListVehicles = false;
			} else if (idTipoNovedad.longValue() == new Long(
					ParametersUtil.COBRO_AUTOSEGURO)) {
				annoPeriodo = ManipulacionFechas.getAgno(new Date());
				Periodo = billingAccountAutoInsuranceService
						.consultarPeriodByAnoAutoSeguro(annoPeriodo);

				listVehicles = billingAccountAutoInsuranceService
						.listVehiclesFlatFile(annoPeriodo, Periodo);
				if (listVehicles == null || listVehicles.size() <= 0)
					throw new GWorkException(Util
							.loadMessageValue("AUTOSEGURO.NOCOBRO"));

				showListFlatFile = false;
				showPaginatorListFlatFile = false;
				showListVehicles = true;
				showPaginatorListVehicles = true;
			}
			setListFlatFile(listFlatFile);
			activarBtnGenerar();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de mostrar el mensaje de confirmacion
	 * 
	 * @param event
	 * @throws GWorkException
	 */
	public void mostrarGenerarArchivo(ActionEvent event) throws GWorkException {
		try {
			Validar();

			activarConfirmacion = true;
			setOpcConfirmacion(getGenerar());
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_Generar() {
		try {

			if (idTipoNovedad.longValue() == ParametersUtil.COBRO_AUTOSEGURO)
				NombreNovedad = Util.loadMessageValue("COBRO_AUTOSURE");
			else {
				listNovedad = new SearchNoveltyTypes()
						.consultarTipoNovedadPorId(idTipoNovedad);
				NombreNovedad = listNovedad.getNtNombre().toString();
			}
			if (idTipoNovedad.longValue() != ParametersUtil.COBRO_AUTOSEGURO) {
				listFlatFile = consultarFlatFile(idTipoNovedad);
				if (listFlatFile != null && listFlatFile.size() > 0) {
					new GenerateFlatFileImpl().GeneradorArchivoPlano(
							listFlatFile, NombreNovedad);

					getGeneratorPlainFileService().ActualizarEstado(
							listFlatFile);

					mostrarMensaje(Util.loadMessageValue("EXITO.GENERAR"),
							false);
					limpiar();
				}
			} else if (idTipoNovedad.longValue() == ParametersUtil.COBRO_AUTOSEGURO) {
				listff = new BillingAccountAutoInsuranceServiceImpl()
						.action_aceptar(listVehicles, loginPage.getLogin(),
								annoPeriodo, Periodo);
				listFlatFile = new ArrayList<FlatFileVO>();
				for (FlatFile flatFile : listff) {
					Users user = SearchUser.consultarUsuarios(null,
							flatFile.getFfCarne().toString(), 2).get(0);
					FlatFileVO flatFileVO = new FlatFileVO();
					flatFileVO.setFfAsignatario(user.getUsrNombre());
					if (user.getUsrApellido() != null)
						flatFileVO.setFfAsignatario(user.getUsrNombre() + " "
								+ user.getUsrApellido());
					flatFileVO.setFfCarne(flatFile.getFfCarne().toString());
					flatFileVO.setFfCodigo(flatFile.getFfCodigo().longValue());
					flatFileVO.setFfConcepto(flatFile.getFfConcepto()
							.longValue());
					flatFileVO.setFfDescripcion(flatFile.getFfDescripcion()
							.toString().trim());
					flatFileVO.setFfDocumento(flatFile.getFfDocumento()
							.toString().trim());
					flatFileVO.setFfFecha(flatFile.getFfFecha().longValue());
					flatFileVO.setFfMoneda(flatFile.getFfMoneda().longValue());
					flatFileVO.setFfUnidades(flatFile.getFfUnidades()
							.longValue());
					flatFileVO.setFfValor(flatFile.getFfValor().toString());
					listFlatFile.add(flatFileVO);
				}
				new GenerateFlatFileImpl().GeneradorArchivoPlano(listFlatFile,
						NombreNovedad);

				getGeneratorPlainFileService().ActualizarEstado(listFlatFile);
				mostrarMensaje(Util.loadMessageValue("EXITO.GENERAR"), false);
				limpiar();
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de mostrar el mensaje de confirmacion para la volver a
	 * renovar los datos de la tabla FlatFile
	 * 
	 * @param event
	 * @throws GWorkException
	 */
	public void mostrarReGenerarDatos(ActionEvent event) throws GWorkException {
		try {
			Validar();
			activarConfirmacion = true;
			setOpcConfirmacion(getReGenerar());
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de volver a generar los datos para el archivo plano de
	 * nuevo
	 * 
	 * @param param
	 * @return
	 * @throws GWorkException
	 */
	public void action_ReGenerarArchivo() throws GWorkException {
		try {

			new BillingAccountServiceImpl().CobroCiatCasaCiat();

			mostrarMensaje(Util.loadMessageValue("EXITO.REGENERADOS"), false);
			limpiar();
		} catch (RuntimeException re) {
			FacesUtils.addErrorMessage(re.getMessage());
		}

		catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public List<FlatFileVO> getListFlatFile() {
		return listFlatFile;
	}

	public void setListFlatFile(List<FlatFileVO> listFlatFiles) {
		listFlatFile = listFlatFiles;
	}

	public Long getidTipoNovedad() {
		return idTipoNovedad;
	}

	public void setidTipoNovedad(Long idTipoNoveda) {
		idTipoNovedad = idTipoNoveda;
	}

	public void action_limpiar(ActionEvent event) {
		limpiar();
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public static Integer getGenerar() {
		return GENERAR_ARCHIVO_PLANO;
	}

	public static void setGenerar(Integer Generar) {
		GENERAR_ARCHIVO_PLANO = Generar;
	}

	public static Integer getReGenerar() {
		return REGENERAR_ARCHIVO_PLANO;
	}

	public static void setReGenerar(Integer Generar) {
		REGENERAR_ARCHIVO_PLANO = Generar;
	}

	public void activarBtnGenerar() {

		getBtnGenerar().setDisabled(false);
	}

	public void desActivarBtnGenerar() {

		getBtnGenerar().setDisabled(true);
	}

	public void activarBtnReGenerarDatos() {

		getBtnReGenerarDatos().setDisabled(false);
	}

	public void desActivarBtnReGenerarDatos() {

		getBtnReGenerarDatos().setDisabled(true);
	}

	public HtmlCommandButton getBtnGenerar() {
		return btnGenerar;
	}

	public void setBtnGenerar(HtmlCommandButton btngenerar) {
		btnGenerar = btngenerar;
	}

	public HtmlCommandButton getBtnReGenerarDatos() {
		return btnReGenerarDatos;
	}

	public void setBtnReGenerarDatos(HtmlCommandButton btnreGenerarDatos) {
		btnReGenerarDatos = btnreGenerarDatos;
	}

	public void limpiar() {
		idTipoNovedad = -1L;
		listFlatFile = null;
		showListFlatFile = true;
		showListVehicles = false;
		showPaginatorListVehicles = false;
		desActivarBtnGenerar();
		desActivarBtnReGenerarDatos();
	}

	/**
	 * Metodo encargado de consultar un archivo flatFile por tipo de novedad
	 * 
	 * @param param
	 * @return
	 * @throws GWorkException
	 */
	public List<FlatFileVO> consultarFlatFile(Long IdNovelty)
			throws GWorkException {
		return getGeneratorPlainFileService().consultarFlatFile(IdNovelty);
	}

	public GeneratorPlainFileSer getGeneratorPlainFileService() {
		return generatorPlainFileService;
	}

	public void setGeneratorPlainFileService(
			GeneratorPlainFileSer generatorPlainFileService) {
		this.generatorPlainFileService = generatorPlainFileService;
	}

	public BillingAccountService getBillingAccountService() {
		return billingAccountService;
	}

	public void setBillingAccountService(
			BillingAccountService billingAccountService) {
		this.billingAccountService = billingAccountService;
	}

	public GenerateFlatFileSer getGenerateFlatFileSer() {
		return generateFlatFileSer;
	}

	public void setGenerateFlatFileSer(GenerateFlatFileSer generateFlatFileSer) {
		this.generateFlatFileSer = generateFlatFileSer;
	}

	public BillingAccountAutoInsuranceService getBillingAccountAutoInsuranceService() {
		return billingAccountAutoInsuranceService;
	}

	public void setBillingAccountAutoInsuranceService(
			BillingAccountAutoInsuranceService billingAccountAutoInsuranceService) {
		this.billingAccountAutoInsuranceService = billingAccountAutoInsuranceService;
	}

	public List<BillingAccountAutoInsuranceVO> getListVehicles() {
		return listVehicles;
	}

	public void setListVehicles(List<BillingAccountAutoInsuranceVO> listVehicles) {
		this.listVehicles = listVehicles;
	}

	/**
	 * @return the showListVehicles
	 */
	public boolean isShowListVehicles() {
		return showListVehicles;
	}

	/**
	 * @param showListVehicles
	 *            the showListVehicles to set
	 */
	public void setShowListVehicles(boolean showListVehicles) {
		this.showListVehicles = showListVehicles;
	}

	/**
	 * @return the showListFlatFile
	 */
	public boolean isShowListFlatFile() {
		return showListFlatFile;
	}

	/**
	 * @param showListFlatFile
	 *            the showListFlatFile to set
	 */
	public void setShowListFlatFile(boolean showListFlatFile) {
		this.showListFlatFile = showListFlatFile;
	}

	/**
	 * @return the showPaginatorListFlatFile
	 */
	public boolean isShowPaginatorListFlatFile() {
		return showPaginatorListFlatFile;
	}

	/**
	 * @param showPaginatorListFlatFile
	 *            the showPaginatorListFlatFile to set
	 */
	public void setShowPaginatorListFlatFile(boolean showPaginatorListFlatFile) {
		this.showPaginatorListFlatFile = showPaginatorListFlatFile;
	}

	/**
	 * @return the showPaginatorListVehicles
	 */
	public boolean isShowPaginatorListVehicles() {
		return showPaginatorListVehicles;
	}

	/**
	 * @param showPaginatorListVehicles
	 *            the showPaginatorListVehicles to set
	 */
	public void setShowPaginatorListVehicles(boolean showPaginatorListVehicles) {
		this.showPaginatorListVehicles = showPaginatorListVehicles;
	}

	/**
	 * @return the annoPeriodo
	 */
	public String getAnnoPeriodo() {
		return annoPeriodo;
	}

	/**
	 * @param annoPeriodo
	 *            the annoPeriodo to set
	 */
	public void setAnnoPeriodo(String annoPeriodo) {
		this.annoPeriodo = annoPeriodo;
	}

	/**
	 * @return the periodo
	 */
	public Period getPeriodo() {
		return Periodo;
	}

	/**
	 * @param periodo
	 *            the periodo to set
	 */
	public void setPeriodo(Period periodo) {
		Periodo = periodo;
	}

	/**
	 * @return the listff
	 */
	public List<FlatFile> getListff() {
		return listff;
	}

	/**
	 * @param listff
	 *            the listff to set
	 */
	public void setListff(List<FlatFile> listff) {
		this.listff = listff;
	}
}
