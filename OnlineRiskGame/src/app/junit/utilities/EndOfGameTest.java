package app.junit.utilities;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;
import app.utilities.Constant;
import app.utilities.MessageUtil;
import app.utilities.ReadFile;
import app.utilities.Validation;

public class EndOfGameTest {

	GameMapModel gameMapModel;
	GamePlayModel gamePlayModel;
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

	/**
	 * Test single strike
	 */
	@Test
	public void test() {
		boolean endOfGameFlag = this.val.endOfGame(gamePlayModel);
		if(endOfGameFlag) {
			MessageUtil msg = new MessageUtil("It is an end of game");			
		}else {
			MessageUtil msg = new MessageUtil("It is an not an end of game");
		}
	}
}
