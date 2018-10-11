package app.view;

//This is the View
//Its only job is to display what the user sees
//It performs no function, but instead passes
//information entered by the user to whomever needs
//it.
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.*;

public class WelcomeScreenView extends JFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5402570613272324763L;
	private String welcomeText = "<html>WELCOME TO ONLINE \"RISK\" GAME</html>";
	private JLabel welcomeLabel = new JLabel(welcomeText);
	private String questionText = "<html>PLEASE SELECT AN OPTION</html>";
	private JLabel questionLabel = new JLabel(questionText);
	
	private JButton createMapButton = new JButton("Create Map");
	private JButton editMapButton = new JButton("Edit Map");
	private JButton defaultMapButton = new JButton("Play");
	private JButton exitButton = new JButton("Exit");
	
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
      
      defaultMapButton.setFont(smallFont);
      welcomePanel.add(defaultMapButton);
      defaultMapButton.setBounds(100, 400, 200, 40);
      
      exitButton.setFont(smallFont);
      welcomePanel.add(exitButton);
      exitButton.setBounds(100, 500, 200, 40);
      
      
      
      //this.add(welcomePanel);
      // End of setting up the components --------
  }
	
	
	
	// If any button is clicked execute a method
  // in the Controller named <.........>ActionPerformed
	public void createMapListener(ActionListener listenForCreateMapButton){
		createMapButton.addActionListener(listenForCreateMapButton);
	}
	
	public void editMapListener(ActionListener listenForEditMapButton){
		editMapButton.addActionListener(listenForEditMapButton);
	}
	
	public void defaultMapListener(ActionListener listenForDefaultMapButton){
		defaultMapButton.addActionListener(listenForDefaultMapButton);
	}
	    
	public void exitListener(ActionListener listenForExitButton){
		exitButton.addActionListener(listenForExitButton);
	}

	// Open a popup that contains the error message passed
	public void displayErrorMessage(String errorMessage){
	    JOptionPane.showMessageDialog(this, errorMessage);
	}

	/*
	public static void setWelcomeMessageLabel(String newMesage) {
		
		JLabel welcomeMessageLabel = new JLabel(newMesage);
		JPanel aPanel = new JPanel();
	    aPanel.add(welcomeMessageLabel);
	    welcomeMessageLabel.setBounds(100, 600, 600, 200);
	    
	}
	*/
  
}
	



/*      

public int getFirstNumber(){
    return Integer.parseInt(firstNumber.getText());
}
public int getSecondNumber(){
    return Integer.parseInt(secondNumber.getText());
}
public int getCalcSolution(){
    return Integer.parseInt(calcSolution.getText());
}
public void setCalcSolution(int solution){
    calcSolution.setText(Integer.toString(solution));
}
// If the calculateButton is clicked execute a method
// in the Controller named actionPerformed
void addCalculateListener(ActionListener listenForCalcButton){
    calculateButton.addActionListener(listenForCalcButton);
}
// Open a popup that contains the error message passed
void displayErrorMessage(String errorMessage){
    JOptionPane.showMessageDialog(this, errorMessage);
}

*/
