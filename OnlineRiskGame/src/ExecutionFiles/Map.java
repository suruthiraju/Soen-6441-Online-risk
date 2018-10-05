package ExecutionFiles;
/**
 * This is the <b>JavaDoc</b> for Map Java file.
 */
/**
 * This is Map Java file for creating User-driven map.
 * It's implemented in Core Java.
 * 
 * The initial task performed by the tasks are:
 * - Asking for User input
 * - Creating a map file
 * - Validation of Correctness
 * 
 * @author - Suruthi Raju
 * @since 1.0.0
 * @see Map.java
 * @version
 * 1.0.0 - Initial - Suruthi
 * 
 */
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


import org.junit.Ignore;

public class Map {
	@Ignore
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Map");
	}

/**
 * This method is a Test Method
 * 
 */
	@Test
	public void testAdd() {
	      String str = "Junit is working fine";
	      assertEquals("Junit is working fine",str);
	   }
	
	@BeforeClass 
	public static void beforeClass() {
		  System.out.println("in before TestJunit1 class");
	}
	
	@AfterClass 
	public static void  afterClass() {
		  System.out.println("in after TestJunit1 class");
	}
	
	@Before 
	public void before() {
		  System.out.println("in before TestJunit1 test case");
	}
		   
	@After 
	public void after() {
		  System.out.println("in after TestJunit1 test case");
	}

	

}
