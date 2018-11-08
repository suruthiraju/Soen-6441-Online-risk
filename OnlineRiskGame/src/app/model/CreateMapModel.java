package app.model;


/**
 * "CreateMapModel" represents Map Model as an object Properties are: continents
 * array, countries array, and links like continent-country and country-country.
 *
 * @author GROUP-35
 */

public class CreateMapModel {

	/** The continents. */
	String[] continents;
	
	/** The countries. */
	String[] countries;
	
	/** The continents to countries. */
	boolean[][] continentsToCountries;
	
	/** The links. */
	boolean[][] links;

}
