package geniar.siscar.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author - GeniarArq S.A
 * @version 1.0 Descripción : Componente que proporciona la funcionalidad de
 *          carga y procesamiento de archivos de propiedades, de acuerdo al
 *          nombre del archivo y las claves correspondientes.
 */
public class LoaderResourceElements {

	private static final Log log = LogFactory.getLog(LoaderResourceElements.class);
	private static LoaderResourceElements loaderResourceElements;

	private LoaderResourceElements() {
	}

	/**
	 * Retorna una única instancia de la clase LoaderReourceElements
	 * 
	 * @return loaderReourceElements
	 * 
	 */
	public static LoaderResourceElements getInstance() {
		if (loaderResourceElements == null)
			loaderResourceElements = new LoaderResourceElements();
		return loaderResourceElements;
	}

	/**
	 * Retorna una cadena los mensajes de error de la aplicación
	 * 
	 * @return loaderReourceElements
	 * 
	 */
	public String getErrorMessageValue(String key) {
		String keyValue = null;
		try {
			InputStream error = this.getClass().getClassLoader().getResourceAsStream(
					"geniar/siscar/config/msgerror.properties");
			Properties properties = new Properties();

			properties.load(error);
			keyValue = properties.getProperty(key);
		} catch (IOException e) {
			log.error(e);
		}

		return keyValue;
	}

	/**
	 * Retorna una cadena con un propiedad para los labels en pantalla
	 * 
	 * @return loaderReourceElements
	 * 
	 */
	public String getLabelsProperties(String key) {
		String keyValue = null;
		try {
			InputStream labels = this.getClass().getClassLoader().getResourceAsStream(
					"geniar/siscar/config/labels.properties");
			Properties properties = new Properties();

			properties.load(labels);
			keyValue = properties.getProperty(key);
		} catch (IOException e) {
			log.error(e);
		}
		return keyValue;
	}
	
	/**
	 * Retorna una cadena con un propiedad para los mensajes en pantalla
	 * 
	 * @return loaderReourceElements
	 * 
	 */
	public String getMsgProperties(String key) {
		String keyValue = null;
		try {
			InputStream labels = this.getClass().getClassLoader().getResourceAsStream(
					"geniar/siscar/config/msg.properties");
			Properties properties = new Properties();

			properties.load(labels);
			keyValue = properties.getProperty(key);
		} catch (IOException e) {
			log.error(e);
		}

		return keyValue;
	}
	
	/**
	 * Retorna una cadena con un propiedad para los parametros generales del sistema
	 * 
	 * @return loaderReourceElements
	 * 
	 */
	public String getParametrosGenerales(String key) {
		String keyValue = null;
		try {
			InputStream labels = this.getClass().getClassLoader().getResourceAsStream(
					"geniar/siscar/config/parametros_generales.properties");
			Properties properties = new Properties();

			properties.load(labels);
			keyValue = properties.getProperty(key);
		} catch (IOException e) {
			log.error(e);
		}

		return keyValue;
	}

	/**
	 * Retorna una cadena la expresion regular
	 * 
	 * @return loaderReourceElements
	 * 
	 */
	public String getValueExpresion(String key) {
		String keyValue = null;
		try {
			InputStream msg = this.getClass().getClassLoader().getResourceAsStream(
					"geniar/siscar/config/expresiones.properties");
			Properties properties = new Properties();

			properties.load(msg);
			keyValue = properties.getProperty(key);
		} catch (IOException e) {
			log.error(e);
		}

		return keyValue;
	}

}
