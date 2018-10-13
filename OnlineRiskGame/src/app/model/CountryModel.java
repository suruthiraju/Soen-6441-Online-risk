package app.model;

import java.util.ArrayList;
import java.util.List;

public class CountryModel {

	private String countryName;
	private int xPosition;
	private int yPosition;
	private String continentName;
	private List linkedCountries;
	
	public CountryModel(String countryName, int xPosition, int yPosition, String continentName, List linkedCountries) {
		this.countryName = countryName;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.continentName = continentName;
		this.linkedCountries = linkedCountries;		
	}
	
	/**
	 * 
	 * @return the country name.
	 */
	public String getCountryName() {
		return countryName;
	}
	
	/**
	 * Sets the country name.
	 * @param countryName
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	/**
	 * 
	 * @return the X Position Value.
	 */
	public int getXPosition() {
		return xPosition;
	}
	
	/**
	 * Sets the X Position Value.
	 * @param xPosition
	 */
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}



/**
 * 
 * @return the Y Position Value.
 */
public int getYPosition() {
	return yPosition;
}

/**
 * Sets the Y Position Value.
 * @param yPosition
 */
public void setYPosition(int yPosition) {
	this.yPosition = yPosition;
}



/**
 * 
 * @return the Continent Name.
 */
public String continentName() {
	return continentName;
}

/**
 * Sets the Continent Name.
 * @param continentName
 */
public void setContinentName(String continentName) {
	this.continentName = continentName;
}



/**
 * 
 * @return the list of array.
 */
public List linkedCountries() {
	return linkedCountries;
}

/**
 * Sets the list of array.
 * @param ArrayList
 */
public void setLinkedCountries(List linkedCountries) {
	this.linkedCountries = linkedCountries;
}


}
