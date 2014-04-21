package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.parameters.services.LocationService;
import geniar.siscar.logic.parameters.services.LocationTypesService;
import geniar.siscar.model.Locations;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.view.vehicle.VehiclePage;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class SelectItemLocationPage {

	private SelectItem listLocationsTypes[];
	private SelectItem listLocations[];
	private LocationService locationService;
	private List<LocationsTypes> listUtilLocationTypes;
	private List<Locations> setLocations;
	private LocationTypesService locationTypesService;
	private HtmlSelectOneMenu selectOneMenuLocations;
	private HtmlSelectOneMenu selectOneMenuLocationTypes;
	private boolean postinol = true;
	private int temp = 0;

	public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public SelectItem[] getListLocationsTypes() {

		try {
			listLocationsTypes = null;
			listUtilLocationTypes = SearchParameters.getAllLocationsTypes();
			listLocationsTypes = new SelectItem[listUtilLocationTypes.size() + 1];
			listLocationsTypes[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;

			for (LocationsTypes locationsTypes : listUtilLocationTypes) {
				listLocationsTypes[i] = new SelectItem(locationsTypes.getLctCodigo(), locationsTypes.getLctNombre());
				i++;

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listLocationsTypes;
	}

	public void setListLocationsTypes(SelectItem[] listLocationsTypes) {
		this.listLocationsTypes = listLocationsTypes;
	}

	public LocationTypesService getLocationTypesService() {
		return locationTypesService;
	}

	public void setLocationTypesService(LocationTypesService locationTypesService) {
		this.locationTypesService = locationTypesService;
	}

	public SelectItem[] getListLocations() {
		if (getSetLocations() == null)
			setLocations = new ArrayList<Locations>(1);

		listLocations = null;
		listLocations = new SelectItem[getSetLocations().size() + 1];
		int i = 1;
		listLocations[0] = new SelectItem("-1", "--SELECCIONAR--");
		for (Locations locations : setLocations) {
			listLocations[i] = new SelectItem(locations.getCities().getCtsId(), locations.getCities().getCtsNombre());
			i++;
		}
		return listLocations;
	}

	public void setListLocations(SelectItem[] listLocations) {
		this.listLocations = listLocations;
	}

	public void changeLocation(ValueChangeEvent changeEvent) {
		try {
			String idLocationType = null;
			List<Locations> setLocations = null;
			if (changeEvent.getNewValue() != null) {
				idLocationType = (String) changeEvent.getNewValue().toString();

				listLocations = null;
				if (idLocationType != null && !idLocationType.equalsIgnoreCase("-1")) {
					setLocations = SearchVehicles.consultarUbicacionPorIdTipo(new Long(idLocationType));
					setSetLocations(setLocations);
				} else {
					setSetLocations(null);
					VehiclePage vehicle = (VehiclePage) FacesUtils.getManagedBean("vehiclePage");
					if (vehicle != null)
						vehicle.setCountryName(null);
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void mostrarPais(ValueChangeEvent event) throws GWorkException {
		Locations locations = null;
		String idLocation = null;
		VehiclePage vehicle = (VehiclePage) FacesUtils.getManagedBean("vehiclePage");

		if (event.getNewValue() != null) {
			idLocation = event.getNewValue().toString();

			if (idLocation != null && !idLocation.equalsIgnoreCase("-1"))
				locations = SearchVehicles.consultarDatosUbicacionPorIdCiudad(new Long(idLocation));

			if (locations != null && locations.getCities() != null && vehicle != null) {
				vehicle.setCountryName(locations.getCities().getCountries().getCntNombre());
			} else if (vehicle != null)
				vehicle.setCountryName(null);
		}
	}

	public HtmlSelectOneMenu getSelectOneMenuLocations() {
		return selectOneMenuLocations;
	}

	public void setSelectOneMenuLocations(HtmlSelectOneMenu selectOneMenuLocations) {
		this.selectOneMenuLocations = selectOneMenuLocations;
	}

	public HtmlSelectOneMenu getSelectOneMenuLocationTypes() {
		return selectOneMenuLocationTypes;
	}

	public void setSelectOneMenuLocationTypes(HtmlSelectOneMenu selectOneMenuLocationTypes) {
		this.selectOneMenuLocationTypes = selectOneMenuLocationTypes;
	}

	public List<LocationsTypes> getListUtilLocationTypes() {

		try {
			listUtilLocationTypes = locationTypesService.listLocationsTypes();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listUtilLocationTypes;
	}

	public void setListUtilLocationTypes(List<LocationsTypes> listUtilLocationTypes) {
		this.listUtilLocationTypes = listUtilLocationTypes;
	}

	public List<Locations> getSetLocations() {
		return setLocations;
	}

	public void setSetLocations(List<Locations> setLocations) {
		this.setLocations = setLocations;
	}

	public boolean isPostinol() {
		return postinol;
	}

	public void setPostinol(boolean postinol) {
		this.postinol = postinol;
	}
}
