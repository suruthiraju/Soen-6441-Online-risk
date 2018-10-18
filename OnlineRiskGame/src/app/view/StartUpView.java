package app.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import app.helper.View;
import app.model.ContinentsModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.PlayerModel;
import app.utilities.ReadFile;
import app.view.CreateCountryView.CountryViewRenderer;

/**
 * 
 * @author DELL
 *
 */

public class StartUpView extends JFrame implements View {

	public GameMapModel gameMapModel;
	public PlayerModel playerModel;

	public JPanel welcomePanel;
	public JPanel graphicPanel;

	public JLabel welcomeLabel;
	public JLabel noOfTroopsLabel;

	public JComboBox<Integer> numOfTroopsComboBox;
	public JButton addButton;
	public JLabel listOfCountriesLabel;

	public JLabel countryListLabel;
	public JComboBox<Object> countryListComboBox;
	public Object[] countryListArray;
	private CountryViewRenderer countriesViewRenderer;

	public JButton[] button;
	public JButton button2;
	public JButton button3;

	public StartUpView(GameMapModel gameMapModel, PlayerModel playerModel) {

		this.setTitle("Startup Phase");
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
		
		updateWindow(gameMapModel, playerModel);
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

		this.welcomeLabel = new JLabel("It's Player X's turn");
		welcomeLabel.setBounds(1300, 80, 300, 25);
		welcomeLabel.setFont(largeFont);
		welcomePanel.add(welcomeLabel);

		this.noOfTroopsLabel = new JLabel("Number of Troops :");
		noOfTroopsLabel.setBounds(1300, 140, 150, 25);
		welcomePanel.add(noOfTroopsLabel);

		Integer[] troops = new Integer[this.playerModel.getmyTroop()];
		for (int i = 0; i < this.playerModel.getmyTroop(); i++) {
			troops[i] = i+1;
		}

		numOfTroopsComboBox = new JComboBox(troops);
		numOfTroopsComboBox.setBounds(1300, 170, 150, 25);
		welcomePanel.add(numOfTroopsComboBox);

		this.countryListLabel = new JLabel("Select Country :");
		countryListLabel.setBounds(1300, 230, 150, 25);
		welcomePanel.add(this.countryListLabel);

		countriesViewRenderer = new CountryViewRenderer();
		countryListArray = this.gameMapModel.getCountries().toArray();
		countryListComboBox = new JComboBox(countryListArray);
		welcomePanel.add(this.countryListComboBox);

		if (countryListArray.length > 0) {
			countryListComboBox.setRenderer(countriesViewRenderer);
		}
		countryListComboBox.setBounds(1300, 260, 150, 25);
		welcomePanel.add(countryListComboBox);

		this.addButton = new JButton("Add");
		this.addButton.setBounds(1300, 300, 150, 25);
		welcomePanel.add(this.addButton);

		int n = this.gameMapModel.getCountries().size();
		button = new JButton[n];
		for (int i = 0; i < n; i++) {
			button[i] = new JButton(this.gameMapModel.getCountries().get(i).getCountryName().substring(0, 3));
			button[i].setBounds(this.gameMapModel.getCountries().get(i).getXPosition() * 2,
					this.gameMapModel.getCountries().get(i).getYPosition() * 2, 50, 50);

			graphicPanel.add(button[i]);
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
			if(this.gameMapModel.getCountries().get(k)
					.getLinkedCountries()!=null) {
			ArrayList<CountryModel> neighbourCountries = (ArrayList<CountryModel>) this.gameMapModel.getCountries().get(k)
					.getLinkedCountries();

			for (int j = 0; j < neighbourCountries.size(); j++) {
				for (int i = 0; i < this.gameMapModel.getCountries().size(); i++)
					if (neighbourCountries.get(j).equals(this.gameMapModel.getCountries().get(i)))
						g2.drawLine(connectorPoints[i].x+25, connectorPoints[i].y+25, connectorPoints[k].x+25,
								connectorPoints[k].y+25);

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
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.addButton.addActionListener(actionListener);
	}

}