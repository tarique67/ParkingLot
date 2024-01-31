package org.example;

import java.util.Objects;
import java.util.UUID;

public class Slot {

    private int number;
    private Vehicle vehicle;

    private String token;

    public Slot(int number) {
        this.number = number;
    }

    public String park(Vehicle vehicle){
        if(isVehicleParked())
            throw new RuntimeException();
        String token = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.token = token;

        return token;
    }
    public boolean vehicleColorAvailable(String color){
        if(vehicle == null)
            return false;
        return vehicle.colorAvailable(color);
    }

    public boolean tokenMatches(String providedToken){
        return providedToken.equals(token);
    }

    public Vehicle unpark(String providedToken){
        if(!providedToken.equals(token))
            throw new IllegalArgumentException();
        Vehicle vehicleToReturn = this.vehicle;
        this.vehicle = null;
        return vehicleToReturn;
    }

    public boolean isVehicleParked() {
        return this.vehicle!=null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return number == slot.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
