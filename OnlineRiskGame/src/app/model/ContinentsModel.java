package app.model;

import java.util.List;

public class ContinentsModel {

	/**
	 * 
	 * @author DELL
	 *
	 */
	

	private String continentName;
	private int valueControl;
	private List<CountryModel> Countries;
	public ContinentsModel(String continentName, int valueControl, List<CountryModel> Countries ) {
		this.continentName = continentName;
		this.valueControl = valueControl;
		this.Countries = Countries;
	}
	
	/**
	 * 
	 * @return the continent name.
	 */
	public String getContinentName() {
		return continentName;
	}
	
	/**
	 * Sets the continent name.
	 * @param continentName
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
	
	/**
	 * 
	 * @return the value Control.
	 */
	public int getValueControl() {
		return valueControl;
	}
	
	/**
	 * Sets the value Control.
	 * @param valueControl
	 */
	public void setValueControl(int valueControl) {
		this.valueControl = valueControl;
	}
	/**
	 * 
	 * @return the list of array.
	 */
	public List<CountryModel> getCountries() {
		return Countries;
	}

	/**
	 * Sets the list of array.
	 * @param ArrayList
	 */
	public void setCountries(List<CountryModel> Countries) {
		this.Countries = Countries;
	}
}
