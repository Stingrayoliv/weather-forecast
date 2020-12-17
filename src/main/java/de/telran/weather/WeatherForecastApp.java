package de.telran.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import de.telran.weather.service.InputOutputService;
import de.telran.weather.service.WeatherGateway;
import de.telran.weather.service.WeatherService;

import java.util.Optional;

public class WeatherForecastApp {
    private InputOutputService inputOutputService;
    private WeatherService service;

    public WeatherForecastApp(InputOutputService inputOutputService, WeatherService service) {
        this.inputOutputService = inputOutputService;
        this.service = service;
    }

    public void execute() throws Exception {
        String s = inputOutputService.readValue();
        Optional<String> weatherByCityName = service.getWeatherByCityName(s);
        if (weatherByCityName.isPresent()){
            inputOutputService.print(weatherByCityName.get());
        }else {
            inputOutputService.print("No result for city "+s);
        }

    }

    public static void main(String[] args) throws Exception {
        InputOutputService inputOutputService = new InputOutputService();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        WeatherGateway gateway = new WeatherGateway(mapper);
        WeatherService weatherService = new WeatherService(gateway);
        WeatherForecastApp app = new WeatherForecastApp(inputOutputService, weatherService);
        app.execute();
    }
}
