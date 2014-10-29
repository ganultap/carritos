/**
 * VoEmployee.java
 *
 * Generated on Tue Feb 03 00:17:51 GMT 2009
 * Generated from http://localhost:8082/siscar.services/SiscarService?wsdl
 */

package ciat.siscar.mobile.services;

public class VoEmployee {
    private java.lang.String empApellido;
    private java.lang.String empNombre;

    public VoEmployee() {
    }

    public java.lang.String getEmpApellido() {
        return empApellido;
    }

    public void setEmpApellido(java.lang.String empApellido) {
        this.empApellido = empApellido;
    }

    public java.lang.String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(java.lang.String empNombre) {
        this.empNombre = empNombre;
    }

   public static javax.microedition.xml.rpc.Type populateTypeMap( javax.xml.namespace.QName targetName, java.util.Hashtable typeMap )
   {
      if (typeMap == null) {
         typeMap = new java.util.Hashtable();
      }

      String complexTypeName = "ciat_siscar_mobile_services_VoEmployee";

      if (typeMap.containsKey( complexTypeName )) {
         return (javax.microedition.xml.rpc.Type)typeMap.get( complexTypeName ); 
      }

      javax.microedition.xml.rpc.ComplexType ciat_siscar_mobile_services_VoEmployee = new javax.microedition.xml.rpc.ComplexType();
      ciat_siscar_mobile_services_VoEmployee.elements = new javax.microedition.xml.rpc.Element[2];
          {
          javax.xml.namespace.QName __QNAME_empApellido = new javax.xml.namespace.QName("", "empApellido");
          javax.microedition.xml.rpc.Element __ELEM_empApellido = new javax.microedition.xml.rpc.Element (__QNAME_empApellido, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_VoEmployee.elements[0] = __ELEM_empApellido;
          }

          {
          javax.xml.namespace.QName __QNAME_empNombre = new javax.xml.namespace.QName("", "empNombre");
          javax.microedition.xml.rpc.Element __ELEM_empNombre = new javax.microedition.xml.rpc.Element (__QNAME_empNombre, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_VoEmployee.elements[1] = __ELEM_empNombre;
          }

      typeMap.put(complexTypeName, ciat_siscar_mobile_services_VoEmployee);
      return ciat_siscar_mobile_services_VoEmployee;
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
          getEmpApellido(),
          getEmpNombre(),
      };
      recursionCheck = false;
      return (Object)objs;
	}


   public void deserialize( Object value )
   {
      Object[] values = (Object[])value;
      if ((values != null) && (values.length == 2)) {
         setEmpApellido( (String)values[0] );
         setEmpNombre( (String)values[1] );
      }
   }

}
