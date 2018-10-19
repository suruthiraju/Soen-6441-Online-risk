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

	public boolean checkIfValidMove(GameMapModel gameMapModel,CountryModel fromCountryModel, CountryModel toCountryModel) {
	
		
		 LinkedList<CountryModel>temp; 
		 
		 HashMap<CountryModel,Integer> mapOfCountries =new HashMap<CountryModel, Integer>(); 
		 
		 for(int i=0;i<gameMapModel.getCountries().size();i++) {
			 mapOfCountries.put(gameMapModel.getCountries().get(i), i);
		 }
		 
		 if(isReachable(mapOfCountries.get(fromCountryModel),mapOfCountries.get(toCountryModel),gameMapModel,mapOfCountries)) {
			 return true;
		 }
	        return false; 
	}
	
	//prints BFS traversal from a given source s 
    Boolean isReachable(int s, int d,GameMapModel gameMapModel,HashMap<CountryModel,Integer> mapOfCountries) 
    { 
        LinkedList<Integer>temp; 
  
        // Mark all the vertices as not visited(By default set 
        // as false) 
        boolean visited[] = new boolean[gameMapModel.getCountries().size()]; 
  
        // Create a queue for BFS 
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
  
        // Mark the current node as visited and enqueue it 
        visited[s]=true; 
        queue.add(s); 
  
        // 'i' will be used to get all adjacent vertices of a vertex 
        Iterator<Integer> i; 
        while (queue.size()!=0) 
        { 
            // Dequeue a vertex from queue and print it 
            s = queue.poll(); 
  
            int n;
            
            List<Integer> m = new ArrayList<Integer>();
            for(int l=0;l<gameMapModel.getCountries().get(s).getLinkedCountries().size();l++) {
            	m.add(mapOfCountries.get(gameMapModel.getCountries().get(s).getLinkedCountries().get(l)));
            }
  
            i = m.listIterator(); 
            
            // Get all adjacent vertices of the dequeued vertex s 
            // If a adjacent has not been visited, then mark it 
            // visited and enqueue it 
            while (i.hasNext()) 
            { 
                n = i.next(); 
  
                // If this adjacent node is the destination node, 
                // then return true 
                if (n==d) 
                    return true; 
  
                // Else, continue to do BFS 
                if (!visited[n]) 
                { 
                    visited[n] = true; 
                    queue.add(n); 
                } 
            } 
        } 
  
        // If BFS is complete without visited d 
        return false; 
    } 

}
