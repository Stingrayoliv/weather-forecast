package de.telran.weather;

import de.telran.weather.service.InputOutputService;
import de.telran.weather.service.WeatherService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class WeatherForecastAppTest {
    InputOutputService inputOutputService = mock(InputOutputService.class);
    WeatherService service = mock(WeatherService.class);

    @Test
    public void testExecute() throws Exception {
        when(inputOutputService.readValue()).thenReturn("berlin");
        when(service.getWeatherByCityName(eq("berlin"))).thenReturn(java.util.Optional.of("0.33"));
        doNothing().when(inputOutputService).print("0.33");

        WeatherForecastApp weatherForecastApp = new WeatherForecastApp(inputOutputService, service);
        weatherForecastApp.execute();

        verify(inputOutputService, times(1)).readValue();
        verify(service, times(1)).getWeatherByCityName(anyString());
    }
}