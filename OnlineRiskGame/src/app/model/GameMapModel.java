package app.model;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import javax.net.ssl.HandshakeCompletedListener;

import app.utilities.Constant;
import app.utilities.ReadFile;

/**
 * Model of whole map file that gets generated in the end
 * @author Suruthi
 *
 */

public class GameMapModel extends Observable {

/**
 * List of Continents 
 */
	private List<ContinentsModel> continentList;
	
/**
 * List of Countries
 */
	private List<CountryModel> countryList;
	
/**
 * Read file object for creating a file in parameterized constructor
 */
	private ReadFile readfile;

/**
 * Index to fetch the left model in list view
 */
	private int leftModelIndex = 0;
	
/**
 * Index to fetch the right model in list view
 */
	private int rightModelIndex = 0;

/**
 * @return the leftModelIndex to get
 */
	public int getLeftModelIndex() {
		return leftModelIndex;
	}

/**
 * @param leftModelIndex to set
 */
	public void setLeftModelIndex(int leftModelIndex) {
		this.leftModelIndex = leftModelIndex;
	}

/**
 * @return the rightModelIndex to get
 */
	public int getRightModelIndex() {
		return rightModelIndex;
	}

/**
 * @param rightModelIndex to set
 */
	public void setRightModelIndex(int rightModelIndex) {
		this.rightModelIndex = rightModelIndex;
	}

/**
 * Parameterized constructor that helps edit the map
 * 
 * @param file
 */
	public GameMapModel(File file) {
		readfile = new ReadFile();
		try {
			readfile.setFile(file);
			this.continentList = readfile.getMapContinentDetails();
			this.countryList = readfile.getMapCountryDetails();
		} catch (Exception e) {
			// handle properly
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
		callObservers();
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
		callObservers();
	}

/**
 * Method used to notify state change whenever any change is reflected by
 * CreateContinentController via CreateContinentView
 */
	public void callObservers() {
		setChanged();
		notifyObservers(this);
	}

/**
 * Remove continent with the continent model name
 * @param newContinentModel
 */
	public void removeContinent(ContinentsModel newContinentModel) {
		this.continentList.remove(newContinentModel);
		callObservers();
	}

/**
 * Update countries when added
 * @param mapModel
 * @return
 */
	public GameMapModel updateCountries(GameMapModel mapModel) {
		List<CountryModel> newCountryList = new ArrayList<CountryModel>();
		for (int i = 0; i < mapModel.continentList.size(); i++) {
			for (int j = 0; j < mapModel.countryList.size(); j++) {
				if (mapModel.countryList.get(j).getcontinentName()
						.equals(mapModel.continentList.get(i).getContinentName())) {
					newCountryList.add(mapModel.countryList.get(j));
				}
			}
		}
		mapModel.countryList = null;
		mapModel.countryList = newCountryList;
		return mapModel;
	}

/**
 * Set color to a country during create phases
 * @param country
 * @param color
 */
	public void setColorToCountry(CountryModel country, Color color) {
		for (int i = 0; i < this.countryList.size(); i++) {
			if (this.countryList.get(i).equals(country)) {
				this.countryList.get(i).setBackgroundColor(color);
				// this.countryList.get(i).setBorderColor(Color.BLUE);
				// this.countryList.get(i).setCountryName("23");;

			}

		}
		callObservers();
		System.out.println(this.countryList);

	}

/**
 * Set neighboring country for the countries entered during create screens
 * 
 * @param leftModel
 * @param rightModel
 */
	public void setNeighbouringCountry(CountryModel leftModel, CountryModel rightModel) {
		for (int i = 0; i < this.getCountries().size(); i++) {
			if (this.getCountries().get(i).equals(leftModel)) {

				List<CountryModel> temp = this.getCountries().get(i).getLinkedCountries();

				if (temp == null) {
					temp = new ArrayList<CountryModel>();
				}
				temp.add((CountryModel) rightModel);
				this.getCountries().get(i).setLinkedCountries(temp);
				this.setLeftModelIndex(i);
			} else if (this.getCountries().get(i).equals(rightModel)) {
				List<CountryModel> temp = this.getCountries().get(i).getLinkedCountries();

				if (temp == null) {
					temp = new ArrayList<CountryModel>();
				}
				temp.add((CountryModel) leftModel);
				this.getCountries().get(i).setLinkedCountries(temp);
				this.setRightModelIndex(i);
			}

		}
		callObservers();
	}

/**
 * Remove neighbouring country whenever remove button is pressed upon in view 
 * @param leftModelCountry
 * @param rightModelCountry
 */
	public void removeNeighbouringCountry(CountryModel leftModelCountry, CountryModel rightModelCountry) {

		for (int i = 0; i < this.getCountries().size(); i++) {
			if (this.getCountries().get(i).equals(leftModelCountry)) {

				List<CountryModel> temp = this.getCountries().get(i).getLinkedCountries();

				if (temp == null) {
					temp = new ArrayList<CountryModel>();
				}
				temp.remove((CountryModel) rightModelCountry);
				this.getCountries().get(i).setLinkedCountries(temp);
				this.setLeftModelIndex(i);
			} else if (this.getCountries().get(i).equals(rightModelCountry)) {
				List<CountryModel> temp = this.getCountries().get(i).getLinkedCountries();

				if (temp == null) {
					temp = new ArrayList<CountryModel>();
				}
				temp.remove((CountryModel) leftModelCountry);
				this.getCountries().get(i).setLinkedCountries(temp);
				this.setRightModelIndex(i);
			}

		}
		callObservers();

	}

}
