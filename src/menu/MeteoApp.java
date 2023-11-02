package menu;

import com.google.gson.Gson;
import observer.CSI;
import observer.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;


public class MeteoApp {

    private final CSI csiK;
    private User us;
    private final ArrayList<User> listOfUsersWithAccounts = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);


    public MeteoApp(CSI csiK) {
        this.csiK = csiK;
    }

    public void mainMenu() {
        int n = 0;

        while (n != 5) {
            System.out.println("Welcome to Meteo App!");
            System.out.println("[1] Create user account");
            System.out.println("[2] Login user");
            System.out.println("[3] Show the list of users who have the account");
            System.out.println("[4] Show the list of users who are subscribers");
            System.out.println("[5] Exit");

            n = scanner.nextInt();

            switch (n) {
                case 1:
                    createAccount();
                    menu();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    usersWithAccounts();
                    break;
                case 4:
                    usersSubscribers();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Choose other number");
            }
        }
    }

    public void menu() {

        int nu = 0;
        while (nu != 8) {
            System.out.println("[1] Subscribe location for weather information");
            System.out.println("[2] Show the list of subscribed locations");
            System.out.println("[3] Show the list of measurements for subscribed locations");
            System.out.println("[4] Analyze measurements data");
            System.out.println("[5] Export list of measurement to JSON file");
            System.out.println("[6] Resign from subscription");
            System.out.println("[7] More about Meteo App");
            System.out.println("[8] Log out");
            nu = scanner.nextInt();

            switch (nu) {
                case 1:
                    csiK.subscribe();
                    csiK.registerUser(us);
                    break;
                case 2:
                    subscribedLocations();
                    break;
                case 3:
                    show();
                    break;
                case 4:
                    average();
                    max();
                    min();
                    break;
                case 5:
                    exportToFile();
                    break;
                case 6:
                    resign();
                    break;
                case 7:
                    newApp();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Choose other number");
            }
        }
    }


    public void createAccount() {
        System.out.println("Enter your data to create an account: ");
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Surname: ");
        String surname = scanner.nextLine();

        System.out.print("E-mail address: ");
        String email = scanner.nextLine();

        us = new User(csiK, name, surname, email);

        if (listOfUsersWithAccounts.isEmpty()) {
            listOfUsersWithAccounts.add(us);
            System.out.println("Your account has been successfully created!");
        } else {
            if (listOfUsersWithAccounts.contains(us)) {
                System.out.println("You have already had an account!");
            } else {
                listOfUsersWithAccounts.add(us);
                System.out.println("Your account has been successfully created!");
            }
        }
    }

    public void login() {
        System.out.println("Enter your data to log in: ");
        scanner.nextLine();

        System.out.print("Name: ");
        String nameL = scanner.nextLine();

        System.out.print("Surname: ");
        String surnameL = scanner.nextLine();

        User uL = new User(csiK, nameL, surnameL);

        if (listOfUsersWithAccounts.isEmpty()) {
            System.out.println("There are no registered users yet!");
        } else {

            if (listOfUsersWithAccounts.contains(uL)) {
                for (User listOfUsersWithAccount : listOfUsersWithAccounts) {
                    if (listOfUsersWithAccount.toString().equals(uL.toString())) {
                        us = listOfUsersWithAccount;
                    }
                }
                System.out.println("You have been successfully logged in!");
                menu();
            } else {
                System.out.println("You have to be registered if you want to log in!");
                uL = null;
            }
        }
    }

    public void usersSubscribers() {
        TreeSet<String> usersSList = new TreeSet<>();

        for (int i = 0; i < csiK.getListOfUsers().size(); i++) {
            usersSList.add(csiK.getListOfUsers().get(i).toString());
        }

        int j = 0;
        for (String u : usersSList) {
            j++;
            System.out.println("[" + (j) + "] " + u);
        }

        if (csiK.getListOfUsers().isEmpty()) {
            System.out.println("There are no users with subscription yet!");
        }
    }

    public void usersWithAccounts() {
        int i = 0;
        for (User e : listOfUsersWithAccounts) {
            i++;
            System.out.println(i + ". " + e + ", " + e.getEmail());
        }

        if (listOfUsersWithAccounts.isEmpty()) {
            System.out.println("There are no users with account yet!");
        }
    }


    public void resign() {

        if (csiK.getListOfUsers().contains(us)) {
            System.out.println("Are you sure you want to resign from subscription?");
            System.out.println("[1] Yes");
            System.out.println("[2] No");

            int r;
            do {
                r = scanner.nextInt();
                switch (r) {
                    case 1 -> {
                        csiK.removeUser(us);
                        System.out.println("You have been successfully removed from subscription list!");
                    }
                    case 2 -> System.out.println("Good choice!");
                    default -> System.out.println("Choose other number");
                }
            } while (!(r >= 1 && r <= 2));

        } else {
            System.out.println("You are not a subscriber!");
        }
    }

    public void show() {

        if (csiK.getListOfUsers().contains(us)) {
            for (int i = 0; i < us.getListOfMeasurements().size(); i++) {
                us.getListOfMeasurements().get(i).data();
            }
        } else {
            System.out.println("There are no measurements available yet!");
        }
    }

    public void subscribedLocations() {

        if (csiK.getListOfUsers().contains(us)) {
            int i = 0;
            for (String s : us.getListOfSubscribedLocations()) {
                i++;
                System.out.println(i + ". " + s);
            }
        } else {
            System.out.println("There are no subscribed locations yet!");
        }
    }

    public void exportToFile() {
        Gson gson = new Gson();
        File f;
        BufferedWriter bw = null;

        if (csiK.getListOfUsers().contains(us)) {
            System.out.println("Enter the name of your file: ");
            scanner.nextLine();
            String n = scanner.nextLine();

            System.out.println("Do you want to export your file to JSON?");
            System.out.println("[1] Yes");
            System.out.println("[2] No");

            int nr;
            do {
                nr = scanner.nextInt();
                switch (nr) {
                    case 1:
                        try {
                            f = new File(n + ".json");
                            bw = new BufferedWriter(new FileWriter(f));

                            bw.write(gson.toJson(us.getListOfMeasurements()));

                            System.out.println("File will create when you exit Meteo App!");

                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                bw.close();
                            } catch (IOException exc) {
                                exc.printStackTrace();
                            }
                        }
                        break;
                    case 2:
                        System.out.println("What a pity!");
                        break;
                    default:
                        System.out.println("Choose other number");
                        break;
                }
            } while (!(nr >= 1 && nr <= 2));
        } else {
            System.out.println("You are not a subscriber, so measurements are not available!");
        }
    }

    public void newApp() {
        System.out.println("If you want to know more about our new app Super Geo, we will send information on your e-mail:\n" + us.getEmail());

        System.out.println("Do you want to receive information?");
        System.out.println("[1] Yes");
        System.out.println("[2] No");

        int r;
        do {
            r = scanner.nextInt();
            switch (r) {
                case 1 -> System.out.println("Information has been sent!");
                case 2 -> System.out.println("Your loss!");
                default -> System.out.println("Choose other number");
            }
        } while (!(r >= 1 && r <= 2));
    }

    public void average() {
        if (csiK.getListOfUsers().contains(us)) {
            double t = 0;
            double h = 0;
            double p = 0;

            int num = 0;
            for (int i = 0; i < us.getListOfMeasurements().size(); i++) {
                t += us.getListOfMeasurements().get(i).getSensors().getTemperature();
                h += us.getListOfMeasurements().get(i).getSensors().getHumidity();
                p += us.getListOfMeasurements().get(i).getSensors().getPressure();
                num++;
            }
            t = t / num;
            h = h / num;
            p = p / num;

            displayData(t, h, p, "Average");
        } else {
            System.out.println("There are no data to analyze!");
        }
    }

    public void min() {
        if (csiK.getListOfUsers().contains(us)) {
            double tMin = us.getListOfMeasurements().get(0).getSensors().getTemperature();
            double hMin = us.getListOfMeasurements().get(0).getSensors().getHumidity();
            double pMin = us.getListOfMeasurements().get(0).getSensors().getPressure();

            for (int i = 0; i < us.getListOfMeasurements().size(); i++) {
                if (us.getListOfMeasurements().get(i).getSensors().getTemperature() < tMin) {
                    tMin = us.getListOfMeasurements().get(i).getSensors().getTemperature();
                }
                if (us.getListOfMeasurements().get(i).getSensors().getHumidity() < hMin) {
                    hMin = us.getListOfMeasurements().get(i).getSensors().getHumidity();
                }
                if (us.getListOfMeasurements().get(i).getSensors().getPressure() < pMin) {
                    pMin = us.getListOfMeasurements().get(i).getSensors().getPressure();
                }
            }
            displayData(tMin, hMin, pMin, "Minimum");
        }

    }

    public void max() {
        if (csiK.getListOfUsers().contains(us)) {
            double tMax = us.getListOfMeasurements().get(0).getSensors().getTemperature();
            double hMax = us.getListOfMeasurements().get(0).getSensors().getHumidity();
            double pMax = us.getListOfMeasurements().get(0).getSensors().getPressure();

            for (int i = 0; i < us.getListOfMeasurements().size(); i++) {
                if (us.getListOfMeasurements().get(i).getSensors().getTemperature() > tMax) {
                    tMax = us.getListOfMeasurements().get(i).getSensors().getTemperature();
                }
                if (us.getListOfMeasurements().get(i).getSensors().getHumidity() > hMax) {
                    hMax = us.getListOfMeasurements().get(i).getSensors().getHumidity();
                }
                if (us.getListOfMeasurements().get(i).getSensors().getPressure() > pMax) {
                    pMax = us.getListOfMeasurements().get(i).getSensors().getPressure();
                }
            }
            displayData(tMax, hMax, pMax, "Maximum");
        }
    }

    public void displayData(double t, double h, double p, String type) {
        System.out.println(type + " values of measurements for this location: " + csiK.getWeatherStation().getLocation());
        System.out.print("temperature = ");
        System.out.printf("%.2f", t);
        System.out.println();
        System.out.print("humidity = ");
        System.out.printf("%.2f", h);
        System.out.println();
        System.out.print("pressure = ");
        System.out.printf("%.2f", p);
        System.out.println();
    }
}
