package app.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;

public class Validation {
	public boolean unlinkedContinentValidation(GameMapModel mapModel) {
		boolean flag = true;
		List<CountryModel> listOfCountrys = mapModel.getCountries();
		List<CountryModel> listOfLinkedCountries;

		HashMap<CountryModel, CountryModel> listOfCountriesInContinent = new HashMap<CountryModel, CountryModel>();

		for (int i = 0; i < mapModel.getContinents().size(); i++) {
			for (int j = 0; j < listOfCountrys.size(); j++) {
				if (mapModel.getContinents().get(i).getContinentName()
						.equals(listOfCountrys.get(j).getcontinentName())) {
					listOfCountriesInContinent.put(listOfCountrys.get(j), listOfCountrys.get(j));
				}

			}
			Iterator it = listOfCountriesInContinent.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<CountryModel, CountryModel> pair = (Map.Entry<CountryModel, CountryModel>) it.next();
				listOfLinkedCountries = pair.getValue().getLinkedCountries();
				int counter = 0;
				for (int k = 0; k < listOfLinkedCountries.size(); k++) {
					if (listOfCountriesInContinent.containsValue((listOfLinkedCountries.get(k)))) {
						counter++;
					}
				}

				if (counter == listOfLinkedCountries.size() - 1) {
					return false;
				}
			}

		}
		return flag;
	}

	public boolean emptyLinkCountryValidation(GameMapModel mapModel) {
		List<CountryModel> listOfCountrys = mapModel.getCountries();
		List<CountryModel> linkedCountry;
		for (int j = 0; j < listOfCountrys.size(); j++) {
			linkedCountry = listOfCountrys.get(j).getLinkedCountries();
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
		List<String> Countryname = new ArrayList<String>();
		;
		int numb;
		boolean emptyLinkContinent = false;
		for (int j = 0; j < listOfContinents.size(); j++) {
			continent = listOfContinents.get(j).getContinentName();
			numb = 0;
			for (int i = 0; i < listOfCountrys.size(); i++) {
				if (continent.equals(listOfCountrys.get(i).getcontinentName())) {
					Countryname.add(listOfCountrys.get(i).getCountryName());
				}
			}
			for (int i = 0; i < Countryname.size(); i++) {
				for (int k = 0; k < listOfCountrys.size(); k++) {
					if (!continent.equals(listOfCountrys.get(k).getcontinentName())) {
						for (int a = 0; a < listOfCountrys.get(k).getLinkedCountries().size(); a++) {
							if (Countryname.get(i)
									.equals(listOfCountrys.get(k).getLinkedCountries().get(a).getCountryName())) {
								numb++;
							}
						}
					}
				}
			}
			if (numb == 0) {
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
		for (int i = 0; i < listOfContinents.size(); i++) {
			continentName = listOfContinents.get(i).getContinentName();
			numb = 0;
			for (int j = 0; j < listOfCountrys.size(); j++) {
				if (continentName.equals(listOfCountrys.get(j).getcontinentName())) {
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
