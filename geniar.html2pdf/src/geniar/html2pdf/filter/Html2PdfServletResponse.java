package geniar.html2pdf.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Mock implementation of ServletResponse.
 * 
 * @system. Html2PdfFilter
 * @author  Jaime Chavarriaga
 * @version 1.0
 * @requirement. 
 * @date.   05-Dic-2006
 */
public class Html2PdfServletResponse implements HttpServletResponse
{
    private PrintWriter writer;
    private Html2PdfServletOutputStream outputStream;
    private Map<String, List> headers;
    private Locale locale;
    private String characterEncoding;
    private int bufferSize;
    private boolean wasErrorSent;
    private boolean wasRedirectSent;
    private int errorCode;
    private int statusCode;
    private List<Cookie> cookies;

    /**
     * Date format header.
     */
    public final String DATE_FORMAT_HEADER = "EEE, d MMM yyyy HH:mm:ss Z"; 
    
    /**
     * Set default data.
     */
    public Html2PdfServletResponse ()
    {
        headers = new HashMap<String, List>();
        characterEncoding = "ISO-8859-1";
        bufferSize = 8192;
        wasErrorSent = false;
        wasRedirectSent = false;
        errorCode = SC_OK;
        statusCode = SC_OK;
        cookies = new ArrayList<Cookie>();
        outputStream = new Html2PdfServletOutputStream(characterEncoding);
        
        writer = outputStream.getWriter();        
    }

    /**
     * Return encode URL.
     * @param url URL
     * @return encode URL
     */
    public String encodeURL(String url)
    {
        return url;
    }

    /**
     * Return encode redirect url.
     * @param url url
     * @return encode url
     */
    public String encodeRedirectUrl(String url)
    {
        return url;
    }

    /**
     * Return encode URL.
     * @param url URL
     * @return encode URL
     */    
    public String encodeRedirectURL(String url)
    {
        return url;
    }

    /**
     * Return encode redirect url.
     * @param url url
     * @return encode url
     */    
    public String encodeUrl(String url)
    {
        return url;
    }

	/**
	 * Print writer.
	 * @return printstream
	 * @throws IOException exception
	 */	    
    public PrintWriter getWriter() throws IOException
    {
        return writer;
    }

	/**
	 * Return output stream.
	 * @return output stream
	 * @throws IOException exception 
	 */    
    public ServletOutputStream getOutputStream() throws IOException
    {
        return outputStream;
    }

	/**
	 * Return output stream content.
	 * @return output stream content
	 */      
    public String getOutputStreamContent()
    {
        return outputStream.getContent();
    }

    /**
     * Add cookie.
     * @param cookie cookie 
     */
    public void addCookie(Cookie cookie)
    {
        cookies.add(cookie);
    }

    /**
     * Add date header.
     * @param key key
     * @param date date 
     */    
    public void addDateHeader(String key, long date)
    {
        Date dateValue = new Date(date);
        String dateString = new SimpleDateFormat(DATE_FORMAT_HEADER, Locale.US).format(dateValue);
        addHeader(key, dateString);
    }

    /**
     * Add header.
     * @param key key
     * @param value value 
     */        
    @SuppressWarnings("unchecked")
	public void addHeader(String key, String value)
    {
        List valueList = headers.get(key);
        if (null == valueList)
        {
            valueList = new ArrayList();
            headers.put(key, valueList);
        }
        valueList.add(value);
    }

    /**
     * Add int header.
     * @param key key
     * @param value value 
     */        
    public void addIntHeader(String key, int value)
    {
        String stringValue = new Integer(value).toString();
        addHeader(key, stringValue);
    }

    /**
     * Return key header.
     * @param key key
     * @return key header 
     */     
    public boolean containsHeader(String key)
    {
        return headers.containsKey(key);
    }

    /**
     * Send error.
     * @param code code
     * @param message message
     * @throws IOException exception 
     */            
    public void sendError(int code, String message) throws IOException
    {
        errorCode = code;
        wasErrorSent = true;
    }

    /**
     * Send error.
     * @param code code
     * @throws IOException exception 
     */                
    public void sendError(int code) throws IOException
    {
        errorCode = code;
        wasErrorSent = true;
    }

    /**
     * Send redirect.
     * @param location location
     * @throws IOException exception 
     */                
    public void sendRedirect(String location) throws IOException
    {
        setHeader("Location", location);
        wasRedirectSent = true;
    }

    /**
     * Add date header.
     * @param key key
     * @param date date 
     */            
    public void setDateHeader(String key, long date)
    {
        Date dateValue = new Date(date);
        String dateString = DateFormat.getDateInstance().format(dateValue);
        setHeader(key, dateString);
    }

    /**
     * Add date header.
     * @param key key
     * @param value value 
     */        
    @SuppressWarnings("unchecked")
	public void setHeader(String key, String value)
    {
        List valueList = new ArrayList();
        headers.put(key, valueList);
        valueList.add(value);
    }

    /**
     * Set int header.
     * @param key key
     * @param value value 
     */        
    public void setIntHeader(String key, int value)
    {
        String stringValue = new Integer(value).toString();
        setHeader(key, stringValue);
    }

    /**
     * Set status.
     * @param code code
     * @param message message
     */     
    public void setStatus(int code, String message)
    {
        statusCode = code;
    }

    /**
     * Set status.
     * @param code code
     */         
    public void setStatus(int code)
    {
        statusCode = code;
    }

    /**
     * Flush buffer
     * @throws IOException exception
     */         
    public void flushBuffer() throws IOException
    {
        writer.flush();
        outputStream.flush();
    }

    /**
     * Return buffer size.
     * @return buffer size 
     */     
    public int getBufferSize()
    {
        return bufferSize;
    }

    /**
     * Return encoding.
     * @return encoding 
     */     
    public String getCharacterEncoding()
    {
        return characterEncoding;
    }

    /**
     * Set character encoding.
     * @param encoding encoding
     */                 
    public void setCharacterEncoding(String encoding)
    {
        characterEncoding = encoding;
        outputStream.setEncoding(encoding);
    }

    /**
     * Return locale.
     * @return locale 
     */     
    public Locale getLocale()
    {
        return locale;
    }

    /**
     * Set locale.
     * @param locale locale
     */                    
    public void setLocale(Locale locale)
    {
        this.locale = locale;
    }

    /**
     * Return commited.
     * @return false 
     */              
    public boolean isCommitted()
    {
        return false;
    }

    /**
     * Reset buffer.
     */                        
    public void reset()
    {
        headers.clear();
        resetBuffer();
    }

    /**
     * Reset output stream content.
     */     
    public void resetBuffer()
    {
        outputStream.clearContent();
    }

    /**
     * Reset buffer.
     * @param size buffer size
     */      
    public void setBufferSize(int size)
    {
        bufferSize = size;
    }

    /**
     * Set content length.
     * @param length content length
     */          
    public void setContentLength(int length)
    {
        setIntHeader("Content-Length", length);
    }

    /**
     * Return header content type
     * @return header content type
     */     
    public String getContentType()
    {
        return getHeader("Content-Type");
    }

    /**
     * Set content type.
     * @param type content type
     */     
    public void setContentType(String type)
    {
        setHeader("Content-Type", type);
    }

    /**
     * Return header as List.
     * @param key key
     * @return header 
     */              
    public List getHeaderList(String key)
    {
        return headers.get(key);
    }

    /**
     * Return header as string.
     * @param key key
     * @return header 
     */          
    public String getHeader(String key)
    {
        List list = getHeaderList(key);
        if(null == list || 0 == list.size()) return null;
        return (String)list.get(0);
    }
    
    /**
     * Return statusCode.
     * @return statusCode 
     */      
    public int getStatusCode()
    {
        return statusCode;
    }
    
    /**
     * Return errorCode.
     * @return errorCode 
     */      
    public int getErrorCode()
    {
        return errorCode;
    }

    /**
     * Return cookies.
     * @return cookies 
     */      
    public List getCookies()
    {
        return cookies;
    }

    /**
     * Return wasErrorSent.
     * @return wasErrorSent 
     */             
    public boolean wasErrorSent()
    {
        return wasErrorSent;
    }

    /**
     * Return wasRedirectSent.
     * @return wasRedirectSent 
     */         
    public boolean wasRedirectSent()
    {
        return wasRedirectSent;
    }
}