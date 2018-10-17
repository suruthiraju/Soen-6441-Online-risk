package app.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;

import app.model.*;
import app.view.*;

public class MapValidation {
	
	public static void main(String[] args) throws Exception {
	//public static void isMapValid() throws Exception {
		
		File myMap = new File("D:\\Concordia\\SOEN6441\\ProjectFiles\\Maps\\map1.txt");
		ReadFile rf = new ReadFile();
		rf.setFile(myMap);
		ArrayList<ContinentsModel> listOfContinents = new ArrayList<ContinentsModel>();
		ArrayList<CountryModel> listOfCountries = new ArrayList<CountryModel>();
		listOfContinents = rf.getMapContinentDetails();
		listOfCountries = rf.getMapCountryDetails();
		
		//int arrSize = listOfCountries.size();
		//System.out.println("Initial arraySize is:" + arrSize);
		
		//String errorMessage = "";
		
		
		if ( noContinentOverlap(listOfCountries) ) {
			System.out.println("Good! No continent overlap!");
			if(noSoloCountry(listOfCountries)) {
				System.out.println("Good! No solo country!");
				if ( noSameName(listOfCountries)) {
					System.out.println("Good! No same-name country!");
				}
			}
			
		} else {
			System.out.println("Error!....Map has some errors!");
		}
		
		//System.out.println( errorCollection(listOfCountries) );

	}
	


	public static boolean noSoloCountry (ArrayList<CountryModel> tempCountryList) throws Exception {
		
		int arraySize = tempCountryList.size();
		//System.out.println("arraySize is:" + arraySize);
		String nameOfCountry1;
		//String nameOfCountry2;
		
		for (int i = 0; i < arraySize; i++) {
			nameOfCountry1 = (tempCountryList.get(i)).getCountryName();
			
			if ( ((tempCountryList.get(i)).getLinkedCountries()).size() == 0) {
				System.out.println(nameOfCountry1 + " is a solo country.!");
				return false;
			}
		}
		return true;
	}
	
	public static boolean noContinentOverlap (ArrayList<CountryModel> tempCountryList) throws Exception {
		
		int arraySize = tempCountryList.size();
		System.out.println("arraySize is:" + arraySize);
		String nameOfCountry1;
		String nameOfCountry2;
		
		for (int i = 0; i < arraySize; i++) {
			nameOfCountry1 = (tempCountryList.get(i)).getCountryName();
			for (int j=arraySize-1; j > i; j-- ) {
				nameOfCountry2 = (tempCountryList.get(j)).getCountryName();
				//System.out.println(nameOfCountry1 + " ... " + nameOfCountry2);
				if ( nameOfCountry1.equals(nameOfCountry2) ) {
					if ( !(tempCountryList.get(i)).getcontinentName().equals( (tempCountryList.get(j)).getcontinentName() )) {
						return false;
					}
				return false;
				}
			}
			
		}
		return true;
	}

	public static String errorCollection (ArrayList<CountryModel> tempCountryList) throws Exception {
		String result = "No error!";
		int arraySize = tempCountryList.size();
		//System.out.println("arraySize is:" + arraySize);
		String nameOfCountry1;
		String nameOfCountry2;
		
		for (int i = 0; i < arraySize; i++) {
			nameOfCountry1 = (tempCountryList.get(i)).getCountryName();
			for (int j=arraySize-1; j > i; j-- ) {
				nameOfCountry2 = (tempCountryList.get(j)).getCountryName();
				//System.out.println(nameOfCountry1 + " ... " + nameOfCountry2);
				if ( nameOfCountry1.equals(nameOfCountry2) ) {
					result += " ... Country name error!. Two names are same!";
					System.out.println(nameOfCountry1 + " is same as " + nameOfCountry2);

					if ( !(tempCountryList.get(i)).getcontinentName().equals( (tempCountryList.get(j)).getcontinentName() )) {
						result += " ... Continent overlap error!";
					}
				}
			}
			
		}
		return result;
	}
	
	
	public static boolean noSameName (ArrayList<CountryModel> tempCountryList) throws Exception {
		
		int arraySize = tempCountryList.size();
		String nameOfCountry1;
		String nameOfCountry2;
		
		for (int i = 0; i < arraySize; i++) {
			nameOfCountry1 = (tempCountryList.get(i)).getCountryName();
			for (int j=arraySize-1; j > i; j-- ) {
				nameOfCountry2 = (tempCountryList.get(j)).getCountryName();
				if ( nameOfCountry1.equals(nameOfCountry2) ) {
					System.out.println(nameOfCountry1 + " is same as " + nameOfCountry2);
					return false;
				}
			}
			
		}
		return true;
	}
/*	
	public boolean emptyLinkCountryValidation() {
		List<CountryModel> listOfCountrys = this.mapModel.getCountries();
		List<CountryModel> linkedCountry; 
			for (int j=0; j<listOfCountrys.size(); j++) {
				linkedCountry =  listOfCountrys.get(j).getLinkedCountries();
				if (linkedCountry == null || linkedCountry.isEmpty()) {
					return true;
				}
			}
			return false;
	}
	
	public void emptyContinentValidation() {
		List<ContinentsModel> listOfContinents = this.mapModel.getContinents();
		List<CountryModel> listOfCountrys = this.mapModel.getCountries();
		String continentName = " ";
		for (int i=0; i< listOfContinents.size(); i++) {
			continentName = listOfContinents.get(i).getContinentName(); 
			for (int j=0; j<listOfCountrys.size(); j++) {
				if(continentName.equals(listOfCountrys.get(j).getcontinentName())) {
					break;
				}				
			}
		}
	}
	
	*/
}