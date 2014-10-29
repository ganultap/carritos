package ciat.siscar.mobile.ui;

import geniar.mobile.utils.MessageBoxUtil;
import geniar.mobile.utils.PreferencesUtils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import ciat.siscar.mobile.SiscarService;

public abstract class AbstractPreferencesShell extends Shell {

	protected  Text txtServidor;
	protected  Shell preferencesShell;
	
	public AbstractPreferencesShell(Display display, int style) {
		super(display, style);
		createContents();
		preferencesShell = this;
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		setText("SISCAR CIAT Configuración");
		
		setLayout(new GridLayout(2, false));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Menu menuBar = new Menu(this, SWT.BAR);
		setMenuBar(menuBar);

		Label lblServidor = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			lblServidor.setLayoutData(gridData);
		}
		lblServidor.setText("servidor");
		
		txtServidor = new Text(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 120;			
			txtServidor.setLayoutData(gridData);
		}
		String servidor = null;
		try {
			servidor = PreferencesUtils.getProperties().getProperty("servidor");
		} catch (Exception e) {
		}
		txtServidor.setText(servidor == null ? SiscarService.getUrl(): servidor);

		new Label(this, SWT.NONE);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button btnIniciar = new Button(composite, SWT.NONE);
		btnIniciar.setText("Guardar");
		btnIniciar.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
			public void widgetSelected(SelectionEvent arg0) {

				try {
					PreferencesUtils.getProperties().setProperty(
						"servidor",
						txtServidor.getText());
					PreferencesUtils.saveProperties();
					
					SiscarService.setUrl(txtServidor.getText());

					MessageBoxUtil.showMessageBox(
						preferencesShell,
						"SISCAR",
						"Preferencias Modificadas");
					preferencesShell.close();
					
				} catch (Exception e) {
					MessageBoxUtil.showMessageBox(
						preferencesShell,
						"SISCAR",
						"Error : " + e.getMessage());
				}
			}
		});
		
		Button btnCerrar = new Button(composite, SWT.NONE);
		btnCerrar.setText("Cancelar");
		btnCerrar.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
			public void widgetSelected(SelectionEvent arg0) {
				preferencesShell.close();
			}
		});

	}

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
