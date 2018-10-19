package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.PlayerModel;
import app.view.StartUpView;

/**
 * In StartUpController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author Suruthi Raju
 * @version 1.0.0
 * 
 */

public class StartUpController implements ActionListener {

	private StartUpView theStartUpView;
	private List<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
	private GameMapModel gameMapModel;
	private int noOfPlayers;
	private int[] noOfCountryForRuler = new int[5];
	private String[] colorForRuler = new String[5];
	private int[] totalArmiesPlayer = new int[5];
	private int[] remainArmies = new int[5];
	private int loopValue = 0;
	private boolean armiesNull = false;
	private int initial = 0;

	/**
	 * Initialization of controller
	 */
	public StartUpController() {
	}

	/**
	 * Constructor initializes values and sets the screen too visible
	 * 
	 * @param listOfPlayers
	 * @param gameMapModel
	 */
	public StartUpController(ArrayList<PlayerModel> listOfPlayers, GameMapModel gameMapModel) {

		this.listOfPlayers = listOfPlayers;
		this.gameMapModel = gameMapModel;

		listOfCountrys = this.gameMapModel.getCountries();
		noOfPlayers = listOfPlayers.size();

		allocateArmies();
		checkForOverallArmies();
		initial = 1;

		if (armiesNull == false) {
			while (listOfPlayers.get(loopValue).getremainTroop() == 0) {
				loopValue++;
				if (loopValue > listOfPlayers.size()) {
					loopValue = 0;
					break;
				}
			}
			this.theStartUpView = new StartUpView(this.gameMapModel, this.listOfPlayers.get(loopValue));
			this.theStartUpView.setActionListener(this);
			for (int i = 0; i < noOfPlayers; i++) {
				this.listOfPlayers.get(i).addObserver(this.theStartUpView);
			}
			this.gameMapModel.addObserver(theStartUpView);
			this.theStartUpView.setVisible(true);
		}
		this.gameMapModel.setListOfPlayers(listOfPlayers);
	}

	/**
	 * This method allocates Player and Armies to the country to start the game play
	 */
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
			PlayerModel tempMyPlayers = new PlayerModel(namePlayer, 0, colorForRuler[playerNumber - 1], 0);
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
			System.out.println("player " + noOfCountryForRuler[0] + " " + noOfCountryForRuler[1] + " "
					+ noOfCountryForRuler[2] + " " + noOfCountryForRuler[3]);
		}

		for (int i = 0; i < noOfPlayers; i++) {
			if (noOfCountryForRuler[i] >= 3) {
				int tempPlayerTrop = (noOfCountryForRuler[i] / 3);
				totalArmiesPlayer[i] = noOfCountryForRuler[i] + (tempPlayerTrop - 1);
				remainArmies[i] = totalArmiesPlayer[i] - noOfCountryForRuler[i];
			} else {
				totalArmiesPlayer[i] = noOfCountryForRuler[i];
			}
		}

		System.out.println("armies " + totalArmiesPlayer[0] + " " + totalArmiesPlayer[1] + " " + totalArmiesPlayer[2]
				+ " " + totalArmiesPlayer[3]);

		assignPlayerModel();
		for (int i = 0; i < listOfCountrys.size(); i++) {
			System.out.println(listOfCountrys.get(i).getCountryName());
			System.out.println(listOfCountrys.get(i).getRuler());
			System.out.println(listOfCountrys.get(i).getArmies());
		}
	}

	/**
	 * This method assign Player to PlayerModel
	 */
	public void assignPlayerModel() {
		for (int i = 0; i < noOfPlayers; i++) {
			listOfPlayers.get(i).setColor(colorForRuler[i]);
			listOfPlayers.get(i).setmyTroop(totalArmiesPlayer[i]);
			listOfPlayers.get(i).setremainTroop(remainArmies[i]);
		}
		for (int i = 0; i < listOfPlayers.size(); i++) {
			System.out.println(
					"player Model " + listOfPlayers.get(i).getNamePlayer() + " " + listOfPlayers.get(i).getmyTroop());
		}
	}

	/**
	 * This method gives the Random generation of numbers within two values
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public int getRandomBetweenRange(double min, double max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}

	/**
	 * This method performs action, by Listening the action event set in view.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(this.theStartUpView.addButton)) {
			if (this.theStartUpView.numOfTroopsComboBox.getSelectedItem() != null) {
				int selectedArmies = (int) this.theStartUpView.numOfTroopsComboBox.getSelectedItem();
				CountryModel countryName = (CountryModel) this.theStartUpView.countryListComboBox.getSelectedItem();
				System.out.println("selectedArmies " + selectedArmies);

				System.out.println("loopvlaue " + loopValue);
				System.out.println("playername " + this.listOfPlayers.get(loopValue).getNamePlayer());

				this.gameMapModel.robinTroopAssignButton(loopValue, this.listOfPlayers.get(loopValue).getNamePlayer(),
						countryName, selectedArmies, listOfPlayers);
			}
			loopValue++;

			if (loopValue < listOfPlayers.size()) {
				System.out.println("loopvlaue - " + loopValue);
				this.theStartUpView.welcomeLabel
						.setText("It's " + this.listOfPlayers.get(loopValue).getNamePlayer() + "'s turn");
				this.listOfPlayers.get(loopValue).callObservers();

			} else {
				System.out.println("here");
				armiesNull = false;
				checkForOverallArmies();
				if (armiesNull == false) {
					loopValue = 0;
					System.out.println("loopvlaue -> " + loopValue);
					this.theStartUpView.welcomeLabel
							.setText("It's " + this.listOfPlayers.get(loopValue).getNamePlayer() + "'s turn");
					this.listOfPlayers.get(loopValue).callObservers();
				}
			}

		} else if (actionEvent.getSource().equals(theStartUpView.nextButton)) {
			this.gameMapModel.setPlayerIndex(0);
			new GamePlayController(gameMapModel, listOfPlayers);
			this.theStartUpView.dispose();
		}
	}

	/**
	 * Check if the remaining armies allocated to each player has been reached to
	 * zero.
	 */
	private void checkForOverallArmies() {
		int numb = 0;
		for (int i = 0; i < listOfPlayers.size(); i++) {
			if (listOfPlayers.get(i).getremainTroop() != 0) {
				numb++;
			}
		}
		if (numb == 0) {
			this.gameMapModel.setPlayerIndex(0);
			armiesNull = true;
			new GamePlayController(gameMapModel, listOfPlayers);
			if (initial == 1) {
				this.theStartUpView.dispose();
			}
		}
	}

}
