package app.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.PlayerModel;
import app.view.NewGameView;

/**
 * The Class NewGameController 
 *
 * @author Suruthi Raju
 */

public class NewGameController implements ActionListener {

	private NewGameView theView;
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
	private GameMapModel gmM = new GameMapModel();
	private int noOfPlayers;
    
    public NewGameController() {
    	this.theView = new NewGameView();
    	this.theView.setActionListener(this);
    	this.theView.setVisible(true);
        
    }

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(theView.browseMapButton)) {		
			int value = theView.chooseMap.showOpenDialog(theView);			
			if(value == JFileChooser.APPROVE_OPTION){				
				try {
					File mapFile = theView.chooseMap.getSelectedFile();
					gmM = new GameMapModel(mapFile);
					JOptionPane.showMessageDialog(theView, "File Loaded Successfully! Click Next to Play!","Map Loaded",JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}else if(actionEvent.getSource().equals(theView.nextButton)) {
			noOfPlayers = (int) theView.numOfPlayers.getSelectedItem();
			playerValidation();			
						
		}else if(actionEvent.getSource().equals(theView.cancelButton)) {
			new WelcomeScreenController();
			 this.theView.dispose();
		}
		
	}
    
	public void playerValidation() {
		if ( gmM.getCountries().size() > noOfPlayers) {
			System.out.println("no of players");
			String PlayerName = "";
			for (int i=0; i<noOfPlayers; i++) {
				PlayerName = "Player"+ (i+1);
				PlayerModel pm = new PlayerModel(PlayerName, 0, "",0);
				listOfPlayers.add(pm);				
			}
			new StartUpController(listOfPlayers, gmM);
			this.theView.dispose();
		} else {
			JOptionPane.showMessageDialog(theView,
					"Number of cuntry in the Map is less than Number of Players. Select map or player Again!",
					"Map Loaded", JOptionPane.INFORMATION_MESSAGE);
		}
	}   
	
    
}
