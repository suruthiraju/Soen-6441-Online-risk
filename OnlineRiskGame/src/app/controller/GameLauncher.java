package app.controller;

import app.model.WelcomeScreenModel;
import app.view.WelcomeScreenView;

public class GameLauncher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		WelcomeScreenView theView = new WelcomeScreenView();
		WelcomeScreenModel theModel = new WelcomeScreenModel();
		WelcomeScreenController theController = new WelcomeScreenController(theView,theModel);
		
		theView.setVisible(true);


	}

}
