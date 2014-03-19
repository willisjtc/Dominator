package com.xalero.dominion.server.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xalero.dominion.IUniqueObservable;
import com.xalero.dominion.IUniqueObserver;
import com.xalero.dominion.cards.Card;
import com.xalero.dominion.cards.CardFactory;
import com.xalero.dominion.cards.action.KingdomCard;
import com.xalero.dominion.cards.treasure.Treasures;
import com.xalero.dominion.cards.victory.VictoryCards;
import com.xalero.dominion.client.model.SimpleModel;
import com.xalero.dominion.events.EventKey;
import com.xalero.dominion.events.ProtocolEvent;
import com.xalero.dominion.manager.UserManager;
import com.xalero.dominion.utils.Result;

public class DominionModel implements IUniqueObservable {

	private static final Logger log = LogManager.getLogManager().getLogger(
			DominionModel.class.getName());

	private final Map<Long, IUniqueObserver> observers;
	private Treasures treasureCards;
	private VictoryCards victoryCards;
	private List<KingdomCard> kingdomCards;
	private List<Card> trash;
	private List<Player> players;
	private List<Card> curses;
	private boolean[] playersRespondedToCard;
	private Card interactiveCard;
	private boolean interactiveCardInPlay;
	private int playerTurn;
	private boolean gameStarted;
	private boolean gameOver;
	private long gameId;

	private final int START_COPPER_COUNT = 7;
	private final int START_ESTATE_COUNT = 3;

	private final int TWO_PLAYER_CURSE_COUNT = 10;
	private final int THREE_PLAYER_CURSE_COUNT = 20;
	private final int FOUR_PLAYER_CURSE_COUNT = 30;

	/**
	 * @param gameSettings
	 *            - The settings object contains all of the necessary
	 *            information to create and start a game.
	 */
	public DominionModel(GameSettings gameSettings) {

		observers = new HashMap<>();
		playerTurn = -1;
		gameStarted = false;
		gameOver = false;
		interactiveCardInPlay = false;

		initSupplyPile(gameSettings.getSelectedCards(), gameSettings
				.getPlayerInfos().size());
		initKingdomCards(gameSettings.getSelectedCards(), gameSettings
				.getPlayerInfos().size());
		initPlayers(gameSettings.getPlayerInfos());
		initPlayerResponsiveList();
	}

	/**
	 * @return a long representing a unique identifier for the game
	 */
	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	/**
	 * @return Whether or not the game has started.
	 */
	public boolean gameStarted() {
		return gameStarted;
	}

	/**
	 * @return a boolean of whether the game is over or not.
	 */
	public boolean gameOver() {
		return gameOver;
	}

	/**
	 * Ends the game.
	 */
	private void endGame() {
		setPlayerTurn(-1);

		gameOver = true;
	}

	/**
	 * Starts the game: determines the starting player, deals the cards...
	 */
	public Result startGame() {
		Result result = new Result(false, "");
		if (gameStarted) {
			result.setSuccess(true);
			result.setMessage("Game started already");
			return result;
		}

		// Random random = new Random();
		// setPlayerTurn(random.nextInt(players.size()));
		setPlayerTurn(0); // for testing
		players.get(playerTurn).turnStarted();

		dealCards();

		gameStarted = true;
		result.setMessage("Game started!");
		return result;
	}

	/**
	 * Deals out the standard 7 coppers and 3 estates to each player and then
	 * shuffles their decks.
	 */
	private void dealCards() {
		for (Player player : players) {
			for (int i = 0; i < START_COPPER_COUNT; i++) {
				player.addToDeck(treasureCards.getCopper());
			}
			for (int i = 0; i < START_ESTATE_COUNT; i++) {
				player.addToDeck(CardFactory.estate);
			}
			player.shuffleDeck();
			player.draw(5);
//			notifyObserver(player.getUniqueIdentifier())
		}
		notifyObservers(null);
	}

	/**
	 * Depending on the cards chosen for the game and number of players, the
	 * supply pile will be created accordingly. The supply pile will always
	 * consist of gold, copper, silver, provinces, duchies, estates, and the
	 * trash. Depending on whether the witch is in the game, curses will be be
	 * added to the supply pile.
	 * 
	 * @param cards
	 *            - The cards chosen for the game.
	 * @param playerCount
	 *            - the number of players in the game.
	 */
	private void initSupplyPile(Collection<Card> cards, int playerCount) {
		victoryCards = new VictoryCards(playerCount);
		treasureCards = new Treasures();
		trash = new LinkedList<>();

		initCurses(cards, playerCount);
	}

	/**
	 * If a witch is in the game curses will be added to the supply depending on
	 * the number of players in the game.
	 * 
	 * @param cards
	 *            - the cards chosen for the game
	 * @param playerCount
	 *            - the number of players in the game
	 */
	private void initCurses(Collection<Card> cards, int playerCount) {
		curses = new LinkedList<>();
		for (Card card : cards) {
			if (card.equals(CardFactory.witch)) {
				if (playerCount == 2) {
					for (int i = 0; i < TWO_PLAYER_CURSE_COUNT; i++) {
						curses.add(CardFactory.witch);
					}
				} else if (playerCount == 3) {
					for (int i = 0; i < THREE_PLAYER_CURSE_COUNT; i++) {
						curses.add(CardFactory.witch);
					}
				} else {
					for (int i = 0; i < FOUR_PLAYER_CURSE_COUNT; i++) {
						curses.add(CardFactory.witch);
					}
				}
			}
		}
	}

	/**
	 * Initializes the game with the kingdom cards from the game settings
	 * object.
	 * 
	 * @param cards
	 *            - The list of kingdom cards for the game.
	 */
	private void initKingdomCards(Collection<Card> cards, int playerCount) {
		kingdomCards = new LinkedList<>();
		for (Card card : cards) {
			this.kingdomCards.add(new KingdomCard(card, playerCount));
		}
	}

	/**
	 * Creates the human and computer player types.
	 * 
	 * @param playerInfos
	 *            - The types of players (HUMAN, COMPUTER)
	 */
	private void initPlayers(Collection<SimplePlayerInfo> playerInfos) {
		players = new ArrayList<>();
		int index = 0;
		for (SimplePlayerInfo playerInfo : playerInfos) {
			System.out
					.println("initPlayers ids: " + playerInfo.getIdentifier());
			if (playerInfo.getPlayerType().equals(PlayerType.COMPUTER)) {
				playerInfo.setDisplayName(UserManager.INSTANCE.getRandomName());
			}
			players.add(new Player(playerInfo, index));
			index++;
		}
	}

	private void initPlayerResponsiveList() {
		playersRespondedToCard = new boolean[players.size()];
		for (int i = 0; i < playersRespondedToCard.length; i++) {
			playersRespondedToCard[i] = false;
		}
	}

	/**
	 * Sets the player's turn for the beginning of the game.
	 * 
	 * @param playerTurn
	 *            - The index of the player in the list.
	 */
	private void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	/**
	 * @return The index of the current player's turn.
	 */
	public int getPlayerTurn() {
		return playerTurn;
	}

	/**
	 * @return The collection of players.
	 */
	public Collection<Player> getPlayers() {
		return Collections.unmodifiableCollection(players);
	}

	/**
	 * @param index
	 *            - index of the player desired.
	 * @return a player object.
	 */
	public Player getPlayer(int index) {
		return players.get(index);
	}

	/**
	 * @return The player whose turn it is
	 */
	public Player getCurrentPlayer() {
		if (playerTurn < 0 || players.size() == 0
				|| playerTurn >= players.size()) {
			return null;
		}
		return players.get(playerTurn);
	}

	/**
	 * Trashes a card from the game.
	 * 
	 * @param card
	 *            - the card to be trashed.
	 */
	public void addToTrash(Card card) {
		trash.add(card);
	}

	/**
	 * @return a collection of the curse cards in the game.
	 */
	public Collection<Card> getCurses() {
		return Collections.unmodifiableCollection(curses);
	}

	/**
	 * Draws a curse card to give to a player.
	 * 
	 * @return A curse card.
	 */
	public Card drawCurse() {
		return curses.remove(0);
	}

	/**
	 * @return a collection of the trashed cards in the game.
	 */
	public Collection<Card> getTrashCards() {
		return Collections.unmodifiableCollection(trash);
	}

	/**
	 * @return the victory cards in the game.
	 */
	public VictoryCards getVictoryCards() {
		return victoryCards;
	}

	/**
	 * @return a collection of the kingdom cards.
	 */
	public Collection<KingdomCard> getKingdomCards() {
		return Collections.unmodifiableCollection(kingdomCards);
	}

	/**
	 * @return the treasures in the game.
	 */
	public Treasures getTreasures() {
		return treasureCards;
	}

	/**
	 * Check to see if a kingdom card is in the game.
	 * 
	 * @param card
	 *            - card to see if it is in the game.
	 * @return true if the kingdom card is in the game, false otherwise.
	 */
	public boolean kingdomCardInGame(Card card) {
		for (KingdomCard kingdomCard : kingdomCards) {
			if (kingdomCard.equals(card) && kingdomCard.getCardCount() > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Used to get a kingdom card that is in the game
	 * 
	 * @param card
	 * @return
	 */
	public KingdomCard getKingdomCard(Card card) {
		for (int i = 0; i < kingdomCards.size(); i++) {
			if (kingdomCards.get(i).equals(card)) {
				return kingdomCards.get(i);
			}
		}
		return null;
	}

	public Card getCurse() {
		Card curse = null;
		if (curses.size() > 0) {
			curse = curses.remove(0);
		}
		return curse;
	}

	/**
	 * Buys a card for the player if the player is able to do so. Some
	 * preventions include a player can't buy the card or it isn't the player's
	 * turn.
	 * 
	 * @param playerId
	 *            The id of the player that is buying a card
	 * @param card
	 *            The card the player wants to buy
	 * @return a result object
	 */
	public Result buyCard(long playerId, Card card) {
		Result result = new Result(false, "You can't buy that card");
		if (!gameStarted) {
			result.setMessage("Game has not yet started");
			return result;
		}

		Player player = getPlayerById(playerId);

		// if (players.get(playerTurn).equals(player)) {
		result = player.canBuyCard(card);
		if (result.isSuccess()) {
			if (kingdomCardInGame(card)) {
				KingdomCard kingdomCard = getKingdomCard(card);
				player.buyCard(kingdomCard.drawCard());
				result.setMessage("You just bought a " + card);
			} else if (treasureCards.contains(card)) {
				player.buyCard(treasureCards.getTreasure(card));
				result.setMessage("You just bought a " + card);
			} else if (victoryCards.contains(card)) {
				player.buyCard(victoryCards.getVictoryCard(card));
				result.setMessage("You just bought a " + card);
			} else {
				result.setMessage("That card doesn't exist");
			}
		}
		// } else {
		// result.setMessage("not your turn");
		//
		notifyObservers(null);
		return result;
	}

	/**
	 * Retrieves the discard pile of the player
	 * 
	 * @param playerIndex
	 *            the index of the player
	 * @return an unmodifiable collection of the player's discard pile
	 */
	public Collection<Card> getDiscardPile(int playerIndex) {
		return Collections.unmodifiableCollection(players.get(playerIndex)
				.getDiscardPile());
	}

	/**
	 * Goes through the piles and looks for three missing piles or one empty
	 * province pile.
	 * 
	 * @return true if the game is over, false otherwise.
	 */
	private boolean isGameOver() {
		if (victoryCards.getProvinceCount() == 0) {
			return true;
		}
		int pilesGone = 0;
		if (treasureCards.getCopperCount() == 0) {
			pilesGone++;
		}
		if (treasureCards.getSilverCount() == 0) {
			pilesGone++;
		}
		if (treasureCards.getGoldCount() == 0) {
			pilesGone++;
		}
		if (curses.size() == 0) {
			pilesGone++;
		}
		if (victoryCards.getEstateCount() == 0) {
			pilesGone++;
		}
		if (victoryCards.getDuchyCount() == 0) {
			pilesGone++;
		}

		for (KingdomCard kingdomCard : kingdomCards) {
			if (kingdomCard.getCardCount() == 0) {
				pilesGone++;
			}
		}

		if (pilesGone > 2) {
			return true;
		}
		return false;
	}

	/**
	 * Ends a player's turn
	 * 
	 * @param playerId
	 *            the id of the player
	 * @return a result object.
	 */
	public Result endTurn(long playerId) {

		// check for ending turn
		Player curPlayer = players.get(playerTurn);
		
		curPlayer.turnEnded();

		StringBuilder resultMessage = new StringBuilder(
				curPlayer.getPlayerName() + " has ended their turn.");
		playerTurn = ++playerTurn % players.size();
		curPlayer = players.get(playerTurn);
		curPlayer.turnStarted();

		if (isGameOver()) {
			resultMessage.append("Game Over!");
			endGame();
		} else {
			resultMessage.append("\nPlayer Turn: " + curPlayer.getPlayerName());
		}

		notifyObservers(null);
		return new Result(true, resultMessage.toString());
	}

	/**
	 * Retrieves the player by the player's id
	 * 
	 * @param playerId
	 *            the long representing the player id
	 * @return a player object or null if htere is
	 */
	public Player getPlayerById(long playerId) {
		for (Player player : players) {
			if (playerId == player.getUniqueIdentifier()) {
				return player;
			}
		}
		return null;
	}


	public void setInteractiveCardInPlay(Card card) {
		interactiveCard = card;
		interactiveCardInPlay = true;
	}

	@Override
	public void registerObserver(IUniqueObserver obs, Long uniqueId) {
		observers.put(uniqueId, obs);
	}

	@Override
	public void removeObserver(IUniqueObserver obs) {
		observers.remove(obs.getUniqueId());
	}

	@Override
	public void notifyObservers(String event) {
		SimpleModel simpleModel = new SimpleModel(this);
		Gson gson = new GsonBuilder().create();
		String simpleJsonModel = gson.toJson(simpleModel);
		ProtocolEvent modelEvent = new ProtocolEvent(EventKey.DOMINION_MODEL, simpleJsonModel);
		String jsonModelEvent = gson.toJson(modelEvent);
		for (Entry<Long, IUniqueObserver> obs : observers.entrySet()) {
			obs.getValue().update(jsonModelEvent);
		}
	}

	@Override
	public void notifyObserver(Long uniqueId, String event) {
		observers.get(uniqueId).update(event);
	}
}
