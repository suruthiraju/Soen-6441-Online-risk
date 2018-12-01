
package app.junit.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import app.controller.StartUpController;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;
import app.utilities.Constant;
import app.utilities.ReadFile;
import app.utilities.SaveGame;
import app.utilities.Validation;

public class SaveGameTest {
	
	GameMapModel gameMapModel = new GameMapModel() ;
	GamePlayModel gamePlayModel = new GamePlayModel();
	Validation val;
	ReadFile readFile;
	File file;
	ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();
	String name= "file";

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
	public void test()
	{
		SaveGame sg = new SaveGame();
			try {
				assertTrue(sg.writeTOJSONFile(gamePlayModel, name));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
	
	}

}
