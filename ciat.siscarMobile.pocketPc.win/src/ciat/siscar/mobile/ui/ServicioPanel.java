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
import org.eclipse.swt.widgets.Text;

import ciat.siscar.mobile.SiscarData;
import ciat.siscar.mobile.SiscarService;
import ciat.siscar.mobile.constants.TipoCombustible;
import ciat.siscar.mobile.constants.TipoVehiculo;
import ciat.siscar.mobile.constants.UsoDisponible;
import ciat.siscar.mobile.services.SiscarException;
import ciat.siscar.mobile.services.VoCostCenters;
import ciat.siscar.mobile.vo.VOCostCenters;

public class ServicioPanel extends Composite {

	private Label lblGalones;
	private Text txtGalones;
	private Text txtTotal;

	private Label lblTarifa;
	private Label txtTarifa;

	private Label lblTipoCombustible;
	private Label lblDisponiblePrepago;
	private Label txtDisponiblePrepago;
	private Label lblDisponibleGalones;
	private Label txtDisponibleGalones;
	private Label lblValorTotal;

	private Combo cmbUsoDisponible;
	private Combo cmbTipoCombustible;
	private Shell shell;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public ServicioPanel(Composite parent, int style) {

		super(parent, style);
		setLayout(new GridLayout(2, false));
		shell = parent.getParent().getShell();
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {

			}
		});

		lblTipoCombustible = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblTipoCombustible.setLayoutData(gridData);
		}
		lblTipoCombustible.setVisible(true);
		lblTipoCombustible.setText("Tipo Combustible");

		cmbTipoCombustible = new Combo(this, SWT.BORDER);
		cmbTipoCombustible.setItems(new java.lang.String[] { "-SELECCIONAR-",
				"GASOLINA", "DIESEL" });
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			cmbTipoCombustible.setLayoutData(gridData);
		}
		cmbTipoCombustible.setVisible(true);
		cmbTipoCombustible.select(0);

		cmbTipoCombustible.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent event) {
				Shell messageShell = null;
				try {
					float disponible = 0;
					float total = 0;
					setValorTotal("");
					if (cmbTipoCombustible.getSelectionIndex() != 0
							&& SiscarData.getTipoVehiculo() == TipoVehiculo.OTROS) {
						String var = SiscarService.getService()
								.consultarValorTarifaCombustibleCALI(
										new Long(cmbTipoCombustible
												.getSelectionIndex()));
						SiscarData.setTarifa(var);
						setTarifa(SiscarData.getTarifa());

					}
					if (cmbTipoCombustible.getSelectionIndex() != 0
							&& SiscarData.getTipoVehiculo() == TipoVehiculo.PARQUECIENTIFICO) {
						String var = SiscarService.getService()
								.consultarValorTarifaCombustibleCALI(
										new Long(cmbTipoCombustible
												.getSelectionIndex()));
						SiscarData.setTarifa(var);
						setTarifa(SiscarData.getTarifa());
					} else if (cmbTipoCombustible.getSelectionIndex() != 0
							&& SiscarData.getTipoVehiculo() != 0
							&& SiscarData.getTipoVehiculo() != TipoVehiculo.OTROS) {
						String var = SiscarService.getService()
								.consultarValorTarifaCombustibleCIAT(
										new Long(cmbTipoCombustible
												.getSelectionIndex()));
						SiscarData.setTarifa(var);
						setTarifa(SiscarData.getTarifa());

						if (SiscarData.getCentroCosto() != null) {

							if (SiscarData.getTarifa() != null
									&& SiscarData.getTarifa().trim().length() != 0
									&& SiscarData.getCantidadGalones() != null
									&& SiscarData.getCantidadGalones().trim()
											.length() != 0) {
								total = (new Float(SiscarData.getTarifa()))
										.floatValue()
										* (new Float(SiscarData
												.getCantidadGalones()))
												.floatValue();
								setValorTotal(new PreferencesUtils()
										.redondeoNumeros(String.valueOf(total),
												3));
							}

							if (SiscarData.getTarifa() != null
									&& SiscarData.getTarifa().trim().length() != 0
									&& SiscarData.getDisponiblePrepago() != null
									&& SiscarData.getDisponiblePrepago().trim()
											.length() != 0) {
								disponible = new Float(SiscarData
										.getDisponiblePrepago()).floatValue()
										/ new Float(SiscarData.getTarifa())
												.floatValue();
								SiscarData
										.setDisponibleGalones(new PreferencesUtils()
												.redondeoNumeros(String
														.valueOf(disponible), 4));
								setDisponibleGalones(SiscarData
										.getDisponibleGalones());
							}
						}

					} else if (SiscarData.getTipoVehiculo() == 0)
						SiscarData.setTarifa("");
					else if (cmbTipoCombustible.getSelectionIndex() == 0)
						SiscarData.setTarifa("");

					if (cmbTipoCombustible.getSelectionIndex() == 1)
						SiscarData.setIdSurtidor(1);

					if (cmbTipoCombustible.getSelectionIndex() == 2)
						SiscarData.setIdSurtidor(3);
					
					SiscarData.setTipoCombustible(cmbTipoCombustible.getSelectionIndex());

				} catch (Exception e) {
					MessageBoxUtil.showMessageBox(shell, "SISCAR", "Error : "
							+ e.getMessage());
				} finally {
					if (messageShell != null)
						messageShell.close();
				}
			}
		});

		lblTarifa = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblTarifa.setLayoutData(gridData);
		}

		lblTarifa.setText("Tarifa");

		txtTarifa = new Label(this, SWT.NONE);

		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtTarifa.setLayoutData(gridData);
		}

		lblGalones = new Label(this, SWT.NONE);
		{
			GridData gd_lblGalones = new GridData();
			gd_lblGalones.horizontalAlignment = SWT.RIGHT;
			gd_lblGalones.verticalAlignment = SWT.CENTER;
			gd_lblGalones.grabExcessHorizontalSpace = false;
			gd_lblGalones.grabExcessVerticalSpace = false;
			gd_lblGalones.horizontalSpan = 1;
			gd_lblGalones.verticalSpan = 1;
			lblGalones.setLayoutData(gd_lblGalones);
		}
		lblGalones.setText("Galones");
		lblGalones.setVisible(true);

		txtGalones = new Text(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtGalones.setLayoutData(gridData);
		}
		txtGalones.setVisible(true);
		txtGalones.setTextLimit(10);
		txtGalones.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent arg0) {
				Shell messageShell = null;
				float total = 0;
				setGalones(txtGalones.getText());
				try{
					
					boolean cadenaRespuesta = SiscarData.isNumeric(txtGalones.getText());
					
					if (cadenaRespuesta == false) {
						throw new SiscarException("No se pueden ingresar datos de tipo caracter o caracteres especiales en el campo Galones");
					}
					
					arg0.doit = true;
					if (SiscarData.getTarifa() != null
							&& SiscarData.getTarifa().trim().length() != 0
							&& SiscarData.getCantidadGalones() != null
							&& SiscarData.getCantidadGalones().trim().length() != 0) {
						if (new Float(SiscarData.getCantidadGalones()).floatValue() < 200L) {
							total = (new Float(SiscarData.getTarifa())).floatValue()
							* (new Float(SiscarData.getCantidadGalones()))
									.floatValue();
							setValorTotal(new PreferencesUtils().redondeoNumeros(String
									.valueOf(total), 3));
						} else {
							setValorTotal("");
							setGalones("");
							MessageBoxUtil.showMessageBox(shell, "SISCAR",
							" La cantidad de galones ingresada es mayor a 200 galones");							
						}
						
					}
				} catch (Exception e) {
					MessageBoxUtil.showMessageBox(shell, "SISCAR", "Error : "
							+ e.getMessage());
				} finally {
					if (messageShell != null)
						messageShell.close();
				}
			}
		});

		lblValorTotal = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblValorTotal.setLayoutData(gridData);
		}

		lblValorTotal.setVisible(true);
		lblValorTotal.setText("Valor Compra");

		txtTotal = new Text(this, SWT.BORDER);

		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtTotal.setLayoutData(gridData);
			txtTotal.setEnabled(false);
		}

		lblDisponiblePrepago = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblDisponiblePrepago.setLayoutData(gridData);
		}
		lblDisponiblePrepago.setVisible(true);
		lblDisponiblePrepago.setText("Disponible Prepago $");

		txtDisponiblePrepago = new Label(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtDisponiblePrepago.setLayoutData(gridData);
		}
		txtDisponiblePrepago.setVisible(true);
		txtDisponiblePrepago.setText("");

		lblDisponibleGalones = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblDisponibleGalones.setLayoutData(gridData);
		}
		lblDisponibleGalones.setVisible(true);
		lblDisponibleGalones.setText("Disponible Galones");

		txtDisponibleGalones = new Label(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 100;
			txtDisponibleGalones.setLayoutData(gridData);
		}
		txtDisponibleGalones.setVisible(true);
		txtDisponibleGalones.setText("");

	}

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void activar() {
		if (txtGalones.getText().trim().length() == 0) {
			txtTotal.setText("");
		}
		
		/*
		 * las siguientes validaciones estaban incluidad para la validación que está depsués del comentario, se cree que no se necesita
		 * 
		 *	&& SiscarData.getUltimoKilometraje() != null
		 *  && SiscarData.getUltimoKilometraje().longValue() > 0 
		 */
		
		if (SiscarData.getTipoCombustible() != -1)
			setTipoCombustible(SiscarData.getTipoCombustible());
		else
			setTipoCombustible(-1);

		if (SiscarData.getDisponibleGalones() != null
				&& SiscarData.getDisponibleGalones().trim() != "")
			setDisponibleGalones(SiscarData.getDisponibleGalones());
		else
			setDisponibleGalones("");

		if (SiscarData.getTarifa() != null
				&& SiscarData.getTarifa().trim() != "")
			setTarifa(SiscarData.getTarifa());
		if (SiscarData.getValorTotal() != null
				&& SiscarData.getValorTotal().trim() != "")
			setValorTotal(SiscarData.getValorTotal());
		else
			setGalones("");
		if (SiscarData.getDisponiblePrepago() != null
				&& SiscarData.getDisponiblePrepago().trim() != "")
			setDisponiblePrepago(SiscarData.getDisponiblePrepago());
		else
			setDisponiblePrepago("");
	}

	public void setGalones(String galones) {
		SiscarData.setCantidadGalones(galones);
		txtGalones.setText(SiscarData.getCantidadGalones());
	}

	public String getGalones() {
		return txtGalones.getText();
	}

	public void setTipoCombustible(int tipoCombustible) {
		SiscarData.setTipoCombustible(tipoCombustible);
		cmbTipoCombustible.select(SiscarData.getTipoCombustible());
		cmbTipoCombustible.setEnabled(true);
	}

	public int getTipoCombustible() {
		return cmbTipoCombustible.getSelectionIndex();
	}

	public void setDisponiblePrepago(String disponiblePrepago) {
		if (SiscarData.getDisponiblePrepago() != null) {
			txtDisponiblePrepago.setText(SiscarData.getDisponiblePrepago());
		} else {
			txtDisponiblePrepago.setText("");
		}
	}

	public String getDisponiblePrepago() {
		return txtDisponiblePrepago.getText();
	}

	public void setDisponibleGalones(String disponibleCombustible) {
		txtDisponibleGalones.setText(disponibleCombustible);
	}

	public String getDisponibleGalones() {
		return txtDisponibleGalones.getText();
	}

	public void setUsoDisponible(int usoDisponible) {
		SiscarData.setUsoDisponible(usoDisponible);
		cmbUsoDisponible.select(usoDisponible);
	}

	public int getUsoDisponible() {
		return cmbUsoDisponible.getSelectionIndex();
	}

	public String getValorTotal() {
		return txtGalones.getText();
	}

	public void setValorTotal(String valorTotal) {
		SiscarData.setValorTotal(valorTotal);
		txtTotal.setText(SiscarData.getValorTotal());
	}

	public String getTarifa() {
		return txtTarifa.getText();
	}

	public void setTarifa(String Tarifa) {
		txtTarifa.setText("$ " + SiscarData.getTarifa());
	}
}
