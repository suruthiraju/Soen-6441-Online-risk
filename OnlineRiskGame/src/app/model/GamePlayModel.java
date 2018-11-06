package app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * "GamePlayerModel" class represents an object containing current map and
 * players.
 * 
 * @author GROUP-35
 */
public class GamePlayModel extends Observable  {

	private GameMapModel gameMapModel;
	private ArrayList<PlayerModel> players;
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
	/**
	 * Constructor
	 * 
	 * @param gameMap
	 * @param players
	 */
	public GamePlayModel(GameMapModel gameMap, ArrayList<PlayerModel> players) {
		this.gameMapModel = gameMap;
		this.players = players;
	}

	public GamePlayModel() {
		// TODO Auto-generated constructor stub
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

	public PlayerModel getPlayer(CountryModel parmCountry)
	{
		int i,j;
		for(i = 0; i< players.size(); i++)
		{
			for (j=0; j< players.get(i).getOwnedCountries().size();j++)
			{
				if(parmCountry.getCountryName().equals(players.get(i).getOwnedCountries().get(j).getCountryName()))
				{
					return players.get(i);
				}
				else
				{
					continue;
				}
			}
		}
		return null;
		
	}
	public void setSelectedArmiesToCountries(int selectedArmies, CountryModel countryName) {
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).equals(countryName)) {
				this.gameMapModel.getCountries().get(i).setArmies(this.gameMapModel.getCountries().get(i).getArmies() + selectedArmies);
				for (int j=0; j<this.getPlayers().size();j++) {
					if(this.getPlayers().get(j).getNamePlayer().equals(this.gameMapModel.getCountries().get(i).getRulerName())) {
						this.getPlayers().get(j).setmyTroop(this.getPlayers().get(j).getmyTroop() - selectedArmies);
					}
				}
			}
		}
		callObservers();
	}
	
	
	
	public int numberOfCountries(GamePlayModel gPP) {
		int numberOfCountries = 0;
		
		for (int i = 0; i < gPP.getPlayers().size(); i++) {
			
			if (gPP.getPlayers().get(i).getNamePlayer().equals(gPP.getGameMap().getPlayerTurn().getNamePlayer())) 
			{
				numberOfCountries = gPP.getPlayers().get(i).getOwnedCountries().size();
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
	
	public ArrayList<CountryModel> getDefendCountryList( CountryModel attackCountryName ) {
		ArrayList<CountryModel> linkedCountry;
		ArrayList<CountryModel> defenderCountryList = new ArrayList<CountryModel>();
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).equals(attackCountryName)) {
				linkedCountry = (ArrayList<CountryModel>) this.gameMapModel.getCountries().get(i).getLinkedCountries();
				for (int j=0; j<linkedCountry.size();j++) {
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
		for (int i=0; i<attackDice;i++) {
			attackDiceRoll[i] = getRandomBetweenRange(1, 6);
		}
		for (int i=0; i<defendDice;i++) {
			defendDiceRoll[i] = getRandomBetweenRange(1, 6);
		}
		Arrays.sort(attackDiceRoll, Collections.reverseOrder()); 
		Arrays.sort(defendDiceRoll, Collections.reverseOrder()); 
		System.out.println(Arrays.toString(attackDiceRoll));
		System.out.println(Arrays.toString(defendDiceRoll));
		for(int i=0; i<defendDice;i++) {
			if(attackDiceRoll[i] > defendDiceRoll[i]) {
				armiesDeduction(defendCountry, 1);
			}else {
				armiesDeduction(attackCountry, 1);
			}
		}
		if(countryOwned == true) {
			for(int i = 0; i< this.getPlayers().size(); i++) {
				if (this.getPlayers().get(i).getNamePlayer().equals(defendCountry.getRulerName())) {
					this.getPlayers().get(i).defend(defendCountry);
				}
				if (this.getPlayers().get(i).getNamePlayer().equals(attackCountry.getRulerName())) {
					this.getPlayers().get(i).attacked(defendCountry);
				}
			}
			for(int i = 0; i< this.gameMapModel.getCountries().size(); i++) {
				if (this.gameMapModel.getCountries().get(i).getCountryName().equals(defendCountry.getCountryName())) {
					this.gameMapModel.getCountries().get(i).setRulerName(attackCountry.getRulerName());
				}
			}
			
			this.setArmyToMoveText(true);
		}
		callObservers();
	}
	
	public CountryModel armiesDeduction(CountryModel countryForDeduction, int armiesToDeduct) {
		
		for(int i = 0; i< this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).getCountryName().equals(countryForDeduction.getCountryName())) {
				if (this.gameMapModel.getCountries().get(i).getArmies() == 1) {
					countryOwned = true;
					this.gameMapModel.getCountries().get(i).setArmies(this.gameMapModel.getCountries().get(i).getArmies()-armiesToDeduct);
				}else {
					this.gameMapModel.getCountries().get(i).setArmies(this.gameMapModel.getCountries().get(i).getArmies()-armiesToDeduct);
				}
			}
		}
		for(int i = 0; i< this.getPlayers().size(); i++) {
			if (this.getPlayers().get(i).getNamePlayer().equals(countryForDeduction.getRulerName())) {
					this.getPlayers().get(i).setmyTroop(this.getPlayers().get(i).getmyTroop() - armiesToDeduct);
			}
		}
		return countryForDeduction;
	}
	
	
	
	public void alloutStrike(CountryModel attackCountry, CountryModel defendCountry) {
		int attackTotalArmies = attackCountry.getArmies();
		int defendTotalArmies = defendCountry.getArmies();
		this.setArmyToMoveText(true);
		callObservers();
	}
	
	public void moveArmies(CountryModel attackCountry, CountryModel defendCountry, int noOfArmiesToBeMoved){
		for(int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).getCountryName().equals(attackCountry.getCountryName())) {
				this.gameMapModel.getCountries().get(i).setArmies(this.gameMapModel.getCountries().get(i).getArmies() - noOfArmiesToBeMoved );
			}
			if (this.gameMapModel.getCountries().get(i).getCountryName().equals(defendCountry.getCountryName())) {
				this.gameMapModel.getCountries().get(i).setArmies(this.gameMapModel.getCountries().get(i).getArmies() + noOfArmiesToBeMoved );
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
		if (armyToMoveFlag ==  true) {
		callObservers();
		}
	}

	public boolean getArmyToMoveText() {
		return this.armyToMoveFlag;
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
	 * Method used to notify state change whenever any change is reflected by
	 * CreateContinentController via CreateContinentView
	 */
	public void callObservers() {
		setChanged();
		notifyObservers(this);
	}
}
