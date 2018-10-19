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
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.PlayerModel;

/**
 * This class file is the view (observer) for Start Up Phase of Game Play
 * 
 * @author Suruthi Raju
 * @version 1.0.0
 */

public class StartUpView extends JFrame implements View {

	private static final long serialVersionUID = 1L;
	public GameMapModel gameMapModel;
	public PlayerModel playerModel;

	public JPanel welcomePanel;
	public JPanel graphicPanel;

	public JLabel welcomeLabel;
	public JLabel welcomeLabel1;
	public JLabel noOfTroopsLabel;

	public JComboBox<Integer> numOfTroopsComboBox;
	public JButton addButton;
	public JLabel listOfCountriesLabel;

	public JLabel countryListLabel;
	public JComboBox<Object> countryListComboBox;
	public Object[] countryListArray;
	private CountryViewRenderer countriesViewRenderer;

	public JButton[] button;
	public JButton nextButton;
	public JButton button2;
	public JButton button3;

	/**
	 * constructor for Start Up Phase View where the variables are initialized
	 * 
	 * @param gameMapModel
	 * @param playerModel
	 */
	public StartUpView(GameMapModel gameMapModel, PlayerModel playerModel) {

		this.setTitle("Startup Phase");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(300, 200);
		this.setSize(1600, 1000);
		this.setResizable(false);
		this.setVisible(false);
		this.addButton = new JButton("Add");
		this.nextButton = new JButton("Next");
		welcomePanel = new JPanel();
		graphicPanel = new JPanel();
		this.add(graphicPanel);
		graphicPanel.setSize(1200, 1000);
		graphicPanel.setBackground(Color.WHITE);
		graphicPanel.setLayout(null);
		this.addButton = new JButton("Add");
		this.add(welcomePanel);
		this.playerModel = playerModel;
		this.welcomeLabel = new JLabel("It's " + playerModel.getNamePlayer() + "'s turn");
		this.welcomeLabel1 = new JLabel("Remaining Armies: " + playerModel.getremainTroop());
		updateWindow(gameMapModel, playerModel);
		welcomePanel.setLayout(null);
		graphicPanel.setLayout(null);
	}

	/**
	 * This updateWindow method is called whenever the model is updated. It updates
	 * the Screen for Start Up Phase
	 * 
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

		welcomeLabel.setBounds(1300, 80, 300, 25);
		welcomeLabel.setFont(largeFont);
		welcomePanel.add(welcomeLabel);

		this.noOfTroopsLabel = new JLabel("Number of Troops :");
		noOfTroopsLabel.setBounds(1300, 140, 150, 25);
		welcomePanel.add(noOfTroopsLabel);

		Integer[] troops = new Integer[playerModel.getremainTroop()];
		for (int i = 0; i < playerModel.getremainTroop(); i++) {
			troops[i] = i + 1;
		}

		numOfTroopsComboBox = new JComboBox(troops);
		numOfTroopsComboBox.setBounds(1300, 170, 150, 25);
		numOfTroopsComboBox.setEnabled(false);
		welcomePanel.add(numOfTroopsComboBox);

		welcomeLabel1 = new JLabel("Remaining Armies: " + playerModel.getremainTroop());
		welcomeLabel1.setBounds(1450, 170, 300, 25);
		welcomeLabel1.setFont(smallFont);
		welcomePanel.add(welcomeLabel1);

		this.countryListLabel = new JLabel("Select Country :");
		countryListLabel.setBounds(1300, 230, 150, 25);
		welcomePanel.add(this.countryListLabel);

		ArrayList<CountryModel> listOfCountries = new ArrayList<CountryModel>();
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (playerModel.getNamePlayer()
					.equals(this.gameMapModel.getCountries().get(i).getRuler().getNamePlayer())) {
				listOfCountries.add(this.gameMapModel.getCountries().get(i));
			}
		}

		countriesViewRenderer = new CountryViewRenderer();
		countryListArray = listOfCountries.toArray();
		countryListComboBox = new JComboBox(countryListArray);
		welcomePanel.add(this.countryListComboBox);

		if (countryListArray.length > 0) {
			countryListComboBox.setRenderer(countriesViewRenderer);
		}
		countryListComboBox.setBounds(1300, 260, 150, 25);
		welcomePanel.add(countryListComboBox);

		this.addButton.setBounds(1300, 300, 150, 25);
		welcomePanel.add(this.addButton);

		this.nextButton.setBounds(1400, 600, 150, 25);
		welcomePanel.add(this.nextButton);

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

			country.setOpaque(true);
			country.setBounds(this.gameMapModel.getCountries().get(i).getXPosition() * 2,
					this.gameMapModel.getCountries().get(i).getYPosition() * 2, 50, 50);

			country.setMargin(new Insets(0, 0, 0, 0));

			graphicPanel.add(country);
		}
	}

	/**
	 * Countries are rendered as button and linked with Swing using Graphics.
	 * 
	 * @see java.awt.Window#paint(java.awt.Graphics)
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
	 * Getter method that provides us a map model corresponding to a map name
	 * 
	 * @see javax.swing.plaf.basic.BasicComboBoxRenderer#
	 *      getListCellRendererComponent(javax.swing.JList, java.lang.Object, int,
	 *      boolean, boolean)
	 */

	public class CountryViewRenderer extends BasicComboBoxRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			CountryModel countryModel = (CountryModel) value;
			if (countryModel != null)
				setText(countryModel.getCountryName());

			return this;
		}
	}

	/**
	 * Update method is to Update the start up Phase. This is declared as
	 * observable. so when the values are changed the view is updated automatically
	 * by notifying the observer.
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable obs, Object arg) {

		if (obs instanceof GameMapModel) {
			this.gameMapModel = (GameMapModel) obs;
		} else if (obs instanceof PlayerModel) {
			this.playerModel = (PlayerModel) obs;
		}
		this.updateWindow(this.gameMapModel, this.playerModel);
		this.revalidate();
		this.repaint();

	}

	/**
	 * This is actionListener method to listen the action events in the screen
	 * 
	 * @see app.helper.View#setActionListener(java.awt.event.ActionListener)
	 */
	@Override
	public void setActionListener(ActionListener actionListener) {
		this.addButton.addActionListener(actionListener);
		this.nextButton.addActionListener(actionListener);
	}

	/**
	 * This method convert string to color
	 * 
	 * @param value
	 * @return
	 */
	public static Color stringToColor(final String value) {
		if (value == null) {
			return Color.black;
		}
		try {
			return Color.decode(value);
		} catch (NumberFormatException nfe) {
			try {
				final Field f = Color.class.getField(value);

				return (Color) f.get(null);
			} catch (Exception ce) {
				return Color.black;
			}
		}
	}

}