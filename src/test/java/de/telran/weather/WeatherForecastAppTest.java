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
    public void testExecute() throws Exception {
        when(inputOutputService.readValue()).thenReturn("berlin");
        when(service.getWeatherByCityName(anyString())).thenReturn("0.33");
        doNothing().when(inputOutputService).print("0.33");

        WeatherForecastApp weatherForecastApp=new WeatherForecastApp(inputOutputService, service);
        weatherForecastApp.execute();

        verify(inputOutputService, times(1)).readValue();
        verify(service, times(1)).getWeatherByCityName(anyString());
        verify(inputOutputService, times(1)).print(anyString());
    }
}