/**
 * SiscarException.java
 *
 * Generated on Tue Feb 03 00:17:51 GMT 2009
 * Generated from http://localhost:8082/siscar.services/SiscarService?wsdl
 */

package ciat.siscar.mobile.services;

public class SiscarException extends java.lang.Exception {

	private String message = null;
	
	public SiscarException() {
	}

	public SiscarException(String arg0) {
		super(arg0);
		message = arg0;
	}

	public SiscarException(Throwable arg0) {
	//	super(arg0);
	}

	public SiscarException(String arg0, Throwable arg1) {
		super(arg0);
		message = arg0;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return (message != null ? message : super.getMessage());
	}


}
