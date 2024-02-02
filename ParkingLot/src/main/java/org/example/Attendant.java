package org.example;

import java.util.ArrayList;
import java.util.List;

public class Attendant implements Observer{

    private List<ParkingLot> parkingLots;
    private ParkingStrategy parkingStrategy = ParkingStrategy.NEAREST;

    public Attendant() {
        this.parkingLots = new ArrayList<>();
    }
    public void assign(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public String park(Vehicle vehicle){
        Slot slot = this.parkingStrategy.getSlot(parkingLots);
        if(slot.isVehicleParked()) throw new RuntimeException();

        return slot.park(vehicle);
    }

    public Vehicle unpark(String token) {
        Vehicle vehicle = null;
        int unmatchedCount = 0;
        for(int i=0; i< parkingLots.size(); i++){
            try {
                vehicle = parkingLots.get(i).unpark(token);
                break;
            } catch (IllegalArgumentException exception) {
                unmatchedCount++;
            }
        }
        if(unmatchedCount == parkingLots.size()) throw new RuntimeException();
        return  vehicle;
    }

    public void switchStrategy(ParkingStrategy strategy) {
        this.parkingStrategy = strategy;
    }

    private int firstMinFullLot(){
        int minLotIndex = 0 ;
        for(int i=0; i<parkingLots.size(); i++){
            if(parkingLots.get(i).fullSlots()<parkingLots.get(minLotIndex).fullSlots()){
                minLotIndex = i;
                break;
            }
        }
        return minLotIndex;
    }
}