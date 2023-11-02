package observer;


import weather.WeatherStation;

public interface Important {

    void registerUser(User u);

    void removeUser(User u);

    void notifyUsers(WeatherStation w);
}
