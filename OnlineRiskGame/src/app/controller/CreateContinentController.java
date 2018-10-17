
package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import app.model.ContinentsModel;
import app.model.GameMapModel;
import app.view.CreateContinentView;

public class CreateContinentController implements ActionListener {

	private GameMapModel gameMapModel;
	private CreateContinentView createContinentView;

	public CreateContinentController() {
		this.gameMapModel = new GameMapModel();
		this.createContinentView = new CreateContinentView();
		this.gameMapModel.addObserver(createContinentView);
		this.createContinentView.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		String contName;
		int controlValue;
		ContinentsModel tempContinent = new ContinentsModel(this.createContinentView.continentValue.getText(), Integer.parseInt(this.createContinentView.controlValue.getText()));
		if (actionEvent.getSource().equals(this.createContinentView.addButton))
		{
			if (Integer.parseInt(this.createContinentView.controlValue.getText())<10)
			{
				if(this.createContinentView.controlValue.getText() != null)
				{
					if(this.createContinentView.continentValue!=null)
					{
						
					
						for(int index=0; index<=this.gameMapModel.getContinents().size();index++)
						{
							if(this.gameMapModel.getContinents().get(index).getContinentName()==this.createContinentView.continentValue.getText())
							{
								System.out.println("You have already added this Continent");
							}
							else
							{
								this.gameMapModel.getContinents().add(tempContinent);
							}
						}
						else
						{
							JOptionPane.showOptionDialog(null, "Please enter some country name", "Invalid",
									JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
							return;
						}
					}
					else
					{
						JOptionPane.showOptionDialog(null, "Please enter some value for control value", "Invalid",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
						return;
					}
				}
				else
				{
					JOptionPane.showOptionDialog(null, "Please enter a control value between 0 and 10", "Invalid",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
					return;
				}
			}
			else
			{
				if(actionEvent.getSource().equals(this.createContinentView.nextButton))
				{
					if(this.gameMapModel.getContinents().isEmpty())
					{
						System.out.println("Please enter atleast one Continent to proceed");
					}
					
				}
			}
		}
	}
}
