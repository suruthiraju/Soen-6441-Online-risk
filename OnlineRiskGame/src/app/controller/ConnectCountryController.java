package app.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.model.CountryModel;
import app.model.GameMapModel;
import app.utilities.validation;
import app.utilities.writeMap;
import app.view.ConnectCountryView;

public class ConnectCountryController implements ActionListener, ListSelectionListener {

	private GameMapModel gameMapModel;
	private ConnectCountryView connectCountryView;
	private List<CountryModel> countryList;
	private List<CountryModel> countryListLinks;
	private CountryModel newCountryModel;
	private String filename;
	private writeMap tempWrite;

	public ConnectCountryController(GameMapModel mapModel) {

		this.gameMapModel = mapModel;
		this.countryList = this.gameMapModel.getCountries();
		this.countryListLinks = new ArrayList<CountryModel>();
		this.connectCountryView = new ConnectCountryView(this.gameMapModel);

		this.connectCountryView.setActionListener(this);
		this.connectCountryView.setListSelectionListener(this);
		this.connectCountryView.setVisible(true);
		this.gameMapModel.addObserver(this.connectCountryView);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(this.connectCountryView.addButton)) {
			if (connectCountryView.countryParentListLeft.getSelectedValue()
					.equals(connectCountryView.countryParentListRight.getSelectedValue())) {
				JOptionPane.showOptionDialog(null, "Cannot create a self link", "Invalid", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			} else {

				this.gameMapModel.setNeighbouringCountry(
						(CountryModel) this.connectCountryView.countryParentListLeft.getSelectedValue(),
						(CountryModel) this.connectCountryView.countryParentListRight.getSelectedValue());

			}
		} else if (actionEvent.getSource().equals(this.connectCountryView.saveButton)) 
		{
			validation MapValidation = new validation();
			boolean flag1 = MapValidation.emptyLinkCountryValidation(this.gameMapModel);
			
			boolean flag3 = MapValidation.emptyContinentValidation(this.gameMapModel);
			boolean flag2 = MapValidation.checkInterlinkedContinent(this.gameMapModel);
			System.out.println(flag1 +" "+ flag2 +" " + flag3);
			if(!(MapValidation.emptyLinkCountryValidation(this.gameMapModel)))
			{
				if((!MapValidation.checkInterlinkedContinent(this.gameMapModel)))
				{
					if(!(MapValidation.emptyContinentValidation(this.gameMapModel)))
					{
						System.out.println(" All the map validations are correct");
						filename=JOptionPane.showInputDialog("File Name");
						try {
							System.out.println(filename);
							tempWrite = new writeMap();
							tempWrite.writeMapToFile(filename, this.gameMapModel);
							JOptionPane.showMessageDialog(null, "Map has been created select it before you play");
							new WelcomeScreenController();
							this.connectCountryView.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}						
						
					}
					else
					{
						System.out.println("Empty link country validation failed");
							JOptionPane.showOptionDialog(null, "Empty continent validation failed", "Invalid", JOptionPane.DEFAULT_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
					}
				}
				else 
				{
					System.out.println("ECheck interlinked Continent validation failed");
					JOptionPane.showOptionDialog(null, "Check interlinedContinent validation failed", "Invalid", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
					
				}
			}
			else 
			{
					System.out.println("Empty continent validation failed");
					JOptionPane.showOptionDialog(null, "Empty link country validation failed", "Invalid", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
			}

		} 
		else if (actionEvent.getSource().equals(this.connectCountryView.removeButton)) {
			
			this.gameMapModel.removeNeighbouringCountry(
					(CountryModel) this.connectCountryView.countryParentListLeft.getSelectedValue(),
					(CountryModel) this.connectCountryView.countryParentListRight.getSelectedValue());
			
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

		ListSelectionModel lsm = (ListSelectionModel) e.getSource();

		int firstIndex = e.getFirstIndex();
		int lastIndex = e.getLastIndex();
		boolean isAdjusting = e.getValueIsAdjusting();
		System.out.println("Event for indexes " + firstIndex + " - " + lastIndex + "; isAdjusting is " + isAdjusting
				+ "; selected indexes:");

		if (lsm.isSelectionEmpty()) {
			System.out.println(" <none>");
		} else {
			// Find out which indexes are selected.
			int minRightIndex = lsm.getMinSelectionIndex();
			int maxRightIndex = lsm.getMaxSelectionIndex();
			int finalRightModelIndex = 0;
			for (int i = minRightIndex; i <= maxRightIndex; i++) {
				if (this.connectCountryView.listSelectionModelLeft.isSelectedIndex(i)) {
					finalRightModelIndex = i;
				}
			}
			System.out.println(finalRightModelIndex);
		}

		// ListSelectionModel lsm = (ListSelectionModel)actionEvent.getSource();
		if (e.getSource().equals(this.connectCountryView.countryParentListLeft)) {

			this.gameMapModel.setColorToCountry(
					(CountryModel) this.connectCountryView.countryParentListLeft.getSelectedValue(), Color.GREEN);

		} else if (e.getSource().equals(this.connectCountryView.countryParentListRight)) {

			this.gameMapModel.setColorToCountry(
					(CountryModel) this.connectCountryView.countryParentListRight.getSelectedValue(), Color.YELLOW);
			// CountryModel cm =
			// (CountryModel)this.connectCountryView.countryParentListLeft.getSelectedValue();

		}
	}

}
