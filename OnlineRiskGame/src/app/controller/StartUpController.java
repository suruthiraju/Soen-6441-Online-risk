package app.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import app.model.CountryModel;
import app.model.GamePlayModel;
import app.view.StartUpView;

// TODO: Auto-generated Javadoc
/**
 * In StartUpController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author Suruthi Raju
 * @version 1.0.0
 * 
 */

public class StartUpController implements ActionListener {

	/** The start up view. */
	private StartUpView theStartUpView;

	/** The game play model. */
	private GamePlayModel gamePlayModel;

	/** The no of players. */
	private int noOfPlayers;

	/** The no of country for ruler. */
	private int[] noOfCountryForRuler = new int[5];

	/** The color for ruler. */
	private Color[] colorForRuler = new Color[5];

	/** The total armies player. */
	private int[] totalArmiesPlayer = new int[5];

	/** The remain armies. */
	private int[] remainArmies = new int[5];

	/** The loop value. */
	private int loopValue = 0;

	/** The armies null. */
	private boolean armiesNull = false;

	/** The initial. */
	private int initial = 0;

	/** The owned country 0. */
	private List<CountryModel> ownedCountry0 = new ArrayList<>();

	/** The owned country 1. */
	private List<CountryModel> ownedCountry1 = new ArrayList<>();

	/** The owned country 2. */
	private List<CountryModel> ownedCountry2 = new ArrayList<>();

	/** The owned country 3. */
	private List<CountryModel> ownedCountry3 = new ArrayList<>();

	/** The owned country 4. */
	private List<CountryModel> ownedCountry4 = new ArrayList<>();

	/**
	 * Initialization of controller.
	 */
	public StartUpController() {
	}

	/**
	 * Constructor initializes values and sets the screen too visible.
	 *
	 * @param gamePlayModel the game play model
	 */
	public StartUpController(GamePlayModel gamePlayModel) {

		this.gamePlayModel = gamePlayModel;
		noOfPlayers = this.gamePlayModel.getPlayers().size();

		allocateArmies();
		checkForOverallArmies();
		initial = 1;

		if (armiesNull == false) {
			while (this.gamePlayModel.getPlayers().get(loopValue).getremainTroop() == 0) {
				loopValue++;
				if (loopValue > this.gamePlayModel.getPlayers().size()) {
					loopValue = 0;
					break;
				}
			}
			this.theStartUpView = new StartUpView(this.gamePlayModel, this.gamePlayModel.getPlayers().get(loopValue));
			this.gamePlayModel.getConsoleText().append(" \n Initiating Startup for "
					+ this.gamePlayModel.getPlayers().get(loopValue).getNamePlayer() + "\n");
			this.theStartUpView.setActionListener(this);
			for (int i = 0; i < noOfPlayers; i++) {
				this.gamePlayModel.getPlayers().get(i).addObserver(this.theStartUpView);
			}
			this.gamePlayModel.getGameMap().addObserver(theStartUpView);
			this.theStartUpView.setVisible(true);
		}
	}

	/**
	 * This method allocates Player and Armies to the country to start the game
	 * play.
	 */
	public void allocateArmies() {

		noOfCountryForRuler[0] = 0;
		noOfCountryForRuler[1] = 0;
		noOfCountryForRuler[2] = 0;
		noOfCountryForRuler[3] = 0;
		noOfCountryForRuler[4] = 0;

		colorForRuler[0] = Color.RED;
		colorForRuler[1] = Color.BLUE;
		colorForRuler[2] = Color.GREEN;
		colorForRuler[3] = Color.YELLOW;
		colorForRuler[4] = Color.GRAY;

		int playerNumber = 0;

		for (int i = 0; i < this.gamePlayModel.getGameMap().getCountries().size(); i++) {
			playerNumber = getRandomBetweenRange(1, noOfPlayers);
			System.out.println("playerNumber " + playerNumber);
			String namePlayer = this.gamePlayModel.getPlayers().get(playerNumber - 1).getNamePlayer();

			this.gamePlayModel.getGameMap().getCountries().get(i).setRulerName(namePlayer);
			this.gamePlayModel.getGameMap().getCountries().get(i).setArmies(1);
			switch (playerNumber) {
			case 1:
				noOfCountryForRuler[0]++;
				ownedCountry0.add(this.gamePlayModel.getGameMap().getCountries().get(i));
				break;
			case 2:
				noOfCountryForRuler[1]++;
				ownedCountry1.add(this.gamePlayModel.getGameMap().getCountries().get(i));
				break;
			case 3:
				noOfCountryForRuler[2]++;
				ownedCountry2.add(this.gamePlayModel.getGameMap().getCountries().get(i));
				break;
			case 4:
				noOfCountryForRuler[3]++;
				ownedCountry3.add(this.gamePlayModel.getGameMap().getCountries().get(i));
				break;
			case 5:
				noOfCountryForRuler[4]++;
				ownedCountry4.add(this.gamePlayModel.getGameMap().getCountries().get(i));
				break;
			default:
				break;
			}
			System.out.println("player " + noOfCountryForRuler[0] + " " + noOfCountryForRuler[1] + " "
					+ noOfCountryForRuler[2] + " " + noOfCountryForRuler[3]);
		}
		this.gamePlayModel.getConsoleText().append(" Countries has been allocated to players randomly" + "\n");
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
	}

	/**
	 * This method assign Player to PlayerModel.
	 */
	public void assignPlayerModel() {
		for (int i = 0; i < noOfPlayers; i++) {
			if (i == 0) {
				this.gamePlayModel.getPlayers().get(i).setOwnedCountries(ownedCountry0);
			} else if (i == 1) {
				this.gamePlayModel.getPlayers().get(i).setOwnedCountries(ownedCountry1);
			} else if (i == 2) {
				this.gamePlayModel.getPlayers().get(i).setOwnedCountries(ownedCountry2);
			} else if (i == 3) {
				this.gamePlayModel.getPlayers().get(i).setOwnedCountries(ownedCountry3);
			} else if (i == 4) {
				this.gamePlayModel.getPlayers().get(i).setOwnedCountries(ownedCountry4);
			}

			this.gamePlayModel.getPlayers().get(i).setColor(colorForRuler[i]);
			this.gamePlayModel.getPlayers().get(i).setmyTroop(totalArmiesPlayer[i]);
			this.gamePlayModel.getPlayers().get(i).setremainTroop(remainArmies[i]);
		}
		for (int i = 0; i < this.gamePlayModel.getPlayers().size(); i++) {
			System.out.println("player Model " + this.gamePlayModel.getPlayers().get(i).getNamePlayer() + " "
					+ this.gamePlayModel.getPlayers().get(i).getmyTroop());
		}
	}

	/**
	 * This method gives the Random generation of numbers within two values.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the random between range
	 */
	public int getRandomBetweenRange(double min, double max) {
		int x = (int) ((Math.random() * ((max - min) + 1)) + min);
		return x;
	}

	/**
	 * This method performs action, by Listening the action event set in view.
	 *
	 * @param actionEvent the action event
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
				System.out.println("playername " + this.gamePlayModel.getPlayers().get(loopValue).getNamePlayer());

				this.gamePlayModel.getGameMap().robinTroopAssignButton(loopValue,
						this.gamePlayModel.getPlayers().get(loopValue).getNamePlayer(), countryName, selectedArmies,
						this.gamePlayModel.getPlayers());
				this.gamePlayModel.getConsoleText()
						.append(this.gamePlayModel.getPlayers().get(loopValue).getNamePlayer() + "'s Armies "
								+ selectedArmies + " has been added to " + countryName.getCountryName() + " \n");
			}
			loopValue++;

			if (loopValue < this.gamePlayModel.getPlayers().size()) {
				System.out.println("loopvlaue - " + loopValue);
				this.theStartUpView.welcomeLabel
						.setText("It's " + this.gamePlayModel.getPlayers().get(loopValue).getNamePlayer() + "'s turn");
				this.gamePlayModel.getPlayers().get(loopValue).callObservers();

			} else {
				armiesNull = false;
				checkForOverallArmies();
				if (armiesNull == false) {
					loopValue = 0;
					System.out.println("loopvlaue -> " + loopValue);
					this.theStartUpView.welcomeLabel.setText(
							"It's " + this.gamePlayModel.getPlayers().get(loopValue).getNamePlayer() + "'s turn");
					this.gamePlayModel.getPlayers().get(loopValue).callObservers();
				}
			}

		} else if (actionEvent.getSource().equals(theStartUpView.nextButton)) {
			this.gamePlayModel.getGameMap().setPlayerIndex(0);
			new GamePlayController(gamePlayModel);
			this.theStartUpView.dispose();
		}
	}

	/**
	 * Check if the remaining armies allocated to each player has been reached to
	 * zero.
	 */
	public boolean checkForOverallArmies() {
		int numb = 0;
		for (int i = 0; i < this.gamePlayModel.getPlayers().size(); i++) {
			if (this.gamePlayModel.getPlayers().get(i).getremainTroop() != 0) {
				numb++;
				return false;
			}
		}
		if (numb == 0) {
			this.gamePlayModel.getGameMap().setPlayerIndex(0);
			armiesNull = true;
			new GamePlayController(gamePlayModel);
			if (initial == 1) {
				this.theStartUpView.dispose();
				return true;
			}
		}
		return false;
	}

}
