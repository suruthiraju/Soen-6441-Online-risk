package app.view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.helper.View;
import app.model.ContinentsModel;

public class CreateContinentView extends JFrame implements View {

	public JLabel welcomeLabel;
	public JLabel continentListText;
	public JComboBox continentList;
	public Object[] continentListArray;
	public JLabel controlValueText;
	public JTextField controlValue;
	public JButton saveButton;
	public JButton addButton;

	public CreateContinentView(List<ContinentsModel> listOfContinents) {
		
		welcomeLabel = new JLabel("Please select the Continents you want in the map and the control value");
		
		continentListText = new JLabel("Continent");
		continentList = new JComboBox<>();
		
		controlValueText = new JLabel("Control Value");
		controlValue = new JTextField();
		
		saveButton = new JButton("Save");
		addButton = new JButton("add");
		
		JPanel welcomePanel = new JPanel();

		this.setName("RISK GAME");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(300, 200);
		this.setSize(800, 700);
		this.setResizable(false);
		this.setVisible(false);
		welcomePanel.setLayout(null);

		this.add(welcomePanel);

		Font largeFont = new Font("Serif", Font.BOLD, 18);
		Font mediumFont = new Font("Serif", Font.BOLD, 14);
		Font smallFont = new Font("Serif", Font.BOLD, 12);

		welcomeLabel.setFont(largeFont);
		welcomePanel.add(welcomeLabel);
		welcomeLabel.setBounds(100, 0, 600, 200);

		continentListText.setFont(mediumFont);
		welcomePanel.add(continentListText);
		continentListText.setBounds(100, 50, 500, 200);
		
		controlValueText.setFont(mediumFont);
		welcomePanel.add(controlValueText);
		controlValueText.setBounds(100, 150, 200, 100);
		
		continentList.setFont(mediumFont);
		welcomePanel.add(continentList);
		continentList.setBounds(200, 140, 100, 20);
		continentList.setModel(new DefaultComboBoxModel(listOfContinents.toArray()));
		
		controlValue.setFont(mediumFont);
		welcomePanel.add(controlValue);
		controlValue.setBounds(200, 195, 100, 20);

		addButton.setFont(smallFont);
		welcomePanel.add(addButton);
		addButton.setBounds(100, 250, 100, 20);
		
		saveButton.setFont(smallFont);
		welcomePanel.add(saveButton);
		saveButton.setBounds(200, 250, 100, 20);


	}

	@Override
	public void setActionListener(ActionListener actionListener) {

	}

}
