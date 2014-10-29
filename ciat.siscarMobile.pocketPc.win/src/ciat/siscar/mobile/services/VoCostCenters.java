/**
 * VoCostCenters.java
 *
 * Generated on Tue Feb 03 00:17:51 GMT 2009
 * Generated from http://localhost:8082/siscar.services/SiscarService?wsdl
 */

package ciat.siscar.mobile.services;

public class VoCostCenters {
    private java.lang.Long cocCodigo;
    private java.lang.String cocNumero;
    private java.lang.Float cocValorPrepago;

    public VoCostCenters() {
    }

    public java.lang.Long getCocCodigo() {
        return cocCodigo;
    }

    public void setCocCodigo(java.lang.Long cocCodigo) {
        this.cocCodigo = cocCodigo;
    }

    public java.lang.String getCocNumero() {
        return cocNumero;
    }

    public void setCocNumero(java.lang.String cocNumero) {
        this.cocNumero = cocNumero;
    }

    public java.lang.Float getCocValorPrepago() {
        return cocValorPrepago;
    }

    public void setCocValorPrepago(java.lang.Float cocValorPrepago) {
        this.cocValorPrepago = cocValorPrepago;
    }

   public static javax.microedition.xml.rpc.Type populateTypeMap( javax.xml.namespace.QName targetName, java.util.Hashtable typeMap )
   {
      if (typeMap == null) {
         typeMap = new java.util.Hashtable();
      }

      String complexTypeName = "ciat_siscar_mobile_services_VoCostCenters";

      if (typeMap.containsKey( complexTypeName )) {
         return (javax.microedition.xml.rpc.Type)typeMap.get( complexTypeName ); 
      }

      javax.microedition.xml.rpc.ComplexType ciat_siscar_mobile_services_VoCostCenters = new javax.microedition.xml.rpc.ComplexType();
      ciat_siscar_mobile_services_VoCostCenters.elements = new javax.microedition.xml.rpc.Element[3];
          {
          javax.xml.namespace.QName __QNAME_cocCodigo = new javax.xml.namespace.QName("", "cocCodigo");
          javax.microedition.xml.rpc.Element __ELEM_cocCodigo = new javax.microedition.xml.rpc.Element (__QNAME_cocCodigo, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          ciat_siscar_mobile_services_VoCostCenters.elements[0] = __ELEM_cocCodigo;
          }

          {
          javax.xml.namespace.QName __QNAME_cocNumero = new javax.xml.namespace.QName("", "cocNumero");
          javax.microedition.xml.rpc.Element __ELEM_cocNumero = new javax.microedition.xml.rpc.Element (__QNAME_cocNumero, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_VoCostCenters.elements[1] = __ELEM_cocNumero;
          }

          {
          javax.xml.namespace.QName __QNAME_cocValorPrepago = new javax.xml.namespace.QName("", "cocValorPrepago");
          javax.microedition.xml.rpc.Element __ELEM_cocValorPrepago = new javax.microedition.xml.rpc.Element (__QNAME_cocValorPrepago, javax.microedition.xml.rpc.Type.FLOAT, 0, 1, true);
          ciat_siscar_mobile_services_VoCostCenters.elements[2] = __ELEM_cocValorPrepago;
          }

      typeMap.put(complexTypeName, ciat_siscar_mobile_services_VoCostCenters);
      return ciat_siscar_mobile_services_VoCostCenters;
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
          getCocCodigo(),
          getCocNumero(),
          convertFloatObjecttoString( getCocValorPrepago() ),
      };
      recursionCheck = false;
      return (Object)objs;
	}


   public void deserialize( Object value )
   {
      Object[] values = (Object[])value;
      if ((values != null) && (values.length == 3)) {
         setCocCodigo( (Long)values[0] );
         setCocNumero( (String)values[1] );
         setCocValorPrepago( parseToFloatObject( values[2] ) );
      }
   }


   protected Float parseToFloatObject( Object o )
   {
        Float result = null;
        if (o != null) {
           if (o instanceof String) {
              String s = (String)o;
              if ("NaN".equals( s )) {
                 result = new Float(Float.NaN);
              } else if ("INF".equals( s )) {
                 result = new Float(Float.POSITIVE_INFINITY);
              } else if ("-INF".equals( s )) {
                 result = new Float(Float.NEGATIVE_INFINITY);
              } else {
                  try {
                     result = new Float( s );
                  } catch (Throwable t) {
                  }
              }
           } else {
              result = ((Float)o);
           }
        }
        return result;
   }


   protected String convertFloatObjecttoString ( Float o )
   {
       Object obj = (Object)o;
       double data = 0.0;
       if (obj instanceof Float) {
           data = ((Float) obj).doubleValue();
       } else {
           data = ((Double) obj).doubleValue();
       }
       if (Double.isNaN(data)) {
           return "NaN";
       } else if (data == Double.POSITIVE_INFINITY) {
           return "INF";
       } else if (data == Double.NEGATIVE_INFINITY) {
           return "-INF";
       }
       return obj.toString();
   }

}
