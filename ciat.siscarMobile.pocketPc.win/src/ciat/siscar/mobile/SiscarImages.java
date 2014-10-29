/*
 * Created on 13/01/2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ciat.siscar.mobile;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

/**
 * @author Jaime Chavarriaga
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SiscarImages {

	private static Image backgroundImage;		
	private static Image logoImage;			
	private static Image panelBackgroundImage;
 
	/**
	 * @return
	 */
	public static Image getBackgroundImage() {
		if (backgroundImage == null) {
			backgroundImage = SWTResourceManager.getImage(
					SiscarMobile.class,
					"/img/background.jpg");
		}
		return backgroundImage;
	}

	/**
	 * @return
	 */
	public static Image getLogoImage() {
		if (logoImage == null) {
			logoImage = SWTResourceManager.getImage(
					SiscarMobile.class,
					"/img/logo-ciat.jpg");
		}
		return logoImage;
	}

	/**
	 * @return
	 */
	public static Image getPanelBackgroundImage() {
		if (panelBackgroundImage == null) {
			panelBackgroundImage = SWTResourceManager.getImage(
					SiscarImages.class,
					"/img/panel-background.bmp");
		}
		return panelBackgroundImage;
	}

}
