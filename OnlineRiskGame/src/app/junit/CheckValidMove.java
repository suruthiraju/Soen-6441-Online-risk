package app.junit;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import app.model.GameMapModel;
import app.utilities.Constant;
import app.utilities.ReadFile;
import app.utilities.Validation;

public class CheckValidMove {

	private static final boolean False = false;
	GameMapModel gameMapModel;
	Validation val;
	ReadFile readFile;
	File file;
	
	private static boolean setUpIsDone = false;
	
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
		assertTrue(val.checkIfValidMove(gameMapModel, gameMapModel.getCountries().get(0), gameMapModel.getCountries().get(1)));
	}
}
