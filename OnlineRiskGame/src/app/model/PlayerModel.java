package app.model;

import java.util.HashMap;
import java.util.List;

public class PlayerModel {

	private String namePlayer;
	private int myTroop;
	private String color;
	public PlayerModel( String namePlayer, int myTroop, String color) {
		this.namePlayer = namePlayer;
		this.myTroop = myTroop;
		this.color = color;
	}
	
	public PlayerModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return the value Control.
	 */
	public int getmyTroop() {
		return myTroop;
	}
	
	/**
	 * Sets the value Control.
	 * @param valueControl
	 */
	public void setmyTroop(int myTroop) {
		this.myTroop = myTroop;
	}
	
	/**
	 * 
	 * @return the continent name.
	 */
	public String getNamePlayer() {
		return namePlayer;
	}
	
	/**
	 * Sets the continent name.
	 * @param continentName
	 */
	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}
	
	/**
	 * 
	 * @return the continent name.
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * Sets the continent name.
	 * @param continentName
	 */
	public void setColor(String color) {
		this.color = color;
	}
}
