/**
 * VoCostCentersFuels.java
 *
 * Generated on Tue Feb 03 00:17:51 GMT 2009
 * Generated from http://localhost:8082/siscar.services/SiscarService?wsdl
 */

package ciat.siscar.mobile.services;

public class VoCostCentersFuels {
    private java.lang.Long ccfId;
    private java.lang.String costCenterNumber;
    private java.lang.Long idCostCenterTypeFuel;
    private java.lang.Long idCostsCenters;
    private java.lang.Long idPrepaid;

    public VoCostCentersFuels() {
    }

    public java.lang.Long getCcfId() {
        return ccfId;
    }

    public void setCcfId(java.lang.Long ccfId) {
        this.ccfId = ccfId;
    }

    public java.lang.String getCostCenterNumber() {
        return costCenterNumber;
    }

    public void setCostCenterNumber(java.lang.String costCenterNumber) {
        this.costCenterNumber = costCenterNumber;
    }

    public java.lang.Long getIdCostCenterTypeFuel() {
        return idCostCenterTypeFuel;
    }

    public void setIdCostCenterTypeFuel(java.lang.Long idCostCenterTypeFuel) {
        this.idCostCenterTypeFuel = idCostCenterTypeFuel;
    }

    public java.lang.Long getIdCostsCenters() {
        return idCostsCenters;
    }

    public void setIdCostsCenters(java.lang.Long idCostsCenters) {
        this.idCostsCenters = idCostsCenters;
    }

    public java.lang.Long getIdPrepaid() {
        return idPrepaid;
    }

    public void setIdPrepaid(java.lang.Long idPrepaid) {
        this.idPrepaid = idPrepaid;
    }

   public static javax.microedition.xml.rpc.Type populateTypeMap( javax.xml.namespace.QName targetName, java.util.Hashtable typeMap )
   {
      if (typeMap == null) {
         typeMap = new java.util.Hashtable();
      }

      String complexTypeName = "ciat_siscar_mobile_services_VoCostCentersFuels";

      if (typeMap.containsKey( complexTypeName )) {
         return (javax.microedition.xml.rpc.Type)typeMap.get( complexTypeName ); 
      }

      javax.microedition.xml.rpc.ComplexType ciat_siscar_mobile_services_VoCostCentersFuels = new javax.microedition.xml.rpc.ComplexType();
      ciat_siscar_mobile_services_VoCostCentersFuels.elements = new javax.microedition.xml.rpc.Element[5];
          {
          javax.xml.namespace.QName __QNAME_ccfId = new javax.xml.namespace.QName("", "ccfId");
          javax.microedition.xml.rpc.Element __ELEM_ccfId = new javax.microedition.xml.rpc.Element (__QNAME_ccfId, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          ciat_siscar_mobile_services_VoCostCentersFuels.elements[0] = __ELEM_ccfId;
          }

          {
          javax.xml.namespace.QName __QNAME_costCenterNumber = new javax.xml.namespace.QName("", "costCenterNumber");
          javax.microedition.xml.rpc.Element __ELEM_costCenterNumber = new javax.microedition.xml.rpc.Element (__QNAME_costCenterNumber, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_VoCostCentersFuels.elements[1] = __ELEM_costCenterNumber;
          }

          {
          javax.xml.namespace.QName __QNAME_idCostCenterTypeFuel = new javax.xml.namespace.QName("", "idCostCenterTypeFuel");
          javax.microedition.xml.rpc.Element __ELEM_idCostCenterTypeFuel = new javax.microedition.xml.rpc.Element (__QNAME_idCostCenterTypeFuel, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          ciat_siscar_mobile_services_VoCostCentersFuels.elements[2] = __ELEM_idCostCenterTypeFuel;
          }

          {
          javax.xml.namespace.QName __QNAME_idCostsCenters = new javax.xml.namespace.QName("", "idCostsCenters");
          javax.microedition.xml.rpc.Element __ELEM_idCostsCenters = new javax.microedition.xml.rpc.Element (__QNAME_idCostsCenters, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          ciat_siscar_mobile_services_VoCostCentersFuels.elements[3] = __ELEM_idCostsCenters;
          }

          {
          javax.xml.namespace.QName __QNAME_idPrepaid = new javax.xml.namespace.QName("", "idPrepaid");
          javax.microedition.xml.rpc.Element __ELEM_idPrepaid = new javax.microedition.xml.rpc.Element (__QNAME_idPrepaid, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          ciat_siscar_mobile_services_VoCostCentersFuels.elements[4] = __ELEM_idPrepaid;
          }

      typeMap.put(complexTypeName, ciat_siscar_mobile_services_VoCostCentersFuels);
      return ciat_siscar_mobile_services_VoCostCentersFuels;
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
          getCcfId(),
          getCostCenterNumber(),
          getIdCostCenterTypeFuel(),
          getIdCostsCenters(),
          getIdPrepaid(),
      };
      recursionCheck = false;
      return (Object)objs;
	}


   public void deserialize( Object value )
   {
      Object[] values = (Object[])value;
      if ((values != null) && (values.length == 5)) {
         setCcfId( (Long)values[0] );
         setCostCenterNumber( (String)values[1] );
         setIdCostCenterTypeFuel( (Long)values[2] );
         setIdCostsCenters( (Long)values[3] );
         setIdPrepaid( (Long)values[4] );
      }
   }

}
