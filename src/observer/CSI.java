package observer;

import weather.Sensors;
import weather.WeatherStation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class CSI extends Thread implements Important {

    private List<User> listOfUsers;
    private List<String> listOfLocations;
    private WeatherStation weatherStation;

    private String l;
    private boolean howLong = true;
    Scanner sc = new Scanner(System.in);

    public CSI() {
        listOfUsers = new ArrayList<>();
        listOfLocations = new ArrayList<>();
    }

    public List<String> getListOfLocations() {
        return listOfLocations;
    }

    public void setListOfLocations(List<String> listOfLocations) {
        this.listOfLocations = listOfLocations;
    }

    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public WeatherStation getWeatherStation() {
        return weatherStation;
    }

    public void setWeatherStation(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    @Override
    public void run() {
        while (howLong) {
            getStation();
            notifyUsers(weatherStation);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public synchronized void registerUser(User u) {
        listOfUsers.add(u);
    }

    @Override
    public synchronized void removeUser(User u1) {
        listOfUsers.remove(u1);
    }

    @Override
    public synchronized void notifyUsers(WeatherStation w) {
        for (User x : listOfUsers) {
            x.update(w);
        }
    }

    public void stopNotification() {
        howLong = false;
    }


    public void listCreation() {
        File f;
        BufferedReader br = null;
        String line;

        try {
            f = new File("Locations");
            br = new BufferedReader(new FileReader(f));

            while ((line = br.readLine()) != null) {
                listOfLocations.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void subscribe() {
        System.out.println("Select the location from which you want to receive weather information: ");

        for (int i = 0; i < getListOfLocations().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + getListOfLocations().get(i));
        }

        int number;
        do {
            number = sc.nextInt();

            switch (number) {
                case 1 -> l = getListOfLocations().get(0);
                case 2 -> l = getListOfLocations().get(1);
                case 3 -> l = getListOfLocations().get(2);
                case 4 -> l = getListOfLocations().get(3);
                case 5 -> l = getListOfLocations().get(4);
                case 6 -> l = getListOfLocations().get(5);
                case 7 -> l = getListOfLocations().get(6);
                case 8 -> l = getListOfLocations().get(7);
                case 9 -> l = getListOfLocations().get(8);
                case 10 -> l = getListOfLocations().get(9);
                default -> System.out.println("Choose other number");
            }

        } while (!(number >= 1 && number <= 10));

        System.out.println("You are a subscriber now!\n(Please wait a moment for a new update of measurements)");

    }


    public Sensors getMeasurements() {

        double tmp = ThreadLocalRandom.current().nextDouble(-20, 35);
        int hum = ThreadLocalRandom.current().nextInt(0,100);
        double pre = ThreadLocalRandom.current().nextDouble(890, 1050);

        Sensors s = new Sensors(tmp, hum, pre);

        return s;
    }

    public WeatherStation getStation() {
        weatherStation = new WeatherStation(getL(), getMeasurements());
        return weatherStation;
    }


}
