package geniar.siscar.model;

public class BillingAccountVO {

	private Long idVehiculo;
	private String carnet;
	private String placaDiplomatica;

	public String getPlacaDiplomatica() {
		return placaDiplomatica;
	}

	public void setPlacaDiplomatica(String placaDiplomatica) {
		this.placaDiplomatica = placaDiplomatica;
	}

	public BillingAccountVO() {
	}

	public BillingAccountVO(Long idVehiculo, String carnet,
			String placaDiplomatica) {
		super();
		this.idVehiculo = idVehiculo;
		this.carnet = carnet;
		this.placaDiplomatica = placaDiplomatica;
	}

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

}
