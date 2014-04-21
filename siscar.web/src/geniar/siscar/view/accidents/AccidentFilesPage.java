package geniar.siscar.view.accidents;

import java.io.File;
import java.util.List;
import javax.faces.event.ActionEvent;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.inputfile.InputFile;
import com.icesoft.faces.component.paneltabset.PanelTab;

import geniar.siscar.logic.accidents.services.AccidentFilesService;
import geniar.siscar.logic.accidents.services.DataAccidentsService;
import geniar.siscar.model.Accidents;
import geniar.siscar.model.AccidentsFiles;
import geniar.siscar.util.CreateFile;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.FileUtils;
import geniar.siscar.util.Util;
import geniar.siscar.util.ViewOptionUtil;
import gwork.exception.GWorkException;

public class AccidentFilesPage extends AccidentGeneralsPage {

	private AccidentFilesService accidentFilesService;
	private DataAccidentsService dataAccidentsService;

	private Long acfId;
	private Long idAccidente;
	private String acfRuta;
	private String acfDescripcion;
	private String acfNombre;
	private File file;
	private InputFile inputFile;
	private String ruta;
	private HtmlOutputText lblRuta;
	private HtmlOutputText lblDecripcionArchivo;
	private HtmlOutputText lblNombreArchivo;
	private HtmlOutputText txtCodigo;
	private File source = null;
	private File target = null;
	private HtmlInputText txtRutaArchivo;

	private PanelTab panelOtrosDatos;

	private List<AccidentsFiles> listAccidentsFiles;

	private Integer opcEliminarArchivo;
	private static Integer ELIMINAR = 2;
	private Boolean activarConfirmacion = false;

	private Integer opcEliminarArchivo2;
	private Boolean activarConfirmacion2 = false;

	private Long codigoArchivo_elim;

	public List<AccidentsFiles> getListAccidentsFiles() {
		return listAccidentsFiles;
	}

	public void setListAccidentsFiles(List<AccidentsFiles> listAccidentsFiles) {
		this.listAccidentsFiles = listAccidentsFiles;
	}

	public HtmlOutputText getLblRuta() {
		return lblRuta;
	}

	public void setLblRuta(HtmlOutputText lblRuta) {
		this.lblRuta = lblRuta;
	}

	public HtmlOutputText getLblDecripcionArchivo() {
		return lblDecripcionArchivo;
	}

	public void setLblDecripcionArchivo(HtmlOutputText lblDecripcionArchivo) {
		this.lblDecripcionArchivo = lblDecripcionArchivo;
	}

	public HtmlOutputText getLblNombreArchivo() {
		return lblNombreArchivo;
	}

	public void setLblNombreArchivo(HtmlOutputText lblNombreArchivo) {
		this.lblNombreArchivo = lblNombreArchivo;
	}

	public AccidentFilesService getAccidentFilesService() {
		return accidentFilesService;
	}

	public void setAccidentFilesService(
			AccidentFilesService accidentFilesService) {
		this.accidentFilesService = accidentFilesService;
	}

	public Long getAcfId() {
		return acfId;
	}

	public void setAcfId(Long acfId) {
		this.acfId = acfId;
	}

	public Long getIdAccidente() {
		return idAccidente;
	}

	public void setIdAccidente(Long idAccidente) {
		this.idAccidente = idAccidente;
	}

	public String getAcfRuta() {
		return acfRuta;
	}

	public void setAcfRuta(String acfRuta) {
		this.acfRuta = acfRuta;
	}

	public String getAcfDescripcion() {
		return acfDescripcion;
	}

	public void setAcfDescripcion(String acfDescripcion) {
		this.acfDescripcion = acfDescripcion;
	}

	public String getAcfNombre() {
		return acfNombre;
	}

	public void setAcfNombre(String acfNombre) {
		this.acfNombre = acfNombre;
	}

	public void limpiar() {
		acfDescripcion = null;
		acfId = null;
		acfNombre = null;
		acfRuta = null;
		source = null;
		target = null;
	}

	public void action_limpiar(ActionEvent actionEvent) {

		limpiar();
	}

	public void action_registraArchivo(ActionEvent actionEvent) {

		try {
			if (idAccidente == null || idAccidente.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			validarDatosNulos(acfNombre, acfRuta);

			if (acfRuta != null && acfRuta.trim().length() != 0)
				validarTamanhoCaracteres(acfRuta,
						lblRuta.getValue().toString(), 2, panelOtrosDatos
								.getLabel());

			if (acfDescripcion != null && acfDescripcion.trim().length() != 0)
				validarTamanhoCaracteresMax(acfDescripcion,
						lblDecripcionArchivo.getValue().toString(), 100,
						panelOtrosDatos.getLabel());

			validarEstadoAccidente(idAccidente);

			accidentFilesService.registrarArchivosAccidente(idAccidente,
					acfRuta, acfDescripcion, acfNombre.trim());
			listarArchivos();
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_modificarArchivo(ActionEvent actionEvent) {

		try {
			if (idAccidente == null || idAccidente.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			if (acfId == null || acfId.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ARCHIVO.COSULTA"));

			validarDatosNulos(acfNombre, acfRuta);

			if (acfDescripcion != null && acfDescripcion.trim().length() != 0)
				validarTamanhoCaracteres(acfDescripcion, lblDecripcionArchivo
						.getValue().toString(), 2, panelOtrosDatos.getLabel());

			validarEstadoAccidente(idAccidente);
			accidentFilesService.modificarArchivosAccidente(acfId, idAccidente,
					acfRuta, acfDescripcion, acfNombre);
			listarArchivos();
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_eliminarArchivo(ActionEvent actionEvent) {

		try {
			if (idAccidente == null || idAccidente.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			if (acfId == null || acfId.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ARCHIVO.COSULTA"));
			validarEstadoAccidente(idAccidente);

			activarConfirmacion = true;
			setOpcEliminarArchivo(ELIMINAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void eliminar_archivo() {
		try {
			accidentFilesService.eliminarArchivosAccidente(acfId);
			listarArchivos();
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void validarDatosNulos(String acfNombre, String acfRuta)
			throws GWorkException {

		if (acfNombre == null || acfNombre.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBREARCHIVO.NULO"));

		if (acfRuta == null || acfRuta.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("RUTAARCHIVO.NULO"));

	}

	public HtmlInputText getTxtRutaArchivo() {
		return txtRutaArchivo;
	}

	public void setTxtRutaArchivo(HtmlInputText txtRutaArchivo) {
		this.txtRutaArchivo = txtRutaArchivo;
	}

	public void listarArchivos() {

		try {
			setListAccidentsFiles(null);
			setListAccidentsFiles(accidentFilesService
					.listarArchivos(idAccidente));
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
	}

	public void cargarFormulario(ActionEvent actionEvent) {

		Long codigoArchivo = (Long) txtCodigo.getValue();

		for (AccidentsFiles accidentsFiles : listAccidentsFiles) {

			if (codigoArchivo.longValue() == accidentsFiles.getAcfId()
					.longValue()) {
				acfId = accidentsFiles.getAcfId().longValue();
				acfNombre = accidentsFiles.getAcfNombre();
				acfRuta = accidentsFiles.getAcfRuta();
				acfDescripcion = accidentsFiles.getAcfDescripcion();
				break;

			}
		}
	}

	public void action_eliminarArchivoTabla(ActionEvent actionEvent) {
		setCodigoArchivo_elim((Long) txtCodigo.getValue());
		activarConfirmacion2 = true;
		setOpcEliminarArchivo2(ELIMINAR);
		try {
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion2);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void eliminarArchivoTabla() {
		Long codigoArchivo = getCodigoArchivo_elim();

		for (AccidentsFiles accidentsFiles : listAccidentsFiles) {

			if (codigoArchivo.longValue() == accidentsFiles.getAcfId()
					.longValue()) {
				try {
					validarEstadoAccidente(idAccidente);
					accidentFilesService
							.eliminarArchivosAccidente(codigoArchivo);
					listarArchivos();
					mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"),
							false);
					break;
				} catch (GWorkException e) {
					FacesUtils.addErrorMessage(e.getMessage());
				}
			}
		}
	}

	public HtmlOutputText getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(HtmlOutputText txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public void action_cargarArchivo(ActionEvent actionEvent) {

		try {

			if (idAccidente == null || idAccidente.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));
			validarEstadoAccidente(idAccidente);

			if (acfRuta != null && acfNombre != null
					&& acfRuta.trim().length() > 0
					&& acfNombre.trim().length() > 0)
				throw new GWorkException(
						"Debe registrar el archivo cargado actualmente");

			if (acfDescripcion != null && acfDescripcion.trim().length() != 0)
				validarTamanhoCaracteres(acfDescripcion, lblDecripcionArchivo
						.getValue().toString(), 2, panelOtrosDatos.getLabel());

			inputFile = (InputFile) actionEvent.getSource();
			if (inputFile.getStatus() == InputFile.SAVED) {

				String rutaSource = inputFile.getFileInfo().getPhysicalPath();
				rutaSource = rutaSource.substring(0, rutaSource
						.lastIndexOf(File.separator));

				String rutaSource2 = rutaSource;
				rutaSource2 = rutaSource2.substring(0, rutaSource2
						.lastIndexOf(File.separator));
				rutaSource2 = rutaSource2.substring(0, rutaSource2
						.lastIndexOf(File.separator));

				source = new File(rutaSource);
				target = new File(rutaSource2
						+ Util.loadMessageValue("URLDATOSACCIDENTES")
						+ idAccidente.toString());
				acfRuta = ".." + Util.loadMessageValue("URLDATOSACCIDENTES")
						+ idAccidente.toString();

				acfNombre = inputFile.getFileInfo().getFileName().trim();
				String nuevoFile = acfNombre.replace(" ", "");
				if (acfNombre.length() > 100)
					throw new GWorkException(Util
							.loadErrorMessageValue("NOMBREARCHIVO.EXTENSA"));

				CreateFile.createFolder(rutaSource2
						+ Util.loadMessageValue("URLDATOSACCIDENTES"),
						idAccidente.toString());
				if (acfRuta.length() > 300)
					throw new GWorkException(Util
							.loadErrorMessageValue("RUTAARCHIVO.EXTENSA"));

				FileUtils.copyDirectory(source, target);

				File file = new File(target + "\\" + acfNombre);
				File Nuevo = new File(target + "\\" + nuevoFile);
				boolean correct = file.renameTo(Nuevo);

				if (correct)
					System.out.println("archivo cambio con exito");

				acfNombre = nuevoFile;
				FileUtils.deleteDirectory(source);

				accidentFilesService.registrarArchivosAccidente(idAccidente,
						acfRuta, acfDescripcion, acfNombre);
				listarArchivos();
				limpiar();

				mostrarMensaje(Util.loadMessageValue("EXITO.LOADFILE"), false);
				inputFile.setRendered(true);
				inputFile.reset();

			}

		} catch (Exception e) {
			// limpiar();
			// mostrarMensaje(e.getMessage(), false);
			FacesUtils.addErrorMessage(e.getMessage());
			// e.printStackTrace();
			inputFile.reset();
		}
	}

	public InputFile getInputFile() {
		return inputFile;
	}

	public void setInputFile(InputFile inputFile) {
		this.inputFile = inputFile;
	}

	public void remover() {

	}

	public PanelTab getPanelOtrosDatos() {
		return panelOtrosDatos;
	}

	public void setPanelOtrosDatos(PanelTab panelOtrosDatos) {
		this.panelOtrosDatos = panelOtrosDatos;
	}

	public void validarEstadoAccidente(Long idAccidente) throws GWorkException {

		Accidents accidents = dataAccidentsService
				.consultarAccidente(idAccidente);

		String estadoAccidente = accidents.getAccidentsStates().getAcsNombre();

		if (estadoAccidente.equalsIgnoreCase(ViewOptionUtil.ACCIDENTE_CERRADO))
			throw new GWorkException(Util
					.loadErrorMessageValue("ACCIDENTE.CERRADO"));

	}

	public DataAccidentsService getDataAccidentsService() {
		return dataAccidentsService;
	}

	public void setDataAccidentsService(
			DataAccidentsService dataAccidentsService) {
		this.dataAccidentsService = dataAccidentsService;
	}

	public Integer getOpcEliminarArchivo() {
		return opcEliminarArchivo;
	}

	public void setOpcEliminarArchivo(Integer opcEliminarArchivo) {
		this.opcEliminarArchivo = opcEliminarArchivo;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Boolean getActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(Boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Integer getOpcEliminarArchivo2() {
		return opcEliminarArchivo2;
	}

	public void setOpcEliminarArchivo2(Integer opcEliminarArchivo2) {
		this.opcEliminarArchivo2 = opcEliminarArchivo2;
	}

	public Boolean getActivarConfirmacion2() {
		return activarConfirmacion2;
	}

	public void setActivarConfirmacion2(Boolean activarConfirmacion2) {
		this.activarConfirmacion2 = activarConfirmacion2;
	}

	public Long getCodigoArchivo_elim() {
		return codigoArchivo_elim;
	}

	public void setCodigoArchivo_elim(Long codigoArchivo_elim) {
		this.codigoArchivo_elim = codigoArchivo_elim;
	}
}
