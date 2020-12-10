package de.telran.weather;

import de.telran.weather.service.InputOutputService;
import de.telran.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

/**
 * weatherForecast
 * 10.12.20 17 56
 */


class WeatherForecastAppTest {
    InputOutputService inputOutputService = mock(InputOutputService.class);
    WeatherService service = mock(WeatherService.class);

    @Test
    public void execute() throws Exception {
        when(inputOutputService.readValue()).thenReturn(anyString());
        when(service.getWeatherByCityName(anyString())).thenReturn(anyString());
        inputOutputService.print(anyString());

        verify(inputOutputService, times(1)).readValue();
        verify(service, times(1)).getWeatherByCityName(anyString());
        verify(inputOutputService, times(1)).print(anyString());
    }
}