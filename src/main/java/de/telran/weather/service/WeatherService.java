package de.telran.weather.service;

import de.telran.weather.WeatherForecastApp;
import de.telran.weather.entity.Forecast;
import de.telran.weather.entity.SearchResult;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class WeatherService {

    private WeatherGateway gateway;
    protected static final Logger log= LogManager.getLogger(WeatherGateway.class);
    public WeatherService(WeatherGateway gateway) {
        this.gateway = gateway;
    }

    public Optional<String> getWeatherByCityName(String cityName) throws Exception{
        SearchResult[] cityByName = gateway.findCityByName(cityName);

        if (cityByName.length == 0) {
            log.error("City "+cityName+" not found");
            return Optional.empty();
        }
        Forecast forecast = gateway.getWeatherByWoeid(cityByName[0].getWoeid());
        return Optional.of(forecast.getConsolidatedWeather()[0].getTheTemp());
    }
}
