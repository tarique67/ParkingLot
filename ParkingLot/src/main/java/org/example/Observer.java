package org.example;

public interface Observer {
    default void notify(ParkingLotEvent event, Object publisher) {
        System.out.println(event.toString() + " " + publisher.toString());
    }
}
