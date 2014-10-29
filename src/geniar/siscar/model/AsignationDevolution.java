package geniar.siscar.model;

import java.util.Date;

public class AsignationDevolution {
	
	private Long vhaCodigo;
	private String vhaObservacionDev;
	private Requests requests;
	private Vehicles vehicles;
	private AssignationsTypes assignationsTypes;
	private AssignationsStates assignationsStates;
	private Long vhaKilomeActual;
	private Date vhaFechaEntrega;
	private Date vhaFechaDev;
	private Date vhaFechaIni;
	private Date vhaFechaFin;
	private Long vhaKilomDev;
	
	private boolean check;
	
	
	public Requests getRequests() {
		return requests;
	}
	public void setRequests(Requests requests) {
		this.requests = requests;
	}
	public Vehicles getVehicles() {
		return vehicles;
	}
	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}
	public AssignationsTypes getAssignationsTypes() {
		return assignationsTypes;
	}
	public void setAssignationsTypes(AssignationsTypes assignationsTypes) {
		this.assignationsTypes = assignationsTypes;
	}
	public AssignationsStates getAssignationsStates() {
		return assignationsStates;
	}
	public void setAssignationsStates(AssignationsStates assignationsStates) {
		this.assignationsStates = assignationsStates;
	}

	public Long getVhaKilomeActual() {
		return vhaKilomeActual;
	}
	public void setVhaKilomeActual(Long vhaKilomeActual) {
		this.vhaKilomeActual = vhaKilomeActual;
	}
	
	public Date getVhaFechaEntrega() {
		return vhaFechaEntrega;
	}
	public void setVhaFechaEntrega(Date vhaFechaEntrega) {
		this.vhaFechaEntrega = vhaFechaEntrega;
	}
	public Date getVhaFechaDev() {
		return vhaFechaDev;
	}
	public void setVhaFechaDev(Date vhaFechaDev) {
		this.vhaFechaDev = vhaFechaDev;
	}
	public Long getVhaKilomDev() {
		return vhaKilomDev;
	}
	public void setVhaKilomDev(Long vhaKilomDev) {
		this.vhaKilomDev = vhaKilomDev;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public Long getVhaCodigo() {
		return vhaCodigo;
	}
	public void setVhaCodigo(Long vhaCodigo) {
		this.vhaCodigo = vhaCodigo;
	}
	public Date getVhaFechaIni() {
		return vhaFechaIni;
	}
	public void setVhaFechaIni(Date vhaFechaIni) {
		this.vhaFechaIni = vhaFechaIni;
	}
	public Date getVhaFechaFin() {
		return vhaFechaFin;
	}
	public void setVhaFechaFin(Date vhaFechaFin) {
		this.vhaFechaFin = vhaFechaFin;
	}
	public String getVhaObservacionDev() {
		return vhaObservacionDev;
	}
	public void setVhaObservacionDev(String vhaObservacionDev) {
		this.vhaObservacionDev = vhaObservacionDev;
	}
}