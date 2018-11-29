package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
public class HumanPlayerController implements Strategy {

	/** The game play model. */
	private GamePlayModel gamePlayModel;

	/** The reinforcement view. */
	private ReinforcementView theReinforcementView;

	/** The fortification view. */
	private FortificationView theFortificationView;

	/** The attack view. */
	private AttackView theAttackView;

	/** The list of players. */
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();

	/** The no of players. */
	private int noOfPlayers;

	/** The val. */
	private Validation val = new Validation();

	/**
	 * Constructor initializes values and sets the screen too visible.
	 *
	 * @param gamePlayModel the game play model
	 */
	public HumanPlayerController(GamePlayModel gamePlayModel) {

		this.gamePlayModel = gamePlayModel;
		this.gamePlayModel.getConsoleText()
				.append("Initiating reinforcement for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
	}

	/**
	 * This method is called in reinforcement phase.
	 * 
	 */
	public void reinforcement() {
		System.out.println("Human - reinforcement");
		this.gamePlayModel.getConsoleText().setLength(0);
		this.gamePlayModel.callObservers();
		this.gamePlayModel.getConsoleText()
				.append("Initiating Reinforcement for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

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
	}

	/**
	 * This method is called in fortification phase.
	 */
	public void fortification() {
		System.out.println("Human - fortification");
		this.gamePlayModel.getConsoleText().setLength(0);
		this.gamePlayModel.getConsoleText()
				.append("Initiating Fortification for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
		
	}

	/**
	 * This method is called in attack phase.
	 */
	public void attack() {
		System.out.println("Human - attack");
		this.gamePlayModel.getConsoleText().setLength(0);
		this.gamePlayModel.getConsoleText()
				.append("Initiating " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer() + "'s attack");			
	}
}
