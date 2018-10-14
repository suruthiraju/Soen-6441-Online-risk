/**
 * 
 */
package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import app.model.ContinentsModel;
import app.model.CreateMapModel;
import app.model.GameMapModel;
import app.utilities.ReadFile;
import app.view.CreateContinentView;

/**
 * @author Jatan Gohel
 *
 */
public class CreateMapController implements ActionListener {

	private CreateContinentView createContinentView;
	private GameMapModel mapModel;
	private List<ContinentsModel> continentList;

	private ContinentsModel newContinentModel;
	private List<ContinentsModel> newContinentList;

	public CreateMapController() {
		this.mapModel = new GameMapModel();
		this.newContinentList = new ArrayList<ContinentsModel>();
		continentList = this.mapModel.getContinents();
		this.createContinentView = new CreateContinentView(continentList);
		this.createContinentView.setActionListener(this);
		this.createContinentView.setVisible(true);
		this.mapModel.addObserver(this.createContinentView);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(this.createContinentView.addButton)) {
			if (this.createContinentView.controlValue.getText() != null && !this.createContinentView.controlValue.getText().isEmpty()) {
				if(0<Integer.parseInt(this.createContinentView.controlValue.getText()) && Integer.parseInt(this.createContinentView.controlValue.getText())<10) {
				
				this.newContinentModel = (ContinentsModel) this.createContinentView.continentListCombobox
						.getSelectedItem();

				this.mapModel.removeContinent(this.newContinentModel);
				
				this.newContinentModel
						.setValueControl(Integer.parseInt(this.createContinentView.controlValue.getText()));
				this.newContinentList.add(this.newContinentModel);
				
				
				System.out.println(this.newContinentList);
				
				}else {
					JOptionPane.showOptionDialog(null, "Please enter a control value between 0 and 10", "Invalid",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
					return;
				}
			} else {
				JOptionPane.showOptionDialog(null, "Please enter at least one control value", "Invalid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}
		}else if(actionEvent.getSource().equals(this.createContinentView.saveButton) ) {
			if (this.createContinentView.controlValue.getText() != null && !this.createContinentView.controlValue.getText().isEmpty()) {
				if(0<Integer.parseInt(this.createContinentView.controlValue.getText()) && Integer.parseInt(this.createContinentView.controlValue.getText())<10) {
						this.mapModel.setContinents(newContinentList);
						//open connectCountries Controller and pass the map model
					}else {
						JOptionPane.showOptionDialog(null, "Please enter a control value between 0 and 10", "Invalid",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
						return;
					}
				}else {
					JOptionPane.showOptionDialog(null, "Please enter at least one control value", "Invalid",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
					return;
				}
		}
	}

}
