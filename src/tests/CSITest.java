package tests;

import observer.CSI;
import org.junit.jupiter.api.Test;
import weather.Sensors;
import weather.WeatherStation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;



public class CSITest {

    @Test
    public void getMeasurementsTest(){
        CSI tester = new CSI();
        Sensors testS = new Sensors(22,44,900);

        assertEquals(testS.getClass(), tester.getMeasurements().getClass());
    }

    @Test
    public void getStationTest(){
        CSI tester = new CSI();
        Sensors sMocked = mock(Sensors.class);
        WeatherStation w = new WeatherStation("Wrocław", sMocked);

        assertEquals(w.getClass(), tester.getStation().getClass());
    }
}
