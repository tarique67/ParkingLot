package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationBus {
    private static final NotificationBus INSTANCE = new NotificationBus();
    private final Map<ParkingLotEvent, List<Observer>> subscribers;
    public static NotificationBus getInstance() {
        return INSTANCE;
    }

    private NotificationBus() {
        this.subscribers = new HashMap<>();
        for (ParkingLotEvent event: ParkingLotEvent.values()) {
            subscribers.put(event, new ArrayList<>());
        }
    }

    public void publish(Object publisher, ParkingLotEvent event) {
        for (Observer observer: subscribers.get(event)) {
            observer.notify(event, publisher);
        }
    }

    public void subscribe(Observer observer, ParkingLotEvent event) {
        subscribers.get(event).add(observer);
    }

}
