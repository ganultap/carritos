package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.logic.billing.services.GeneratorPlainFileSer;
import geniar.siscar.logic.consultas.SearchFlatFile;
import geniar.siscar.model.FlatFile;
import geniar.siscar.model.FlatFileVO;
import geniar.siscar.model.NoveltyTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FlatFileDAO;
import geniar.siscar.persistence.NoveltyTypesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public class GeneratorPlainFileImpl implements GeneratorPlainFileSer {

	public List<NoveltyTypes> listaNovedades() throws GWorkException {
		NoveltyTypesDAO noveltyTypesDAO = new NoveltyTypesDAO();
		List<NoveltyTypes> listaTipoNovedad = noveltyTypesDAO.findAll();
		if (listaTipoNovedad.size() < 1)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		else
			return listaTipoNovedad;
	}

	public List<FlatFileVO> consultarFlatFile(Long IdNovelty)
			throws GWorkException {
		return new SearchFlatFile().consultar_FF_PorNovedad(IdNovelty);
	}

	public void ActualizarEstado(List<FlatFileVO> listFlatFile)
			throws GWorkException {
		try {

			Long Estado = new Long(1);

			for (FlatFileVO flatFile : listFlatFile) {

				FlatFile objListFaltFile = new FlatFileDAO().findById(flatFile
						.getFfCodigo());

				EntityManagerHelper.getEntityManager().refresh(objListFaltFile);

				objListFaltFile.setFfEstado(Estado);
				objListFaltFile.setFfFechaNomina(new Date());

				new FlatFileDAO().update(objListFaltFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		EntityManagerHelper.getEntityManager().getTransaction().begin();
		EntityManagerHelper.getEntityManager().getTransaction().commit();

	}
}
