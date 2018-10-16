package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.view.ConnectCountryView;
import app.view.CreateContinentView;

public class ConnectCountryController implements ActionListener{

	private GameMapModel gameMapModel;
	private ConnectCountryView connectCountryView;
	private List<CountryModel> CountryList;
	private CountryModel newCountryModel;
	
	public ConnectCountryController(GameMapModel mapModel) {
		
		this.gameMapModel = mapModel;
		this.CountryList = this.gameMapModel.getCountries();
		this.connectCountryView = new ConnectCountryView(this.CountryList);
		this.connectCountryView.setActionListener(this);
		this.connectCountryView.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
