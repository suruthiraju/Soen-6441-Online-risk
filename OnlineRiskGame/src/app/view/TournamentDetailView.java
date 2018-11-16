package app.view;

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

public class TournamentDetailView extends JFrame implements View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The single mode. */
	public JButton singleMode;
	
	/** The single mode. */
	public JLabel gameModeLabel;
	

	/**
	 * Create the application.
	 */
	public TournamentDetailView() {
		this.setTitle("Tournament Mode");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100, 100);
		this.setSize(1000, 840);
		getContentPane().setLayout(null);

		gameModeLabel = new JLabel("Tournament Mode ");
		gameModeLabel.setBounds(53, 47, 311, 27);
		getContentPane().add(gameModeLabel);

		singleMode = new JButton("Exit");
		singleMode.setBounds(202, 337, 116, 25);
		getContentPane().add(singleMode);

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 800, 800);
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