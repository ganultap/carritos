/*
 * Created on 13/01/2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package geniar.mobile.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Shell;


/**
 * @author Jaime Chavarriaga
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MessageDialog extends Shell {

	String title;
	String message;

	/**
	 * @param arg0
	 * @param arg1
	 */
	public MessageDialog(Display display, String title, String message) {
		super(display, SWT.RESIZE | SWT.CENTER | SWT.APPLICATION_MODAL);
		this.title = title;
		this.message = message;
		createContents();
	}

	public MessageDialog(String title, String message) {
		super(Display.getCurrent(), SWT.RESIZE | SWT.CENTER | SWT.APPLICATION_MODAL);
		this.title = title;
		this.message = message;
		createContents();
	}
	

	public static Shell show(Shell parentShell, String title, String message) {
		try {
			Display display = Display.getDefault();
			MessageDialog shell = new MessageDialog(display, title, message);
			shell.open();
			shell.layout();
			return shell;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	

	public void createContents() {
		
		setText(title);
		
		Text txtMessage = new Text(this, SWT.NONE);
		txtMessage.setText(message);
		
		pack();
		
		Rectangle size = getBounds();
		Rectangle screenSize = Display.getCurrent().getBounds();

		setSize(
			size.width + 20,
			size.height + 10);
		setLocation(
			(int)((screenSize.width - size.width) / 2) ,
			(int)((screenSize.height - size.height) / 2)
			);
			
		GridLayout data = new GridLayout(1, true);
		data.marginWidth = 10;
		data.marginHeight = 10;
		
		setLayout(data);

	}
	
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}


}
