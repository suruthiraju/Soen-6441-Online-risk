package app.controller;

import javax.swing.JOptionPane;

import app.model.GamePlayModel;
import app.utilities.Validation;

/**
 * In PlayerController, the data flow into model object and updates the view
 * whenever data changes.
 * 
 * @author Hamid
 * @version 1.0.0
 *
 */
public class PlayerController {

	/** The game play model. */
	private GamePlayModel gamePlayModel;

	/** The val. */
	private Validation val = new Validation();

	/**
	 * Constructor initializes values and sets the screen too visible.
	 *
	 * @param gamePlayModel the game play model
	 */
	public PlayerController(GamePlayModel gamePlayModel) {

		this.gamePlayModel = gamePlayModel;
		if (val.endOfGame(this.gamePlayModel) == false) {
			String PlayerType = this.gamePlayModel.getGameMap().getPlayerTurn().getTypePlayer();
			if ("Human".equals(PlayerType)) {
				this.gamePlayModel.getGameMap().getPlayerTurn()
						.setStrategy(new HumanPlayerController(this.gamePlayModel));
			} else if ("Aggressive".equals(PlayerType)) {
				this.gamePlayModel.getGameMap().getPlayerTurn()
						.setStrategy(new AgressivePlayerController(this.gamePlayModel));
			} else if ("Benevolent".equals(PlayerType)) {
				this.gamePlayModel.getGameMap().getPlayerTurn()
						.setStrategy(new BenevolentPlayerController(this.gamePlayModel));
			} else if ("Random".equals(PlayerType)) {
				this.gamePlayModel.getGameMap().getPlayerTurn()
						.setStrategy(new RandomPlayerController(this.gamePlayModel));
			} else if ("Cheater".equals(PlayerType)) {
				this.gamePlayModel.getGameMap().getPlayerTurn()
						.setStrategy(new CheaterPlayerController(this.gamePlayModel));
			}

			int index = this.gamePlayModel.getGameMap().getPlayerIndex();

			index++;
			if (this.gamePlayModel.getPlayers().size() > index) {
				this.gamePlayModel.getGameMap().setPlayerIndex(index);
				this.gamePlayModel.getPlayers().get(index).callObservers();
			} else {
				index = 0;
				this.gamePlayModel.getGameMap().setPlayerIndex(index);
				this.gamePlayModel.getPlayers().get(index).callObservers();
			}
			new GamePlayController(this.gamePlayModel);
		} else {
			JOptionPane.showOptionDialog(null, "Bravo! You have won! Game is over!", "Valid",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
		}
	}

}
