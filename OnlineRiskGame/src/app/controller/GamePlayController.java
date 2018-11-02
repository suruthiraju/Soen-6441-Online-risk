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
		new ReinforcementController(this.gamePlayModel.getGameMap());

	}
	public int calculateArmies() {
		
		ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
		
		int reinforceArmies = 0;

		for (int i = 0; i < this.gamePlayModel.getGameMap().getCountries().size(); i++) {
			if (this.gamePlayModel.getGameMap().getCountries().get(i).getRulerName().equals(this.gamePlayModel.getGameMap().getPlayerTurn())) {
				listOfCountrys.add(this.gamePlayModel.getGameMap().getCountries().get(i));
			}
		}
		if (listOfCountrys.size() > 3) {
			reinforceArmies = 3 + Math.round(listOfCountrys.size() / 3);
		} else {
			reinforceArmies = 3;
		}
		if (reinforceArmies > 12) {
			reinforceArmies = 12;
		}
		return reinforceArmies;
	
	}
		
		public void attack(PlayerModel currentPlayer)
		{
			PlayerModel defender; // take the country detail from the view and fill the player controlling it.
		}
		public void reinforcement(PlayerModel currentPlayer)
		{
			 // take the country detail from the view and fill the player controlling it.
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
