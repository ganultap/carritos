package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.vehicle.services.BrandService;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.Brands;
import geniar.siscar.model.HeaderProof;
import geniar.siscar.model.Lines;
import geniar.siscar.persistence.BrandsDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IBrandsDAO;
import geniar.siscar.persistence.LinesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandServiceImpl.
 *
 * @author Jorge Ojeda - GeniarArq S.A
 * @version 1.0 Descripción : Clase encargada del manejo de session
 */

public class BrandServiceImpl implements BrandService {

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.BrandService#consultarMarcaPorNombre(java.lang.String)
	 */
	public Brands consultarMarcaPorNombre(String mrcNombre)
			throws GWorkException {
		IBrandsDAO BransDAO = new BrandsDAO();
		List<Brands> listMarcas = BransDAO.findByBrnNombre(mrcNombre);
		if (listMarcas.size() >= 1) {
			return listMarcas.get(0);
		} else {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.BrandService#listaMarcas()
	 */
	public List<Brands> listaMarcas() throws GWorkException {
		IBrandsDAO BransDAO = new BrandsDAO();
		List<Brands> listMarcas = BransDAO.findAll();
		if (listMarcas.size() < 1)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		else
			return listMarcas;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.BrandService#crearMarcas(java.lang.String)
	 */
	public void crearMarcas(String mrcNombre) throws GWorkException {

		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();
		
		Brands Brans = new Brands();
		IBrandsDAO BransDAO = new BrandsDAO();
		/** Se consulta si existe ua marca con el mismo nombre */
		List<Brands> listMarcas = BransDAO.findByBrnNombre(mrcNombre.trim());
		if (listMarcas.size() >= 1) {
			throw new GWorkException(Util.loadErrorMessageValue("NOMBRE"));
		} else {
			/** Si no existe marcas con el mismo nombre se pasa a crear */
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			Brans.setBrnNombre(mrcNombre.toUpperCase().trim());
			BransDAO.update(Brans);
			EntityManagerHelper.commit();
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.BrandService#eliminarMarca(java.lang.Long)
	 */
	public void eliminarMarca(Long idBrand) throws GWorkException {

		Brands brands = new Brands();
		IBrandsDAO brandsDAO = new BrandsDAO();
		brands = brandsDAO.findById(idBrand);
		/** Consulta que la marca exista */
		if (brands == null)
			throw new GWorkException(Util.loadErrorMessageValue("MARCAEXISTEN"));
		if (!brands.getLineses().isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("ELIMINARMARCA"));
		EntityManagerHelper.getEntityManager().getTransaction().begin();
		brandsDAO.delete(brands);
		EntityManagerHelper.commit();

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.BrandService#modificarMarca(java.lang.Long, java.lang.String)
	 */
	public void modificarMarca(Long idBrand, String mrcNombre)
			throws GWorkException {
		Brands brands = new Brands();
		IBrandsDAO brandsDAO = new BrandsDAO();
		brands = brandsDAO.findById(idBrand);

		/** Consulta que la marca exista */
		if (brands == null)
			throw new GWorkException(Util.loadErrorMessageValue("MARCAEXISTEN"));
		if (!brandsDAO.findByBrnNombre(mrcNombre.trim()).isEmpty())
			// TODO Organizar Mensaje
			throw new GWorkException(Util.loadErrorMessageValue("MARCAEXISTE"));

		EntityManagerHelper.getEntityManager().getTransaction().begin();
		brands.setBrnNombre(mrcNombre.toUpperCase().trim());
		brandsDAO.update(brands);
		EntityManagerHelper.commit();

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.BrandService#consultarMarcaPorId(java.lang.Long)
	 */
	public Brands consultarMarcaPorId(Long id) throws GWorkException {
		Brands brands = new BrandsDAO().findById(id);
		if (brands == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return brands;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.BrandService#listLines()
	 */
	public List<Lines> listLines() throws GWorkException {
		LinesDAO linesDAO = new LinesDAO();
		List<Lines> listLines = linesDAO.findAll();
		if (listLines.size() == 0 || listLines == null)
			throw new GWorkException("No hay Lineas para consultar");
		return listLines;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.vehicle.services.BrandService#pruebasContables()
	 */
	@SuppressWarnings("unchecked")
	public void pruebasContables() throws GWorkException {
		List<HeaderProof> listHeaderProof = null;
		try{
			final String queryString0 = "select model "
					+ "from HeaderProof model WHERE model.hepGroupId in (10712," +
							"10757,10904,10906,11002,11036,11159,11181,11427,11439," +
							"11513,12074,12090,12112,12113,12114,12115,12116,12117," +
							"12118,12175,12406,12701,12702,12703,201011121839862)";//12318,12319,
			
			final String queryString = "select model "
				+ "from HeaderProof model WHERE model.hepGroupId in (12318,12319)";//,
			
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			
			listHeaderProof = query.getResultList();
	
			for (HeaderProof headerProof : listHeaderProof) {
	
				Set<ActualOthersApplications> listActual = headerProof.getActualOthersApplicationses();
	
				for (ActualOthersApplications actual : listActual) {
					//System.err.println(actual.getPEntDr());
						ConsultsServiceImpl.insercionContable(actual.getPSob(),
								actual.getPAccdate(), actual.getPCurr(),
								"GVALENCIA", actual.getPCategory(), actual
										.getPSource(), null, null, null, actual
										.getPCompany(), actual.getPAccount(),
								actual.getPCcenter(), actual.getPAuxiliary(),
								actual.getPEntDr(), actual.getPEntCr(), actual
										.getPBname(), actual.getPDescription(),
								null, actual.getPAttribute2(), actual
										.getPAttribute5(), actual.getPAttribute6(),
								null, null, actual.getPAttribute9(), actual
										.getPAttribute10(), actual.getHeaderProof()
										.getHepGroupId());
				}
			}		
		}catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}
}
