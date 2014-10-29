package geniar.html2pdf.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;

import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

/**
 * Filter to convert bad formed HTML to PDF.
 * 
 * @system. Html2PdfFilter
 * @author  Jaime Chavarriaga (Geniar)
 * @version 1.0
 * @requirement. 
 * @date.   05-Dic-2006
 */
public class Html2PdfFilter implements Filter{

	String contextName;
	
	String header = null;
	String footer = null;
	
	String prependFile = null;
	String appendFile = null;
	
	/**
	 * Inicializa el filtro.
	 * 
	 * @param config configuración del Filtro
	 * @throws ServletException excepción
	 */
	public void init(FilterConfig config) throws ServletException {
		System.out.println("Html2PdfFilter");
		System.out.println("(c) 2006-2009 Geniar Architect");
		System.out.println("===============================");
		
		try {
			contextName = config.getServletContext().getResource("/").getPath();
			contextName = contextName.substring(contextName.indexOf("/", 1));
			System.out.println("ContextName : " + contextName);
		} catch (Exception e) {
			contextName = "";
			System.out.println("ContextName : not detected");
		}

		try {
			prependFile = config.getInitParameter("prependFile");
			prependFile = prependFile != null
					? config.getServletContext().getRealPath(prependFile)
					: null;
			if (prependFile != null) {
				System.out.println("prepend file : " + prependFile);
			} else 
				System.out.println("prepend file : no using");

			appendFile = config.getInitParameter("appendFile");
			appendFile = appendFile != null
					? config.getServletContext().getRealPath(appendFile)
					: null;
			if (appendFile != null)
				System.out.println("append file : " + prependFile);
			else 
				System.out.println("append file : no using");

			header = config.getInitParameter("header");
			footer = config.getInitParameter("footer");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * Ejecuta el filtro. Enlaza la sesion local con la sesión compartida.
	 * 
	 * @param request petición del servlet
	 * @param response respuesta del servidor
	 * @param cadena cadena de responsabilidad
	 * @throws IOException excepción
	 * @throws ServletException excepción
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain cadena) 
		throws IOException, ServletException {
		try {		
			
			String basePath = request.getScheme() 
				+ "://" 
				+ request.getServerName() 
				+ ":" 
				+ request.getServerPort() 
				+ contextName;
			System.out.println("Html2PdfFilter : using basePath " + basePath);
			
			// crea el wrapper
			Html2PdfServletResponse wrapperRespuesta = new Html2PdfServletResponse();
			
			// procesa la petición web 
			cadena.doFilter(request, wrapperRespuesta);
						
			// obtiene el resultado
			String resultado = wrapperRespuesta.getOutputStreamContent();
			resultado = removeScript(resultado);
			resultado = removeEntityReference(resultado);
						
			Document xhtmlContent;
			
			// crea un documento XML usando Tidy (para HTML mal formados)
			Tidy tidy = getTidy();
			xhtmlContent = tidy.parseDOM(new ByteArrayInputStream(resultado.getBytes()), null);
			
			// crea un renderer de XHTML			
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(xhtmlContent, basePath);			
			renderer.layout();
			   
			// envia el contenido al navegador
			response.setContentType("application/pdf");
			try {
				((HttpServletResponse) response).setHeader("pragma", "no-cache");
				((HttpServletResponse) response).addHeader(
						"cache-control", 
						"must-revalidate, post-check=0, pre-check=");
				((HttpServletResponse) response).addHeader("cache-control", "private");
				((HttpServletResponse) response).setHeader("content-transfer-encoding", "binary");				
				((HttpServletResponse) response).setHeader("expires", "0");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
						
			// genera el pdf en un output stream en memoria
			ByteArrayOutputStream pdfAntesModificarOutputStream = new ByteArrayOutputStream();
			renderer.createPDF(pdfAntesModificarOutputStream);
			pdfAntesModificarOutputStream.flush();
			PdfReader reader = new PdfReader(pdfAntesModificarOutputStream.toByteArray());

			ByteArrayOutputStream pdfModificadoOutputStream = new ByteArrayOutputStream();
			PdfStamper stamp = new PdfStamper(reader, pdfModificadoOutputStream);
			
			// agrega notas de copyright al pdf
			if (header != null || footer != null) {
				
				int n = reader.getNumberOfPages();
				
				for (int i=0; i<n; i++) {
	
					BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, BaseFont.EMBEDDED);
		
					// PdfContentByte under = stamp.getUnderContent(n);
					PdfContentByte over = stamp.getOverContent(i);
					
					if (header != null) {
						System.out.println("pendiente de definir impresion de encabezados");
					}
					
					/*if (footer != null) {
						over.beginText();
						over.setFontAndSize(bf, 12);
						over.setTextMatrix(30, 30);			
						over.showText(footer);
						// over.setFontAndSize(bf, 32);
						over.endText();
					}*/
				}
				
			}

			if (appendFile != null) {
				
				int n = reader.getNumberOfPages();
				
				// adding an extra page - at beginning
				stamp.insertPage(n + 1, PageSize.A4);

				// adding a page from prepend file
				PdfReader reader2 = new PdfReader(appendFile);
				PdfContentByte under = stamp.getUnderContent(n + 1);
				under.addTemplate(stamp.getImportedPage(reader2, 1), 1, 0, 0, 1, 0, 0);
			}

			if (prependFile != null) {
				// adding an extra page - at beginning
				stamp.insertPage(1, PageSize.A4);

				// adding a page from prepend file
				PdfReader reader2 = new PdfReader(prependFile);
				PdfContentByte under = stamp.getUnderContent(1);
				under.addTemplate(stamp.getImportedPage(reader2, 1), 1, 0, 0, 1, 0, 0);
			}			
			
			// closing PdfStamper will generate the new PDF file
			stamp.close();

			// --
			
			// gets the output stream
			ServletOutputStream browserStream = response.getOutputStream();
			
			System.out.println("enviando salida al browser");
			// send the pdf to the output stream
//			renderer.createPDF(browserStream);
			
			pdfModificadoOutputStream.flush();
			browserStream.write(pdfModificadoOutputStream.toByteArray());
			browserStream.flush();
			
//			browserStream.write(pdfAntesModificarOutputStream.getBinaryContent());
			
			
		} 
		catch (Exception e) {		
			// despliegua el mensaje de error en el navegador
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println();
			
			out.println("<html><head>");
			out.println("<style>");
			out.println("<!--");
			out.println("H1 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:22px;}");
			out.println("H2 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:16px;}");
			out.println("H3 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:14px;}"); 
			out.println("BODY {font-family:Tahoma,Arial,sans-serif;color:black;background-color:white;} ");
			out.println("B {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;} ");
			out.println("P {font-family:Tahoma,Arial,sans-serif;background:white;color:black;font-size:12px;}");
			out.println("A {color : black;}");
			out.println("A.name {color : black;}");
			out.println("HR {color : #525D76;}-->");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Error en el filtro Html2Pdf</h1>");
			out.println("<HR size=\"1\" noshade=\"noshade\">");
			out.println("<p><b>Error</b> " + e.getMessage() + "</p>");
			out.println("<p><b>StackTrace</b><pre>");
			e.printStackTrace(out);
			out.println("</pre></p>");
			out.println("<HR size=\"1\" noshade=\"noshade\"><h3>Geniar Html2Pdf 1.0</h3>");
			out.println("</body></html>");

			out.flush();
			out.close();
			
			// muestra el error en consola
			System.out.println("Html2PdfFilter error: " + e.getMessage());
			e.printStackTrace();
		}
		finally {	
			// cadena.doFilter(request, response);
		}					
	}

	/**
	 * Destruye el filtro.
	 */
	public void destroy() {
		
	}

	// --
	
	private Tidy tidy = null;
	
	private Tidy getTidy() {
	
		if (tidy == null) {
			tidy = new Tidy();
//			tidy.setWord2000(true);
//			tidy.setNumEntities(true);
			tidy.setInputEncoding("ISO-8859-1");
			tidy.setXHTML(true);
			tidy.setQuiet(false);
			tidy.setShowWarnings(true);
			tidy.setXmlOut(true);
			tidy.setFixComments(true);
			tidy.setEscapeCdata(true);
			tidy.setMakeBare(true);
			tidy.setMakeClean(true);
			tidy.setFixBackslash(true);
			tidy.setIndentContent(true);
			tidy.setLogicalEmphasis(true);
			tidy.setJoinClasses(true);
			tidy.setJoinStyles(true);
			tidy.setDocType("strict");
		}
		return tidy;
		
	}
	
	// --
	
	private String removeEntityReference(String documentHtml) {
		
		return documentHtml
			.replaceAll("&nbsp;", "&#160;")
			.replaceAll("&quot;", "&#34;")
			.replaceAll("&apos;", "&#39;")
			.replaceAll("&amp;", "&#38;")
			.replaceAll("&lt;", "&#60;")
			.replaceAll("&gt;", "&#62;")
			.replaceAll("&iexcl;", "&#161;")
			.replaceAll("&cent;", "&#162;")
			.replaceAll("&pound;", "&#163;")
			.replaceAll("&curren;", "&#164;")
			.replaceAll("&yen;", "&#165;")
			.replaceAll("&brvbar;", "&#166;")
			.replaceAll("&sect;", "&#167;")
			.replaceAll("&uml;", "&#168;")
			.replaceAll("&copy;", "&#169;")
			.replaceAll("&ordf;", "&#170;")
			.replaceAll("&laquo;", "&#171;")
			.replaceAll("&not;", "&#172;")
			.replaceAll("&shy;", "&#173;")
			.replaceAll("&reg;", "&#174;")
			.replaceAll("&macr;", "&#175;")
			.replaceAll("&deg;", "&#176;")
			.replaceAll("&plusmn;", "&#177;")
			.replaceAll("&sup2;", "&#178;")
			.replaceAll("&sup3;", "&#179;")
			.replaceAll("&acute;", "&#180;")
			.replaceAll("&micro;", "&#181;")
			.replaceAll("&para;", "&#182;")
			.replaceAll("&middot;", "&#183;")
			.replaceAll("&cedil;", "&#184;")
			.replaceAll("&sup1;", "&#185;")
			.replaceAll("&ordm;", "&#186;")
			.replaceAll("&raquo;", "&#187;")
			.replaceAll("&frac14;", "&#188;")
			.replaceAll("&frac12;", "&#189;")
			.replaceAll("&frac34;", "&#190;")
			.replaceAll("&iquest;", "&#191;")
			.replaceAll("&times;", "&#215;")
			.replaceAll("&divide;", "&#247;")
			.replaceAll("&Agrave;", "&#192;")
			.replaceAll("&Aacute;", "&#193;")
			.replaceAll("&Acirc;", "&#194;")
			.replaceAll("&Atilde;", "&#195;")
			.replaceAll("&Auml;", "&#196;")
			.replaceAll("&Aring;", "&#197;")
			.replaceAll("&AElig;", "&#198;")
			.replaceAll("&Ccedil;", "&#199;")
			.replaceAll("&Egrave;", "&#200;")
			.replaceAll("&Eacute;", "&#201;")
			.replaceAll("&Ecirc;", "&#202;")
			.replaceAll("&Euml;", "&#203;")
			.replaceAll("&Igrave;", "&#204;")
			.replaceAll("&Iacute;", "&#205;")
			.replaceAll("&Icirc;", "&#206;")
			.replaceAll("&Iuml;", "&#207;")
			.replaceAll("&ETH;", "&#208;")
			.replaceAll("&Ntilde;", "&#209;")
			.replaceAll("&Ograve;", "&#210;")
			.replaceAll("&Oacute;", "&#211;")
			.replaceAll("&Ocirc;", "&#212;")
			.replaceAll("&Otilde;", "&#213;")
			.replaceAll("&Ouml;", "&#214;")
			.replaceAll("&Oslash;", "&#216;")
			.replaceAll("&Ugrave;", "&#217;")
			.replaceAll("&Uacute;", "&#218;")
			.replaceAll("&Ucirc;", "&#219;")
			.replaceAll("&Uuml;", "&#220;")
			.replaceAll("&Yacute;", "&#221;")
			.replaceAll("&THORN;", "&#222;")
			.replaceAll("&szlig;", "&#223;")
			.replaceAll("&agrave;", "&#224;")
			.replaceAll("&aacute;", "&#225;")
			.replaceAll("&acirc;", "&#226;")
			.replaceAll("&atilde;", "&#227;")
			.replaceAll("&auml;", "&#228;")
			.replaceAll("&aring;", "&#229;")
			.replaceAll("&aelig;", "&#230;")
			.replaceAll("&ccedil;", "&#231;")
			.replaceAll("&egrave;", "&#232;")
			.replaceAll("&eacute;", "&#233;")
			.replaceAll("&ecirc;", "&#234;")
			.replaceAll("&euml;", "&#235;")
			.replaceAll("&igrave;", "&#236;")
			.replaceAll("&iacute;", "&#237;")
			.replaceAll("&icirc;", "&#238;")
			.replaceAll("&iuml;", "&#239;")
			.replaceAll("&Eth;", "&#240;")
			.replaceAll("&ntilde;", "&#241;")
			.replaceAll("&ograve;", "&#242;")
			.replaceAll("&oacute;", "&#243;")
			.replaceAll("&ocirc;", "&#244;")
			.replaceAll("&otilde;", "&#245;")
			.replaceAll("&ouml;", "&#246;")
			.replaceAll("&oslash;", "&#248;")
			.replaceAll("&ugrave;", "&#249;")
			.replaceAll("&uacute;", "&#250;")
			.replaceAll("&ucirc;", "&#251;")
			.replaceAll("&uuml;", "&#252;")
			.replaceAll("&yacute;", "&#253;")
			.replaceAll("&thorn;", "&#254;")
			.replaceAll("&yuml;", "&#255;")
			.replaceAll("<o:p>", "<p>")
			.replaceAll("</o:p>", "</p>"); 		
	}
	
	@SuppressWarnings("unchecked")
	private static String removeScript(String documentHtml) {
		final List toRemove = new ArrayList();
		final Pattern p = Pattern.compile("(<script\\s*)");
		final Matcher m = p.matcher(documentHtml);
		int start = 0;
		while (m.find(start)) {
			final int is = m.start();
			int ie = m.start();
			while (ie < documentHtml.length()) {
				if (documentHtml.substring(ie).startsWith("</script>")) {
					ie = ie + 9;
					break;
				} else {
					ie++;
				}
			}
			start = ie + 1;
			toRemove.add(documentHtml.substring(is, ie));
			if (start >= documentHtml.length()) {
				break;
			}
		}
		for (int i = 0; i < toRemove.size(); i++) {
			final String rem = (String) toRemove.get(i);
			final int index = documentHtml.indexOf(rem);
			documentHtml = documentHtml.substring(0, index) + documentHtml.substring(index + rem.length());
		}
		return documentHtml;
	}

}
