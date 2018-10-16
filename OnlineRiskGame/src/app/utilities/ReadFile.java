package app.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import app.model.*;

/**
 * 
 * @author DELL
 *
 */


public class ReadFile {
	
	public ArrayList<ContinentsModel> getMapContinentDetails(File mapFile) throws Exception  {
	  
	    // pass the path to the file as a parameter 
	    File file = mapFile; 
	    Scanner sc = new Scanner(file); 

	    ArrayList<ContinentsModel> listOfContinents = new ArrayList<ContinentsModel>();
	   	      
	    while (sc.hasNextLine()) 
	    { 	    	
	      String sc1 = sc.nextLine();
	      
	      if (sc1.contains("[Continents]")) {
	    	  String sc2 = sc.nextLine();
	    	  sc2.trim();
	    	  
	    	  while (!"".equals(sc2)) {
	    		  int positionEqual = sc2.indexOf('=');
	    		  String sc3 = sc2.substring(0, positionEqual);
	    		  System.out.println("Continents: " + sc3);
	    		  
	    		  String sc4 = sc2.substring(positionEqual+1);
	    		  int result = Integer.parseInt(sc4);
	    		  System.out.println("Value: " + sc4);
	    		  
	    		  ContinentsModel tempMyContinents=new ContinentsModel(sc3,result);
	    		  listOfContinents.add(tempMyContinents);
	    		  sc2 = sc.nextLine();
		    	  sc2.trim();    	  
	    	  }
	      }
	      
	    }
	    return listOfContinents;
	}
	
	
	public ArrayList<CountryModel> getMapCountryDetails(File mapFile) throws Exception{
		  
	    // pass the path to the file as a parameter 
	    File file = mapFile;
	    Scanner sc = new Scanner(file); 

	    ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
	   	      
	    while (sc.hasNextLine()) 
	    { 	    	
	      String sc1 = sc.nextLine();
	      
	      if (sc1.contains("[Territories]")) {
	    	  
    	  	while (sc.hasNextLine()) {
    	  	  String sc2 = sc.nextLine();
    	  	  sc2.trim();
    	  	  
    	  	  if (!"".equals(sc2)) {
    	  		  
    	  		  int positionEqual = sc2.indexOf(',');
    	  	  	  String sc3 = sc2.substring(0, positionEqual);
    	  	  	  System.out.println("Country Name: " + sc3 );
    	  	  	  
    	  	  	  int positionEqual1 = sc2.indexOf(',', (positionEqual+1));
    	  	  	  String sc4 = sc2.substring((positionEqual+1), positionEqual1); 
    	  	  	  System.out.println("xposition: " + sc4.trim()); 
    	  	  	  int result = Integer.parseInt(sc4.trim());
    	  	  	  
    	  	  	  int positionEqual2 = sc2.indexOf(',', (positionEqual1+1));
    	  	  	  String sc5 = sc2.substring((positionEqual1+1), positionEqual2);
    	  	  	  System.out.println("yPosition" + sc5.trim());  
    	  	  	  int result1 = Integer.parseInt(sc5.trim());
    	  	  	  
    	  	  	  int positionEqual3 = sc2.indexOf(',', (positionEqual2+1));
    	  	  	  String sc6 = sc2.substring((positionEqual2+1), positionEqual3);
    	  	  	  System.out.println("Continent: " + sc6);
    	  	  	  
    	  	  	  String sc7 = sc2.substring((positionEqual3+1));
    	  	  	  List<String> tempLinks = Arrays.asList(sc7.split("\\s*,\\s*"));
    	  	  	  System.out.println("link: " + sc7);
    	  	  	  
    	  	  	  PlayerModel pm = new PlayerModel();
    	  	  	  
    	  	  	  CountryModel tempMyCountry=new CountryModel(sc3,result,result1,sc6,tempLinks,pm,0 );
    	  	  	  listOfCountrys.add(tempMyCountry);
    	  	  }
    	  	}
	      }
	    } 
	    return listOfCountrys;
	}
}