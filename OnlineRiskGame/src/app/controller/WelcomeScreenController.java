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
        
    			// Tell the View that when ever the calculate button
        		// is clicked to execute the actionPerformed method
        		// in the CalculateListener inner class
    
    	this.theView.createMapListener(new createMapButtonListener());
    	this.theView.editMapListener(new editMapButtonListener());
    	this.theView.playMapListener(new playMapButtonListener());
    	this.theView.exitListener(new exitButtonListener());
    	
    	
    }

    class createMapButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // create a new map
            try{
                //theView.setCalcSolution(theModel.getCalculationValue());
                theView.displayErrorMessage("You pressed \"Create Map\" button");
        		//System.out.println("Hey! I am here...!");
            }
            catch(NumberFormatException ex){
                //System.out.println(ex);
                //theView.displayErrorMessage("You Need to Enter 2 Integers");
            }
        }
    }

    class editMapButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // create a new map
            try{
                theView.displayErrorMessage("You pressed \"Edit Map\" button");
                //theView.setCalcSolution(theModel.getCalculationValue());
            }
            catch(NumberFormatException ex){
                //System.out.println(ex);
                //theView.displayErrorMessage("You Need to Enter 2 Integers");
            }
        }
    }

    class playMapButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // create a new map
            try{
            	NewGameView theView = new NewGameView();
            	theView.setVisible(true);
                //theView.displayErrorMessage("You pressed \"Default Map\" button");
                //theView.setCalcSolution(theModel.getCalculationValue());
            }
            catch(NumberFormatException ex){
                //System.out.println(ex);
                //theView.displayErrorMessage("You Need to Enter 2 Integers");
            }
        }
    }

    class exitButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // create a new map
            try{
                theView.displayErrorMessage("You pressed \"Exit\" button");
                //theView.setCalcSolution(theModel.getCalculationValue());
            }
            catch(NumberFormatException ex){
                //System.out.println(ex);
                //theView.displayErrorMessage("You Need to Enter 2 Integers");
            }
        }
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
		// TODO Auto-generated method stub
		
	}

	private void showEditGameWindow() {
		// TODO Auto-generated method stub
		
	}

	private void showCreateMapWindow() {
		
		new CreateMapController();
		 this.theView.dispose();
	}
}
