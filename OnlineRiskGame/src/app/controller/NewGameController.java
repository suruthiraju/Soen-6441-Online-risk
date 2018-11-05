package app.controller;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;
import app.view.NewGameView;

/**
 * In NewGameController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author Suruthi Raju
 * @version 1.0.0
 * 
 */

public class NewGameController implements ActionListener {

	private NewGameView theView;
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
	private GameMapModel gameMapModel = new GameMapModel();
	private GamePlayModel gamePlayModel = new GamePlayModel();
	private int noOfPlayers;
	private String PlayerName = "";
	private List<CountryModel> listOfCountry = null;
    
    /**
     * Constructor initializes values and sets the screen too visible
     */
    public NewGameController() {
    	this.theView = new NewGameView();
    	this.theView.setActionListener(this);
    	this.theView.setVisible(true);
        
    }
    
    /**
	 * This method performs action, by Listening the action event set in view.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(theView.browseMapButton)) {		
			int value = theView.chooseMap.showOpenDialog(theView);			
			if(value == JFileChooser.APPROVE_OPTION){				
				try {
					File mapFile = theView.chooseMap.getSelectedFile();
					gameMapModel = new GameMapModel(mapFile);
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
    
	/**
	 *  Check for the player validation
	 */
	public void playerValidation() {
		if ( gameMapModel.getCountries().size() > noOfPlayers) {
			System.out.println("no of players" + noOfPlayers);
			for (int i=0; i<noOfPlayers; i++) {
				if (i == 0) {
					PlayerName = theView.PlayerName1.getText();
				}else if(i == 1) {
					PlayerName = theView.PlayerName2.getText();
				}else if(i == 2) {
					PlayerName = theView.PlayerName3.getText();
				}else if(i == 3) {
					PlayerName = theView.PlayerName4.getText();
				}else if(i == 4){
					PlayerName = theView.PlayerName5.getText();
				}
				System.out.println("PlayerName " + PlayerName);
				if (PlayerName ==  null || "".equals(PlayerName.trim())) {
					PlayerName = "Player "+(i+1);
				}
				PlayerModel pm = new PlayerModel(PlayerName, 0, Color.WHITE,0,listOfCountry);
				listOfPlayers.add(pm);		
			}
			gamePlayModel.setGameMap(gameMapModel);
			gamePlayModel.setPlayers(listOfPlayers);
			new StartUpController(gamePlayModel);
			this.theView.dispose();
		} else {
			JOptionPane.showMessageDialog(theView,
					"Number of cuntry in the Map is less than Number of Players. Select map or player Again!",
					"Map Loaded", JOptionPane.INFORMATION_MESSAGE);
		}
	}   
	
    
}
