package app.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import app.helper.View;
import app.model.ContinentsModel;
import app.model.GameMapModel;

/**
 * 
 * @author Jatan Gohel
 *
 */


public class EditContinentView extends JFrame implements View {

	public JLabel welcomeLabel;
	public JLabel continentListText;
	public JComboBox continentListCombobox;
	public Object[] continentListArray;
	private ContinentViewRenderer continentViewRenderer;
	public JLabel controlValueText;
	public JTextField controlValue;
	public JButton saveButton;
	public JButton addButton;
	public JPanel welcomePanel;

	public EditContinentView(List<ContinentsModel> listOfContinents) {

		welcomeLabel = new JLabel("Please select the Continents you want in the map and the control value");

		continentListText = new JLabel("Continent");
		controlValueText = new JLabel("Control Value");
		controlValue = new JTextField();

		saveButton = new JButton("Save");
		addButton = new JButton("Add");

		welcomePanel = new JPanel();

		this.setName("RISK GAME");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(300, 200);
		this.setSize(800, 700);
		this.setResizable(false);
		this.setVisible(false);
		welcomePanel.setLayout(null);
		this.add(welcomePanel);
		this.updateWindow(listOfContinents);

	}

	/*
	 * Inner Class Continent View Renderer class that is used to generate a dynamic
	 * combobox
	 */
	public class ContinentViewRenderer extends BasicComboBoxRenderer {

		/*
		 * Getter method that provides us a map model corresponding to a map name
		 * 
		 * @see javax.swing.plaf.basic.BasicComboBoxRenderer#
		 * getListCellRendererComponent(javax.swing.JList, java.lang.Object, int,
		 * boolean, boolean)
		 */
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			ContinentsModel map_model = (ContinentsModel) value;
			if (map_model != null)
				setText(map_model.getContinentName());

			return this;
		}
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		addButton.addActionListener(actionListener);
		saveButton.addActionListener(actionListener);
	}

	@Override
	public void update(Observable obs, Object obj) {
		List<ContinentsModel> listOfContinentModel = ((GameMapModel) obs).getContinents();
		this.updateWindow(listOfContinentModel);
		this.revalidate();
		this.repaint();
	}

	private void updateWindow(List<ContinentsModel> listOfContinentModel) {
		welcomePanel.removeAll();
		Font largeFont = new Font("Serif", Font.BOLD, 18);
		Font mediumFont = new Font("Serif", Font.BOLD, 14);
		Font smallFont = new Font("Serif", Font.BOLD, 12);

		welcomeLabel.setFont(largeFont);
		welcomePanel.add(welcomeLabel);
		welcomeLabel.setBounds(100, 0, 600, 200);

		continentListText.setFont(mediumFont);
		welcomePanel.add(continentListText);
		continentListText.setBounds(100, 50, 500, 200);

		controlValueText.setFont(mediumFont);
		welcomePanel.add(controlValueText);
		controlValueText.setBounds(100, 150, 200, 100);

		continentViewRenderer = new ContinentViewRenderer();
		/*
		 * Combo-box for selecting a particular map from list of maps available in the
		 * gameMapModel
		 */
		continentListArray = listOfContinentModel.toArray();
		continentListCombobox = new JComboBox(continentListArray);

		if (continentListArray.length > 0) {
			continentListCombobox.setRenderer(continentViewRenderer);
		}
		continentListCombobox.setBounds(200, 140, 100, 20);
		welcomePanel.add(continentListCombobox);

		controlValue.setFont(mediumFont);
		welcomePanel.add(controlValue);
		controlValue.setBounds(200, 195, 100, 20);

		addButton.setFont(smallFont);
		welcomePanel.add(addButton);
		addButton.setBounds(100, 250, 100, 20);

		saveButton.setFont(smallFont);
		welcomePanel.add(saveButton);
		saveButton.setBounds(200, 250, 100, 20);
	}

}
