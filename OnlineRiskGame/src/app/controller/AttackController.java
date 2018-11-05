package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import app.model.CountryModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;
import app.view.AttackView;

/**
 * In Reinforcement Controller, the data flow into model object and updates the
 * view whenever data changes.
 * 
 * @author Suruthi Raju
 *
 */
public class AttackController implements ActionListener, ItemListener {

	private AttackView theAttackView;
	private GamePlayModel gamePlayModel;

	/**
	 * Constructor initializes values and sets the screen too visible
	 * 
	 * @param gamePlayModel
	 */
	public AttackController(GamePlayModel gamePlayModel) {
		this.gamePlayModel = gamePlayModel;
		theAttackView = new AttackView(this.gamePlayModel);
		theAttackView.setActionListener(this);
		theAttackView.setItemListener(this);
		theAttackView.setVisible(true);
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
			this.gamePlayModel.singleStrike(attackDice, attackCountry, defendDice, defendCountry);
		}else if (actionEvent.getSource().equals(this.theAttackView.alloutButton)) {
			this.gamePlayModel.alloutStrike();
		}
	}
	
	/**
	 * Item Listener
	 * 
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent itemEvent) {
		if (itemEvent.getSource().equals(this.theAttackView.attackCountryListComboBox)) {
			this.gamePlayModel
					.setSelectedAttackComboBoxIndex(this.theAttackView.attackCountryListComboBox.getSelectedIndex());
		}else if (itemEvent.getSource().equals(this.theAttackView.defendCountryListComboBox)) {
			this.gamePlayModel
			.setSelectedDefendComboBoxIndex(this.theAttackView.defendCountryListComboBox.getSelectedIndex());
		}

	}
}