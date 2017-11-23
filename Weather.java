package net.knightsys.jarvis;

/**
 * Created by root on 11/23/17.
 */

public class Weather extends Card {
    public Weather(int pType) {
        super(CardType.Weather);
        this.Details = GetWeather("77447");
    }

    public String GetWeather(String Location)
    {
        return Location;
    }
}
