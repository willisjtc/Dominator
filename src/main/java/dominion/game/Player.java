/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.game;

import dominion.application.model.PlayerType;
import dominion.cards.Card;
import dominion.cards.CardUtils;
import dominion.game.user.User;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class Player {
    private User user;
    private int score;
    private PlayerType playerType;
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> discardPile;
    
    public Player(PlayerType playerType) {
        this.playerType = playerType;
        
        deck = new LinkedList<>();
        hand = new LinkedList<>();
        discardPile = new LinkedList<>();
    }
    
    /**
     * Shuffles the players deck and clears the discard pile.
     */
    public void shuffleDeck() {
        deck = new LinkedList<>(CardUtils.shuffle(deck, discardPile));
        
        discardPile.clear();
    }
    
    /**
     * Adds a card to the deck.
     * @param card - the card added.
     */
    public void addToDeck(Card card) {
        deck.add(card);
    }
    
    /**
     * Removes a card from the player's hand into the discard pile.
     * @param card - the card being discarded.
     */
    public void addToDiscard(Card card) {
        if (hand.remove(card)) {
            discardPile.add(card);
        }
    }
    
    /**
     * @return Whether the player is a computer or a human
     */
    public PlayerType getPlayerType() {
        return playerType;
    }
    
    /**
     * @return The current cards in the players hand.
     */
    public Collection<Card> getHand() {
        return hand;
    }
    
    /**
     * @return the current deck of the player.
     */
    public Collection<Card> getDeck() {
        return deck;
    }
    
    /**
     * @return the cards in the players discard pile.
     */
    public Collection<Card> getDiscardPile() {
        return discardPile;
    }
    
    /**
     * @return the current score of the player.
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Draws a number of cards from the player's deck into the player's hand.
     * @param numCards - the number of cards to draw.
     */
    public void draw(int numCards) {
        for (int i = 0; i < numCards; i++) {
            if (deck.isEmpty()) {
                CardUtils.shuffle(deck, discardPile);
            }
            hand.add(deck.get(0));
            deck.remove(0);
        }
    }
}
