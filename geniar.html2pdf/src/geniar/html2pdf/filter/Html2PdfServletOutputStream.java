package geniar.html2pdf.filter;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;

/**
 * Mock implementation of ServletOutputStream.
 * 
 * @system. Html2PdfFilter
 * @author  Jaime Chavarriaga
 * @version 1.0
 * @requirement. 
 * @date.   05-Dic-2006
 */
public class Html2PdfServletOutputStream extends ServletOutputStream
{
	private PrintWriter printStream; 	
    private ByteArrayOutputStream buffer;
    private String encoding;
    
    /**
     * Set default output stream encoding.
     */
    public Html2PdfServletOutputStream()
    {
        this("ISO-8859-1");
    }
    
    /**
     * Set output stream encoding.
     * @param encoding encoding
     */
    public Html2PdfServletOutputStream(String encoding)
    {
        buffer = new ByteArrayOutputStream();
        printStream = new PrintWriter(buffer, true);
        this.encoding = encoding;
    }
    
    /**
     * Set class encoding.
     * @param encoding encoding
     */
    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }
    
    /** 
     * Print to buffer.
     * @see java.io.OutputStream#write(int)
     */
    public void write(int value) throws IOException
    {
        System.out.println("imprimiendo" + value + " en " + buffer.size());
        buffer.write(value);
    }
    
    /**
     * Return stream content.
     * @return stream content.
     */
    public String getContent()
    {
        try
        {
        	printStream.flush();
            buffer.flush();
            return buffer.toString(encoding);
        } 
        catch(IOException exc)
        {
        	System.out.println("error en outputstream.getcontent");
            throw new RuntimeException(exc);
        }
    }
    
    /**
     * Return binary content.
     * @return binary content.
     */
    public byte[] getBinaryContent()
    {
        try
        {
            buffer.flush();
            return buffer.toByteArray();
        } 
        catch(IOException exc)
        {
        	System.out.println("error en outputstream.getbinarycontent");
        	throw new RuntimeException(exc);
        }
    }
    
    /**
     * Clear buffer content. 
     */
    public void clearContent()
    {
        buffer.reset(); 
    }

	/** 
	 * Overrides close.
	 * @throws IOException exception
	 */
	public void close() throws IOException {}

	/** 
	 * Overrides flush
	 * @throws IOException excepction
	 */
	public void flush() throws IOException {
		System.out.println("Hola");
	}

	/**
	 * Return hashcode.
	 * @return hashcode
	 */
	public int hashCode() {
		return buffer.hashCode();
	}

	/**
	 * Print boolean to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */
	public void print(boolean arg0) throws IOException {
		printStream.print(arg0);
	}

	/**
	 * Print char to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */
	public void print(char arg0) throws IOException {
		printStream.print(arg0);
	}

	/**
	 * Print double to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */
	public void print(double arg0) throws IOException {
		printStream.print(arg0);
	}

	/**
	 * Print float to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */
	public void print(float arg0) throws IOException {
		printStream.print(arg0);
	}

	/**
	 * Print int to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */	
	public void print(int arg0) throws IOException {
		printStream.print(arg0);
	}

	/**
	 * Print long to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */	
	public void print(long arg0) throws IOException {
		printStream.print(arg0);
	}

	/**
	 * Print string to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */	
	public void print(String arg0) throws IOException {
		printStream.print(arg0);
	}

	/**
	 * Overrides println.
	 * @throws IOException exception
	 */	
	public void println() throws IOException {
		printStream.println();
	}

	/**
	 * Println boolean to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */
	public void println(boolean arg0) throws IOException {
		printStream.println(arg0);
	}

	/**
	 * Println char to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */	
	public void println(char arg0) throws IOException {
		printStream.println(arg0);
	}

	/**
	 * Println double to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */	
	public void println(double arg0) throws IOException {
		printStream.println(arg0);
	}

	/**
	 * Println float to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */	
	public void println(float arg0) throws IOException {
		printStream.println(arg0);
	}

	/**
	 * Println int to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */	
	public void println(int arg0) throws IOException {
		printStream.println(arg0);
	}

	/**
	 * Println long to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */	
	public void println(long arg0) throws IOException {
		printStream.println(arg0);
	}

	/**
	 * Println string to stream.
	 * @param arg0 argument to be printed
	 * @throws IOException exception
	 */	
	public void println(String arg0) throws IOException {
		printStream.println(arg0);
	}

	/**
	 * Convert buffer to string.
	 * @return buffer as string
	 */	
	public String toString() {
		return buffer.toString();
	}

	/** 
	 * Write to buffer.
	 * @param arg0 argument
	 * @param arg1 argument
	 * @param arg2 argument
	 * @throws IOException exception
	 */
	public void write(byte[] arg0, int arg1, int arg2) throws IOException {
		// System.out.println("no se puede imprimir - byte 2 args");
		buffer.write(arg0, arg1, arg2);
	}

	/** 
	 * Write to buffer.
	 * @param arg0 argument
	 * @throws IOException exception
	 */	
	public void write(byte[] arg0) throws IOException {
		// System.out.println("no se puede imprimir - byte array");
		buffer.write(arg0);
	}
    
	/**
	 * Print writer.
	 * @return printstream
	 */	
	public PrintWriter getWriter() {
		return printStream;
	}
}
