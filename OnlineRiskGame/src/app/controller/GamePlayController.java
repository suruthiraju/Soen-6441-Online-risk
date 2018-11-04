package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.GamePlayModel;
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
	
	//use the model gameplay instead of member variables
	public GamePlayModel gamePlayModel = null;

	/**
	 * Constructor initializes values and sets the screen too visible
	 * 
	 * @param gamePlayModel
	 */
	public GamePlayController(GamePlayModel gamePlayModel) {
		this.gamePlayModel = gamePlayModel;
		gamePlay();
	}

	public void gamePlay() {

		this.gamePlayModel.getGameMap().setPlayerTurn(this.gamePlayModel.getPlayers().get(this.gamePlayModel.getGameMap().getPlayerIndex()));
		//new ReinforcementController(this.gamePlayModel);
		new PlayerController(this.gamePlayModel);
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
