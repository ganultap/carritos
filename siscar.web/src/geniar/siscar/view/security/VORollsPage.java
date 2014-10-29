package geniar.siscar.view.security;

public class VORollsPage {
	private String idRol;
	private String nombre;
	private String descripcion;
	private String email;
	private boolean checkState;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean getCheckState() {
		return checkState;
	}
	public void setCheckState(boolean checkState) {
		this.checkState = checkState;
	}
	public String getIdRol() {
		return idRol;
	}
	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
