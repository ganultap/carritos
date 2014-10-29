/*
 * Created on 11/01/2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package geniar.mobile.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Jaime Chavarriaga
 *
 */
public class MessageBoxUtil {

	public static int showMessageBox(Shell shell, String titulo, String mensaje) {
		MessageBox messageBox = new MessageBox(
			shell, 
			SWT.ICON_INFORMATION | SWT.YES);
		messageBox.setText(titulo);
		messageBox.setMessage(mensaje);
		int result = messageBox.open();
				
		return result;
	}

	public static int showYesNoMessageBox(Shell shell, String titulo, String mensaje) {
		MessageBox messageBox = new MessageBox(
			shell, 
			SWT.ICON_INFORMATION | SWT.YES | SWT.NO);
		messageBox.setText(titulo);
		messageBox.setMessage(mensaje);
		int result = messageBox.open();
		return result;
	}

}
