package app.model;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Observable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
	/**
	 * Default Constructor
	 * 
	 *
	 */
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

	/**
	 * Sets the defeated country.
	 *
	 * @param defeatedCountry the new defeated country
	 */
	public void setDefeatedCountry(CountryModel defeatedCountry) {
		this.defeatedCountry = defeatedCountry;
	}

	/**
	 * Gets the defeated country.
	 *
	 * @return the defeated country
	 */
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
					System.getProperty("user.dir") + "/OnlineRiskGame/src/app/helper/ConstantCard.json"));
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
			//Collections.shuffle(this.deck);
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

	/**
	 * Sets the list of card.
	 * 
	 * @param deck
	 */
	public CardModel getCard() {
		CardModel temp = this.deck.get(this.deck.size() - 1);
		this.deck.remove(this.deck.size() - 1);
		return temp;
	}

	/**
	 * Sets the cards.
	 *
	 * @param deck the new cards
	 */
	public void setCards(ArrayList<CardModel> deck) {
		this.deck = deck;
	}

	/**
	 * Gets the console text.
	 *
	 * @return the console text
	 */
	public StringBuilder getConsoleText() {
		return consoleText;
	}

	/**
	 * Sets the console text.
	 *
	 * @param consoleText the new console text
	 */
	public void setConsoleText(StringBuilder consoleText) {
		this.consoleText = consoleText;
	}

	/**
	 * Gets the player.
	 *
	 * @param parmCountry the parm country
	 * @return the player
	 */
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

	/**
	 * Sets the selected armies to countries.
	 *
	 * @param selectedArmies the selected armies
	 * @param countryName the country name
	 */
	public void setSelectedArmiesToCountries(int selectedArmies, CountryModel countryName) {
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).equals(countryName)) {
				this.gameMapModel.getCountries().get(i)
						.setArmies(this.gameMapModel.getCountries().get(i).getArmies() + selectedArmies);
				for (int j = 0; j < this.getPlayers().size(); j++) {
					if (this.getPlayers().get(j).getNamePlayer()
							.equals(this.gameMapModel.getCountries().get(i).getRulerName())) {
						this.getPlayers().get(j)
								.setremainTroop(this.getPlayers().get(j).getremainTroop() - selectedArmies);
						this.consoleText.append("\n" + this.getPlayers().get(j).getNamePlayer() + " added "
								+ selectedArmies + " armies to " + countryName.getCountryName() + "\n");
					}
				}
			}
		}

		callObservers();
	}

	/**
	 * Number of countries.
	 *
	 * @return the int
	 */
	public int numberOfCountries() {
		int numberOfCountries = 0;

		for (int i = 0; i < this.getPlayers().size(); i++) {

			if (this.getPlayers().get(i).getNamePlayer().equals(this.getGameMap().getPlayerTurn().getNamePlayer())) {
				numberOfCountries = this.getPlayers().get(i).getOwnedCountries().size();
			}
		}
		return reinforcementArmies(numberOfCountries);
	}

	/**
	 * Reinforcement armies.
	 *
	 * @param numberOfCountries the number of countries
	 * @return the int
	 */
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

	/**
	 * Gets the defend country list.
	 *
	 * @param attackCountryName the attack country name
	 * @return the defend country list
	 */
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

	/**
	 * Single strike.
	 *
	 * @param attackDice the attack dice
	 * @param attackCountry the attack country
	 * @param defendDice the defend dice
	 * @param defendCountry the defend country
	 */
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
		this.consoleText.append("\n Attack country dice " + Arrays.toString(attackDiceRoll) + " \n");
		this.consoleText.append("Defender country dice " + Arrays.toString(defendDiceRoll) + " \n");
		for (int i = 0; i < defendDice; i++) {
			if (attackDiceRoll[i] > defendDiceRoll[i]) {
				armiesDeduction(defendCountry, 1);
			} else {
				armiesDeduction(attackCountry, 1);
			}
		}
		if (countryOwned == true) {
			this.consoleText.append("Attacker " + attackCountry.getRulerName() + " defeated Country "
					+ defendCountry.getCountryName() + " \n");
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

	/**
	 * Armies deduction.
	 *
	 * @param countryForDeduction the country for deduction
	 * @param armiesToDeduct the armies to deduct
	 * @return the country model
	 */
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

	/**
	 * Allout strike.
	 *
	 * @param attackCountry the attack country
	 * @param defendCountry the defend country
	 */
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
			this.consoleText.append("Attack country dice " + Arrays.toString(attackDiceRoll) + " \n");
			this.consoleText.append("Defender country dice " + Arrays.toString(defendDiceRoll) + " \n");
			for (int i = 0; (i < defendDice && i < attackDice); i++) {
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
			this.consoleText.append("Attacker " + attackCountry.getRulerName() + "defeated Country "
					+ defendCountry.getCountryName() + " \n");
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

	/**
	 * Move armies.
	 *
	 * @param attackCountry the attack country
	 * @param defendCountry the defend country
	 * @param noOfArmiesToBeMoved the no of armies to be moved
	 */
	public void moveArmies(CountryModel attackCountry, CountryModel defendCountry, int noOfArmiesToBeMoved) {
		this.consoleText.append(attackCountry.getRulerName() + " Armies " + noOfArmiesToBeMoved + " moved from"
				+ attackCountry.getCountryName() + " to " + defendCountry.getCountryName() + " \n");
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

	/**
	 * Sets the selected combo box index.
	 *
	 * @param selectedComboBoxIndex the new selected combo box index
	 */
	public void setSelectedComboBoxIndex(int selectedComboBoxIndex) {
		this.selectedComboBoxIndex = selectedComboBoxIndex;
		callObservers();

	}

	/**
	 * Gets the selected combo box index.
	 *
	 * @return the selected combo box index
	 */
	public int getSelectedComboBoxIndex() {
		return this.selectedComboBoxIndex;
	}

	/**
	 * Sets the selected attack combo box index.
	 *
	 * @param selectedAttackComboBoxIndex the new selected attack combo box index
	 */
	public void setSelectedAttackComboBoxIndex(int selectedAttackComboBoxIndex) {
		this.selectedAttackComboBoxIndex = selectedAttackComboBoxIndex;
		callObservers();

	}

	/**
	 * Gets the selected attack combo box index.
	 *
	 * @return the selected attack combo box index
	 */
	public int getSelectedAttackComboBoxIndex() {
		return this.selectedAttackComboBoxIndex;
	}

	/**
	 * Sets the selected defend combo box index.
	 *
	 * @param selectedDefendComboBoxIndex the new selected defend combo box index
	 */
	public void setSelectedDefendComboBoxIndex(int selectedDefendComboBoxIndex) {
		this.selectedDefendComboBoxIndex = selectedDefendComboBoxIndex;
		callObservers();

	}

	/**
	 * Gets the selected defend combo box index.
	 *
	 * @return the selected defend combo box index
	 */
	public int getSelectedDefendComboBoxIndex() {
		return this.selectedDefendComboBoxIndex;
	}

	/**
	 * Sets the army to move text.
	 *
	 * @param armyToMoveFlag the new army to move text
	 */
	public void setArmyToMoveText(boolean armyToMoveFlag) {
		this.armyToMoveFlag = armyToMoveFlag;
		if (armyToMoveFlag == true) {
			callObservers();
		}
	}

	/**
	 * Gets the army to move text.
	 *
	 * @return the army to move text
	 */
	public boolean getArmyToMoveText() {
		return this.armyToMoveFlag;
	}

	/**
	 * Sets the card to be assigned.
	 *
	 * @param cardToBeAssigned the new card to be assigned
	 */
	public void setCardToBeAssigned(boolean cardToBeAssigned) {
		this.cardToBeAssigned = cardToBeAssigned;
	}

	/**
	 * Gets the card to be assigned.
	 *
	 * @return the card to be assigned
	 */
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
			this.consoleText.append("						" + this.getPlayers().get(i).getNamePlayer() + " has "
					+ this.getPlayers().get(i).getmyTroop() + " troops \n");
		}
		this.getConsoleText().append("\n");
		for (int i = 0; i < this.gameMapModel.getContinents().size(); i++) {
			continentCoverage(this.gameMapModel.getContinents().get(i));
		}

		setChanged();
		notifyObservers(this);
	}

	/**
	 * World coverage.
	 *
	 * @param parmPlayer the parm player
	 */
	public void worldCoverage(PlayerModel parmPlayer) {
		double percentage = (parmPlayer.getOwnedCountries().size() * 100) / this.gameMapModel.getCountries().size();
		this.getConsoleText().append("						" + " Map coverage for " + parmPlayer.getNamePlayer()
				+ " is " + percentage + "% " + "\n");

	}

	/**
	 * Continent coverage.
	 *
	 * @param parmContinent the parm continent
	 */
	public void continentCoverage(ContinentsModel parmContinent) {
		this.getConsoleText().append("						" + "Continent " + parmContinent.getContinentName()
				+ "'s coverage distribution: \n");
		int countryCount = 0;
		double percentage;

		for (int j = 0; j < this.players.size(); j++) {
			for (int k = 0; k < this.players.get(j).getOwnedCountries().size(); k++) {
				if (this.players.get(j).getOwnedCountries().get(k).getcontinentName()
						.equals(parmContinent.getContinentName())) {
					countryCount = countryCount + 1;
				}

			}
			percentage = (countryCount * 100) / parmContinent.getCoveredCountries().size();
			this.getConsoleText().append("						" + this.players.get(j).getNamePlayer()
					+ " has covered " + percentage + "% of the continent " + parmContinent.getContinentName() + "\n");
			countryCount = 0;
		}

	}

	/**
	 * Continent covered.
	 *
	 * @param player the player
	 * @return the int
	 */
	public int continentCovered(PlayerModel player) {
		int countryCount = 0;
		double percentage = 0.0;
		int controlValue = 0;
		for (int j = 0; j < this.players.size(); j++) {
			if (this.players.get(j).getNamePlayer().equals(player.getNamePlayer())) {
				for (int i = 0; i < this.getGameMap().getContinents().size(); i++) {
					for (int k = 0; k < this.players.get(j).getOwnedCountries().size(); k++) {
						if (this.players.get(j).getOwnedCountries().get(k).getcontinentName()
								.equals(this.getGameMap().getContinents().get(i).getContinentName())) {
							countryCount = countryCount + 1;
						}
					}
					percentage = (countryCount * 100)
							/ this.getGameMap().getContinents().get(i).getCoveredCountries().size();
					if (percentage == 100.0) {
						controlValue = controlValue + this.getGameMap().getContinents().get(i).getValueControl();
					}
					percentage = 0;
					countryCount = 0;
				}
			}
		}
		return controlValue;
	}

	/**
	 * Move deck.
	 */
	public void moveDeck() {
		

		if (this.getCardToBeAssigned() == true) {
			CardModel card;

			for (int i = 0; i < this.getPlayers().size(); i++) {
				if (this.getPlayers().get(i).getNamePlayer()
						.equals(this.getGameMap().getPlayerTurn().getNamePlayer())) {
					card = this.getCard();
					ArrayList<CardModel> tempList = (ArrayList<CardModel>) this.getPlayers().get(i).getOwnedCards();
					tempList.add(card);
					this.getPlayers().get(i).setOwnedCards(tempList);
				}
			}
			this.setCardToBeAssigned(false);
		}
	}
}