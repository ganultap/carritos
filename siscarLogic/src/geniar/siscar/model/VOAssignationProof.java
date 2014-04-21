package geniar.siscar.model;

import java.util.Date;

/**
 * The Class VOAssignationProof.
 */
public class VOAssignationProof {

	/** The nombre asignatario. */
	private String nombreAsignatario;
	
	/** The id asignacion. */
	private Long idAsignacion;
	
	/** The Lgt codigo. */
	private Long LgtCodigo;

	/** The Lct codigo. */
	private Long LctCodigo;
	
	/** The placa. */
	private String placa;
	
	/** The id tipo asignacion. */
	private Long idTipoAsignacion;
	
	/** The tipo asignacion. */
	private String tipoAsignacion;
	
	/** The centro costo. */
	private String centroCosto;
	
	/** The visible. */
	private boolean visible;
	
	/** The seleccion. */
	private boolean seleccion;
	
	/** The observaciones. */
	private String observaciones;
	
	/** The fecha ini. */
	private Date fechaIni;
	
	/** The fecha fin. */
	private Date fechaFin;
	
	/** The valor asignacion. */
	private Double valorAsignacion;
	
	/** The valor mantenimiento. */
	private Double valorMantenimiento;
	
	/** The valor depreciacion. */
	private Double valorDepreciacion;
	
	/** The valor autoseguro. */
	private Double valorAutoseguro;
	
	/** The valor espacio fisico. */
	private Double valorEspacioFisico;
	
	/** The porcentaje cc. */
	private Float porcentajeCC;
	
	/**
	 * Gets the valor asignacion.
	 *
	 * @return the valor asignacion
	 */
	public Double getValorAsignacion() {
		return valorAsignacion;
	}

	/**
	 * Sets the valor asignacion.
	 *
	 * @param valorAsignacion the new valor asignacion
	 */
	public void setValorAsignacion(Double valorAsignacion) {
		this.valorAsignacion = valorAsignacion;
	}

	/**
	 * Gets the porcentaje cc.
	 *
	 * @return the porcentaje cc
	 */
	public Float getPorcentajeCC() {
		return porcentajeCC;
	}

	/**
	 * Sets the porcentaje cc.
	 *
	 * @param porcentajeCC the new porcentaje cc
	 */
	public void setPorcentajeCC(Float porcentajeCC) {
		this.porcentajeCC = porcentajeCC;
	}

	/**
	 * Gets the nombre asignatario.
	 *
	 * @return the nombre asignatario
	 */
	public String getNombreAsignatario() {
		return nombreAsignatario;
	}

	/**
	 * Sets the nombre asignatario.
	 *
	 * @param nombreAsignatario the new nombre asignatario
	 */
	public void setNombreAsignatario(String nombreAsignatario) {
		this.nombreAsignatario = nombreAsignatario;
	}

	/**
	 * Gets the id asignacion.
	 *
	 * @return the id asignacion
	 */
	public Long getIdAsignacion() {
		return idAsignacion;
	}

	/**
	 * Sets the id asignacion.
	 *
	 * @param idAsignacion the new id asignacion
	 */
	public void setIdAsignacion(Long idAsignacion) {
		this.idAsignacion = idAsignacion;
	}

	/**
	 * Gets the id tipo asignacion.
	 *
	 * @return the id tipo asignacion
	 */
	public Long getIdTipoAsignacion() {
		return idTipoAsignacion;
	}

	/**
	 * Sets the id tipo asignacion.
	 *
	 * @param idTipoAsignacion the new id tipo asignacion
	 */
	public void setIdTipoAsignacion(Long idTipoAsignacion) {
		this.idTipoAsignacion = idTipoAsignacion;
	}

	/**
	 * Gets the tipo asignacion.
	 *
	 * @return the tipo asignacion
	 */
	public String getTipoAsignacion() {
		return tipoAsignacion;
	}

	/**
	 * Sets the tipo asignacion.
	 *
	 * @param tipoAsignacion the new tipo asignacion
	 */
	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}

	/**
	 * Gets the centro costo.
	 *
	 * @return the centro costo
	 */
	public String getCentroCosto() {
		return centroCosto;
	}

	/**
	 * Sets the centro costo.
	 *
	 * @param centroCosto the new centro costo
	 */
	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	/**
	 * Gets the observaciones.
	 *
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Sets the observaciones.
	 *
	 * @param observaciones the new observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Gets the fecha ini.
	 *
	 * @return the fecha ini
	 */
	public Date getFechaIni() {
		return fechaIni;
	}

	/**
	 * Sets the fecha ini.
	 *
	 * @param fechaIni the new fecha ini
	 */
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	/**
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Sets the fecha fin.
	 *
	 * @param fechaFin the new fecha fin
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Gets the placa.
	 *
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * Sets the placa.
	 *
	 * @param placa the new placa
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * Checks if is visible.
	 *
	 * @return true, if is visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Sets the visible.
	 *
	 * @param visible the new visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Checks if is seleccion.
	 *
	 * @return true, if is seleccion
	 */
	public boolean isSeleccion() {
		return seleccion;
	}

	/**
	 * Sets the seleccion.
	 *
	 * @param seleccion the new seleccion
	 */
	public void setSeleccion(boolean seleccion) {
		this.seleccion = seleccion;
	}

	/**
	 * Gets the valor mantenimiento.
	 *
	 * @return the valor mantenimiento
	 */
	public Double getValorMantenimiento() {
		return valorMantenimiento;
	}

	/**
	 * Sets the valor mantenimiento.
	 *
	 * @param valorMantenimiento the new valor mantenimiento
	 */
	public void setValorMantenimiento(Double valorMantenimiento) {
		this.valorMantenimiento = valorMantenimiento;
	}

	/**
	 * Gets the valor depreciacion.
	 *
	 * @return the valor depreciacion
	 */
	public Double getValorDepreciacion() {
		return valorDepreciacion;
	}

	/**
	 * Sets the valor depreciacion.
	 *
	 * @param valorDepreciacion the new valor depreciacion
	 */
	public void setValorDepreciacion(Double valorDepreciacion) {
		this.valorDepreciacion = valorDepreciacion;
	}

	/**
	 * Gets the valor autoseguro.
	 *
	 * @return the valor autoseguro
	 */
	public Double getValorAutoseguro() {
		return valorAutoseguro;
	}

	/**
	 * Sets the valor autoseguro.
	 *
	 * @param valorAutoseguro the new valor autoseguro
	 */
	public void setValorAutoseguro(Double valorAutoseguro) {
		this.valorAutoseguro = valorAutoseguro;
	}

	/**
	 * Gets the valor espacio fisico.
	 *
	 * @return the valor espacio fisico
	 */
	public Double getValorEspacioFisico() {
		return valorEspacioFisico;
	}

	/**
	 * Sets the valor espacio fisico.
	 *
	 * @param valorEspacioFisico the new valor espacio fisico
	 */
	public void setValorEspacioFisico(Double valorEspacioFisico) {
		this.valorEspacioFisico = valorEspacioFisico;
	}

	/**
	 * Gets the lgt codigo.
	 *
	 * @return the lgt codigo
	 */
	public Long getLgtCodigo() {
		return LgtCodigo;
	}

	/**
	 * Sets the lgt codigo.
	 *
	 * @param lgtCodigo the new lgt codigo
	 */
	public void setLgtCodigo(Long lgtCodigo) {
		LgtCodigo = lgtCodigo;
	}

	/**
	 * @return the lctCodigo
	 */
	public Long getLctCodigo() {
		return LctCodigo;
	}

	/**
	 * @param lctCodigo the lctCodigo to set
	 */
	public void setLctCodigo(Long lctCodigo) {
		LctCodigo = lctCodigo;
	}
}