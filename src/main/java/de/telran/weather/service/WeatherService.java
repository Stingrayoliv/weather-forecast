package de.telran.weather.service;

import de.telran.weather.entity.Forecast;
import de.telran.weather.entity.SearchResult;

import java.util.Optional;

public class WeatherService {

    private WeatherGateway gateway;

    public WeatherService(WeatherGateway gateway) {
        this.gateway = gateway;
    }

    public Optional<String> getWeatherByCityName(String cityName) throws Exception{
        SearchResult[] cityByName = gateway.findCityByName(cityName);

        if (cityByName.length == 0) {
            return Optional.empty();
        }
        Forecast forecast = gateway.getWeatherByWoeid(cityByName[0].getWoeid());
        return Optional.of(forecast.getConsolidatedWeather()[0].getTheTemp());
    }
}
