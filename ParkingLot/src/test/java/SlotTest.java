import org.example.ParkingLot;
import org.example.Slot;
import org.example.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class SlotTest {

    @Test
    void expectExceptionWhenParkingInOccupiedSlot() {
        Slot slot = new Slot(0);
        slot.park(new Vehicle("KJ212", "Black"));
        assertThrows(Exception.class,()-> slot.park(new Vehicle("BT212", "Black")));
    }

    @Test
    void expectNoExceptionWhenParkingInUnoccupiedSlot() {
        Slot slot = new Slot(0);
        assertDoesNotThrow(()-> slot.park(new Vehicle("BT212", "Black")));
    }

    @Test
    void expectTrueWhenCheckingColorAvailableOfCarInSlotWithSameColorVehicle() {
        Slot slot = new Slot(0);
        slot.park(new Vehicle("KJ212", "Black"));
        assertTrue(slot.vehicleColorAvailable("black"));
    }

    @Test
    void expectFalseWhenCheckingColorAvailableOfCarInSlotWithDifferentColorVehicle() {
        Slot slot = new Slot(0);
        assertFalse(slot.vehicleColorAvailable("white"));
    }

    @Test
    void expectFalseWhenCheckingTokenMatchesIfTokenProvidedMatchesSlotToken() {
        Slot slot = new Slot(0);
        String token = slot.park(new Vehicle("JH01KH09876","Black"));
        String anotherToken = UUID.randomUUID().toString();

        assertFalse(slot.tokenMatches(anotherToken));
    }

    @Test
    void expectTrueWhenCheckingTokenMatchesIfTokenProvidedMatchesSlotToken() {
        Slot slot = new Slot(0);
        String token = slot.park(new Vehicle("JH01KH09876","Black"));

        assertTrue(slot.tokenMatches(token));
    }

    @Test
    void expectExceptionForUnparkIfTokenDoesntMatch() {
        Slot slot = new Slot(1);
        slot.park(new Vehicle("BR01KJ0088", "Blue"));
        assertThrows(Exception.class, ()-> slot.unpark(UUID.randomUUID().toString()));
    }

    @Test
    void expectCorrectVehicleForUnparkIfTokenMatches() {
        Vehicle vehicle = new Vehicle("BR01KJ0088", "Blue");
        Slot slot = new Slot(1);
        String token = slot.park(vehicle);

        assertEquals(vehicle, slot.unpark(token));
    }

    @Test
    void expectFalseWhenCheckingIsVehicleParkedOnEmptySlot() {
        Slot slot = new Slot(1);
        assertFalse(slot.isVehicleParked());
    }

    @Test
    void expectTrueWhenCheckingIsVehicleParkedOnOccupiedSlot() {
        Slot slot = new Slot(1);
        slot.park(new Vehicle("KA12e", "RED"));
        assertTrue(slot.isVehicleParked());
    }
}
