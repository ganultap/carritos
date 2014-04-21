package geniar.html2pdf.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 * Filter to convert well formed HTML (XHTML) to PDF.
 * 
 * @system. Html2PdfFilter
 * @author  Jaime Chavarriaga (Geniar)
 * @version 1.0
 * @requirement. 
 * @date.   05-Dic-2006
 * 
 * @modifications.
 * [*] Requerimiento 15173. Modificado por José Manuel Martinez De Valdenebro en la fecha 06-Dic-2006.
 * Coloca el basepath de la aplicación para crear el documento XML y poder utilizar referencias CSS y de
 * imagenes en los JSP que se convierten a PDF. Cuando se crea el documento XML se le dice que los datos
 * van a estar en encoding UTF-8 (encoding de los XML). <br><br>
 */
public class Xhtml2PdfFilter implements Filter{

	String contextName;
	
	/**
	 * Inicializa el filtro
	 * @param config configuración del Filtro
	 * @throws ServletException exception
	 */
	public void init(FilterConfig config) throws ServletException {
		System.out.println("Html2PdfFilter");
		System.out.println("(c) 2006-2008 Geniar Architect");
		System.out.println("===============================");
		
		try {
			contextName = config.getServletContext().getResource("/").getPath();
			contextName = contextName.substring(contextName.indexOf("/", 1));
			System.out.println("using ContextName " + contextName);
		} catch (Exception e) {
			contextName = "";
			System.out.println("ContextName not detected");
		}
	}

	/**
	 * ejecuta el filtro. Enlaza la sesion local con la sesión compartida
	 * @param request petición del servlet
	 * @param response respuesta del servidor
	 * @param cadena cadena de responsabilidad
	 * @throws IOException exception 
	 * @throws ServletException exception
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain cadena) 
		throws IOException, ServletException 	
	{
		try {
			// crea el basepath para poder utilizar referencias en los JSP y HTML
			String basepath = request.getScheme() 
				+ "://" 
				+ request.getServerName() 
				+ ":" 
				+ request.getServerPort() 
				+ contextName;			

			// crea el wrapper
			Html2PdfServletResponse wrapperRespuesta = new Html2PdfServletResponse();
			
			// procesa la petición web 
			cadena.doFilter(request, wrapperRespuesta);
			
			// obtiene el resultado
			String resultado = wrapperRespuesta.getOutputStreamContent();
			
			// crea un documento XML
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document xhtmlContent = documentBuilder.parse(new ByteArrayInputStream(resultado.getBytes("UTF-8")));		

			// crea un renderer de XHTML
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(xhtmlContent, basepath);
			renderer.layout();
			   
			// envia el contenido al navegador
			response.setContentType("application/pdf");			
			ServletOutputStream browserStream = response.getOutputStream();
			renderer.createPDF(browserStream);			
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
			out.println("<h1>Error en el filtro XHtml2Pdf</h1>");
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
			System.out.println("Xhtml2PdfFilter error: " + e.getMessage());
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
}
