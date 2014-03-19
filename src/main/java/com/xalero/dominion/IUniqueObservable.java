package com.xalero.dominion;

public interface IUniqueObservable {
	public void registerObserver(IUniqueObserver obs, Long uniqueId);
	public void removeObserver(IUniqueObserver obs);
	public void notifyObservers(String event);
	public void notifyObserver(Long uniqueId, String event);
}
