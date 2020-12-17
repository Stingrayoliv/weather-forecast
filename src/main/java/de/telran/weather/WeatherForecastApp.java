package de.telran.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import de.telran.weather.service.InputOutputService;
import de.telran.weather.service.WeatherGateway;
import de.telran.weather.service.WeatherService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Optional;

public class WeatherForecastApp {
    private InputOutputService inputOutputService;
    private WeatherService service;

    protected static final Logger log= LogManager.getLogger(WeatherForecastApp.class);

    public WeatherForecastApp(InputOutputService inputOutputService, WeatherService service) {
        this.inputOutputService = inputOutputService;
        this.service = service;
    }

    public void execute() throws Exception {
        log.info("Application started");
        String s = inputOutputService.readValue();
        Optional<String> weatherByCityName = service.getWeatherByCityName(s);
        if (weatherByCityName.isPresent()){
            inputOutputService.print(weatherByCityName.get());
        }else {
            inputOutputService.print("No result for city "+s);
        }
        log.info("Application finished");
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
