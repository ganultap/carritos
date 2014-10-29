/**
 * ciat.siscar.mobile.services.SiscarDelegateStub.java
 * Interface v.1.0
 *
 * Generated on Tue Feb 03 00:17:51 GMT 2009
 * Based on WSDL at http://localhost:8082/siscar.services/SiscarService?wsdl
 */

package ciat.siscar.mobile.services;

public interface SiscarDelegate extends java.rmi.Remote {
    public java.lang.String validarLogin(java.lang.String _arg0, java.lang.String _arg1) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public java.lang.String consultarAuxiliarCiat(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public ciat.siscar.mobile.services.VoEmployee consultEmpleoyeeName(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public java.lang.String consultarValorTarifaCombustibleCIAT(java.lang.Long _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public ciat.siscar.mobile.services.VoModel consultCostCenters(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public ciat.siscar.mobile.services.VoModel consultTypeRequest(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public ciat.siscar.mobile.services.VoModel consultPums(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public java.lang.String consultarValorTarifaCombustibleCALI(java.lang.Long _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public ciat.siscar.mobile.services.VoCostCenters consultarCentroCostoVO(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public ciat.siscar.mobile.services.VoCostCentersFuels consultCostCenterFuelByPlaca(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public java.lang.String obtenerTarifa(int _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public ciat.siscar.mobile.services.ValidacionSolicitante validarSolicitante(int _arg0, java.lang.String _arg1) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public ciat.siscar.mobile.services.ValidacionVehiculo validarVehiculo(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public boolean validarCentroCosto(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public void guardarServicioRegistro(java.lang.Long _arg0, java.lang.String _arg1, java.lang.String _arg2, java.lang.String _arg3, java.lang.String _arg4, java.lang.String _arg5, java.lang.Float _arg6, java.lang.Float _arg7, java.lang.String _arg8, java.lang.Long _arg9, java.lang.Long _arg10, java.lang.Long _arg11, java.lang.String _arg12, java.lang.Long _arg13, java.lang.Long _arg14, java.lang.Float _arg15, java.lang.Long _arg16) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
    public ciat.siscar.mobile.services.VoCostCentersFuels[] consultarCostCenterFuelPorPlaca(java.lang.String _arg0) throws java.rmi.RemoteException, javax.xml.rpc.JAXRPCException;
}
