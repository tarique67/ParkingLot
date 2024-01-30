package org.example;

import java.util.ArrayList;
import java.util.List;

public class Attendant {

    private List<ParkingLot> parkingLots;

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
                token = parkingLots.get(i).park(vehicle);
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
}






















































//    public void assign(List<Integer> lotSizes) {
//        if(lotSizes.isEmpty() || lotSizes.size() != parkingLots.length)
//            throw new IllegalArgumentException();
//        for(int i=0; i<parkingLots.length; i++){
//            parkingLots[i] = new ParkingLot(lotSizes.get(i));
//        }
//    }
//

//    public String park(Vehicle vehicle) {
//        int lotsFullCount = 0;
//        String token = "";
//        for(int i=0; i<parkingLots.length; i++){
//            if(parkingLots[i].isFull()) {
//                lotsFullCount++;
//                if (lotsFullCount == parkingLots.length) throw new RuntimeException();
//            } else {
//                token = parkingLots[i].park(vehicle);
//                break;
//            }
//        }
//        return token;
//    }
//}
