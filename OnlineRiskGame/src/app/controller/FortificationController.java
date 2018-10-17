package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import app.model.*;
import app.view.*;

public class FortificationController implements ActionListener {

    private FortificationView theFortificationView;
	private ArrayList<ContinentsModel> listOfContinents = new ArrayList<ContinentsModel>();
	private ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
	private GameMapModel GameMap = new GameMapModel();
	private int noOfPlayers;

	public FortificationController() {
		theFortificationView = new FortificationView();
		theFortificationView.setActionListener(this);
		theFortificationView.setVisible(true);
		//GamePlayerModel GamePlay = new GamePlayerModel(GameMap, listOfPlayers);
	}
	
	public void movingArmies(int armies, String fromCountryName, String toCountryName) {
		int previousArmies = 0;
		for (int i = 0; i < listOfCountrys.size(); i++) {			
			if (fromCountryName.equals(listOfCountrys.get(i).getCountryName())) {
				previousArmies = listOfCountrys.get(i).getArmies();
				listOfCountrys.get(i).setArmies( previousArmies - armies);
			}
			if (toCountryName.equals(listOfCountrys.get(i).getCountryName())) {
				previousArmies = listOfCountrys.get(i).getArmies();
				listOfCountrys.get(i).setArmies( previousArmies + armies);
			}
		}
	}	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}