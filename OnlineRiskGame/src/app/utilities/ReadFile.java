package app.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import app.model.ContinentsModel;
import app.model.CountryModel;

/**
 * 
 * @author DELL
 *
 */


public class ReadFile {
	public static File FILE;

	public ArrayList<ContinentsModel> getMapContinentDetails() {

		// pass the path to the file as a parameter
		File file = getFile();
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public ArrayList<CountryModel> getMapCountryDetails()  {
		File file = getFile();
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<CountryModel> listOfCountryModel;
		HashMap<String, CountryModel> listOfCountries = new HashMap<String, CountryModel>();

		while (sc.hasNextLine()) {
			String fileData = sc.nextLine();

			if (fileData.contains("[Territories]")) {

				while (sc.hasNextLine()) {
					String territories = sc.nextLine();
					territories.trim();

					if (!"".equals(territories)) {

						int indexOfCountryName = territories.indexOf(',');
						String countryName = territories.substring(0, indexOfCountryName);
						CountryModel cm = listOfCountries.get(countryName);
						if (cm == null) {
							cm = new CountryModel();
							cm.setCountryName(countryName);
							listOfCountries.put(cm.getCountryName(), cm);
						}

						int indexOfXPos = territories.indexOf(',', (indexOfCountryName + 1));
						String xPosition = territories.substring((indexOfCountryName + 1), indexOfXPos);
						cm.setXPosition(Integer.parseInt(xPosition.trim()));
						System.out.println("xposition: " + xPosition);
					//	int result = Integer.parseInt(xPosition);

						int indexOfYPos = territories.indexOf(',', (indexOfXPos + 1));
						String yPosition = territories.substring((indexOfXPos + 1), indexOfYPos);
						cm.setYPosition(Integer.parseInt(yPosition.trim()));
						System.out.println("yPosition" + yPosition);
					//	int result1 = Integer.parseInt(yPosition);

						int indexOfContinent = territories.indexOf(',', (indexOfYPos + 1));
						String continent = territories.substring((indexOfYPos + 1), indexOfContinent);
						cm.setContinentName(continent);
						System.out.println("Continent: " + continent);

						String neighbouringCountries = territories.substring((indexOfContinent + 1));
						List<String> listOfNeighbouringCountries = Arrays
								.asList(neighbouringCountries.split("\\s*,\\s*"));
						CountryModel newNeighbour;

						List<CountryModel> linkedCountriesList = new ArrayList<CountryModel>();

						for (int i = 0; i < listOfNeighbouringCountries.size(); i++) {
							if (listOfCountries.containsKey(listOfNeighbouringCountries.get(i))) {
								newNeighbour = listOfCountries.get(listOfNeighbouringCountries.get(i));

							} else {
								newNeighbour = new CountryModel();
								newNeighbour.setCountryName(listOfNeighbouringCountries.get(i));

							}
							listOfCountries.put(listOfNeighbouringCountries.get(i), newNeighbour);
							linkedCountriesList.add(newNeighbour);

						}

						cm.setLinkedCountries(linkedCountriesList);

					}
				}
			}
		}

		Collection<CountryModel> c = (Collection<CountryModel>) listOfCountries.values();
		listOfCountryModel = new ArrayList<CountryModel>(c);
		return listOfCountryModel;
	}
	public void setFile(File file) {
		
		FILE = file;
		
	}
	public File getFile() {
		return FILE;
	}
	
}