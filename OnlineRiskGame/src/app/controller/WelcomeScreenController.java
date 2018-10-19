package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.model.WelcomeScreenModel;
import app.view.WelcomeScreenView;

//The Controller coordinates interactions
//between the View and Model

/**
 * "WelcomeScreenController" is the main class. 
 * It represents a welcome window containing main buttons to edit/create
 * map file and start playing game
 * @author GROUP-35
 */
public class WelcomeScreenController implements ActionListener {

	public static void main(String[] args) {
		WelcomeScreenController wsc = new WelcomeScreenController();
	}

	private WelcomeScreenView theView;
	private WelcomeScreenModel theModel;

	public WelcomeScreenController() {
		this.theView = new WelcomeScreenView();

		this.theView.setActionListener(this);
		this.theView.setVisible(true);

		this.theModel = new WelcomeScreenModel();

	}

/**
 * Sets actions to clicked buttons	
 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(theView.createMapButton)) {
			// open new game window
			this.showCreateMapWindow();
		} else if (actionEvent.getSource().equals(theView.editMapButton)) {
			// open load game window
			this.showEditGameWindow();
		} else if (actionEvent.getSource().equals(theView.playMapButton)) {
			// open create game window
			this.showPlayGameWindow();
		} else if (actionEvent.getSource().equals(theView.exitButton)) {
			// exit game
			this.exitGame();
		}
	}

	
	private void exitGame() {
		this.theView.dispose();
	}

	private void showPlayGameWindow() {
		new NewGameController();
		this.theView.dispose();
	}

	private void showEditGameWindow() {
		//new EditMapController();
		new EditContinentController();
		this.theView.dispose();
	}

	private void showCreateMapWindow() {
		new CreateContinentController();
		this.theView.dispose();
	}
}
