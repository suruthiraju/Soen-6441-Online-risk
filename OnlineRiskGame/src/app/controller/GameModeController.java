package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.view.GameModeView;

public class GameModeController implements ActionListener {

	/** The view. */
	private GameModeView theGameModeView;

	/**
	 * Constructor initializes values and sets the screen too visible.
	 */
	public GameModeController() {
		this.theGameModeView = new GameModeView();
		this.theGameModeView.setActionListener(this);
		this.theGameModeView.setVisible(true);

	}

	/**
	 * This method performs action, by Listening the action event set in view.
	 *
	 * @param actionEvent the action event
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(theGameModeView.singleMode)) {
			new NewGameController();
			this.theGameModeView.dispose();
		} else if (actionEvent.getSource().equals(theGameModeView.tournamentMode)) {
			new TournamentDetailController();
			this.theGameModeView.dispose();
		}
	}

}
