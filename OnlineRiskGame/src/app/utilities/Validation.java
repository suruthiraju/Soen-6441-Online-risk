package app.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.GamePlayModel;

/**
 * This class is to valdiate map for its correctness
 * 
 * @author Rohit
 *
 */
public class Validation {

	/**
	 * Check on un linked Continent Validation
	 * 
	 * @param mapModel
	 * @return
	 */
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

	/**
	 * Check on empty link Country Validation
	 * 
	 * @param mapModel
	 * @return boolean
	 */
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

	/**
	 * Check on Inter Linked Contient
	 * 
	 * @param mapModel
	 * @return boolean
	 */
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

	/**
	 * Check on empty Continent Validation
	 * 
	 * @param mapModel
	 * @return boolean
	 */
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

	/**
	 * Check on valid move for fortification
	 * 
	 * @param gameMapModel
	 * @param fromCountryModel
	 * @param toCountryModel
	 * @return
	 */
	public boolean checkIfValidMove(GameMapModel gameMapModel, CountryModel fromCountryModel,
			CountryModel toCountryModel) {

		LinkedList<CountryModel> temp;

		HashMap<CountryModel, Integer> mapOfCountries = new HashMap<CountryModel, Integer>();

		for (int i = 0; i < gameMapModel.getCountries().size(); i++) {
			mapOfCountries.put(gameMapModel.getCountries().get(i), i);
		}

		if (isReachable(mapOfCountries.get(fromCountryModel), mapOfCountries.get(toCountryModel), gameMapModel,
				mapOfCountries)) {
			return true;
		}
		return false;
	}

	// prints BFS traversal from a given source s
	Boolean isReachable(int s, int d, GameMapModel gameMapModel, HashMap<CountryModel, Integer> mapOfCountries) {
		LinkedList<Integer> temp;

		boolean visited[] = new boolean[gameMapModel.getCountries().size()];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[s] = true;
		queue.add(s);

		Iterator<Integer> i;
		while (queue.size() != 0) {
			s = queue.poll();

			int n;

			List<Integer> m = new ArrayList<Integer>();
			for (int l = 0; l < gameMapModel.getCountries().get(s).getLinkedCountries().size(); l++) {
					if (gameMapModel.getCountries().get(s).getRulerName()
							.equals(gameMapModel.getCountries().get(s).getLinkedCountries().get(l).getRulerName())) {
						m.add(mapOfCountries.get(gameMapModel.getCountries().get(s).getLinkedCountries().get(l)));
					}
			}

			i = m.listIterator();

			while (i.hasNext()) {
				n = i.next();

				// If this adjacent node is the destination node,
				// then return true
				if (n == d)
					return true;

				// Else, continue to do BFS
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}

		// If BFS is complete without visited d
		return false;
	}

	public boolean endOfGame(GamePlayModel gamePlayModel) {
		for (int i = 0; i < gamePlayModel.getPlayers().size(); i++) {
			if (gamePlayModel.getPlayers().get(i).getOwnedCountries().size() == gamePlayModel.getGameMap()
					.getCountries().size()) {
				return true;
			}
		}
		return false;
	}

}
