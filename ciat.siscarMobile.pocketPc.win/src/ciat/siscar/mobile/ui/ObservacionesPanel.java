package ciat.siscar.mobile.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ObservacionesPanel extends Composite {

	private Label lblObservaciones;
	private Text txtObservaciones;
	
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public ObservacionesPanel(Composite parent, int style) {
		
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		lblObservaciones = new Label(this, SWT.NONE);
		{
			GridData gd_lblObservacion = new GridData();
			gd_lblObservacion.horizontalAlignment = SWT.RIGHT;
			gd_lblObservacion.verticalAlignment = SWT.CENTER;
			gd_lblObservacion.grabExcessHorizontalSpace = false;
			gd_lblObservacion.grabExcessVerticalSpace = false;
			gd_lblObservacion.horizontalSpan = 1;
			gd_lblObservacion.verticalSpan = 1;
			lblObservaciones.setLayoutData(gd_lblObservacion);
		}
		lblObservaciones.setVisible(true);
		lblObservaciones.setText("Observación");

		new Label(this, SWT.NONE);
		
		txtObservaciones = new Text(this, 
			SWT.V_SCROLL | SWT.MULTI | SWT.BORDER | SWT.WRAP);
		txtObservaciones.setVisible(true);
		{
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.LEFT;
			gridData.verticalAlignment = SWT.CENTER;
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = false;
			gridData.horizontalSpan = 2;
			gridData.verticalSpan = 1;
			gridData.widthHint = 150;
			gridData.heightHint = 100;
			txtObservaciones.setLayoutData(gridData);
		}
		txtObservaciones.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent arg0) {
				setObservaciones(txtObservaciones.getText());
			}
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}			
		});
		
	}

	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setObservaciones(String observacion) {
		txtObservaciones.setText(observacion);
	}
	
	public String getObservaciones() {
		return txtObservaciones.getText();
	}

}
