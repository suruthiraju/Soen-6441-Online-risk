package app.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import app.view.*;
import app.model.*;

/**
 * The Class NewGameController 
 *
 * @author Suruthi Raju
 */

public class StartUpController implements ActionListener {

	private StartUpView theStartUpView;
	private ArrayList<ContinentsModel> listOfContinents;  
	private ArrayList<CountryModel> listOfCountrys;
	private int noOfPlayers;
	
    public StartUpController() {
    	theStartUpView = new StartUpView();
    	theStartUpView.setActionListener(this);
    	theStartUpView.setVisible(true);
    	playerValidation();
    }

	public StartUpController(int noOfPlayers, ArrayList<ContinentsModel> listOfContinents, ArrayList<CountryModel> listOfCountrys) {
		this.listOfContinents = listOfContinents;
		this.listOfCountrys = listOfCountrys;
		this.noOfPlayers = noOfPlayers;
		
	}
	
	public void playerValidation() {
		if (listOfCountrys.size() > noOfPlayers) {			
	    	System.out.println("no of players");
		}else {
			JOptionPane.showMessageDialog(theStartUpView, "Number of cuntry in the Map is less than Number of Players. Select map or player Again!","Map Loaded",JOptionPane.INFORMATION_MESSAGE);
			new NewGameController();
			 this.theStartUpView.dispose();
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {}
    
    
	
    
}
