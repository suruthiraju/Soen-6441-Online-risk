package app.controller;

import java.util.ArrayList;

import app.helper.Strategy;
import app.model.CountryModel;
import app.model.GamePlayModel;
import app.utilities.Validation;

/**
 * In BenevolentPlayerController, the data flow into model object and updates
 * the view whenever data changes.
 * 
 * @author Hamid
 * @version 1.0.0
 *
 */
public class BenevolentPlayerController implements Strategy {

	/** The game play model. */
	private GamePlayModel gamePlayModel;

	/** The val. */
	private Validation val = new Validation();

	/**
	 * Constructor initializes values and sets the screen too visible.
	 *
	 * @param gamePlayModel the game play model
	 */
	public BenevolentPlayerController(GamePlayModel gamePlayModel) {

		this.gamePlayModel = gamePlayModel;
	}

	/**
	 * This method is called in reinforcement phase.
	 * 
	 */
	public void reinforcement() {
		System.out.println("Benevolent - reinforcement");

		this.gamePlayModel.getGameMap().getPlayerTurn().setremainTroop(this.gamePlayModel.numberOfCountries()
				+ this.gamePlayModel.continentCovered(gamePlayModel.getGameMap().getPlayerTurn()));

		// select Weakest Country
		ArrayList<CountryModel> listofcountry = this.gamePlayModel.selectWeakestCountry(
				(ArrayList<CountryModel>) gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries());

		if (listofcountry.size() > 3) {
			int selectedArmies = Math.round(this.gamePlayModel.getGameMap().getPlayerTurn().getremainTroop() / 3);
			int index;
			for (int i = 0; i < 3; i++) {
				index = getRandomBetweenRange(1, listofcountry.size());
				this.gamePlayModel.setSelectedArmiesToCountries(selectedArmies, listofcountry.get(index - 1));
			}
		} else {
			int selectedArmies = Math
					.round(this.gamePlayModel.getGameMap().getPlayerTurn().getremainTroop() / listofcountry.size());
			for (int i = 0; i < listofcountry.size(); i++) {
				this.gamePlayModel.setSelectedArmiesToCountries(selectedArmies, listofcountry.get(i));
			}
		}
	}

	/**
	 * This method is called in fortification phase.
	 */
	public void fortification() {
		System.out.println("Benevolent - fortification");
		ArrayList<CountryModel> listofcountry = this.gamePlayModel
				.sortCountry((ArrayList<CountryModel>) gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries());

		// BFS
		int i = 0;
		int j = listofcountry.size() - 1;
		boolean flag = false;
		int armies;
		while (flag == false) {
			if (val.checkIfValidMove(this.gamePlayModel.getGameMap(), listofcountry.get(i), listofcountry.get(j))) {
				flag = true;
				armies = listofcountry.get(j).getArmies() - 1;
				System.out.println("listofcountry " + i + " " + j);
				this.gamePlayModel.getGameMap().setMovingArmies(armies, listofcountry.get(j), listofcountry.get(i));
			}
			i++;
			j--;
			if (i > listofcountry.size() / 2) {
				flag = true;
			}
		}
	}

	/**
	 * This method is called in attack phase.
	 */
	public void attack() {
		System.out.println("Benevolent - attack");
		System.out.println("Benevolent passes his attack");
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
