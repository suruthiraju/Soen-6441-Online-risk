package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import app.model.*;
import app.view.*;

public class ReinforcementController implements ActionListener {

    private ReinforcementView theReinforcementView;
	private ArrayList<ContinentsModel> listOfContinents = new ArrayList<ContinentsModel>();
	private ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
	private GameMapModel gameMapModel = null;
	private int noOfPlayers;

	public ReinforcementController(GameMapModel gameMapModel) {
		this.gameMapModel = gameMapModel;
		this.gameMapModel.getPlayerTurn().setmyTroop(this.calculateArmies());
		theReinforcementView = new ReinforcementView(this.gameMapModel);
		theReinforcementView.setActionListener(this);
		theReinforcementView.setVisible(true);
		
		this.gameMapModel.addObserver(theReinforcementView);
		
		//GamePlayerModel GamePlay = new GamePlayerModel(GameMap, listOfPlayers);
	}
	
	public int calculateArmies() {
		int reinforceArmies = 0;
		
		 
		for(int i = 0;i<this.gameMapModel.getCountries().size();i++) {
			if(this.gameMapModel.getCountries().get(i).getRuler().equals(this.gameMapModel.getPlayerTurn())) {
				this.listOfCountrys.add(this.gameMapModel.getCountries().get(i));
			}
		}
		if (listOfCountrys.size() > 3) {
			reinforceArmies = 3 + Math.round(listOfCountrys.size()/3);
		}else {
			reinforceArmies = 3;
		}
		if(reinforceArmies>12) {
			reinforceArmies = 12;
		}
		return reinforceArmies;
	}
	


	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getSource().equals(this.theReinforcementView.addButton)) {
			int selectedArmies = 0;
			if(theReinforcementView.numOfTroopsComboBox.getSelectedItem()!=null) {
			 selectedArmies = (int) theReinforcementView.numOfTroopsComboBox.getSelectedItem();
			CountryModel countryName = (CountryModel) theReinforcementView.countryListComboBox.getSelectedItem();
			//this.gameMapModel.ge
			
			this.gameMapModel.setSelectedArmiesToCountries(selectedArmies,countryName);
			}
			else {
				
				new FortificationController(this.gameMapModel);
				this.theReinforcementView.dispose();
			}
		}
	}
}