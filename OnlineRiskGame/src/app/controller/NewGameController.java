package app.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;  

import javax.swing.JFileChooser;

import app.view.*;
import app.model.*;

/**
 * The Class NewGameController 
 *
 * @author Suruthi Raju
 */

public class NewGameController implements ActionListener {

	private NewGameView theView;
    
    public NewGameController() {
    	this.theView = new NewGameView();
    	this.theView.setActionListener(this);
    	this.theView.setVisible(true);
        
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(theView.browseMapButton)) {
			
			System.out.println("Helo");
			// open new game window
			//this.showCreateMapWindow();

		}
		
	}
    
    
	
    
}
