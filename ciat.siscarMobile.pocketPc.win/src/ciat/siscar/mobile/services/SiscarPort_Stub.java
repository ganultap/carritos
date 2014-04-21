/**
 * ciat.siscar.mobile.services.SiscarPort_Stub.java
 * Stub v.1.0
 *
 * Generated on Tue Feb 03 00:17:51 GMT 2009
 * Based on WSDL at http://localhost:8082/siscar.services/SiscarService?wsdl
 */

package ciat.siscar.mobile.services;

import javax.microedition.xml.rpc.Element;
import javax.microedition.xml.rpc.Operation;

public class SiscarPort_Stub implements javax.xml.rpc.Stub, ciat.siscar.mobile.services.SiscarDelegate{

    public javax.microedition.xml.rpc.Operation operation = null;
    public java.util.Hashtable typeMap = new java.util.Hashtable();
    public java.util.Hashtable properties = new java.util.Hashtable(); {
        properties.put(javax.xml.rpc.Stub.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8081/siscar.services/SiscarService");
//        properties.put(javax.xml.rpc.Stub.USERNAME_PROPERTY, "");
//        properties.put(javax.xml.rpc.Stub.PASSWORD_PROPERTY, "");
//        properties.put(javax.xml.rpc.Stub.SESSION_MAINTAIN_PROPERTY, new java.lang.Boolean(true));
    }
    public static java.util.Vector propertyNames = new java.util.Vector(); {
        propertyNames.addElement(javax.xml.rpc.Stub.ENDPOINT_ADDRESS_PROPERTY);
        propertyNames.addElement(javax.xml.rpc.Stub.USERNAME_PROPERTY);
        propertyNames.addElement(javax.xml.rpc.Stub.PASSWORD_PROPERTY);
        propertyNames.addElement(javax.xml.rpc.Stub.SESSION_MAINTAIN_PROPERTY);
    }

    public void _setProperty(java.lang.String name, java.lang.Object value) throws javax.xml.rpc.JAXRPCException {
        if (!propertyNames.contains(name))
            throw new javax.xml.rpc.JAXRPCException("property not supported: " + name);
        properties.put(name, value);
    }

    public java.lang.Object _getProperty(java.lang.String name) throws javax.xml.rpc.JAXRPCException {
        if (!propertyNames.contains(name))
            throw new javax.xml.rpc.JAXRPCException("property not supported: " + name);
        return properties.get(name);
    }

    private void setPropertiesOnOperation(javax.microedition.xml.rpc.Operation operation) throws javax.xml.rpc.JAXRPCException {
        java.util.Enumeration iterator = properties.keys();
        while (iterator.hasMoreElements()) {
        	java.lang.String s = (java.lang.String) iterator.nextElement();
        	if ( s.equals(javax.xml.rpc.Stub.SESSION_MAINTAIN_PROPERTY)) {
        	   operation.setProperty(s, ((java.lang.Boolean)properties.get(s)).toString());
        	} else { 
	           operation.setProperty(s, (java.lang.String) properties.get(s));
            }
        }
    }
    public java.lang.String validarLogin(java.lang.String _arg0, java.lang.String _arg1) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[2];

        Element __ELEM_validarLogin= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_validarLoginResponse = null;
        if (typeMap.containsKey( "__ELEM_validarLogin" ) ) {
           __ELEM_validarLogin = (Element)typeMap.get( "__ELEM_validarLogin"); 
           __ELEM_validarLoginResponse = (Element)typeMap.get( "__ELEM_validarLoginResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME_arg1 = new javax.xml.namespace.QName("", "arg1");
          javax.microedition.xml.rpc.Element __ELEM_arg1 = new javax.microedition.xml.rpc.Element (__QNAME_arg1, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[1] = __ELEM_arg1;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
          javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType validarLogin = new javax.microedition.xml.rpc.ComplexType();
          validarLogin.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_validarLogin = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "validarLogin");
          __ELEM_validarLogin = new Element (__QNAME_validarLogin, validarLogin,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType validarLoginResponse = new javax.microedition.xml.rpc.ComplexType();
          validarLoginResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_validarLoginResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "validarLoginResponse");
          __ELEM_validarLoginResponse = new Element (__QNAME_validarLoginResponse, validarLoginResponse,1 ,1, false); 

          typeMap.put( "__ELEM_validarLogin" , __ELEM_validarLogin);
          typeMap.put( "__ELEM_validarLoginResponse" , __ELEM_validarLoginResponse);

        }
        javax.xml.namespace.QName __QNAME_validarLogin = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "validarLogin");

          Object[] inputObjects = new Object[] { _arg0 ,_arg1          };
        Operation operation = Operation.newInstance(__QNAME_validarLogin, __ELEM_validarLogin, __ELEM_validarLoginResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        return (java.lang.String) returnObject;
    }

    public java.lang.String consultarAuxiliarCiat(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_consultarAuxiliarCiat= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_consultarAuxiliarCiatResponse = null;
        if (typeMap.containsKey( "__ELEM_consultarAuxiliarCiat" ) ) {
           __ELEM_consultarAuxiliarCiat = (Element)typeMap.get( "__ELEM_consultarAuxiliarCiat"); 
           __ELEM_consultarAuxiliarCiatResponse = (Element)typeMap.get( "__ELEM_consultarAuxiliarCiatResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
          javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType consultarAuxiliarCiat = new javax.microedition.xml.rpc.ComplexType();
          consultarAuxiliarCiat.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_consultarAuxiliarCiat = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarAuxiliarCiat");
          __ELEM_consultarAuxiliarCiat = new Element (__QNAME_consultarAuxiliarCiat, consultarAuxiliarCiat,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType consultarAuxiliarCiatResponse = new javax.microedition.xml.rpc.ComplexType();
          consultarAuxiliarCiatResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_consultarAuxiliarCiatResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarAuxiliarCiatResponse");
          __ELEM_consultarAuxiliarCiatResponse = new Element (__QNAME_consultarAuxiliarCiatResponse, consultarAuxiliarCiatResponse,1 ,1, false); 

          typeMap.put( "__ELEM_consultarAuxiliarCiat" , __ELEM_consultarAuxiliarCiat);
          typeMap.put( "__ELEM_consultarAuxiliarCiatResponse" , __ELEM_consultarAuxiliarCiatResponse);

        }
        javax.xml.namespace.QName __QNAME_consultarAuxiliarCiat = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarAuxiliarCiat");

          Object[] inputObjects = new Object[] { _arg0          };
        Operation operation = Operation.newInstance(__QNAME_consultarAuxiliarCiat, __ELEM_consultarAuxiliarCiat, __ELEM_consultarAuxiliarCiatResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        return (java.lang.String) returnObject;
    }

    public ciat.siscar.mobile.services.VoEmployee consultEmpleoyeeName(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_consultEmpleoyeeName= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_consultEmpleoyeeNameResponse = null;
        if (typeMap.containsKey( "__ELEM_consultEmpleoyeeName" ) ) {
           __ELEM_consultEmpleoyeeName = (Element)typeMap.get( "__ELEM_consultEmpleoyeeName"); 
           __ELEM_consultEmpleoyeeNameResponse = (Element)typeMap.get( "__ELEM_consultEmpleoyeeNameResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
           javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, ciat.siscar.mobile.services.VoEmployee.populateTypeMap( null, typeMap ) , 0, 1, false);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType consultEmpleoyeeName = new javax.microedition.xml.rpc.ComplexType();
          consultEmpleoyeeName.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_consultEmpleoyeeName = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultEmpleoyeeName");
          __ELEM_consultEmpleoyeeName = new Element (__QNAME_consultEmpleoyeeName, consultEmpleoyeeName,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType consultEmpleoyeeNameResponse = new javax.microedition.xml.rpc.ComplexType();
          consultEmpleoyeeNameResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_consultEmpleoyeeNameResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultEmpleoyeeNameResponse");
          __ELEM_consultEmpleoyeeNameResponse = new Element (__QNAME_consultEmpleoyeeNameResponse, consultEmpleoyeeNameResponse,1 ,1, false); 

          typeMap.put( "__ELEM_consultEmpleoyeeName" , __ELEM_consultEmpleoyeeName);
          typeMap.put( "__ELEM_consultEmpleoyeeNameResponse" , __ELEM_consultEmpleoyeeNameResponse);

        }
        javax.xml.namespace.QName __QNAME_consultEmpleoyeeName = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultEmpleoyeeName");

          Object[] inputObjects = new Object[] { _arg0          };
        Operation operation = Operation.newInstance(__QNAME_consultEmpleoyeeName, __ELEM_consultEmpleoyeeName, __ELEM_consultEmpleoyeeNameResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        ciat.siscar.mobile.services.VoEmployee result = null;
        if (returnObject != null) {
           result = new ciat.siscar.mobile.services.VoEmployee();
           result.deserialize( returnObject );
        }
        return result;
    }

    public java.lang.String consultarValorTarifaCombustibleCIAT(java.lang.Long _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_consultarValorTarifaCombustibleCIAT= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_consultarValorTarifaCombustibleCIATResponse = null;
        if (typeMap.containsKey( "__ELEM_consultarValorTarifaCombustibleCIAT" ) ) {
           __ELEM_consultarValorTarifaCombustibleCIAT = (Element)typeMap.get( "__ELEM_consultarValorTarifaCombustibleCIAT"); 
           __ELEM_consultarValorTarifaCombustibleCIATResponse = (Element)typeMap.get( "__ELEM_consultarValorTarifaCombustibleCIATResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
          javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType consultarValorTarifaCombustibleCIAT = new javax.microedition.xml.rpc.ComplexType();
          consultarValorTarifaCombustibleCIAT.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_consultarValorTarifaCombustibleCIAT = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarValorTarifaCombustibleCIAT");
          __ELEM_consultarValorTarifaCombustibleCIAT = new Element (__QNAME_consultarValorTarifaCombustibleCIAT, consultarValorTarifaCombustibleCIAT,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType consultarValorTarifaCombustibleCIATResponse = new javax.microedition.xml.rpc.ComplexType();
          consultarValorTarifaCombustibleCIATResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_consultarValorTarifaCombustibleCIATResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarValorTarifaCombustibleCIATResponse");
          __ELEM_consultarValorTarifaCombustibleCIATResponse = new Element (__QNAME_consultarValorTarifaCombustibleCIATResponse, consultarValorTarifaCombustibleCIATResponse,1 ,1, false); 

          typeMap.put( "__ELEM_consultarValorTarifaCombustibleCIAT" , __ELEM_consultarValorTarifaCombustibleCIAT);
          typeMap.put( "__ELEM_consultarValorTarifaCombustibleCIATResponse" , __ELEM_consultarValorTarifaCombustibleCIATResponse);

        }
        javax.xml.namespace.QName __QNAME_consultarValorTarifaCombustibleCIAT = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarValorTarifaCombustibleCIAT");

          Object[] inputObjects = new Object[] { _arg0          };
        Operation operation = Operation.newInstance(__QNAME_consultarValorTarifaCombustibleCIAT, __ELEM_consultarValorTarifaCombustibleCIAT, __ELEM_consultarValorTarifaCombustibleCIATResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        return (java.lang.String) returnObject;
    }

    public ciat.siscar.mobile.services.VoModel consultCostCenters(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_consultCostCenters= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_consultCostCentersResponse = null;
        if (typeMap.containsKey( "__ELEM_consultCostCenters" ) ) {
           __ELEM_consultCostCenters = (Element)typeMap.get( "__ELEM_consultCostCenters"); 
           __ELEM_consultCostCentersResponse = (Element)typeMap.get( "__ELEM_consultCostCentersResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
           javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, ciat.siscar.mobile.services.VoModel.populateTypeMap( null, typeMap ) , 0, 1, false);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType consultCostCenters = new javax.microedition.xml.rpc.ComplexType();
          consultCostCenters.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_consultCostCenters = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultCostCenters");
          __ELEM_consultCostCenters = new Element (__QNAME_consultCostCenters, consultCostCenters,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType consultCostCentersResponse = new javax.microedition.xml.rpc.ComplexType();
          consultCostCentersResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_consultCostCentersResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultCostCentersResponse");
          __ELEM_consultCostCentersResponse = new Element (__QNAME_consultCostCentersResponse, consultCostCentersResponse,1 ,1, false); 

          typeMap.put( "__ELEM_consultCostCenters" , __ELEM_consultCostCenters);
          typeMap.put( "__ELEM_consultCostCentersResponse" , __ELEM_consultCostCentersResponse);

        }
        javax.xml.namespace.QName __QNAME_consultCostCenters = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultCostCenters");

          Object[] inputObjects = new Object[] { _arg0          };
        Operation operation = Operation.newInstance(__QNAME_consultCostCenters, __ELEM_consultCostCenters, __ELEM_consultCostCentersResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        ciat.siscar.mobile.services.VoModel result = null;
        if (returnObject != null) {
           result = new ciat.siscar.mobile.services.VoModel();
           result.deserialize( returnObject );
        }
        return result;
    }

    public ciat.siscar.mobile.services.VoModel consultTypeRequest(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_consultTypeRequest= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_consultTypeRequestResponse = null;
        if (typeMap.containsKey( "__ELEM_consultTypeRequest" ) ) {
           __ELEM_consultTypeRequest = (Element)typeMap.get( "__ELEM_consultTypeRequest"); 
           __ELEM_consultTypeRequestResponse = (Element)typeMap.get( "__ELEM_consultTypeRequestResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
           javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, ciat.siscar.mobile.services.VoModel.populateTypeMap( null, typeMap ) , 0, 1, false);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType consultTypeRequest = new javax.microedition.xml.rpc.ComplexType();
          consultTypeRequest.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_consultTypeRequest = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultTypeRequest");
          __ELEM_consultTypeRequest = new Element (__QNAME_consultTypeRequest, consultTypeRequest,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType consultTypeRequestResponse = new javax.microedition.xml.rpc.ComplexType();
          consultTypeRequestResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_consultTypeRequestResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultTypeRequestResponse");
          __ELEM_consultTypeRequestResponse = new Element (__QNAME_consultTypeRequestResponse, consultTypeRequestResponse,1 ,1, false); 

          typeMap.put( "__ELEM_consultTypeRequest" , __ELEM_consultTypeRequest);
          typeMap.put( "__ELEM_consultTypeRequestResponse" , __ELEM_consultTypeRequestResponse);

        }
        javax.xml.namespace.QName __QNAME_consultTypeRequest = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultTypeRequest");

          Object[] inputObjects = new Object[] { _arg0          };
        Operation operation = Operation.newInstance(__QNAME_consultTypeRequest, __ELEM_consultTypeRequest, __ELEM_consultTypeRequestResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        ciat.siscar.mobile.services.VoModel result = null;
        if (returnObject != null) {
           result = new ciat.siscar.mobile.services.VoModel();
           result.deserialize( returnObject );
        }
        return result;
    }

    public ciat.siscar.mobile.services.VoModel consultPums(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_consultPums= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_consultPumsResponse = null;
        if (typeMap.containsKey( "__ELEM_consultPums" ) ) {
           __ELEM_consultPums = (Element)typeMap.get( "__ELEM_consultPums"); 
           __ELEM_consultPumsResponse = (Element)typeMap.get( "__ELEM_consultPumsResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
           javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, ciat.siscar.mobile.services.VoModel.populateTypeMap( null, typeMap ) , 0, 1, false);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType consultPums = new javax.microedition.xml.rpc.ComplexType();
          consultPums.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_consultPums = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultPums");
          __ELEM_consultPums = new Element (__QNAME_consultPums, consultPums,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType consultPumsResponse = new javax.microedition.xml.rpc.ComplexType();
          consultPumsResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_consultPumsResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultPumsResponse");
          __ELEM_consultPumsResponse = new Element (__QNAME_consultPumsResponse, consultPumsResponse,1 ,1, false); 

          typeMap.put( "__ELEM_consultPums" , __ELEM_consultPums);
          typeMap.put( "__ELEM_consultPumsResponse" , __ELEM_consultPumsResponse);

        }
        javax.xml.namespace.QName __QNAME_consultPums = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultPums");

          Object[] inputObjects = new Object[] { _arg0          };
        Operation operation = Operation.newInstance(__QNAME_consultPums, __ELEM_consultPums, __ELEM_consultPumsResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        ciat.siscar.mobile.services.VoModel result = null;
        if (returnObject != null) {
           result = new ciat.siscar.mobile.services.VoModel();
           result.deserialize( returnObject );
        }
        return result;
    }

    public java.lang.String consultarValorTarifaCombustibleCALI(java.lang.Long _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_consultarValorTarifaCombustibleCALI= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_consultarValorTarifaCombustibleCALIResponse = null;
        if (typeMap.containsKey( "__ELEM_consultarValorTarifaCombustibleCALI" ) ) {
           __ELEM_consultarValorTarifaCombustibleCALI = (Element)typeMap.get( "__ELEM_consultarValorTarifaCombustibleCALI"); 
           __ELEM_consultarValorTarifaCombustibleCALIResponse = (Element)typeMap.get( "__ELEM_consultarValorTarifaCombustibleCALIResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
          javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType consultarValorTarifaCombustibleCALI = new javax.microedition.xml.rpc.ComplexType();
          consultarValorTarifaCombustibleCALI.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_consultarValorTarifaCombustibleCALI = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarValorTarifaCombustibleCALI");
          __ELEM_consultarValorTarifaCombustibleCALI = new Element (__QNAME_consultarValorTarifaCombustibleCALI, consultarValorTarifaCombustibleCALI,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType consultarValorTarifaCombustibleCALIResponse = new javax.microedition.xml.rpc.ComplexType();
          consultarValorTarifaCombustibleCALIResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_consultarValorTarifaCombustibleCALIResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarValorTarifaCombustibleCALIResponse");
          __ELEM_consultarValorTarifaCombustibleCALIResponse = new Element (__QNAME_consultarValorTarifaCombustibleCALIResponse, consultarValorTarifaCombustibleCALIResponse,1 ,1, false); 

          typeMap.put( "__ELEM_consultarValorTarifaCombustibleCALI" , __ELEM_consultarValorTarifaCombustibleCALI);
          typeMap.put( "__ELEM_consultarValorTarifaCombustibleCALIResponse" , __ELEM_consultarValorTarifaCombustibleCALIResponse);

        }
        javax.xml.namespace.QName __QNAME_consultarValorTarifaCombustibleCALI = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarValorTarifaCombustibleCALI");

          Object[] inputObjects = new Object[] { _arg0          };
        Operation operation = Operation.newInstance(__QNAME_consultarValorTarifaCombustibleCALI, __ELEM_consultarValorTarifaCombustibleCALI, __ELEM_consultarValorTarifaCombustibleCALIResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        return (java.lang.String) returnObject;
    }

    public ciat.siscar.mobile.services.VoCostCenters consultarCentroCostoVO(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_consultarCentroCostoVO= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_consultarCentroCostoVOResponse = null;
        if (typeMap.containsKey( "__ELEM_consultarCentroCostoVO" ) ) {
           __ELEM_consultarCentroCostoVO = (Element)typeMap.get( "__ELEM_consultarCentroCostoVO"); 
           __ELEM_consultarCentroCostoVOResponse = (Element)typeMap.get( "__ELEM_consultarCentroCostoVOResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
           javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, ciat.siscar.mobile.services.VoCostCenters.populateTypeMap( null, typeMap ) , 0, 1, false);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType consultarCentroCostoVO = new javax.microedition.xml.rpc.ComplexType();
          consultarCentroCostoVO.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_consultarCentroCostoVO = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarCentroCostoVO");
          __ELEM_consultarCentroCostoVO = new Element (__QNAME_consultarCentroCostoVO, consultarCentroCostoVO,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType consultarCentroCostoVOResponse = new javax.microedition.xml.rpc.ComplexType();
          consultarCentroCostoVOResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_consultarCentroCostoVOResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarCentroCostoVOResponse");
          __ELEM_consultarCentroCostoVOResponse = new Element (__QNAME_consultarCentroCostoVOResponse, consultarCentroCostoVOResponse,1 ,1, false); 

          typeMap.put( "__ELEM_consultarCentroCostoVO" , __ELEM_consultarCentroCostoVO);
          typeMap.put( "__ELEM_consultarCentroCostoVOResponse" , __ELEM_consultarCentroCostoVOResponse);

        }
        javax.xml.namespace.QName __QNAME_consultarCentroCostoVO = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarCentroCostoVO");

          Object[] inputObjects = new Object[] { _arg0          };
        Operation operation = Operation.newInstance(__QNAME_consultarCentroCostoVO, __ELEM_consultarCentroCostoVO, __ELEM_consultarCentroCostoVOResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        ciat.siscar.mobile.services.VoCostCenters result = null;
        if (returnObject != null) {
           result = new ciat.siscar.mobile.services.VoCostCenters();
           result.deserialize( returnObject );
        }
        return result;
    }

    public ciat.siscar.mobile.services.VoCostCentersFuels consultCostCenterFuelByPlaca(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_consultCostCenterFuelByPlaca= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_consultCostCenterFuelByPlacaResponse = null;
        if (typeMap.containsKey( "__ELEM_consultCostCenterFuelByPlaca" ) ) {
           __ELEM_consultCostCenterFuelByPlaca = (Element)typeMap.get( "__ELEM_consultCostCenterFuelByPlaca"); 
           __ELEM_consultCostCenterFuelByPlacaResponse = (Element)typeMap.get( "__ELEM_consultCostCenterFuelByPlacaResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
           javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, ciat.siscar.mobile.services.VoCostCentersFuels.populateTypeMap( null, typeMap ) , 0, 1, false);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType consultCostCenterFuelByPlaca = new javax.microedition.xml.rpc.ComplexType();
          consultCostCenterFuelByPlaca.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_consultCostCenterFuelByPlaca = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultCostCenterFuelByPlaca");
          __ELEM_consultCostCenterFuelByPlaca = new Element (__QNAME_consultCostCenterFuelByPlaca, consultCostCenterFuelByPlaca,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType consultCostCenterFuelByPlacaResponse = new javax.microedition.xml.rpc.ComplexType();
          consultCostCenterFuelByPlacaResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_consultCostCenterFuelByPlacaResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultCostCenterFuelByPlacaResponse");
          __ELEM_consultCostCenterFuelByPlacaResponse = new Element (__QNAME_consultCostCenterFuelByPlacaResponse, consultCostCenterFuelByPlacaResponse,1 ,1, false); 

          typeMap.put( "__ELEM_consultCostCenterFuelByPlaca" , __ELEM_consultCostCenterFuelByPlaca);
          typeMap.put( "__ELEM_consultCostCenterFuelByPlacaResponse" , __ELEM_consultCostCenterFuelByPlacaResponse);

        }
        javax.xml.namespace.QName __QNAME_consultCostCenterFuelByPlaca = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultCostCenterFuelByPlaca");

          Object[] inputObjects = new Object[] { _arg0          };
        Operation operation = Operation.newInstance(__QNAME_consultCostCenterFuelByPlaca, __ELEM_consultCostCenterFuelByPlaca, __ELEM_consultCostCenterFuelByPlacaResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        ciat.siscar.mobile.services.VoCostCentersFuels result = null;
        if (returnObject != null) {
           result = new ciat.siscar.mobile.services.VoCostCentersFuels();
           result.deserialize( returnObject );
        }
        return result;
    }

    public java.lang.String obtenerTarifa(int _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_obtenerTarifa= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_obtenerTarifaResponse = null;
        if (typeMap.containsKey( "__ELEM_obtenerTarifa" ) ) {
           __ELEM_obtenerTarifa = (Element)typeMap.get( "__ELEM_obtenerTarifa"); 
           __ELEM_obtenerTarifaResponse = (Element)typeMap.get( "__ELEM_obtenerTarifaResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.INT, 0, 1, false);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
          javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType obtenerTarifa = new javax.microedition.xml.rpc.ComplexType();
          obtenerTarifa.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_obtenerTarifa = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "obtenerTarifa");
          __ELEM_obtenerTarifa = new Element (__QNAME_obtenerTarifa, obtenerTarifa,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType obtenerTarifaResponse = new javax.microedition.xml.rpc.ComplexType();
          obtenerTarifaResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_obtenerTarifaResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "obtenerTarifaResponse");
          __ELEM_obtenerTarifaResponse = new Element (__QNAME_obtenerTarifaResponse, obtenerTarifaResponse,1 ,1, false); 

          typeMap.put( "__ELEM_obtenerTarifa" , __ELEM_obtenerTarifa);
          typeMap.put( "__ELEM_obtenerTarifaResponse" , __ELEM_obtenerTarifaResponse);

        }
        javax.xml.namespace.QName __QNAME_obtenerTarifa = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "obtenerTarifa");

          Object[] inputObjects = new Object[] { new java.lang.Integer(_arg0)          };
        Operation operation = Operation.newInstance(__QNAME_obtenerTarifa, __ELEM_obtenerTarifa, __ELEM_obtenerTarifaResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        return (java.lang.String) returnObject;
    }

    public ciat.siscar.mobile.services.ValidacionSolicitante validarSolicitante(int _arg0, java.lang.String _arg1) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[2];

        Element __ELEM_validarSolicitante= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_validarSolicitanteResponse = null;
        if (typeMap.containsKey( "__ELEM_validarSolicitante" ) ) {
           __ELEM_validarSolicitante = (Element)typeMap.get( "__ELEM_validarSolicitante"); 
           __ELEM_validarSolicitanteResponse = (Element)typeMap.get( "__ELEM_validarSolicitanteResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.INT, 0, 1, false);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME_arg1 = new javax.xml.namespace.QName("", "arg1");
          javax.microedition.xml.rpc.Element __ELEM_arg1 = new javax.microedition.xml.rpc.Element (__QNAME_arg1, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[1] = __ELEM_arg1;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
           javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, ciat.siscar.mobile.services.ValidacionSolicitante.populateTypeMap( null, typeMap ) , 0, 1, false);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType validarSolicitante = new javax.microedition.xml.rpc.ComplexType();
          validarSolicitante.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_validarSolicitante = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "validarSolicitante");
          __ELEM_validarSolicitante = new Element (__QNAME_validarSolicitante, validarSolicitante,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType validarSolicitanteResponse = new javax.microedition.xml.rpc.ComplexType();
          validarSolicitanteResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_validarSolicitanteResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "validarSolicitanteResponse");
          __ELEM_validarSolicitanteResponse = new Element (__QNAME_validarSolicitanteResponse, validarSolicitanteResponse,1 ,1, false); 

          typeMap.put( "__ELEM_validarSolicitante" , __ELEM_validarSolicitante);
          typeMap.put( "__ELEM_validarSolicitanteResponse" , __ELEM_validarSolicitanteResponse);

        }
        javax.xml.namespace.QName __QNAME_validarSolicitante = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "validarSolicitante");

          Object[] inputObjects = new Object[] { new java.lang.Integer(_arg0) ,_arg1          };
        Operation operation = Operation.newInstance(__QNAME_validarSolicitante, __ELEM_validarSolicitante, __ELEM_validarSolicitanteResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        ciat.siscar.mobile.services.ValidacionSolicitante result = null;
        if (returnObject != null) {
           result = new ciat.siscar.mobile.services.ValidacionSolicitante();
           result.deserialize( returnObject );
        }
        return result;
    }

    public ciat.siscar.mobile.services.ValidacionVehiculo validarVehiculo(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_validarVehiculo= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_validarVehiculoResponse = null;
        if (typeMap.containsKey( "__ELEM_validarVehiculo" ) ) {
           __ELEM_validarVehiculo = (Element)typeMap.get( "__ELEM_validarVehiculo"); 
           __ELEM_validarVehiculoResponse = (Element)typeMap.get( "__ELEM_validarVehiculoResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
           javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, ciat.siscar.mobile.services.ValidacionVehiculo.populateTypeMap( null, typeMap ) , 0, 1, false);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType validarVehiculo = new javax.microedition.xml.rpc.ComplexType();
          validarVehiculo.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_validarVehiculo = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "validarVehiculo");
          __ELEM_validarVehiculo = new Element (__QNAME_validarVehiculo, validarVehiculo,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType validarVehiculoResponse = new javax.microedition.xml.rpc.ComplexType();
          validarVehiculoResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_validarVehiculoResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "validarVehiculoResponse");
          __ELEM_validarVehiculoResponse = new Element (__QNAME_validarVehiculoResponse, validarVehiculoResponse,1 ,1, false); 

          typeMap.put( "__ELEM_validarVehiculo" , __ELEM_validarVehiculo);
          typeMap.put( "__ELEM_validarVehiculoResponse" , __ELEM_validarVehiculoResponse);

        }
        javax.xml.namespace.QName __QNAME_validarVehiculo = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "validarVehiculo");

          Object[] inputObjects = new Object[] { _arg0          };
        Operation operation = Operation.newInstance(__QNAME_validarVehiculo, __ELEM_validarVehiculo, __ELEM_validarVehiculoResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        ciat.siscar.mobile.services.ValidacionVehiculo result = null;
        if (returnObject != null) {
           result = new ciat.siscar.mobile.services.ValidacionVehiculo();
           result.deserialize( returnObject );
        }
        return result;
    }

    public boolean validarCentroCosto(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_validarCentroCosto= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_validarCentroCostoResponse = null;
        if (typeMap.containsKey( "__ELEM_validarCentroCosto" ) ) {
           __ELEM_validarCentroCosto = (Element)typeMap.get( "__ELEM_validarCentroCosto"); 
           __ELEM_validarCentroCostoResponse = (Element)typeMap.get( "__ELEM_validarCentroCostoResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
          javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, javax.microedition.xml.rpc.Type.BOOLEAN, 0, 1, false);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType validarCentroCosto = new javax.microedition.xml.rpc.ComplexType();
          validarCentroCosto.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_validarCentroCosto = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "validarCentroCosto");
          __ELEM_validarCentroCosto = new Element (__QNAME_validarCentroCosto, validarCentroCosto,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType validarCentroCostoResponse = new javax.microedition.xml.rpc.ComplexType();
          validarCentroCostoResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_validarCentroCostoResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "validarCentroCostoResponse");
          __ELEM_validarCentroCostoResponse = new Element (__QNAME_validarCentroCostoResponse, validarCentroCostoResponse,1 ,1, false); 

          typeMap.put( "__ELEM_validarCentroCosto" , __ELEM_validarCentroCosto);
          typeMap.put( "__ELEM_validarCentroCostoResponse" , __ELEM_validarCentroCostoResponse);

        }
        javax.xml.namespace.QName __QNAME_validarCentroCosto = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "validarCentroCosto");

          Object[] inputObjects = new Object[] { _arg0          };
        Operation operation = Operation.newInstance(__QNAME_validarCentroCosto, __ELEM_validarCentroCosto, __ELEM_validarCentroCostoResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        return ((java.lang.Boolean) (returnObject)).booleanValue();
    }

    public void guardarServicioRegistro(java.lang.Long _arg0, java.lang.String _arg1, java.lang.String _arg2, java.lang.String _arg3, java.lang.String _arg4, java.lang.String _arg5, java.lang.Float _arg6, java.lang.Float _arg7, java.lang.String _arg8, java.lang.Long _arg9, java.lang.Long _arg10, java.lang.Long _arg11, java.lang.String _arg12, java.lang.Long _arg13, java.lang.Long _arg14, java.lang.Float _arg15, java.lang.Long _arg16) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[17];

        Element __ELEM_guardarServicioRegistro= null;
        if (typeMap.containsKey( "__ELEM_guardarServicioRegistro" ) ) {
           __ELEM_guardarServicioRegistro = (Element)typeMap.get( "__ELEM_guardarServicioRegistro"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME_arg1 = new javax.xml.namespace.QName("", "arg1");
          javax.microedition.xml.rpc.Element __ELEM_arg1 = new javax.microedition.xml.rpc.Element (__QNAME_arg1, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[1] = __ELEM_arg1;
          }

          {
          javax.xml.namespace.QName __QNAME_arg2 = new javax.xml.namespace.QName("", "arg2");
          javax.microedition.xml.rpc.Element __ELEM_arg2 = new javax.microedition.xml.rpc.Element (__QNAME_arg2, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[2] = __ELEM_arg2;
          }

          {
          javax.xml.namespace.QName __QNAME_arg3 = new javax.xml.namespace.QName("", "arg3");
          javax.microedition.xml.rpc.Element __ELEM_arg3 = new javax.microedition.xml.rpc.Element (__QNAME_arg3, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[3] = __ELEM_arg3;
          }

          {
          javax.xml.namespace.QName __QNAME_arg4 = new javax.xml.namespace.QName("", "arg4");
          javax.microedition.xml.rpc.Element __ELEM_arg4 = new javax.microedition.xml.rpc.Element (__QNAME_arg4, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[4] = __ELEM_arg4;
          }

          {
          javax.xml.namespace.QName __QNAME_arg5 = new javax.xml.namespace.QName("", "arg5");
          javax.microedition.xml.rpc.Element __ELEM_arg5 = new javax.microedition.xml.rpc.Element (__QNAME_arg5, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[5] = __ELEM_arg5;
          }

          {
          javax.xml.namespace.QName __QNAME_arg6 = new javax.xml.namespace.QName("", "arg6");
          javax.microedition.xml.rpc.Element __ELEM_arg6 = new javax.microedition.xml.rpc.Element (__QNAME_arg6, javax.microedition.xml.rpc.Type.FLOAT, 0, 1, true);
          inputTypes[6] = __ELEM_arg6;
          }

          {
          javax.xml.namespace.QName __QNAME_arg7 = new javax.xml.namespace.QName("", "arg7");
          javax.microedition.xml.rpc.Element __ELEM_arg7 = new javax.microedition.xml.rpc.Element (__QNAME_arg7, javax.microedition.xml.rpc.Type.FLOAT, 0, 1, true);
          inputTypes[7] = __ELEM_arg7;
          }

          {
          javax.xml.namespace.QName __QNAME_arg8 = new javax.xml.namespace.QName("", "arg8");
          javax.microedition.xml.rpc.Element __ELEM_arg8 = new javax.microedition.xml.rpc.Element (__QNAME_arg8, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[8] = __ELEM_arg8;
          }

          {
          javax.xml.namespace.QName __QNAME_arg9 = new javax.xml.namespace.QName("", "arg9");
          javax.microedition.xml.rpc.Element __ELEM_arg9 = new javax.microedition.xml.rpc.Element (__QNAME_arg9, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          inputTypes[9] = __ELEM_arg9;
          }

          {
          javax.xml.namespace.QName __QNAME_arg10 = new javax.xml.namespace.QName("", "arg10");
          javax.microedition.xml.rpc.Element __ELEM_arg10 = new javax.microedition.xml.rpc.Element (__QNAME_arg10, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          inputTypes[10] = __ELEM_arg10;
          }

          {
          javax.xml.namespace.QName __QNAME_arg11 = new javax.xml.namespace.QName("", "arg11");
          javax.microedition.xml.rpc.Element __ELEM_arg11 = new javax.microedition.xml.rpc.Element (__QNAME_arg11, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          inputTypes[11] = __ELEM_arg11;
          }

          {
          javax.xml.namespace.QName __QNAME_arg12 = new javax.xml.namespace.QName("", "arg12");
          javax.microedition.xml.rpc.Element __ELEM_arg12 = new javax.microedition.xml.rpc.Element (__QNAME_arg12, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[12] = __ELEM_arg12;
          }

          {
          javax.xml.namespace.QName __QNAME_arg13 = new javax.xml.namespace.QName("", "arg13");
          javax.microedition.xml.rpc.Element __ELEM_arg13 = new javax.microedition.xml.rpc.Element (__QNAME_arg13, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          inputTypes[13] = __ELEM_arg13;
          }

          {
          javax.xml.namespace.QName __QNAME_arg14 = new javax.xml.namespace.QName("", "arg14");
          javax.microedition.xml.rpc.Element __ELEM_arg14 = new javax.microedition.xml.rpc.Element (__QNAME_arg14, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          inputTypes[14] = __ELEM_arg14;
          }

          {
          javax.xml.namespace.QName __QNAME_arg15 = new javax.xml.namespace.QName("", "arg15");
          javax.microedition.xml.rpc.Element __ELEM_arg15 = new javax.microedition.xml.rpc.Element (__QNAME_arg15, javax.microedition.xml.rpc.Type.FLOAT, 0, 1, true);
          inputTypes[15] = __ELEM_arg15;
          }

          {
          javax.xml.namespace.QName __QNAME_arg16 = new javax.xml.namespace.QName("", "arg16");
          javax.microedition.xml.rpc.Element __ELEM_arg16 = new javax.microedition.xml.rpc.Element (__QNAME_arg16, javax.microedition.xml.rpc.Type.LONG, 0, 1, true);
          inputTypes[16] = __ELEM_arg16;
          }

          javax.microedition.xml.rpc.ComplexType guardarServicioRegistro = new javax.microedition.xml.rpc.ComplexType();
          guardarServicioRegistro.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_guardarServicioRegistro = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "guardarServicioRegistro");
          __ELEM_guardarServicioRegistro = new Element (__QNAME_guardarServicioRegistro, guardarServicioRegistro,1 ,1, false); 

          typeMap.put( "__ELEM_guardarServicioRegistro" , __ELEM_guardarServicioRegistro);

        }
        javax.xml.namespace.QName __QNAME_guardarServicioRegistro = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "guardarServicioRegistro");

          Object[] inputObjects = new Object[] { _arg0 ,_arg1 ,_arg2 ,_arg3 ,_arg4 ,_arg5 ,convertFloatObjecttoString ( _arg6 )  ,convertFloatObjecttoString ( _arg7 )  ,_arg8 ,_arg9 ,_arg10 ,_arg11 ,_arg12 ,_arg13 ,_arg14 ,convertFloatObjecttoString ( _arg15 )  ,_arg16          };
        Operation operation = Operation.newInstance(__QNAME_guardarServicioRegistro, __ELEM_guardarServicioRegistro, null);
        setPropertiesOnOperation(operation);

        Object returnObject = operation.invoke(inputObjects);
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

    public ciat.siscar.mobile.services.VoCostCentersFuels[] consultarCostCenterFuelPorPlaca(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException
    {
        Element[] inputTypes = new Element[1];

        Element __ELEM_consultarCostCenterFuelPorPlaca= null;
        Element[] outTypes = new Element[1];
        Element __ELEM_consultarCostCenterFuelPorPlacaResponse = null;
        if (typeMap.containsKey( "__ELEM_consultarCostCenterFuelPorPlaca" ) ) {
           __ELEM_consultarCostCenterFuelPorPlaca = (Element)typeMap.get( "__ELEM_consultarCostCenterFuelPorPlaca"); 
           __ELEM_consultarCostCenterFuelPorPlacaResponse = (Element)typeMap.get( "__ELEM_consultarCostCenterFuelPorPlacaResponse"); 
        } else {

          {
          javax.xml.namespace.QName __QNAME_arg0 = new javax.xml.namespace.QName("", "arg0");
          javax.microedition.xml.rpc.Element __ELEM_arg0 = new javax.microedition.xml.rpc.Element (__QNAME_arg0, javax.microedition.xml.rpc.Type.STRING, 0, 1, true);
          inputTypes[0] = __ELEM_arg0;
          }

          {
          javax.xml.namespace.QName __QNAME__return = new javax.xml.namespace.QName("", "return");
           javax.microedition.xml.rpc.Element __ELEM__return = new javax.microedition.xml.rpc.Element (__QNAME__return, ciat.siscar.mobile.services.VoCostCentersFuels.populateTypeMap( null, typeMap ) , 0, -1, false);
          outTypes[0] = __ELEM__return;
          }

          javax.microedition.xml.rpc.ComplexType consultarCostCenterFuelPorPlaca = new javax.microedition.xml.rpc.ComplexType();
          consultarCostCenterFuelPorPlaca.elements = (Element[])inputTypes;
          javax.xml.namespace.QName __QNAME_consultarCostCenterFuelPorPlaca = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarCostCenterFuelPorPlaca");
          __ELEM_consultarCostCenterFuelPorPlaca = new Element (__QNAME_consultarCostCenterFuelPorPlaca, consultarCostCenterFuelPorPlaca,1 ,1, false); 

          javax.microedition.xml.rpc.ComplexType consultarCostCenterFuelPorPlacaResponse = new javax.microedition.xml.rpc.ComplexType();
          consultarCostCenterFuelPorPlacaResponse.elements = (Element[])outTypes;
          javax.xml.namespace.QName __QNAME_consultarCostCenterFuelPorPlacaResponse = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarCostCenterFuelPorPlacaResponse");
          __ELEM_consultarCostCenterFuelPorPlacaResponse = new Element (__QNAME_consultarCostCenterFuelPorPlacaResponse, consultarCostCenterFuelPorPlacaResponse,1 ,1, false); 

          typeMap.put( "__ELEM_consultarCostCenterFuelPorPlaca" , __ELEM_consultarCostCenterFuelPorPlaca);
          typeMap.put( "__ELEM_consultarCostCenterFuelPorPlacaResponse" , __ELEM_consultarCostCenterFuelPorPlacaResponse);

        }
        javax.xml.namespace.QName __QNAME_consultarCostCenterFuelPorPlaca = new javax.xml.namespace.QName("http://services.siscarMobile.ciat/", "consultarCostCenterFuelPorPlaca");

          Object[] inputObjects = new Object[] { _arg0          };
        Operation operation = Operation.newInstance(__QNAME_consultarCostCenterFuelPorPlaca, __ELEM_consultarCostCenterFuelPorPlaca, __ELEM_consultarCostCenterFuelPorPlacaResponse);
        setPropertiesOnOperation(operation);

        Object[] returnObjectArray = (Object[])operation.invoke(inputObjects);
        Object returnObject = returnObjectArray[0];
        ciat.siscar.mobile.services.VoCostCentersFuels[] result = null;
        result = deserialize_ciat_siscar_mobile_services_VoCostCentersFuels( returnObject );
        return result;
    }


   private ciat.siscar.mobile.services.VoCostCentersFuels[] deserialize_ciat_siscar_mobile_services_VoCostCentersFuels( Object obj )
   {
      Object[] objs = (Object[])obj;
      ciat.siscar.mobile.services.VoCostCentersFuels[] result = null;
      if (objs != null) {
         result = new ciat.siscar.mobile.services.VoCostCentersFuels[ objs.length ];
         for (int i = 0; i < objs.length; i++ ) {
            if (objs[i] != null) {
               result[i] = new ciat.siscar.mobile.services.VoCostCentersFuels();
               result[i].deserialize( (Object[])objs[ i ] );
            }
         }
      }
      return result;
   }

}
