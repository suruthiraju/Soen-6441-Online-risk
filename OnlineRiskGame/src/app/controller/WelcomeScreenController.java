package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.model.WelcomeScreenModel;
import app.view.WelcomeScreenView;
import app.view.NewGameView;

//The Controller coordinates interactions
//between the View and Model

public class WelcomeScreenController implements ActionListener {

    
    private WelcomeScreenView theView;
    private WelcomeScreenModel theModel;
    public WelcomeScreenController() {
    	this.theView = new WelcomeScreenView();
    	
    	this.theView.setActionListener(this);
    	this.theView.setVisible(true);    	
    	
        this.theModel = new WelcomeScreenModel(); 	
    	
    }

    public static void main(String[] args) {
    	WelcomeScreenController wsc = new WelcomeScreenController();
    	
    }

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
		// TODO Auto-generated method stub
		
	}

	private void showPlayGameWindow() {
		new NewGameController();
		 this.theView.dispose();
		
	}

	private void showEditGameWindow() {
		// TODO Auto-generated method stub
		
	}

	private void showCreateMapWindow() {
		
		new CreateMapController();
		 this.theView.dispose();
	}
}
