package app.model;

import java.util.List;

/**
 * 
 * @author DELL
 *
 */


public class GamePlayerModel {

	private GameMapModel gameMap;
	private List<PlayerModel> players;
	
	public GamePlayerModel( GameMapModel gameMap, List<PlayerModel> players) {
		this.gameMap = gameMap;
		this.players = players;
	}
	
	/**
	 * 
	 * @return the gameMap.
	 */
	public GameMapModel getGameMap() {
		return gameMap;
	}
	
	/**
	 * Sets the gameMap Model.
	 * @param gameMap
	 */
	public void setGameMap(GameMapModel gameMap) {
		this.gameMap = gameMap;
	}
	
	/**
	 * 
	 * @return the list of players.
	 */
	public List<PlayerModel> getPlayers() {
		return players;
	}
	
	/**
	 * Sets the list of players.
	 * @param players
	 */
	public void setPlayers(List<PlayerModel> players) {
		this.players = players;
	}
}
