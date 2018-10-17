package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import app.model.*;
import app.view.*;

public class ReinforcementController implements ActionListener {

    private ReinforcementView theReinforcementView;
	private ArrayList<ContinentsModel> listOfContinents = new ArrayList<ContinentsModel>();
	private ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
	private GameMapModel GameMap = new GameMapModel();
	private int noOfPlayers;

	public ReinforcementController() {
		theReinforcementView = new ReinforcementView();
		theReinforcementView.setActionListener(this);
		theReinforcementView.setVisible(true);
		//GamePlayerModel GamePlay = new GamePlayerModel(GameMap, listOfPlayers);
	}
	
	public void validate() {
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}