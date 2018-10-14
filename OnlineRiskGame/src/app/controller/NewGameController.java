package app.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import app.view.*;
import app.model.*;
import app.utilities.ReadFile;

/**
 * The Class NewGameController 
 *
 * @author Suruthi Raju
 */

public class NewGameController implements ActionListener {

	private NewGameView theView;
	private ArrayList<ContinentsModel> listOfContinents;  
	private ArrayList<CountryModel> listOfCountrys;
    
    public NewGameController() {
    	this.theView = new NewGameView();
    	this.theView.setActionListener(this);
    	this.theView.setVisible(true);
        
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(theView.browseMapButton)) {		
			int value = theView.chooseMap.showOpenDialog(theView);
			
			if(value == JFileChooser.APPROVE_OPTION){
				File mapFile = theView.chooseMap.getSelectedFile();
				System.out.println(mapFile);
				ReadFile rf = new ReadFile();
				try {
					listOfContinents = rf.getMapContinentDetails(mapFile);
					listOfCountrys = rf.getMapCountryDetails(mapFile);
					JOptionPane.showMessageDialog(theView, "Map Loaded Successfully! Click Next to Play!","Map Loaded",JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//MapService.getInstance().loadMap(mapFile);				
			}
		}else if(arg0.getSource().equals(theView.nextButton)) {
			int noOfPlayers = (int) theView.numOfPlayers.getSelectedItem();
			new StartUpController(noOfPlayers, listOfContinents, listOfCountrys);
			 this.theView.dispose();			
		}else if(arg0.getSource().equals(theView.cancelButton)) {
			new WelcomeScreenController();
			 this.theView.dispose();
		}
		
	}
    
    
	
    
}
