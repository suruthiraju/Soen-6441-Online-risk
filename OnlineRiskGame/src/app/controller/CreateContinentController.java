
package app.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			if (this.createContinentView.controlValue.getText() != null
					&& this.createContinentView.controlValue.getText() != "") {
				if (0 < Integer.parseInt(this.createContinentView.controlValue.getText())
						&& Integer.parseInt(this.createContinentView.controlValue.getText()) < 10) {
					if (this.createContinentView.continentValue != null) {
						for (int index = 0; index < this.gameMapModel.getContinents().size(); index++) {
							if (this.gameMapModel.getContinents().get(index).getContinentName()
									.equals(this.createContinentView.continentValue.getText())) {
								JOptionPane.showOptionDialog(null, "You have already added this Continent", "Invalid",
										JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
										new Object[] {}, null);
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
				JOptionPane.showOptionDialog(null, "Please enter a non empty control value", "Invalid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}
		} else if (actionEvent.getSource().equals(this.createContinentView.nextButton)) {
			if (this.gameMapModel.getContinents().isEmpty()) {
				JOptionPane.showOptionDialog(null, "Please enter atleast one Continent to proceed", "Invalid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}
			ArrayList<ArrayList<Point>> pointsList = new ArrayList<ArrayList<Point>>();
			ArrayList<Color> colorList = new ArrayList<Color>();

			colorList.add(Color.RED);
			colorList.add(Color.GREEN);
			colorList.add(Color.BLUE);
			colorList.add(Color.CYAN);
			colorList.add(Color.ORANGE);

			pointsList.get(0).add(new Point(330, 40));
			pointsList.get(0).add(new Point(300, 95));
			pointsList.get(0).add(new Point(255, 110));
			pointsList.get(0).add(new Point(270, 120));
			pointsList.get(0).add(new Point(325, 130));

			pointsList.get(1).add(new Point(230, 160));
			pointsList.get(1).add(new Point(265, 150));
			pointsList.get(1).add(new Point(290, 160));
			pointsList.get(1).add(new Point(300, 180));
			pointsList.get(1).add(new Point(270, 195));

			pointsList.get(2).add(new Point(200, 210));
			pointsList.get(2).add(new Point(240, 200));
			pointsList.get(2).add(new Point(255, 220));
			pointsList.get(2).add(new Point(230, 245));
			pointsList.get(2).add(new Point(275, 225));

			pointsList.get(3).add(new Point(300, 210));
			pointsList.get(3).add(new Point(290, 240));
			pointsList.get(3).add(new Point(300, 260));
			pointsList.get(3).add(new Point(260, 285));
			pointsList.get(3).add(new Point(210, 270));

			pointsList.get(4).add(new Point(165, 260));
			pointsList.get(4).add(new Point(125, 220));
			pointsList.get(4).add(new Point(120, 260));
			pointsList.get(4).add(new Point(70, 290));
			pointsList.get(4).add(new Point(30, 285));

			HashMap<String, Color> colorMapList = new HashMap<String, Color>();
			HashMap<String, ArrayList<Point>> mapPointList = new HashMap<String, ArrayList<Point>>();
			HashMap<String, Integer> indexMap = new HashMap<String, Integer>();
			
			
			for (int i = 0; i < this.gameMapModel.getContinents().size(); i++) {
				mapPointList.put(this.gameMapModel.getContinents().get(i).getContinentName(), pointsList.get(i));
				colorMapList.put(this.gameMapModel.getContinents().get(i).getContinentName(), colorList.get(i));
				indexMap.put(this.gameMapModel.getContinents().get(i).getContinentName(), 0);
			}

			new CreateCountryController(this.gameMapModel, mapPointList, colorMapList,indexMap);
			this.createContinentView.dispose();
		}

	}
}
