package geniar.siscar.model;

public class InvoicesHeaderPoliciesVO {

	private String strError;
	private String strGraba;
	private Long lgSecuenciaFactura;
	private Long lgOrigenId;
	private Long lgUbicacion;
	private Long lgCodigoUsuario;

	public String getStrError() {
		return strError;
	}

	public void setStrError(String strError) {
		this.strError = strError;
	}

	public String getStrGraba() {
		return strGraba;
	}

	public void setStrGraba(String strGraba) {
		this.strGraba = strGraba;
	}

	public Long getLgSecuenciaFactura() {
		return lgSecuenciaFactura;
	}

	public void setLgSecuenciaFactura(Long lgSecuenciaFactura) {
		this.lgSecuenciaFactura = lgSecuenciaFactura;
	}

	public Long getLgOrigenId() {
		return lgOrigenId;
	}

	public void setLgOrigenId(Long lgOrigenId) {
		this.lgOrigenId = lgOrigenId;
	}

	public Long getLgUbicacion() {
		return lgUbicacion;
	}

	public void setLgUbicacion(Long lgUbicacion) {
		this.lgUbicacion = lgUbicacion;
	}

	public Long getLgCodigoUsuario() {
		return lgCodigoUsuario;
	}

	public void setLgCodigoUsuario(Long lgCodigoUsuario) {
		this.lgCodigoUsuario = lgCodigoUsuario;
	}

	public InvoicesHeaderPoliciesVO() {
	}

	public InvoicesHeaderPoliciesVO(String Error, String Graba,
			Long SecuenciaFactura, Long OrigenId, Long Ubicacion,
			Long CodigoUsuario) {
		super();
		this.strError = Error;
		this.strGraba = Graba;
		this.lgSecuenciaFactura = SecuenciaFactura;
		this.lgOrigenId = OrigenId;
		this.lgUbicacion = Ubicacion;
		this.lgCodigoUsuario = CodigoUsuario;
	}
}
