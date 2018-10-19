package app.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import app.helper.View;
import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.PlayerModel;

/**
 * "FortificationView" class represents a view object of fortification 
 * phase.
 * 
 * @author GROUP-35
 *
 */
public class FortificationView extends JFrame implements View {

	public GameMapModel gameMapModel;
	public PlayerModel playerModel;

	public JPanel welcomePanel;
	public JPanel graphicPanel;

	public JLabel welcomeLabel;
	public JLabel noOfTroopsLabel;

	public JComboBox<Integer> numOfTroopsComboBox;
	public JButton moveButton;
	public JLabel listOfCountriesLabel;

	public JLabel fromCountryListLabel;
	public JLabel toCountryListLabel;
	public JComboBox<Object> countryListComboBox;
	public JComboBox<Object> countryListComboBox1;
	public Object[] countryListArray;
	private CountryViewRenderer countriesViewRenderer;
	public Object[] countryListArray1;
	private CountryViewRenderer countriesViewRenderer1;

	public JButton[] button;

/**
 * Constructor of FortificationView	
 * @param gameMapModel
 */
	public FortificationView(GameMapModel gameMapModel) {
		this.gameMapModel = gameMapModel;
		this.setTitle("Fortification Phase");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(300, 200);
		this.setSize(1600, 1000);
		this.setResizable(false);
		this.setVisible(false);

		welcomePanel = new JPanel();
		graphicPanel = new JPanel();
		this.add(graphicPanel);
		graphicPanel.setSize(1200, 1000);
		graphicPanel.setBackground(Color.WHITE);
		graphicPanel.setLayout(null);

		this.add(welcomePanel);
		this.playerModel = this.gameMapModel.getPlayerTurn();
		this.moveButton = new JButton("Move");
		updateWindow(this.gameMapModel, this.playerModel);
		welcomePanel.setLayout(null);
		graphicPanel.setLayout(null);
	}

/**
 * Updates the window based on new data in fortification phase	
 * @param gameMapModel
 * @param playerModel
 */
	public void updateWindow(GameMapModel gameMapModel, PlayerModel playerModel) {

		welcomePanel.removeAll();
		graphicPanel.removeAll();
		Font largeFont = new Font("Serif", Font.BOLD, 18);
		Font mediumFont = new Font("Serif", Font.BOLD, 14);
		Font smallFont = new Font("Serif", Font.BOLD, 12);

		this.gameMapModel = gameMapModel;
		this.playerModel = playerModel;

		this.welcomeLabel = new JLabel("It's " + playerModel.getNamePlayer() + "'s turn");
		welcomeLabel.setBounds(1300, 80, 300, 25);
		welcomeLabel.setFont(largeFont);
		welcomePanel.add(welcomeLabel);

		this.fromCountryListLabel = new JLabel("From Country :");
		fromCountryListLabel.setBounds(1300, 120, 150, 25);
		welcomePanel.add(this.fromCountryListLabel);

		// to country comboBox
		ArrayList<CountryModel> fromListOfCountries = new ArrayList<CountryModel>();
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (playerModel.getNamePlayer()
					.equals(this.gameMapModel.getCountries().get(i).getRuler().getNamePlayer())) {
				fromListOfCountries.add(this.gameMapModel.getCountries().get(i));
			}
		}
		countriesViewRenderer1 = new CountryViewRenderer();
		countryListArray1 = fromListOfCountries.toArray();
		countryListComboBox1 = new JComboBox(countryListArray1);
		welcomePanel.add(this.countryListComboBox1);

		if (countryListArray1.length > 0) {
			countryListComboBox1.setRenderer(countriesViewRenderer1);
		}
		countryListComboBox1.setBounds(1300, 150, 150, 25);
		welcomePanel.add(countryListComboBox1);

		this.noOfTroopsLabel = new JLabel("Number of Troops :");
		noOfTroopsLabel.setBounds(1300, 180, 150, 25);
		welcomePanel.add(noOfTroopsLabel);
		
		CountryModel countryName = (CountryModel) this.countryListComboBox1.getSelectedItem();
		Integer[] troops = new Integer[countryName.getArmies()-1];
		for (int i = 0; i < (countryName.getArmies()-1); i++) {
			troops[i] = i + 1;
		}

		numOfTroopsComboBox = new JComboBox(troops);
		numOfTroopsComboBox.setBounds(1300, 210, 150, 25);
		welcomePanel.add(numOfTroopsComboBox);

		this.toCountryListLabel = new JLabel("To Country :");
		toCountryListLabel.setBounds(1300, 240, 150, 25);
		welcomePanel.add(this.toCountryListLabel);

		// to country comboBox
		ArrayList<CountryModel> toListOfCountries = new ArrayList<CountryModel>();
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (playerModel.getNamePlayer()
					.equals(this.gameMapModel.getCountries().get(i).getRuler().getNamePlayer())) {
				toListOfCountries.add(this.gameMapModel.getCountries().get(i));
			}
		}
		countriesViewRenderer = new CountryViewRenderer();
		countryListArray = toListOfCountries.toArray();
		countryListComboBox = new JComboBox(countryListArray);
		welcomePanel.add(this.countryListComboBox);

		if (countryListArray.length > 0) {
			countryListComboBox.setRenderer(countriesViewRenderer);
		}
		countryListComboBox.setBounds(1300, 270, 150, 25);
		welcomePanel.add(countryListComboBox);

		this.moveButton.setBounds(1300, 330, 150, 25);
		welcomePanel.add(this.moveButton);

		int n = this.gameMapModel.getCountries().size();
		button = new JButton[n];
		for (int i = 0; i < n; i++) {
			CountryModel country = this.gameMapModel.getCountries().get(i);

			country.setBackground(this.gameMapModel.getCountries().get(i).getBackgroundColor());
			country.setText(this.gameMapModel.getCountries().get(i).getCountryName().substring(0, 3));
			country.setToolTipText("Troops: " + this.gameMapModel.getCountries().get(i).getArmies());
			country.setFont(smallFont);

			Border border = BorderFactory
					.createLineBorder(stringToColor(this.gameMapModel.getCountries().get(i).getRuler().getColor()), 3);

			country.setBorder(border);

			// countryButton[i].setForeground(Color.GREEN);

			country.setOpaque(true);
			country.setBounds(this.gameMapModel.getCountries().get(i).getXPosition() * 2,
					this.gameMapModel.getCountries().get(i).getYPosition() * 2, 50, 50);

			country.setMargin(new Insets(0, 0, 0, 0));

			graphicPanel.add(country);
		}
	}

/**
 * 	
 */
	public void paint(final Graphics g) {

		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		Point[] connectorPoints = new Point[this.gameMapModel.getCountries().size()];

		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			connectorPoints[i] = SwingUtilities.convertPoint(this.gameMapModel.getCountries().get(i), 0, 0, this);

		}

		for (int k = 0; k < this.gameMapModel.getCountries().size(); k++) {
			if (this.gameMapModel.getCountries().get(k).getLinkedCountries() != null) {
				ArrayList<CountryModel> neighbourCountries = (ArrayList<CountryModel>) this.gameMapModel.getCountries()
						.get(k).getLinkedCountries();

				for (int j = 0; j < neighbourCountries.size(); j++) {
					for (int i = 0; i < this.gameMapModel.getCountries().size(); i++)
						if (neighbourCountries.get(j).equals(this.gameMapModel.getCountries().get(i)))
							g2.drawLine(connectorPoints[i].x + 25, connectorPoints[i].y + 25, connectorPoints[k].x + 25,
									connectorPoints[k].y + 25);

				}
			}
		}

	}

/**
 * Provides a dynamic comboBox of country names	
 * @author GROUPE-35
 */
	public class CountryViewRenderer extends BasicComboBoxRenderer {

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

			CountryModel countryModel = (CountryModel) value;
			if (countryModel != null)
				setText(countryModel.getCountryName());

			return this;
		}
	}
	// }

/**
 * 	
 */
	@Override
	public void update(Observable obs, Object arg) {

		this.updateWindow(((GameMapModel) obs), this.playerModel);
		this.revalidate();
		this.repaint();

	}

/**
 * Sets "moveButton" action	
 */
	@Override
	public void setActionListener(ActionListener actionListener) {
		this.moveButton.addActionListener(actionListener);
	}

	
	public static Color stringToColor(final String value) {
		if (value == null) {
			return Color.black;
		}
		try {
			// get color by hex or octal value
			return Color.decode(value);
		} catch (NumberFormatException nfe) {
			// if we can't decode lets try to get it by name
			try {
				// try to get a color by name using reflection
				final Field f = Color.class.getField(value);

				return (Color) f.get(null);
			} catch (Exception ce) {
				// if we can't get any color return black
				return Color.black;
			}
		}
	}

}