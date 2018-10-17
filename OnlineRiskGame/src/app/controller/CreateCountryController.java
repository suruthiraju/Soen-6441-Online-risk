package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.view.CreateCountryView;

public class CreateCountryController implements ActionListener {
	private GameMapModel gameMapModel;
	private CreateCountryView createCountryView;
	private ContinentsModel newContinentModel;

	public CreateCountryController(GameMapModel gameMapModel) {
		this.gameMapModel = gameMapModel;
		this.createCountryView = new CreateCountryView(this.gameMapModel.getContinents());
		this.createCountryView.setVisible(true);
		this.gameMapModel.addObserver(this.createCountryView);
		this.createCountryView.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		CountryModel temp = new CountryModel();

		if (actionEvent.getSource().equals(this.createCountryView.addButton)) {
			if (this.createCountryView.countryValue.getText() != null
					&& this.createCountryView.countryValue.getText() != "") {

				if (sameCountryValidation()) {
					JOptionPane.showOptionDialog(null, "Please enter a different country", "Invalid",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
					return;
				} else {
					this.newContinentModel = (ContinentsModel) this.createCountryView.continentListCombobox
							.getSelectedItem();
					temp.setContinentName(this.newContinentModel.getContinentName());
					temp.setCountryName(this.createCountryView.countryValue.getText());
					this.gameMapModel.getCountries().add(temp);
					this.gameMapModel.setCountries(this.gameMapModel.getCountries());
				}

			} else {
				JOptionPane.showOptionDialog(null, "Please enter a valid input", "Invalid", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}
		} else if (actionEvent.getSource().equals(this.createCountryView.nextButton)) {
			if (emptyContinentValidation()) {
				JOptionPane.showOptionDialog(null, "Please enter at least one country for each continent",
						"Invalid", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
						new Object[] {}, null);
				return;
			} else {
				new ConnectCountryController(this.gameMapModel);
				this.createCountryView.dispose();
			}
		}
	}

	private boolean sameCountryValidation() {
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).getCountryName()
					.equals(this.createCountryView.countryValue.getText())) {
				return true;
			}
		}
		return false;
	}

	public boolean emptyContinentValidation() {
		List<ContinentsModel> listOfContinents = this.gameMapModel.getContinents();
		List<CountryModel> listOfCountrys = this.gameMapModel.getCountries();
		String continentName = " ";
		for (int i = 0; i < listOfContinents.size(); i++) {
			continentName = listOfContinents.get(i).getContinentName();
			int count = 0;
			for (int j = 0; j < listOfCountrys.size(); j++) {
				count++;
				if (continentName.equals(listOfCountrys.get(j).getcontinentName())) {
					count = 0;
					break;
				}
			}
			if (count == listOfCountrys.size()) {
				return true;
			}
		}
		return false;
	}
}
