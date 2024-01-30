package org.example;

import java.util.Objects;

public class Vehicle {

    private String registrationNumber;
    private String color;

    public Vehicle(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(registrationNumber, vehicle.registrationNumber) && Objects.equals(color, vehicle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, color);
    }

    public boolean colorAvailable(String color){
        return color.equalsIgnoreCase(this.color);
    }
}
