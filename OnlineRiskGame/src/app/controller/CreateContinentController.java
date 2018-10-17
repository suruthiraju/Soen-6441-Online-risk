
package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import app.model.ContinentsModel;
import app.model.GameMapModel;
import app.view.CreateContinentView;

public class CreateContinentController implements ActionListener {

	private GameMapModel gameMapModel;
	private CreateContinentView createContinentView;
	private List<ContinentsModel> continentModelList;

	public CreateContinentController() {
		this.gameMapModel = new GameMapModel();
		this.createContinentView = new CreateContinentView();
		this.continentModelList = new ArrayList<ContinentsModel>();
		this.gameMapModel.addObserver(this.createContinentView);
		this.createContinentView.setActionListener(this);
		this.createContinentView.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		String contName;
		int controlValue;
		ContinentsModel tempContinent = new ContinentsModel(this.createContinentView.continentValue.getText(),
				Integer.parseInt(this.createContinentView.controlValue.getText()));
		if (actionEvent.getSource().equals(this.createContinentView.addButton)) {
			if (this.createContinentView.controlValue.getText() != null && this.createContinentView.controlValue.getText() != "") {
				if (0 < Integer.parseInt(this.createContinentView.controlValue.getText())
						&& Integer.parseInt(this.createContinentView.controlValue.getText()) < 10) {
					if (this.createContinentView.continentValue != null) {
						for (int index = 0; index < this.gameMapModel.getContinents().size(); index++) {
							if (this.gameMapModel.getContinents().get(index)
									.getContinentName().equals(this.createContinentView.continentValue.getText()) ) {
								JOptionPane.showOptionDialog(null, "You have already added this Continent", "Invalid",
										JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
										null);
								return;
							}
							}
								this.gameMapModel.getContinents().add(tempContinent);
								this.gameMapModel.setContinents(this.gameMapModel.getContinents());
							
						
					} else {
						JOptionPane.showOptionDialog(null, "Please enter some country name", "Invalid",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
								null);
						return;
					}
				} else {
					JOptionPane.showOptionDialog(null, "Please enter a control value between 0 and 10", "Invalid",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
					return;
				}
			} else {
				JOptionPane.showOptionDialog(null, "Please enter some value for control value", "Invalid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}
		} else if (actionEvent.getSource().equals(this.createContinentView.nextButton)) {
			if (this.gameMapModel.getContinents().isEmpty()) {
				JOptionPane.showOptionDialog(null, "Please enter atleast one Continent to proceed", "Invalid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;	
			}
				new CreateCountryController(this.gameMapModel);
				this.createContinentView.dispose();
		}

	}
}
