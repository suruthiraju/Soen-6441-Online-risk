package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.view.TournamentDetailView;

public class TournamentDetailController implements ActionListener {

	/** The view. */
	private TournamentDetailView theTournamentDetailView;

	/**
	 * Constructor initializes values and sets the screen too visible.
	 */
	public TournamentDetailController() {
		this.theTournamentDetailView = new TournamentDetailView();
		this.theTournamentDetailView.setActionListener(this);
		this.theTournamentDetailView.setVisible(true);

	}

	/**
	 * This method performs action, by Listening the action event set in view.
	 *
	 * @param actionEvent the action event
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(theTournamentDetailView.saveAndPlayButton)) {
			this.theTournamentDetailView.dispose();
		}else if (actionEvent.getSource().equals(theTournamentDetailView.exitButton)) {
			this.theTournamentDetailView.dispose();
		}
	}

}
