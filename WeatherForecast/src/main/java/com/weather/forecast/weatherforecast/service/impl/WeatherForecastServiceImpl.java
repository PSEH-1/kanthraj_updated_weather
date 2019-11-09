package com.weather.forecast.weatherforecast.service.impl;

import com.weather.forecast.weatherforecast.data.WeatherData;
import com.weather.forecast.weatherforecast.service.WeatherForecastService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

    @Value("http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={key}")
    private String weatherForecastURL;
    @Value("04d0ed4b058b56bedc9e01dfbbaa3435")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WeatherForecastServiceImpl() {
        restTemplate = new RestTemplate();
    }

    public String getWeatherForHighTemp(String city)
    {
        Map<String, Object> map = getRestOutputMap(weatherForecastURL+city+weatherForecastURL);
        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");

        Double retValue = 0.0;

        for(Map<String, Object> obj : list)
        {
            Map<String, Object> temperature = (Map<String, Object>)obj.get("main");
            Double temperature2 = (Double)temperature.get("temp");
            temperature2 = (Double) (temperature2 - 273.15);
            if(temperature2 > retValue)
                retValue = temperature2;
        }
        return roundTemperature(retValue);
    }

    @Override
    public String getWeatherForLowTemp(String city)
    {
        Map<String, Object> map = getRestOutputMap(weatherForecastURL+city+weatherForecastURL);
        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");

        Double retValue = 0.0;

        for(Map<String, Object> obj : list)
        {
            Map<String, Object> temperature = (Map<String, Object>)obj.get("main");
            Double temperature2 = (Double)temperature.get("temp");
            temperature2 = (temperature2 - 273.15);
            if(temperature2 < retValue)
                retValue = temperature2;
        }
        return roundTemperature(retValue);
    }

    public WeatherData getWeather(String city){
        WeatherData weather = null;
        if(validParameters(city)) {
            URI url = new UriTemplate(this.weatherForecastURL).expand(city, this.apiKey);

            weather = invoke(url, WeatherData.class);
        }
        if(weather.getTemperature()>=40){
        	weather.setWeatherMain("Carry umbrella or Use sunscreen lotion");
        }
        return weather;
    }

    private boolean validParameters(String city) {
        return city !=null && !"".equals(city) && apiKey !=null && !"".equals(apiKey) && weatherForecastURL!=null && !"".equals(weatherForecastURL);
    }

    private <T> T invoke(URI url, Class<T> responseType){
        T weather = null;
        try {
            RequestEntity<?> request = RequestEntity.get(url)
                    .accept(MediaType.APPLICATION_JSON).build();
            ResponseEntity<T> exchange = restTemplate
                    .exchange(request, responseType);
            weather = exchange.getBody();
        } catch(Exception e){
            e.printStackTrace();
        }

        return weather;
    }


    private Map<String, Object> getRestOutputMap(String url)
    {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        JsonParser parser = JsonParserFactory.getJsonParser();
        return parser.parseMap(result);

    }


    private String roundTemperature(Double temp)
    {
        DecimalFormat df2 = new DecimalFormat("#.##");
        return df2.format(temp)+" C";

    }

	@Override
	public List<WeatherData> getWeather3Days(String city) {
		List<WeatherData> threeDaylist = new ArrayList<>();

        WeatherData weather = null;
        if(validParameters(city)) {
            URI url = new UriTemplate(this.weatherForecastURL).expand(city, this.apiKey);

            weather = invoke(url, WeatherData.class);
        }
        if(weather.getTemperature()>=40){
        	weather.setWeatherMain("Carry umbrella or Use sunscreen lotion");
        }
        threeDaylist.add(weather);
        threeDaylist.add(weather);
        threeDaylist.add(weather);
        
        return threeDaylist;
	}
}
