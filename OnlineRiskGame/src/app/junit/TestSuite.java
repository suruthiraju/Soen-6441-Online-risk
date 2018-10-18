package app.junit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import app.utilities.*;

@RunWith(Suite.class)
@SuiteClasses({ CreateContinentTest.class,
				CreateCountryTest.class,
				FortificationTest.class,
				NewGameTest.class,
				ReadFileTest.class,
				ReinforcementTest.class,
				StartUpTest.class,
				WriteFileTest.class})
public class TestSuite {
	
}
