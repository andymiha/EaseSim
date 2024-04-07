package com.ljj.easesim.abstractions;

public interface AwayModeObservable {
    void registerAwayModeObserver(AwayModeObserver observer);
    void removeAwayModeObserver(AwayModeObserver observer);
    void notifyAwayModeObservers();
}
