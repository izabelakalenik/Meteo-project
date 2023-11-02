package weather;

public class WeatherStation {

    private String location;
    private Sensors sensors;


    public WeatherStation(String localization, Sensors sensors) {
        this.location = localization;
        this.sensors = sensors;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Sensors getSensors() {
        return sensors;
    }

    public void setSensors(Sensors sensors) {
        this.sensors = sensors;
    }

    public void data() {
        System.out.print(getLocation() + " ---> ");
        getSensors().values();
    }

}
