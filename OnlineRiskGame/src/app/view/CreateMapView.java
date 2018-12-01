package app.view;

import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import app.helper.View;

/**
 * "CreateMapView" class represents an object of map view Properties are
 * containing labels, text fields, buttons, a pane, and a panel.
 *
 * @author GROUP-35
 */
public class CreateMapView extends JFrame implements View {

	/** The enter continent. */
	private JTextField enterContinent = new JTextField(20);

	/** The add continent. */
	private JButton addContinent = new JButton("Push to the list");

	/** The model continent. */
	private DefaultListModel modelContinent = new DefaultListModel<>();

	/** The current continent list. */
	private JList currentContinentList = new JList(modelContinent);

	/** The remove continent. */
	private JButton removeContinent = new JButton("Pull from the list");

	/** The finalize continents. */
	private JButton finalizeContinents = new JButton("Finalize the list");

	/** The enter country. */
	private JTextField enterCountry = new JTextField(20);

	/** The add country. */
	private JButton addCountry = new JButton("Push to the list");

	/** The model country. */
	private DefaultListModel modelCountry = new DefaultListModel<>();

	/** The current country list. */
	private JList currentCountryList = new JList(modelCountry);

	/** The remove country. */
	private JButton removeCountry = new JButton("Pull from the list");

	/** The finalize countries. */
	private JButton finalizeCountries = new JButton("Finalize the list");

	/**
	 * Constructor of CreateMapView.
	 */
	public CreateMapView() {
		// initialization of the panel and all the components
		JPanel mapPanel = new JPanel();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		mapPanel.add(enterContinent);
		enterContinent.setLocation(250, 20);
		mapPanel.add(addContinent);
		addContinent.setLocation(250, 40);
		mapPanel.add(currentContinentList);
		currentContinentList.setLocation(300, 20);
		mapPanel.add(removeContinent);
		removeContinent.setLocation(300, 80);
		mapPanel.add(finalizeContinents);
		finalizeContinents.setLocation(350, 80);
		JScrollPane jScrollContinent = new JScrollPane(currentContinentList);
		currentContinentList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		mapPanel.add(enterCountry);
		enterCountry.setLocation(250, 100);
		mapPanel.add(addCountry);
		addCountry.setLocation(250, 120);
		mapPanel.add(currentCountryList);
		currentCountryList.setLocation(300, 100);
		mapPanel.add(removeCountry);
		removeContinent.setLocation(300, 160);
		mapPanel.add(finalizeCountries);
		finalizeContinents.setLocation(350, 160);
		JScrollPane jScrollCountry = new JScrollPane(currentCountryList);
		currentCountryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		enterContinent.setEditable(false);
		addCountry.setEnabled(false);
		currentCountryList.setEnabled(false);
		removeCountry.setEnabled(false);
		finalizeCountries.setEnabled(false);

		this.add(mapPanel);

	}

	/**
	 * Gets the continent name.
	 *
	 * @return continent name entered by user
	 */
	public String getContinentName() {
		String tempStore = this.enterContinent.getText();
		return tempStore;
	}

	/**
	 * Gets the country name.
	 *
	 * @return country name entered by user
	 */
	public String getCountryName() {
		String tempStore = this.enterCountry.getText();
		return tempStore;
	}

	/**
	 * Adds entered "parmContinent" continents to the existing list.
	 *
	 * @param parmContinent the new continents list
	 */
	public void setContinentsList(String parmContinent[]) {
		for (int counter = 0; counter < parmContinent.length; counter++) {
			modelContinent.addElement(parmContinent[counter]);
		}
		currentContinentList = new JList(modelContinent);

	}

	/**
	 * Adds entered "parmCountry" countries to the existing list.
	 *
	 * @param parmCountry the new countries list
	 */
	public void setCountriesList(String parmCountry[]) {
		for (int counter = 0; counter < parmCountry.length; counter++) {
			modelCountry.addElement(parmCountry[counter]);
		}
		currentCountryList = new JList(modelCountry);

	}

	/**
	 * Sets action to "addContinent" button.
	 *
	 * @param listenAddContinent the listen add continent
	 */
	public void addContinentListner(ActionListener listenAddContinent) {
		addContinent.addActionListener(listenAddContinent);

	}

	/**
	 * Select continent listner.
	 *
	 * @return tempIndex, index of selected continent
	 */
	public int selectContinentListner() {
		int tempIndex = currentContinentList.getSelectedIndex();
		return tempIndex;
	}

	/**
	 * Select country listner.
	 *
	 * @return tempIndex, index of selected country
	 */
	public int selectCountryListner() {
		int tempIndex = currentCountryList.getSelectedIndex();
		return tempIndex;
	}

	/**
	 * Sets action to "removeContinent" button.
	 *
	 * @param listenRemoveContinent the listen remove continent
	 */
	public void removeContinentListner(ActionListener listenRemoveContinent) {
		removeContinent.addActionListener(listenRemoveContinent);
	}

	/**
	 * Sets action to "addCountry" button.
	 *
	 * @param listenAddCountry the listen add country
	 */
	public void addCountryListner(ActionListener listenAddCountry) {
		addCountry.addActionListener(listenAddCountry);
	}

	/**
	 * Sets action to "removeCountry" button.
	 *
	 * @param listenRemoveCountry the listen remove country
	 */
	public void removeCountryListner(ActionListener listenRemoveCountry) {
		removeContinent.addActionListener(listenRemoveCountry);
	}

	/**
	 * Finalizes the continents added to map by "finalizeContinent" button.
	 *
	 * @param listenfinalizeContinents the listenfinalize continents
	 */
	public void finalizeContinentsListner(ActionListener listenfinalizeContinents) {
		finalizeContinents.addActionListener(listenfinalizeContinents);
	}

	/**
	 * Finalizes the countries added to map by "finalizeCountry" button.
	 *
	 * @param listenfinalizeCountries the listenfinalize countries
	 */
	public void finalizeCountriesListner(ActionListener listenfinalizeCountries) {
		finalizeCountries.addActionListener(listenfinalizeCountries);
	}

	/**
	 * Sets the action listener.
	 *
	 * @param actionListener the new action listener
	 * @see app.helper.View#setActionListener(java.awt.event.ActionListener)
	 */
	@Override
	public void setActionListener(ActionListener actionListener) {

	}

	/**
	 * Update.
	 *
	 * @param o   the o
	 * @param arg the arg
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}