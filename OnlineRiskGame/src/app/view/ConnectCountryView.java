package app.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import app.helper.View;
import app.model.CountryModel;

/**
 * 
 * @author DELL
 *
 */


public class ConnectCountryView extends JFrame implements View {

	public JPanel welcomePanel;
	public JButton saveButton;
	public JButton addButton;
	public JLabel welcomeLabel;
	public JLabel countryListLabelLeft;
	public JLabel countryListLabelRight;
	public JList countryParentListRight;
	public List<CountryModel> leftCountryList;
	public List<CountryModel> rightCountryList;

	public ConnectCountryView(List<CountryModel> countryList) {
		welcomeLabel = new JLabel("Please select the Continents you want in the map and the control value");
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
		this.updateWindow(countryList);

	}

	private void updateWindow(List<CountryModel> countryList) {
		welcomePanel.removeAll();

		Font largeFont = new Font("Serif", Font.BOLD, 18);
		Font mediumFont = new Font("Serif", Font.BOLD, 14);
		Font smallFont = new Font("Serif", Font.BOLD, 12);

		countryListLabelLeft = new JLabel("Country 1");
		countryListLabelLeft.setBounds(40, 150, 100, 25);
		welcomePanel.add(countryListLabelLeft);

		countryListLabelRight = new JLabel("Country 2");
		countryListLabelRight.setBounds(40, 300, 100, 25);
		welcomePanel.add(countryListLabelRight);
		
		// left panel
		this.leftCountryList = countryList;

		CountryModel[] countryModelArray = new CountryModel[this.leftCountryList.size()];
		for (int i = 0; i < this.leftCountryList.size(); i++) {
			countryModelArray[i] = this.leftCountryList.get(i);
		}

		countryParentListRight = new JList<CountryModel>();
		if (countryModelArray.length > 0) {
			countryParentListRight.setListData(countryModelArray);
			countryParentListRight.setCellRenderer(new CountryModelRenderer());
		}
		countryParentListRight.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane countryParentListPaneLeft = new JScrollPane(countryParentListRight);
		countryParentListPaneLeft.setBounds(50, 100, 150, 150);
		welcomePanel.add(countryParentListPaneLeft);

		// Right panel
		this.rightCountryList = countryList;

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
		JScrollPane countryParentListPaneRight = new JScrollPane(countryParentListRight);
		countryParentListPaneRight.setBounds(50, 300, 150, 150);
		welcomePanel.add(countryParentListPaneRight);

		addButton.setFont(smallFont);
		welcomePanel.add(addButton);
		addButton.setBounds(100, 250, 100, 20);

		saveButton.setFont(smallFont);
		welcomePanel.add(saveButton);
		saveButton.setBounds(200, 250, 100, 20);
		
		

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// Auto generated code

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.saveButton.addActionListener(actionListener);
		this.addButton.addActionListener(actionListener);
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
