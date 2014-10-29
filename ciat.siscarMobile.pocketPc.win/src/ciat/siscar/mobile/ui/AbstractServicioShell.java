package ciat.siscar.mobile.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Menu;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;

import ciat.siscar.mobile.SiscarImages;

public abstract class AbstractServicioShell extends Shell {

	protected UsuarioPanel usuarioPanel;
	protected SolicitantePanel solicitantePanel;
	protected CargoAPanel cargoAPanel;
	protected VehiculoPanel vehiculoPanel;
	protected ServicioPanel servicioPanel;
	protected ObservacionesPanel observacionesPanel;

	/**
	 * Create the shell
	 * @param display
	 * @param style
	 */
	public AbstractServicioShell(Display display, int style) {
		super(display, style);
		createContents();
		setLayout(new GridLayout(1, true));
	}

	TabFolder tabFolder;
	
	/**
	 * Create contents of the window
	 */
	protected void createContents() {
			
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				arg0.gc.drawImage(SiscarImages.getPanelBackgroundImage(), 0, 0);
			}
		});


		setText("Atención");

		Menu menuBar = new Menu(this, SWT.BAR);
		setMenuBar(menuBar);

		usuarioPanel = new UsuarioPanel(this, SWT.NONE );		
		tabFolder = new TabFolder(this, SWT.NONE);

		final TabItem tiposolicitanteTabItem = new TabItem(tabFolder, SWT.NONE);
		solicitantePanel = new SolicitantePanel(this, tabFolder, SWT.NONE);
		tiposolicitanteTabItem.setText("Solicit");
		tiposolicitanteTabItem.setControl(solicitantePanel);
		
		final TabItem vehiculoTabItem = new TabItem(tabFolder, SWT.NONE);
		vehiculoPanel = new VehiculoPanel(this, tabFolder, SWT.NONE);
		vehiculoTabItem.setText("Vehic");
		vehiculoTabItem.setControl(vehiculoPanel);

		final TabItem cargoATabItem = new TabItem(tabFolder, SWT.NONE);
		cargoAPanel = new CargoAPanel(this, tabFolder, SWT.NONE);
		cargoATabItem.setText("Cargo");
		cargoATabItem.setControl(cargoAPanel);

		final TabItem servicioTabItem = new TabItem(tabFolder, SWT.NONE);
		servicioPanel = new ServicioPanel(tabFolder, SWT.NONE);
		servicioTabItem.setText("Servic");
		servicioTabItem.setControl(servicioPanel);

		final TabItem observacionesTabItem = new TabItem(tabFolder, SWT.NONE);
		observacionesPanel = new ObservacionesPanel(tabFolder, SWT.NONE);
		observacionesTabItem.setText("Observ");
		observacionesTabItem.setControl(observacionesPanel);

		tabFolder.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
			}

			public void widgetSelected(SelectionEvent arg0) {
				switch(tabFolder.getSelectionIndex()) {
				
				case 0:
					// solicitante	
					solicitantePanel.activar();
					break;
				case 1:
					//System.out.println("vehiculo");
					vehiculoPanel.activar();
					break;
				case 2:
					//System.out.println("cargoA");
					cargoAPanel.activar();
					break;
				case 3:
					//System.out.println("regsitro");
					servicioPanel.activar();
					break;
				case 4:
					//System.out.println("observacion");
					break;
				default:
					break;
				}				
			}		
		});
		
		addShellListener(new ShellListener() {
			public void shellActivated(ShellEvent arg0) {
			}
			public void shellClosed(ShellEvent arg0) {
				arg0.doit = cerrar();
			}
			public void shellDeactivated(ShellEvent arg0) {
			}
			public void shellDeiconified(ShellEvent arg0) {
			}
			public void shellIconified(ShellEvent arg0) {
			}			
		});
		
		tabFolder.pack();
	}

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public abstract boolean cerrar();

}
