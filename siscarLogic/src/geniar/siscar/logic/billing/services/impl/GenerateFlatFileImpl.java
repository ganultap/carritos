package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.logic.billing.services.GenerateFlatFileSer;
import geniar.siscar.model.FlatFileVO;
import geniar.siscar.util.CreateFile;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class GenerateFlatFileImpl.
 */
public class GenerateFlatFileImpl implements GenerateFlatFileSer {

	/** The Constant log. */
	private static final Log log = LogFactory
			.getLog(GenerateFlatFileImpl.class);
	
	/* (non-Javadoc)
	 * @see geniar.siscar.logic.billing.services.GenerateFlatFileSer#generarArchivo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void generarArchivo(String carne, String concepto, String unidades,
			String valor, Date fecha, String documento, String centroCosto,
			String moneda, String descripcion) throws GWorkException {

		try {

			// Carnet 11
			int tam = carne.length();
			for (int i = 11; i > (11 - tam); i--) {
				carne += " ";
			}
			// Concepto 4
			// concepto = plainFileParameter.getPfpConceptonomina().toString();
			tam = concepto.length();
			for (int i = 4; i > (4 - tam); i--) {
				concepto += " ";
			}
			// Unidades 4
			unidades = "1   ";
			// Valor 12

			tam = valor.length();
			for (int i = 12; i > (12 - tam); i--) {
				valor += " ";
			}
			// Fecha 8

			String formato = "yyyyMMdd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
			String fechaFormato = simpleDateFormat.format(fecha).toString();

			// fecha="20090908";
			tam = fechaFormato.length();
			for (int i = 8; i > (8 - tam); i--) {
				fechaFormato += " ";
			}
			// Documento 10

			tam = documento.length();
			for (int i = 10; i > (10 - tam); i--) {
				documento += " ";
			}
			// Centro Costo 15
			centroCosto = "               ";
			// Moneda 2

			tam = moneda.length();
			for (int i = 2; i > (2 - tam); i--) {
				moneda += " ";
			}
			// Descripcion 35

			tam = descripcion.length();
			for (int i = 35; i > (35 - tam); i--) {
				descripcion += " ";
			}
			String linea = carne + concepto + unidades + valor + fecha
					+ documento + centroCosto + moneda + descripcion + "\n";

			CreateFile
					.createFile(Util.loadMessageValue("RUTA.BILLINGACCOUNT"),
							documento.trim() + "-" + fechaFormato.trim(),
							".txt", linea);

		} catch (Exception e) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.NOARCHIVOPLANO"));
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.billing.services.GenerateFlatFileSer#GeneradorArchivoPlano(java.util.List, java.lang.String)
	 */
	public void GeneradorArchivoPlano(List<FlatFileVO> listadoFlatFile,
			String Novedad) throws GWorkException {
		try {
			String strEspacios = "";
			String strCarnet;
			String strConcepto;
			String strUnidades;
			String strValor;
			String strFecha;
			String strDocumento = "";
			String strCentroCosto = "";
			String strMoneda;
			String strDescripcion = "";
			String strLineaArchivoPlano = "";
			File file;
			FileWriter fileWriter;
			BufferedWriter writer;
			PrintWriter printWriter;
			String formato = "yyyyMMdd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
			String fechaFormato = simpleDateFormat.format(new Date())
					.toString();
			file = new File(Util.loadMessageValue("RUTA.BILLINGACCOUNT"),
					Novedad.trim() + "_" + fechaFormato + ".txt");
			fileWriter = new FileWriter(file);
			writer = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(writer);

			for (FlatFileVO flatFile : listadoFlatFile) {
				strEspacios = "";
				strCarnet = flatFile.getFfCarne();
				strConcepto = flatFile.getFfConcepto().toString();
				strUnidades = flatFile.getFfUnidades().toString();
				strValor = flatFile.getFfValor();
				strFecha = flatFile.getFfFecha().toString();
				
				if(flatFile.getFfDocumento() != null){
					strDocumento = flatFile.getFfDocumento().trim();
				}
				
				strMoneda = flatFile.getFfMoneda().toString();
				
				if(flatFile.getFfDescripcion() != null){
					strDescripcion = flatFile.getFfDescripcion().trim();
				}

				int tam = 11 - strCarnet.length();
				for (int i = 0; i < tam; i++) {
					strCarnet += " ";
				}

				// Concepto 4
				tam = 4 - strConcepto.length();
				for (int i = 0; i < tam; i++) {
					strEspacios += " ";
				}
				strConcepto = strEspacios + strConcepto;
				strEspacios = "";

				// Unidades 4
				tam = 8 - strUnidades.length();
				for (int i = 0; i < tam; i++) {
					strEspacios += " ";
				}
				strUnidades = strEspacios + strUnidades;
				strEspacios = "";

				// Valor 12
				tam = 12 - strValor.length();
				for (int i = 0; i < tam; i++) {
					strEspacios += " ";
				}
				strValor = strEspacios + strValor;
				strEspacios = "";

				// Fecha 8
				tam = 8 - strFecha.length();
				for (int i = 0; i < tam; i++) {
					strFecha += " ";
				}

				// Documento 10
				tam = 10 - strDocumento.length();
				for (int i = 0; i < tam; i++) {
					strDocumento += " ";
				}

				// Centro Costo 15
				tam = 15 - strCentroCosto.length();
				for (int i = 0; i < tam; i++) {
					strCentroCosto += " ";
				}

				// Moneda 2
				tam = 2 - strMoneda.length();
				for (int i = 0; i < tam; i++) {
					strEspacios += " ";
				}
				strMoneda = strEspacios + strMoneda;
				strEspacios = "";

				// Descripcion 35
				tam = 35 - strDescripcion.length();
				for (int i = 0; i < tam; i++) {
					strDescripcion += " ";
				}

				strLineaArchivoPlano = strCarnet + strConcepto + strUnidades
						+ strValor + strFecha + strDocumento + strCentroCosto
						+ strMoneda + strDescripcion;

				printWriter.println(strLineaArchivoPlano);
			}

			printWriter.close();
			writer.close();
			fileWriter.close();

		} catch (Exception e) {
			log.error(e);
			
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.NOARCHIVOPLANO"));
		}
	}

}
