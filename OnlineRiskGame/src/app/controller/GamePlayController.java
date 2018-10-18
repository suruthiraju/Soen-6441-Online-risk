package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import app.view.*;
import app.model.*;

/**
 * The Class GamePlayController
 *
 * @author Suruthi Raju
 */

public class GamePlayController implements ActionListener {
	
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();

	public GamePlayController(GameMapModel gMM, ArrayList<PlayerModel> listOfPlayers) {
		this.listOfPlayers = listOfPlayers;
		gamePlay();		
	}
	
	public void gamePlay() {
		for (int i=0; i<listOfPlayers.size();i++) {
			new ReinforcementController();
			new FortificationController();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		
	}
	
}
