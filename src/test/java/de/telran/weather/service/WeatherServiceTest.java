package de.telran.weather.service;

import de.telran.weather.entity.ConsolidatedWeather;
import de.telran.weather.entity.Forecast;
import de.telran.weather.entity.SearchResult;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class WeatherServiceTest {
    private WeatherGateway gateway = mock(WeatherGateway.class);

    @Test
    public void testGetWeatherByCityName() throws Exception {
        //1. configure mock
        when(gateway.findCityByName(eq("Berlin"))).thenReturn(createSearchResult());
        when(gateway.getWeatherByWoeid(eq("noncity"))).thenThrow(Exception.class);
        when(gateway.getWeatherByWoeid(anyString())).thenReturn(createTestForecast());

        //2. execute testing method
        WeatherService service = new WeatherService(gateway);

        Optional<String> weatherInBerlin = service.getWeatherByCityName("Berlin");
        //3. verify result
        assertEquals("25", weatherInBerlin.get());
        verify(gateway, times(1)).findCityByName(eq("Berlin"));
        verify(gateway, times(1)).getWeatherByWoeid(eq("1234"));

    }

    private SearchResult[] createSearchResult() {
        SearchResult result = new SearchResult("1234");
        SearchResult[] results = {result};
        return results;
    }

    private Forecast createTestForecast() {
        ConsolidatedWeather weather = new ConsolidatedWeather("25");
        ConsolidatedWeather[] results = {weather};
        Forecast forecast = new Forecast(results);
        return forecast;
    }

}