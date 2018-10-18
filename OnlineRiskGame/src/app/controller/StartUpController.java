package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import app.view.*;
import app.model.*;

/**
 * The Class StartUpController
 *
 * @author Suruthi Raju
 */

public class StartUpController implements ActionListener {

	private StartUpView theStartUpView;
	private List<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
	private GameMapModel gmM;
	private int noOfPlayers;
	private int[] noOfCountryForRuler = new int[5];
	private String[] colorForRuler = new String[5];
	private int[] totalArmiesPlayer = new int[5];
	private int[] remainArmies = new int[5];
	private int loopvlaue = 0;

	public StartUpController() {

	}

	public StartUpController(ArrayList<PlayerModel> listOfPlayers, GameMapModel gmM) {
		this.listOfPlayers = listOfPlayers;
		this.gmM = gmM;	
		
		listOfCountrys = this.gmM.getCountries();
		noOfPlayers = listOfPlayers.size();
		
		allocateArmies();
		// Tejas -- Implement with the constructor of view as seen		
		System.out.println("ss" + remainArmies[0]);
		while(remainArmies[loopvlaue] == 0) {
			loopvlaue++;
			if(loopvlaue >= listOfPlayers.size()) {
				break;
			}
		}
		theStartUpView = new StartUpView( this.gmM, this.listOfPlayers.get(loopvlaue), remainArmies[loopvlaue]  );
		loopvlaue++;
		theStartUpView.setActionListener(this);
		theStartUpView.setVisible(true);
	}	

	public void allocateArmies() {
		
		noOfCountryForRuler[0] = 0;
		noOfCountryForRuler[1] = 0;
		noOfCountryForRuler[2] = 0;
		noOfCountryForRuler[3] = 0;
		noOfCountryForRuler[4] = 0;
		
		colorForRuler[0] = "red";
		colorForRuler[1] = "blue";
		colorForRuler[2] = "green";
		colorForRuler[3] = "yellow";
		colorForRuler[4] = "grey";
		for (int i = 0; i < listOfCountrys.size(); i++) {
			int playerNumber = getRandomBetweenRange(1, noOfPlayers);
			System.out.println("playerNumber " + playerNumber);
			String namePlayer = "Player" + playerNumber;
			PlayerModel tempMyPlayers = new PlayerModel(namePlayer, 0, colorForRuler[playerNumber-1]);
			listOfCountrys.get(i).setRuler(tempMyPlayers);
			listOfCountrys.get(i).setArmies(1);
			switch (namePlayer) {
			case "Player1":
				noOfCountryForRuler[0]++;
				break;
			case "Player2":
				noOfCountryForRuler[1]++;
				break;
			case "Player3":
				noOfCountryForRuler[2]++;
				break;
			case "Player4":
				noOfCountryForRuler[3]++;
				break;
			case "Player5":
				noOfCountryForRuler[4]++;
				break;
			default:
				break;
			}
			System.out.println("player " + noOfCountryForRuler[0] + " " + noOfCountryForRuler[1] + " " + noOfCountryForRuler[2] + " " + noOfCountryForRuler[3]);
		}
		
		for (int i=0; i< noOfPlayers;i++) {
			int tempPlayerTrop = (noOfCountryForRuler[i] / 3);
			totalArmiesPlayer[i] = noOfCountryForRuler[i] + (tempPlayerTrop - 1);
			remainArmies[i] = totalArmiesPlayer[i] - noOfCountryForRuler[i] ;
		}

		System.out.println("armies " + totalArmiesPlayer[0] + " " + totalArmiesPlayer[1] + " " + totalArmiesPlayer[2]
				+ " " + totalArmiesPlayer[3]);

		assignPlayerModel();
		// assignTroops();
		for (int i = 0; i < listOfCountrys.size(); i++) {
			System.out.println(listOfCountrys.get(i).getCountryName());
			System.out.println(listOfCountrys.get(i).getRuler());
			System.out.println(listOfCountrys.get(i).getArmies());
		}
	}

	public void assignPlayerModel() {
		for (int i = 0; i < noOfPlayers; i++) {
			listOfPlayers.get(i).setColor(colorForRuler[i]);
			listOfPlayers.get(i).setmyTroop(totalArmiesPlayer[i]);
		}
		for (int i = 0; i < listOfPlayers.size(); i++) {
			System.out.println(
					"player Model " + listOfPlayers.get(i).getNamePlayer() + " " + listOfPlayers.get(i).getmyTroop());
		}
	}

	public int getRandomBetweenRange(double min, double max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(theStartUpView.addButton)) {
			int selectedArmies = (int) theStartUpView.numOfTroopsComboBox.getSelectedItem();
			CountryModel countryName = (CountryModel) theStartUpView.countryListComboBox.getSelectedItem();
			robinTroopAssignButton(this.listOfPlayers.get(loopvlaue-1).getNamePlayer(), countryName , selectedArmies );
			checkForOverallArmies();
			if (listOfPlayers.size() > loopvlaue ) {
				callStartUpView();
			}else {
				loopvlaue = 0;
				callStartUpView();
			}			
		}
	}
	
	private void callStartUpView() {
		if (remainArmies[loopvlaue] != 0) {
			this.theStartUpView.dispose();
			this.theStartUpView = new StartUpView(this.gmM , this.listOfPlayers.get(loopvlaue), remainArmies[loopvlaue]  );
			this.theStartUpView.setActionListener(this);
			this.theStartUpView.setVisible(true);
			}	
		loopvlaue++;
	}

	private void checkForOverallArmies() {
		int numb = 0;
		for (int i=0; i<listOfPlayers.size(); i++) {
			if(remainArmies[i] != 0) {
				numb++;
			}
		}
		if (numb == 0) {
			new GamePlayController(gmM, listOfPlayers);
			this.theStartUpView.dispose();
		}
	}

	public void robinTroopAssignButton(String namePlayer, CountryModel Country, int selectedArmies) {
		System.out.println("selectedArmies " + selectedArmies);
		for (int i = 0; i < listOfCountrys.size(); i++) {
			System.out.println("CountryName " + Country.getCountryName());
			if (Country.getCountryName().equals(listOfCountrys.get(i).getCountryName())) {
				int prevArmies; 
				System.out.println("namePlayer " + namePlayer);
				switch (namePlayer) {
				case "Player1":
					if (remainArmies[0] > 0) {
						remainArmies[0] = remainArmies[0] - selectedArmies;
						System.out.println("remainArmies[0] " + remainArmies[0]);
						prevArmies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(prevArmies + selectedArmies);
					}else {
						checkForRemainArmies();
					}
					break;
				case "Player2":
					if (remainArmies[1] > 0) {
						remainArmies[1] = remainArmies[1] - selectedArmies;
						System.out.println("remainArmies[1] " + remainArmies[1]);
						prevArmies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(prevArmies + selectedArmies);
					}else {
						checkForRemainArmies();
					}
					break;
				case "Player3":
					if (remainArmies[2] > 0) {
						remainArmies[2] = remainArmies[2] - selectedArmies;
						System.out.println("remainArmies[2] " + remainArmies[2]);
						prevArmies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(prevArmies + selectedArmies);
					}
					else {
						checkForRemainArmies();
					}
					break;
				case "Player4":
					if (remainArmies[3] > 0) {
						remainArmies[3] = remainArmies[3] - selectedArmies;
						System.out.println("remainArmies[3] " + remainArmies[3]);
						prevArmies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(prevArmies + selectedArmies);
					}
					else {
						checkForRemainArmies();
					}
					break;
				case "Player5":
					if (remainArmies[4] > 0) {
						remainArmies[4] = remainArmies[4] - selectedArmies;
						System.out.println("remainArmies[4] " + remainArmies[4]);
						prevArmies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(prevArmies + selectedArmies);
					}
					else {
						checkForRemainArmies();
					}
					break;
				default:
					break;
				}
			}
		}
	}
	public void checkForRemainArmies() {
		loopvlaue++;
		while(loopvlaue < listOfPlayers.size()) {
			if (remainArmies[loopvlaue] == 0) {
				loopvlaue++;
			}else {
				break;
			}
		}
	}

}
