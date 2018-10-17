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
	private int noOfPlayers;
	private int[] player = new int[5];
	private int[] totalArmiesPlayer = new int[5];
	private int[] remainArmies = new int[5];

	public StartUpController() {

	}

	public StartUpController(int noOfPlayers, ArrayList<ContinentsModel> listOfContinents,
			ArrayList<CountryModel> listOfCountrys) {
		this.listOfCountrys = listOfCountrys;
		this.noOfPlayers = noOfPlayers;
		theStartUpView = new StartUpView();
		theStartUpView.setActionListener(this);
		theStartUpView.setVisible(true);
		playerValidation();
	}

	public void playerValidation() {
		if (listOfCountrys.size() > noOfPlayers) {
			System.out.println("no of players");
			allocateArmies();
		} else {
			JOptionPane.showMessageDialog(theStartUpView,
					"Number of cuntry in the Map is less than Number of Players. Select map or player Again!",
					"Map Loaded", JOptionPane.INFORMATION_MESSAGE);
			new NewGameController();
			this.theStartUpView.dispose();
		}
	}

	public void allocateArmies() {
		player[0] = 0;
		player[1] = 0;
		player[2] = 0;
		player[3] = 0;
		player[4] = 0;
		for (int i = 0; i < listOfCountrys.size(); i++) {
			int playerNumber = getRandomBetweenRange(1, noOfPlayers);
			String namePlayer = "Player" + playerNumber;
			PlayerModel tempMyPlayers = new PlayerModel(namePlayer, 0, "");
			listOfCountrys.get(i).setRuler(tempMyPlayers);
			listOfCountrys.get(i).setArmies(1);
			switch (namePlayer) {
			case "Player1":
				player[0]++;
				break;
			case "Player2":
				player[1]++;
				break;
			case "Player3":
				player[2]++;
				break;
			case "Player4":
				player[3]++;
				break;
			case "Player5":
				player[4]++;
				break;
			default:
				break;
			}
			System.out.println("player " + player[0] + " " + player[1] + " " + player[2] + " " + player[3]);
		}

		int tempPlayerTrop1 = (player[0] / 3);
		totalArmiesPlayer[0] = player[0] + (tempPlayerTrop1 - 1);
		remainArmies[0] = player[0] - totalArmiesPlayer[0];

		int tempPlayerTrop2 = (player[1] / 3);
		totalArmiesPlayer[1] = player[1] + (tempPlayerTrop2 - 1);
		remainArmies[1] = player[1] - totalArmiesPlayer[1];

		int tempPlayerTrop3 = (player[2] / 3);
		totalArmiesPlayer[2] = player[2] + (tempPlayerTrop3 - 1);
		remainArmies[2] = player[2] - totalArmiesPlayer[2];

		int tempPlayerTrop4 = (player[3] / 3);
		totalArmiesPlayer[3] = player[3] + (tempPlayerTrop4 - 1);
		remainArmies[3] = player[3] - totalArmiesPlayer[3];

		int tempPlayerTrop5 = (player[4] / 3);
		totalArmiesPlayer[4] = player[4] + (tempPlayerTrop5 - 1);
		remainArmies[4] = player[4] - totalArmiesPlayer[4];

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
			String playerName = "Player" + (i + 1);
			PlayerModel pm = new PlayerModel(playerName, totalArmiesPlayer[i], "");
			listOfPlayers.add(pm);
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
				switch (namePlayer) {
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
