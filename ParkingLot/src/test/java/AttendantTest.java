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
    void expectAttendantAbleToUnparkFromParkingLotAssigned() {
        Attendant attendant = new Attendant();
        ParkingLot lot = new ParkingLot(1);
        attendant.assign(lot);
        String token = attendant.park(new Vehicle("KA04AD", "Black"));
        assertTrue(lot.isFull());
        attendant.unpark(token);

        assertFalse(lot.isFull());
    }

    @Test
    void expectTwoAttendantAbleToUnparkFromSameLotAfterParking() {
        Attendant firstAttendant = new Attendant();
        Attendant secondAttendant = new Attendant();
        ParkingLot lot = new ParkingLot(2);
        firstAttendant.assign(lot);
        secondAttendant.assign(lot);
        String firstToken = firstAttendant.park(new Vehicle("KA04AD", "Black"));
        String secondToken = secondAttendant.park(new Vehicle("JH01KP", "Black"));

        firstAttendant.unpark(firstToken);
        secondAttendant.unpark(secondToken);
        assertFalse(lot.isFull());
    }

    @Test
    void expectExceptionIfUnassignedAttendantTriesToUnpark() {
        Attendant assignedAttendant = new Attendant();
        Attendant unassignedAttendant = new Attendant();
        ParkingLot lot = new ParkingLot(1);
        assignedAttendant.assign(lot);
        String firstToken = assignedAttendant.park(new Vehicle("KA04AD", "Black"));
        assertThrows(RuntimeException.class, ()-> unassignedAttendant.unpark(firstToken));
    }

    @Test
    void expectExceptionIfAttendantWithDifferentParkingLotAssignedTriesToUnparkFromDifferenParkingLot() {
        Attendant firstAttendant = new Attendant();
        Attendant secondAttendant = new Attendant();
        ParkingLot firstLot = new ParkingLot(1);
        ParkingLot secondLot = new ParkingLot(1);
        firstAttendant.assign(firstLot);
        secondAttendant.assign(secondLot);
        String firstToken = firstAttendant.park(new Vehicle("KA04AD", "Black"));

        assertThrows(RuntimeException.class, ()-> secondAttendant.unpark(firstToken));
    }
}
