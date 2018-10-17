package app.view;

import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.helper.View;

/**
 * 
 * @author Jatan Gohel
 *
 */


public class CreateContinentView extends JFrame implements View {
	
	public JLabel welcomeLabel;
	public JTextField continentValue;
	public JTextField controlValue;
	public JLabel continentListText;
	public JLabel controlValueText;
	public JTextArea observerList;
	public JButton nextButton;
	public JButton addButton;
	public JTextArea consoleTextArea;
	public JTextArea consoleMainPanel;
	public JScrollPane consolePanel;
	public JPanel mainPanel;

	
    public CreateContinentView()
    {
    	this.setName("RISK GAME");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(300, 200);
		this.setSize(800, 700);
		this.setResizable(false);
		this.setVisible(false);
		
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
    	
    	welcomeLabel = new JLabel("Please name the Continents you want in the map and their control values");
    	welcomeLabel.setBounds(100, 0, 600, 100);
    	
		continentListText = new JLabel("Continent Name: ");
		continentListText.setBounds(100, 100, 200, 40);
		
		continentValue = new JTextField();
		continentValue.setBounds(200, 100, 200, 40);
		
		controlValueText = new JLabel("Control Value: ");
		controlValueText.setBounds(100, 200, 200, 40);
		
		controlValue = new JTextField();
    	controlValue.setBounds(200, 200, 200, 40);
    	
    	addButton = new JButton("Add");
    	addButton.setBounds(100, 300, 100, 40);
    	
    	nextButton = new JButton("Next");
    	nextButton.setBounds(200, 300, 100, 40);
    
        
        updateScreen();
               
    }


	public void updateScreen() {
		this.add(mainPanel);
		mainPanel.add(welcomeLabel);
		mainPanel.add(addButton);
		mainPanel.add(nextButton);
        mainPanel.add(continentListText);
        mainPanel.add(continentValue);
        mainPanel.add(controlValue);
        mainPanel.add(controlValueText);
        
//		this.consoleTextArea = new JTextArea("Please name the Continents you want in the map and their control value\n", 10, 500);
//    	this.consoleTextArea.setEditable(false);
//    	this.consoleTextArea.setFocusable(false);
//    	this.consoleTextArea.setVisible(true);
//    	this.consoleTextArea.setForeground(Color.WHITE);
//    	this.consoleTextArea.setBackground(Color.BLACK);
//    	DefaultCaret caret = (DefaultCaret) this.consoleTextArea.getCaret();
//    	caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
//
//    	this.consolePanel = new JScrollPane(this.consoleTextArea);
//    	this.consolePanel.setPreferredSize(new Dimension(950, 130));

    	//this.consoleMainPanel.add(this.consolePanel);
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