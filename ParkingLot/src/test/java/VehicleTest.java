import org.example.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    @Test
    void expectTrueWhenEquatingTwoSameVehicles() {
        Vehicle vehicle = new Vehicle("JH015564","White");

        assertEquals(vehicle, new Vehicle("JH015564", "White"));
    }

    @Test
    void expectFalseWhenCheckingDifferentColorForVehicle() {
        assertFalse(new Vehicle("JH01888", "Black").colorAvailable("white"));
    }

    @Test
    void expectTrueWhenCheckingSameColorForVehicle() {
        assertTrue(new Vehicle("JH01888", "Black").colorAvailable("black"));
    }

}
