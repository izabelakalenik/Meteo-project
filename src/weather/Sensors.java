package weather;

public class Sensors {

    private double temperature;
    private int humidity;
    private double pressure;

    public Sensors(double temperature, int humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void values() {
        System.out.print("temperature = ");
        System.out.printf("%6.2f", temperature);
        System.out.print(" C | humidity = ");
        System.out.printf("%2d", humidity);
        System.out.print(" % | pressure = ");
        System.out.printf("%6.2f", pressure);
        System.out.print(" hPa \n");
    }
}
