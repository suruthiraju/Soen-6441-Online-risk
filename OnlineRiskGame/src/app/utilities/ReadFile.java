package app.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import app.model.*;

public class ReadFile {
<<<<<<< HEAD
	
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
    	  	  	  
    	  	  	  CountryModel tempMyCountry=new CountryModel(sc3,result,result1,sc6,tempLinks,"",0 );
    	  	  	  listOfCountrys.add(tempMyCountry);
    	  	  }
    	  	}
	      }
	    } 
	    return listOfCountrys;
=======

	public ArrayList<ContinentsModel> getMapContinentDetails() {

		// pass the path to the file as a parameter
		Scanner sc = readFile();

		ArrayList<ContinentsModel> listOfContinents = new ArrayList<ContinentsModel>();

		while (sc.hasNextLine()) {
			String sc1 = sc.nextLine();

			if (sc1.contains("[Continents]")) {
				String sc2 = sc.nextLine();
				sc2.trim();

				while (!"".equals(sc2)) {
					int positionEqual = sc2.indexOf('=');
					String sc3 = sc2.substring(0, positionEqual);
					System.out.println("Continents: " + sc3);

					String sc4 = sc2.substring(positionEqual + 1);
					int result = Integer.parseInt(sc4);
					System.out.println("Value: " + sc4);

					ContinentsModel tempMyContinents = new ContinentsModel(sc3, result);
					listOfContinents.add(tempMyContinents);
					sc2 = sc.nextLine();
					sc2.trim();
				}
			}

		}
		return listOfContinents;
	}

	public ArrayList<CountryModel> getMapCountryDetails() {

		Scanner sc = readFile();

		ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();

		while (sc.hasNextLine()) {
			String sc1 = sc.nextLine();

			if (sc1.contains("[Territories]")) {

				while (sc.hasNextLine()) {
					String sc2 = sc.nextLine();
					sc2.trim();

					if (!"".equals(sc2)) {

						int positionEqual = sc2.indexOf(',');
						String sc3 = sc2.substring(0, positionEqual);
						System.out.println("Country Name: " + sc3);

						int positionEqual1 = sc2.indexOf(',', (positionEqual + 1));
						String sc4 = sc2.substring((positionEqual + 1), positionEqual1);
						System.out.println("xposition: " + sc4);
						int result = Integer.parseInt(sc4);

						int positionEqual2 = sc2.indexOf(',', (positionEqual1 + 1));
						String sc5 = sc2.substring((positionEqual1 + 1), positionEqual2);
						System.out.println("yPosition" + sc5);
						int result1 = Integer.parseInt(sc5);

						int positionEqual3 = sc2.indexOf(',', (positionEqual2 + 1));
						String sc6 = sc2.substring((positionEqual2 + 1), positionEqual3);
						System.out.println("Continent: " + sc6);

						String sc7 = sc2.substring((positionEqual3 + 1));
						List<String> tempLinks = Arrays.asList(sc7.split("\\s*,\\s*"));
						System.out.println("link: " + sc7);

						CountryModel tempMyCountry = new CountryModel(sc3, result, result1, sc6, tempLinks);
						listOfCountrys.add(tempMyCountry);
					}
				}
			}
		}
		return listOfCountrys;
	}

	private Scanner readFile() {
		File file = new File(Constant.FILE_LOCATION);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return sc;
>>>>>>> 69837fe307167b95a9e9ff07af1c4f2f4e20c1bf
	}
}