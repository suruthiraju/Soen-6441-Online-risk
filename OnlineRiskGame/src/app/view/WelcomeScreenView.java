package app.view;

//This is the View
//Its only job is to display what the user sees
//It performs no function, but instead passes
//information entered by the user to whomever needs
//it.
import java.awt.event.ActionListener;
import java.util.Observable;
import java.awt.Font;
import javax.swing.*;

import app.helper.View;

public class WelcomeScreenView  extends JFrame implements View{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5402570613272324763L;
	private String welcomeText = "<html>WELCOME TO ONLINE \"RISK\" GAME</html>";
	private JLabel welcomeLabel = new JLabel(welcomeText);
	private String questionText = "<html>PLEASE SELECT AN OPTION</html>";
	private JLabel questionLabel = new JLabel(questionText);
	
	public JButton createMapButton = new JButton("Create Map");
	public JButton editMapButton = new JButton("Edit Map");
	public JButton playMapButton = new JButton("Play");
	public JButton exitButton = new JButton("Exit");
	
	//private String welcomeMessageText = "";
	//private JLabel welcomeMessageLabel = new JLabel(welcomeMessageText);

	public WelcomeScreenView(){
      // Sets up the view and adds the components
      JPanel welcomePanel = new JPanel();
      this.setName("RISK GAME");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocation(300, 200);
      this.setSize(800, 700);
      this.setResizable(false);
      this.setVisible(false);
      //this.setLayout(null);
      
      //welcomePanel.setSize(200, 500);
      welcomePanel.setLayout(null);
      
      //welcomePanel.setLocation(400, 300);
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
      
      playMapButton.setFont(smallFont);
      welcomePanel.add(playMapButton);
      playMapButton.setBounds(100, 400, 200, 40);
      
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
	

