package geniar.siscar.model;
import java.util.Date;

public class BillingAccountAutoInsuranceVO {
	
	private String asignacion;
	private Long idVehiculo;
	private String carnet;
	private String placaDiplomatica;
	private String nombreAsignatario;
	private Long vhaCodigo;
	private Date fechaInicial;
	private Date fechaFinal;

	public String getPlacaDiplomatica() {
		return placaDiplomatica;
	}

	public void setPlacaDiplomatica(String placaDiplomatica) {
		this.placaDiplomatica = placaDiplomatica;
	}

	public BillingAccountAutoInsuranceVO(){}

	public Long getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public Long getVhaCodigo() {
		return vhaCodigo;
	}

	public void setVhaCodigo(Long vhaCodigo) {
		this.vhaCodigo = vhaCodigo;
	}

	public String getNombreAsignatario() {
		return nombreAsignatario;
	}

	public void setNombreAsignatario(String nombreAsignatario) {
		this.nombreAsignatario = nombreAsignatario;
	}

	public String getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
}

