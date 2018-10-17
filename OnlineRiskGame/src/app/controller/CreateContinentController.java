
package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import app.model.ContinentsModel;
import app.model.GameMapModel;
import app.view.CreateContinentView;

public class CreateContinentController implements ActionListener{

	private GameMapModel gameMapModel;
	private List<ContinentsModel> continentModel;
	private CreateContinentView createContinentView;
	
	public CreateContinentController(){
		this.gameMapModel = new GameMapModel();
		this.continentModel = this.gameMapModel.getContinents();
		this.createContinentView = new CreateContinentView();
		this.gameMapModel.addObserver(createContinentView);
		this.createContinentView.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
	}

}