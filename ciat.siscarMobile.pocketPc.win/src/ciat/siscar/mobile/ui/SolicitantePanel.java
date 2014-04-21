package ciat.siscar.mobile.ui;

import java.util.List;

import geniar.mobile.utils.MessageBoxUtil;
import geniar.mobile.utils.MessageDialog;
import geniar.mobile.utils.PreferencesUtils;

import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Text;

import ciat.siscar.mobile.SiscarData;
import ciat.siscar.mobile.SiscarService;
import ciat.siscar.mobile.constants.CargoA;
import ciat.siscar.mobile.constants.TipoAsignacion;
import ciat.siscar.mobile.constants.TipoVehiculo;
import ciat.siscar.mobile.constants.UsoDisponible;
import ciat.siscar.mobile.services.SiscarException;
import ciat.siscar.mobile.services.SiscarPort_Stub;
import ciat.siscar.mobile.services.VoEmployee;
import ciat.siscar.mobile.services.VoModel;

public class SolicitantePanel extends Composite {

	private Label lblCarnet;
	private Text txtCarnet;
	private Label lblNombreSolicitante;
	private Text txtNombreSolicitante;

	private Label lblTipoSolicitante;
	private Combo cmbTipoSolicitante;

	private Label lblUsoDisponible;
	private Combo cmbUsoDisponible;

	private Label lblRecibo;
	private Text txtRecibo;

	private Shell shell;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public SolicitantePanel(Shell parentShell, Composite parent, int style) {

		super(parent, style);

		setLayout(new GridLayout(2, false));
		shell = parentShell;

		lblTipoSolicitante = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblTipoSolicitante.setLayoutData(gridData);
		}
		lblTipoSolicitante.setText("Tipo");

		cmbTipoSolicitante = new Combo(this, SWT.NONE);

		SiscarPort_Stub service = SiscarService.getService();
		
	
		cmbTipoSolicitante.setItems(new java.lang.String[] { "-SELECCIONAR-", "VEH. CIAT", "TRACTOR POOL", "MOTO CIAT",
				"MEMO", "BANCO", "PARQUE CIENTÍFICO" });
		cmbTipoSolicitante.select(1);
		SiscarData.setTipoCobro(TipoVehiculo.VEHICULO_CIAT);
		cmbTipoSolicitante.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				SiscarData.setTipoCobro(TipoVehiculo.VEHICULO_CIAT);
				cmbUsoDisponible.setEnabled(true);	
				lblRecibo.setVisible(false);
				txtRecibo.setVisible(false);
				txtNombreSolicitante.setVisible(true);				
			}

			public void widgetSelected(SelectionEvent event) {
				Shell messageShell = null;
				try {
					txtCarnet.setText("");
					txtNombreSolicitante.setText("");
					
					if (cmbTipoSolicitante.getSelectionIndex() == TipoVehiculo.OTROS) {
						lblRecibo.setVisible(true);
						cmbUsoDisponible.setEnabled(false);		
						SiscarData.setTipoCobro(TipoVehiculo.OTROS);
						txtNombreSolicitante.setVisible(true);
						txtRecibo.setVisible(true);
						setUsoDisponible(0);						
						SiscarData.setCargoA(CargoA.TERCEROS);
						SiscarData.setTarifa("");
					} else if (cmbTipoSolicitante.getSelectionIndex() == TipoVehiculo.VEHICULO_CIAT) {
						SiscarData.setTipoCobro(TipoVehiculo.VEHICULO_CIAT);
						cmbUsoDisponible.setEnabled(true);	
						lblRecibo.setVisible(false);
						txtRecibo.setVisible(false);
						txtNombreSolicitante.setVisible(true);
					} else {
						SiscarData.setTipoCobro(TipoVehiculo.VEHICULO_CIAT);
						cmbUsoDisponible.setEnabled(true);	
						lblRecibo.setVisible(false);
						txtRecibo.setVisible(false);
						txtNombreSolicitante.setVisible(true);
					}

					if (cmbUsoDisponible.getSelectionIndex() == UsoDisponible.PREPAGO)
						setUsoDisponible(UsoDisponible.PREPAGO);

					else if (cmbUsoDisponible.getSelectionIndex() == UsoDisponible.PRESUPUESTO)
						setUsoDisponible(UsoDisponible.PRESUPUESTO);
					
					SiscarData.setTipoCobro(cmbTipoSolicitante.getSelectionIndex());
					SiscarData.setTipoTarifa(cmbTipoSolicitante.getSelectionIndex());

					messageShell = MessageDialog.show(shell, "SISCAR", "Validando");					
					setTipoSolicitante(cmbTipoSolicitante.getSelectionIndex());

				} catch (Exception e) {
					MessageBoxUtil.showMessageBox(shell, "SISCAR", "Error : " + e.getMessage());
				} finally {
					if (messageShell != null)
						messageShell.close();
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
			cmbTipoSolicitante.setLayoutData(gridData);
		}

		lblUsoDisponible = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblUsoDisponible.setLayoutData(gridData);
		}
		lblUsoDisponible.setText("Uso Disponible");
		

		cmbUsoDisponible = new Combo(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			cmbUsoDisponible.setLayoutData(gridData);
		}
		cmbUsoDisponible.setVisible(true);
		cmbUsoDisponible.setItems(new java.lang.String[] { "-SELECCIONAR-", "PREPAGO", "PRESUPUESTO" });
		cmbUsoDisponible.select(UsoDisponible.PREPAGO);
		SiscarData.setUsoDisponible(UsoDisponible.PREPAGO);
		cmbUsoDisponible.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent event) {
				Shell messageShell = null;
				try {
					
					if (cmbUsoDisponible.getSelectionIndex() == UsoDisponible.PREPAGO)
						setUsoDisponible(UsoDisponible.PREPAGO);
					else if (cmbUsoDisponible.getSelectionIndex() == UsoDisponible.PRESUPUESTO)
						setUsoDisponible(UsoDisponible.PRESUPUESTO);
					
				} catch (Exception e) {
					MessageBoxUtil.showMessageBox(shell, "SISCAR", "Error : " + e.getMessage());
				} finally {
					if (messageShell != null)
						messageShell.close();
				}
			}
		});
		
		lblCarnet = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblCarnet.setLayoutData(gridData);
		}
		lblCarnet.setText("Carn\u00E9");
		lblCarnet.setVisible(true);

		txtCarnet = new Text(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtCarnet.setLayoutData(gridData);
		}
		txtCarnet.setTextLimit(12);
		txtCarnet.setVisible(true);
		txtCarnet.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent arg0) {
				Shell messageShell = null;
				try {
					messageShell = MessageDialog.show(shell, "SISCAR", "Validando");

					boolean cadenaRespuesta = SiscarData.isNumericCarne(txtCarnet.getText());
					
					if (cadenaRespuesta == false) {
						throw new SiscarException("No se pueden ingresar datos de tipo caracter o caracteres especiales en el campo Carné");
					}
					setCarnet(txtCarnet.getText());

					VoEmployee resp = SiscarService.getService().consultEmpleoyeeName(txtCarnet.getText());
					if(resp != null){
						setNombreSolicitante(resp.getEmpNombre()+" "+resp.getEmpApellido());
						txtNombreSolicitante.setEnabled(false);
					}else{
						txtNombreSolicitante.setEnabled(true);
					}
					//txtNombreSolicitante.setText(resp.getEmpNombre()+" "+resp.getEmpApellido());
					arg0.doit = true;

				} catch (Exception e) {
					MessageBoxUtil.showMessageBox(shell, "SISCAR", "Error : " + e.getMessage());
					txtNombreSolicitante.setText("");
					txtCarnet.setText("");
					// habilitar el campo ??
					lblNombreSolicitante.setEnabled(true);
					txtNombreSolicitante.setEnabled(true);
				} finally {
					if (messageShell != null)
						messageShell.close();
				}
			}
		});

		lblNombreSolicitante = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblNombreSolicitante.setLayoutData(gridData);
		}
		lblNombreSolicitante.setText("Usuario");
		lblNombreSolicitante.setVisible(true);
		lblNombreSolicitante.setEnabled(true);

		txtNombreSolicitante = new Text(this, SWT.BORDER);
		//txtNombreSolicitante.setText("");
		txtNombreSolicitante.setVisible(true);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtNombreSolicitante.setLayoutData(gridData);
		}

		lblRecibo = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblRecibo.setLayoutData(gridData);
		}
		lblRecibo.setText("Numero Recibo");
		lblRecibo.setVisible(false);

		txtRecibo = new Text(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			txtRecibo.setLayoutData(gridData);
		}
		txtRecibo.setVisible(false);
		txtRecibo.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent arg0) {
				try {
					setRecibo(txtRecibo.getText());
					arg0.doit = true;
				} catch (Exception e) {
					MessageBoxUtil.showMessageBox(shell, "SISCAR", "Error : " + e.getMessage());
				}
			}
		});

	}

	public void activar(){
		
		if(SiscarData.getTipoCobro() == TipoVehiculo.PARQUECIENTIFICO){
			cmbUsoDisponible.select(UsoDisponible.PRESUPUESTO);
			cmbUsoDisponible.setEnabled(false);
		}
		if(SiscarData.getTipoAsignacion() != null){
			if (SiscarData.getTipoAsignacion().equals(TipoAsignacion.CONVENIO)) {			
				cmbUsoDisponible.select(UsoDisponible.PRESUPUESTO);
				cmbUsoDisponible.setEnabled(false);
			}else {
				cmbUsoDisponible.setEnabled(true);
			}
		}else {
			cmbUsoDisponible.setEnabled(true);
		}
		
	}
	
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	// --

	public void setCarnet(String carnet) {
		SiscarData.setCarnetSolicitante(carnet);
		txtCarnet.setText(carnet);
	}

	public String getCarnet() {
		return txtCarnet.getText();
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		SiscarData.setNombreSolicitante(nombreSolicitante);
		txtNombreSolicitante.setText(nombreSolicitante);
	}

	public String getNombreSolicitante() {
		return txtNombreSolicitante.getText();
	}

	public void setTipoSolicitante(int tipoSolicitante) {
		SiscarData.setTipoVehiculo(tipoSolicitante);
		cmbTipoSolicitante.select(tipoSolicitante);
	}

	public int getUsoDisponible() {
		return cmbUsoDisponible.getSelectionIndex();
	}

	public void setUsoDisponible(int usoDisponible) {
		SiscarData.setUsoDisponible(usoDisponible);
		cmbUsoDisponible.select(SiscarData.getUsoDisponible());
	}

	public int getTipoSolicitante() {		
		return cmbTipoSolicitante.getSelectionIndex();
	}

	public void setRecibo(String recibo) {
		SiscarData.setNumeroRecibo(recibo);
		txtRecibo.setText(recibo);
	}

	public String getRecibo() {
		return txtRecibo.getText();
	}
}
