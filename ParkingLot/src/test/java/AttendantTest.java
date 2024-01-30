import org.example.Attendant;
import org.example.ParkingLot;
import org.example.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttendantTest {

    @Test
    void expectAttendantAbleToParkAfterAssigningNewParkingLotToAttendant() {
        Attendant attendant = new Attendant();
        ParkingLot lot = new ParkingLot(1);
        attendant.assign(lot);
        attendant.park(new Vehicle("KA04AD", "Black"));

        assertTrue(lot.isFull());
    }

    @Test
    void expectAttendantAbleToParkToMultipleLotsAfterAssigningMultipleParkingLotsToAttendant() {
        Attendant attendant = new Attendant();
        ParkingLot firstLot = new ParkingLot(1);
        ParkingLot secondLot = new ParkingLot(1);
        attendant.assign(firstLot);
        attendant.assign(secondLot);
        attendant.park(new Vehicle("KA04AD", "Black"));
        attendant.park(new Vehicle("JH01KP", "Black"));

        assertTrue(firstLot.isFull());
    }

    @Test
    void expectTwoAttendantAbleToParkToSameLotAfterAssigningParkingLotToAttendant() {
        Attendant firstAttendant = new Attendant();
        Attendant secondAttendant = new Attendant();
        ParkingLot lot = new ParkingLot(2);
        firstAttendant.assign(lot);
        secondAttendant.assign(lot);
        firstAttendant.park(new Vehicle("KA04AD", "Black"));
        assertFalse(lot.isFull());
        secondAttendant.park(new Vehicle("JH01KP", "Black"));

        assertTrue(lot.isFull());
    }

    @Test
    void expectAttendantAbleToUnparkFromSameLotAfterAssigningParkingLotToAttendant() {
        Attendant attendant = new Attendant();
        ParkingLot lot = new ParkingLot(1);
        attendant.assign(lot);
        attendant.park(new Vehicle("KA04AD", "Black"));

        assertTrue(lot.isFull());
    }
}

































//    @Test
//    void expectNoExceptionIfCreationParkingLotsOfValidSize() {
//        assertDoesNotThrow(()-> new Attendant(3));
//    }
//
//    @Test
//    void expectExceptionWhenInitializingParkingLotsWithoutSizesOfEachParkingLot() {
//        assertThrows(IllegalArgumentException.class, ()->new Attendant(3).initialize(new ArrayList<>()));
//    }
//
//    @Test
//    void expectNoExceptionWhenInitializingParkingLotsWithSizesOfEachParkingLot() {
//        List<Integer> sizeList = new ArrayList<>();
//        sizeList.add(10);
//        sizeList.add(10);
//        sizeList.add(10);
//        assertDoesNotThrow(()->new Attendant(3).initialize(sizeList));
//    }
//
//    @Test
//    void expectExceptionWhenInitializingParkingLotsByProvidingMoreSizesThanSizeOfTheParkingLots() {
//        List<Integer> sizeList = new ArrayList<>();
//        sizeList.add(10);
//        sizeList.add(10);
//        sizeList.add(10);
//        assertThrows(IllegalArgumentException.class, ()->new Attendant(2).initialize(sizeList));
//    }
//
//    @Test
//    void expectExceptionIfParkingLotFull() {
//        List<Integer> sizeList = new ArrayList<>();
//        sizeList.add(1);
//        sizeList.add(1);
//        Attendant lots = new Attendant(2);
//        lots.initialize(sizeList);
//        lots.park(new Vehicle("JH01BH0987", "Blue"));
//        lots.park(new Vehicle("KA01BH0987", "Black"));
//        assertThrows(RuntimeException.class , ()->lots.park(new Vehicle("DL01BH0987", "Blue")));
//    }
//
//    @Test
//    void expectNoExceptionIfParkingLotNotFull() {
//        List<Integer> sizeList = new ArrayList<>();
//        sizeList.add(1);
//        sizeList.add(1);
//        Attendant lots = new Attendant(2);
//        lots.initialize(sizeList);
//        lots.park(new Vehicle("JH01BH0987", "Blue"));
//        assertDoesNotThrow(()->lots.park(new Vehicle("DL01BH0987", "Blue")));
//    }
//}
