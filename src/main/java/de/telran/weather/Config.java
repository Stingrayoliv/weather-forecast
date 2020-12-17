package de.telran.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import de.telran.weather.service.InputOutputService;
import de.telran.weather.service.WeatherGateway;
import de.telran.weather.service.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public InputOutputService getInputOutputService(){
        return new InputOutputService();
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        ObjectMapper mapper=new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return mapper;
    }

    @Bean
    public WeatherGateway getWeatherGateway(ObjectMapper mapper){
        return new WeatherGateway(mapper);
    }

    @Bean
    public WeatherService getWeatherService(WeatherGateway gateway){
        return new WeatherService(gateway);
    }

    @Bean
    public WeatherForecastApp app(InputOutputService inputOutputService, WeatherService service){
        return new WeatherForecastApp(inputOutputService, service);
    }
}
