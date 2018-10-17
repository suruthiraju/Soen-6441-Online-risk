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
	private GameMapModel GameMap = new GameMapModel();
	private int noOfPlayers;

	public ReinforcementController() {
		theReinforcementView = new ReinforcementView();
		theReinforcementView.setActionListener(this);
		theReinforcementView.setVisible(true);
		//GamePlayerModel GamePlay = new GamePlayerModel(GameMap, listOfPlayers);
	}
	
	public int calculateArmies() {
		int reinforceArmies = 0;
		if (listOfCountrys.size() > 3) {
			reinforceArmies = Math.round(listOfCountrys.size()/3);
		}else {
			reinforceArmies = listOfCountrys.size();
		}
		return reinforceArmies;
	}
	
//	public void checkControlValue() {
//		String permanentName ="";
//		String tempName ="";
//		boolean controlFlag = false;
//		    Country
//			permanentName = Country.get(0).getRuler().getNamePlayer();
//			for(int j=0; j < Country.size(); j++ ) {
//				 tempName= Country.get(j).getRuler().getNamePlayer();
//				 if (tempName.equals(permanentName)) {
//					 controlFlag = true;
//				 }else {
//					 controlFlag = false;
//					 break;
//				 }
//			}
//	}
	public void addArmies(int armies, String CountryName) {
		for (int i = 0; i < listOfCountrys.size(); i++) {
			int previousArmies = 0;
			if (CountryName.equals(listOfCountrys.get(i).getCountryName())) {
				switch (CountryName) {
				case "Player1":
						previousArmies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(armies + previousArmies);
					break;
				case "Player2":
						previousArmies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(armies + previousArmies);
					break;
				case "Player3":
						previousArmies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(armies + previousArmies);
					break;
				case "Player4":
						previousArmies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(armies + previousArmies);
					break;
				case "Player5":
						previousArmies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(armies + previousArmies);
					break;
				default:
					break;
				}
			}
		}
	}
	public void validate() {
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}