package ciat.siscar.mobile.ui;

import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import geniar.mobile.utils.MessageBoxUtil;
import geniar.mobile.utils.MessageDialog;
import geniar.mobile.utils.PreferencesUtils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import ciat.siscar.mobile.SiscarData;
import ciat.siscar.mobile.SiscarService;
import ciat.siscar.mobile.constants.TipoAsignacion;
import ciat.siscar.mobile.constants.TipoVehiculo;
import ciat.siscar.mobile.constants.UsoDisponible;
import ciat.siscar.mobile.services.ValidacionVehiculo;
import ciat.siscar.mobile.services.VoCostCenters;
import ciat.siscar.mobile.services.VoCostCentersFuels;
import ciat.siscar.mobile.vo.VOCostCenters;

import com.swtdesigner.SWTResourceManager;

public class VehiculoPanel extends Composite {

	protected Label txtNombreAsignatario;
	protected Label lblNombreAsignatario;
	protected Label txtCarnetAsignatario;
	protected Label lblCarnetAsignatario;
	protected Text txtKmActual;
	protected Label lblKmActual;
	protected Label lblPlaca;
	protected Text txtPlaca;
	protected Label lblTipoAsignacion;
	protected Label txtTipoAsignacion;

	protected Label lblUltimoKm;
	protected Label txtUltimoKm;

	private Shell shell;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public VehiculoPanel(Shell parentShell, Composite parent, int style) {

		super(parent, style);
		setLayout(new GridLayout(2, false));

		shell = parentShell;

		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {

				if (SiscarData.getTipoCobro() != -1
					&& SiscarData.getTipoCobro() != 0)
					activarPlaca();

				if (SiscarData.getTipoCobro() == TipoVehiculo.OTROS)
					desActivarCampo();
				else
					activarCampo();
			}
		});

		lblPlaca = new Label(this, SWT.NONE);
		{
			GridData gd_lblPlaca = new GridData();
			gd_lblPlaca.horizontalAlignment = SWT.RIGHT;
			gd_lblPlaca.verticalAlignment = SWT.CENTER;
			gd_lblPlaca.grabExcessHorizontalSpace = false;
			gd_lblPlaca.grabExcessVerticalSpace = false;
			gd_lblPlaca.horizontalSpan = 1;
			gd_lblPlaca.verticalSpan = 1;
			lblPlaca.setLayoutData(gd_lblPlaca);
		}
		lblPlaca.setText("Placa");
		lblPlaca.setVisible(true);

		txtPlaca = new Text(this, SWT.BORDER);
		{
			GridData gd_txtPlaca = new GridData();
			gd_txtPlaca.horizontalAlignment = SWT.LEFT;
			gd_txtPlaca.verticalAlignment = SWT.CENTER;
			gd_txtPlaca.grabExcessHorizontalSpace = true;
			gd_txtPlaca.grabExcessVerticalSpace = false;
			gd_txtPlaca.horizontalSpan = 1;
			gd_txtPlaca.verticalSpan = 1;
			gd_txtPlaca.widthHint = 100;
			txtPlaca.setLayoutData(gd_txtPlaca);
		}
		txtPlaca.setTextLimit(8);
		txtPlaca.setVisible(true);
		txtPlaca.setEnabled(false);
		txtPlaca.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent arg0) {
				Shell messageShell = null;
				float disponible = 0;
				VoCostCenters voCostCenters = null;
				try {
					String placa = txtPlaca.getText();
					// muestra ventana
					messageShell =
						MessageDialog.show(shell, "SISCAR", "Validando");

					setPlaca(placa);

					if ((SiscarData.getTipoCobro() == TipoVehiculo.VEHICULO_CIAT || SiscarData.getTipoVehiculo() == TipoVehiculo.VEHICULO_CIAT) && placa.trim().length() > 0) {
						ValidacionVehiculo resp =
							SiscarService.getService().validarVehiculo(
								placa.trim().toUpperCase());
						if (resp != null) {
							SiscarData.limpiarDatosConsultaPlaca();
							setKmActual("");
							if (resp.getTipoAsignacion() != null) {
								setTipoAsignacion(resp.getTipoAsignacion());
								SiscarData.setTipoAsignacion(resp.getTipoAsignacion());
								
								if (SiscarData.getTipoAsignacion().equals(TipoAsignacion.CONVENIO)) {
									//SiscarData.setIdTipoAsignacion(TipoAsignacion.PESINNOMINA);
									SiscarData.setCarnetAsignatario(resp.getCarneAsignado());
									SiscarData.setNombreAsignatario(resp.getNombreAsignado());
									SiscarData.setUsoDisponible(UsoDisponible.PRESUPUESTO);
								}
								
							} else
								setTipoAsignacion("");

							if (resp.getCarneAsignado() != null)
								setCarnetAsignatario(resp.getCarneAsignado());
							else
								setCarnetAsignatario("");

							if (resp.getNombreAsignado() != null)
								setNombreAsignatario(resp.getNombreAsignado());
							else {
								setNombreAsignatario("");
								SiscarData.setCentroCostoCombustible("");
							}
							setUltimoKm(resp.getUltimoKilometraje());

							SiscarData.setCapacidadTanque(
								resp.getCapacidadTanque());
							SiscarData.setTipoCombustible(
								resp.getTipoCombustible());
							SiscarData.setValorTanqueada(
								resp.getValorTanqueada());
							SiscarData.setCapacidadTanque(
								resp.getCapacidadTanque());
						} else {
							setUltimoKm("");
							setTipoAsignacion("");
							setCarnetAsignatario("");
							setNombreAsignatario("");
							SiscarData.setKmActual("");
							SiscarData.setTipoCombustible(0);
							SiscarData.setCentroCostoCombustible("");
							SiscarData.setObservaciones("");
							SiscarData.setCentroCostoCombustiblePrepago(null);
							
							MessageBoxUtil.showMessageBox(
									shell,
									"SISCAR",
									"Error : Placa no existe");
							
						}
					} else {
						setUltimoKm("");
						setTipoAsignacion("");
						setCarnetAsignatario("");
						setNombreAsignatario("");
						setKmActual("");
						SiscarData.setKmActual("");
						SiscarData.setTipoCombustible(0);
						SiscarData.setCentroCostoCombustible("");
						SiscarData.setObservaciones("");
						SiscarData.setCentroCostoCombustiblePrepago(null);
					}

					VoCostCentersFuels costCentersFuels =
						SiscarService
							.getService()
							.consultCostCenterFuelByPlaca(
							placa);

					VoCostCentersFuels[] VOcostCentersFuels =
						SiscarService
							.getService()
							.consultarCostCenterFuelPorPlaca(
							placa);

					String var = "";
					try {

						if (SiscarData.getTipoTarifa() == TipoVehiculo.OTROS
							&& SiscarData.getTipoCombustible() != -1) {
							var =
								SiscarService
									.getService()
									.consultarValorTarifaCombustibleCALI(
									new Long(SiscarData.getTipoCombustible()));
							SiscarData.setTarifa(var);
						} else {
							if (SiscarData.getTipoCombustible() != -1) {
								var =
									SiscarService
										.getService()
										.consultarValorTarifaCombustibleCIAT(
										new Long(
											SiscarData.getTipoCombustible()));
								SiscarData.setTarifa(var);
							}
						}
					} catch (Exception e) {
					}

					if (costCentersFuels != null) {
						SiscarData.setCentroCostoCombustible(
							costCentersFuels.getCostCenterNumber());
						int i = 0;
						String[] arr =
							new String[VOcostCentersFuels.length + 1];
						for (i = 0; i < VOcostCentersFuels.length; i++) {
							arr[i] =
								VOcostCentersFuels[i].getCostCenterNumber();
						}
						arr[i] = "OTRO";
						SiscarData.setCentroCostoCombustiblePrepago(arr);

						voCostCenters =
							SiscarService.getService().consultarCentroCostoVO(
								SiscarData.getCentroCostoCombustible());
						if (voCostCenters != null
							&& voCostCenters.getCocValorPrepago() != null)
							SiscarData.setDisponiblePrepago(
								new PreferencesUtils().redondeoNumeros(
									voCostCenters
										.getCocValorPrepago()
										.toString(),
									3));
																		
						if (SiscarData.getTarifa() != null
							&& SiscarData.getTarifa().trim().length() != 0
							&& SiscarData.getDisponiblePrepago() != null
							&& SiscarData.getTarifa().trim().length() != 0
							&& SiscarData.getDisponiblePrepago().length() > 0) {
							disponible =
								new Float(SiscarData.getDisponiblePrepago())
									.floatValue()
									/ new Float(SiscarData.getTarifa())
										.floatValue();
							SiscarData.setDisponibleGalones(
								new PreferencesUtils().redondeoNumeros(
									String.valueOf(disponible),
									4));
						}
					} else {
						if (SiscarData.getTarifa() == null
							|| SiscarData.getTarifa() == ""
							|| SiscarData.getTarifa() == "$ ") {
							SiscarData.setTarifa("");
						}

						SiscarData.setDisponibleGalones("");
						SiscarData.setDisponiblePrepago("");
						SiscarData.setValorTotal("");
						SiscarData
							.setCentroCostoCombustiblePrepago(new String[] {
						});

					}

				} catch (Exception e) {
					e.printStackTrace();
					MessageBoxUtil.showMessageBox(
						shell,
						"SISCAR",
						"Error : " + e.getMessage());
				} finally {
					// cierra la ventana
					if (messageShell != null)
						messageShell.close();
				}
			}
		});

		lblKmActual = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblKmActual.setLayoutData(gridData);
		}
		lblKmActual.setVisible(true);
		lblKmActual.setText("Km Actual");

		txtKmActual = new Text(this, SWT.BORDER);
		txtKmActual.setVisible(true);
		txtKmActual.setTextLimit(10);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtKmActual.setLayoutData(gridData);
		}

		final Label lblAsignatario = new Label(this, SWT.NONE);
		lblAsignatario.setFont(SWTResourceManager.getFont("", 10, SWT.BOLD));
		lblAsignatario.setLayoutData(new GridData());
		lblAsignatario.setText("Asignatario");
		new Label(this, SWT.NONE);

		lblTipoAsignacion = new Label(this, SWT.NONE);
		{
			GridData gd_lblTipoAsignacion = new GridData();
			gd_lblTipoAsignacion.horizontalAlignment = SWT.RIGHT;
			gd_lblTipoAsignacion.verticalAlignment = SWT.CENTER;
			gd_lblTipoAsignacion.grabExcessHorizontalSpace = false;
			gd_lblTipoAsignacion.grabExcessVerticalSpace = false;
			gd_lblTipoAsignacion.horizontalSpan = 1;
			gd_lblTipoAsignacion.verticalSpan = 1;
			lblTipoAsignacion.setLayoutData(gd_lblTipoAsignacion);
		}
		lblTipoAsignacion.setText("Tipo");
		lblTipoAsignacion.setVisible(true);

		txtTipoAsignacion = new Label(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtTipoAsignacion.setLayoutData(gridData);
		}
		txtTipoAsignacion.setVisible(true);
		txtTipoAsignacion.setText("");
		txtTipoAsignacion.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent arg0) {
				setTipoAsignacion(txtTipoAsignacion.getText());
			}
		});

		lblCarnetAsignatario = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblCarnetAsignatario.setLayoutData(gridData);
		}
		lblCarnetAsignatario.setVisible(true);
		lblCarnetAsignatario.setText("Carnet");

		txtCarnetAsignatario = new Label(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtCarnetAsignatario.setLayoutData(gridData);
		}
		txtCarnetAsignatario.setVisible(true);
		txtCarnetAsignatario.setText("");

		lblNombreAsignatario = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblNombreAsignatario.setLayoutData(gridData);
		}
		lblNombreAsignatario.setVisible(true);
		lblNombreAsignatario.setText("Nombre");

		txtNombreAsignatario = new Label(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtNombreAsignatario.setLayoutData(gridData);
		}
		txtNombreAsignatario.setVisible(true);
		txtNombreAsignatario.setText("");

		lblUltimoKm = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblUltimoKm.setLayoutData(gridData);
		}
		lblUltimoKm.setVisible(true);
		lblUltimoKm.setText("Ultimo Km");

		txtUltimoKm = new Label(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtUltimoKm.setLayoutData(gridData);
		}
		txtUltimoKm.setVisible(true);
		txtUltimoKm.setText("");

	}

	public void activarCampo() {
		txtKmActual.setEditable(true);
	}

	public void desActivarCampo() {
		txtKmActual.setEditable(false);
	}

	public void activarPlaca() {
		txtPlaca.setEnabled(true);
	}

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	// -- atributos

	public void setNombreAsignatario(String nombreAsignatario) {
		txtNombreAsignatario.setText(nombreAsignatario);
	}

	public String getNombreAsignatario() {
		return txtNombreAsignatario.getText();
	}

	public void setCarnetAsignatario(String carnetAsignatario) {
		txtCarnetAsignatario.setText(carnetAsignatario);
	}

	public String getCarnetAsignatario() {
		return txtCarnetAsignatario.getText();
	}

	public void setKmActual(String kmActual) {
		txtKmActual.setText(kmActual);
	}

	public String getKmActual() {
		return txtKmActual.getText();
	}

	public void setPlaca(String placa) {
		txtPlaca.setText(placa);
	}

	public String getPlaca() {
		return txtPlaca.getText();
	}

	public void setTipoAsignacion(String tipoAsignacion) {
		txtTipoAsignacion.setText(tipoAsignacion);
	}

	public String getTipoAsignacion() {
		return txtTipoAsignacion.getText();
	}

	public void setUltimoKm(String ultimoKm) {
		if (ultimoKm != null && ultimoKm.trim().length() > 0) {
			SiscarData.setUltimoKilometraje(
				new Long(new Float(ultimoKm).longValue()));
			txtUltimoKm.setText(ultimoKm);
		} else {
			txtUltimoKm.setText("");
		}

	}

	public String getUltimoKm() {
		return txtUltimoKm.getText();
	}

	public void activar() {
		
	
		
		if (SiscarData.getTipoCobro() == TipoVehiculo.OTROS) {
			setUltimoKm("");
			setPlaca("");
			setTipoAsignacion("");
			setCarnetAsignatario("");
			setNombreAsignatario("");
			SiscarData.setTipoCombustible(0);
			SiscarData.setCentroCostoCombustible("");

		}
		
		if (SiscarData.getTipoCobro() == TipoVehiculo.MEMO) {
			setUltimoKm("");
			setPlaca("");
			setTipoAsignacion("");
			setCarnetAsignatario("");
			setNombreAsignatario("");
			SiscarData.setTipoCombustible(0);
			SiscarData.setCentroCostoCombustible("");
			txtPlaca.setEnabled(false);
			txtKmActual.setEnabled(false);
		}
		
		if (SiscarData.getTipoCobro() == TipoVehiculo.VEHICULO_CIAT) {
			txtPlaca.setEnabled(true);
		}
	}

}
