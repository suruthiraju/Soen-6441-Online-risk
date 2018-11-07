package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import java.awt.event.ItemListener;
import app.model.CountryModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;
import app.utilities.Validation;
import app.view.AttackView;
import app.view.FortificationView;
import app.view.ReinforcementView;

/**
 * In PlayerController, the data flow into model object and updates the view
 * whenever data changes.
 * 
 * @author Hamid
 * @version 1.0.0
 *
 */
public class PlayerController implements ActionListener,ItemListener {
	
	private GamePlayModel gamePlayModel;
	private ReinforcementView theReinforcementView;
	private FortificationView theFortificationView;
	private AttackView theAttackView;
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
	private int noOfPlayers;
	
	/**
	 * Constructor initializes values and sets the screen too visible
	 * 
	 * @param gamePlayModel
	 */
	public PlayerController(GamePlayModel gamePlayModel) {
		this.gamePlayModel = gamePlayModel;
		reinforcement();
		//reinforcementView();
		//attackView();
		//fortificationview();
	}
	
	/**
	 * This method is called in reinforcement phase. 
	 * 
	 */
	public void reinforcement(){
		this.gamePlayModel.getGameMap().getPlayerTurn().setmyTroop(this.gamePlayModel.numberOfCountries(this.gamePlayModel));
		theReinforcementView = new ReinforcementView(this.gamePlayModel);
		theReinforcementView.setActionListener(this);
		theReinforcementView.setVisible(true);

		this.gamePlayModel.getGameMap().addObserver(theReinforcementView);
		this.gamePlayModel.addObserver(theReinforcementView);
		for (int i = 0; i < noOfPlayers; i++) {
			this.listOfPlayers.get(i).addObserver(this.theReinforcementView);
		}
		
	}
	
	/**
	 * This method is called in fortification phase. 
	 */
	public void fortification()
	{
		theFortificationView = new FortificationView(this.gamePlayModel);
		theFortificationView.setActionListener(this);
		theFortificationView.setItemListener(this);
		theFortificationView.setVisible(true);
		this.gamePlayModel.getGameMap().addObserver(this.theFortificationView);
	}
	
	/**
	 * This method is called in attack phase.
	 */
	public void attack()
	{
		theAttackView = new AttackView(this.gamePlayModel);
		theAttackView.setActionListener(this);
		theAttackView.setVisible(true);
	}
	
	/**
	 * This method performs action, by Listening the action event set in view.
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
				this.theReinforcementView.dispose();
				attack();
				
			}
		}
		if (actionEvent.getSource().equals(this.theAttackView.nextButton)) {
			new FortificationController(this.gamePlayModel);
			this.theAttackView.dispose();
			fortification();
		}
		if (actionEvent.getSource().equals(this.theFortificationView.moveButton)) {

			// BFS
			Validation val = new Validation();
			if (val.checkIfValidMove(gamePlayModel.getGameMap(),
					(CountryModel) this.theFortificationView.fromCountryListComboBox.getSelectedItem(),
					(CountryModel) this.theFortificationView.toCountryListComboBox.getSelectedItem())) {
				this.gamePlayModel.getGameMap().setMovingArmies(
						(Integer) this.theFortificationView.numOfTroopsComboBox.getSelectedItem(),
						(CountryModel) this.theFortificationView.fromCountryListComboBox.getSelectedItem(),
						(CountryModel) this.theFortificationView.toCountryListComboBox.getSelectedItem());
			}

			int index = this.gamePlayModel.getGameMap().getPlayerIndex();
			index++;
			if (this.gamePlayModel.getPlayers().size() > index) {
				this.gamePlayModel.getGameMap().setPlayerIndex(index);
				this.gamePlayModel.getPlayers().get(index).callObservers();
				new GamePlayController(this.gamePlayModel);
				this.theFortificationView.dispose();
			} else {
				JOptionPane.showOptionDialog(null, "Bravo! Game is over! No one won!", "Valid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				this.theFortificationView.dispose();
			}

		} else if (actionEvent.getSource().equals(this.theFortificationView.fromCountryListComboBox)) {
			this.gamePlayModel
					.setSelectedComboBoxIndex(this.theFortificationView.fromCountryListComboBox.getSelectedIndex());
		}
	
	}
	/**
	 * Item Listener
	 * 
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void itemStateChanged(ItemEvent itemEvent) {
		if (itemEvent.getSource().equals(this.theFortificationView.fromCountryListComboBox)) {
			this.gamePlayModel
					.setSelectedComboBoxIndex(this.theFortificationView.fromCountryListComboBox.getSelectedIndex());
		}

	}
}
