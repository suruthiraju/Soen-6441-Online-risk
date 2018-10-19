package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import app.model.GameMapModel;
import app.model.PlayerModel;

/**
 * In GamePlayController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author Suruthi Raju
 * @version 1.0.0
 * 
 */

public class GamePlayController implements ActionListener {

	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
	public GameMapModel gameMapModel = null;

	/**
	 * Constructor initializes values and sets the screen too visible
	 * 
	 * @param gameMapModel
	 * @param listOfPlayers
	 */
	public GamePlayController(GameMapModel gameMapModel, ArrayList<PlayerModel> listOfPlayers) {
		this.gameMapModel = gameMapModel;
		this.listOfPlayers = listOfPlayers;
		gamePlay();
	}

	public void gamePlay() {

		this.gameMapModel.setPlayerTurn(this.listOfPlayers.get(this.gameMapModel.getPlayerIndex()));
		new ReinforcementController(this.gameMapModel);

	}

	/**
	 * This method performs action, by Listening the action event set in view.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub

	}

}
