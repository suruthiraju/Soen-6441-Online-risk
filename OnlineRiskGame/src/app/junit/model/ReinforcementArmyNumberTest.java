package app.junit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import app.controller.PlayerController;
import app.model.CardModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;
import app.utilities.Constant;
import app.utilities.MessageUtil;
import app.utilities.ReadFile;
import app.utilities.Validation;

/**
 * ReinforcementArmyNumberTest
 * 
 * @author team 35
 *
 */
public class ReinforcementArmyNumberTest {

	GameMapModel gameMapModel;
	GamePlayModel gamePlayModel;
	Validation val;
	ReadFile readFile;
	File file;
	ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();
	ArrayList<CountryModel> cardList = new ArrayList<CountryModel>();
	
	PlayerModel pm = new PlayerModel("X", "Human", 0, Color.WHITE, 0, countryList, cardList);
	CardModel card ;

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
		gameMapModel = new GameMapModel(file);
		gamePlayModel = new GamePlayModel();
		gamePlayModel.setGameMap(gameMapModel);

		countryList.add(gameMapModel.getCountries().get(0));
		countryList.add(gameMapModel.getCountries().get(1));

		countryList.get(0).setArmies(2);

		ArrayList<PlayerModel> pmList = new ArrayList<PlayerModel>();

		pmList.add(this.pm);
		gamePlayModel.getCardFromJSON();
		card = gamePlayModel.getCards().get(0);
		
		

		gamePlayModel.setPlayers(pmList);
		setUpIsDone = true;
	}

	/**
	 * Test single strike
	 */
	@Test
	public void test() {
		if(gamePlayModel.reinforcementArmies(15) > 0) {
			MessageUtil msg = new MessageUtil("Number of countries is calculated " );	
		}
	}

}