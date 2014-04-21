package geniar.siscar.logic.billing.services;

import geniar.siscar.model.FlatFile;
import geniar.siscar.model.NoveltyTypes;
import gwork.exception.GWorkException;

import java.util.List;

public interface GeneratorPlainFile {
	
	 public List<NoveltyTypes> listaNovedades() throws GWorkException;
	 
	 public List<FlatFile> consultarFlatFile(Long IdNovelty) throws GWorkException;
	 
	 public void ActualizarEstado(List<FlatFile> ListaFlatFile) throws GWorkException;
}
