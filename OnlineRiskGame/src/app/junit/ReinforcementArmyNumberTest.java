 package app.junit;
import org.junit.Test;

import app.controller.ReinforcementController;
import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.utilities.Constant;
import app.utilities.ReadFile;
import app.utilities.Validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

public class ReinforcementArmyNumberTest {
	
	private static final boolean False = false;
	GameMapModel gameMapModel;
	ReinforcementController rC;
	ReadFile readFile;
	File file;
	int reinforceArmies;
	private ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
	
	private static boolean setUpIsDone = false;
	
	@Before
	public void setUp() 
	{
	    if (setUpIsDone) {
	        return;
	    }
		 // do the setup
	    readFile = new ReadFile();
	    file = new File(Constant.FILE_LOCATION);
	    readFile.setFile(file);
	    gameMapModel = new GameMapModel(file);
	    rC = new ReinforcementController(gameMapModel);
	   // gameMapModel.setPlayerTurn(1);
	    for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).getRuler().equals(this.gameMapModel.getPlayerTurn())) {
				this.listOfCountrys.add(this.gameMapModel.getCountries().get(i));
			}
		}
		if (listOfCountrys.size() > 3) {
			reinforceArmies = 3 + Math.round(listOfCountrys.size() / 3);
		} else {
			reinforceArmies = 3;
		}
		if (reinforceArmies > 12) {
			reinforceArmies = 12;
		}
	    setUpIsDone = true;
	}
	@Test 
	public void testUnlinkedContinentValidation()
	{
		assertEquals(reinforceArmies,rC.calculateArmies());
	}
	
}