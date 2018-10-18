package app.model;

import java.util.Observable;

/**
 * "CreateMapModel" represents Map Model as an object
 * Properties are: continents array, countries array, 
 * and links like continent-country and country-country
 * @author SOEN-6441, GROUP-35
 */


public class CreateMapModel{

	String[] continents;
	String[] countries;
	boolean[][] continentsToCountries;
	boolean[][] links;
	
}
