package com.xalero.dominion;

public interface IObservable {
	public void registerObserver(IObserver obs);
	public void removeObserver(IObserver obs);
	public void notifyObservers();
}
