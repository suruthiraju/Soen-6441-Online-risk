package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import app.model.CountryModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;
import app.view.AttackView;

/**
 * In Reinforcement Controller, the data flow into model object and updates the
 * view whenever data changes.
 * 
 * @author Suruthi Raju
 *
 */
public class AttackController implements ActionListener {

	private AttackView theAttackView;
	private ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
	private GamePlayModel gamePlayModel;

	/**
	 * Constructor initializes values and sets the screen too visible
	 * 
	 * @param gamePlayModel
	 */
	public AttackController(GamePlayModel gamePlayModel) {
		this.gamePlayModel = gamePlayModel;
		theAttackView = new AttackView(this.gamePlayModel);
		theAttackView.setActionListener(this);
		theAttackView.setVisible(true);
	}

	/**
	 * This method performs action, by Listening the action event set in view.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(this.theAttackView.nextButton)) {
			new FortificationController(this.gamePlayModel);
			this.theAttackView.dispose();
		}
	}
}