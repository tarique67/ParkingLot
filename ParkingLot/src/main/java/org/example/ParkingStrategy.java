package org.example;

import java.util.List;
import java.util.function.Function;

public enum ParkingStrategy {
    NEAREST((List<ParkingLot> parkingLots)->{
        for(int i=0; i<parkingLots.size(); i++){
            if(!parkingLots.get(i).isFull()){
                return parkingLots.get(i).checkNearestVacant();
            }
        }
        throw new RuntimeException();

    }),
    FARTHEST((List<ParkingLot> parkingLots)->{
        for(int i= parkingLots.size()-1; i>=0; i--){
            if(!parkingLots.get(i).isFull()){
                return parkingLots.get(i).checkFarthestVacant();
            }
        }
        throw new RuntimeException();
    }),
    DISTRIBUTIVE((List<ParkingLot> parkingLots)->{
        int minLotIndex = 0 ;
        for(int i=0; i<parkingLots.size(); i++){
            if(parkingLots.get(i).fullSlots()<parkingLots.get(minLotIndex).fullSlots()){
                minLotIndex = i;
                break;
            }
        }
        return parkingLots.get(minLotIndex).checkNearestVacant();
    });

    private final Function<List<ParkingLot>, Slot> getSlot;

    ParkingStrategy(Function<List<ParkingLot>, Slot> getSlot) {
        this.getSlot = getSlot;
    }

    public Slot getSlot(List<ParkingLot> parkingLots) {
        return this.getSlot.apply(parkingLots);
    }
}
