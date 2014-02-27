package com.xalero.dominion;

public interface IObserver {

    /**
     *  Updated whenever the referencing model calls notifyObservers
     */
    public void update();
}
