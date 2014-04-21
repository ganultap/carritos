package geniar.siscar.logic.accidents.services;

import java.util.Date;
import java.util.List;

import geniar.siscar.model.Accidents;
import geniar.siscar.model.Driver;
import geniar.siscar.model.InjuredPersons;
import geniar.siscar.model.InvolvedVehicles;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import gwork.exception.GWorkException;

public interface DataAccidentsService {

	public void registroAccidente(String placa, Date accFechaAccidente,
			Long idCities, String accSitioAccidente, String accNumeroSiniestro,
			String accVehiculosInvolucrados, String accTotalMuertos,
			String accTotalHeridos, String accTotalTestigos,
			String accIntervinoTransito, String accNombreConduct,
			String accCargoConduct, String accCodCargoAcc,
			String accDescripcion, Long resultados, Long responsibility,
			Long severity, String accUso, String accReclamo,
			String accValorDano, String accDeduciblesPesos,
			String accDeduciblesDolar, String accDeducible,
			String accCargoDeducible, Long accTipoAsignacion,
			String accNombreAsignatario, String accCedulaConduc,
			String sanciones, String ordenTrabajo, String accRecomendaciones,
			String accObservaciones, String accEmailConductor, String accHora)
			throws GWorkException;

	public void modificarAccidente(Long idAccidente, String placa,
			Date accFechaAccidente, Long idCities, String accSitioAccidente,
			String accNumeroSiniestro, String accVehiculosInvolucrados,
			String accTotalMuertos, String accTotalHeridos,
			String accTotalTestigos, String accIntervinoTransito,
			String accNombreConduct, String accCargoConduct,
			String accCodCargoAcc, String accDescripcion, Long resultados,
			Long responsibility, Long severity, String accUso,
			String accReclamo, String accValorDano, String accDeduciblesPesos,
			String accDeduciblesDolar, String accDeducible,
			String accCargoDeducible, Long accTipoAsignacion,
			String accNombreAsignatario, String accCedulaConduc,
			Long estadoAccidente, String sanciones, String ordenTrabajo,
			String accRecomendaciones, String accObservaciones,
			String accEmailConductor, String accHora) throws GWorkException;

	public Vehicles findVehicleByPlaca(String placa) throws GWorkException;

	public VehiclesAssignation consultarAsignacionVehiculo(String placa)
			throws GWorkException;

	public List<Accidents> findAccidentByPlaca(String placa, Date fechaInicia,
			Date fechaFinal) throws GWorkException;

	public List<Accidents> findAccidentByNumeroSiniestro(Long numeroSiniestro,
			Date fechaInicia, Date fechaFinal) throws GWorkException;

	public List<Accidents> findAccidentByEstado(Long idEstado,
			Date fechaInicia, Date fechaFinal) throws GWorkException;

	public void registrarVehiculosInvolucrados(String hnvTipoVehiculo,
			String hnvPlaca, String hnvMarca, String hnvModelo,
			String hnvIdentifConduc, String hnvConductor,
			String hnvDireccionconductor, String hnvPropietario,
			String hnvTelefono, String hnvIdentifProp, Long idAccidente,
			Long idCiudad) throws GWorkException;

	public void modificarVehiculosInvolucrados(Long idVehiculo,
			String hnvTipoVehiculo, String hnvPlaca, String hnvMarca,
			String hnvModelo, String hnvIdentifConduc, String hnvConductor,
			String hnvDireccionconductor, String hnvPropietario,
			String hnvTelefono, String hnvIdentifProp, Long idAccidente,
			Long idCiudad) throws GWorkException;

	public void registroLesionadosVehiculos(String pivId, String pivNombre,
			String pivDireccion, String pnvTelefono, Long pivEdad,
			String pivSitioatencion, String placa) throws GWorkException;

	public void modificarLesionadosVehiculos(Long idLesionadoVehiculo,
			String pivId, String pivNombre, String pivDireccion,
			String pnvTelefono, Long pivEdad, String pivSitioatencion,
			String placa) throws GWorkException;

	public void eliminarVehiculoInvolucrado(Long idVehiculo)
			throws GWorkException;

	public List<InjuredPersons> lesionadosVehiculos(String placa)
			throws GWorkException;

	public InjuredPersons consultarLesionado(String identicacionLesionado)
			throws GWorkException;

	public void eliminarLesionado(String identificacion) throws GWorkException;

	public List<InvolvedVehicles> listarVehiculosInvolucrados(Long idAccidente)
			throws GWorkException;

	public Accidents consultarAccidente(Long idAccidente) throws GWorkException;

	public List<InjuredPersons> cantidadLesionados(Long idAccidente)
			throws GWorkException;

	public List<Driver> filtroConductores(String cedula, String nombre)
			throws GWorkException;

	/* metodo nuevo */
	public void deleteVehiculoInvolucrado(Long idVehiculo)
			throws GWorkException;

	public void GuardarTestigos(String CantidadTestigos, Long idAccidente)
			throws GWorkException;

	public void GuardarLesionados(String CantidadLesionados, Long idAccidente)
			throws GWorkException;

	public void GuardarVehiculosInvolucrados(
			String CantidadVehiculosInvolucrados, Long idAccidente)
			throws GWorkException;
}
