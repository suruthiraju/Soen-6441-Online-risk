package app.model;

/**
 * "ContinentsModel" is a class for continents of the map. "continentName" and
 * "valueControl" are attributes of an object
 * 
 * @author user
 *
 */
public class ContinentsModel {

	private String continentName;
	private int valueControl;

	/**
	 * Constructor of ContinentsModel
	 * 
	 * @param continentName
	 * @param valueControl
	 */
	public ContinentsModel(String continentName, int valueControl) {
		this.continentName = continentName;
		this.valueControl = valueControl;
	}

	/**
	 * Returns the continent name
	 * 
	 * @return the continent name.
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * Sets the continent name.
	 * 
	 * @param continentName
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * Returns the continent value control
	 * 
	 * @return the value Control.
	 */
	public int getValueControl() {
		return valueControl;
	}

	/**
	 * Sets the continent value Control.
	 * 
	 * @param valueControl
	 */
	public void setValueControl(int valueControl) {
		this.valueControl = valueControl;
	}
}
