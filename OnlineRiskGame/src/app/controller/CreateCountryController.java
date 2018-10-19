package app.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.view.CreateCountryView;

/**
 * In CreateCountryController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author Rohit
 * @version 1.0.0
 * 
 */

public class CreateCountryController implements ActionListener {
	private GameMapModel gameMapModel;
	private CreateCountryView createCountryView;
	private ContinentsModel newContinentModel;
	static int count = 0;
	private HashMap<String, ArrayList<Point>> mapPointList;
	private HashMap<String, Color> colorMapList;
	private  HashMap<String, Integer> indexMap;

	/**
	 * Constructor initializes values and sets the screen too visible
	 * @param gameMapModel
	 * @param mapPointList
	 * @param colorMapList
	 * @param indexMap
	 */
	public CreateCountryController(GameMapModel gameMapModel, HashMap<String, ArrayList<Point>> mapPointList,
			HashMap<String, Color> colorMapList, HashMap<String, Integer> indexMap) {
		this.gameMapModel = gameMapModel;
		this.mapPointList = mapPointList;
		this.colorMapList = colorMapList;
		this.indexMap = indexMap;
		this.createCountryView = new CreateCountryView(this.gameMapModel.getContinents());
		this.createCountryView.setVisible(true);
		this.gameMapModel.addObserver(this.createCountryView);
		this.createCountryView.setActionListener(this);
	}
	
	/**
	 * This method performs action, by Listening the action event set in view.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		CountryModel temp = new CountryModel();

		if (actionEvent.getSource().equals(this.createCountryView.addButton)) {
			if (this.createCountryView.countryValue.getText().equals(null)
					&& "".equals(this.createCountryView.countryValue.getText())) {

				if (sameCountryValidation()) {
					JOptionPane.showOptionDialog(null, "Please enter a different country", "Invalid",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
					return;
				} else {
					this.newContinentModel = (ContinentsModel) this.createCountryView.continentListCombobox
							.getSelectedItem();
					temp.setContinentName(this.newContinentModel.getContinentName());
					temp.setCountryName(this.createCountryView.countryValue.getText());
					
					temp.setBackground(Color.WHITE);
					temp.setBorderColor(Color.BLACK);
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
				JOptionPane.showOptionDialog(null, "Please enter at least one country for each continent", "Invalid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			} else {

				
				
				
				for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
						
						ArrayList<Point> pointList = this.mapPointList.get(this.gameMapModel.getCountries().get(i).getcontinentName());
						
						int index = this.indexMap.get(this.gameMapModel.getCountries().get(i).getcontinentName());
						
						System.out.println("==>" + this.mapPointList.get(this.gameMapModel.getCountries().get(i).getcontinentName()).get(index).x);
						this.gameMapModel.getCountries().get(i).setXPosition(this.mapPointList.get(this.gameMapModel.getCountries().get(i).getcontinentName()).get(index).x);
						this.gameMapModel.getCountries().get(i).setYPosition(this.mapPointList.get(this.gameMapModel.getCountries().get(i).getcontinentName()).get(index).y);
						this.gameMapModel.getCountries().get(i).setBackgroundColor(this.colorMapList.get(this.gameMapModel.getCountries().get(i).getcontinentName()));
						
						this.indexMap.put(this.gameMapModel.getCountries().get(i).getcontinentName(),this.indexMap.get(this.gameMapModel.getCountries().get(i).getcontinentName())+1);

				//	}

				}

				new ConnectCountryController(this.gameMapModel);
				this.createCountryView.dispose();
			}
		}
	}

	/**
	 * Check for same country validation
	 * @return boolean
	 */
	private boolean sameCountryValidation() {
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (this.gameMapModel.getCountries().get(i).getCountryName()
					.equals(this.createCountryView.countryValue.getText())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check for empty continent Value
	 * @return boolean
	 */
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
