package dominion.application.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import dominion.application.IObservable;
import dominion.application.IObserver;
import dominion.cards.Card;

public class SingleGameSettings implements IObservable {

	private List<IObserver> observers;
	private List<PlayerType> players;
	private List<Card> customCards;
	private List<Card> randomCards;
	
	private CardSet setChosen;
	
	public SingleGameSettings() {
		observers = new LinkedList<IObserver>();
		players = new LinkedList<PlayerType>();
		customCards = new LinkedList<Card>();
		randomCards = new LinkedList<Card>();
	}
	
	public Collection<Card> getCustomCards() {
		return customCards;
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
	
	public boolean addPlayer(PlayerType playerType) {
		return players.add(playerType);
	}
	
	public boolean removePlayer(PlayerType playerType) {
		return players.remove(playerType);
	}
	
	public void setCardSet(CardSet cardSet) {
		System.out.println("cardset: " + cardSet);
			setChosen = cardSet;
	}
	
	public void cardAdded(Card[] cards) {
		for (Card card : cards) {
			if (card.selectedProperty().get() && !customCards.contains(card)) {
				customCards.add(card);
			} else if (!card.selectedProperty().get() && customCards.contains(card)) {
				customCards.remove(card);
			}
		}
		notifyObservers();
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
