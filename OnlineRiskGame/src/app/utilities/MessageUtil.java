package app.utilities;

/**
 * This class prints the given message on console.
 * 
 * @author GROUP-35
 */
public class MessageUtil {

	private String message;

	/**
	 * @param message
	 */
	public MessageUtil(String message) {
		this.message = message;
	}

	/**
	 * prints Message
	 * 
	 * @return String
	 */
	public String printMessage() {
		System.out.println(message);
		return message;
	}

	/**
	 * Salutation Message
	 * 
	 * @return String
	 */
	public String salutationMessage() {
		message = "Hi!" + message;
		System.out.println(message);
		return message;
	}
}