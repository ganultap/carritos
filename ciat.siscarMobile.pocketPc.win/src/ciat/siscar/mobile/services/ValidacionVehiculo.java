/**
 * ValidacionVehiculo.java
 *
 * Generated on Tue Feb 03 00:17:51 GMT 2009
 * Generated from http://localhost:8082/siscar.services/SiscarService?wsdl
 */

package ciat.siscar.mobile.services;

public class ValidacionVehiculo {
    private java.lang.String capacidadTanque;
    private java.lang.String carneAsignado;
    private java.lang.String nombreAsignado;
    private java.lang.String tipoAsignacion;
    private int tipoCombustible;
    private java.lang.String ultimoKilometraje;
    private java.lang.String valorTanqueada;

    public ValidacionVehiculo() {
    }

    public java.lang.String getCapacidadTanque() {
        return capacidadTanque;
    }

    public void setCapacidadTanque(java.lang.String capacidadTanque) {
        this.capacidadTanque = capacidadTanque;
    }

    public java.lang.String getCarneAsignado() {
        return carneAsignado;
    }

    public void setCarneAsignado(java.lang.String carneAsignado) {
        this.carneAsignado = carneAsignado;
    }

    public java.lang.String getNombreAsignado() {
        return nombreAsignado;
    }

    public void setNombreAsignado(java.lang.String nombreAsignado) {
        this.nombreAsignado = nombreAsignado;
    }

    public java.lang.String getTipoAsignacion() {
        return tipoAsignacion;
    }

    public void setTipoAsignacion(java.lang.String tipoAsignacion) {
        this.tipoAsignacion = tipoAsignacion;
    }

    public int getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(int tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public java.lang.String getUltimoKilometraje() {
        return ultimoKilometraje;
    }

    public void setUltimoKilometraje(java.lang.String ultimoKilometraje) {
        this.ultimoKilometraje = ultimoKilometraje;
    }

    public java.lang.String getValorTanqueada() {
        return valorTanqueada;
    }

    public void setValorTanqueada(java.lang.String valorTanqueada) {
        this.valorTanqueada = valorTanqueada;
    }

   public static javax.microedition.xml.rpc.Type populateTypeMap( javax.xml.namespace.QName targetName, java.util.Hashtable typeMap )
   {
      if (typeMap == null) {
         typeMap = new java.util.Hashtable();
      }

      String complexTypeName = "ciat_siscar_mobile_services_ValidacionVehiculo";

      if (typeMap.containsKey( complexTypeName )) {
         return (javax.microedition.xml.rpc.Type)typeMap.get( complexTypeName ); 
      }

      javax.microedition.xml.rpc.ComplexType ciat_siscar_mobile_services_ValidacionVehiculo = new javax.microedition.xml.rpc.ComplexType();
      ciat_siscar_mobile_services_ValidacionVehiculo.elements = new javax.microedition.xml.rpc.Element[7];
          {
          javax.xml.namespace.QName __QNAME_capacidadTanque = new javax.xml.namespace.QName("", "capacidadTanque");
          javax.microedition.xml.rpc.Element __ELEM_capacidadTanque = new javax.microedition.xml.rpc.Element (__QNAME_capacidadTanque, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_ValidacionVehiculo.elements[0] = __ELEM_capacidadTanque;
          }

          {
          javax.xml.namespace.QName __QNAME_carneAsignado = new javax.xml.namespace.QName("", "carneAsignado");
          javax.microedition.xml.rpc.Element __ELEM_carneAsignado = new javax.microedition.xml.rpc.Element (__QNAME_carneAsignado, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_ValidacionVehiculo.elements[1] = __ELEM_carneAsignado;
          }

          {
          javax.xml.namespace.QName __QNAME_nombreAsignado = new javax.xml.namespace.QName("", "nombreAsignado");
          javax.microedition.xml.rpc.Element __ELEM_nombreAsignado = new javax.microedition.xml.rpc.Element (__QNAME_nombreAsignado, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_ValidacionVehiculo.elements[2] = __ELEM_nombreAsignado;
          }

          {
          javax.xml.namespace.QName __QNAME_tipoAsignacion = new javax.xml.namespace.QName("", "tipoAsignacion");
          javax.microedition.xml.rpc.Element __ELEM_tipoAsignacion = new javax.microedition.xml.rpc.Element (__QNAME_tipoAsignacion, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_ValidacionVehiculo.elements[3] = __ELEM_tipoAsignacion;
          }

          {
          javax.xml.namespace.QName __QNAME_tipoCombustible = new javax.xml.namespace.QName("", "tipoCombustible");
          javax.microedition.xml.rpc.Element __ELEM_tipoCombustible = new javax.microedition.xml.rpc.Element (__QNAME_tipoCombustible, javax.microedition.xml.rpc.Type.INT, 1, 1, false);
          ciat_siscar_mobile_services_ValidacionVehiculo.elements[4] = __ELEM_tipoCombustible;
          }

          {
          javax.xml.namespace.QName __QNAME_ultimoKilometraje = new javax.xml.namespace.QName("", "ultimoKilometraje");
          javax.microedition.xml.rpc.Element __ELEM_ultimoKilometraje = new javax.microedition.xml.rpc.Element (__QNAME_ultimoKilometraje, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_ValidacionVehiculo.elements[5] = __ELEM_ultimoKilometraje;
          }

          {
          javax.xml.namespace.QName __QNAME_valorTanqueada = new javax.xml.namespace.QName("", "valorTanqueada");
          javax.microedition.xml.rpc.Element __ELEM_valorTanqueada = new javax.microedition.xml.rpc.Element (__QNAME_valorTanqueada, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          ciat_siscar_mobile_services_ValidacionVehiculo.elements[6] = __ELEM_valorTanqueada;
          }

      typeMap.put(complexTypeName, ciat_siscar_mobile_services_ValidacionVehiculo);
      return ciat_siscar_mobile_services_ValidacionVehiculo;
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
          getCapacidadTanque(),
          getCarneAsignado(),
          getNombreAsignado(),
          getTipoAsignacion(),
          new Integer( getTipoCombustible() ),
          getUltimoKilometraje(),
          getValorTanqueada(),
      };
      recursionCheck = false;
      return (Object)objs;
	}


   public void deserialize( Object value )
   {
      Object[] values = (Object[])value;
      if ((values != null) && (values.length == 7)) {
         setCapacidadTanque( (String)values[0] );
         setCarneAsignado( (String)values[1] );
         setNombreAsignado( (String)values[2] );
         setTipoAsignacion( (String)values[3] );
         setTipoCombustible( ((Integer)values[4]).intValue() );
         setUltimoKilometraje( (String)values[5] );
         setValorTanqueada( (String)values[6] );
      }
   }

}
