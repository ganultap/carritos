package geniar.siscar.model;

import java.util.Date;

public class VORegistryService {

	/* Nuevas variables */
	private Date fechaRecibo;
	private Long sercodigo;
	private Float galones;
	private Float kmPromedio;
	private Float valor;
	private String centroCosto;
	private String nroRecibo;
	private String serKilometrajeActual;
	private String serKilometrajeAnterior;
	private Vehicles vehicles;
	private VehiclesAssignation vehiclesAssignation;

	public Vehicles getVehicles() {
		return vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	public VehiclesAssignation getVehiclesAssignation() {
		return vehiclesAssignation;
	}

	public void setVehiclesAssignation(VehiclesAssignation vehiclesAssignation) {
		this.vehiclesAssignation = vehiclesAssignation;
	}

	public String getSerKilometrajeActual() {
		return serKilometrajeActual;
	}

	public void setSerKilometrajeActual(String serKilometrajeActual) {
		this.serKilometrajeActual = serKilometrajeActual;
	}

	public VORegistryService() {
	}

	public VORegistryService(Date fechaRecibo, Float galones, Float kmPromedio,
			Float valor, String centroCosto, String serKilometrajeActual) {
		super();
		this.fechaRecibo = fechaRecibo;
		this.galones = galones;
		this.kmPromedio = kmPromedio;
		this.valor = valor;
		this.centroCosto = centroCosto;
		this.serKilometrajeActual = serKilometrajeActual;
	}

	public Date getFechaRecibo() {
		return fechaRecibo;
	}

	public void setFechaRecibo(Date fechaRecibo) {
		this.fechaRecibo = fechaRecibo;
	}

	public Float getKmPromedio() {
		return kmPromedio;
	}

	public void setKmPromedio(Float kmPromedio) {
		this.kmPromedio = kmPromedio;
	}

	public String getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	public Float getGalones() {
		return galones;
	}

	public void setGalones(Float galones) {
		this.galones = galones;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Long getSercodigo() {
		return sercodigo;
	}

	public void setSercodigo(Long sercodigo) {
		this.sercodigo = sercodigo;
	}

	public String getNroRecibo() {
		return nroRecibo;
	}

	public void setNroRecibo(String nroRecibo) {
		this.nroRecibo = nroRecibo;
	}

	public String getSerKilometrajeAnterior() {
		return serKilometrajeAnterior;
	}

	public void setSerKilometrajeAnterior(String serKilometrajeAnterior) {
		this.serKilometrajeAnterior = serKilometrajeAnterior;
	}
}
