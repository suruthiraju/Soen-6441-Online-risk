package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import app.model.*;
import app.view.*;

public class FortificationController implements ActionListener {

    private FortificationView theFortificationView;
	private GameMapModel gameMapModel = null ;

	public FortificationController(GameMapModel gameMapModel) {
		this.gameMapModel = gameMapModel;
		theFortificationView = new FortificationView(this.gameMapModel);
		theFortificationView.setActionListener(this);
		theFortificationView.setVisible(true);
		//GamePlayerModel GamePlay = new GamePlayerModel(GameMap, listOfPlayers);
	}
	
	public void movingArmies(int armies, String fromCountryName, String toCountryName) {
		int previousArmies = 0;
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {			
			if (fromCountryName.equals(this.gameMapModel.getCountries().get(i).getCountryName())) {
				previousArmies = this.gameMapModel.getCountries().get(i).getArmies();
				this.gameMapModel.getCountries().get(i).setArmies( previousArmies - armies);
			}
			if (toCountryName.equals(this.gameMapModel.getCountries().get(i).getCountryName())) {
				previousArmies = this.gameMapModel.getCountries().get(i).getArmies();
				this.gameMapModel.getCountries().get(i).setArmies( previousArmies + armies);
			}
		}
	}	
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getSource().equals(this.theFortificationView.moveButton)) {
			int index = this.gameMapModel.getPlayerIndex();
			index++;
			if(this.gameMapModel.getListOfPlayers().size()>index) {
				this.gameMapModel.setPlayerIndex(index);
				new GamePlayController(this.gameMapModel, this.gameMapModel.getListOfPlayers());
				this.theFortificationView.dispose();
			}
			else {
				this.theFortificationView.dispose();
			}
		}
		
	}
}