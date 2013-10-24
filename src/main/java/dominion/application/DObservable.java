package dominion.application;

public interface DObservable {
	public void registerObserver(DObserver obs);
	public void removeObserver(DObserver obs);
	public void notifyObservers();
}
