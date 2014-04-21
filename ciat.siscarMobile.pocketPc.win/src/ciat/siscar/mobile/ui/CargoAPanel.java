package ciat.siscar.mobile.ui;

import geniar.mobile.utils.MessageBoxUtil;
import geniar.mobile.utils.MessageDialog;
import geniar.mobile.utils.PreferencesUtils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import ciat.siscar.mobile.SiscarData;
import ciat.siscar.mobile.SiscarService;
import ciat.siscar.mobile.constants.CargoA;
import ciat.siscar.mobile.constants.TipoAsignacion;
import ciat.siscar.mobile.constants.TipoVehiculo;
import ciat.siscar.mobile.constants.UsoDisponible;
import ciat.siscar.mobile.services.VoCostCenters;
import ciat.siscar.mobile.services.VoEmployee;

public class CargoAPanel extends Composite {

	private Label lblCargoA;
	private Combo cmbCargoA;
	private Combo cmbCentroCosto;

	private Label lblCarnetCargoA;
	private Text txtCarnetCargoA;
	private Label lblNombreCargoA;
	private Label txtNombreCargoA;

	private Label lblCentroCosto;
	private Text txtCentroCosto;

	private Label lblCentroCostoCombust;
	// private Text txtCentroCostoCombust;

	private Shell shell;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public CargoAPanel(Shell parentShell, Composite parent, int style) {

		super(parent, style);

		setLayout(new GridLayout(2, false));
		shell = parentShell;

		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {

				
			}
		});

		lblCargoA = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblCargoA.setLayoutData(gridData);
		}

		lblCargoA.setText("Cargo A");

		cmbCargoA = new Combo(this, SWT.NONE);

		cmbCargoA.setItems(new java.lang.String[] { "-SELECCIONAR-",
				"CENTROS COSTOS", "CARNET", "TERCEROS" });
		cmbCargoA.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent event) {
				try {
					setCargoA(cmbCargoA.getSelectionIndex());
					if (cmbCargoA.getSelectionIndex() == CargoA.TERCEROS
							|| cmbCargoA.getSelectionIndex() == CargoA.CARNET) {
						selectTercerosEvent();
					} else if (cmbCargoA.getSelectionIndex() == CargoA.CENTRO_COSTOS) {
						lblCarnetCargoA.setVisible(false);
						txtCarnetCargoA.setVisible(false);
						lblNombreCargoA.setVisible(false);
						txtNombreCargoA.setVisible(false);
						lblCentroCosto.setVisible(true);
						txtCentroCosto.setVisible(true);
						txtCentroCosto.setText("");
						txtCarnetCargoA.setText("");
						txtNombreCargoA.setText("");
						if (SiscarData.getCentroCostoCombustiblePrepago() != null
								&& SiscarData.getCentroCostoCombustiblePrepago().length > 0) {						
							lblCentroCostoCombust.setVisible(true);
							cmbCentroCosto.setVisible(true);						
						}
					}
					
				} catch (Exception e) {
				}
			}
		});
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			cmbCargoA.setLayoutData(gridData);
		}

		lblCarnetCargoA = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblCarnetCargoA.setLayoutData(gridData);
		}
		lblCarnetCargoA.setText("Carn\u00E9");
		lblCarnetCargoA.setVisible(false);

		txtCarnetCargoA = new Text(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtCarnetCargoA.setLayoutData(gridData);
		}
		txtCarnetCargoA.setTextLimit(10);
		txtCarnetCargoA.setVisible(false);
		txtCarnetCargoA.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent arg0) {
				Shell messageShell = null;
				try {
					messageShell = MessageDialog.show(shell, "SISCAR",
							"Validando");

					setCarnetCargoA(txtCarnetCargoA.getText());

					if (cmbCargoA.getSelectionIndex() != -1) {
						VoEmployee employee = null;
						String resp = "";
						if (cmbCargoA.getSelectionIndex() == CargoA.TERCEROS) {
							resp = SiscarService.getService()
									.consultarAuxiliarCiat(
											txtCarnetCargoA.getText());
							txtNombreCargoA.setText(resp);
							SiscarData.setCarnetAsignatario(txtCarnetCargoA.getText());
						} else if (cmbCargoA.getSelectionIndex() == CargoA.CARNET) {
							employee = SiscarService.getService()
									.consultEmpleoyeeName(
											txtCarnetCargoA.getText());
							txtNombreCargoA.setText(employee.getEmpNombre()
									+ " " + employee.getEmpApellido());
							SiscarData.setCarnetAsignatario(txtCarnetCargoA.getText());
						}
						if(SiscarData.getTipoCobro() == TipoVehiculo.PARQUECIENTIFICO){
							resp = SiscarService.getService().consultarAuxiliarCiat(txtCarnetCargoA.getText());
							txtNombreCargoA.setText(resp);
						}
					}

					arg0.doit = true;

				} catch (Exception e) {
					MessageBoxUtil.showMessageBox(shell, "SISCAR", "Error : "
							+ e.getMessage());
				} finally {
					if (messageShell != null)
						messageShell.close();
				}
			}
		});

		lblNombreCargoA = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblNombreCargoA.setLayoutData(gridData);
		}
		lblNombreCargoA.setText("Usuario");
		lblNombreCargoA.setVisible(false);

		txtNombreCargoA = new Label(this, SWT.NONE);
		txtNombreCargoA.setText("");
		txtNombreCargoA.setVisible(false);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtNombreCargoA.setLayoutData(gridData);
		}

		lblCentroCostoCombust = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblCentroCostoCombust.setLayoutData(gridData);
		}
		lblCentroCostoCombust.setText("CC Vehículo");
		lblCentroCostoCombust.setVisible(false);

		/*
		 * txtCentroCostoCombust = new Text(this, SWT.BORDER); { GridData
		 * gridData = new GridData(); gridData.horizontalAlignment = SWT.LEFT;
		 * gridData.verticalAlignment = SWT.CENTER;
		 * gridData.grabExcessHorizontalSpace = true;
		 * gridData.grabExcessVerticalSpace = false; gridData.horizontalSpan =
		 * 1; gridData.verticalSpan = 1; gridData.widthHint = 100;
		 * txtCentroCostoCombust.setLayoutData(gridData); }
		 * txtCentroCostoCombust.setVisible(false);
		 * txtCentroCostoCombust.setTextLimit(10);
		 */

		/**
		 * 
		 */

		cmbCentroCosto = new Combo(this, SWT.NONE);

		// cmbCentroCosto.setItems(new java.lang.String[] { "OTRO"});
		cmbCentroCosto.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent event) {
				
				//Shell messageShell = null;
				VoCostCenters voCostCenters = null;
				float disponible = 0;
				
				try {
					if (cmbCentroCosto.getText().equals("OTRO")) {
						lblCentroCosto.setVisible(true);
						txtCentroCosto.setVisible(true);
					} else {
							if(cmbCentroCosto.getText() != "OTRO"){
						
								if(SiscarData.getUsoDisponible() == UsoDisponible.PRESUPUESTO){
									boolean resp = SiscarService.getService().validarCentroCosto(cmbCentroCosto.getText());
									if (resp) {										
										setCentroCosto(cmbCentroCosto.getText());
										SiscarData.setCentroCosto(cmbCentroCosto.getText());
									}else{											
											txtCentroCosto.setText("");
											setCentroCosto("");
											SiscarData.setCentroCosto("");
											SiscarData.setDisponibleGalones("");
											SiscarData.setDisponiblePrepago("");
											MessageBoxUtil.showMessageBox(shell, "SISCAR",
													" Centro de Costo Inactivo o Sin Presupesto ");
									}
								}else{
									
									voCostCenters = SiscarService.getService().consultarCentroCostoVO(cmbCentroCosto.getText());
									if (voCostCenters != null && voCostCenters.getCocValorPrepago() != null 
										&& voCostCenters.getCocValorPrepago().floatValue() > 0){
										SiscarData.setDisponiblePrepago(new PreferencesUtils().redondeoNumeros(voCostCenters.getCocValorPrepago().toString(), 3));
		
										if (SiscarData.getTarifa() != null
												&& SiscarData.getTarifa().trim().length() != 0
												&& SiscarData.getDisponiblePrepago() != null
												&& SiscarData.getTarifa().trim().length() != 0) {
											disponible = new Float(SiscarData.getDisponiblePrepago()).floatValue() / new Float(SiscarData.getTarifa()).floatValue();
											SiscarData.setDisponibleGalones(new PreferencesUtils().redondeoNumeros(String.valueOf(disponible), 4));
										}
										
										if (cmbCentroCosto.getText().equals("OTRO")) {
											SiscarData.setCentroCostoCombustible(cmbCentroCosto.getText());
										}
											
										setCentroCosto(txtCentroCosto.getText());
										SiscarData.setCentroCosto(txtCentroCosto.getText());
									} else {								
										if(SiscarData.getUsoDisponible() == UsoDisponible.PREPAGO){
											SiscarData.setDisponiblePrepago("");
											SiscarData.setDisponibleGalones("");
											MessageBoxUtil.showMessageBox(shell, "SISCAR", " El centro de costos no tiene prepago disponible ");
											
										}else if(SiscarData.getUsoDisponible() == UsoDisponible.PRESUPUESTO){
											
											boolean resp = SiscarService.getService().validarCentroCosto(txtCentroCosto.getText());
											if (resp) {										
												setCentroCosto(txtCentroCosto.getText());
												SiscarData.setCentroCosto(txtCentroCosto.getText());
											}else{											
													txtCentroCosto.setText("");
													setCentroCosto("");
													SiscarData.setCentroCosto("");
													SiscarData.setDisponibleGalones("");
													SiscarData.setDisponiblePrepago("");
													MessageBoxUtil.showMessageBox(shell, "SISCAR",
															" El centro de costos ingresado es invalido ");
											}																				
										}
									}
								}
							lblCentroCosto.setVisible(false);
							txtCentroCosto.setVisible(false);
							txtCentroCosto.setText("");	
						}
					}
				} catch (Exception e) {
				}
			}
		});
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			cmbCentroCosto.setLayoutData(gridData);
		}

		/**
		 * 
		 */

		lblCentroCosto = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblCentroCosto.setLayoutData(gridData);
		}
		lblCentroCosto.setText("Centro Costo");
		lblCentroCosto.setVisible(false);

		txtCentroCosto = new Text(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtCentroCosto.setLayoutData(gridData);
		}
		txtCentroCosto.setVisible(false);
		txtCentroCosto.setTextLimit(10);
		txtCentroCosto.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent arg0) {
				Shell messageShell = null;
				VoCostCenters voCostCenters = null;
				try {
					float disponible = 0;
					messageShell = MessageDialog.show(shell, "SISCAR",
							"Validando");														
					
					SiscarData.setDisponiblePrepago("");
					SiscarData.setDisponibleGalones("");
					boolean resp = validacionCENCOS();
					
					if (resp) {

						voCostCenters = SiscarService.getService()
								.consultarCentroCostoVO(
										txtCentroCosto.getText());
						if (voCostCenters != null
								&& voCostCenters.getCocValorPrepago() != null && voCostCenters.getCocValorPrepago().floatValue() > 0){
							SiscarData
									.setDisponiblePrepago(new PreferencesUtils()
											.redondeoNumeros(voCostCenters
													.getCocValorPrepago()
													.toString(), 3));

							if (SiscarData.getTarifa() != null
									&& SiscarData.getTarifa().trim().length() != 0
									&& SiscarData.getDisponiblePrepago() != null
									&& SiscarData.getTarifa().trim().length() != 0) {
								disponible = new Float(SiscarData
										.getDisponiblePrepago()).floatValue()
										/ new Float(SiscarData.getTarifa())
												.floatValue();
								SiscarData
										.setDisponibleGalones(new PreferencesUtils()
												.redondeoNumeros(String
														.valueOf(disponible), 4));
							}
							
							if (cmbCentroCosto.getText().equals("OTRO")) {
								SiscarData.setCentroCostoCombustible(cmbCentroCosto.getText());
							}
								
							setCentroCosto(txtCentroCosto.getText());
							SiscarData.setCentroCosto(txtCentroCosto.getText());
						} else {								
							if(SiscarData.getUsoDisponible() == UsoDisponible.PREPAGO){
								
								MessageBoxUtil.showMessageBox(shell, "SISCAR", " El centro de costos no tiene prepago disponible ");
								
							}else if(SiscarData.getUsoDisponible() == UsoDisponible.PRESUPUESTO){
								setCentroCosto(txtCentroCosto.getText());
								SiscarData.setCentroCosto(txtCentroCosto.getText());
							}
							
							if (cmbCentroCosto.getText().equals("OTRO")) {
								SiscarData.setCentroCostoCombustible(cmbCentroCosto.getText());
								
								setCentroCosto(txtCentroCosto.getText());
								SiscarData.setCentroCosto(txtCentroCosto.getText());
							}								
						}
					} else {
						txtCentroCosto.setText("");
						MessageBoxUtil.showMessageBox(shell, "SISCAR",
								" Centro de Costo Inactivo o Sin Presupesto ");
					}

					arg0.doit = true;

				} catch (Exception e) {
					MessageBoxUtil.showMessageBox(shell, "SISCAR", "Error : "
							+ e.getMessage());
				} finally {
					if (messageShell != null)
						messageShell.close();
				}
			}
		});

	}

	public boolean validacionCENCOS() {
		boolean respuesta = false;
		try {
			//Tipo cobro y Uso Disponible han relacion al mismo dato, se mantiene las varibles porque no se sabe donde puede afectar su uso
			if(SiscarData.getUsoDisponible() == UsoDisponible.PRESUPUESTO){
				respuesta = SiscarService.getService().validarCentroCosto(txtCentroCosto.getText());
			}else{
				respuesta = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return respuesta;
	}
	
	public void activar() {

		if (SiscarData.getTipoVehiculo() == TipoVehiculo.VEHICULO_CIAT) {
			lblCentroCosto.setVisible(false);
			txtCentroCosto.setVisible(false);
		}else if (SiscarData.getTipoVehiculo() == TipoVehiculo.OTROS) {
			setCargoA(SiscarData.getCargoA());
			selectTercerosEvent();
		}else if(SiscarData.getTipoVehiculo() == TipoVehiculo.PARQUECIENTIFICO) {
			setCargoA(SiscarData.getCargoA());
			selectTercerosEvent();
		}

		if (SiscarData.getTipoCobro() == 0){
			cmbCargoA.select(CargoA.CENTRO_COSTOS);
			eventLimpiar();
		}
		
		if (SiscarData.getCentroCostoCombustible() != null
				&& SiscarData.getCentroCostoCombustible().trim()
						.length() != 0)
			setCentroCostoCombust(SiscarData
					.getCentroCostoCombustible());
		else
			setCentroCostoCombust("");

		if (SiscarData.getTipoCobro() == TipoVehiculo.OTROS)
			inactivarCombo();
		else if(SiscarData.getTipoCobro() == TipoVehiculo.PARQUECIENTIFICO){
			inactivarCombo();
		}else
			activarCombo();
		
		if (SiscarData.getCentroCostoCombustiblePrepago() != null
				&& SiscarData.getCentroCostoCombustiblePrepago().length > 0) {
			if (cmbCentroCosto.getText().equals("OTRO") && SiscarData.getCentroCostoCombustible().equals("OTRO")) {
				
			} else {
				cmbCentroCosto.setItems(SiscarData.getCentroCostoCombustiblePrepago());
				cmbCentroCosto.select(0);
				txtCentroCosto.setText("");
				lblCentroCosto.setVisible(false);
				txtCentroCosto.setVisible(false);
			}			
		} else {
			SiscarData.setCentroCostoCombustiblePrepago(new String[] {});
			cmbCentroCosto.clearSelection();
			cmbCentroCosto.removeAll();
		}

		if (SiscarData.getTipoAsignacion() != null) {
			if (SiscarData.getTipoAsignacion().equals(TipoAsignacion.CONVENIO)) {
				cmbCargoA.select(CargoA.TERCEROS);
				lblCentroCosto.setVisible(false);
				txtCentroCosto.setVisible(false);
				cmbCentroCosto.setVisible(false);
				lblCarnetCargoA.setVisible(true);
				txtCarnetCargoA.setVisible(true);
				lblNombreCargoA.setVisible(true);
				txtNombreCargoA.setVisible(true);
				txtNombreCargoA.setText(SiscarData.getNombreAsignatario());
				txtCarnetCargoA.setText(SiscarData.getCarnetAsignatario());	
				SiscarData.setCargoA(CargoA.TERCEROS);
			}else{
				if(cmbCargoA.getText() != "CENTROS COSTOS"){
					if(SiscarData.getTipoCobro() == TipoVehiculo.PARQUECIENTIFICO){					
						cmbCargoA.select(CargoA.TERCEROS);					
					}else if(cmbCargoA.getSelectionIndex() == -1){					
						cmbCargoA.select(CargoA.CENTRO_COSTOS);
						SiscarData.setCargoA(CargoA.CENTRO_COSTOS);
						lblCentroCosto.setVisible(true);
						txtCentroCosto.setVisible(true);
					}else if(cmbCargoA.getSelectionIndex() == CargoA.CARNET){
						SiscarData.setCargoA(CargoA.CARNET);
						lblCentroCosto.setVisible(false);
						txtCentroCosto.setVisible(false);
					}else if (cmbCargoA.getSelectionIndex() == CargoA.TERCEROS) {
						SiscarData.setCargoA(CargoA.TERCEROS);
						lblCentroCosto.setVisible(false);
						txtCentroCosto.setVisible(false);
					}
				}else{
					cmbCargoA.select(CargoA.CENTRO_COSTOS);
				}							
			}
		}
				
		if (SiscarData.getTipoCobro() == TipoVehiculo.OTROS) {
			lblCentroCosto.setVisible(false);
			txtCentroCosto.setVisible(false);
			cmbCentroCosto.setVisible(false);
		} else if(SiscarData.getTipoCobro() == TipoVehiculo.MEMO){
			if(cmbCargoA.getSelectionIndex() == -1){
				cmbCargoA.select(CargoA.CENTRO_COSTOS);
			}
			if (SiscarData.getCargoA() == CargoA.CARNET || SiscarData.getCargoA() == CargoA.TERCEROS) {
				lblCentroCosto.setVisible(false);
				txtCentroCosto.setVisible(false);
			}else{
				lblCentroCosto.setVisible(true);
				txtCentroCosto.setVisible(true);
			}
			
		}else if(SiscarData.getTipoCobro() == TipoVehiculo.MOTO_CIAT){
			if(cmbCargoA.getSelectionIndex() == -1){
				cmbCargoA.select(CargoA.CENTRO_COSTOS);
			}
			lblCentroCosto.setVisible(true);
			txtCentroCosto.setVisible(true);
		}else if(SiscarData.getTipoCobro() == TipoVehiculo.TRACTOR_POOL){
			if(cmbCargoA.getSelectionIndex() == -1){
				cmbCargoA.select(CargoA.CENTRO_COSTOS);
			}			
			lblCentroCosto.setVisible(true);
			txtCentroCosto.setVisible(true);
		}else if(SiscarData.getTipoCobro() == TipoVehiculo.PARQUECIENTIFICO){
			lblCentroCosto.setVisible(false);
			txtCentroCosto.setVisible(false);
			cmbCentroCosto.setVisible(false);
			cmbCargoA.setText("TERCEROS");
			cmbCargoA.select(CargoA.TERCEROS);
			SiscarData.setCargoA(CargoA.TERCEROS);
		}else{
			if (getCmbCentroCosto().getItemCount() > 0) {
				if(cmbCargoA.getSelectionIndex() == CargoA.CARNET){
					SiscarData.setCargoA(CargoA.CARNET);
					lblCentroCosto.setVisible(false);
					txtCentroCosto.setVisible(false);
					lblCentroCostoCombust.setVisible(false);
					cmbCentroCosto.setVisible(false);
				}else if (cmbCargoA.getSelectionIndex() == CargoA.TERCEROS) {
					SiscarData.setCargoA(CargoA.TERCEROS);
					lblCentroCostoCombust.setVisible(false);
					lblCentroCosto.setVisible(false);
					txtCentroCosto.setVisible(false);
					cmbCentroCosto.setVisible(false);
				}else{
					SiscarData.setCentroCostoCombustible(getCmbCentroCosto()
							.getItem(getCmbCentroCosto().getSelectionIndex()));
					lblCentroCostoCombust.setVisible(true);
					cmbCentroCosto.setEnabled(true);
				}
			}

			if (SiscarData.getCentroCostoCombustible() != null
					&& SiscarData.getCentroCostoCombustible().equals("OTRO")) {
				lblCentroCosto.setVisible(true);
				txtCentroCosto.setVisible(true);
			} else if (SiscarData.getCentroCostoCombustiblePrepago() == null
					|| SiscarData.getCentroCostoCombustiblePrepago().length == 0) {
				if(SiscarData.getTipoCobro() == TipoVehiculo.VEHICULO_CIAT){
					if (SiscarData.getTipoAsignacion() != null) {
						if (SiscarData.getTipoAsignacion().equals(TipoAsignacion.CONVENIO)) {
							//cmbCargoA.setText("TERCEROS");
							lblCentroCosto.setVisible(false);
							cmbCargoA.select(CargoA.TERCEROS);//TERCEROS
							txtCentroCosto.setVisible(false);
							cmbCentroCosto.setVisible(false);
						}
					}else{
						/**
						 * Si se está cargando por primera vez la pestaña
						 */
						if(cmbCargoA.getSelectionIndex() == -1){
							cmbCargoA.select(CargoA.CENTRO_COSTOS);
							lblCentroCostoCombust.setVisible(false);						
							cmbCentroCosto.setEnabled(false);
							if (SiscarData.getTipoAsignacion() != null) {
								lblCentroCosto.setVisible(false);
								txtCentroCosto.setVisible(false);
							}else{
								lblCentroCosto.setVisible(true);
								txtCentroCosto.setVisible(true);
							}
						}else{
							lblCentroCostoCombust.setVisible(false);						
							cmbCentroCosto.setEnabled(false);
							lblCentroCosto.setVisible(true);
							txtCentroCosto.setVisible(true);
						}
					}
					//OI0472
					if(cmbCargoA.getText() != "CENTROS COSTOS"){
						if(cmbCargoA.getText().equals("CARNET")){
							txtCentroCosto.setText("");
							lblCentroCostoCombust.setVisible(false);
							lblCentroCosto.setVisible(false);
							cmbCentroCosto.setEnabled(false);
							txtCentroCosto.setVisible(false);
						}
						
						if(SiscarData.getUsoDisponible() == UsoDisponible.PRESUPUESTO && CargoA.CENTRO_COSTOS == cmbCargoA.getSelectionIndex()){
							lblCentroCosto.setVisible(true);
							txtCentroCosto.setVisible(true);
						}
					}else{
						cmbCargoA.select(CargoA.CENTRO_COSTOS);						
					}				
				}
				else{
					lblCentroCostoCombust.setVisible(false);
					cmbCentroCosto.setEnabled(false);
					if (cmbCargoA.getSelectionIndex() != CargoA.TERCEROS) {
						lblCentroCosto.setVisible(true);
						txtCentroCosto.setVisible(true);
					}					
				}
			}
		}
	}

	public void selectTercerosEvent() {
		lblCarnetCargoA.setVisible(true);
		txtCarnetCargoA.setVisible(true);
		lblNombreCargoA.setVisible(true);
		txtNombreCargoA.setVisible(true);
		txtCentroCosto.setText("");
		lblCentroCosto.setVisible(false);
		txtCentroCosto.setVisible(false);
		lblCentroCostoCombust.setVisible(false);		
		cmbCentroCosto.setVisible(false);
	}

	public void eventLimpiar() {
		lblCarnetCargoA.setVisible(false);
		txtCarnetCargoA.setVisible(false);
		txtCarnetCargoA.setText("");

		lblNombreCargoA.setVisible(false);
		txtNombreCargoA.setVisible(false);
		txtNombreCargoA.setText("");

		lblCentroCosto.setVisible(false);
		txtCentroCosto.setVisible(false);
		txtCentroCosto.setText("");

		lblCentroCostoCombust.setVisible(false);
		// txtCentroCostoCombust.setVisible(false);
		// txtCentroCostoCombust.setText("");
		cmbCentroCosto.removeAll();
		cmbCentroCosto.setVisible(false);

	}

	public void inactivarCombo() {
		cmbCargoA.setEnabled(false);
	}

	public void activarCombo() {
		cmbCargoA.setEnabled(true);
	}

	public void inactivarComboPrepago() {
		cmbCargoA.setEnabled(false);
	}

	public void activarComboPrepago() {
		cmbCargoA.setEnabled(true);
	}

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	// --

	public void setCarnetCargoA(String carnet) {
		SiscarData.setCarnetCargoA(carnet);
		txtCarnetCargoA.setText(carnet);
	}

	public String getCarnet() {
		return txtCarnetCargoA.getText();
	}

	public void setNombreCargoA(String nombreSolicitante) {
		SiscarData.setNombreCargoA(nombreSolicitante);
		txtNombreCargoA.setText(nombreSolicitante);
	}

	public String getNombreSolicitante() {
		return txtNombreCargoA.getText();
	}

	public void setCentroCosto(String centroCosto) {
		SiscarData.setCentroCosto(centroCosto);
		txtCentroCosto.setText(centroCosto);
	}

	/*
	 * public String getCentroCostoCombust() { return
	 * txtCentroCostoCombust.getText(); }
	 */

	public void setCentroCostoCombust(String centroCostoCombust) {

		if (centroCostoCombust != null
				&& centroCostoCombust.trim().length() != 0) {

			SiscarData.setCentroCostoCombustible(centroCostoCombust);
			cmbCentroCosto.setVisible(true);
			// cmbCentroCosto.setEditable(true);

			// txtCentroCostoCombust.setVisible(true);
			// txtCentroCostoCombust.setEditable(false);
			lblCentroCostoCombust.setVisible(true);
			// txtCentroCostoCombust.setText(centroCostoCombust);

		} else {
			SiscarData.setCentroCostoCombustible("");
			cmbCentroCosto.setVisible(false);
			// txtCentroCostoCombust.setVisible(false);
			// txtCentroCostoCombust.setEditable(false);
			// txtCentroCostoCombust.setText(SiscarData.getCentroCostoCombustible());
		}
	}

	public String getCentroCosto() {
		return txtCentroCosto.getText();
	}

	public void setCargoA(int cargoA) {
		SiscarData.setCargoA(cargoA);
		cmbCargoA.select(cargoA);
	}

	public int getCargoA() {
		return cmbCargoA.getSelectionIndex();
	}

	public Combo getCmbCentroCosto() {
		return cmbCentroCosto;
	}

	public void setCmbCentroCosto(Combo cmbCentroCosto) {
		this.cmbCentroCosto = cmbCentroCosto;
	}

}
