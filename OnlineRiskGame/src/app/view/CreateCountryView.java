package app.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import app.helper.View;
import app.model.ContinentsModel;

/**
 * 
 * @author Jatan Gohel
 *
 */

public class CreateCountryView extends JFrame implements View {

	public JLabel welcomeLabel;
	public JTextField continentValue;
	public JComboBox listOfContinents;
	public JLabel countryListText;
	public JTextArea observerList;
	public JButton nextButton;
	public JButton addButton;
	public JButton saveButton;
	public JTextArea consoleTextArea;
	public JTextArea consoleMainPanel;
	public JScrollPane consolePanel;
	public JPanel mainPanel;
	JTextArea textArea;
	
	public CreateCountryView(List<ContinentsModel> listOfContinents) {

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
		this.updateScreen();
	}

	public class ContinentViewRenderer extends BasicComboBoxRenderer {

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

			ContinentsModel map_model = (ContinentsModel) value;
			if (map_model != null)
				setText(map_model.getContinentName());

			return this;
		}
	}
	
	public CreateCountryView() {
	
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		textArea =  new JTextArea("Default text",5,5);

		welcomeLabel = new JLabel("Please add the Countries in the Continents you created:");
		welcomeLabel.setBounds(100, 0, 600, 100);

		countryListText = new JLabel("Country Name: ");
		countryListText.setBounds(100, 100, 200, 40);

		continentValue = new JTextField();
		continentValue.setBounds(200, 100, 200, 40);	

		addButton = new JButton("Add");
		addButton.setBounds(100, 300, 100, 40);

		nextButton = new JButton("Next");
		nextButton.setBounds(200, 300, 100, 40);
		
		updateScreen();

	}

	public void updateScreen() {
		
		
		
		textArea.setText("// update it using observer");
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		mainPanel.add(textArea);
		textArea.setBorder(new TitledBorder(new LineBorder(Color.black, 5),
		        "Continents added list:"));
		textArea.setBounds(520, 0, 260, 650);
		
		
		
		Color main = new Color(230, 230, 255);
		Color secondary = new Color(0, 0, 26);
		textArea.setBackground(main);  //sets the background color
		textArea.setForeground(secondary);

		this.add(mainPanel);
		mainPanel.add(welcomeLabel);
		mainPanel.add(addButton);
		mainPanel.add(nextButton);
		mainPanel.add(listOfContinents);
		mainPanel.add(countryListText);
		mainPanel.add(continentValue);
		mainPanel.add(countryListText);

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.addButton.addActionListener(actionListener);
		this.nextButton.addActionListener(actionListener);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.updateScreen();
	}

}