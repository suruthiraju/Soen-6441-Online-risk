package app.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import app.view.ReinforcementView;

/**
 * "PlayerModel" class represents an object of a player. The properties are:
 * Player name, player's troop number, the color in view screen (displaying)
 * representing the player,
 * 
 * @author GROUP-35
 *
 */
public class PlayerModel extends Observable {

	private String namePlayer;
	private int myTroop;
	private Color color;
	private int remainTroop;
	private List<CountryModel> ownedCountries  = new ArrayList<CountryModel>();
	private List<CardModel> ownedCards = new ArrayList<CardModel>();

	/**
	 * Constructor of PlayerModel
	 * 
	 * @param namePlayer
	 * @param myTroop
	 * @param color
	 */
	public PlayerModel(String namePlayer, int myTroop, Color color, int remainTroop, List ownedCountries, List ownedCards) {
		this.namePlayer = namePlayer;
		this.myTroop = myTroop;
		this.color = color;
		this.remainTroop = remainTroop;
		this.ownedCountries = ownedCountries;
		this.ownedCards = ownedCards;
	}

	/**
	 * Default constructor
	 */
	public PlayerModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the value Control.
	 */
	public int getmyTroop() {
		return myTroop;
	}

	/**
	 * Sets the value Control.
	 * 
	 * @param myTroop
	 */
	public void setmyTroop(int myTroop) {
		this.myTroop = myTroop;
	}

	/**
	 * @return the continent name.
	 */
	public String getNamePlayer() {
		return namePlayer;
	}

	/**
	 * Sets the continent name.
	 * 
	 * @param namePlayer
	 */
	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}

	/**
	 * @return the continent name.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the continent name.
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the .
	 */
	public int getremainTroop() {
		return remainTroop;
	}

	/**
	 * Sets the value Control.
	 * 
	 * @param remainTroop
	 */
	public void setremainTroop(int remainTroop) {
		this.remainTroop = remainTroop;
	}

	/**
	 * Calling Observer
	 */
	public void callObservers() {
		setChanged();
		notifyObservers(this);

	}
	/**
	 * @return the list of array.
	 */
	public List<CountryModel> getOwnedCountries() {
		return ownedCountries;
	}

	/**
	 * Sets the list of array.
	 * 
	 * @param ownedCountries
	 */
	public void setOwnedCountries(List<CountryModel> ownedCountries) {
		this.ownedCountries = ownedCountries;
	}
	/**
	 * @return the list of array.
	 */
	public List<CardModel> getOwnedCards() {
		return ownedCards;
	}

	/**
	 * Sets the list of array.
	 * 
	 * @param ownedCountries
	 */
	public void setOwnedCards(List<CardModel> ownedCards) {
		this.ownedCards = ownedCards;
	}
	
	public boolean attacked(CountryModel countryForAddition) {
		this.ownedCountries.add(countryForAddition);		
		return true;
	}
	public boolean defend(CountryModel countryForDeduction) {
		for(int i=0; i< this.ownedCountries.size(); i++) {
			if (this.ownedCountries.get(i).getCountryName().equals(countryForDeduction.getCountryName())) {
				this.ownedCountries.remove(i);
			}
		}
		return true;
	}
	public boolean addCard(CardModel toAddCard) {
		this.ownedCards.add(toAddCard);		
		return true;
	}
	public boolean removeCard(CardModel toRemoveCard) {
		for(int i=0; i< this.ownedCards.size(); i++) {
			if (this.ownedCards.get(i).getCardId() == toRemoveCard.getCardId()) {
				this.ownedCards.remove(i);
			}
		}
		return true;
	}
}
