/*
 * Created on 12/01/2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ciat.siscar.mobile;

import geniar.mobile.utils.PreferencesUtils;
import ciat.siscar.mobile.services.SiscarPort_Stub;;

/**
 * @author Jaime Chavarriaga
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SiscarService {

	private static String url
		= "http://192.168.123.110:8081/siscar.services/SiscarService";
	private static SiscarPort_Stub service;
	
	public static void setUrl (String newUrl) {
		if (service != null) {
			service._setProperty(
				javax.xml.rpc.Stub.ENDPOINT_ADDRESS_PROPERTY, 
				newUrl);
		}
		url = newUrl;
	}
	
	public static String getUrl() {
		try {
			String newUrl = PreferencesUtils.getProperties().getProperty("servidor");
			if (newUrl != null) {
				url = newUrl;
			}			
		} catch (Exception e) {
		}
		return url;
	}
	
	public static SiscarPort_Stub getService() {

		if (service == null){ 
			service = new SiscarPort_Stub();
			service._setProperty(
				javax.xml.rpc.Stub.ENDPOINT_ADDRESS_PROPERTY, 
				getUrl());			
		}
		return service;		
	}
	
}
