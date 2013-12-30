package dominion.application.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import dominion.application.IObservable;
import dominion.application.IObserver;
import dominion.cards.Card;
import java.util.Arrays;

public enum SingleGameSettings implements IObservable {

    INSTANCE;
    private List<IObserver> observers;
    private List<PlayerType> players;
    private List<Card> customCards;
    private List<Card> randomCards;
    private CardSet setChosen;

    private SingleGameSettings() {
        observers = new LinkedList<>();
        players = new LinkedList<>();
        customCards = new LinkedList<>();
        randomCards = new LinkedList<>();

        setChosen = CardSet.RANDOM;
    }

    public void setCustomCards() {
    }

    public Collection<Card> getCustomCards() {
        return customCards;
    }

    public void setRandomCards(Card[] cards) {
        randomCards.clear();
        randomCards.addAll(Arrays.asList(cards));
        
        notifyObservers();
    }

    public Collection<Card> getRandomCards() {
        return randomCards;
    }

    public int getCustomCardCount() {
        return customCards.size();
    }

    public int getRandomCardCount() {
        return randomCards.size();
    }

    public Collection<Card> getSelectedCards() {
        if (setChosen.equals(CardSet.RANDOM)) {
            return randomCards;
        }
        return customCards;
    }

    public boolean addPlayer(PlayerType playerType) {
        boolean result = players.add(playerType);
        notifyObservers();
        return result;
    }

    public Collection<PlayerType> getPlayerTypes() {
        return players;
    }

    public boolean removePlayer(PlayerType playerType) {
        boolean result = players.remove(playerType);
        notifyObservers();
        return result;
    }

    public void setCardSet(CardSet cardSet) {
        setChosen = cardSet;
        notifyObservers();
    }

    public void cardToggled(Card[] cards) {
        for (Card card : cards) {
            if (card.selectedProperty().get() && !customCards.contains(card)) {
                customCards.add(card);
            } else if (!card.selectedProperty().get() && customCards.contains(card)) {
                customCards.remove(card);
            }
        }
        notifyObservers();
    }

    @PostConstruct
    public void init() {
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
