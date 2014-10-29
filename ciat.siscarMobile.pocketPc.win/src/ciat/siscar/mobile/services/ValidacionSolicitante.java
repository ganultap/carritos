/**
 * ValidacionSolicitante.java
 *
 * Generated on Tue Feb 03 00:17:51 GMT 2009
 * Generated from http://localhost:8082/siscar.services/SiscarService?wsdl
 */

package ciat.siscar.mobile.services;

public class ValidacionSolicitante {
    private java.lang.String nombre;
    private java.lang.String tarifa;

    public ValidacionSolicitante() {
    }

    public java.lang.String getNombre() {
        return nombre;
    }

    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    public java.lang.String getTarifa() {
        return tarifa;
    }

    public void setTarifa(java.lang.String tarifa) {
        this.tarifa = tarifa;
    }

   public static javax.microedition.xml.rpc.Type populateTypeMap( javax.xml.namespace.QName targetName, java.util.Hashtable typeMap )
   {
      if (typeMap == null) {
         typeMap = new java.util.Hashtable();
      }

      String complexTypeName = "ciat_siscar_mobile_services_ValidacionSolicitante";

      if (typeMap.containsKey( complexTypeName )) {
         return (javax.microedition.xml.rpc.Type)typeMap.get( complexTypeName ); 
      }

      javax.microedition.xml.rpc.ComplexType ciat_siscar_mobile_services_ValidacionSolicitante = new javax.microedition.xml.rpc.ComplexType();
      ciat_siscar_mobile_services_ValidacionSolicitante.elements = new javax.microedition.xml.rpc.Element[2];
          {
          javax.xml.namespace.QName __QNAME_nombre = new javax.xml.namespace.QName("", "nombre");
          javax.microedition.xml.rpc.Element __ELEM_nombre = new javax.microedition.xml.rpc.Element (__QNAME_nombre, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_ValidacionSolicitante.elements[0] = __ELEM_nombre;
          }

          {
          javax.xml.namespace.QName __QNAME_tarifa = new javax.xml.namespace.QName("", "tarifa");
          javax.microedition.xml.rpc.Element __ELEM_tarifa = new javax.microedition.xml.rpc.Element (__QNAME_tarifa, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_ValidacionSolicitante.elements[1] = __ELEM_tarifa;
          }

      typeMap.put(complexTypeName, ciat_siscar_mobile_services_ValidacionSolicitante);
      return ciat_siscar_mobile_services_ValidacionSolicitante;
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
          getNombre(),
          getTarifa(),
      };
      recursionCheck = false;
      return (Object)objs;
	}


   public void deserialize( Object value )
   {
      Object[] values = (Object[])value;
      if ((values != null) && (values.length == 2)) {
         setNombre( (String)values[0] );
         setTarifa( (String)values[1] );
      }
   }

}
