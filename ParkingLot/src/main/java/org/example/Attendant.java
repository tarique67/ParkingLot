package org.example;

import java.util.ArrayList;
import java.util.List;

public class Attendant {

    private List<ParkingLot> parkingLots;
    private ParkingStrategy parkingStrategy = ParkingStrategy.Nearest;

    public Attendant() {
        this.parkingLots = new ArrayList<>();
    }
    public void assign(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public String park(Vehicle vehicle){
        int parkingLotsFull = 0;
        String token = "";
        for(int i=0; i<parkingLots.size(); i++){
            if(!parkingLots.get(i).isFull()){
                token = parkingLots.get(i).park(vehicle, this.parkingStrategy);
                break;
            }else{
                parkingLotsFull++;
            }
        }
        if(parkingLotsFull>=parkingLots.size()) throw new RuntimeException();
        return token;
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

    public void switchStrategy() {
        if(this.parkingStrategy==ParkingStrategy.Nearest)
            this.parkingStrategy = ParkingStrategy.Farthest;
        else
            this.parkingStrategy = ParkingStrategy.Nearest;
    }
}