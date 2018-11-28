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
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Field;

import app.model.CardModel;
import app.model.ContinentsModel;
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
		if (gamePlayModel.getGameMap().getPlayerTurn() != null) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("namePlayer", gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
			jsonObj.put("myTroop", gamePlayModel.getGameMap().getPlayerTurn().getmyTroop());
			if (gamePlayModel.getGameMap().getPlayerTurn().getColor() != null) {
				jsonObj.put("color", gamePlayModel.getGameMap().getPlayerTurn().getColor().toString());
			} else {
				jsonObj.put("color", "");
			}
			jsonObj.put("remainTroop", gamePlayModel.getGameMap().getPlayerTurn().getremainTroop());
			jsonObj.put("showReinforcementCard", gamePlayModel.getGameMap().getPlayerTurn().getShowReinforcementCard());

			// list of country
			JSONArray countryOwn = new JSONArray();
			if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries() != null) {
				for (int j = 0; j < gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().size(); j++) {
					JSONObject jsonObj1 = new JSONObject();
					jsonObj1.put("countryName",
							gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getCountryName());
					jsonObj1.put("xPosition",
							gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getXPosition());
					jsonObj1.put("yPosition",
							gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getYPosition());
					jsonObj1.put("continentName",
							gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getcontinentName());
					jsonObj1.put("armies",
							gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getArmies());
					if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getBackgroundColor() != null) {
						jsonObj1.put("backgroundColor", gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j)
								.getBackgroundColor().toString());
					} else {
						jsonObj1.put("backgroundColor", "");
					}
					if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getBorderColor() != null) {
						jsonObj1.put("borderColor", gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j)
								.getBorderColor().toString());
					} else {
						jsonObj1.put("borderColor", "");
					}
					jsonObj1.put("rulerName",
							gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getRulerName());
					countryOwn.add(jsonObj1);
				}
			}
			jsonObj.put("ownedCountries", countryOwn);

			// list of owned cards
			JSONArray cardList = new JSONArray();
			if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards() != null) {
				for (int j = 0; j < gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().size(); j++) {
					JSONObject jsonObj1 = new JSONObject();
					jsonObj1.put("cardId", gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().get(j).getCardId());
					jsonObj1.put("cardValue",
							gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().get(j).getCardValue());
					cardList.add(jsonObj1);
				}
			}
			jsonObj.put("ownedCards", cardList);

			gameMapModelParam.put("playerTurn", jsonObj);
		} else {
			gameMapModelParam.put("playerTurn", null);
		}
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
		Long lvalue ;
		int value;
		Boolean flag;
		String content;
		Color color;
		
		try
        {
            //Read JSON file
			Object inputModel = parser.parse(new FileReader(file));
			JSONObject playModel = (JSONObject) inputModel;
			
			JSONObject getMapModel = (JSONObject) playModel.get("gameMapModel");
			
			JSONArray playerList = (JSONArray) getMapModel.get("listOfPlayers");
			for (Object o : playerList) {
				PlayerModel playerModel = new PlayerModel();
				JSONObject player = (JSONObject) o;
				
				if (player.get("namePlayer") != null) {
					content = (String) player.get("namePlayer");
					playerModel.setNamePlayer(content);
				}else {
					playerModel.setNamePlayer("");
				}
				
				lvalue = (Long) player.get("myTroop");
				value = lvalue.intValue();
				playerModel.setmyTroop(value);
				
				content = (String) player.get("color");				
				color = stringToColor(content);
				playerModel.setColor(color);
				
				lvalue = (Long) player.get("remainTroop");
				value = lvalue.intValue();
				playerModel.setremainTroop(value);
				
				flag = (Boolean) player.get("showReinforcementCard");
				playerModel.setShowReinforcementCard(flag);

				// list of country
				JSONArray OwnedCountry = (JSONArray) player.get("ownedCountries");
				for (Object j : OwnedCountry) {
					CountryModel countryModel = new CountryModel();
					JSONObject country = (JSONObject) j;
					
					if (country.get("countryName") != null) {
						content = (String) country.get("countryName");
						countryModel.setCountryName(content);
					}else {
						countryModel.setCountryName("");
					}
					
					lvalue = (Long) country.get("xPosition");
					value = lvalue.intValue();
					countryModel.setXPosition(value);
					
					lvalue = (Long) country.get("yPosition");
					value = lvalue.intValue();
					countryModel.setYPosition(value);					
					
					if (country.get("continentName") != null) {
						content = (String) country.get("continentName");
						countryModel.setContinentName(content);
					}else {
						countryModel.setContinentName("");
					}
					
					lvalue = (Long) country.get("armies");
					value = lvalue.intValue();
					countryModel.setArmies(value);
					
					content = (String) country.get("backgroundColor");				
					color = stringToColor(content);
					countryModel.setBackgroundColor(color);
					
					content = (String) country.get("borderColor");				
					color = stringToColor(content);		
					countryModel.setBorderColor(color);
					
					if (country.get("rulerName") != null) {
						content = (String)country.get("rulerName");
						countryModel.setRulerName(content);
					}else {
						countryModel.setRulerName("");
					}
					playerModel.getOwnedCountries().add(countryModel);
				}

				// list of owned cards
				JSONArray OwnedCards = (JSONArray) player.get("ownedCards");
				for (Object j : OwnedCards) {
					CardModel cardModel = new CardModel();
					JSONObject card = (JSONObject) j;
					lvalue = (Long) card.get("cardId");
					value = lvalue.intValue();
					cardModel.setCardId(value);
					
					lvalue = (Long) card.get("cardValue");
					value = lvalue.intValue();
					cardModel.setCardValue(value);				
					playerModel.getOwnedCards().add(cardModel);
				}
				gamePlayModel.getGameMap().getListOfPlayers().add(playerModel);
			}
			
			JSONObject playerTurn = (JSONObject) getMapModel.get("playerTurn");
			PlayerModel playerModel1 = new PlayerModel();
			
			if (!playerTurn.get("namePlayer").equals(null)) {
				content = (String)playerTurn.get("namePlayer") ;
				playerModel1.setNamePlayer(content);
			}else {
				playerModel1.setNamePlayer("");
			}
			
			lvalue = (Long) playerTurn.get("myTroop");
			value = lvalue.intValue();
			playerModel1.setmyTroop(value);
			
			content = (String) playerTurn.get("color");				
			color = stringToColor(content);
			playerModel1.setColor(color);
			
			lvalue = (Long) playerTurn.get("remainTroop");
			value = lvalue.intValue();
			playerModel1.setremainTroop(value);
			
			flag = (Boolean) playerTurn.get("showReinforcementCard");
			playerModel1.setShowReinforcementCard(flag);

			// list of country
			JSONArray OwnedCountry = (JSONArray) playerTurn.get("ownedCountries");
			for (Object j : OwnedCountry) {
				CountryModel countryModel = new CountryModel();
				JSONObject country = (JSONObject) j;
								
				if (country.get("countryName") != null) {
					content = (String) country.get("countryName");
					countryModel.setCountryName(content);
				}else {
					countryModel.setCountryName("");
				}
				
				lvalue = (Long) country.get("xPosition");
				value = lvalue.intValue();
				countryModel.setXPosition(value);
				
				lvalue = (Long) country.get("yPosition");
				value = lvalue.intValue();
				countryModel.setYPosition(value);
								
				if (country.get("continentName") != null) {
					content = (String) country.get("continentName");
					countryModel.setContinentName(content);
				}else {
					countryModel.setContinentName("");
				}
				
				lvalue = (Long) country.get("armies");
				value = lvalue.intValue();
				countryModel.setArmies(value);
				
				content = (String) country.get("backgroundColor");				
				color = stringToColor(content);
				countryModel.setBackgroundColor(color);
				
				content = (String) country.get("borderColor");				
				color = stringToColor(content);		
				countryModel.setBorderColor(color);
								
				if (country.get("rulerName") != null) {
					content = (String)country.get("rulerName");
					countryModel.setRulerName(content);
				}else {
					countryModel.setRulerName("");
				}
				playerModel1.getOwnedCountries().add(countryModel);
			}

			// list of owned cards
			JSONArray OwnedCards = (JSONArray) playerTurn.get("ownedCards");
			for (Object j : OwnedCards) {
				CardModel cardModel = new CardModel();
				JSONObject card = (JSONObject) j;
				lvalue = (Long) card.get("cardId");
				value = lvalue.intValue();
				cardModel.setCardId(value);
				
				lvalue = (Long) card.get("cardValue");
				value = lvalue.intValue();
				cardModel.setCardValue(value);				
				playerModel1.getOwnedCards().add(cardModel);
			}
			gamePlayModel.getGameMap().setPlayerTurn(playerModel1);;
			
			
			lvalue = (Long) getMapModel.get("playerIndex");
			value = lvalue.intValue();
			gamePlayModel.getGameMap().setPlayerIndex(value);
			
			lvalue = (Long) getMapModel.get("leftModelIndex");
			value = lvalue.intValue();
			gamePlayModel.getGameMap().setLeftModelIndex(value);			
			
			lvalue = (Long) getMapModel.get("rightModelIndex");
			value = lvalue.intValue();
			gamePlayModel.getGameMap().setRightModelIndex(value);			
			
			JSONArray continentJSON = (JSONArray) getMapModel.get("continentList");
			int index1 = 0;
			for (Object o : continentJSON) {
				ContinentsModel continentModel = new ContinentsModel();
				JSONObject continent = (JSONObject) o;
								
				if (continent.get("continentName") != null) {
					content = (String) continent.get("continentName");
					continentModel.setContinentName(content);
				}else {
					continentModel.setContinentName("");
				}
				
				lvalue = (Long) continent.get("valueControl");
				value = lvalue.intValue();
				continentModel.setValueControl(value);	
				
				// list of country
				JSONArray linkedCountry = (JSONArray) continent.get("listOfCountries");
				for (Object j : linkedCountry) {
					CountryModel countryModel1 = new CountryModel();
					JSONObject country = (JSONObject) j;					
					
					if (country.get("countryName") != null) {
						content = (String) country.get("countryName");
						countryModel1.setCountryName(content);
					}else {
						countryModel1.setCountryName("");
					}
					
					lvalue = (Long) country.get("xPosition");
					value = lvalue.intValue();
					countryModel1.setXPosition(value);
					
					lvalue = (Long) country.get("yPosition");
					value = lvalue.intValue();
					countryModel1.setYPosition(value);					
					
					if (country.get("continentName") != null) {
						content = (String) country.get("continentName");
						countryModel1.setContinentName(content);
					}else {
						countryModel1.setContinentName("");
					}
					
					lvalue = (Long) country.get("armies");
					value = lvalue.intValue();
					countryModel1.setArmies(value);
					
					content = (String) country.get("backgroundColor");				
					color = stringToColor(content);
					countryModel1.setBackgroundColor(color);
					
					content = (String) country.get("borderColor");				
					color = stringToColor(content);		
					countryModel1.setBorderColor(color);				
					
					if (country.get("rulerName") != null) {
						content = (String)country.get("rulerName");
						countryModel1.setRulerName(content);
					}else {
						countryModel1.setRulerName("");
					}
					continentModel.getCoveredCountries().add(countryModel1);
				}
				gamePlayModel.getGameMap().getContinents().add(continentModel);
			}
			
			JSONArray countryJSON = (JSONArray) getMapModel.get("countryList");
			for (Object o : countryJSON) {
				CountryModel countryModel = new CountryModel();
				JSONObject country = (JSONObject) o;
				
				if (country.get("countryName") != null) {
					content = (String) country.get("countryName");
					countryModel.setCountryName(content);
				}else {
					countryModel.setCountryName("");
				}
				
				lvalue = (Long) country.get("xPosition");
				value = lvalue.intValue();
				countryModel.setXPosition(value);
				
				lvalue = (Long) country.get("yPosition");
				value = lvalue.intValue();
				countryModel.setYPosition(value);
				
				
				if (country.get("continentName") != null) {
					content = (String) country.get("continentName");
					countryModel.setContinentName(content);
				}else {
					countryModel.setContinentName("");
				}
				
				lvalue = (Long) country.get("armies");
				value = lvalue.intValue();
				countryModel.setArmies(value);
				
				content = (String) country.get("backgroundColor");				
				color = stringToColor(content);
				countryModel.setBackgroundColor(color);
				
				content = (String) country.get("borderColor");				
				color = stringToColor(content);		
				countryModel.setBorderColor(color);
								
				if (country.get("rulerName") != null) {
					content = (String)country.get("rulerName");
					countryModel.setRulerName(content);
				}else {
					countryModel.setRulerName("");
				}
				gamePlayModel.getGameMap().getCountries().add(countryModel);
			}
			
			JSONArray playersJSON = (JSONArray) playModel.get("players");
			for (Object o : playersJSON) {
				PlayerModel playerModel2 = new PlayerModel();
				JSONObject player = (JSONObject) o;
				
				if (player.get("namePlayer") != null) {
					content = (String) player.get("namePlayer");
					playerModel2.setNamePlayer(content);
				}else {
					playerModel2.setNamePlayer("");
				}
				
				lvalue = (Long) player.get("myTroop");
				value = lvalue.intValue();
				playerModel2.setmyTroop(value);
				
				content = (String) player.get("color");				
				color = stringToColor(content);
				playerModel2.setColor(color);
				
				lvalue = (Long) player.get("remainTroop");
				value = lvalue.intValue();
				playerModel2.setremainTroop(value);
				
				flag = (Boolean) player.get("showReinforcementCard");
				playerModel2.setShowReinforcementCard(flag);

				// list of country
				JSONArray OwnedCountries = (JSONArray) player.get("ownedCountries");
				for (Object j : OwnedCountries) {
					CountryModel countryModel2 = new CountryModel();
					JSONObject country = (JSONObject) j;
					
					
					if (country.get("countryName") != null) {
						content = (String) country.get("countryName");
						countryModel2.setCountryName(content);
					}else {
						countryModel2.setCountryName("");
					}
					
					lvalue = (Long) country.get("xPosition");
					value = lvalue.intValue();
					countryModel2.setXPosition(value);
					
					lvalue = (Long) country.get("yPosition");
					value = lvalue.intValue();
					countryModel2.setYPosition(value);
					
					
					if (country.get("continentName") != null) {
						content = (String) country.get("continentName");
						countryModel2.setContinentName(content);
					}else {
						countryModel2.setContinentName("");
					}
					
					lvalue = (Long) country.get("armies");
					value = lvalue.intValue();
					countryModel2.setArmies(value);
					
					content = (String) country.get("backgroundColor");				
					color = stringToColor(content);
					countryModel2.setBackgroundColor(color);
					
					content = (String) country.get("borderColor");				
					color = stringToColor(content);		
					countryModel2.setBorderColor(color);
					
					
					if (country.get("rulerName")!= null) {
						content = (String)country.get("rulerName");
						countryModel2.setRulerName(content);
					}else {
						countryModel2.setRulerName("");
					}
					playerModel2.getOwnedCountries().add(countryModel2);
				}

				// list of owned cards
				JSONArray OwnedCard = (JSONArray) player.get("ownedCards");
				for (Object j : OwnedCard) {
					CardModel cardModel = new CardModel();
					JSONObject card = (JSONObject) j;
					lvalue = (Long) card.get("cardId");
					value = lvalue.intValue();
					cardModel.setCardId(value);
					
					lvalue = (Long) card.get("cardValue");
					value = lvalue.intValue();
					cardModel.setCardValue(value);				
					playerModel2.getOwnedCards().add(cardModel);
				};
				gamePlayModel.getPlayers().add(playerModel2);
			}
			
			JSONArray deckJSON = (JSONArray) playModel.get("deck");			
			for (Object o : deckJSON) {
				CardModel cardModel = new CardModel();
				JSONObject card = (JSONObject) o;
				lvalue = (Long) card.get("cardId");
				value = lvalue.intValue();
				cardModel.setCardId(value);
				
				lvalue = (Long) card.get("cardValue");
				value = lvalue.intValue();
				cardModel.setCardValue(value);				
				gamePlayModel.getCards().add(cardModel);				
			}
			
			lvalue = (Long) playModel.get("selectedComboBoxIndex");
			value = lvalue.intValue();
			gamePlayModel.setSelectedComboBoxIndexRead(value);
			
			lvalue = (Long) playModel.get("selectedAttackComboBoxIndex");
			value = lvalue.intValue();
			gamePlayModel.setSelectedAttackComboBoxIndexRead(value);
			
			lvalue = (Long) playModel.get("selectedDefendComboBoxIndex");
			value = lvalue.intValue();
			gamePlayModel.setSelectedDefendComboBoxIndexRead(value);
			
			flag = (Boolean) playModel.get("countryOwned");
			gamePlayModel.setCountryOwned(flag);
			
			flag = (Boolean) playModel.get("armyToMoveFlag");
			gamePlayModel.setArmyToMoveTextRead(flag);
			
			flag = (Boolean) playModel.get("cardToBeAssigned");
			gamePlayModel.setCardToBeAssigned(flag);
			
				
			StringBuilder sb = new StringBuilder();
			if (playModel.get("consoleText") != null) {
				content = (String) playModel.get("consoleText");
				sb.append(content);
				gamePlayModel.setConsoleText(sb);
			}else {
				gamePlayModel.setConsoleText(sb);
			}
			
			if (playModel.get("defeatedCountry") != null) {
				JSONObject jsonObj = (JSONObject) playModel.get("defeatedCountry");
				
				if (jsonObj.get("countryName") != null) {
					content = (String) jsonObj.get("countryName");
					gamePlayModel.getDefeatedCountry().setCountryName(content);
				}else {
					gamePlayModel.getDefeatedCountry().setCountryName("");
				}
				
				lvalue = (Long) jsonObj.get("xPosition");
				value = lvalue.intValue();
				gamePlayModel.getDefeatedCountry().setXPosition(value);
				
				lvalue = (Long) jsonObj.get("yPosition");
				value = lvalue.intValue();
				gamePlayModel.getDefeatedCountry().setYPosition(value);
								
				if (jsonObj.get("continentName") != null) {
					content = (String) jsonObj.get("continentName");
					gamePlayModel.getDefeatedCountry().setContinentName(content);
				}else {
					gamePlayModel.getDefeatedCountry().setContinentName("");
				}
				
				lvalue = (Long) jsonObj.get("armies");
				value = lvalue.intValue();
				gamePlayModel.getDefeatedCountry().setArmies(value);
				
				content = (String) jsonObj.get("backgroundColor");				
				color = stringToColor(content);
				gamePlayModel.getDefeatedCountry().setBackgroundColor(color);
				
				content = (String) jsonObj.get("borderColor");
				color = stringToColor(content);
				gamePlayModel.getDefeatedCountry().setBorderColor(color);			
				
				if (jsonObj.get("rulerName") != null) {
					content = (String) jsonObj.get("rulerName");
					gamePlayModel.getDefeatedCountry().setRulerName(content);
				}else {
					gamePlayModel.getDefeatedCountry().setRulerName("");
				}
				
			}else {
				gamePlayModel.setDefeatedCountry(null);
			}          
       
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return gamePlayModel;
		
	}
	
	public static Color stringToColor(final String value) {
	    if (value == null) {
	      return Color.black;
	    }
	    try {
	      // get color by hex or octal value
	      return Color.decode(value);
	    } catch (NumberFormatException nfe) {
	      // if we can't decode lets try to get it by name
	      try {
	        // try to get a color by name using reflection
	        final Field f = Color.class.getField(value);

	        return (Color) f.get(null);
	      } catch (Exception ce) {
	        // if we can't get any color return black
	        return Color.black;
	      }
	    }
	  }
}
