package ciat.siscar.mobile.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ciat.siscar.mobile.SiscarData;

/**
 * @author Jaime Chavarriaga
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LoginShell extends AbstractLoginShell {
	
	// Constructores

	/**
	 * @param display
	 * @param style
	 * @param parentShell
	 */
	public LoginShell(Display display, int style, Shell parentShell) {
		super(display, style, parentShell);
	}

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		show();	
	}
	
	public static void show() {
		show(null);
	}		
	
	public static void show(Shell parentShell) {
		try {
			Display display = Display.getDefault();
			LoginShell shell = new LoginShell(display, SWT.RESIZE, parentShell);
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
	
	// datos
	
	public void setLogin(String login) {
		txtLogin.setText(login);
	}
	
	public String getLogin() {
		return txtLogin.getText();
	}
	
	public void setPassword(String password) {
		txtPassword.setText(password);
	}
	
	public String getPassword() {
		return txtPassword.getText();
	}

	// binding
	
	public void updateModel() {
		SiscarData.setLogin(getLogin());
	}

	public void updateForm() {
		setLogin(SiscarData.getLogin());
	}

}
