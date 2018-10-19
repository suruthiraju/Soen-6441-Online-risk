
package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import app.model.ContinentsModel;
import app.model.GameMapModel;
import app.utilities.ReadFile;
import app.view.EditContinentView;

/**
 * In EditContinentController, the data flow into model object and updates the
 * view whenever data changes.
 *
 * @author Jatan
 * @version 1.0.0
 * 
 */

public class EditContinentController implements ActionListener {

	private EditContinentView editContinentView;
	private GameMapModel mapModel;
	private List<ContinentsModel> continentList;
	private ReadFile tempRead = new ReadFile();
	private File file;
	private ContinentsModel newContinentModel;
	private List<ContinentsModel> newContinentList;

	/**
	 * Constructor initializes values and sets the screen too visible
	 */
	public EditContinentController() {

		file = this.selectFile();
		tempRead.setFile(file);
		continentList = tempRead.getMapContinentDetails();
		this.mapModel = new GameMapModel();
		this.mapModel.setCountries(tempRead.getMapCountryDetails());
		mapModel.setContinents(continentList);
		mapModel.callObservers();
		this.newContinentList = new ArrayList<ContinentsModel>();
		continentList = this.mapModel.getContinents();
		this.editContinentView = new EditContinentView(continentList);
		this.editContinentView.setActionListener(this);
		this.editContinentView.setVisible(true);
		this.mapModel.addObserver(this.editContinentView);
	}

	/**
	 * Browsing a file
	 * 
	 * @return File
	 */
	public File selectFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		File file;
		int returnValue = jfc.showOpenDialog(this.editContinentView);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			file = new File(selectedFile.getAbsolutePath());
			return file;
		} else
			return null;

	}

	/**
	 * This method performs action, by Listening the action event set in view.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(this.editContinentView.addButton)) {
			if (this.editContinentView.controlValue.getText() != null
					&& !this.editContinentView.controlValue.getText().isEmpty()) {
				if (0 < Integer.parseInt(this.editContinentView.controlValue.getText())
						&& Integer.parseInt(this.editContinentView.controlValue.getText()) < 10) {

					this.newContinentModel = (ContinentsModel) this.editContinentView.continentListCombobox
							.getSelectedItem();

					this.mapModel.removeContinent(this.newContinentModel);

					this.newContinentModel
							.setValueControl(Integer.parseInt(this.editContinentView.controlValue.getText()));
					this.newContinentList.add(this.newContinentModel);

					System.out.println(this.newContinentList);

				} else {
					JOptionPane.showOptionDialog(null, "Please enter a control value between 0 and 10", "Invalid",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
					return;
				}
			} else {
				JOptionPane.showOptionDialog(null, "Please enter at least one control value", "Invalid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}
		} else if (actionEvent.getSource().equals(this.editContinentView.saveButton)) {
			if (this.editContinentView.controlValue.getText() != null
					&& !this.editContinentView.controlValue.getText().isEmpty()) {
				if (0 < Integer.parseInt(this.editContinentView.controlValue.getText())
						&& Integer.parseInt(this.editContinentView.controlValue.getText()) < 10) {
					if (!this.newContinentList.isEmpty()) {
						this.mapModel.setContinents(newContinentList);
						this.mapModel = this.mapModel.updateCountries(this.mapModel);
						new ConnectCountryController(this.mapModel);
						this.editContinentView.dispose();
						// open connectCountries Controller and pass the map model
					} else {
						JOptionPane.showOptionDialog(null, "Please add atleast one continent first.", "Invalid",
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
				JOptionPane.showOptionDialog(null, "Please enter at least one control value", "Invalid",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
				return;
			}
		}
	}

}
