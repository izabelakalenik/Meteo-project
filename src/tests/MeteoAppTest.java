package tests;

import menu.MeteoApp;
import observer.CSI;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

public class MeteoAppTest {

    @Test
    public void usersSubscribersTestFalse(){
        CSI c = mock(CSI.class);
        MeteoApp k = new MeteoApp(c);
        k.usersSubscribers();
    }

    @Test
    public void showTestFalse() {
        CSI c = mock(CSI.class);
        MeteoApp k = new MeteoApp(c);
        k.show();
    }

    @Test
    public void subscribedLocationFalse(){
        CSI c = mock(CSI.class);
        MeteoApp k = new MeteoApp(c);
        k.subscribedLocations();
    }
}
