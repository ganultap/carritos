package geniar.siscar.logic.accidents.services;

import java.util.Date;
import java.util.List;

import geniar.siscar.model.Accidents;
import geniar.siscar.model.Acts;
import geniar.siscar.model.Assistants;
import gwork.exception.GWorkException;

public interface AccidentActsService {

	public void generarActaSubcomite(Date actFechaReunion,
			String actDescripcion, String actObservaciones,
			List<Accidents> listaAccidentes, List<Assistants> listaAsistentes)
			throws GWorkException;

	public void modificarActaSubcomite(Long actCodigo, Date actFechaReunion,
			String actDescripcion, String actObservaciones,
			String actAprobacion, Date actFechaAprobacion,
			List<Accidents> listaAccidentes, List<Assistants> listaAsistentes)
			throws GWorkException;

	public List<Acts> consultarActa(Long numeroActa, Date fechaIni,
			Date fechaFin) throws GWorkException;

	public List<Accidents> accidentesByActa(Long numeroActa)
			throws GWorkException;

	public void actualizarActaAccidente(Long idAccidente) throws GWorkException;

	public void modificarActaAccidente(Long numAccidente,
			String deduciblesPesos, String deduciblesDolar,
			Long responsibility, Long severity, String ordenTrabajo,
			String sanciones, String observaciones, String descripcion,
			String accRecomendaciones) throws GWorkException;

	public List<Assistants> cosultarAssistenteActa(Long idActa)
			throws GWorkException;

	public void enviarNotificacionAsistente(String email, String CuerpoMensaje)
			throws GWorkException;

	/**
	 * Find accidents with several parameters
	 * 
	 * @param placa
	 * @param numeroSiniestro
	 * @param idEstado
	 * @param fechaIni
	 * @param fechaFin
	 * @return
	 * @throws GWorkException
	 */
	public List<Accidents> filtroActaAccidente(String placa,
			Long numeroSiniestro, Long idEstado, Date fechaIni,
			Date fechaFin) throws GWorkException;
}
