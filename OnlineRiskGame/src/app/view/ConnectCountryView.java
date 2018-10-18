package app.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;

import app.helper.View;
import app.model.CountryModel;
import app.model.GameMapModel;

public class ConnectCountryView extends JFrame implements View,Observer {

	public JPanel welcomePanel;
	public JPanel graphicPanel;
	public JButton saveButton;
	public JButton addButton;
	public JButton removeButton;
	public JLabel welcomeLabel;
	public JLabel countryListLabelLeft;
	public JLabel countryListLabelRight;
	public JList countryParentListRight;
	public JList countryParentListLeft;
	public ListSelectionModel listSelectionModelLeft;
	public ListSelectionModel listSelectionModelRight;
	public List<CountryModel> leftCountryList;
	public List<CountryModel> rightCountryList;
	public GameMapModel gameMapModel;
	
//	public CountryModel[] countryButton;

	public ConnectCountryView(GameMapModel gameMapModel) {
		this.gameMapModel = gameMapModel;
		welcomeLabel = new JLabel("Please select the Continents you want in the map and the control value");
		
		saveButton = new JButton("Save");
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");

		welcomePanel = new JPanel();
		graphicPanel = new JPanel();
		getContentPane().add(graphicPanel);
		graphicPanel.setSize(1200, 800);
		graphicPanel.setBackground(Color.WHITE);
		graphicPanel.setLayout(null);
		//setSize(1000, 1000);
		
		
		
		
		this.setName("RISK GAME");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(300, 200);
		this.setSize(1400, 800);
		this.setResizable(false);
		this.setVisible(false);
		welcomePanel.setLayout(null);
		this.add(welcomePanel);
		this.updateWindow(gameMapModel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void updateWindow(GameMapModel gmm) {
		welcomePanel.removeAll();
		graphicPanel.removeAll();
		Font largeFont = new Font("Serif", Font.BOLD, 18);
		Font mediumFont = new Font("Serif", Font.BOLD, 14);
		Font smallFont = new Font("Serif", Font.BOLD, 12);

		countryListLabelLeft = new JLabel("Country 1");
		countryListLabelLeft.setBounds(1200, 80, 100, 25);
		welcomePanel.add(countryListLabelLeft);

		countryListLabelRight = new JLabel("Country 2");
		countryListLabelRight.setBounds(1200, 280, 100, 25);
		welcomePanel.add(countryListLabelRight);
		
		// left panel
		this.leftCountryList = gmm.getCountries();
		CountryModel[] countryModelArrayLeft = new CountryModel[this.leftCountryList.size()];
		for (int i = 0; i < this.leftCountryList.size(); i++) {
			countryModelArrayLeft[i] = this.leftCountryList.get(i);
		}
		
		countryParentListLeft = new JList<CountryModel>();
		if (countryModelArrayLeft.length > 0) {
			countryParentListLeft.setListData(countryModelArrayLeft);
			countryParentListLeft.setCellRenderer(new CountryModelRenderer());
			
					}		
		
		countryParentListLeft.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane countryParentListPaneLeft = new JScrollPane(countryParentListLeft);
		
		this.listSelectionModelLeft = countryParentListLeft.getSelectionModel();
		countryParentListLeft.setSelectedIndex(this.gameMapModel.getLeftModelIndex());
		countryParentListPaneLeft.setBounds(1200, 100, 150, 150);
		
		welcomePanel.add(countryParentListPaneLeft);

		// Right panel
		this.rightCountryList = gmm.getCountries();

		CountryModel[] countryModelArrayRight = new CountryModel[this.rightCountryList.size()];
		for (int i = 0; i < this.rightCountryList.size(); i++) {
			countryModelArrayRight[i] = this.rightCountryList.get(i);
		}

		countryParentListRight = new JList<CountryModel>();
		if (countryModelArrayRight.length > 0) {
			countryParentListRight.setListData(countryModelArrayRight);
			countryParentListRight.setCellRenderer(new CountryModelRenderer());
			
		}
		countryParentListRight.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.listSelectionModelRight = countryParentListRight.getSelectionModel();
		JScrollPane countryParentListPaneRight = new JScrollPane(countryParentListRight);
		countryParentListRight.setSelectedIndex(this.gameMapModel.getRightModelIndex());
		countryParentListPaneRight.setBounds(1200, 300, 150, 150);
		welcomePanel.add(countryParentListPaneRight);
		

		addButton.setFont(smallFont);
		welcomePanel.add(addButton);
		addButton.setBounds(1200, 250, 100, 20);

		saveButton.setFont(smallFont);
		welcomePanel.add(saveButton);
		saveButton.setBounds(1200, 460, 100, 20);
		
		
		removeButton.setFont(smallFont);
		welcomePanel.add(removeButton);
		removeButton.setBounds(1300, 250, 100, 20);
		
		
		//countryButton = new CountryModel[this.leftCountryList.size()];
		
		for (int i = 0; i < this.leftCountryList.size(); i++) {
			CountryModel country = this.leftCountryList.get(i);
			
			country.setBackground(this.leftCountryList.get(i).getBackgroundColor());
			country.setText(this.leftCountryList.get(i).getCountryName());
			country.setBorderColor(this.leftCountryList.get(i).getBorderColor());
			//countryButton[i].setForeground(Color.GREEN);
			country.setOpaque(true);
			country.setBounds(this.leftCountryList.get(i).getXPosition()*2, this.leftCountryList.get(i).getYPosition()*2,
					50, 50);

			graphicPanel.add(country);
		}
		for (int i = 0; i < this.rightCountryList.size(); i++) {
			CountryModel country = this.rightCountryList.get(i);
			
			country.setBackground(this.rightCountryList.get(i).getBackgroundColor());
			country.setText(this.rightCountryList.get(i).getCountryName());
			country.setBorderColor(this.rightCountryList.get(i).getBorderColor());
			//countryButton[i].setForeground(Color.GREEN);
			country.setOpaque(true);
			country.setBounds(this.rightCountryList.get(i).getXPosition()*2, this.rightCountryList.get(i).getYPosition()*2,
					50, 50);

			graphicPanel.add(country);
		}
		graphicPanel.setLayout(null);
		
		
		
		

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

	@Override
	public void update(Observable gameMapModel, Object arg1) {
		
		this.updateWindow(((GameMapModel)gameMapModel));
		this.repaint();
		this.revalidate();

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.saveButton.addActionListener(actionListener);
		this.addButton.addActionListener(actionListener);
		this.removeButton.addActionListener(actionListener);
		
	}
	
	public void setListSelectionListener(ListSelectionListener listSelectionListener) {
		//this.countryParentListLeft.addListSelectionListener(listSelectionListener);
		this.listSelectionModelLeft.addListSelectionListener(listSelectionListener);
		this.listSelectionModelRight.addListSelectionListener(listSelectionListener);
		//this.countryParentListRight.addListSelectionListener(listSelectionListener);
	}

	class CountryModelRenderer extends JLabel implements ListCellRenderer<CountryModel> {

		private static final long serialVersionUID = 1L;

		public CountryModelRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends CountryModel> arg0, CountryModel arg1, int arg2,
				boolean arg3, boolean arg4) {

			if (arg1 != null) {
				setText(arg1.getCountryName());
			}

			if (arg3) {
				setBackground(new Color(0, 0, 128));
				setForeground(Color.white);
			} else {
				setBackground(Color.white);
				setForeground(Color.black);
			}

			return this;
		}
	}

}