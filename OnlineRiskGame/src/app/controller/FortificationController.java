package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;

import app.model.CountryModel;
import app.model.GameMapModel;
import app.utilities.Validation;
import app.view.FortificationView;

/**
 * In FortificationController, the data flow into model object and updates the
 * view whenever data changes.
 *
 * @author Suruthi Raju
 * @version 1.0.0
 * 
 */

public class FortificationController implements ActionListener, ItemListener {

	private FortificationView theFortificationView;
	private GameMapModel gameMapModel = null;

	/**
	 * Constructor initializes values and sets the screen too visible
	 * 
	 * @param gameMapModel
	 */
	public FortificationController(GameMapModel gameMapModel) {
		this.gameMapModel = gameMapModel;
		theFortificationView = new FortificationView(this.gameMapModel);
		theFortificationView.setActionListener(this);
		theFortificationView.setItemListener(this);
		theFortificationView.setVisible(true);
		this.gameMapModel.addObserver(this.theFortificationView);
	}

	/**
	 * This method performs action, by Listening the action event set in view.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(this.theFortificationView.moveButton)) {

			// BFS
			Validation val = new Validation();
			if (val.checkIfValidMove(gameMapModel,
					(CountryModel) this.theFortificationView.fromCountryListComboBox.getSelectedItem(),
					(CountryModel) this.theFortificationView.toCountryListComboBox.getSelectedItem())) {
				this.gameMapModel.setMovingArmies(
						(Integer) this.theFortificationView.numOfTroopsComboBox.getSelectedItem(),
						(CountryModel) this.theFortificationView.fromCountryListComboBox.getSelectedItem(),
						(CountryModel) this.theFortificationView.toCountryListComboBox.getSelectedItem());
			}

			int index = this.gameMapModel.getPlayerIndex();
			index++;
			if (this.gameMapModel.getListOfPlayers().size() > index) {
				this.gameMapModel.setPlayerIndex(index);
				this.gameMapModel.getListOfPlayers().get(index).callObservers();
				new GamePlayController(this.gameMapModel, this.gameMapModel.getListOfPlayers());
				this.theFortificationView.dispose();
			} else {
				JOptionPane.showOptionDialog(null, "Bravo! Game is over! No one won!", "Valid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				this.theFortificationView.dispose();
			}

		} else if (actionEvent.getSource().equals(this.theFortificationView.fromCountryListComboBox)) {
			this.gameMapModel
					.setSelectedComboBoxIndex(this.theFortificationView.fromCountryListComboBox.getSelectedIndex());
		}

	}

	/**
	 * Item Listener
	 * 
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent itemEvent) {
		if (itemEvent.getSource().equals(this.theFortificationView.fromCountryListComboBox)) {
			this.gameMapModel
					.setSelectedComboBoxIndex(this.theFortificationView.fromCountryListComboBox.getSelectedIndex());
		}

	}
}