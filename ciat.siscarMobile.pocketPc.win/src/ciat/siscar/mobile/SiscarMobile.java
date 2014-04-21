package ciat.siscar.mobile;

import geniar.mobile.utils.MessageBoxUtil;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;

import ciat.siscar.mobile.ui.*;

public class SiscarMobile {
	
	static Shell shlCiatSiscar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		final Display display = Display.getDefault();
		shlCiatSiscar = new Shell(SWT.CLOSE | SWT.RESIZE);
		
		shlCiatSiscar.setText("CIAT Siscar");

		// background image
		SiscarImages.getBackgroundImage();
		
		shlCiatSiscar.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				arg0.gc.drawImage(SiscarImages.getBackgroundImage(), 0, 0);
			}
		});

		// menu
		
		Menu menuBar = new Menu(shlCiatSiscar, SWT.BAR);
		shlCiatSiscar.setMenuBar(menuBar);

		MenuItem ciatMenuItem = new MenuItem (menuBar, SWT.CASCADE);
		ciatMenuItem.setText ("&Ciat");
		Menu submenu = new Menu (menuBar);
		ciatMenuItem.setMenu (submenu);

		
		MenuItem item = new MenuItem (submenu, SWT.PUSH);
		item.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				PreferencesShell.show();
			}
		});
		item.setText ("&Configurar");
		item.setAccelerator (SWT.MOD1 + 'C');

		item = new MenuItem (submenu, SWT.PUSH);
		item.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				LoginShell.show(shlCiatSiscar);
			}
		});
		item.setText ("&Login");
		item.setAccelerator (SWT.MOD1 + 'L');


		item = new MenuItem (submenu, SWT.PUSH);
		item.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				MessageBox messageBox = new MessageBox(shlCiatSiscar, SWT.ICON_INFORMATION| SWT.YES | SWT.NO);
				messageBox.setText("SISCAR");
				messageBox.setMessage("Desea salir de SISCAR ?");
				int result = messageBox.open();
				if (result == SWT.YES) {
					shlCiatSiscar.close();
					System.exit(0);
				}						
			}
		});
		item.setText ("&Salir");
		item.setAccelerator (SWT.MOD1 + 'S');

		
		// layout
		GridLayout layout = new GridLayout(1, true);		
		shlCiatSiscar.setLayout(layout);
		
		Composite header = new Composite(shlCiatSiscar, SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 0;
		header.setLayout(rowLayout);
		
		Label lblLogo = new Label(header, SWT.NONE);
		{
			RowData rowData = new RowData();
			rowData.width = 107;
			rowData.height = 48;
			lblLogo.setLayoutData(rowData);
		}		
		lblLogo.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				arg0.gc.drawImage(SiscarImages.getLogoImage(), 0, 0);
			}
		});		
		
		Button btnLogin = new Button(shlCiatSiscar, SWT.FLAT);
		btnLogin.setText("Login");
		btnLogin.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
			public void widgetSelected(SelectionEvent arg0) {
				LoginShell.show();
			}
		});		
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 208;
			btnLogin.setLayoutData(gridData);
		}

		Button btnIniciar = new Button(shlCiatSiscar, SWT.FLAT);
		btnIniciar.setText("Iniciar");
		btnIniciar.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
			public void widgetSelected(SelectionEvent arg0) {
				if (SiscarData.login == null) {
					MessageBoxUtil.showMessageBox(
						shlCiatSiscar,
						"SISCAR",
						"Debe iniciar sesión antes de ofrecer servicio"
						);					
				} else {
					ServicioShell.show();
				}
			}
		});		
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 208;
			btnIniciar.setLayoutData(gridData);
		}

		Button btnConfigurar = new Button(shlCiatSiscar, SWT.FLAT);
		btnConfigurar.setText("Configurar");
		btnConfigurar.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
			public void widgetSelected(SelectionEvent arg0) {
				PreferencesShell.show();
			}
		});
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 208;
			btnConfigurar.setLayoutData(gridData);
		}
				
		
		Button btnSalir = new Button(shlCiatSiscar, SWT.FLAT);
		btnSalir.setText("Salir");
		btnSalir.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
			public void widgetSelected(SelectionEvent arg0) {
				MessageBox messageBox = new MessageBox(shlCiatSiscar, SWT.ICON_INFORMATION| SWT.YES | SWT.NO);
				messageBox.setText("SISCAR");
				messageBox.setMessage("Desea salir de SISCAR ?");
				int result = messageBox.open();
				if (result == SWT.YES) {
					shlCiatSiscar.close();
					System.exit(0);
				}						
			}
		});		
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.RIGHT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 1;
			gridData.verticalSpan = 1;
			gridData.widthHint = 208;
			btnSalir.setLayoutData(gridData);
		}
		
		shlCiatSiscar.open();
		shlCiatSiscar.layout();
		while (!shlCiatSiscar.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
