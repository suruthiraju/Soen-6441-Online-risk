package app.view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import app.helper.View;

/**
 * The Class GameModeView
 *
 * @author team 35
 */

public class GameModeView extends JFrame implements View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The single mode. */
	public JButton singleMode;
	
	/** The single mode. */
	public JLabel gameModeLabel;
	
	/** The tournament mode. */
	public JButton tournamentMode;

	/**
	 * Create the application.
	 */
	public GameModeView() {
		Font mediumFont = new Font("Serif", Font.BOLD, 25);
		this.setTitle("Select Game Mode");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100, 100);
		this.setSize(1000, 840);
		getContentPane().setLayout(null);

		gameModeLabel = new JLabel("Select the Mode to play Game");
		gameModeLabel.setBounds(150, 150, 400, 100);
		gameModeLabel.setFont(mediumFont);
		getContentPane().add(gameModeLabel);

		singleMode = new JButton("Single game mode");
		singleMode.setBounds(153, 300, 150, 40);
		getContentPane().add(singleMode);

		tournamentMode = new JButton("Tournament Mode");
		tournamentMode.setBounds(350, 300, 150, 40);
		getContentPane().add(tournamentMode);

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Set the Action Listener
	 * 
	 * @see app.helper.View#setActionListener(java.awt.event.ActionListener)
	 */
	@Override
	public void setActionListener(ActionListener actionListener) {
		singleMode.addActionListener(actionListener);
		tournamentMode.addActionListener(actionListener);

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