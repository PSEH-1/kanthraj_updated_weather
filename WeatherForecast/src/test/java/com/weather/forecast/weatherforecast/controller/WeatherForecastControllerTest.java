package com.weather.forecast.weatherforecast.controller;

import com.weather.forecast.weatherforecast.BaseTestCase;
import com.weather.forecast.weatherforecast.data.WeatherData;
import com.weather.forecast.weatherforecast.service.WeatherForecastService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.anyString;

public class WeatherForecastControllerTest extends BaseTestCase {

    @Autowired
            @InjectMocks
    WeatherForecastController controller;

    @Mock
    WeatherForecastService weatherForecastService;

    @Test
    public void testGetWeatherPositive(){

        WeatherData data = new WeatherData();
        Mockito.when(weatherForecastService.getWeather(anyString())).thenReturn(data);
        WeatherData resultData = controller.getWeatherForecast("Pune");
        Assert.assertEquals(data, resultData);
    }



    @Test(expected = RuntimeException.class)
    public void testGetWeatherNegative(){

        WeatherData data = new WeatherData();
        Mockito.when(weatherForecastService.getWeather("")).thenThrow(new RuntimeException());
        controller.getWeatherForecast("");

    }

}
