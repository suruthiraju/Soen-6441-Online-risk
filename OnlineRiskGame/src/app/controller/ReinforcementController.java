package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;
import app.view.ReinforcementView;

/**
 * In Reinforcement Controller, the data flow into model object and updates the
 * view whenever data changes.
 * 
 * @author Suruthi Raju
 *
 */
public class ReinforcementController implements ActionListener {

	private ReinforcementView theReinforcementView;
	private ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
	private GamePlayModel gamePlayModel = null;
	private int noOfPlayers;

	/**
	 * Constructor initializes values and sets the screen too visible
	 * 
	 * @param gameMapModel
	 */
	public ReinforcementController(GamePlayModel gamePlayModel) {
		this.gamePlayModel = gamePlayModel;
		this.gamePlayModel.getGameMap().getPlayerTurn().setmyTroop(this.calculateArmies());
		theReinforcementView = new ReinforcementView(this.gamePlayModel);
		theReinforcementView.setActionListener(this);
		theReinforcementView.setVisible(true);

		this.gamePlayModel.getGameMap().addObserver(theReinforcementView);
		for (int i = 0; i < noOfPlayers; i++) {
			this.listOfPlayers.get(i).addObserver(this.theReinforcementView);
		}
	}

	/**
	 * Calculation of Number of Reinforcement Armies
	 * 
	 * @return Integer
	 */
	public int calculateArmies() {
		int reinforceArmies = 0;
		int numberOfCountries = 0;
		
		for (int i = 0; i < this.gamePlayModel.getPlayers().size(); i++) {
			
			if (this.gamePlayModel.getPlayers().get(i).getNamePlayer().equals(this.gamePlayModel.getGameMap().getPlayerTurn())) {
				numberOfCountries = this.gamePlayModel.getPlayers().get(i).getOwnedCountries().size();
			}
		}
		if (numberOfCountries > 3) {
			reinforceArmies = 3 + Math.round(listOfCountrys.size() / 3);
		} else {
			reinforceArmies = 3;
		}
		if (reinforceArmies > 12) {
			reinforceArmies = 12;
		}
		return reinforceArmies;
	}

	/**
	 * This method performs action, by Listening the action event set in view.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(this.theReinforcementView.addButton)) {
			int selectedArmies = 0;
			if (theReinforcementView.numOfTroopsComboBox.getSelectedItem() != null) {
				selectedArmies = (int) theReinforcementView.numOfTroopsComboBox.getSelectedItem();
				CountryModel countryName = (CountryModel) theReinforcementView.countryListComboBox.getSelectedItem();
				System.out.println("countryName" +selectedArmies + countryName);
				this.gamePlayModel.setSelectedArmiesToCountries(selectedArmies, countryName);
			} else {

				new FortificationController(this.gamePlayModel);
				this.theReinforcementView.dispose();
			}
		}
	}
}