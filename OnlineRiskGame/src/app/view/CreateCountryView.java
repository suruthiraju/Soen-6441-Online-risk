package app.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import app.helper.View;
import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;

/**
 * "CreateCountryView" class represents a view object for creating a country
 * view Properties are containing labels, text fields, buttons, combo-boxes, a
 * pane, and a panel
 * 
 * @author Jatan Gohel
 */

public class CreateCountryView extends JFrame implements View {

	public JLabel welcomeLabel;
	public JTextField countryValue;
	public JComboBox continentListCombobox;
	public Object[] continentListArray;
	private CountryViewRenderer continentViewRenderer;
	public JLabel countryListText;
	public JLabel continentNameLabel;
	public JTextArea observerList;
	public JButton nextButton;
	public JButton addButton;
	public JButton saveButton;
	public JTextArea consoleTextArea;
	public JTextArea consoleMainPanel;
	public JScrollPane consolePanel;
	public JPanel mainPanel;
	JTextArea textArea;

	/**
	 * Construction of "CreateCountryView"
	 * 
	 * @param listOfContinents
	 */
	public CreateCountryView(List<ContinentsModel> listOfContinents) {
		this.setTitle("Create Country");

		welcomeLabel = new JLabel("Please add the Countries in the Continents you created:");

		countryListText = new JLabel("Country");
		addButton = new JButton("Add");
		saveButton = new JButton("Save");
		mainPanel = new JPanel();

		this.setName("RISK GAME");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(300, 200);
		this.setSize(800, 700);
		this.setResizable(false);
		this.setVisible(false);
		mainPanel.setLayout(null);
		this.add(mainPanel);
		mainPanel = new JPanel();
		mainPanel.setLayout(null);

		textArea = new JTextArea("Default text", 5, 5);

		welcomeLabel = new JLabel("Please add the Countries in the Continents you created:");
		welcomeLabel.setBounds(100, 0, 600, 100);

		countryListText = new JLabel("Country Name: ");
		countryListText.setBounds(100, 100, 200, 40);

		countryValue = new JTextField();
		countryValue.setBounds(200, 100, 200, 40);

		continentNameLabel = new JLabel("Continent Name: ");
		continentNameLabel.setBounds(100, 200, 200, 40);

		addButton = new JButton("Add");
		addButton.setBounds(100, 400, 100, 40);

		nextButton = new JButton("Next");
		nextButton.setBounds(200, 400, 100, 40);
		updateScreen(listOfContinents, null);
	}

	/**
	 * Updates the screen after creating a country
	 * 
	 * @param listOfContinentModel
	 * @param listOfCountryModel
	 */
	public void updateScreen(List<ContinentsModel> listOfContinentModel, List<CountryModel> listOfCountryModel) {
		mainPanel.removeAll();

		StringBuilder textAreaText = new StringBuilder("------------------------------------------------");

		if (listOfCountryModel == null) {
			textArea.setText(textAreaText.toString());
		} else {
			textAreaText.setLength(0);
			for (int i = 0; i < listOfCountryModel.size(); i++) {
				textAreaText.append("Country: " + listOfCountryModel.get(i).getCountryName() + " ,Continent: "
						+ listOfCountryModel.get(i).getcontinentName() + "\n");
			}
		}
		textArea.setText(textAreaText.toString());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		mainPanel.add(textArea);
		textArea.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "Countries added list:"));
		textArea.setBounds(520, 0, 260, 650);

		Color main = new Color(230, 230, 255);
		Color secondary = new Color(0, 0, 26);
		textArea.setBackground(main); // sets the background color
		textArea.setForeground(secondary);

		continentViewRenderer = new CountryViewRenderer();
		continentListArray = listOfContinentModel.toArray();
		continentListCombobox = new JComboBox(continentListArray);

		if (continentListArray.length > 0) {
			continentListCombobox.setRenderer(continentViewRenderer);
		}
		continentListCombobox.setBounds(200, 200, 100, 40);
		mainPanel.add(continentListCombobox);

		this.add(mainPanel);
		mainPanel.add(welcomeLabel);
		mainPanel.add(continentNameLabel);
		mainPanel.add(addButton);
		mainPanel.add(nextButton);
		mainPanel.add(countryListText);
		mainPanel.add(countryValue);
		mainPanel.add(countryListText);

	}

	/**
	 * Sets actions to "addButton" and "nextButton"
	 * 
	 * @see app.helper.View#setActionListener(java.awt.event.ActionListener)
	 */
	@Override
	public void setActionListener(ActionListener actionListener) {
		this.addButton.addActionListener(actionListener);
		this.nextButton.addActionListener(actionListener);
	}

	/**
	 * Update the view based on observer
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable obs, Object arg) {
		List<CountryModel> listOfCountryModel = ((GameMapModel) obs).getCountries();
		List<ContinentsModel> listOfContinentModel = ((GameMapModel) obs).getContinents();
		this.updateScreen(listOfContinentModel, listOfCountryModel);
		this.revalidate();
		this.repaint();
	}

	/**
	 * Inside, getter method that provides us a map model corresponding to a map
	 * name
	 */
	public class CountryViewRenderer extends BasicComboBoxRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			ContinentsModel map_model = (ContinentsModel) value;
			if (map_model != null)
				setText(map_model.getContinentName());

			return this;
		}
	}

}