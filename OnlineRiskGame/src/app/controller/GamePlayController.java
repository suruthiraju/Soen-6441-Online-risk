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
	public GameMapModel gameMapModel = null;
	public GamePlayController(GameMapModel gameMapModel, ArrayList<PlayerModel> listOfPlayers) {
		this.gameMapModel = gameMapModel;
		this.listOfPlayers = listOfPlayers;
		gamePlay();		
	}
	
	public void gamePlay() {
		
			this.gameMapModel.setPlayerTurn(this.listOfPlayers.get(this.gameMapModel.getPlayerIndex()));
			new ReinforcementController(this.gameMapModel);
			
		
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		
	}
	
}
