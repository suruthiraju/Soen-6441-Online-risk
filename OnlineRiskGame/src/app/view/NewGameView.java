package app.view;

import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import app.helper.View;

import javax.swing.JComboBox;

/**
 * The Class NewGameView 
 *
 * @author Jatan Gohel
 */


public class NewGameView extends JFrame implements View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The browse map. */
	public JButton browseMapButton;
	
	/** The next button. */
	public JButton nextButton;
	
	/** The cancel button. */
	public JButton cancelButton;
	
	
	/** The choose map. */
	public JComboBox<Integer> numOfPlayers;

	public JFileChooser chooseMap;
	
	/** The label map file. */
	public JLabel labelPlayers, labelMapFile;
	
	/** The final players. */
	public int finalPlayers;
	
	/**
	 * Create the application.
	 */
	public NewGameView() {
		getContentPane().setLayout(null);
		
		labelPlayers = new JLabel("Number of Players?");
		labelPlayers.setBounds(53, 47, 311, 27);
		getContentPane().add(labelPlayers);
		
		Integer[] items = {2,3,4,5};
		numOfPlayers = new JComboBox<>(items);
		numOfPlayers.setBounds(202, 49, 116, 22);
		getContentPane().add(numOfPlayers);
		
		labelMapFile = new JLabel("Please Select Map File");
		labelMapFile.setBounds(53, 133, 157, 27);
		getContentPane().add(labelMapFile);
		
		browseMapButton = new JButton("Browse");
		browseMapButton.setBounds(202, 134, 116, 27);
		getContentPane().add(browseMapButton);
		
		nextButton = new JButton("Next");
		nextButton.setBounds(202, 237, 116, 25);
		getContentPane().add(nextButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(53, 237, 97, 25);
		getContentPane().add(cancelButton);
		
		
		chooseMap = new JFileChooser();
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	

	@Override
	public void setActionListener(ActionListener actionListener) {
		browseMapButton.addActionListener(actionListener);
		nextButton.addActionListener(actionListener);
		cancelButton.addActionListener(actionListener);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
