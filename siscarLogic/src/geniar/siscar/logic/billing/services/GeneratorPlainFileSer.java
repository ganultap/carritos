package geniar.siscar.logic.billing.services;

import geniar.siscar.model.FlatFileVO;
import geniar.siscar.model.NoveltyTypes;
import gwork.exception.GWorkException;

import java.util.List;

public interface GeneratorPlainFileSer {

	public List<NoveltyTypes> listaNovedades() throws GWorkException;

	public List<FlatFileVO> consultarFlatFile(Long IdNovelty)
			throws GWorkException;

	public void ActualizarEstado(List<FlatFileVO> ListaFlatFile)
			throws GWorkException;
}
