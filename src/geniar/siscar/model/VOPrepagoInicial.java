package geniar.siscar.model;

import java.util.Date;

public class VOPrepagoInicial {

	private String placa;
	private Double consumoPromedio;
	private String centroCosto;
	private Long kmAnual;
	private Long kmPromedio;
	private Double galonesAno;
	private String tipoCombustible;
	private Double valorPrepago;
	private String tipoCargo;
	private String observacion;
	private CostCentersFuel costCentersFuel;
	private VehiclesAssignation vehiclesAssignation;
	private Date minFechaServicio;
	private Date maxFechaServicio;
	private boolean visible;
	private boolean seleccion;
	private String tipoAsignacion;
	private String asignatario;

	public String getAsignatario() {
		return asignatario;
	}

	public void setAsignatario(String asignatario) {
		this.asignatario = asignatario;
	}

	public String getTipoAsignacion() {
		return tipoAsignacion;
	}

	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}

	public boolean isSeleccion() {
		return seleccion;
	}

	public void setSeleccion(boolean seleccion) {
		this.seleccion = seleccion;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Double getConsumoPromedio() {
		return consumoPromedio;
	}

	public void setConsumoPromedio(Double consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
	}

	public String getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	public Long getKmAnual() {
		return kmAnual;
	}

	public void setKmAnual(Long kmAnual) {
		this.kmAnual = kmAnual;
	}

	public Double getGalonesAno() {
		return galonesAno;
	}

	public void setGalonesAno(Double galonesAno) {
		this.galonesAno = galonesAno;
	}

	public String getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public Double getValorPrepago() {
		return valorPrepago;
	}

	public void setValorPrepago(Double valorPrepago) {
		this.valorPrepago = valorPrepago;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Long getKmPromedio() {
		return kmPromedio;
	}

	public void setKmPromedio(Long kmPromedio) {
		this.kmPromedio = kmPromedio;
	}

	public CostCentersFuel getCostCentersFuel() {
		return costCentersFuel;
	}

	public void setCostCentersFuel(CostCentersFuel costCentersFuel) {
		this.costCentersFuel = costCentersFuel;
	}

	public VehiclesAssignation getVehiclesAssignation() {
		return vehiclesAssignation;
	}

	public void setVehiclesAssignation(VehiclesAssignation vehiclesAssignation) {
		this.vehiclesAssignation = vehiclesAssignation;
	}

	public Date getMinFechaServicio() {
		return minFechaServicio;
	}

	public void setMinFechaServicio(Date minFechaServicio) {
		this.minFechaServicio = minFechaServicio;
	}

	public Date getMaxFechaServicio() {
		return maxFechaServicio;
	}

	public void setMaxFechaServicio(Date maxFechaServicio) {
		this.maxFechaServicio = maxFechaServicio;
	}

}
