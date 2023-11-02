package menu;

import observer.CSI;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        CSI csi = new CSI();
        csi.listCreation();
        csi.start();

        MeteoApp MeteoApp = new MeteoApp(csi);
        MeteoApp.mainMenu();

        try {
            Thread.sleep(1000);
            csi.stopNotification();
            csi.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
