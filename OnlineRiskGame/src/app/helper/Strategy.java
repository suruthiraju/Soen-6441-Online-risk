package app.helper;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

public interface Strategy {
	public void fortification();
	public void attack();
	public void reinforcement();
	public void itemStateChanged(ItemEvent itemEvent);
	public void actionPerformed(ActionEvent actionEvent);
}
