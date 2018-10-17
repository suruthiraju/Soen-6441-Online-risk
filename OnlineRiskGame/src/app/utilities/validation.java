package app.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.model.ContinentsModel;
import app.model.CountryModel;
import app.utilities.*;
public class validation {
	

	public static void main(String[] args) {
		
		ReadFile rf = new ReadFile();
		rf.setFile(new File("C:\\Users\\sukuri\\Documents\\Montreal.map"));
		String continent = ""; 
		List<ContinentsModel> listOfContinents = rf.getMapContinentDetails();
		List<CountryModel> listOfCountrys = rf.getMapCountryDetails();
		List<String> Countryname = new ArrayList<String>();;
		int numb;
		boolean emptyLinkContinent=false;
			for (int j=0; j<listOfContinents.size(); j++) {
				continent = listOfContinents.get(j).getContinentName();
				numb = 0;
				System.out.println("XXX " + continent );
				for (int i=0; i<listOfCountrys.size();i++) {
					if(continent.equals(listOfCountrys.get(i).getcontinentName())) {
					  Countryname.add(listOfCountrys.get(i).getCountryName());
					}
				}
				for(int i=0; i<Countryname.size();i++) {
					for (int k=0; k<listOfCountrys.size();k++) {
						if(!continent.equals(listOfCountrys.get(k).getcontinentName())) {
							for (int a=0; a<listOfCountrys.get(k).getLinkedCountries().size(); a++) {
								if(Countryname.get(i).equals(listOfCountrys.get(k).getLinkedCountries().get(a).getCountryName())) {
									numb++;
								}
							}
						}
					}
				}
				if(numb == 0) {
					emptyLinkContinent = true;
					break;					
				}
			}	
			System.out.println("Boolean value " + emptyLinkContinent);
	
	}

}
