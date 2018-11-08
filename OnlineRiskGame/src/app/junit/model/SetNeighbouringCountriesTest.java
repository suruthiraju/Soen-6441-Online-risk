package app.junit.model;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.utilities.Constant;
import app.utilities.ReadFile;


/**
 * SetNeighbouringCountriesTest
 * @author team 35
 *
 */
public class SetNeighbouringCountriesTest {
	
	List<ContinentsModel> continentList;
	List<CountryModel> countryList;
	GameMapModel gameMapModel;
	ReadFile readFile;
	File file;
	
	
	private static boolean setUpIsDone = false;
	
	/**
	 * Set up variables
	 */
	@Before
	public void setUp() {
	    if (setUpIsDone) {
	        return;
	    }
	    // do the setup
	    readFile = new ReadFile();
	    file = new File(Constant.FILE_LOCATION);
	    readFile.setFile(file);
	    this.continentList = readFile.getMapContinentDetails();
	    this.countryList = readFile.getMapCountryDetails();
	    gameMapModel = new GameMapModel();
	    setUpIsDone = true;
	}
	
	/**
	 * Test adding Neighbour validation
	 */
	@Test 
	public void testAddingNeighboringCountries() {
		assertEquals(true,gameMapModel.setNeighbouringCountry(this.countryList.get(0),this.countryList.get(1)));
	}
	
}
