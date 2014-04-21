package ciat.siscar.mobile.ui;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import ciat.siscar.mobile.SiscarData;

public class UsuarioPanel extends Composite {

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public UsuarioPanel(Composite parent, int style) {
		
		super(parent, style);

		setLayout(new GridLayout(2, false));
		
		Label lblFecha = new Label(this, SWT.NONE);
		lblFecha.setText("Fecha");
		
		txtFecha = new Label(this, SWT.NONE);
		txtFecha.setText(fecha.toString());
				
		Label lblUsuario = new Label(this, SWT.NONE);
		lblUsuario.setText("Usuario");
		
		txtUsuario = new Label(this, SWT.NONE);
		txtUsuario.setText(SiscarData.getUsuario());
						
		this.addPaintListener(new PaintListener() {

			public void paintControl(PaintEvent arg0) {
				setUsuario(SiscarData.getUsuario());
				setFecha();				
			}
		});		
						
						
	}
	
	Label txtFecha;
	Label txtUsuario;

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	// atributos
	
	Date fecha = new Date();
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha() {		
		this.fecha = new Date();
		txtFecha.setText(fecha.toString());
	}

	public void setFecha(Date fecha) {		
		this.fecha = fecha;
		txtFecha.setText(fecha.toString());
	}
	
	public String getUsuario() {
		return txtUsuario.getText();
	}

	public void setUsuario(String usuario) {
		txtUsuario.setText(usuario);
	}
}
