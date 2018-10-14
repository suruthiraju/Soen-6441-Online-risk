/**
 * 
 */
package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.model.CreateMapModel;
import app.view.CreateMapView;

/**
 * @author
 *
 */
public class CreateMapController implements ActionListener{
	
	CreateMapView mapView;
	CreateMapModel mapModel;
	
	
	
	public CreateMapController() {
		this.mapView = new CreateMapView();
		
		this.mapView.setActionListener(this);
		this.mapView.setVisible(true);
		
		
		
		
		
		this.mapModel = new CreateMapModel();
		
		
		
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
