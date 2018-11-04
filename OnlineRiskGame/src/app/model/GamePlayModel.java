package app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * "GamePlayerModel" class represents an object containing current map and
 * players.
 * 
 * @author GROUP-35
 */
public class GamePlayModel extends Observable  {

	private GameMapModel gameMapModel;
	private ArrayList<PlayerModel> players;
	/**
	 * Constructor
	 * 
	 * @param gameMap
	 * @param players
	 */
	public GamePlayModel(GameMapModel gameMap, ArrayList<PlayerModel> players) {
		this.gameMapModel = gameMap;
		this.players = players;
	}

	public GamePlayModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the gameMap.
	 */
	public GameMapModel getGameMap() {
		return gameMapModel;
	}

	/**
	 * Sets the gameMap Model.
	 * 
	 * @param gameMap
	 */
	public void setGameMap(GameMapModel gameMap) {
		this.gameMapModel = gameMap;
	}

	/**
	 * @return the list of players.
	 */
	public ArrayList<PlayerModel> getPlayers() {
		return players;
	}

	/**
	 * Sets the list of players.
	 * 
	 * @param players
	 */
	public void setPlayers(ArrayList<PlayerModel> players) {
		this.players = players;
	}

	public PlayerModel getPlayer(CountryModel parmCountry)
	{
		int i,j;
		for(i = 0; i< players.size(); i++)
		{
			for (j=0; j< players.get(i).getOwnedCountries().size();j++)
			{
				if(parmCountry.getCountryName().equals(players.get(i).getOwnedCountries().get(j).getCountryName()))
				{
					return players.get(i);
				}
				else
				{
					continue;
				}
			}
		}
		return null;
		
	}
	public void setSelectedArmiesToCountries(int selectedArmies, CountryModel countryName) {
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).equals(countryName)) {
				this.gameMapModel.getCountries().get(i).setArmies(this.gameMapModel.getCountries().get(i).getArmies() + selectedArmies);
				for (int j=0; j<this.getPlayers().size();j++) {
					if(this.getPlayers().get(j).getNamePlayer().equals(this.gameMapModel.getCountries().get(i).getRulerName())) {
						this.getPlayers().get(j).setmyTroop(this.getPlayers().get(j).getmyTroop() - selectedArmies);
					}
				}
			}
		}
		callObservers();
	}
	
	/**
	 * Method used to notify state change whenever any change is reflected by
	 * CreateContinentController via CreateContinentView
	 */
	public void callObservers() {
		setChanged();
		notifyObservers(this);
	}
	
	public ArrayList<CountryModel> getDefendCountryList( CountryModel attackCountryName ) {
		//ArrayList<CountryModel> defend
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).equals(attackCountryName)) {
				
			}
		}
		return null;
	}
}
