package app.view;

import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.helper.View;

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
	public JLabel labelPlayers, labelMapFile, PlayerText, PlayerText2 ;

	/** The final players. */
	public int finalPlayers;

	/** The final players. */
	public JTextField PlayerName5, PlayerName1, PlayerName2, PlayerName3, PlayerName4;

	/**
	 * Create the application.
	 */
	public NewGameView() {
		getContentPane().setLayout(null);

		labelPlayers = new JLabel("Number of Players?");
		labelPlayers.setBounds(53, 47, 311, 27);
		getContentPane().add(labelPlayers);

		Integer[] items = { 2, 3, 4, 5 };
		numOfPlayers = new JComboBox<>(items);
		numOfPlayers.setBounds(202, 49, 116, 22);
		getContentPane().add(numOfPlayers);
		
		PlayerText2 = new JLabel("Fill the names as per the number selected");
		PlayerText2.setBounds(53, 83, 311, 27);
		getContentPane().add(PlayerText2);
		
		PlayerText = new JLabel("Player 1 Name: ");
		PlayerText.setBounds(53, 110, 311, 27);
		getContentPane().add(PlayerText);

		PlayerName1 = new JTextField();
		PlayerName1.setBounds(202, 116, 100, 20);
		getContentPane().add(PlayerName1);
		
		PlayerText = new JLabel("Player 2 Name: ");
		PlayerText.setBounds(53, 140, 311, 27);
		getContentPane().add(PlayerText);
		
		PlayerName2 = new JTextField();
		PlayerName2.setBounds(202, 146, 100, 20);
		getContentPane().add(PlayerName2);

		PlayerText = new JLabel("Player 3 Name: ");
		PlayerText.setBounds(53, 170, 311, 27);
		getContentPane().add(PlayerText);
		
		PlayerName3 = new JTextField();
		PlayerName3.setBounds(202, 176, 100, 20);
		getContentPane().add(PlayerName3);
		
		PlayerText = new JLabel("Player 4 Name: ");
		PlayerText.setBounds(53, 200, 311, 27);
		getContentPane().add(PlayerText);
		
		PlayerName4 = new JTextField();
		PlayerName4.setBounds(202, 206, 100, 20);
		getContentPane().add(PlayerName4);
		
		PlayerText = new JLabel("Player 5 Name: ");
		PlayerText.setBounds(53, 230, 311, 27);
		getContentPane().add(PlayerText);
		
		PlayerName5 = new JTextField();
		PlayerName5.setBounds(202, 236, 100, 20);
		getContentPane().add(PlayerName5);

		labelMapFile = new JLabel("Please Select Map File");
		labelMapFile.setBounds(53, 280, 157, 27);
		getContentPane().add(labelMapFile);

		browseMapButton = new JButton("Browse");
		browseMapButton.setBounds(202, 279, 116, 27);
		getContentPane().add(browseMapButton);

		nextButton = new JButton("Next");
		nextButton.setBounds(202, 337, 116, 25);
		getContentPane().add(nextButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(53, 337, 97, 25);
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

	/**
	 * Set the Action Listener
	 * 
	 * @see app.helper.View#setActionListener(java.awt.event.ActionListener)
	 */
	@Override
	public void setActionListener(ActionListener actionListener) {
		browseMapButton.addActionListener(actionListener);
		nextButton.addActionListener(actionListener);
		cancelButton.addActionListener(actionListener);

	}

	/**
	 * Update the view based on observer
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}