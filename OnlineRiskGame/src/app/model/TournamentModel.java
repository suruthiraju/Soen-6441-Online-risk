package app.model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Model of whole map file that gets generated in the end
 * 
 * @author Suruthi
 *
 */

public class TournamentModel extends Observable {

	/** The game Play model. */
	private ArrayList<GamePlayModel> gamePlayModels = new ArrayList<GamePlayModel>();

	/** The no of games. */
	private int noOfGames;

	/** The no of turns. */
	private int noOfTurns;

	/**
	 * Constructor.
	 *
	 * @param gameMap the game map
	 * @param players the players
	 * @param deck    the deck
	 */
	public TournamentModel(ArrayList<GamePlayModel> gamePlayModels, int noOfGames, int noOfTurns) {
		this.gamePlayModels = gamePlayModels;
		this.noOfGames = noOfGames;
		this.noOfTurns = noOfTurns;
	}

	/**
	 * Default Constructor.
	 */
	public TournamentModel() {
	}

	/**
	 * Gets the game play.
	 *
	 * @return the gamePlayModel.
	 */
	public ArrayList<GamePlayModel> getGamePlay() {
		return gamePlayModels;
	}

	/**
	 * Sets the game play Model.
	 *
	 * @param gamePlayModel the new game play
	 */
	public void setGamePlay(ArrayList<GamePlayModel> gamePlayModels) {
		this.gamePlayModels = gamePlayModels;
	}

	/**
	 * @return the no Of Games.
	 */
	public int getNoOfGames() {
		return noOfGames;
	}

	/**
	 * Sets the no Of Games.
	 * 
	 * @param noOfGames
	 */
	public void setNoOfGames(int noOfGames) {
		this.noOfGames = noOfGames;
	}

	/**
	 * set pnoOfTurns
	 */
	public void setNoOfTurns(int noOfTurns) {
		this.noOfTurns = noOfTurns;
	}

	/**
	 * @return noOfTurns
	 */
	public int getNoOfTurns() {
		return noOfTurns;
	}

	/**
	 * Method used to notify state change whenever any change is reflected by
	 * CreateContinentController via CreateContinentView
	 */
	public void callObservers() {
		setChanged();
		notifyObservers(this);
	}
}
