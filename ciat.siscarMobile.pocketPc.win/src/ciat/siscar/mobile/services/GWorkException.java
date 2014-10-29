/**
 * GWorkException.java
 *
 * Generated on Tue Feb 03 00:17:51 GMT 2009
 * Generated from http://localhost:8082/siscar.services/SiscarService?wsdl
 */

package ciat.siscar.mobile.services;

public class GWorkException extends java.lang.Exception{
    private java.lang.String message;

   public GWorkException()
   {
      super();
   }

   public GWorkException(String s)
   {
      super(s);
   }

    public java.lang.String getMessage() {
        return message;
    }

    public void setMessage(java.lang.String message) {
        this.message = message;
    }

}
