package app.model;

import java.util.List;

public class GameMapModel {

	private List<ContinentsModel> Continents;
	private List<CountryModel> Countries;
	
	public GameMapModel( List<ContinentsModel> Continents, List<CountryModel> Countries) {
		this.Continents = Continents;
		this.Countries = Countries;
	}
	
	/**
	 * 
	 * @return the list of Continents.
	 */
	public List<ContinentsModel> getContinents() {
		return Continents;
	}
	
	/**
	 * Sets the value Control.
	 * @param valueControl
	 */
	public void setContinents(List<ContinentsModel> Continents) {
		this.Continents = Continents;
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
	 * @param valueControl
	 */
	public void setCountries(List<CountryModel> Countries) {
		this.Countries = Countries;
	}
}
