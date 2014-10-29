package geniar.siscar.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Julian Andres Marin, Richard Gómez - GeniarArq S.A
 * @version 1.0 
 * Descripción : Clase de Utilerias para el manejo de archivos
 * 
 */
public class CreateFile {

	private static final Log log = LogFactory.getLog(CreateFile.class);

	/**
	 * Constructor of the class
	 */
	public CreateFile() {
		super();
	}

	/**
	 * Create a file in the specific path, with the given name, extention and
	 * text.
	 * 
	 * @param strFilePath
	 * @param strFileName
	 * @param strExtention
	 * @param strText
	 * @throws IOException
	 */
	public static String createFile(String strPath, String strFileName, String strExtention, String strText) throws IOException {
		try {
			
			String strPathXhtml = strPath + strFileName + strExtention;
			// Verified that parameters are not empty
			if (strPath.trim().length() <= 0 || strFileName.trim().length() <= 0 || strText.trim().length() <= 0 || strExtention.trim().length() <= 0) {
				throw new IOException("You must specified the file path, file name " + "extention and body (text) of the file.");
			}
			// complete the fileName plus extention
			strFileName = strFileName + strExtention;
			// create file
			File file = new File(strPath, strFileName);
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);
			PrintWriter out = new PrintWriter(bw);
			// write into file
			out.println(strText);
			// close writers
			out.close();
			bw.close();
			writer.close();
			return strPathXhtml;
		} catch (IOException e) {
			log.error("Error createFile", e);
		}
		return null;
	}

	/**
	 * Create a file in the default path (root folder), with the given name,
	 * extention and text.
	 * 
	 * @param strFileName
	 * @param strExtention
	 * @param strText
	 * @throws IOException
	 */
	public static void createFile(String strFileName, String strExtention, String strText) throws IOException {
		try {
			// Verified that parameters are not empty
			if (strFileName.trim().length() <= 0 || strText.trim().length() <= 0 || strExtention.trim().length() <= 0) {
				throw new IOException("You must specified the file name " + "extention and body (text) of the file.");
			}
			// complete the fileName plus extention
			strFileName = strFileName + "." + strExtention;
			// create file
			File file = new File(strFileName);
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);
			PrintWriter out = new PrintWriter(bw);
			// write into file
			out.println(strText);
			// close writers
			out.close();
			bw.close();
			writer.close();
		} catch (IOException e) {
			log.error("Error createFile", e);
		}
	}

	/**
	 * Create a folder in the specified path
	 * 
	 * @param path
	 * @param nameFolder
	 * @return
	 */
	public static boolean createFolder(String strPath, String strFolderName) {
		boolean retorno = false;
		try {
			strPath = strPath + strFolderName;
			File myDirectory = new File(strPath);
			myDirectory.mkdirs();
			retorno = true;
		} catch (Exception e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
		return retorno;
	}

	/**
	 * Copy an file from source to target. The target file must be exist
	 * 
	 * @param strSource
	 * @param strTarget
	 */
	public static void copy(String strSource, String strTarget) {
		FileInputStream fIn = null;
		FileOutputStream fOut = null;
		byte[] b;
		int l = 0;

		try {
			fIn = new FileInputStream(strSource);
			fOut = new FileOutputStream(strTarget);

			b = new byte[1024];
			while ((l = fIn.read(b)) > 0) {
				fOut.write(b, 0, l);
			}

			fOut.close();
			fIn.close();
		} catch (FileNotFoundException fnfe) {
			log.error("Error createFolder", fnfe);
		} catch (IOException ioe) {
			log.error("Error createFolder", ioe);
		}
	}

	public static void deleteFile(String routeFile) {

		File file = new File(routeFile);
		if (file.exists())
			file.delete();
	}

	public static void createFolder(File theFile) {
		try {
			theFile.mkdirs();
		} catch (Exception e) {
			log.error("Error createFolder", e);
		}
	}
}
