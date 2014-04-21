package geniar.siscar.logic.consultas;

import geniar.siscar.model.FlatFile;
import geniar.siscar.model.FlatFileVO;
import geniar.siscar.model.Period;
import geniar.siscar.model.Users;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchFlatFile {

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Consulta un objeto de tipo {@link Period} con la combina ción de
	 * identificadores especificadas en los parametros.
	 * 
	 * @param nombre
	 *            del periodo.
	 * @return Un objeto de tipo {@link Period}.
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public List<FlatFileVO> consultar_FF_PorNovedad(Long IdNovelty)
			throws GWorkException {
		try {
			String queryString = "";
			List<FlatFile> listaFlatFile = new ArrayList<FlatFile>();
			List<FlatFileVO> listaFlatFileVO = new ArrayList<FlatFileVO>();
			List<Users> listaUsuarios = new ArrayList<Users>();

			queryString = "select ff from FlatFile ff "
					+ "where ff.noveltyTypes.ntId = :IdNovelty "
					+ "AND (ff.ffEstado = :Estado OR ff.ffEstado IS NULL) "
					+ "ORDER BY ff.ffCarne ASC";

			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("IdNovelty", IdNovelty);
			query.setParameter("Estado", new Long(0));

			listaFlatFile = query.getResultList();

			if (listaFlatFile != null) {
				for (FlatFile flatFile : listaFlatFile) {
					listaUsuarios = SearchUser.consultarUsuarios(null,
							flatFile.getFfCarne().toString(), 2);

					if (listaUsuarios != null && listaUsuarios.size() > 0) {
						Users user = listaUsuarios.get(0);
						
						FlatFileVO flatFileVO = new FlatFileVO();
						flatFileVO.setFfCarne(flatFile.getFfCarne().toString());
						flatFileVO.setFfAsignatario(user.getUsrNombre());
						if (user.getUsrApellido() != null)
							flatFileVO.setFfAsignatario(user.getUsrNombre() + " "
									+ user.getUsrApellido());
						flatFileVO.setFfConcepto(flatFile.getFfConcepto()
								.longValue());
						flatFileVO.setFfUnidades(flatFile.getFfUnidades()
								.longValue());
						flatFileVO.setFfValor(flatFile.getFfValor().toString());
						flatFileVO.setFfFecha(flatFile.getFfFecha().longValue());
						flatFileVO.setFfDocumento(flatFile.getFfDocumento()
								.toString());
						flatFileVO.setFfMoneda(flatFile.getFfMoneda().longValue());
						flatFileVO.setFfDescripcion(flatFile.getFfDescripcion()
								.toString());
						flatFileVO.setFfCodigo(flatFile.getFfCodigo().longValue());
						listaFlatFileVO.add(flatFileVO);
					}else
						throw new GWorkException(Util
								.loadErrorMessageValue("search.not.found") + " para el carné " + flatFile.getFfCarne().toString() + ". Deberá crear el usuario");			

					//Users user = SearchUser.consultarUsuarios(null,
					//		flatFile.getFfCarne().toString(), 2).get(0);
					
				}

				return listaFlatFileVO;
			} else
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));

		} catch (RuntimeException re) {
			throw new GWorkException(Util
					.loadErrorMessageValue("search.not.found"));
		}
	}
}