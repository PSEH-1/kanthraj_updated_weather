package com.weather.forecast.weatherforecast.controller;

import java.util.List;

import com.weather.forecast.weatherforecast.data.WeatherData;
import com.weather.forecast.weatherforecast.service.WeatherForecastService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forecast")
public class WeatherForecastController {

    @Autowired
    private WeatherForecastService weatherForeCastService;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    
    @GetMapping(value = "/forecast/general")
    public WeatherData getWeatherForecast(@RequestParam String city){

       return weatherForeCastService.getWeather(city);
    }
    
    @GetMapping(value = "/forecast/general/3")
    public  List<WeatherData> getWeatherForecast3day(@RequestParam String city){

       return weatherForeCastService.getWeather3Days(city);
    }


    @RequestMapping(value = "/forecast/{city}/lowTemperature", method=RequestMethod.GET)
    public String getWeatherLowTemp(@PathVariable("city") final String city)
    {

        return weatherForeCastService.getWeatherForLowTemp(city);
    }

    @RequestMapping(value = "/forecast/{city}highTemperature", method=RequestMethod.GET)
    public String getWeatherHighTemp(@PathVariable("city") final String city)
    {
   return weatherForeCastService.getWeatherForHighTemp(city);
    }
    
    @GetMapping(value = "/forecast/highTemp")
    public String getaWeatherHighTempe(@RequestParam String city)
    {
   return weatherForeCastService.getWeatherForHighTemp(city);
    }

}
