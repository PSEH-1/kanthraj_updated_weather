package com.weather.forecast.weatherforecast.service;

import java.util.List;

import com.weather.forecast.weatherforecast.data.WeatherData;

public interface WeatherForecastService {
     WeatherData getWeather(String city);
     List<WeatherData> getWeather3Days(String city);
     String getWeatherForLowTemp(String city);
     String getWeatherForHighTemp(String city);
}
