package app.view;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class CreateMapView extends JFrame{
	
	//creating all the components of the current view
	private JTextField enterContinent = new JTextField(20);
	private JButton addContinent = new JButton("Push to the list");
	private DefaultListModel modelContinent = new DefaultListModel<>();
	private JList currentContinentList= new JList(modelContinent);
	private JButton removeContinent = new JButton("Pull from the list");
	private JButton finalizeContinent = new JButton("Finalize the list");
	
	private JTextField enterCountry = new JTextField(20);
	private JButton addCountry = new JButton("Push to the list");
	private DefaultListModel modelCountry = new DefaultListModel<>();
	private JList currentCountryList= new JList(modelCountry);
	private JButton removeCountry = new JButton("Pull from the list");
	private JButton finalizeCountry = new JButton("Finalize the list");
	
	CreateMapView()
	{
		//initialization of the panel and all the components 
		JPanel mapPanel= new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		mapPanel.add(enterContinent);
		enterContinent.setLocation(250, 20);
		mapPanel.add(addContinent);
		addContinent.setLocation(250, 40);
		mapPanel.add(currentContinentList);
		currentContinentList.setLocation(300, 20);
		mapPanel.add(removeContinent);
		removeContinent.setLocation(300, 80);
		mapPanel.add(finalizeContinent);
		finalizeContinent.setLocation(350, 80);
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
		mapPanel.add(finalizeCountry);
		finalizeContinent.setLocation(350, 160);
		JScrollPane jScrollCountry = new JScrollPane(currentCountryList);
		currentCountryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
		
		this.add(mapPanel);		
		
	}
	public String getContinentName()
	{
		String tempStore = this.enterContinent.getText();
		return tempStore;
	}
	
	public String getCountryName()
	{
		String tempStore = this.enterCountry.getText();
		return tempStore;
	}
	
	public void setContinentName()
	{
		
	}
	
	public void addContinentListner(ActionListener listenAddContinent)
	{
		addContinent.addActionListener(listenAddContinent);
		
	}
	
	public void removeContinentListner(ActionListener listenRemoveContinent)
	{
		removeContinent.addActionListener(listenRemoveContinent);
		
	}
	public void addCountryListner(ActionListener listenAddCountry)
	{
		addCountry.addActionListener(listenAddCountry);
		
	}
	public void removeCountryListner(ActionListener listenRemoveCountry)
	{
		removeContinent.addActionListener(listenRemoveCountry);
		
	}
	public void finalizeContinentListner(ActionListener listenFinalizeContinent)
	{
		finalizeContinent.addActionListener(listenFinalizeContinent);
		
	}
	public void finalizeCountryListner(ActionListener listenFinalizeCountry)
	{
		finalizeCountry.addActionListener(listenFinalizeCountry);
		
	}
}