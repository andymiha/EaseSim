package com.ljj.easesim.abstractions;

public interface TemperatureObservable {
    void registerObserver(TemperatureObserver observer);
    void removeObserver(TemperatureObserver observer);
    void notifyObservers();
}

