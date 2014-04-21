package ciat.siscar.mobile.ui;

import geniar.mobile.utils.MessageBoxUtil;
import geniar.mobile.utils.MessageDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ciat.siscar.mobile.SiscarData;
import ciat.siscar.mobile.constants.CargoA;
import ciat.siscar.mobile.constants.TipoAsignacion;
import ciat.siscar.mobile.constants.TipoVehiculo;
import ciat.siscar.mobile.constants.UsoDisponible;

/**
 * @author Jaime Chavarriaga
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ServicioShell extends AbstractServicioShell {

	/**
	 * @param display
	 * @param style
	 */
	public ServicioShell(Display display, int style) {
		super(display, style);
	}

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		show();
	}

	public static void show() {
		try {
			Display display = Display.getDefault();
			Shell shell = new ServicioShell(display, SWT.CLOSE | SWT.RESIZE);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// binding

	public void updateModel() {

		SiscarData.setFecha(usuarioPanel.getFecha());

		SiscarData.setCarnetSolicitante(solicitantePanel.getCarnet());
		SiscarData
				.setNombreSolicitante(solicitantePanel.getNombreSolicitante());
		SiscarData.setTipoVehiculo(solicitantePanel.getTipoSolicitante());
		SiscarData.setNumeroRecibo(solicitantePanel.getRecibo());
		
		if(SiscarData.getCargoA() == CargoA.TERCEROS && SiscarData.getUsoDisponible() == UsoDisponible.PRESUPUESTO){
			SiscarData.setUsoDisponible(UsoDisponible.PRESUPUESTO);	
		}else{
			SiscarData.setUsoDisponible(solicitantePanel.getUsoDisponible());
		}

		SiscarData.setPlaca(vehiculoPanel.getPlaca());
		if (vehiculoPanel.getCarnetAsignatario() != null
				&& vehiculoPanel.getCarnetAsignatario().trim().length() > 0){
			if (SiscarData.getCarnetAsignatario() != null && SiscarData.getCarnetAsignatario().trim().length() > 0 && SiscarData.getCargoA() == 2) {
				
			} else {
				SiscarData.setCarnetAsignatario(vehiculoPanel
						.getCarnetAsignatario());
			}
		}

		SiscarData.setNombreAsignatario(vehiculoPanel.getNombreAsignatario());
		SiscarData.setTipoAsignacion(vehiculoPanel.getTipoAsignacion());
		
		if (vehiculoPanel.getUltimoKm() != null
				&& vehiculoPanel.getUltimoKm().trim().length() > 0){
		
			SiscarData.setUltimoKilometraje(new Long(new Float(vehiculoPanel.getUltimoKm())
				.longValue()));
		}
		
		SiscarData.setKmActual(vehiculoPanel.getKmActual());

		if (SiscarData.getTipoCobro() == TipoVehiculo.OTROS) {
			SiscarData.setCentroCostoCombustible("");
		} else if (SiscarData.getCentroCostoCombustible().length() > 0) {

			SiscarData
					.setCentroCostoCombustible(cargoAPanel.getCmbCentroCosto()
							.getItem(
									cargoAPanel.getCmbCentroCosto()
											.getSelectionIndex()));

			if (SiscarData.getCentroCostoCombustible().equals("OTRO")) {
				SiscarData.setCentroCostoCombustible(cargoAPanel
						.getCentroCosto());
			}
		} else if (SiscarData.getTipoCobro() == TipoVehiculo.VEHICULO_CIAT
				&& SiscarData.getTipoAsignacion().equals(TipoAsignacion.CONVENIO)) {
			SiscarData.setCentroCostoCombustible(cargoAPanel.getCentroCosto());
		}

		SiscarData.setDisponibleGalones(servicioPanel.getDisponibleGalones());
		SiscarData.setCantidadGalones(servicioPanel.getGalones());
		SiscarData.setTipoCombustible(servicioPanel.getTipoCombustible());
		SiscarData.setDisponiblePrepago(servicioPanel.getDisponiblePrepago());
		SiscarData.setObservaciones(observacionesPanel.getObservaciones());
	}

	public void updateForm() {

		usuarioPanel.setFecha();

		solicitantePanel.setCarnet(SiscarData.getCarnetSolicitante());
		solicitantePanel
				.setNombreSolicitante(SiscarData.getNombreSolicitante());
		solicitantePanel.setTipoSolicitante(SiscarData.getTipoVehiculo());
		solicitantePanel.setRecibo(SiscarData.getNumeroRecibo());

		cargoAPanel.setCentroCostoCombust(SiscarData
				.getCentroCostoCombustible());

		vehiculoPanel.setPlaca(SiscarData.getPlaca());
		vehiculoPanel.setCarnetAsignatario(SiscarData.getCarnetAsignatario());
		vehiculoPanel.setNombreAsignatario(SiscarData.getNombreAsignatario());
		vehiculoPanel.setTipoAsignacion(SiscarData.getTipoAsignacion());
		vehiculoPanel.setUltimoKm(SiscarData.getUltimoKilometraje().toString());
		vehiculoPanel.setKmActual(SiscarData.getKmActual());

		servicioPanel.setDisponibleGalones(SiscarData.getDisponibleGalones());
		servicioPanel.setGalones(SiscarData.getCantidadGalones());
		servicioPanel.setTipoCombustible(SiscarData.getTipoCombustible());
		servicioPanel.setUsoDisponible(SiscarData.getUsoDisponible());
		servicioPanel.setDisponiblePrepago(SiscarData.getDisponiblePrepago());
		observacionesPanel.setObservaciones(SiscarData.getObservaciones());
	}

	public boolean cerrar() {
		Shell messageShell = null;
		try {
			int resp = MessageBoxUtil.showYesNoMessageBox(this, "SISCAR",
					"Desea guardar el servicio");

			if (resp == SWT.YES) {

				// Muestra ventana de "Guardando !!"
				messageShell = MessageDialog.show(this, "SISCAR", "Guardando");

				// ejecuta la operación de guardado !!
				updateModel();
				SiscarData.dumpModel();
				SiscarData.save();

				// cierra la ventana
				messageShell.close();

				// muestra mensaje de confirmación
				MessageBoxUtil.showMessageBox(this, "SISCAR",
						"Servicio Guardado !!");

				return true;

			} else {
				SiscarData.limpiarDatos();

				return true;
			}
		} catch (Exception e) {
			MessageBoxUtil.showMessageBox(this, "SISCAR", "Error : "
					+ e.getMessage());
			if (messageShell != null) {
				messageShell.close();
			}
			return false;
		}
	}

}
