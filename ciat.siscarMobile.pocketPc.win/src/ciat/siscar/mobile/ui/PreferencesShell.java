package ciat.siscar.mobile.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Jaime Chavarriaga
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PreferencesShell extends AbstractPreferencesShell {

	/**
	 * @param display
	 * @param style
	 */
	public PreferencesShell(Display display, int style) {
		super(display, style);		
	}

	public static void main(String[] args) {
		show();
	}
	
	public static void show() {
		try {
			Display display = Display.getDefault();
			Shell shell = new PreferencesShell(display, SWT.CLOSE | SWT.RESIZE);

			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	// -- atributos
	
	public void setServidor(String servidor) {
		txtServidor.setText(servidor);
	}
	
	public String getServidor() {
		return txtServidor.getText();
	}
	
}
