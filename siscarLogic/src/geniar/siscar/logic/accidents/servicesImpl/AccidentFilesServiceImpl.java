package geniar.siscar.logic.accidents.servicesImpl;

import java.io.File;
import java.util.List;

import geniar.siscar.logic.accidents.services.AccidentFilesService;
import geniar.siscar.model.AccidentsFiles;
import geniar.siscar.persistence.AccidentsDAO;
import geniar.siscar.persistence.AccidentsFilesDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IAccidentsFilesDAO;
import geniar.siscar.util.FileUtils;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class AccidentFilesServiceImpl implements AccidentFilesService {

	public void eliminarArchivosAccidente(Long idArchivoAccidente)
			throws GWorkException {

		AccidentsFiles accidentsFiles = new AccidentsFiles();
		IAccidentsFilesDAO accidentsFilesDAO = new AccidentsFilesDAO();
		accidentsFiles = accidentsFilesDAO.findById(idArchivoAccidente);

		if (accidentsFiles == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ARCHIVO.EXISTEN"));

		EntityManagerHelper.beginTransaction();
		accidentsFilesDAO.delete(accidentsFiles);
		FileUtils.deleteFile(new File(accidentsFiles.getAcfRuta() + "\\"
				+ accidentsFiles.getAcfNombre()));
		EntityManagerHelper.commit();

	}

	public void modificarArchivosAccidente(Long idArchivoAccidente,
			Long idAccidente, String acfRuta, String acfDescripcion,
			String acfNombre) throws GWorkException {

		AccidentsFiles accidentsFiles = new AccidentsFiles();
		IAccidentsFilesDAO accidentsFilesDAO = new AccidentsFilesDAO();
		accidentsFiles = accidentsFilesDAO.findById(idArchivoAccidente);

		if (accidentsFiles == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ARCHIVO.EXISTEN"));

		accidentsFiles.setAccidents(new AccidentsDAO().findById(idAccidente));
		accidentsFiles.setAcfDescripcion(acfDescripcion);
		accidentsFiles.setAcfNombre(acfNombre);
		accidentsFiles.setAcfRuta(acfRuta);

		EntityManagerHelper.beginTransaction();
		accidentsFilesDAO.update(accidentsFiles);
		EntityManagerHelper.commit();

	}

	public void registrarArchivosAccidente(Long idAccidente, String acfRuta,
			String acfDescripcion, String acfNombre) throws GWorkException {

		AccidentsFiles accidentsFiles = new AccidentsFiles();
		IAccidentsFilesDAO accidentsFilesDAO = new AccidentsFilesDAO();

		accidentsFiles.setAccidents(new AccidentsDAO().findById(idAccidente));
		accidentsFiles.setAcfDescripcion(acfDescripcion);
		accidentsFiles.setAcfNombre(acfNombre);
		accidentsFiles.setAcfRuta(acfRuta);
		
		//Verificar que no exista el archivo
		List<AccidentsFiles> listAccidentFiles = accidentsFilesDAO.findByAcfNombre(acfNombre);

		if(listAccidentFiles==null || listAccidentFiles.size()==0){
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			accidentsFilesDAO.save(accidentsFiles);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
		}
		else
			throw new GWorkException(Util.loadErrorMessageValue("ARCHIVO.EXISTE"));
	}

	public List<AccidentsFiles> listarArchivos(Long idAccidente)
			throws GWorkException {

		List<AccidentsFiles> listAccidentsFiles = new SearchParametersAccidents()
				.listarArchivos(idAccidente);

		if (listAccidentsFiles == null || listAccidentsFiles.size() == 0
				|| listAccidentsFiles.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listAccidentsFiles;
	}

}
