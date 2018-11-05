package app.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

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

import app.helper.View;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;

/**
 * This View provides the attackView of the Game Play. It also provides
 * the observer pattern when the data is modified
 * 
 * @author Suruthi Raju
 * @version 1.0.0
 *
 */

public class AttackView extends JFrame implements View {

	public GameMapModel gameMapModel;
	public PlayerModel playerModel;
	public GamePlayModel gamePlayModel;

	public JPanel welcomePanel;
	public JPanel graphicPanel;

	public JLabel welcomeLabel;
	public JLabel attackCountryListLabel;
	public JLabel defendCountryListLabel;
	public JLabel noOfTroopsLabel;
	public JLabel noOfDiceAttackLabel;
	public JLabel noOfDiceDefendLabel;
	public JButton nextButton;
	public JButton SingleButton;
	public JButton alloutButton;
	public JComboBox<Object> attackCountryListComboBox;
	public JComboBox<Object> defendCountryListComboBox;
	public Object[] attackCountryListArray;
	public Object[] defendCountryListArray;
	private CountryViewRenderer countriesViewRenderer;
	public JButton[] button;
	public JComboBox<Integer> numOfDiceAttackComboBox;
	public JComboBox<Integer> numOfDiceDefendComboBox;

	public AttackView(GamePlayModel gamePlayModel) {
		this.gameMapModel = gamePlayModel.getGameMap();
		this.setTitle("Attack Phase");
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

		this.nextButton = new JButton("Next");
		this.SingleButton = new JButton("Single attack");
		this.alloutButton = new JButton("All Out");
		this.add(welcomePanel);

		this.playerModel = this.gameMapModel.getPlayerTurn();
		this.welcomeLabel = new JLabel("It's " + playerModel.getNamePlayer() + "'s turn");

		welcomePanel.setLayout(null);
		graphicPanel.setLayout(null);

		updateWindow(gamePlayModel, playerModel);
	}

	/**
	 * This updateWindow method is called whenever the model is updated. It updates
	 * the Screen for attack Phase
	 * 
	 * @param gamePlayModel
	 * @param playerModel
	 */
	public void updateWindow(GamePlayModel gamePlayModel, PlayerModel playerModel) {

		Font largeFont = new Font("Serif", Font.BOLD, 18);
		Font mediumFont = new Font("Serif", Font.BOLD, 14);
		Font smallFont = new Font("Serif", Font.BOLD, 12);

		this.gameMapModel = gamePlayModel.getGameMap();
		this.playerModel = this.gameMapModel.getPlayerTurn();

		welcomeLabel.setBounds(1300, 80, 300, 25);
		welcomeLabel.setFont(largeFont);
		welcomePanel.add(welcomeLabel);

		this.attackCountryListLabel = new JLabel("Select attack country:");
		attackCountryListLabel.setBounds(1300, 140, 150, 25);
		welcomePanel.add(attackCountryListLabel);

		// attack country comboBox
		ArrayList<CountryModel> attackListOfCountries = new ArrayList<CountryModel>();
		for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
			if (playerModel.getNamePlayer()
					.equals(this.gameMapModel.getCountries().get(i).getRulerName())) {
				if(this.gameMapModel.getCountries().get(i).getArmies() > 1) {
					attackListOfCountries.add(this.gameMapModel.getCountries().get(i));
				}
			}
		}

		countriesViewRenderer = new CountryViewRenderer();
		attackCountryListArray = attackListOfCountries.toArray();
		attackCountryListComboBox = new JComboBox(attackCountryListArray);
		attackCountryListComboBox.setSelectedIndex(gamePlayModel.getSelectedAttackComboBoxIndex());
		welcomePanel.add(this.attackCountryListComboBox);

		if (attackCountryListArray.length > 0) {
			attackCountryListComboBox.setRenderer(countriesViewRenderer);
		}
		attackCountryListComboBox.setBounds(1300, 180, 150, 25);
		
		this.noOfDiceAttackLabel = new JLabel("Number of Dice to Roll(for attack) :");
		noOfDiceAttackLabel.setBounds(1300, 215, 150, 35);
		welcomePanel.add(noOfDiceAttackLabel);

		CountryModel attackCountry = (CountryModel) this.attackCountryListComboBox.getSelectedItem();
		System.out.print("country name " + attackCountry.getCountryName() );
		Integer[] attackTroops = new Integer[3];
		if (attackCountry.getArmies() > 3) {
			for (int i = 0; i < 3; i++) {
				attackTroops[i] = i + 1;
				System.out.println("i " + i);
			}
		}else {		
			for (int i = 0; i < (attackCountry.getArmies() - 1); i++) {
				attackTroops[i] = i + 1;
				System.out.println("i " + i);
			}
		}
		
		numOfDiceAttackComboBox = new JComboBox(attackTroops);
		numOfDiceAttackComboBox.setBounds(1300, 250, 150, 25);
		welcomePanel.add(numOfDiceAttackComboBox);
		

		this.defendCountryListLabel = new JLabel("Select defend Country :");
		defendCountryListLabel.setBounds(1300, 280, 150, 25);
		welcomePanel.add(this.defendCountryListLabel);
		
		
		// defend country comboBox
		ArrayList<CountryModel> defendListOfCountries = new ArrayList<CountryModel>();
		defendListOfCountries = gamePlayModel.getDefendCountryList(attackCountry );

		countriesViewRenderer = new CountryViewRenderer();
		defendCountryListArray = defendListOfCountries.toArray();
		defendCountryListComboBox = new JComboBox(defendCountryListArray);
		defendCountryListComboBox.setSelectedIndex(gamePlayModel.getSelectedDefendComboBoxIndex());
		welcomePanel.add(this.defendCountryListComboBox);

		if (defendCountryListArray.length > 0) {
			defendCountryListComboBox.setRenderer(countriesViewRenderer);
		}
		defendCountryListComboBox.setBounds(1300, 310, 150, 25);
		
		this.noOfDiceDefendLabel = new JLabel("Number of Dice to Roll(for defend) :");
		noOfDiceDefendLabel.setBounds(1300, 350, 150, 45);
		welcomePanel.add(noOfDiceDefendLabel);

		CountryModel defendCountry = (CountryModel) this.defendCountryListComboBox.getSelectedItem();
		Integer[] defendTroops = new Integer[2];
		if (defendCountry.getArmies() > 2) {
			for (int i = 0; i < 2; i++) {
				defendTroops[i] = i + 1;
				System.out.println("i " + i);
			}
		}else {		
			for (int i = 0; i < defendCountry.getArmies(); i++) {
				defendTroops[i] = i + 1;
				System.out.println("i " + i);
			}
		}
		
		numOfDiceDefendComboBox = new JComboBox(defendTroops);
		numOfDiceDefendComboBox.setBounds(1300, 380, 150, 25);
		welcomePanel.add(numOfDiceDefendComboBox);
		
		
		this.SingleButton.setBounds(1210, 450, 150, 25);
		welcomePanel.add(this.SingleButton);
		
		this.alloutButton.setBounds(1375, 450, 150, 25);
		welcomePanel.add(this.alloutButton);
		
		this.nextButton.setBounds(1300, 600, 150, 25);
		welcomePanel.add(this.nextButton);

		int n = this.gameMapModel.getCountries().size();
		button = new JButton[n];

		PlayerModel pm = new PlayerModel();
		CountryModel cm = new CountryModel();

		for (int i = 0; i < gameMapModel.getCountries().size(); i++) {

			button[i] = new JButton();
			button[i].setText(gameMapModel.getCountries().get(i).getCountryName().substring(0, 3));
			button[i].setBackground(gameMapModel.getCountries().get(i).getBackgroundColor());
			button[i].setToolTipText("Troops: " + gameMapModel.getCountries().get(i).getArmies());
			cm = gameMapModel.getCountries().get(i);
			pm = gamePlayModel.getPlayer(cm);
			Border border = BorderFactory.createLineBorder(pm.getColor(), 3);

			button[i].setBorder(border);
			button[i].setOpaque(true);
			button[i].setBounds(gameMapModel.getCountries().get(i).getXPosition() * 2,
					gameMapModel.getCountries().get(i).getYPosition() * 2, 50, 50);

			graphicPanel.add(button[i]);
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

		welcomePanel.removeAll();
		graphicPanel.removeAll();
		if (obs instanceof GameMapModel) {
			this.gameMapModel = (GameMapModel) obs;
		} else if (obs instanceof PlayerModel) {
			this.playerModel = (PlayerModel) obs;
		} else if (obs instanceof GamePlayModel) {
			this.gamePlayModel = (GamePlayModel) obs;
		}
		this.updateWindow(this.gamePlayModel, this.playerModel);
		this.revalidate();
		this.repaint();

	}

	/**
	 * This is actionListener method to listen the action events in the screen
	 * 
	 * @see app.helper.View#setActionListener(java.awt.event.ActionListener)
	 */
	public void setActionListener(ActionListener actionListener) {
		this.nextButton.addActionListener(actionListener);
		this.SingleButton.addActionListener(actionListener);
		this.alloutButton.addActionListener(actionListener);
		this.defendCountryListComboBox.addActionListener(actionListener);
		this.attackCountryListComboBox.addActionListener(actionListener);
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
	public void setItemListener(ItemListener itemListener) {
		this.attackCountryListComboBox.addItemListener(itemListener);
		this.defendCountryListComboBox.addItemListener(itemListener);

	}

}