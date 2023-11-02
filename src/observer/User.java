package observer;

import weather.WeatherStation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class User implements BigBrain {

    private String name;
    private String surname;
    private String email;
    private final CSI csiU;
    private List<WeatherStation> listOfMeasurements = new ArrayList<>();
    private TreeSet<String> listOfSubscribedLocations = new TreeSet<>();

    public User(CSI csiU, String name, String surname, String email) {
        this.csiU = csiU;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public User(CSI csiU, String name, String surname) {
        this.csiU = csiU;
        this.name = name;
        this.surname = surname;
    }

    public List<WeatherStation> getListOfMeasurements() {
        return listOfMeasurements;
    }

    public void setListOfMeasurements(List<WeatherStation> listOfMeasurements) {
        this.listOfMeasurements = listOfMeasurements;
    }

    public TreeSet<String> getListOfSubscribedLocations() {
        return listOfSubscribedLocations;
    }

    public void setListOfSubscribedLocations(TreeSet<String> listOfSubscribedLocations) {
        this.listOfSubscribedLocations = listOfSubscribedLocations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return name + " " + surname;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof User us){
            return this.toString().equals(us.toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public void update(WeatherStation w) {
        receiveInformation(w);
    }

    public void receiveInformation(WeatherStation ws) {
        listOfMeasurements.add(ws);
        listOfSubscribedLocations.add(ws.getLocation());
    }

}
