/**
 * 
 */
package app.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import app.model.ContinentsModel;
import app.model.CreateMapModel;
import app.model.GameMapModel;
import app.utilities.ReadFile;
import app.view.CreateContinentView;

/**
 * @author Jatan Gohel
 *
 */
public class CreateMapController implements ActionListener{
	
	private CreateContinentView createContinentView;
	private GameMapModel mapModel;
	private List<ContinentsModel> continentList;
	
	
	public CreateMapController() {
		this.mapModel = new GameMapModel();
		continentList = this.mapModel.getContinents();
		this.createContinentView = new CreateContinentView(continentList);
		this.createContinentView.setActionListener(this);
		this.createContinentView.setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent actionEvent) {
//		if (actionEvent.getSource().equals(this.mapView.)) {
//			this.showCreateMapWindow();
//
//		}
	}
	
	
}
