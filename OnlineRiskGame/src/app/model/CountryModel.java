package app.model;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * "CountryModel" represents a country's properties such as name, position,
 * continent name that it belongs to, countries that it connected to, owner
 * (player who owns it) name, Number of armies in, and color.
 * 
 * @author Suruthi
 *
 */
public class CountryModel extends JButton {

	private String countryName;
	private int xPosition;
	private int yPosition;
	private String continentName;
	private List<CountryModel> linkedCountries;
	private int armies;
	private Color backgroundColor;
	private Color borderColor;
	private String rulerName;

	/**
	 * Constructor of CountryModel with parameters
	 * 
	 * @param countryName
	 * @param xPosition
	 * @param yPosition
	 * @param continentName
	 * @param linkedCountries
	 * @param ruler
	 * @param armies
	 */
	public CountryModel(String countryName, int xPosition, int yPosition, String continentName, List linkedCountries,
			PlayerModel ruler, int armies, String rulerName ) {
		this.countryName = countryName;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.continentName = continentName;
		this.linkedCountries = linkedCountries;
		this.armies = armies;
		this.backgroundColor = Color.WHITE;
		this.borderColor = Color.BLACK;
		this.rulerName = rulerName;
	}

	/**
	 * Default constructor
	 */
	public CountryModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the country name.
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Sets the country name.
	 * 
	 * @param countryName
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the X Position Value.
	 */
	public int getXPosition() {
		return xPosition;
	}

	/**
	 * Sets the X Position Value.
	 * 
	 * @param xPosition
	 */
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	/**
	 * @return the Y Position Value.
	 */
	public int getYPosition() {
		return yPosition;
	}

	/**
	 * Sets the Y Position Value.
	 * 
	 * @param yPosition
	 */
	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	/**
	 * @return the Continent Name.
	 */
	public String getcontinentName() {
		return continentName;
	}

	/**
	 * Sets the Continent Name.
	 * 
	 * @param continentName
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * @return the list of array.
	 */
	public List<CountryModel> getLinkedCountries() {
		return linkedCountries;
	}

	/**
	 * Sets the list of array.
	 * 
	 * @param linkedCountries
	 */
	public void setLinkedCountries(List<CountryModel> linkedCountries) {
		this.linkedCountries = linkedCountries;
	}

	/**
	 * @return the army number of the country.
	 */
	public int getArmies() {
		return armies;
	}

	/**
	 * Sets the army number of the country.
	 * 
	 * @param armies
	 */
	public void setArmies(int armies) {
		this.armies = armies;
	}

	/**
	 * @return the color
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * Sets the color of the country
	 * 
	 * @param color
	 */
	public void setBackgroundColor(Color color) {
		this.setBackground(color);
		this.backgroundColor = color;
	}

	/**
	 * @return the borderColor
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * Sets the borderColor
	 * 
	 * @param borderColor
	 */
	public void setBorderColor(Color borderColor) {
		this.setBorder(new LineBorder(borderColor));
		this.borderColor = borderColor;
	}
	/**
	 * @return the ruler name of the country.
	 */
	public String getRulerName() {
		return rulerName;
	}

	/**
	 * Sets the ruler name of the country.
	 * 
	 * @param rulerName
	 */
	public void setRulerName(String rulerName) {
		this.rulerName = rulerName;
	}


}
