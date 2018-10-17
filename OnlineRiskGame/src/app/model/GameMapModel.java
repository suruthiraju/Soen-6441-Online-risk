package app.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import app.utilities.Constant;
import app.utilities.ReadFile;

/**
 * 
 * @author DELL
 *
 */


public class GameMapModel extends Observable{

	private List<ContinentsModel> continentList;
	private List<CountryModel> countryList;
	private ReadFile readfile;

	/**
	 * Parameterized constructor that helps edit the map
	 * @param file
	 */
	public GameMapModel(File file) {
		readfile = new ReadFile();
		try {
			readfile.setFile(new File(Constant.FILE_LOCATION));
			this.continentList = readfile.getMapContinentDetails();
			this.countryList = readfile.getMapCountryDetails();
		} catch (Exception e) {
			//handle properly
			e.printStackTrace();
		}
	}
	
	/**
	 * Default constructor to make new map
	 */
	public GameMapModel() {
		this.continentList = new ArrayList<ContinentsModel>();
		this.countryList = new ArrayList<CountryModel>();
	}

	/**
	 * 
	 * @return the list of Continents.
	 */
	public List<ContinentsModel> getContinents() {
		return this.continentList;
	}

	/**
	 * Sets the value Control.
	 * 
	 * @param valueControl
	 */
	public void setContinents(List<ContinentsModel> Continents) {
		this.continentList = Continents;
	}

	/**
	 * 
	 * @return the list of Countries.
	 */
	public List<CountryModel> getCountries() {
		return countryList;
	}

	/**
	 * Sets the list of Countries.
	 * 
	 * @param valueControl
	 */
	public void setCountries(List<CountryModel> Countries) {
		this.countryList = Countries;
	}
	
	
	/**
	 * Method used to notify state change whenever any change is reflected by
	 * CreateContinentController via CreateContinentView
	 */
	public void callObservers() {
		setChanged();
		notifyObservers(this);
	}

	public void removeContinent(ContinentsModel newContinentModel) {
		this.continentList.remove(newContinentModel);
		callObservers();
	}

	public GameMapModel updateCountries(GameMapModel mapModel) {
		List<CountryModel> newCountryList = new ArrayList<CountryModel>();
		for(int i=0;i<mapModel.continentList.size();i++) {
			for(int j=0;j<mapModel.countryList.size();j++) {
				if(mapModel.countryList.get(j).getcontinentName().equals(mapModel.continentList.get(i).getContinentName())) {
					newCountryList.add(mapModel.countryList.get(j));
				}
			}
		}
		mapModel.countryList = null;
		mapModel.countryList = newCountryList;
		return mapModel;
	}
}
