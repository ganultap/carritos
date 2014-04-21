package ciat.siscar.mobile.ui;

import geniar.mobile.utils.MessageBoxUtil;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import ciat.siscar.mobile.SiscarData;
import ciat.siscar.mobile.SiscarService;
import ciat.siscar.mobile.services.SiscarPort_Stub;

public abstract class AbstractLoginShell extends Shell {

	Text txtLogin;
	Text txtPassword;
	
	Shell loginShell;

	/**
	 * Create the shell
	 * @param display
	 * @param style
	 */
	public AbstractLoginShell(Display display, int style, Shell parentShell) {
		super(display, style);
		createContents();
		setLayout(new GridLayout(2, false));
		
		this.loginShell = this;
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {

		Menu menuBar = new Menu(this, SWT.BAR);
		setMenuBar(menuBar);

		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		Label lblLogin = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;			

			lblLogin.setLayoutData(gridData);
		}
		lblLogin.setText("Login");
		
		txtLogin = new Text(this, SWT.BORDER);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;			
			
			txtLogin.setLayoutData(gridData);
		}
		
		Label lblPassword = new Label(this, SWT.NONE);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = false;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;			
			lblPassword.setLayoutData(gridData);
		}
		lblPassword.setText("Password");
		
		txtPassword = new Text(this, SWT.BORDER);
		txtPassword.setEchoChar('*');
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;			
			txtPassword.setLayoutData(gridData);
		}
		new Label(this, SWT.NONE);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Button btnIniciar = new Button(composite, SWT.NONE);
		btnIniciar.setText("Iniciar");
		btnIniciar.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
			public void widgetSelected(SelectionEvent arg0) {
				try {
					SiscarPort_Stub service = SiscarService.getService();
					String res = service.validarLogin(
						txtLogin.getText(), txtPassword.getText());
					SiscarData.setLogin(txtLogin.getText());
					SiscarData.setUsuario(res);
					loginShell.close();
				} catch (Exception e) {
					MessageBoxUtil.showMessageBox(
						loginShell, 
						"SISCAR",
						"Error al validar usuario " + e.getMessage());
				}
			}
		});		
		
		Button btnSalir = new Button(composite, SWT.NONE);
		btnSalir.setText("Cancelar");
		btnSalir.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
			public void widgetSelected(SelectionEvent arg0) {
				MessageBox messageBox = new MessageBox(loginShell, SWT.ICON_INFORMATION| SWT.YES | SWT.NO);
				messageBox.setText("SISCAR");
				messageBox.setMessage("Desea cancelar el inicio de sesión?");
				int result = messageBox.open();
				if (result == SWT.YES) {
					loginShell.close();
				}						
			}
		});		

	}

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
