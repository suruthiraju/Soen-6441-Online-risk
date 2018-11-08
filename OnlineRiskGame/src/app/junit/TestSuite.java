package app.junit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import app.junit.utilities.CheckInterlinkedContinentTest;
import app.junit.utilities.CheckValidMove;
import app.junit.utilities.EmptyContinentValidationTest;
import app.junit.utilities.EmptyLinkCountryValidationTest;
import app.junit.utilities.ReadFileContinent;
import app.junit.utilities.ReadFileCountry;
import app.junit.utilities.UnlinkedContinentValidationTest;
import app.utilities.*;

@RunWith(Suite.class)
@SuiteClasses({ 
				UnlinkedContinentValidationTest.class,
				CheckInterlinkedContinentTest.class,
				CheckValidMove.class,
				EmptyContinentValidationTest.class,
				ReadFileContinent.class,
				ReadFileCountry.class,
				EmptyLinkCountryValidationTest.class
				})

/**
 * Test suite 
 */
public class TestSuite {
	
}
