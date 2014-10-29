/**
 * ValidacionCentroCosto.java
 *
 * Generated on Wed Jan 21 01:31:03 GMT 2009
 * Generated from http://localhost:8080/siscar.services/SiscarService?wsdl
 */

package ciat.siscar.mobile.services;

public class ValidacionCentroCosto {
    private java.lang.String nombreCentroCosto;

    public ValidacionCentroCosto() {
    }

    public java.lang.String getNombreCentroCosto() {
        return nombreCentroCosto;
    }

    public void setNombreCentroCosto(java.lang.String nombreCentroCosto) {
        this.nombreCentroCosto = nombreCentroCosto;
    }

   public static javax.microedition.xml.rpc.Type populateTypeMap( javax.xml.namespace.QName targetName, java.util.Hashtable typeMap )
   {
      if (typeMap == null) {
         typeMap = new java.util.Hashtable();
      }

      String complexTypeName = "ciat_siscar_mobile_services_ValidacionCentroCosto";

      if (typeMap.containsKey( complexTypeName )) {
         return (javax.microedition.xml.rpc.Type)typeMap.get( complexTypeName ); 
      }

      javax.microedition.xml.rpc.ComplexType ciat_siscar_mobile_services_ValidacionCentroCosto = new javax.microedition.xml.rpc.ComplexType();
      ciat_siscar_mobile_services_ValidacionCentroCosto.elements = new javax.microedition.xml.rpc.Element[1];
          {
          javax.xml.namespace.QName __QNAME_nombreCentroCosto = new javax.xml.namespace.QName("", "nombreCentroCosto");
          javax.microedition.xml.rpc.Element __ELEM_nombreCentroCosto = new javax.microedition.xml.rpc.Element (__QNAME_nombreCentroCosto, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_ValidacionCentroCosto.elements[0] = __ELEM_nombreCentroCosto;
          }

      typeMap.put(complexTypeName, ciat_siscar_mobile_services_ValidacionCentroCosto);
      return ciat_siscar_mobile_services_ValidacionCentroCosto;
   }

   private boolean recursionCheck = false;
   public Object serialize() throws javax.xml.rpc.JAXRPCException
   {
      if (recursionCheck) {
         throw new javax.xml.rpc.JAXRPCException( "An instance of " + getClass().getName() + " exists in a circular reference.  Serialization of this instance will fail." );
      } else {
         recursionCheck = true;
      }
      Object[] objs = new Object[] {
          getNombreCentroCosto(),
      };
      recursionCheck = false;
      return (Object)objs;
	}


   public void deserialize( Object value )
   {
      Object[] values = (Object[])value;
      if ((values != null) && (values.length == 1)) {
         setNombreCentroCosto( (String)values[0] );
      }
   }

}
