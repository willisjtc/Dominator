package dominion.application;

public interface IObserver {

    /**
     *  Updated whenever the referencing model calls notifyObservers
     */
    public void update();
}
