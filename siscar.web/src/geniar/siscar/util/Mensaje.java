package geniar.siscar.util;

import javax.faces.FactoryFinder;
import javax.faces.application.ApplicationFactory;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;


public class Mensaje {

	boolean visible = false;

	String mensaje = "";

	private String managedBeanDestino;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void mostrarMensaje(String mensaje) {
		// Muestra el mensaje en el panel popup
		this.mensaje = mensaje;
		visible = true;
		FacesContext.getCurrentInstance().notifyAll();
	}

	public String aceptar() throws Exception {
		Object objReturn = null;
		try {
			MethodBinding metodo = getMethodBinding(this.getManagedBeanDestino(), new Class[] { String.class });
			objReturn = metodo.invoke(FacesContext.getCurrentInstance(), new Object[] { null });
		} catch (Exception e) {
			/*
			 * throw new ExcepcionesCentralRiesgo( "No se pudo insertar los
			 * datos seleccionados en los campos de destino");
			 */
		}
		FacesContext.getCurrentInstance().notifyAll();
		return "ok";
	}

	public String ocultarMensaje() {
		String gui = "";
		Object objReturn = null;
		this.mensaje = "";
		visible = false;
		String strDestino = this.getManagedBeanDestino();
		if (strDestino != null && strDestino.length() > 0) {
			MethodBinding metodo = getMethodBinding(strDestino, null);
			objReturn = metodo.invoke(FacesContext.getCurrentInstance(), null);
			if (objReturn != null) {
				gui = (String) objReturn;
			}
		}
		FacesContext.getCurrentInstance().notifyAll();
		return gui;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public static MethodBinding getMethodBinding(String bindingName, Class params[]) {
		ApplicationFactory factory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
		MethodBinding binding = factory.getApplication().createMethodBinding(bindingName, params);
		return binding;
	}

	public String getManagedBeanDestino() {
		return managedBeanDestino;
	}

	public void setManagedBeanDestino(String managedBeanDestino) {
		this.managedBeanDestino = managedBeanDestino;
	}

}
