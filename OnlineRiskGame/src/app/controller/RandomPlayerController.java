package app.controller;

import java.util.ArrayList;

import app.helper.Strategy;
import app.model.CardModel;
import app.model.CountryModel;
import app.model.GamePlayModel;
import app.utilities.Validation;

/**
 * In PlayerController, the data flow into model object and updates the view
 * whenever data changes.
 * 
 * @author Hamid
 * @version 1.0.0
 *
 */
public class RandomPlayerController implements Strategy {

	/** The game play model. */
	private GamePlayModel gamePlayModel;

	/** The val. */
	private Validation val = new Validation();

	private int index1, index2;

	/**
	 * Constructor initializes values and sets the screen too visible.
	 *
	 * @param gamePlayModel the game play model
	 */
	public RandomPlayerController(GamePlayModel gamePlayModel) {

		this.gamePlayModel = gamePlayModel;
		reinforcement();
	}

	/**
	 * This method is called in reinforcement phase.
	 * 
	 */
	public void reinforcement() {
		System.out.println("Random - reinforcement");

		this.gamePlayModel.getGameMap().getPlayerTurn().setremainTroop(this.gamePlayModel.numberOfCountries()
				+ this.gamePlayModel.continentCovered(gamePlayModel.getGameMap().getPlayerTurn()));
		if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().size() == 5) {
			this.gamePlayModel.getGameMap().getPlayerTurn()
					.setremainTroop(5 + this.gamePlayModel.getGameMap().getPlayerTurn().getremainTroop());
			for (int j = 0; j < gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().size(); j++) {
				CardModel card = new CardModel();
				int cardID = this.gamePlayModel.getCards().get(j).getCardId();
				int cardValue = this.gamePlayModel.getCards().get(j).getCardValue();
				card.setCardId(cardID);
				card.setCardValue(cardValue);
				for (int i = 0; i < this.gamePlayModel.getPlayers().size(); i++) {
					if (gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer()
							.equals(gamePlayModel.getPlayers().get(i).getNamePlayer())) {
						this.gamePlayModel.getPlayers().get(i).removeCard(card);
					}
				}
				this.gamePlayModel.getCards().add(card);
			}
		}

		int selectedArmies = this.gamePlayModel.getGameMap().getPlayerTurn().getremainTroop();
		int index = getRandomBetweenRange(1,
				this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().size());

		this.gamePlayModel.setSelectedArmiesToCountries(selectedArmies,
				this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index - 1));

		attack();
	}

	/**
	 * This method is called in fortification phase.
	 */
	public void fortification() {
		System.out.println("Random - fortification");

		index1 = getRandomBetweenRange(1, this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().size());
		index1 = index1 - 1;

		index2 = indexRandomvalues();
		index2 = index2 - 1;

		int armies = this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index1).getArmies() - 1;
		if (val.checkIfValidMove(this.gamePlayModel.getGameMap(),
				this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index1),
				this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index2))) {
			this.gamePlayModel.getGameMap().setMovingArmies(armies,
					this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index1),
					this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index2));
		}

		this.gamePlayModel.moveDeck();

	}

	/**
	 * This method is called in attack phase.
	 */
	public void attack() {
		System.out.println("Random - attack");
		index1 = getRandomBetweenRange(1, this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().size());
		index1 = index1 - 1;

		CountryModel attackCountry = this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index1);
		CountryModel defendCountry = new CountryModel();
		ArrayList<CountryModel> linkedCountry = new ArrayList<CountryModel>();
		for (int i = 0; i < this.gamePlayModel.getGameMap().getCountries().size(); i++) {
			if (attackCountry.getCountryName()
					.equals(this.gamePlayModel.getGameMap().getCountries().get(i).getCountryName())) {
				linkedCountry = (ArrayList<CountryModel>) this.gamePlayModel.getGameMap().getCountries().get(i)
						.getLinkedCountries();
			}
		}
		if (linkedCountry != null) {
			for (int i = 0; i < linkedCountry.size(); i++) {
				if (!this.gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer()
						.equals(linkedCountry.get(i).getCountryName())) {
					defendCountry = linkedCountry.get(i);
				}
			}
		}

		this.gamePlayModel.setDefeatedCountry(defendCountry);
		this.gamePlayModel.alloutStrike(attackCountry, defendCountry);
		int noOfArmiesToBeMoved = 0;
		for (int i = 0; i < this.gamePlayModel.getGameMap().getCountries().size(); i++) {
			if (attackCountry.getCountryName()
					.equals(this.gamePlayModel.getGameMap().getCountries().get(i).getCountryName())) {
				noOfArmiesToBeMoved = this.gamePlayModel.getGameMap().getCountries().get(i).getArmies() - 1;
			}
		}
		if (this.gamePlayModel.getArmyToMoveText()) {
			this.gamePlayModel.moveArmies(attackCountry, defendCountry, noOfArmiesToBeMoved);
		}
		fortification();
	}

	public int indexRandomvalues() {
		boolean flag = false;
		index2 = getRandomBetweenRange(1, this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().size());

		if (index1 == index2) {
			flag = true;
		}
		while (flag == true) {
			index2 = getRandomBetweenRange(1,
					this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().size());
			if (index1 != index2) {
				flag = false;
			}
		}
		return index2;
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
}
