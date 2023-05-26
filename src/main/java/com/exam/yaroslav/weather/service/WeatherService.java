package com.exam.yaroslav.weather.service;

import com.exam.yaroslav.weather.model.WeatherDataDTO;
import com.exam.yaroslav.weather.model.WeatherDataStatisticsDTO;
import com.exam.yaroslav.weather.model.WeatherEntity;
import com.exam.yaroslav.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    /**
     *
     * @param weatherDataDTO
     * @return
     */
    public WeatherEntity createWeatherData(WeatherDataDTO weatherDataDTO) {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setDate(weatherDataDTO.getDate());
        weatherEntity.setType(weatherDataDTO.getType());
        weatherEntity.setTemperature(weatherDataDTO.getTemperature());
        weatherEntity.setWind_speed(weatherDataDTO.getWind_speed());
        return weatherRepository.save(weatherEntity);
    }

    /**
     *
     * @param date
     * @param weatherDataDTO
     * @return
     */
    public WeatherEntity updateWeatherData(Date date, WeatherDataDTO weatherDataDTO) {
        Optional<WeatherEntity> optionalWeatherEntity = Optional.ofNullable(this.searchDataByDate(date));
        if (optionalWeatherEntity.isPresent()) {
            WeatherEntity weatherEntity = optionalWeatherEntity.get();
            weatherEntity.setDate(weatherDataDTO.getDate());
            weatherEntity.setType(weatherDataDTO.getType());
            weatherEntity.setTemperature(weatherDataDTO.getTemperature());
            weatherEntity.setWind_speed(weatherDataDTO.getWind_speed());
            return weatherRepository.save(weatherEntity);
        }
        return null;
    }

    /**
     *
     * @param date
     * @return
     */
    public WeatherEntity searchDataByDate(Date date) {
        Optional<WeatherEntity> optionalWeatherEntity = Optional.ofNullable(weatherRepository.findByDate(date));
        if (!optionalWeatherEntity.isEmpty()) {
            WeatherEntity weatherEntity = optionalWeatherEntity.get();
            return weatherEntity;
        }
        return null;
    }

    /**
     *
     * @return
     */
    public List<Object[]> getWeatherDataStatistics() {
        return weatherRepository.countDaysByWeatherType();
    }
}
