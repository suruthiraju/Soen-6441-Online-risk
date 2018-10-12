package app.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

/**
 * The Class NewGameView 
 *
 * @author Jatan Gohel
 */


public class NewGameView extends JFrame implements InterfaceView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The browse map. */
	public JButton browse_map;
	
	/** The next button. */
	public JButton next_button;
	
	/** The cancel button. */
	public JButton cancel_button;
	
	
	/** The choose map. */
	public JComboBox<Integer> num_players;

	public JFileChooser choose_map;
	
	/** The label map file. */
	public JLabel label_players, label_map_file;
	
	/** The final players. */
	public int final_players;
	
	/**
	 * Create the application.
	 */
	public NewGameView() {
		getContentPane().setLayout(null);
		
		label_players = new JLabel("Number of Players?");
		label_players.setBounds(53, 47, 311, 27);
		getContentPane().add(label_players);
		
		Integer[] items = {2,3,4,5};
		num_players = new JComboBox<>(items);
		num_players.setBounds(202, 49, 116, 22);
		getContentPane().add(num_players);
		
		label_map_file = new JLabel("Please Select Map File");
		label_map_file.setBounds(53, 133, 157, 27);
		getContentPane().add(label_map_file);
		
		browse_map = new JButton("Browse");
		browse_map.setBounds(202, 134, 116, 27);
		getContentPane().add(browse_map);
		
		next_button = new JButton("Next");
		next_button.setBounds(202, 237, 116, 25);
		getContentPane().add(next_button);
		
		cancel_button = new JButton("Cancel");
		cancel_button.setBounds(53, 237, 97, 25);
		getContentPane().add(cancel_button);
		
		
		choose_map = new JFileChooser();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 881, 536);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	/**
	 * calls the setActionlistener
	 * @param actionListener
	 */
	
	public void setActionListener(ActionListener actionListener) {
		browse_map.addActionListener(actionListener);
		next_button.addActionListener(actionListener);
		cancel_button.addActionListener(actionListener);
	}

	/* (non-Javadoc)
	 * @see app.view.InterfaceView#setMouseListener(java.awt.event.MouseListener)
	 */
	
	public void setMouseListener(MouseListener mouseListener) {
		
	}
}
