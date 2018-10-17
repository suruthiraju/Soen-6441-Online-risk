package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import app.model.CountryModel;
import app.model.GameMapModel;
import app.view.ConnectCountryView;
import app.view.EditContinentView;

public class ConnectCountryController implements ActionListener {

	private GameMapModel gameMapModel;
	private ConnectCountryView connectCountryView;
	private List<CountryModel> countryList;
	private List<CountryModel> countryListlinks;
	private CountryModel newCountryModel;

	public ConnectCountryController(GameMapModel mapModel) {

		this.gameMapModel = mapModel;
		this.countryList = this.gameMapModel.getCountries();
		this.connectCountryView = new ConnectCountryView(this.countryList);
		mapModel.addObserver(this.connectCountryView);
		gameMapModel.callObservers();
		this.countryListlinks = new ArrayList<CountryModel>();
		this.connectCountryView.setActionListener(this);
		this.connectCountryView.setVisible(true);
		

	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(this.connectCountryView.addButton)
				&& connectCountryView.countryParentListLeft.getSelectedValue()
						.equals(connectCountryView.countryParentListRight.getSelectedValue())) {
			System.out.println("Cannot create a self link");
		} else {
			for (int i = 0; i < this.countryList.size(); i++) {
				if (this.countryList.get(i).equals(this.connectCountryView.countryParentListLeft.getSelectedValue())) {
					this.newCountryModel = this.countryList.get(i);
					List<CountryModel> temp = this.newCountryModel.getLinkedCountries();

					if (temp == null) {
						temp = new ArrayList<CountryModel>();
					}
					temp.add((CountryModel) this.connectCountryView.countryParentListRight.getSelectedValue());
					this.newCountryModel.setLinkedCountries(temp);
					this.countryListlinks.add(this.newCountryModel);
				}

			}
			this.newCountryModel = new CountryModel();
			for (int i = 0; i < this.countryList.size(); i++) {
				if (this.countryList.get(i).equals(this.connectCountryView.countryParentListRight.getSelectedValue())) {
					this.newCountryModel = this.countryList.get(i);
					List<CountryModel> temp = this.newCountryModel.getLinkedCountries();

					if (temp == null) {
						temp = new ArrayList<CountryModel>();
					}

					temp.add((CountryModel) this.connectCountryView.countryParentListLeft.getSelectedValue());
					this.newCountryModel.setLinkedCountries(temp);
					this.countryListlinks.add(this.newCountryModel);
				}

			}
			System.out.println(this.countryListlinks);
		}

	}

}
