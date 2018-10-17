package app.junit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import app.utilities.LaunchOpeningScreen;

/**
 * 
 * @author DELL
 *
 */


@RunWith(Suite.class)
@SuiteClasses({ LaunchOpeningScreen.class })
public class TestRunner {
	
}
