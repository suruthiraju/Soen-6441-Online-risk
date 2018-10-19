package app.junit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import app.utilities.*;

@RunWith(Suite.class)
@SuiteClasses({ 
				UnlinkedContinentValidationTest.class,
				CheckInterlinkedContinentTest.class,
				EmptyContinentValidationTest.class,
				ReadFileTest.class,
				ReinforcementTest.class,
				EmptyLinkCountryValidationTest.class,
				WriteFileTest.class})
public class TestSuite {
	
}
