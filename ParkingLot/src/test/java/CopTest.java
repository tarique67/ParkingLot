import org.example.*;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CopTest {

    @Test
    void expectCopNotifiedWhenLotIsFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        Observer observer = mock(Cop.class);
        Vehicle vehicle = new Vehicle("JH01BG5544", "Blacl");
        NotificationBus.getInstance().subscribe(observer, ParkingLotEvent.FULL);

        parkingLot.park(vehicle);

        verify(observer).notify(ParkingLotEvent.FULL, parkingLot);
    }

    @Test
    void expectCopNotifiedWhenParkingLotHasEmptySlot() {
        ParkingLot lot = new ParkingLot(2);
        Observer observer = mock(Cop.class);
        NotificationBus.getInstance().subscribe(observer, ParkingLotEvent.EMPTY);
        Vehicle vehicle = new Vehicle("JK09Bh9876", "Red");
        Vehicle vehicle2 = new Vehicle("JK09Bh9876", "Red");
        String token1 = lot.park(vehicle);
        String token2 = lot.park(vehicle2);

        lot.unpark(token1);

        verify(observer).notify(ParkingLotEvent.EMPTY, lot);
    }
}
