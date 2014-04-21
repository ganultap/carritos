package geniar.siscar.model;

public class NitProveedorVO {

	private String NIT_PROVEEDOR;
	private String VENDOR_NAME;

	public NitProveedorVO() {
	}

	public NitProveedorVO(String nitProveedor,
			String nombreProveedor) {
		super();
		this.NIT_PROVEEDOR = nitProveedor;
		this.VENDOR_NAME = nombreProveedor;
	}

	public String getNit_Proveedor() {
		return NIT_PROVEEDOR;
	}

	public void setNit_Proveedor(String nit_proveedor) {
		NIT_PROVEEDOR = nit_proveedor;
	}

	public String getVendor_Name() {
		return VENDOR_NAME;
	}

	public void setVendor_Name(String vendor_name) {
		VENDOR_NAME = vendor_name;
	}
}
