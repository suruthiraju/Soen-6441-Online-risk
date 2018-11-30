package app.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import app.model.CardModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;
import app.utilities.Validation;
import app.view.NewGameView;

/**
 * In NewGameController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author Suruthi Raju
 * @version 1.0.0
 * 
 */

public class NewGameController implements ActionListener {

	/** The view. */
	private NewGameView theView;

	/** The list of players. */
	private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();

	/** The game map model. */
	private GameMapModel gameMapModel = new GameMapModel();

	/** The game play model. */
	private GamePlayModel gamePlayModel = new GamePlayModel();

	/** The no of players. */
	private int noOfPlayers;

	/** The Player name. */
	private String PlayerName = "";

	/** The Player type. */
	private String PlayerType = "";

	/** The list of country. */
	private List<CountryModel> listOfCountry;

	/** The list of cards. */
	private List<CardModel> listOfCards;

	/**
	 * Constructor initializes values and sets the screen too visible.
	 */
	public NewGameController() {
		this.theView = new NewGameView();
		this.theView.setActionListener(this);
		this.theView.setVisible(true);

	}

	/**
	 * This method performs action, by Listening the action event set in view.
	 *
	 * @param actionEvent the action event
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource().equals(theView.browseMapButton)) {
			int value = theView.chooseMap.showOpenDialog(theView);
			if (value == JFileChooser.APPROVE_OPTION) {
				try {
					File mapFile = theView.chooseMap.getSelectedFile();
					gameMapModel = new GameMapModel(mapFile);

					Validation MapValidation = new Validation();
					boolean flag1 = MapValidation.emptyLinkCountryValidation(this.gameMapModel);

					boolean flag3 = MapValidation.emptyContinentValidation(this.gameMapModel);
					boolean flag2 = MapValidation.checkInterlinkedContinent(this.gameMapModel);
					System.out.println(flag1 + " " + flag2 + " " + flag3);
					if (!(MapValidation.nonContinentValidation(this.gameMapModel))) {
						if (!(MapValidation.emptyLinkCountryValidation(this.gameMapModel))) {
							if (!(MapValidation.emptyContinentValidation(this.gameMapModel))) {

								System.out.println(" All the map validations are correct");
								try {
									JOptionPane.showMessageDialog(theView,
											"File Loaded Successfully! Click Next to Play!", "Map Loaded",
											JOptionPane.INFORMATION_MESSAGE);

								} catch (Exception e) {
									e.printStackTrace();
								}

							} else {
								System.out.println("Empty link country validation failed");
								JOptionPane.showOptionDialog(null, "Empty continent validation failed", "Invalid",
										JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
										new Object[] {}, null);
							}
						} else {
							System.out.println("Empty continent validation failed");
							JOptionPane.showOptionDialog(null, "Empty link country validation failed", "Invalid",
									JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
									null);
						}
					} else {
						System.out.println("One of the continent is invalid");
						JOptionPane.showOptionDialog(null, "Map is not linked properly", "Invalid",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
								null);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (actionEvent.getSource().equals(theView.nextButton)) {
			noOfPlayers = (int) theView.numOfPlayers.getSelectedItem();
			try {
				playerValidation();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else if (actionEvent.getSource().equals(theView.cancelButton)) {
			new WelcomeScreenController();
			this.theView.dispose();
		}

	}

	/**
	 * Check for the player validation.
	 *
	 * @throws ParseException the parse exception
	 */
	public void playerValidation() throws ParseException {
		if (gameMapModel.getCountries().size() > noOfPlayers) {
			System.out.println("no of players" + noOfPlayers);
			int h = 1, a = 1, b = 1, r = 1, c = 1;
			for (int i = 0; i < noOfPlayers; i++) {
				if (i == 0) {
					PlayerType = theView.playerType1.getSelectedItem().toString();
					PlayerName = theView.PlayerName1.getText();
				} else if (i == 1) {
					PlayerType = theView.playerType2.getSelectedItem().toString();
					PlayerName = theView.PlayerName2.getText();
				} else if (i == 2) {
					PlayerType = theView.playerType3.getSelectedItem().toString();
					PlayerName = theView.PlayerName3.getText();
				} else if (i == 3) {
					PlayerType = theView.playerType4.getSelectedItem().toString();
					PlayerName = theView.PlayerName4.getText();
				} else if (i == 4) {
					PlayerType = theView.playerType5.getSelectedItem().toString();
					PlayerName = theView.PlayerName5.getText();
				}
				System.out.println("PlayerName " + PlayerName);
				if (PlayerType == null || "".equals(PlayerType.trim())) {
					PlayerType = "Human";
					if (PlayerName == null || "".equals(PlayerName.trim())) {
						PlayerName = "Human " + h;
						h++;
					}
				} else if ("Human".equals(PlayerType)) {
					PlayerType = "Human";
					if (PlayerName == null || "".equals(PlayerName.trim())) {
						PlayerName = "Human " + h;
						h++;
					}
				} else if ("Aggressive".equals(PlayerType)) {
					PlayerType = "Aggressive";
					if (PlayerName == null || "".equals(PlayerName.trim())) {
						PlayerName = "Aggressive " + a;
						a++;
					}
				} else if ("Benevolent".equals(PlayerType)) {
					PlayerType = "Benevolent";
					if (PlayerName == null || "".equals(PlayerName.trim())) {
						PlayerName = "Benevolent " + b;
						b++;
					}
				} else if ("Random".equals(PlayerType)) {
					PlayerType = "Random";
					if (PlayerName == null || "".equals(PlayerName.trim())) {
						PlayerName = "Random " + r;
						r++;
					}
				} else if ("Cheater".equals(PlayerType)) {
					PlayerType = "Cheater";
					if (PlayerName == null || "".equals(PlayerName.trim())) {
						PlayerName = "Cheater " + c;
						c++;
					}
				}

				PlayerModel pm = new PlayerModel(PlayerName, PlayerType, 0, Color.WHITE, 0,
						new ArrayList<CountryModel>(), new ArrayList<CardModel>());
				listOfPlayers.add(pm);
			}
			gamePlayModel.setGameMap(gameMapModel);
			gamePlayModel.setPlayers(listOfPlayers);
			gamePlayModel.setCards(gamePlayModel.getCardFromJSON());
			new StartUpController(gamePlayModel);
			this.theView.dispose();
		} else {
			JOptionPane.showMessageDialog(theView,
					"Number of cuntry in the Map is less than Number of Players. Select map or player Again!",
					"Map Loaded", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
