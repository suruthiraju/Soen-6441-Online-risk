package app.utilities;

import java.awt.Color;

import javax.swing.JButton;

import app.model.CountryModel;

public class MapButton extends JButton{
	
	
	private int xPos;
	private int yPos;
	private CountryModel country;
	private Color color;
	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}
	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}
	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	/**
	 * @return the country
	 */
	public CountryModel getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(CountryModel country) {
		this.country = country;
	}
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	

}
