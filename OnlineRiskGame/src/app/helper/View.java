package app.helper;

import java.awt.event.ActionListener;
import java.util.Observer;

/**
 * Rule to build a view
 * 
 * @author j_gohel
 *
 */
public interface View extends Observer {

	/**
	 * Sets Action Listener
	 * 
	 * @param actionListener
	 */
	public void setActionListener(ActionListener actionListener);
}
