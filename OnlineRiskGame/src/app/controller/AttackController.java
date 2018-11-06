package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.model.CountryModel;
import app.model.GamePlayModel;
import app.view.AttackView;

/**
 * In Reinforcement Controller, the data flow into model object and updates the
 * view whenever data changes.
 * 
 * @author Suruthi Raju
 *
 */
public class AttackController implements ActionListener {

	private AttackView theAttackView;
	private GamePlayModel gamePlayModel;
	private CountryModel defeatedCountry;

	/**
	 * Constructor initializes values and sets the screen too visible
	 * 
	 * @param gamePlayModel
	 */
	public AttackController(GamePlayModel gamePlayModel) {
		this.gamePlayModel = gamePlayModel;
		theAttackView = new AttackView(this.gamePlayModel);
		this.gamePlayModel.setArmyToMoveText(false);
		theAttackView.setActionListener(this);
		theAttackView.setVisible(true);
		this.gamePlayModel.deleteObservers();
		this.gamePlayModel.addObserver(this.theAttackView);
	}

	/**
	 * This method performs action, by Listening the action event set in view.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(this.theAttackView.nextButton)) {
			new FortificationController(this.gamePlayModel);
			this.theAttackView.dispose();
		}else if (actionEvent.getSource().equals(this.theAttackView.attackCountryListComboBox)) {
			this.gamePlayModel
			.setSelectedAttackComboBoxIndex(this.theAttackView.attackCountryListComboBox.getSelectedIndex());
		}else if (actionEvent.getSource().equals(this.theAttackView.defendCountryListComboBox)) {
			this.gamePlayModel
			.setSelectedDefendComboBoxIndex(this.theAttackView.defendCountryListComboBox.getSelectedIndex());
		}else if (actionEvent.getSource().equals(this.theAttackView.SingleButton)) {
			int attackDice = (int) theAttackView.numOfDiceAttackComboBox.getSelectedItem();
			int defendDice = (int) theAttackView.numOfDiceDefendComboBox.getSelectedItem();
			CountryModel attackCountry = (CountryModel) theAttackView.attackCountryListComboBox.getSelectedItem();
			CountryModel defendCountry = (CountryModel) theAttackView.defendCountryListComboBox.getSelectedItem();
			defeatedCountry = defendCountry;
			this.gamePlayModel.singleStrike(attackDice, attackCountry, defendDice, defendCountry);
		}else if (actionEvent.getSource().equals(this.theAttackView.alloutButton)) {
			CountryModel attackCountry = (CountryModel) theAttackView.attackCountryListComboBox.getSelectedItem();
			CountryModel defendCountry = (CountryModel) theAttackView.defendCountryListComboBox.getSelectedItem();
			defeatedCountry = defendCountry;
			this.gamePlayModel.alloutStrike(attackCountry, defendCountry);
		}
		else if (actionEvent.getSource().equals(this.theAttackView.moveButton)) {
			CountryModel attackCountry = (CountryModel) theAttackView.attackCountryListComboBox.getSelectedItem();
			int noOfArmiesToBeMoved = (int) theAttackView.numOfArmiesToBeMovedComboBox.getSelectedItem();
			CountryModel defendCountry = defeatedCountry ;
			this.gamePlayModel.moveArmies(attackCountry, defendCountry, noOfArmiesToBeMoved );
		}
	}
	
}