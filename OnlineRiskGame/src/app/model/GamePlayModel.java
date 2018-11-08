package app.model;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Arrays;
import java.util.Collections;
import java.util.Observable;

/**
 * "GamePlayerModel" class represents an object containing current map and
 * players.
 * 
 * @author GROUP-35
 */
public class GamePlayModel extends Observable {

	private GameMapModel gameMapModel;
	private ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();
	private ArrayList<CardModel> deck = new ArrayList<CardModel>();
	/**
	 * to save Selected ComboBox index
	 */
	private int selectedComboBoxIndex;
	/**
	 * to save Selected ComboBox index
	 */
	private int selectedAttackComboBoxIndex;
	/**
	 * to save Selected ComboBox index
	 */
	private int selectedDefendComboBoxIndex;
	private boolean countryOwned = false;
	private boolean armyToMoveFlag;
	private boolean cardToBeAssigned;
	private StringBuilder consoleText;
	private CountryModel defeatedCountry;

	/**
	 * Constructor
	 * 
	 * @param gameMap
	 * @param players
	 */
	public GamePlayModel(GameMapModel gameMap, ArrayList<PlayerModel> players, ArrayList<CardModel> deck) {
		this.gameMapModel = gameMap;
		this.players = players;
		this.deck = deck;
		this.consoleText = new StringBuilder("Hello to the Risk Game ! ");
	}

	public GamePlayModel() {
		this.consoleText = new StringBuilder("Hello to the Risk Game ! ");
	}

	/**
	 * @return the gameMap.
	 */
	public GameMapModel getGameMap() {
		return gameMapModel;
	}

	/**
	 * Sets the gameMap Model.
	 * 
	 * @param gameMap
	 */
	public void setGameMap(GameMapModel gameMap) {
		this.gameMapModel = gameMap;
	}

	/**
	 * @return the list of players.
	 */
	public ArrayList<PlayerModel> getPlayers() {
		return players;
	}

	/**
	 * Sets the list of players.
	 * 
	 * @param players
	 */
	public void setPlayers(ArrayList<PlayerModel> players) {
		this.players = players;
	}

	public void setDefeatedCountry(CountryModel defeatedCountry) {
		this.defeatedCountry = defeatedCountry;
	}

	public CountryModel getDefeatedCountry() {
		return this.defeatedCountry;
	}

	/**
	 * @return the list of card.
	 * @throws org.json.simple.parser.ParseException
	 */
	public ArrayList<CardModel> getCardFromJSON() throws org.json.simple.parser.ParseException {
		try {
			JSONParser parser = new JSONParser();
			Object cards = parser.parse(new FileReader(
					System.getProperty("user.dir") + "src/app/helper/ConstantCard.json"));
			JSONObject jsonObject = (JSONObject) cards;
			System.out.println("jsonObject " + jsonObject.get("cards"));
			JSONArray cardsJSON = (JSONArray) jsonObject.get("cards");

			int i = 0;
			CardModel cardModel = new CardModel();
			for (Object o : cardsJSON) {
				JSONObject card = (JSONObject) o;

				int cardId = Integer.parseInt((String) card.get("cardId"));
				System.out.println("cardId " + cardId);
				cardModel.setCardId(cardId);

				int cardValue = Integer.parseInt((String) card.get("cardValue"));
				System.out.println("cardValue " + cardValue);
				cardModel.setCardValue(cardValue);
				this.deck.add(cardModel);
			}
			Collections.shuffle(this.deck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.deck;
	}

	/**
	 * Sets the list of card.
	 * 
	 * @param deck
	 */
	public ArrayList<CardModel> getCards() {
		return this.deck;
	}

	public void setCards(ArrayList<CardModel> deck) {
		this.deck = deck;
	}

	public StringBuilder getConsoleText() {
		return consoleText;
	}

	public void setConsoleText(StringBuilder consoleText) {
		this.consoleText = consoleText;
	}

	public PlayerModel getPlayer(CountryModel parmCountry) {
		int i, j;
		for (i = 0; i < players.size(); i++) {
			for (j = 0; j < players.get(i).getOwnedCountries().size(); j++) {
				if (parmCountry.getCountryName().equals(players.get(i).getOwnedCountries().get(j).getCountryName())) {
					return players.get(i);
				} else {
					continue;
				}
			}
		}
		return null;

	}

	public void setSelectedArmiesToCountries(int selectedArmies, CountryModel countryName) {
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).equals(countryName)) {
				this.gameMapModel.getCountries().get(i)
						.setArmies(this.gameMapModel.getCountries().get(i).getArmies() + selectedArmies);
				for (int j = 0; j < this.getPlayers().size(); j++) {
					if (this.getPlayers().get(j).getNamePlayer()
							.equals(this.gameMapModel.getCountries().get(i).getRulerName())) {
						this.getPlayers().get(j).setremainTroop(this.getPlayers().get(j).getremainTroop() - selectedArmies);
						this.consoleText.append("\n"+this.getPlayers().get(j).getNamePlayer()+" added "+ selectedArmies+ " armies to " + countryName.getCountryName()+"\n");
					}
				}
			}
		}
		
		callObservers();
	}

	public int numberOfCountries() {
		int numberOfCountries = 0;

		for (int i = 0; i < this.getPlayers().size(); i++) {

			if (this.getPlayers().get(i).getNamePlayer().equals(this.getGameMap().getPlayerTurn().getNamePlayer())) {
				numberOfCountries = this.getPlayers().get(i).getOwnedCountries().size();
			}
		}
		return reinforcementArmies(numberOfCountries);
	}

	public int reinforcementArmies(int numberOfCountries) {
		int reinforceArmies = 0;
		if (numberOfCountries > 3) {
			reinforceArmies = 3 + Math.round(numberOfCountries / 3);
		} else {
			reinforceArmies = 3;
		}
		if (reinforceArmies > 12) {
			reinforceArmies = 12;
		}
		return reinforceArmies;
	}

	public ArrayList<CountryModel> getDefendCountryList(CountryModel attackCountryName) {
		ArrayList<CountryModel> linkedCountry;
		ArrayList<CountryModel> defenderCountryList = new ArrayList<CountryModel>();
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).equals(attackCountryName)) {
				linkedCountry = (ArrayList<CountryModel>) this.gameMapModel.getCountries().get(i).getLinkedCountries();
				for (int j = 0; j < linkedCountry.size(); j++) {
					if (!attackCountryName.getRulerName().equals(linkedCountry.get(j).getRulerName())) {
						defenderCountryList.add(linkedCountry.get(j));
					}
				}
			}
		}
		return defenderCountryList;
	}

	public void singleStrike(int attackDice, CountryModel attackCountry, int defendDice, CountryModel defendCountry) {
		Integer[] attackDiceRoll = new Integer[attackDice];
		Integer[] defendDiceRoll = new Integer[defendDice];
		for (int i = 0; i < attackDice; i++) {
			attackDiceRoll[i] = getRandomBetweenRange(1, 6);
		}
		for (int i = 0; i < defendDice; i++) {
			defendDiceRoll[i] = getRandomBetweenRange(1, 6);
		}
		Arrays.sort(attackDiceRoll, Collections.reverseOrder());
		Arrays.sort(defendDiceRoll, Collections.reverseOrder());
		System.out.println(Arrays.toString(attackDiceRoll));
		System.out.println(Arrays.toString(defendDiceRoll));
		this.consoleText.append("\n Attack country dice " + Arrays.toString(attackDiceRoll)+" \n");
		this.consoleText.append("Defender country dice " + Arrays.toString(defendDiceRoll) +" \n");
		for (int i = 0; i < defendDice; i++) {
			if (attackDiceRoll[i] > defendDiceRoll[i]) {
				armiesDeduction(defendCountry, 1);
			} else {
				armiesDeduction(attackCountry, 1);
			}
		}
		if (countryOwned == true) {
			this.consoleText.append("Attacker " + attackCountry.getRulerName() + " defeated Country "+ defendCountry.getCountryName() +" \n");
			for (int i = 0; i < this.getPlayers().size(); i++) {
				if (this.getPlayers().get(i).getNamePlayer().equals(defendCountry.getRulerName())) {
					this.getPlayers().get(i).defend(defendCountry);
				}
				if (this.getPlayers().get(i).getNamePlayer().equals(attackCountry.getRulerName())) {					
					this.getPlayers().get(i).attacked(defendCountry);
				}
			}
			for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
				if (this.gameMapModel.getCountries().get(i).getCountryName().equals(defendCountry.getCountryName())) {
					this.gameMapModel.getCountries().get(i).setRulerName(attackCountry.getRulerName());
				}
			}

			this.setArmyToMoveText(true);
			this.setCardToBeAssigned(true);
		}
		callObservers();
	}

	public CountryModel armiesDeduction(CountryModel countryForDeduction, int armiesToDeduct) {

		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).getCountryName().equals(countryForDeduction.getCountryName())) {
				if (this.gameMapModel.getCountries().get(i).getArmies() == 1) {
					countryOwned = true;
					this.gameMapModel.getCountries().get(i)
							.setArmies(this.gameMapModel.getCountries().get(i).getArmies() - armiesToDeduct);
				} else {
					this.gameMapModel.getCountries().get(i)
							.setArmies(this.gameMapModel.getCountries().get(i).getArmies() - armiesToDeduct);
				}
			}
		}
		for (int i = 0; i < this.getPlayers().size(); i++) {
			if (this.getPlayers().get(i).getNamePlayer().equals(countryForDeduction.getRulerName())) {
				this.getPlayers().get(i).setmyTroop(this.getPlayers().get(i).getmyTroop() - armiesToDeduct);
			}
		}
		return countryForDeduction;
	}

	public void alloutStrike(CountryModel attackCountry, CountryModel defendCountry) {
		int attackTotalArmies = attackCountry.getArmies() - 1;
		int defendTotalArmies = defendCountry.getArmies();
		int attackDice, defendDice;

		while (attackTotalArmies > 0 && defendTotalArmies > 0) {
			if (attackTotalArmies > 3) {
				attackDice = 3;
			} else {
				attackDice = attackTotalArmies;
			}
			if (defendTotalArmies > 2) {
				defendDice = 2;
			} else {
				defendDice = defendTotalArmies;
			}
			Integer[] attackDiceRoll = new Integer[attackDice];
			Integer[] defendDiceRoll = new Integer[defendDice];
			for (int i = 0; i < attackDice; i++) {
				attackDiceRoll[i] = getRandomBetweenRange(1, 6);
			}
			for (int i = 0; i < defendDice; i++) {
				defendDiceRoll[i] = getRandomBetweenRange(1, 6);
			}
			Arrays.sort(attackDiceRoll, Collections.reverseOrder());
			Arrays.sort(defendDiceRoll, Collections.reverseOrder());
			System.out.println(Arrays.toString(attackDiceRoll));
			System.out.println(Arrays.toString(defendDiceRoll));
			this.consoleText.append("Attack country dice " + Arrays.toString(attackDiceRoll)+" \n");
			this.consoleText.append("Defender country dice " + Arrays.toString(defendDiceRoll) +" \n");
			for (int i = 0; i < defendDice; i++) {
				if (attackDiceRoll[i] > defendDiceRoll[i]) {
					armiesDeduction(defendCountry, 1);
					defendTotalArmies--;
				} else {
					armiesDeduction(attackCountry, 1);
					attackTotalArmies--;
				}
			}
		}
		if (countryOwned == true) {
			this.consoleText.append("Attacker " + attackCountry.getRulerName() + "defeated Country "+ defendCountry.getCountryName() +" \n");
			for (int i = 0; i < this.getPlayers().size(); i++) {
				if (this.getPlayers().get(i).getNamePlayer().equals(defendCountry.getRulerName())) {
					this.getPlayers().get(i).defend(defendCountry);
				}
				if (this.getPlayers().get(i).getNamePlayer().equals(attackCountry.getRulerName())) {
					this.getPlayers().get(i).attacked(defendCountry);
				}
			}
			for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
				if (this.gameMapModel.getCountries().get(i).getCountryName().equals(defendCountry.getCountryName())) {
					this.gameMapModel.getCountries().get(i).setRulerName(attackCountry.getRulerName());
				}
			}

			this.setArmyToMoveText(true);
			this.setCardToBeAssigned(true);
		}
		callObservers();
	}

	public void moveArmies(CountryModel attackCountry, CountryModel defendCountry, int noOfArmiesToBeMoved) {
		this.consoleText.append(attackCountry.getRulerName()+ " Armies "+ noOfArmiesToBeMoved + " moved from" + attackCountry.getCountryName() + " to "+ defendCountry.getCountryName()  +" \n");
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).getCountryName().equals(attackCountry.getCountryName())) {
				this.gameMapModel.getCountries().get(i)
						.setArmies(this.gameMapModel.getCountries().get(i).getArmies() - noOfArmiesToBeMoved);
			}
			if (this.gameMapModel.getCountries().get(i).getCountryName().equals(defendCountry.getCountryName())) {
				this.gameMapModel.getCountries().get(i)
						.setArmies(this.gameMapModel.getCountries().get(i).getArmies() + noOfArmiesToBeMoved);
			}
		}
		this.setArmyToMoveText(false);
		callObservers();
	}

	public void setSelectedComboBoxIndex(int selectedComboBoxIndex) {
		this.selectedComboBoxIndex = selectedComboBoxIndex;
		callObservers();

	}

	public int getSelectedComboBoxIndex() {
		return this.selectedComboBoxIndex;
	}

	public void setSelectedAttackComboBoxIndex(int selectedAttackComboBoxIndex) {
		this.selectedAttackComboBoxIndex = selectedAttackComboBoxIndex;
		callObservers();

	}

	public int getSelectedAttackComboBoxIndex() {
		return this.selectedAttackComboBoxIndex;
	}

	public void setSelectedDefendComboBoxIndex(int selectedDefendComboBoxIndex) {
		this.selectedDefendComboBoxIndex = selectedDefendComboBoxIndex;
		callObservers();

	}

	public int getSelectedDefendComboBoxIndex() {
		return this.selectedDefendComboBoxIndex;
	}

	public void setArmyToMoveText(boolean armyToMoveFlag) {
		this.armyToMoveFlag = armyToMoveFlag;
		if (armyToMoveFlag == true) {
			callObservers();
		}
	}

	public boolean getArmyToMoveText() {
		return this.armyToMoveFlag;
	}

	public void setCardToBeAssigned(boolean cardToBeAssigned) {
		this.cardToBeAssigned = cardToBeAssigned;
	}

	public boolean getCardToBeAssigned() {
		return this.cardToBeAssigned;
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
	};

	/**
	 * Method used to notify state change whenever any change is reflected by
	 * CreateContinentController via CreateContinentView
	 */
	public void callObservers() {
			for (int i = 0; i < this.getPlayers().size(); i++) {
			this.worldCoverage(this.getPlayers().get(i));
			this.consoleText.append("						"+this.getPlayers().get(i).getNamePlayer()+" has "+this.getPlayers().get(i).getmyTroop()+" troops \n");
		}
		this.getConsoleText().append("\n");
		for (int i = 0; i < this.gameMapModel.getContinents().size(); i++) {
			continentCoverage(this.gameMapModel.getContinents().get(i));
		}

		setChanged();
		notifyObservers(this);
	}

	public void worldCoverage(PlayerModel parmPlayer) {
		double percentage = (parmPlayer.getOwnedCountries().size() * 100) / this.gameMapModel.getCountries().size();
		this.getConsoleText()
				.append("						"+" Map coverage for " + parmPlayer.getNamePlayer() + " is " + percentage + "% " + "\n");

	}

	public void continentCoverage(ContinentsModel parmContinent) {
		this.getConsoleText().append("						"+"Continent " + parmContinent.getContinentName() + "'s coverage distribution: \n");
		int countryCount = 0;
		double percentage;

		for (int j = 0; j < this.players.size(); j++) {
			for (int k = 0; k < this.players.get(j).getOwnedCountries().size(); k++) {
				if (this.players.get(j).getOwnedCountries().get(k).getcontinentName()
						.equals(parmContinent.getContinentName())) {
					countryCount = countryCount + 1;
				}

			}
			percentage = (countryCount*100) / parmContinent.getCoveredCountries().size();
			this.getConsoleText().append("						"+this.players.get(j).getNamePlayer() + " has covered " + percentage
					+ "% of the continent " + parmContinent.getContinentName() + "\n");
			countryCount = 0;
		}

	}
}