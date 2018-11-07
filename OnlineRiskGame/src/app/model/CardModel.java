package app.model;

/**
 * "CardModel" is a class for continents of the map. "cardId" and
 * "cardValue" are attributes of an object
 * 
 * @author Team 35
 *
 */
public class CardModel {

	private int cardId;
	private int cardValue;

	/**
	 * Constructor of CardModel
	 * 
	 * @param cardId
	 * @param cardValue
	 */
	public CardModel(int cardId, int cardValue) {
		this.cardId = cardId;
		this.cardValue = cardValue;
	}

	/**
	 * Returns the CardId
	 * 
	 * @return the CardId.
	 */
	public int getCardId() {
		return cardId;
	}

	/**
	 * Sets the CardId.
	 * 
	 * @param CardId
	 */
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	/**
	 * Returns the cardValue
	 * 
	 * @return the cardValue.
	 */
	public int getCardValue() {
		return cardValue;
	}

	/**
	 * Sets the cardValue.
	 * 
	 * @param cardValue
	 */
	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}
}
