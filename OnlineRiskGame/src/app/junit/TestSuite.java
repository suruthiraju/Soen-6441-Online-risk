package app.junit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import app.utilities.*;

@RunWith(Suite.class)
@SuiteClasses({ 
				UnlinkedContinentValidationTest.class,
				CheckInterlinkedContinentTest.class,
				CheckValidMove.class,
				EmptyContinentValidationTest.class,
				ReadFileContinent.class,
				ReadFileCountry.class,
				EmptyLinkCountryValidationTest.class,
				ReinforcementArmyNumberTest.class})
public class TestSuite {
	
}
