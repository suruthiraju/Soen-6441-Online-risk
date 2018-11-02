package app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * "GamePlayerModel" class represents an object containing current map and
 * players.
 * 
 * @author GROUP-35
 */
public class GamePlayModel {

	private GameMapModel gameMap;
	private ArrayList<PlayerModel> players;
	/**
	 * Constructor
	 * 
	 * @param gameMap
	 * @param players
	 */
	public GamePlayModel(GameMapModel gameMap, ArrayList<PlayerModel> players) {
		this.gameMap = gameMap;
		this.players = players;
	}

	public GamePlayModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the gameMap.
	 */
	public GameMapModel getGameMap() {
		return gameMap;
	}

	/**
	 * Sets the gameMap Model.
	 * 
	 * @param gameMap
	 */
	public void setGameMap(GameMapModel gameMap) {
		this.gameMap = gameMap;
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
				if(parmCountry.equals(players.get(i).getOwnedCountries().get(j)))
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
	
	
	
}
