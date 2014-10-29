package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.parameters.services.CountriesService;
import geniar.siscar.model.Cities;
import geniar.siscar.model.Countries;
import geniar.siscar.persistence.CitiesDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.view.vehicle.VehiclePage;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 * @author jam Geniar Arq
 */

public class SelectItemCountryPage {
	private SelectItem[] listCountry;
	private SelectItem[] listCity;
	private SelectItem[] listCityDefault;
	private SelectItem[] listarCiudades;
	private SelectItem[] listCountryDefault;
	private SelectItem[] listCityUbication;
	private CountriesService countriesService;
	private List<Cities> setCities;
	private List<Cities> setCitiesDefault;
	private Set<Countries> setCountryDefault;
	private List<Countries> setCountry;
	private List<Countries> listUtilCountry;

	public SelectItem[] getListCountryDefault() throws GWorkException {

		listUtilCountry = SearchParameters.getAllCountries();
		listCountryDefault = new SelectItem[listUtilCountry.size() + 1];
		int i = 1;
		listCountryDefault[0] = new SelectItem("-1", "--SELECCIONAR--");
		for (Countries countries : listUtilCountry) {
			listCountryDefault[i] = new SelectItem(countries.getCntId(),
					countries.getCntNombre());
			i++;
		}
		return listCountryDefault;
	}

	public SelectItem[] getListCountry() throws GWorkException {

		if (getSetCountry() != null && getSetCountry().size() > 0) {
			listCountry = new SelectItem[getSetCountry().size() + 1];
			int i = 1;
			listCountry[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (Countries countries : getSetCountry()) {
				listCountry[i] = new SelectItem(countries.getCntId(), countries
						.getCntNombre());
				i++;
			}
		}
		return listCountry;
	}

	public void changeUbicationTypesCountries(ValueChangeEvent event)
			throws GWorkException {
		if (event.getNewValue() != null) {
			Long idTipoUbicacion = (Long) event.getNewValue();
			if (idTipoUbicacion != null && idTipoUbicacion != -1L) {
				setCountry = SearchVehicles.consultarUbicacion(idTipoUbicacion);
				setSetCountry(setCountry);
			}
		}
	}

	public void changeCountriesDefault(ValueChangeEvent event)
			throws GWorkException {
		Long sbCode = null;
		if (event.getNewValue() != null) {
			sbCode = (Long) event.getNewValue();
			setCitiesDefault = SearchVehicles.ConsultarUbicacionCiudad(sbCode);
			setSetCitiesDefault(setCitiesDefault);
		}
	}

	public void listener_listarCiudades(ValueChangeEvent event)
			throws GWorkException {
		Long sbCode = null;
		if (event.getNewValue() != null) {
			sbCode = (Long) event.getNewValue();
			setCitiesDefault = SearchVehicles.ConsultarUbicacionCiudad(sbCode);
			setSetCitiesDefault(setCitiesDefault);

			listarCiudades = new SelectItem[getSetCitiesDefault().size() + 1];
			int i = 1;
			listarCiudades[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (Cities cities : setCitiesDefault) {
				listarCiudades[i] = new SelectItem(cities.getCtsId(), cities
						.getCtsNombre());
				i++;
			}
			setListarCiudades(listarCiudades);
		}
	}

	public void changeCountriesDefault2(ValueChangeEvent event)
			throws GWorkException {
		Long sbCode = null;
		if (event.getNewValue() != null) {
			sbCode = (Long) event.getNewValue();
			setCitiesDefault = SearchVehicles.ConsultarUbicacionCiudad(sbCode);
			setSetCitiesDefault(setCitiesDefault);
		}
	}

	public void changeCitesDefault(ValueChangeEvent event) {
		Long sbCode = null;
		if (event.getNewValue() != null) {
			sbCode = (Long) event.getNewValue();
			Cities cities = new CitiesDAO().findById(sbCode);
			VehiclePage vehicle = (VehiclePage) FacesUtils
					.getManagedBean("vehiclePage");
			if (vehicle != null && cities != null) {
				vehicle.setVhcCiuAduan(cities.getCtsNombre());
			}
		}
	}

	public void changeCountries(ValueChangeEvent event) {
		if (event.getNewValue() != null) {
			Long sbCode = (Long) event.getNewValue();
			for (Countries countries : getSetCountry()) {
				if (sbCode != null)
					if (countries.getCntId() == sbCode.longValue()) {
						List<Cities> list = new ArrayList<Cities>();
						try {
							list = SearchVehicles
									.ConsultarUbicacionCiudad(sbCode);
						} catch (GWorkException e) {
							e.printStackTrace();
						}

						if (list == null || list.isEmpty()) {
							setCities.clear();
							setSetCities(setCities);
							listCity = new SelectItem[1];
							listCity[0] = new SelectItem("-1",
									"--SELECCIONAR--");
							break;
						}
						setCities = new ArrayList<Cities>();
						for (Cities cities : list) {
							setCities.add(cities);
						}
						setSetCities(list);
					}
			}
		}
	}

	public void setListCountry(SelectItem[] listCountry) {
		this.listCountry = listCountry;
	}

	public SelectItem[] getListCityDefault() {

		if (getSetCitiesDefault() != null && getSetCitiesDefault().size() > 0) {
			listCityDefault = new SelectItem[getSetCitiesDefault().size() + 1];
			int i = 1;
			listCityDefault[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (Cities cities : setCitiesDefault) {
				listCityDefault[i] = new SelectItem(cities.getCtsId(), cities
						.getCtsNombre());
				i++;
			}
			setListCity(listCity);
		}
		return listCityDefault;
	}

	public SelectItem[] getListCity() {
		if (getSetCities() != null && getSetCities().size() > 0) {
			listCity = new SelectItem[getSetCities().size() + 1];
			int i = 1;
			listCity[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (Cities cities : setCities) {
				listCity[i] = new SelectItem(cities.getCtsId(), cities
						.getCtsNombre());
				i++;
			}
			setListCity(listCity);
		}
		return listCity;
	}

	public void setListCity(SelectItem[] listCity) {
		this.listCity = listCity;
	}

	public CountriesService getCountriesService() {
		return countriesService;
	}

	public void setCountriesService(CountriesService countriesService) {
		this.countriesService = countriesService;
	}

	public List<Cities> getSetCities() {
		return setCities;
	}

	public void setSetCities(List<Cities> setCities) {
		this.setCities = setCities;
	}

	public SelectItem[] getListCityUbication() {
		List<Cities> listUbications = SearchParameters.getAllCities();
		if (listUbications != null && listUbications.size() > 0) {
			listCityUbication = new SelectItem[listUbications.size() + 1];
			int i = 1;
			listCityUbication[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (Cities cities : listUbications) {
				listCityUbication[i] = new SelectItem(cities.getCtsId(), cities
						.getCtsNombre());
				i++;
			}
		}
		return listCityUbication;
	}

	public void setListCityUbication(SelectItem[] listCityUbication) {
		this.listCityUbication = listCityUbication;
	}

	public List<Countries> getSetCountry() {
		return setCountry;
	}

	public void setSetCountry(List<Countries> setCountry) {
		this.setCountry = setCountry;
	}

	public List<Countries> getListUtilCountry() {
		return listUtilCountry;
	}

	public void setListUtilCountry(List<Countries> listUtilCountry) {
		this.listUtilCountry = listUtilCountry;
	}

	public List<Cities> getSetCitiesDefault() {
		return setCitiesDefault;
	}

	public void setSetCitiesDefault(List<Cities> setCitiesDefault) {
		this.setCitiesDefault = setCitiesDefault;
	}

	public Set<Countries> getSetCountryDefault() {
		return setCountryDefault;
	}

	public void setSetCountryDefault(Set<Countries> setCountryDefault) {
		this.setCountryDefault = setCountryDefault;
	}

	public SelectItem[] getListarCiudades() {
		return listarCiudades;
	}

	public void setListarCiudades(SelectItem[] listarCiudades) {
		this.listarCiudades = listarCiudades;
	}
}
