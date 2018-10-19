package app.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
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
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import app.controller.FortificationController;
import app.helper.View;
import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.PlayerModel;

/**
 * 
 * @author DELL
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
	public JComboBox<Object> fromCountryListComboBox;
	public JComboBox<Object> toCountryListComboBox;
	public Object[] fromCountryListArray;
	private CountryViewRenderer fromCountriesViewRenderer;
	public Object[] toCountryListArray;
	private CountryViewRenderer toCountriesViewRenderer;

	public JButton[] button;
	private ActionListener actionListener;

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

		// from country comboBox
		ArrayList<CountryModel> fromListOfCountries = new ArrayList<CountryModel>();
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (playerModel.getNamePlayer().equals(this.gameMapModel.getCountries().get(i).getRuler().getNamePlayer())
					&& this.gameMapModel.getCountries().get(i).getArmies() >= 2) {
				fromListOfCountries.add(this.gameMapModel.getCountries().get(i));
			}
		}
		fromCountriesViewRenderer = new CountryViewRenderer();
		fromCountryListArray = fromListOfCountries.toArray();

		fromCountryListComboBox = new JComboBox(fromCountryListArray);
		fromCountryListComboBox.setSelectedIndex(this.gameMapModel.getSelectedComboBoxIndex());
		welcomePanel.add(this.fromCountryListComboBox);

		
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
		toCountriesViewRenderer = new CountryViewRenderer();
		toCountryListArray = toListOfCountries.toArray();
		toCountryListComboBox = new JComboBox(toCountryListArray);
		welcomePanel.add(this.toCountryListComboBox);

		if (toCountryListArray.length > 0) {
			toCountryListComboBox.setRenderer(toCountriesViewRenderer);
		}
		toCountryListComboBox.setBounds(1300, 270, 150, 25);
		welcomePanel.add(toCountryListComboBox);

		this.noOfTroopsLabel = new JLabel("Number of Troops :");
		noOfTroopsLabel.setBounds(1300, 180, 150, 25);
		welcomePanel.add(noOfTroopsLabel);

		
		CountryModel countryName = (CountryModel) this.fromCountryListComboBox.getSelectedItem();
		Integer[] troops = new Integer[countryName.getArmies() - 1];
		for (int i = 0; i < (countryName.getArmies() - 1); i++) {
			troops[i] = i + 1;
		}

		numOfTroopsComboBox = new JComboBox(troops);
		numOfTroopsComboBox.setBounds(1300, 210, 150, 25);
		welcomePanel.add(numOfTroopsComboBox);

		if (fromCountryListArray.length > 0) {
			fromCountryListComboBox.setRenderer(fromCountriesViewRenderer);
		}
		fromCountryListComboBox.setBounds(1300, 150, 150, 25);
		welcomePanel.add(fromCountryListComboBox);
		

		this.moveButton.setBounds(1300, 330, 150, 25);
		welcomePanel.add(this.moveButton);

		int n = this.gameMapModel.getCountries().size();
		button = new JButton[n];
		
		//graphicPanel.add(button[0]);
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			
			button[i] = new JButton();
			button[i].setText(this.gameMapModel.getCountries().get(i).getCountryName().substring(0,3));
			button[i].setBackground(this.gameMapModel.getCountries().get(i).getBackgroundColor());
			button[i].setToolTipText("Troops: "+this.gameMapModel.getCountries().get(i).getArmies());
			button[i].setBorder(new LineBorder(stringToColor(this.gameMapModel.getCountries().get(i).getRuler().getColor()), 3));
			button[i].setOpaque(true);
			button[i].setBounds(this.gameMapModel.getCountries().get(i).getXPosition() * 2,
					this.gameMapModel.getCountries().get(i).getYPosition() * 2, 50, 50);



			graphicPanel.add(button[i]);
		}
		if(null!=this.actionListener) {
			this.setActionListener(this.actionListener);
		}
		
	}

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

	@Override
	public void update(Observable obs, Object arg) {

		this.updateWindow(((GameMapModel) obs), this.playerModel);
		this.revalidate();
		this.repaint();

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		this.moveButton.addActionListener(actionListener);
		this.fromCountryListComboBox.addActionListener(actionListener);
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

	public void setItemListener(ItemListener itemListener) {
		this.fromCountryListComboBox.addItemListener(itemListener);

	}

}