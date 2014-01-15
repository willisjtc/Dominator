package dominion.application.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import dominion.application.IObservable;
import dominion.application.IObserver;
import dominion.cards.Card;
import java.util.Arrays;

public enum GameSettings implements IObservable {

    INSTANCE;
    private List<IObserver> observers;
    private List<SimplePlayerInfo> players;
    private List<Card> customCards;
    private List<Card> randomCards;
    private CardSet setChosen;
    
    private final static int MAX_PLAYERS = 4;

    // TODO: handle simple player info types
    
    private GameSettings() {
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

    public boolean addPlayer(SimplePlayerInfo playerInfo) {
        boolean result = false;
        if (playerInfo.getPlayerType().equals(PlayerType.HUMAN) && (players.size() >= MAX_PLAYERS || getNumHumanPlayers() > 0)) {
            return result;
        }
        if (playerInfo.getPlayerType().equals(PlayerType.COMPUTER) && players.size() >= MAX_PLAYERS) {
            return result;
        }
        result = players.add(playerInfo);
        notifyObservers();
        return result;
    }
    
    private int getNumHumanPlayers() {
        int numHumans = 0;
        for (SimplePlayerInfo playerInfo : players) {
            if (playerInfo.getPlayerType().equals(PlayerType.HUMAN)) {
                numHumans++;
            }
        }
        return numHumans;
    }
    
    public boolean humanPlayerExists() {
        for (SimplePlayerInfo playerInfo : players) {
            if (playerInfo.getPlayerType().equals(PlayerType.HUMAN)) {
                return true;
            }
        }
        return false;
    }
    
    public Collection<SimplePlayerInfo> getPlayerInfos() {
        return players;
    }

    public boolean removePlayer(SimplePlayerInfo playerInfo) {
        boolean result = players.remove(playerInfo);
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
