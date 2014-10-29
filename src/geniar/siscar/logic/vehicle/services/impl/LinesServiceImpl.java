package geniar.siscar.logic.vehicle.services.impl;

import geniar.siscar.logic.vehicle.services.LinesService;

import geniar.siscar.model.Brands;
import geniar.siscar.model.Lines;

import geniar.siscar.persistence.BrandsDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IBrandsDAO;
import geniar.siscar.persistence.ILinesDAO;
import geniar.siscar.persistence.LinesDAO;

import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

public class LinesServiceImpl implements LinesService {

	public void crearLineas(String lnsNombre, Long codigoMrca)
			throws GWorkException {

		if (lnsNombre.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("NOMBRELINEA"));
		if (codigoMrca == null || codigoMrca.longValue() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("MARCA"));

		Lines lines = new Lines();
		ILinesDAO linesDAO = new LinesDAO();

		List<Lines> listLineas = linesDAO.findByLnsNombre(lnsNombre.trim());
		if (listLineas.size() >= 1)
			throw new GWorkException(Util.loadErrorMessageValue("LINEAEXISTE"));

		Brands brands = new Brands();
		IBrandsDAO brandsDAO = new BrandsDAO();
		brands = brandsDAO.findById(codigoMrca);

		if (brands == null)
			throw new GWorkException(Util.loadErrorMessageValue("MARCAEXISTEN"));

		lines.setLnsNombre(lnsNombre.toUpperCase().trim());
		lines.setBrands(brands);
		EntityManagerHelper.getEntityManager().getTransaction().begin();
		linesDAO.save(lines);
		EntityManagerHelper.commit();

	}

	public Lines consultarLineasByName(String lnsNombre) throws GWorkException {
		ILinesDAO linesDAO = new LinesDAO();
		List<Lines> listLineas = linesDAO.findByLnsNombre(lnsNombre);
		if (listLineas.size() >= 1)
			return listLineas.get(0);
		else
			// TODO : GworkExcepcion
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

	}

	public void eliminarLineas(Long idLine) throws GWorkException {

		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();

		Lines lines = new Lines();
		ILinesDAO linesDAO = new LinesDAO();
		lines = linesDAO.findById(idLine);
		if (lines == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("LINEAEXISTENO"));
		if (!lines.getVehicleses().isEmpty() || !lines.getTariffses().isEmpty()
				|| !lines.getSupplyingCatalogses().isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("ELIMINARLINEA"));

		EntityManagerHelper.getEntityManager().getTransaction().begin();
		linesDAO.delete(lines);
		EntityManagerHelper.commit();
		EntityManagerHelper.getEntityManager().clear();
	}

	public List<Lines> listLineas() throws GWorkException {
		ILinesDAO linesDAO = new LinesDAO();
		List<Lines> listLineas = linesDAO.findAll();
		if (listLineas.size() < 1)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		else
			return listLineas;

	}

	public Lines consultarLineasById(Long codigo) throws GWorkException {
		ILinesDAO linesDAO = new LinesDAO();
		Lines lines = linesDAO.findById(codigo);
		if (lines == null)
			// TODO:GworkException
			throw new GWorkException("CONSULTA");
		else
			return lines;

	}

	public void modificarLinea(Long idLine, String lnsNombre, Long idBrand)
			throws GWorkException {

		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();

		if (idLine == null || idLine.longValue() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("LINEAEXISTENO"));
		if (lnsNombre.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("NOMBRELINEA"));
		if (idBrand == null || idBrand.longValue() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("MARCA"));

		Lines lines = new Lines();
		Brands brands = new Brands();
		IBrandsDAO brandsDAO = new BrandsDAO();
		brands = brandsDAO.findById(idBrand);
		ILinesDAO linesDAO = new LinesDAO();
		lines = linesDAO.findById(idLine);

		if (!linesDAO.findByLnsNombre(lnsNombre.trim()).isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("LINEAEXISTE"));

		if (lines == null)
			// TODO:Exception Line
			throw new GWorkException(Util
					.loadErrorMessageValue("LINEAEXISTENO"));

		if (brands == null)
			throw new GWorkException(Util.loadErrorMessageValue("MARCAEXISTEN"));

		EntityManagerHelper.getEntityManager().getTransaction().begin();
		lines.setLnsNombre(lnsNombre.toUpperCase().trim());
		lines.setBrands(brands);
		linesDAO.update(lines);
		EntityManagerHelper.commit();

	}
}
