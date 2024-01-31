package org.example;

import java.util.Arrays;

public class ParkingLot {

    private Slot[] slots;

    public ParkingLot(int size) {
        if(size<=0)
            throw new IllegalArgumentException();
        this.slots = new Slot[size];
        for(int i = 0; i< slots.length; i++){
            this.slots[i] = new Slot(i);
        }
    }

    public int checkNearestVacant(){
        for(int i=0; i<slots.length; i++){
            if(!slots[i].isVehicleParked()){
                return i;
            }
        }
        throw new RuntimeException();
    }

    public String park(Vehicle vehicle, ParkingStrategy strategy) {
        if(isFull())
            throw new IllegalArgumentException();
        if(strategy == ParkingStrategy.Nearest)
            return slots[checkNearestVacant()].park(vehicle);
        else
            return slots[checkFarthestVacant()].park(vehicle);
    }

    public int checkFarthestVacant() {
        for(int i= slots.length-1; i>=0; i--){
            if(!slots[i].isVehicleParked()){
                return i;
            }
        }
        throw new RuntimeException();
    }

    public boolean isFull() {
        for(int i=0; i< slots.length; i++){
            if(!slots[i].isVehicleParked())
                return false;
        }
        return true;
    }

    public int countVehiclesOfColor(String color){
        int count = 0;
        for(int i=0; i<slots.length; i++){
            if(slots[i] != null && slots[i].vehicleColorAvailable(color))
                count++;
        }
        return count;
    }

    public Vehicle unpark(String token){
        Vehicle vehicle = null;
        int unmatchedCount = 0;
        for(int i=0; i< slots.length; i++){
            if(slots[i].tokenMatches(token)){
                vehicle = slots[i].unpark(token);
                break;
            }else{
                unmatchedCount++;
            }
        }
        if(unmatchedCount == slots.length) throw new RuntimeException();
        return  vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return Arrays.equals(slots, that.slots);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(slots);
    }
}
