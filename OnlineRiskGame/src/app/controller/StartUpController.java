package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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
	private ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
	private GameMapModel gmM;
	private int noOfPlayers;
	private int[] noOfCountryForRuler = new int[5];
	private String[] colorForRuler = new String[5];
	private int[] totalArmiesPlayer = new int[5];
	private int[] remainArmies = new int[5];

	public StartUpController() {

	}

	public StartUpController(ArrayList<PlayerModel> listOfPlayers, GameMapModel gmM) {
		this.listOfPlayers = listOfPlayers;
		this.gmM = gmM;	
		
		listOfCountrys = (ArrayList<CountryModel>) gmM.getCountries();
		noOfPlayers = listOfPlayers.size();
		
		allocateArmies();
		// Tejas -- Implement with the constructor of view as seen
		//theStartUpView = new StartUpView(this.);
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
			remainArmies[i] = noOfCountryForRuler[i] - totalArmiesPlayer[i];
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
	public void actionPerformed(ActionEvent arg0) {
	}

	public void robinTroopAssignButton(String namePlayer, String CountryName) {
		for (int i = 0; i < listOfCountrys.size(); i++) {
			if (CountryName.equals(listOfCountrys.get(i).getCountryName())) {
				int armies;
				switch (CountryName) {
				case "Player1":
					if (remainArmies[0] != 0) {
						remainArmies[0] = remainArmies[0] - 1;
						armies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(armies + 1);
					}
					break;
				case "Player2":
					if (remainArmies[0] != 0) {
						remainArmies[1] = remainArmies[1] - 1;
						armies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(armies + 1);
					}
					break;
				case "Player3":
					if (remainArmies[0] != 0) {
						remainArmies[2] = remainArmies[2] - 1;
						armies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(armies + 1);
					}
					break;
				case "Player4":
					if (remainArmies[0] != 0) {
						remainArmies[3] = remainArmies[3] - 1;
						armies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(armies + 1);
					}
					break;
				case "Player5":
					if (remainArmies[0] != 0) {
						remainArmies[4] = remainArmies[4] - 1;
						armies = listOfCountrys.get(i).getArmies();
						listOfCountrys.get(i).setArmies(armies + 1);
					}
					break;
				default:
					break;
				}
			}
		}
	}

}
