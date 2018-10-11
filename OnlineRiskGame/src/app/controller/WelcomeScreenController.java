package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.model.WelcomeScreenModel;
import app.view.WelcomeScreenView;

//The Controller coordinates interactions
//between the View and Model

public class WelcomeScreenController {

    
    private WelcomeScreenView theView;
    private WelcomeScreenModel theModel;
    public WelcomeScreenController(WelcomeScreenView theView, WelcomeScreenModel theModel) {
    	this.theView = theView;
        this.theModel = theModel;
        
    			// Tell the View that when ever the calculate button
        		// is clicked to execute the actionPerformed method
        		// in the CalculateListener inner class
    
    	this.theView.createMapListener(new createMapButtonListener());
    	this.theView.editMapListener(new editMapButtonListener());
    	this.theView.defaultMapListener(new defaultMapButtonListener());
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

    class defaultMapButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // create a new map
            try{
                theView.displayErrorMessage("You pressed \"Default Map\" button");
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
}
