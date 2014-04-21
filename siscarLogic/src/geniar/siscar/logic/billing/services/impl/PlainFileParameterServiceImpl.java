package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.logic.billing.services.PlainFileParameterService;
import geniar.siscar.logic.consultas.SearchCurrencyType;
import geniar.siscar.logic.consultas.SearchNoveltyTypes;
import geniar.siscar.logic.consultas.SearchPlainFileParameter;
import geniar.siscar.model.CurrencyTypes;
import geniar.siscar.model.NoveltyTypes;
import geniar.siscar.model.PlainFileParameter;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.PlainFileParameterDAO;
import gwork.exception.GWorkException;

import java.util.List;

public class PlainFileParameterServiceImpl 
implements PlainFileParameterService {

	/*
	 * (non-Javadoc)
	 * @see geniar.siscar.logic.parameters.services.
	 * PlainFileParameterService#consultarPlainFileParameter
	 * (java.lang.Long, java.lang.Long)
	 */
	public PlainFileParameter consultarPlainFileParameter(Long idTipoMoneda,
			Long idTipoNovedad) throws GWorkException {
		return new SearchPlainFileParameter().consultar_PFP_PorId(
				idTipoMoneda, idTipoNovedad);
	}

	/*
	 * (non-Javadoc)
	 * @see geniar.siscar.logic.parameters.services.
	 * PlainFileParameterService#crearPlainFileParameter(
	 * java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public void crearPlainFileParameter(Long idTipoMoneda, Long idTipoNovedad,
			Long conceptoNomina, String descripcion) throws GWorkException {

		CurrencyTypes currencyTypes = 
			new SearchCurrencyType().consultarTipoMonedaPorId(idTipoMoneda);

		if (currencyTypes == null) {
			throw new GWorkException(
			"El tipo de moneda no existe");
		}

		NoveltyTypes noveltyTypes = 
			new SearchNoveltyTypes().consultarTipoNovedadPorId(idTipoNovedad);

		if (noveltyTypes == null) {
			throw new GWorkException(
			"El tipo de novedad no existe");
		}

		if (new SearchPlainFileParameter().consultar_PFP_PorId(
				idTipoMoneda, idTipoNovedad) != null) {
			throw new GWorkException(
					"Ya existen datos para el tipo de moneda y de novedad " +
			"ingresados");			
		}

		PlainFileParameter plainFileParameter =
			new PlainFileParameter();

		plainFileParameter.setCurrencyTypes(currencyTypes);
		plainFileParameter.setNoveltyTypes(noveltyTypes);
		plainFileParameter.setPfpConceptonomina(conceptoNomina);
		plainFileParameter.setPfpDescripcion(descripcion);

		EntityManagerHelper.beginTransaction();
		new PlainFileParameterDAO().save(plainFileParameter);
		EntityManagerHelper.commit();
	}

	/*
	 * (non-Javadoc)
	 * @see geniar.siscar.logic.parameters.services.
	 * PlainFileParameterService#listarTodosPlainFileParameters()
	 */
	public List<PlainFileParameter> listarTodosPlainFileParameters()
	throws GWorkException {
		return new PlainFileParameterDAO().findAll();
	}

	/*
	 * (non-Javadoc)
	 * @see geniar.siscar.logic.parameters.services.
	 * PlainFileParameterService#modificarPlainFileParameter(
	 * java.lang.Long, java.lang.Long)
	 */
	public void modificarPlainFileParameter(Long idTipoMoneda,
			Long idTipoNovedad, Long conceptoNomina, String descripcion)
				throws GWorkException {

		PlainFileParameter plainFileParameter = new SearchPlainFileParameter()
			.consultar_PFP_PorId(idTipoMoneda, idTipoNovedad); 

		if (plainFileParameter == null) {
			throw new GWorkException(
					"No existen datos para el tipo de moneda y de novedad " +
			"ingresados");			
		}

		plainFileParameter.setPfpConceptonomina(conceptoNomina);
		plainFileParameter.setPfpDescripcion(descripcion);

		EntityManagerHelper.beginTransaction();
		new PlainFileParameterDAO().update(plainFileParameter);
		EntityManagerHelper.commit();

	}
}
