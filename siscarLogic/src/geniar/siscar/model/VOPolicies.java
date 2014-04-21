package geniar.siscar.model;

import java.util.Date;

public class VOPolicies {

	private LegateesTypes legateesTypes;
	private Vehicles vehicles;
	private Policies policies;
	private MonthTransacType monthTransacType;
	private Long pvsEstado;
	private Date pvsFechaini;
	private Date pvsFechafin;
	private Float pvsValorPrima;
	private Float pvsValorIva;
	private Float pvsValorTotal;
	private Float pvsValorAsegurado;
	private String pvsCarnetAsignatario;
	private String pvsNumeroFactura;
	private PoliciesInvoice policiesInvoice;
	private boolean seleccion;
	
	public boolean isSeleccion() {
		return seleccion;
	}
	public void setSeleccion(boolean seleccion) {
		this.seleccion = seleccion;
	}
	public LegateesTypes getLegateesTypes() {
		return legateesTypes;
	}
	public void setLegateesTypes(LegateesTypes legateesTypes) {
		this.legateesTypes = legateesTypes;
	}
	public Vehicles getVehicles() {
		return vehicles;
	}
	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}
	public Policies getPolicies() {
		return policies;
	}
	public void setPolicies(Policies policies) {
		this.policies = policies;
	}
	public MonthTransacType getMonthTransacType() {
		return monthTransacType;
	}
	public void setMonthTransacType(MonthTransacType monthTransacType) {
		this.monthTransacType = monthTransacType;
	}
	public Long getPvsEstado() {
		return pvsEstado;
	}
	public void setPvsEstado(Long pvsEstado) {
		this.pvsEstado = pvsEstado;
	}
	public Date getPvsFechaini() {
		return pvsFechaini;
	}
	public void setPvsFechaini(Date pvsFechaini) {
		this.pvsFechaini = pvsFechaini;
	}
	public Date getPvsFechafin() {
		return pvsFechafin;
	}
	public void setPvsFechafin(Date pvsFechafin) {
		this.pvsFechafin = pvsFechafin;
	}
	public Float getPvsValorPrima() {
		return pvsValorPrima;
	}
	public void setPvsValorPrima(Float pvsValorPrima) {
		this.pvsValorPrima = pvsValorPrima;
	}
	public Float getPvsValorIva() {
		return pvsValorIva;
	}
	public void setPvsValorIva(Float pvsValorIva) {
		this.pvsValorIva = pvsValorIva;
	}
	public Float getPvsValorTotal() {
		return pvsValorTotal;
	}
	public void setPvsValorTotal(Float pvsValorTotal) {
		this.pvsValorTotal = pvsValorTotal;
	}
	public Float getPvsValorAsegurado() {
		return pvsValorAsegurado;
	}
	public void setPvsValorAsegurado(Float pvsValorAsegurado) {
		this.pvsValorAsegurado = pvsValorAsegurado;
	}
	public String getPvsCarnetAsignatario() {
		return pvsCarnetAsignatario;
	}
	public void setPvsCarnetAsignatario(String pvsCarnetAsignatario) {
		this.pvsCarnetAsignatario = pvsCarnetAsignatario;
	}
	public String getPvsNumeroFactura() {
		return pvsNumeroFactura;
	}
	public void setPvsNumeroFactura(String pvsNumeroFactura) {
		this.pvsNumeroFactura = pvsNumeroFactura;
	}
	public PoliciesInvoice getPoliciesInvoice() {
		return policiesInvoice;
	}
	public void setPoliciesInvoice(PoliciesInvoice policiesInvoice) {
		this.policiesInvoice = policiesInvoice;
	}
	
}
