/**
 * VoModel.java
 *
 * Generated on Tue Feb 03 00:17:51 GMT 2009
 * Generated from http://localhost:8082/siscar.services/SiscarService?wsdl
 */

package ciat.siscar.mobile.services;

public class VoModel {
    private java.lang.String param;

    public VoModel() {
    }

    public java.lang.String getParam() {
        return param;
    }

    public void setParam(java.lang.String param) {
        this.param = param;
    }

   public static javax.microedition.xml.rpc.Type populateTypeMap( javax.xml.namespace.QName targetName, java.util.Hashtable typeMap )
   {
      if (typeMap == null) {
         typeMap = new java.util.Hashtable();
      }

      String complexTypeName = "ciat_siscar_mobile_services_VoModel";

      if (typeMap.containsKey( complexTypeName )) {
         return (javax.microedition.xml.rpc.Type)typeMap.get( complexTypeName ); 
      }

      javax.microedition.xml.rpc.ComplexType ciat_siscar_mobile_services_VoModel = new javax.microedition.xml.rpc.ComplexType();
      ciat_siscar_mobile_services_VoModel.elements = new javax.microedition.xml.rpc.Element[1];
          {
          javax.xml.namespace.QName __QNAME_param = new javax.xml.namespace.QName("", "param");
          javax.microedition.xml.rpc.Element __ELEM_param = new javax.microedition.xml.rpc.Element (__QNAME_param, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_VoModel.elements[0] = __ELEM_param;
          }

      typeMap.put(complexTypeName, ciat_siscar_mobile_services_VoModel);
      return ciat_siscar_mobile_services_VoModel;
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
          getParam(),
      };
      recursionCheck = false;
      return (Object)objs;
	}


   public void deserialize( Object value )
   {
      Object[] values = (Object[])value;
      if ((values != null) && (values.length == 1)) {
         setParam( (String)values[0] );
      }
   }

}
