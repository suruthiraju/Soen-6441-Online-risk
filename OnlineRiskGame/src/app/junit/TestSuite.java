package app.junit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import app.junit.model.AddCardTest;
import app.junit.model.AddCountryToAttackerTest;
import app.junit.model.AllOutTest;
import app.junit.model.CheckSingleStrike;
import app.junit.model.ContinentCoverageTest;
import app.junit.model.GetArmiesTest;
import app.junit.model.GetCardForPlayerTest;
import app.junit.model.GetCards;
import app.junit.model.GetPlayerForCountryTest;
import app.junit.model.MoveDeckTest;
import app.junit.model.MovingArmies;
import app.junit.model.ReinforcementArmyNumberTest;
import app.junit.model.RemoveCardTest;
import app.junit.model.RemoveCountryToDefeaterTest;
import app.junit.model.SetNeighbouringCountriesTest;
import app.junit.model.WorldCoverageTest;
import app.junit.utilities.CheckInterlinkedContinentTest;
import app.junit.utilities.CheckValidMove;
import app.junit.utilities.EmptyContinentValidationTest;
import app.junit.utilities.EmptyLinkCountryValidationTest;
import app.junit.utilities.EndOfGameTest;
import app.junit.utilities.GetContinentWriteTest;
import app.junit.utilities.GetCountryWriteTest;
import app.junit.utilities.ReadFileContinent;
import app.junit.utilities.ReadFileCountry;
import app.junit.utilities.StartupTest;
import app.junit.utilities.UnlinkedContinentValidationTest;
import app.junit.utilities.WinnerCheckTest;
import app.utilities.*;

@RunWith(Suite.class)
@SuiteClasses({ 
				UnlinkedContinentValidationTest.class,
				CheckInterlinkedContinentTest.class,
				EmptyContinentValidationTest.class,
				ReadFileContinent.class,
				ReadFileCountry.class,
				EmptyLinkCountryValidationTest.class,
				AddCardTest.class,
				AddCountryToAttackerTest.class,
				AllOutTest.class,
				CheckSingleStrike.class,
				ContinentCoverageTest.class,
				GetArmiesTest.class,
				MoveDeckTest.class,
				MovingArmies.class,
				ReinforcementArmyNumberTest.class,
				RemoveCardTest.class,
				RemoveCountryToDefeaterTest.class,
				WorldCoverageTest.class,
				SetNeighbouringCountriesTest.class,
				EndOfGameTest.class,
				GetContinentWriteTest.class,
				GetCountryWriteTest.class,
				GetCardForPlayerTest.class,
				GetCards.class,
				GetPlayerForCountryTest.class,
				StartupTest.class,
				WinnerCheckTest.class
				})

/**
 * Test suite 
 */
public class TestSuite {
	
}
