package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.ParParametros;
import geniar.siscar.model.ParValoresparametros;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public interface ParametrosService {
	
	public  void crearParametros(String strNombre, String descripcion, String strValorIni)throws GWorkException;
	
	public  void actualizarValoresParametros(Long IdParametro, ParParametros parametros, String strValorIni,
			String strValorFin, Date fechaIni, Date fechaFin) throws Exception;
	
	public  void crearValoresParametros(ParParametros parametros, String strValorIni) throws GWorkException;
	
	public  void actualizarParametros(Long idParametro,String strNombre, String descripcion,
			String strValorFin) throws GWorkException;
	
	public  ParParametros buscarParametroPorId(Long id);
	
	public  ParParametros buscarParParametrosPorNombre(String nombre);
	
	public  ParValoresparametros buscarValorParametroPorId(Long id);
	
	public  ParValoresparametros buscarValorParametroPorNombre(Long id);
	
	public  List<ParParametros> getParParametros()throws GWorkException;
	
	public ParValoresparametros consultarUltimoParametro(Long idParametro)throws GWorkException;

}
