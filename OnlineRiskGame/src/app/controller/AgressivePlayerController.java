package app.controller;

import java.util.ArrayList;
import java.util.List;

import app.helper.Strategy;
import app.model.CardModel;
import app.model.CountryModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;
import app.utilities.Validation;
import app.view.AttackView;
import app.view.FortificationView;
import app.view.ReinforcementView;
/**
 * In PlayerController, the data flow into model object and updates the view
 * whenever data changes.
 * 
 * @author Hamid
 * @version 1.0.0
 *
 */
public class AgressivePlayerController implements Strategy {

	/** The game play model. */
	private GamePlayModel gamePlayModel;

	/** The attacker country. */
	private CountryModel attackerCountry;

	/** The defender country. */
	private CountryModel defenderCountry;

	/** The val. */
	private Validation val = new Validation();

	/**
	 * Constructor initializes values and sets the screen too visible.
	 *
	 * @param gamePlayModel the game play model
	 */
	public AgressivePlayerController(GamePlayModel gamePlayModel) {
		
		this.gamePlayModel = gamePlayModel;
	}

	/**
	 * This method is called in reinforcement phase.
	 * 
	 */
	public void reinforcement() {
		System.out.println("Agressive - reinforcement");
		ArrayList<CardModel> deck = new ArrayList<CardModel>();
		int troops = 0;
		ArrayList<CountryModel> controlledCountries = new ArrayList<CountryModel>();
		ArrayList<CountryModel> linkedCountries = new ArrayList<CountryModel>();
		this.gamePlayModel.getConsoleText().setLength(0);
		this.gamePlayModel.getConsoleText()
				.append("Initiating Reinforcement for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
		CountryModel strongestCountry = new CountryModel();
		this.gamePlayModel.getGameMap().getPlayerTurn().setremainTroop(this.gamePlayModel.numberOfCountries()
				+ this.gamePlayModel.continentCovered(gamePlayModel.getGameMap().getPlayerTurn()));
		if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().size() > 0) {
			this.gamePlayModel.getConsoleText().append("\n Reinforcement View - Please find the list of Cards: \n");
			for (int i = 0; i < gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().size(); i++) {
				this.gamePlayModel.getConsoleText()
						.append(gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().get(i).getCardId() + "\n ");
			}
			this.gamePlayModel.getGameMap().getPlayerTurn().setShowReinforcementCard(true);
		}
		for (int i = 0; i < gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().size(); i++) {
			troops = troops + gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().get(i).getCardValue();
			gamePlayModel.getCards().add(gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().get(i));
			gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().remove(i);

		}

		controlledCountries.addAll(gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries());
		controlledCountries = gamePlayModel.sortCountry(controlledCountries);

		for (int i = controlledCountries.size() - 1; i > 0; i--) {
			strongestCountry = controlledCountries.get(i);
			linkedCountries = gamePlayModel
					.sortCountry((ArrayList<CountryModel>) strongestCountry.getLinkedCountries());

			for (int j = linkedCountries.size() - 1; j > 0; j--) {
				for (int k = 0; k < gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().size(); k++) {
					if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(k).getCountryName()
							.equals(linkedCountries.get(j).getCountryName())) {
						continue;
					} else {
						attackerCountry = strongestCountry;
						defenderCountry = linkedCountries.get(j);
						break;
					}
				}

			}

		}
		gamePlayModel.getGameMap().getPlayerTurn()
				.setmyTroop(gamePlayModel.getGameMap().getPlayerTurn().getmyTroop() + troops);
		attackerCountry
				.setArmies(gamePlayModel.getGameMap().getPlayerTurn().getmyTroop() + attackerCountry.getArmies());
		System.out.println(
				"Attacking from " + attackerCountry.getCountryName() + " to " + defenderCountry.getCountryName());

	}

	/**
	 * This method is called in fortification phase.
	 */
	public void fortification() {
		System.out.println("Agressive - fortification");
		int i = 0;
		int j = 0;
		boolean flag = false;
		List<CountryModel> linkedCountries = new ArrayList<CountryModel>();

		ArrayList<CountryModel> controlledCountries = (ArrayList<CountryModel>) gamePlayModel.getGameMap()
				.getPlayerTurn().getOwnedCountries();
		controlledCountries = gamePlayModel.sortCountry(controlledCountries);
		for (i = controlledCountries.size() - 1; i >= 0; i--) {
			linkedCountries = controlledCountries.get(i).getLinkedCountries();
			for (j = 0; j < linkedCountries.size(); j++) {
				for (int k = 0; k < controlledCountries.size(); k++) {

					if ((linkedCountries.get(j).getArmies() > 1) && (linkedCountries.get(j).getCountryName()
							.equals(controlledCountries.get(k).getCountryName()))) {
						controlledCountries.get(i).setArmies(
								controlledCountries.get(i).getArmies() + linkedCountries.get(j).getArmies() - 1);
						linkedCountries.get(j).setArmies(1);
						flag = true;
					}
					if (flag == true)
						break;

				}
				if (flag == true)
					break;

			}
			if (flag == true)
				break;
		}
		gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().add(gamePlayModel.getCard());

		System.out.println("Fortifiction done");

	}

	/**
	 * This method is called in attack phase.
	 */
	public void attack() {
		System.out.println("Agressive - attack");
		this.gamePlayModel.getConsoleText().setLength(0);
		this.gamePlayModel.getConsoleText()
				.append("Initiating " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer() + "'s attack");
		this.gamePlayModel.setArmyToMoveText(false);
		this.gamePlayModel.setCardToBeAssigned(false);
		gamePlayModel.alloutStrike(attackerCountry, defenderCountry);
		System.out.println("Before moving the attacker has " + attackerCountry.getArmies());
		System.out.println("Before moving The defender has " + defenderCountry.getArmies());
		if (attackerCountry.getArmies() > 1) {
			int low = 1;
			int high = attackerCountry.getArmies() - 1;
			int movingArmies = this.gamePlayModel.getRandomBetweenRange(low, high);
			gamePlayModel.moveArmies(attackerCountry, defenderCountry, movingArmies);
		}
		System.out.println("the attacker has " + attackerCountry.getArmies());
		System.out.println("The defender has " + defenderCountry.getArmies());

	}
}
