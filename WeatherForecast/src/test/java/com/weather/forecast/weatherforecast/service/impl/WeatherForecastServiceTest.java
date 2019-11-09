package com.weather.forecast.weatherforecast.service.impl;

import com.weather.forecast.weatherforecast.BaseTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherForecastServiceTest extends BaseTestCase {


    @Autowired
    @InjectMocks
    WeatherForecastServiceImpl service;

    @Mock
    RestTemplate restTemplate;

    @Test
    public void testGetWeatherData_positive(){

//        when(restTemplate.exchange(Mockito.any(RequestEntity.class), Mockito.any(Class.class)));
    }

    @Test
    public void testGetWeatherData_Negative(){

//        when(restTemplate.exchange(Mockito.any(RequestEntity.class), Mockito.any(Class.class))).then
    }



}
