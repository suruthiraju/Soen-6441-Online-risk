package app.utilities;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import app.model.CardModel;
import app.model.CountryModel;
import app.model.GameMapModel;
import app.model.GamePlayModel;
import app.model.PlayerModel;

public class SaveGame {

	public static void main(String[] args) throws IOException, ParseException {
		GameMapModel gameMapModel;
		GamePlayModel gamePlayModel;
		Validation val;
		ReadFile readFile;
		File file;
		ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();
		ArrayList<CountryModel> cardList = new ArrayList<CountryModel>();

		PlayerModel pm = new PlayerModel("X", 0, Color.WHITE, 0, countryList, cardList);
		CardModel card;
		readFile = new ReadFile();
		file = new File(Constant.FILE_LOCATION);
		readFile.setFile(file);
		gameMapModel = new GameMapModel(file);
		gamePlayModel = new GamePlayModel();
		gamePlayModel.setGameMap(gameMapModel);

		countryList.add(gameMapModel.getCountries().get(0));
		countryList.add(gameMapModel.getCountries().get(1));

		countryList.get(0).setArmies(2);

		ArrayList<PlayerModel> pmList = new ArrayList<PlayerModel>();

		pmList.add(pm);
		gamePlayModel.getCardFromJSON();
		card = gamePlayModel.getCards().get(0);

		gamePlayModel.setPlayers(pmList);

		writeTOJSONFile(gamePlayModel);
		
		File readFile1 = new File(System.getProperty("user.dir") + "\\mapfiles\\file.json");
		readFROMJSONFile(readFile1);
	}

	public static void writeTOJSONFile(GamePlayModel gamePlayModel) throws IOException {
		File rootFolder = new File(System.getProperty("user.dir") + "\\mapfiles\\");
		File jsonFile = new File(rootFolder, "file.json");
		FileWriter writer = new FileWriter(jsonFile);

		JSONObject gameMapModelParam = new JSONObject();

		// continent
		JSONArray continentParam = new JSONArray();
		if (gamePlayModel.getGameMap().getContinents() != null) {
			for (int i = 0; i < gamePlayModel.getGameMap().getContinents().size(); i++) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("continentName", gamePlayModel.getGameMap().getContinents().get(i).getContinentName());
				jsonObj.put("valueControl", gamePlayModel.getGameMap().getContinents().get(i).getValueControl());

				// list of country
				JSONArray countryList = new JSONArray();
				for (int j = 0; j < gamePlayModel.getGameMap().getContinents().get(i).getCoveredCountries()
						.size(); j++) {
					JSONObject jsonObj1 = new JSONObject();
					jsonObj1.put("countryName", gamePlayModel.getGameMap().getContinents().get(i).getCoveredCountries()
							.get(j).getCountryName());
					jsonObj1.put("xPosition", gamePlayModel.getGameMap().getContinents().get(i).getCoveredCountries()
							.get(j).getXPosition());
					jsonObj1.put("yPosition", gamePlayModel.getGameMap().getContinents().get(i).getCoveredCountries()
							.get(j).getYPosition());
					jsonObj1.put("continentName", gamePlayModel.getGameMap().getContinents().get(i)
							.getCoveredCountries().get(j).getcontinentName());
					jsonObj1.put("armies",
							gamePlayModel.getGameMap().getContinents().get(i).getCoveredCountries().get(j).getArmies());
					if (gamePlayModel.getGameMap().getContinents().get(i).getCoveredCountries().get(j)
							.getBackgroundColor() != null) {
						jsonObj1.put("backgroundColor", gamePlayModel.getGameMap().getContinents().get(i)
								.getCoveredCountries().get(j).getBackgroundColor().toString());
					} else {
						jsonObj1.put("backgroundColor", "");
					}
					if (gamePlayModel.getGameMap().getContinents().get(i).getCoveredCountries().get(j)
							.getBorderColor() != null) {
						jsonObj1.put("borderColor", gamePlayModel.getGameMap().getContinents().get(i)
								.getCoveredCountries().get(j).getBorderColor().toString());
					} else {
						jsonObj1.put("borderColor", "");
					}
					jsonObj1.put("rulerName", gamePlayModel.getGameMap().getContinents().get(i).getCoveredCountries()
							.get(j).getRulerName());
					countryList.add(jsonObj1);
				}
				jsonObj.put("listOfCountries", countryList);
				continentParam.add(jsonObj);
			}
		}

		// Country
		JSONArray countryParam = new JSONArray();
		if (gamePlayModel.getGameMap().getCountries() != null) {
			for (int i = 0; i < gamePlayModel.getGameMap().getCountries().size(); i++) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("countryName", gamePlayModel.getGameMap().getCountries().get(i).getCountryName());
				jsonObj.put("xPosition", gamePlayModel.getGameMap().getCountries().get(i).getXPosition());
				jsonObj.put("yPosition", gamePlayModel.getGameMap().getCountries().get(i).getYPosition());
				jsonObj.put("continentName", gamePlayModel.getGameMap().getCountries().get(i).getcontinentName());
				jsonObj.put("armies", gamePlayModel.getGameMap().getCountries().get(i).getArmies());
				if (gamePlayModel.getGameMap().getCountries().get(i).getBackgroundColor() != null) {
					jsonObj.put("backgroundColor",
							gamePlayModel.getGameMap().getCountries().get(i).getBackgroundColor().toString());
				} else {
					jsonObj.put("backgroundColor", "");
				}
				if (gamePlayModel.getGameMap().getCountries().get(i).getBorderColor() != null) {
					jsonObj.put("borderColor",
							gamePlayModel.getGameMap().getCountries().get(i).getBorderColor().toString());
				} else {
					jsonObj.put("borderColor", "");
				}
				jsonObj.put("rulerName", gamePlayModel.getGameMap().getCountries().get(i).getRulerName());
				countryParam.add(jsonObj);
			}
		}

		// list of players
		JSONArray playerList = new JSONArray();
		if (gamePlayModel.getGameMap().getListOfPlayers() != null) {

			for (int i = 0; i < gamePlayModel.getGameMap().getListOfPlayers().size(); i++) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("namePlayer", gamePlayModel.getGameMap().getListOfPlayers().get(i).getNamePlayer());
				jsonObj.put("myTroop", gamePlayModel.getGameMap().getListOfPlayers().get(i).getmyTroop());
				if (gamePlayModel.getGameMap().getListOfPlayers().get(i).getColor() != null) {
					jsonObj.put("color", gamePlayModel.getGameMap().getListOfPlayers().get(i).getColor().toString());
				} else {
					jsonObj.put("color", "");
				}
				jsonObj.put("remainTroop", gamePlayModel.getGameMap().getListOfPlayers().get(i).getremainTroop());
				jsonObj.put("showReinforcementCard",
						gamePlayModel.getGameMap().getListOfPlayers().get(i).getShowReinforcementCard());

				// list of country
				JSONArray countryOwn = new JSONArray();
				if (gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCountries() != null) {
					for (int j = 0; j < gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCountries()
							.size(); j++) {
						JSONObject jsonObj1 = new JSONObject();
						jsonObj1.put("countryName", gamePlayModel.getGameMap().getListOfPlayers().get(i)
								.getOwnedCountries().get(j).getCountryName());
						jsonObj1.put("xPosition", gamePlayModel.getGameMap().getListOfPlayers().get(i)
								.getOwnedCountries().get(j).getXPosition());
						jsonObj1.put("yPosition", gamePlayModel.getGameMap().getListOfPlayers().get(i)
								.getOwnedCountries().get(j).getYPosition());
						jsonObj1.put("continentName", gamePlayModel.getGameMap().getListOfPlayers().get(i)
								.getOwnedCountries().get(j).getcontinentName());
						jsonObj1.put("armies", gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCountries()
								.get(j).getArmies());
						if (gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCountries().get(j)
								.getBackgroundColor() != null) {
							jsonObj1.put("backgroundColor", gamePlayModel.getGameMap().getListOfPlayers().get(i)
									.getOwnedCountries().get(j).getBackgroundColor().toString());
						} else {
							jsonObj1.put("backgroundColor", "");
						}
						if (gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCountries().get(j)
								.getBorderColor() != null) {
							jsonObj1.put("borderColor", gamePlayModel.getGameMap().getListOfPlayers().get(i)
									.getOwnedCountries().get(j).getBorderColor().toString());
						} else {
							jsonObj1.put("borderColor", "");
						}
						jsonObj1.put("rulerName", gamePlayModel.getGameMap().getListOfPlayers().get(i)
								.getOwnedCountries().get(j).getRulerName());
						countryOwn.add(jsonObj1);
					}
				}
				jsonObj.put("ownedCountries", countryOwn);

				// list of owned cards
				JSONArray cardowned = new JSONArray();
				if (gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCards() != null) {
					for (int j = 0; j < gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCards()
							.size(); j++) {
						JSONObject jsonObj1 = new JSONObject();
						jsonObj1.put("cardId", gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCards()
								.get(j).getCardId());
						jsonObj1.put("cardValue", gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCards()
								.get(j).getCardValue());
						cardowned.add(jsonObj1);
					}
				}
				jsonObj.put("ownedCards", cardowned);

				playerList.add(jsonObj);
			}
		}

		gameMapModelParam.put("continentList", continentParam);
		gameMapModelParam.put("countryList", countryParam);
		gameMapModelParam.put("playerTurn", gamePlayModel.getGameMap().getPlayerTurn());
		gameMapModelParam.put("playerIndex", gamePlayModel.getGameMap().getPlayerIndex());
		gameMapModelParam.put("leftModelIndex", gamePlayModel.getGameMap().getLeftModelIndex());
		gameMapModelParam.put("rightModelIndex", gamePlayModel.getGameMap().getRightModelIndex());
		gameMapModelParam.put("listOfPlayers", playerList);

		// cards
		JSONArray cardParam = new JSONArray();
		if (gamePlayModel.getCards() != null) {
			for (int i = 0; i < gamePlayModel.getCards().size(); i++) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("cardId", gamePlayModel.getCards().get(i).getCardId());
				jsonObj.put("cardValue", gamePlayModel.getCards().get(i).getCardValue());
				cardParam.add(jsonObj);
			}
		}

		// Players
		JSONArray playerParam = new JSONArray();
		if (gamePlayModel.getPlayers() != null) {
			for (int i = 0; i < gamePlayModel.getPlayers().size(); i++) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("namePlayer", gamePlayModel.getPlayers().get(i).getNamePlayer());
				jsonObj.put("myTroop", gamePlayModel.getPlayers().get(i).getmyTroop());
				if (gamePlayModel.getPlayers().get(i).getColor() != null) {
					jsonObj.put("color", gamePlayModel.getPlayers().get(i).getColor().toString());
				} else {
					jsonObj.put("color", "");
				}
				jsonObj.put("remainTroop", gamePlayModel.getPlayers().get(i).getremainTroop());
				jsonObj.put("showReinforcementCard", gamePlayModel.getPlayers().get(i).getShowReinforcementCard());

				// list of country
				JSONArray countryOwned = new JSONArray();
				if (gamePlayModel.getPlayers().get(i).getOwnedCountries() != null) {
					for (int j = 0; j < gamePlayModel.getPlayers().get(i).getOwnedCountries().size(); j++) {
						JSONObject jsonObj1 = new JSONObject();
						jsonObj1.put("countryName",
								gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getCountryName());
						jsonObj1.put("xPosition",
								gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getXPosition());
						jsonObj1.put("yPosition",
								gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getYPosition());
						jsonObj1.put("continentName",
								gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getcontinentName());
						jsonObj1.put("armies",
								gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getArmies());
						if (gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getBackgroundColor() != null) {
							jsonObj1.put("backgroundColor", gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j)
									.getBackgroundColor().toString());
						} else {
							jsonObj1.put("backgroundColor", "");
						}
						if (gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getBorderColor() != null) {
							jsonObj1.put("borderColor", gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j)
									.getBorderColor().toString());
						} else {
							jsonObj1.put("borderColor", "");
						}
						jsonObj1.put("rulerName",
								gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getRulerName());
						countryOwned.add(jsonObj1);
					}
				}
				jsonObj.put("ownedCountries", countryOwned);

				// list of owned cards
				JSONArray cardList = new JSONArray();
				if (gamePlayModel.getPlayers().get(i).getOwnedCards() != null) {
					for (int j = 0; j < gamePlayModel.getPlayers().get(i).getOwnedCards().size(); j++) {
						JSONObject jsonObj1 = new JSONObject();
						jsonObj1.put("cardId", gamePlayModel.getPlayers().get(i).getOwnedCards().get(j).getCardId());
						jsonObj1.put("cardValue",
								gamePlayModel.getPlayers().get(i).getOwnedCards().get(j).getCardValue());
						cardList.add(jsonObj1);
					}
				}
				jsonObj.put("ownedCards", cardList);

				playerParam.add(jsonObj);
			}
		}

		JSONObject mainobj = new JSONObject();
		mainobj.put("selectedComboBoxIndex", gamePlayModel.getSelectedComboBoxIndex());
		mainobj.put("selectedAttackComboBoxIndex", gamePlayModel.getSelectedAttackComboBoxIndex());
		mainobj.put("selectedDefendComboBoxIndex", gamePlayModel.getSelectedDefendComboBoxIndex());
		mainobj.put("countryOwned", gamePlayModel.getCountryOwned());
		mainobj.put("armyToMoveFlag", gamePlayModel.getArmyToMoveFlag());
		mainobj.put("cardToBeAssigned", gamePlayModel.getCardToBeAssigned());
		mainobj.put("consoleText", gamePlayModel.getConsoleText().toString());
		if (gamePlayModel.getDefeatedCountry() != null) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("countryName", gamePlayModel.getDefeatedCountry().getCountryName());
			jsonObj.put("xPosition", gamePlayModel.getDefeatedCountry().getXPosition());
			jsonObj.put("yPosition", gamePlayModel.getDefeatedCountry().getYPosition());
			jsonObj.put("continentName", gamePlayModel.getDefeatedCountry().getcontinentName());
			jsonObj.put("armies", gamePlayModel.getDefeatedCountry().getArmies());
			if (gamePlayModel.getDefeatedCountry().getBackgroundColor() != null) {
				jsonObj.put("backgroundColor", gamePlayModel.getDefeatedCountry().getBackgroundColor().toString());
			} else {
				jsonObj.put("backgroundColor", "");
			}
			if (gamePlayModel.getDefeatedCountry().getBorderColor() != null) {
				jsonObj.put("borderColor", gamePlayModel.getDefeatedCountry().getBorderColor().toString());
			} else {
				jsonObj.put("borderColor", "");
			}
			jsonObj.put("rulerName", gamePlayModel.getDefeatedCountry().getRulerName());

			mainobj.put("defeatedCountry", jsonObj);
		} else {
			mainobj.put("defeatedCountry", null);
		}

		mainobj.put("deck", cardParam);
		mainobj.put("players", playerParam);
		mainobj.put("gameMapModel", gameMapModelParam);

		StringWriter out = new StringWriter();
		mainobj.writeJSONString(out);
		String jsonText = out.toString();
		writer.write(jsonText);

		writer.close();
	}
	
	public static GamePlayModel readFROMJSONFile(File file) throws IOException, ParseException {
		GamePlayModel gamePlayModel = new GamePlayModel() ;
		JSONParser parser = new JSONParser();
		
		
		try
        {
            //Read JSON file
			Object inputModel = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) inputModel;
			System.out.println(jsonObject.get("selectedComboBoxIndex"));
            
            
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return gamePlayModel;
		
	}
}
