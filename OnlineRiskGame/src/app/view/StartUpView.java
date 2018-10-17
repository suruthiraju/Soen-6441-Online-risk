package app.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import app.helper.View;
import app.model.ContinentsModel;
import app.model.CountryModel;
import app.utilities.ReadFile;

/**
 * 
 * @author DELL
 *
 */


public class StartUpView extends JFrame implements View {

	public ArrayList<ContinentsModel> listOfContinents;
	public ArrayList<CountryModel> listOfCountries;

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub

	}

	public JButton[] button;
	public JButton button2;
	public JButton button3;

	public StartUpView() {

		ReadFile rf = new ReadFile();
		this.listOfContinents = rf.getMapContinentDetails();
		this.listOfCountries = rf.getMapCountryDetails();

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		setSize(1000, 1000);
		int n = this.listOfCountries.size();
		button = new JButton[n];

		for (int i = 0; i < n; i++) {
			button[i] = new JButton(this.listOfCountries.get(i).getCountryName().substring(0, 3));
			button[i].setBounds(this.listOfCountries.get(i).getXPosition(), this.listOfCountries.get(i).getYPosition(),
					50, 50);

			panel.add(button[i]);
		}
		panel.setLayout(null);
	}

	public void paint(final Graphics g) {

		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		Point[] connectorPoints = new Point[this.listOfCountries.size()];

		for (int i = 0; i < this.listOfCountries.size(); i++) {
			connectorPoints[i] = SwingUtilities.convertPoint(button[i], 0, 0, this);

		}

		for (int k = 0; k < this.listOfCountries.size(); k++) {
			ArrayList<CountryModel> neighbourCountries = (ArrayList<CountryModel>) this.listOfCountries.get(k)
					.getLinkedCountries();

//			for (int j = 0; j < neighbourCountries.size(); j++) {
//
//				g2.drawLine(connectorPoints[j].x, connectorPoints[j].y, connectorPoints[k].x, connectorPoints[k].y);
//
//			}
		}

	}

}