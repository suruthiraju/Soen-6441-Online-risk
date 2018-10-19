 package app.junit;
import org.junit.Test;

import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.utilities.Constant;
import app.utilities.ReadFile;
import app.utilities.Validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 * EmptyContinentValidationTest
 * @author team 35
 *
 */
public class EmptyContinentValidationTest {
	
	private static final boolean False = false;
	GameMapModel gameMapModel;
	Validation val;
	ReadFile readFile;
	File file;
	
	private static boolean setUpIsDone = false;
	
	/**
	 * Set up variables
	 */
	@Before
	public void setUp() 
	{
	    if (setUpIsDone) {
	        return;
	    }
		 // do the setup
	    readFile = new ReadFile();
	    file = new File(Constant.FILE_LOCATION);
	    readFile.setFile(file);
	    val = new Validation();
	    gameMapModel = new GameMapModel(file);
	    setUpIsDone = true;
	}
	@Test 
	public void testUnlinkedContinentVAlidation() 
	{
		assertFalse(val.emptyContinentValidation(gameMapModel));
	}
	
}