package app.model;

import java.util.List;

import app.utilities.ReadFile;

public class GameMapModel {

	private List<ContinentsModel> continentList;
	private List<CountryModel> Countries;
	private ReadFile readfile;

	public GameMapModel() {
		readfile = new ReadFile();
		this.continentList = readfile.getMapContinentDetails();
		this.Countries = readfile.getMapCountryDetails();
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
		return Countries;
	}

	/**
	 * Sets the list of Countries.
	 * 
	 * @param valueControl
	 */
	public void setCountries(List<CountryModel> Countries) {
		this.Countries = Countries;
	}
}
