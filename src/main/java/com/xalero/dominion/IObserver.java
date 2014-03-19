package com.xalero.dominion;

public interface IObserver {

    /**
     *  Updated whenever the referencing model calls notifyObservers
     * @param event TODO
     */
    public void update(String event);
}
