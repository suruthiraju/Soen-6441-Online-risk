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
import app.model.GameMapModel;
import app.utilities.*;
public class validation {
	public boolean unlinkedContinentVAlidation(GameMapModel mapModel)
	{
		boolean flag= true;
		List<CountryModel> listOfCountrys = mapModel.getCountries();
		List<CountryModel> listOfLinkedCountries;
		for(int index=0; index<listOfCountrys.size();index++)
			for(int index2 =0;index<=listOfCountrys.get(index).getLinkedCountries().size(); index2++)
			{
				listOfLinkedCountries=listOfCountrys.get(index).getLinkedCountries();
			     if(listOfLinkedCountries.get(index2).getcontinentName().equals(listOfCountrys.get(index).getcontinentName()))
			     {
			    	flag = true; 
			    	return flag;
			     }
			     else
			     {
			    	 flag = false;
			     }
			    
			}
		return flag;
	}
	public boolean emptyLinkCountryValidation(GameMapModel mapModel) {
		List<CountryModel> listOfCountrys = mapModel.getCountries();
		List<CountryModel> linkedCountry; 
			for (int j=0; j<listOfCountrys.size(); j++) {
				linkedCountry =  listOfCountrys.get(j).getLinkedCountries();
				if (linkedCountry == null || linkedCountry.isEmpty()) {
					return true;
				}
			}
			return false;
	}
	

	public boolean checkInterlinkedContinent(GameMapModel mapModel) {
		
		String continent = ""; 
		List<ContinentsModel> listOfContinents = mapModel.getContinents();
		List<CountryModel> listOfCountrys = mapModel.getCountries();
		List<String> Countryname = new ArrayList<String>();;
		int numb;
		boolean emptyLinkContinent=false;
			for (int j=0; j<listOfContinents.size(); j++) {
				continent = listOfContinents.get(j).getContinentName();
				numb = 0;
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
					return emptyLinkContinent;				
				}
			}	
			return emptyLinkContinent;
	
	
	}
	public boolean emptyContinentValidation(GameMapModel mapModel) {
		List<ContinentsModel> listOfContinents = mapModel.getContinents();
		List<CountryModel> listOfCountrys = mapModel.getCountries();
		String continentName = " ";
		int numb;
		for (int i=0; i< listOfContinents.size(); i++) {
			continentName = listOfContinents.get(i).getContinentName(); 
			numb =0;
			for (int j=0; j<listOfCountrys.size(); j++) {
				if(continentName.equals(listOfCountrys.get(j).getcontinentName())) {
					numb++;
				}				
			}
			if (numb == 0) {
				return true;
			}
		}
		return false;
	}

}
