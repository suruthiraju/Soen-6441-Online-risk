package app.junit;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import app.controller.PlayerController;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.GamePlayModel;
import app.utilities.Constant;
import app.utilities.ReadFile;

/**
 * ReinforcementArmyNumberTest
 * 
 * @author team 35
 *
 */
public class ReinforcementArmyNumberTest {

	private static final boolean False = false;
	GamePlayModel gamePlayModel;
	GameMapModel gameMapModel;
	PlayerController rC;
	ReadFile readFile;
	File file;
	int reinforceArmies;
	private ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();

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
		gameMapModel = new GameMapModel(file);
		int numberOfCountries = 0;
		for (int i = 0; i < this.gamePlayModel.getPlayers().size(); i++) {

			if (this.gamePlayModel.getPlayers().get(i).getNamePlayer()
					.equals(this.gamePlayModel.getGameMap().getPlayerTurn())) {
				numberOfCountries = this.gamePlayModel.getPlayers().get(i).getOwnedCountries().size();
			}
		}
		if (numberOfCountries > 3) {
			reinforceArmies = 3 + Math.round(listOfCountrys.size() / 3);
		} else {
			reinforceArmies = 3;
		}
		if (reinforceArmies > 12) {
			reinforceArmies = 12;
		}
		setUpIsDone = true;
	}

	/**
	 * Test unlink continent validation
	 */
	@Test
	public void testUnlinkedContinentValidation() {
		assertEquals(reinforceArmies, gamePlayModel.numberOfCountries());
	}

}