package app.view;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.awt.Font;
import javax.swing.*;

import app.helper.View;

/**
 * This is the Welcome Screen of the game. It displays Welcome Message and the
 * options for user to start the game with default map or to create/edit any map
 * before starting the game
 */
public class WelcomeScreenView extends JFrame implements View {

	private static final long serialVersionUID = -5402570613272324763L;
	private String welcomeText = "<html>WELCOME TO ONLINE \"RISK\" GAME</html>";
	private JLabel welcomeLabel = new JLabel(welcomeText);
	private String questionText = "<html>PLEASE SELECT AN OPTION</html>";
	private JLabel questionLabel = new JLabel(questionText);

	public JButton createMapButton = new JButton("Create Map");
	public JButton editMapButton = new JButton("Edit Map");
	public JButton playMapButton = new JButton("Play");
	public JButton exitButton = new JButton("Exit");

	public WelcomeScreenView() {
		// Sets up the view and adds the components
		JPanel welcomePanel = new JPanel();
		this.setName("RISK GAME");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(300, 200);
		this.setSize(800, 700);
		this.setResizable(false);
		this.setVisible(false);

		welcomePanel.setLayout(null);
		this.add(welcomePanel);

		Font largeFont = new Font("Serif", Font.BOLD, 30);
		Font mediumFont = new Font("Serif", Font.BOLD, 18);
		Font smallFont = new Font("Serif", Font.BOLD, 14);

		welcomeLabel.setFont(largeFont);
		welcomePanel.add(welcomeLabel);
		welcomeLabel.setBounds(100, 0, 600, 200);

		questionLabel.setFont(mediumFont);
		welcomePanel.add(questionLabel);
		questionLabel.setBounds(100, 50, 500, 200);

		createMapButton.setFont(smallFont);
		welcomePanel.add(createMapButton);
		createMapButton.setBounds(100, 200, 200, 40);

		editMapButton.setFont(smallFont);
		welcomePanel.add(editMapButton);
		editMapButton.setBounds(100, 300, 200, 40);

		exitButton.setFont(smallFont);
		welcomePanel.add(exitButton);
		exitButton.setBounds(100, 500, 200, 40);

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		createMapButton.addActionListener(actionListener);

		editMapButton.addActionListener(actionListener);

		playMapButton.addActionListener(actionListener);

		exitButton.addActionListener(actionListener);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
