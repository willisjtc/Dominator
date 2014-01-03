package dominion.game;

import dominion.application.IObservable;
import dominion.application.IObserver;
import dominion.application.model.PlayerType;
import dominion.application.model.SingleGameSettings;
import java.util.Collection;
import java.util.List;

import dominion.cards.Card;
import dominion.cards.CardFactory;
import dominion.cards.action.KingdomCard;
import dominion.cards.treasure.Treasures;
import dominion.cards.victory.VictoryCards;
import java.util.LinkedList;
import java.util.Random;

public class DominionModel implements IObservable {

    private List<IObserver> observers;
    private Treasures treasureCards;
    private VictoryCards victoryCards;
    private List<KingdomCard> kingdomCards;
    private List<Card> trash;
    private List<Player> players;
    private List<Card> curses;
    private int playerTurn;
    private boolean gameStarted;
    
    private final int START_COPPER_COUNT = 7;
    private final int START_ESTATE_COUNT = 3;
    
    private final int TWO_PLAYER_CURSE_COUNT = 10;
    private final int THREE_PLAYER_CURSE_COUNT = 20;
    private final int FOUR_PLAYER_CURSE_COUNT = 30;

    /**
     * @param gameSettings - The settings object contains all of the necessary
     * information to create and start a game.
     */
    public DominionModel(SingleGameSettings gameSettings) {
        
        observers = new LinkedList<>();
        gameStarted = false;

        initSupplyPile(gameSettings.getSelectedCards(), gameSettings.getPlayerTypes().size());
        initKingdomCards(gameSettings.getSelectedCards(), gameSettings.getPlayerTypes().size());
        initPlayers(gameSettings.getPlayerTypes());
    }
    
    /**
     * @return Whether or not the game has started.
     */
    public boolean gameStarted() {
        return gameStarted;
    }
    
    /**
     * Starts the game: determines the starting player, deals the cards...
     */
    public void startGame() {
        // choose player to start
        Random random = new Random();
        setPlayerTurn(random.nextInt(players.size()));
        
        dealCards();
        
        gameStarted = true;
        notifyObservers();
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
        }
    }

    /**
     * Depending on the cards chosen for the game and number of players, the 
     * supply pile will be created accordingly. The supply pile will always 
     * consist of gold, copper, silver, provinces, duchies, estates, and the
     * trash. Depending on whether the witch is in the game, curses will be 
     * be added to the supply pile.
     * @param cards - The cards chosen for the game.
     * @param playerCount - the number of players in the game.
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
     * @param cards - the cards chosen for the game
     * @param playerCount - the number of players in the game
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
     * @param cards - The list of kingdom cards for the game.
     */
    private void initKingdomCards(Collection<Card> cards, int playerCount) {
        kingdomCards = new LinkedList<>();
        for (Card card : cards) {
            this.kingdomCards.add(new KingdomCard(card, playerCount));
        }
    }
   
    /**
     * Creates the human and computer player types.
     * @param playerTypes - The types of players (HUMAN, COMPUTER)
     */
    private void initPlayers(Collection<PlayerType> playerTypes) {
        players = new  LinkedList<>();
        for (PlayerType playerType : playerTypes) {
            players.add(new Player(playerType));
        }
    }

    /**
     * Sets the player's turn for the beginning of the game.
     * @param playerTurn - The index of the player in the list.
     */
    private  void setPlayerTurn(int playerTurn) {
        System.out.println("setting player turn");
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
        return players;
    }

    /**
     * @param index - index of the player desired.
     * @return a player object.
     */
    public Player getPlayer(int index) {
        return players.get(index);
    }

    /**
     * Trashes a card from the game.
     * @param card - the card to be trashed.
     */
    public void addToTrash(Card card) {
        trash.add(card);
    }

    /**
     * @return a collection of the curse cards in the game.
     */
    public Collection<Card> getCurses() {
        return curses;
    }
    
    /** 
     * @return a collection of the trashed cards in the game.
     */
    public Collection<Card> getTrashCards() {
        return trash;
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
        return kingdomCards;
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
     * @param kingdomCard - card to see if it is in the game.
     * @return true if the kingdom card is in the game, false otherwise.
     */
    public boolean kingdomCardInGame(Card card) {
        for (KingdomCard kingdomCard : kingdomCards) {
            if (kingdomCard.getKingdomCard().equals(card)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void registerObserver(IObserver obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(IObserver obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }
}
