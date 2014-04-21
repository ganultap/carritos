package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.SupplyingService;
import geniar.siscar.model.Brands;
import geniar.siscar.model.Lines;
import geniar.siscar.model.Models;
import geniar.siscar.model.SupplyingCatalogs;
import geniar.siscar.persistence.BrandsDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.LinesDAO;
import geniar.siscar.persistence.ModelsDAO;
import geniar.siscar.persistence.SupplyingCatalogsDAO;
import geniar.siscar.util.Util; 
import gwork.exception.GWorkException;

import java.util.List;

public class SupplyingServiceImpl implements SupplyingService {

	public void crearCatalogoAprovisionamientoVehiculo(String num_modelo, String catalogo, Long idMarca, Long idLinea, boolean bandera)
			throws GWorkException {
		
		String catalogoView = catalogo.toUpperCase().trim();
		SupplyingCatalogs catalogs = null;
		Models models = null;
		
		if (SearchVehicles.consultarCatalogoPorNumero(catalogoView) != null)
			throw new GWorkException(Util.loadErrorMessageValue("CATALOGO.EXISTE"));

		List<Models> listModels = new ModelsDAO().findByMdlNombre(num_modelo.trim().toUpperCase());
		if (bandera)
			models = new ModelsDAO().findById(new Long(num_modelo));
		boolean catalogoBd = false;

		if (idMarca == null)
			throw new GWorkException(Util.loadErrorMessageValue("MARCA.NULO"));

		if (idLinea == null)
			throw new GWorkException(Util.loadErrorMessageValue("LINEA.NULO"));

		if (num_modelo == null)
			throw new GWorkException(Util.loadErrorMessageValue("MODELO.NULO"));

		if (listModels != null && listModels.size() > 0)
			throw new GWorkException(Util.loadErrorMessageValue("MODELO.EXISTE"));

		Brands brands = new BrandsDAO().findById(idMarca);

		if (brands == null)
			throw new GWorkException(Util.loadErrorMessageValue("MARCAEXISTEN"));

		Lines lines = new LinesDAO().findById(idLinea);

		if (lines == null)
			throw new GWorkException(Util.loadErrorMessageValue("LINEAEXISTENO"));

		catalogs = new SupplyingCatalogs();
		if (models == null) {
			models = new Models();
			models.setMdlNombre(num_modelo.toUpperCase().trim());
			catalogoBd = true;
		}

		catalogs.setLines(lines);
		catalogs.setSupNumCatalogo(catalogoView);

		catalogs.setModels(models);
		if (catalogoBd)
			guardarModelo(models);

		guardarCatalogo(catalogs);
	}

	public void guardarModelo(Models models) throws GWorkException {
		try {
			validarSession();
			EntityManagerHelper.beginTransaction();
			new ModelsDAO().save(models);
			EntityManagerHelper.commit();
			limpiarSession();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}
	
	
	public void guardarCatalogo(SupplyingCatalogs catalogs) throws GWorkException {
		try {
			validarSession();
			EntityManagerHelper.beginTransaction();
			new SupplyingCatalogsDAO().save(catalogs);
			EntityManagerHelper.commit();
			limpiarSession();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	public void EliminarCatalogo(Long idCatalogo, Long idModelo) throws GWorkException {
		try {
			List<SupplyingCatalogs> list = null;
			validarSession();
			SupplyingCatalogs catalogs = SearchVehicles.consultarCatalogo(idCatalogo);
			Long modelo = catalogs.getModels().getMdlId();
			if (catalogs != null) {
				EntityManagerHelper.beginTransaction();
				new SupplyingCatalogsDAO().delete(catalogs);
				EntityManagerHelper.commit();
				limpiarSession();
			}

			list = SearchVehicles.consultarCatalogoPorIdModelo(idModelo);

			if (modelo != null && list == null)
				EliminarModelo(modelo);
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			validarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	public void EliminarModelo(Long idModelo) throws GWorkException {
		try {
			validarSession();
			Models models = new ModelsDAO().findById(idModelo);
			if (models != null) {
				EntityManagerHelper.beginTransaction();
				new ModelsDAO().delete(models);
				EntityManagerHelper.commit();
				limpiarSession();
			}
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}
	
	public void limpiarSession() {
		EntityManagerHelper.getEntityManager().clear();
		EntityManagerHelper.closeEntityManager();
	}


	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();
	}

	public boolean validarIngresoAprovisionamiento(String idCatalogo) throws GWorkException {
		return SearchVehicles.validarParametrosIngresoAprovisionamiento(idCatalogo);
	}

	public List<SupplyingCatalogs> consultarAprovisionamiento(String numCatalogo, Long marca, Long linea, Long modelo) throws GWorkException {

		return null;
	}

	public List<SupplyingCatalogs> getListCatalogs() throws GWorkException {
		return new SupplyingCatalogsDAO().findAll();
	}

	public static void main(String[] args) throws Exception {
	}
}
