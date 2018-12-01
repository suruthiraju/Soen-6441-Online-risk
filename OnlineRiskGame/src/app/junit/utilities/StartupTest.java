package app.junit.utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import app.controller.StartUpController;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;
import app.utilities.Constant;
import app.utilities.ReadFile;
import app.utilities.Validation;

public class StartupTest {
	
	GameMapModel gameMapModel = new GameMapModel() ;
	GamePlayModel gamePlayModel = new GamePlayModel();
	Validation val;
	ReadFile readFile;
	File file;
	ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();

	private static boolean setUpIsDone = false;

	/**
	 * Set up file
	 */
	@Before
	public void setUp() throws Exception {
		if (setUpIsDone) {
			return;
		}
		// do the setup
		readFile = new ReadFile();
		file = new File(Constant.FILE_LOCATION);
		readFile.setFile(file);
		val = new Validation();
		gameMapModel = new GameMapModel(file);
		gamePlayModel = new GamePlayModel();
		gamePlayModel.setGameMap(gameMapModel);

		countryList.add(gameMapModel.getCountries().get(0));
		countryList.add(gameMapModel.getCountries().get(1));

		countryList.get(0).setArmies(2);

		PlayerModel pm = new PlayerModel("X", "Human", 0, Color.WHITE, 0, countryList, null);
		ArrayList<PlayerModel> pmList = new ArrayList<PlayerModel>();

		pmList.add(pm);

		gamePlayModel.setPlayers(pmList);
		setUpIsDone = true;
	}
	
	@Test
	public void teststartup()
	{
		StartUpController statup = new StartUpController(gamePlayModel);
		assertTrue(statup.checkForOverallArmies());
	}
	
	

}
