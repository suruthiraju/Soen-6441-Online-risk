/*package app.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import app.utilities.ReadFile;
import app.utilities.Validation;

public class CheckSingleStrike {

	GameMapModel gameMapModel;
	GamePlayModel gamePlayModel;
	Validation val;
	ReadFile readFile;
	File file;
	ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();

	private static boolean setUpIsDone = false;

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
		
		PlayerModel pm = new PlayerModel("X", 0, Color.WHITE, 0, countryList);
		ArrayList<PlayerModel> pmList = new ArrayList<PlayerModel>();
		
		
		pmList.add(pm);

		gamePlayModel.setPlayers(pmList);
		setUpIsDone = true;
	}

	@Test
	public void test() {
		CountryModel countryModel = gamePlayModel.armiesDeduction(countryList.get(0),1);
		assertEquals(1,countryModel.getArmies());
	}

}
*/