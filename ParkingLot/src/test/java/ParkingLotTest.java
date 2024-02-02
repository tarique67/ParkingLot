import org.example.ParkingLot;
import org.example.ParkingStrategy;
import org.example.Slot;
import org.example.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void expectExceptionWhenInitialingParkingLotWithNegativeOrZeroSize() {
        assertThrows(IllegalArgumentException.class, ()-> new ParkingLot(-5));
        assertThrows(IllegalArgumentException.class, ()-> new ParkingLot(0));
    }

    @Test
    void expect0ForNearestVacantParkingSlotWhenNoVehiclesParked() {
        Slot expected = new Slot(0);

        assertEquals(expected, new ParkingLot(10).checkNearestVacant());
    }

    @Test
    void expect2ForFarthestVacantParkingSlotWhenNoVehiclesParked() {
        Slot expected = new Slot(2);

        assertEquals(expected, new ParkingLot(3).checkFarthestVacant());
    }

    @Test
    void expectNoExceptionWhileParkingIfVacantSlotsAvailable() {
        ParkingLot lot = new ParkingLot(10);

        assertDoesNotThrow( ()-> lot.park(new Vehicle("JH01Bk2241", "White"), ParkingStrategy.NEAREST));
    }

    @Test
    void expectTrueWhenCheckingIsParkingLotFullIfAllSlotsOccupied() {
        ParkingLot lot = new ParkingLot(1);

        lot.park(new Vehicle("JH01Bk2241", "White"), ParkingStrategy.NEAREST);

        assertTrue(lot.isFull());
    }

    @Test
    void expectFalseWhenCheckingIsParkingLotFullIfAllSlotsNotOccupied() {
        ParkingLot lot = new ParkingLot(2);

        lot.park(new Vehicle("JH01Bk2241", "White"), ParkingStrategy.NEAREST);

        assertFalse(lot.isFull());
    }

    @Test
    void expectExceptionWhileParkingIfNoVacantSlotsAvailable() {
        ParkingLot lot = new ParkingLot(1);

        lot.park(new Vehicle("DL03BN0098", "Black"), ParkingStrategy.NEAREST);

        assertThrows(IllegalArgumentException.class, ()-> lot.park(new Vehicle("JH01Bk2241", "White"), ParkingStrategy.NEAREST));
    }


    @Test
    void expect1ForBlackCarsWhileCheckingCarsOfBlackColor() {
        int expected = 1;
        ParkingLot lot = new ParkingLot(10);

        lot.park(new Vehicle("KL098273", "Black"), ParkingStrategy.NEAREST);

        assertEquals(expected, lot.countVehiclesOfColor("Black"));
    }

    @Test
    void expect3ForBlackCarsWhileCheckingCarsOfBlackColor() {
        int expected = 3;
        ParkingLot lot = new ParkingLot(10);
        lot.park(new Vehicle("KL098273", "Black"), ParkingStrategy.NEAREST);
        lot.park(new Vehicle("KL098275", "Black"), ParkingStrategy.NEAREST);
        lot.park(new Vehicle("KL098279", "Black"), ParkingStrategy.NEAREST);

        assertEquals(expected, lot.countVehiclesOfColor("Black"));
    }

    @Test
    void expectExceptionForUnparkingIfSlotEmpty() {
        ParkingLot lot = new ParkingLot(1);

        assertThrows(RuntimeException.class, ()-> lot.unpark(UUID.randomUUID().toString()));
    }

    @Test
    void expectExceptionForUnparkingIfTokenDoeNotMatch() {
        ParkingLot lot = new ParkingLot(1);
        lot.park(new Vehicle("KL098279", "Black"), ParkingStrategy.NEAREST);

        assertThrows(RuntimeException.class, ()-> lot.unpark(UUID.randomUUID().toString()));
    }

    @Test
    void expectCorrectVehicleForUnparkingIfTokenMatches() {
        ParkingLot lot = new ParkingLot(1);
        Vehicle expectedVehicle = new Vehicle("KL098279", "Black");
        String token = lot.park(expectedVehicle, ParkingStrategy.NEAREST);

        assertEquals(expectedVehicle, lot.unpark(token));
    }

    @Test
    void expectLotEmptyAfterUnparking() {
        ParkingLot lot = new ParkingLot(1);
        Vehicle expectedVehicle = new Vehicle("KL098279", "Black");
        String token = lot.park(expectedVehicle, ParkingStrategy.NEAREST);

        lot.unpark(token);

        assertFalse(lot.isFull());
    }

    @Test
    void expect1FullSlotIf1VehicleParked() {
        int expected = 1;
        ParkingLot lot = new ParkingLot(10);

        lot.park(new Vehicle("JK02GH9981", "White"), ParkingStrategy.FARTHEST);

        assertEquals(expected, lot.fullSlots());
    }
}
