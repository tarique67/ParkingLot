import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NotificationBusTest {

    @Test
    void expectNotificationBusCreated() {
        assertNotNull(NotificationBus.getInstance());
    }

    @Test
    void expectAttendantNotifiedWhenParkingLotFull() {
        ParkingLot lot = new ParkingLot(1);
        Observer observer = mock(Attendant.class);
        NotificationBus.getInstance().subscribe(observer, ParkingLotEvent.FULL);
        Vehicle vehicle = new Vehicle("JK09Bh9876", "Red");

        lot.park(vehicle);

        verify(observer).notify(ParkingLotEvent.FULL, lot);
    }

    @Test
    void expectCopNotifiedWhenParkingLotFull() {
        ParkingLot lot = new ParkingLot(1);
        Observer observer = mock(Cop.class);
        NotificationBus.getInstance().subscribe(observer, ParkingLotEvent.FULL);
        Vehicle vehicle = new Vehicle("JK09Bh9876", "Red");

        lot.park(vehicle);

        verify(observer).notify(ParkingLotEvent.FULL, lot);
    }
}
