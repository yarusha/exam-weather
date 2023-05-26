package com.exam.yaroslav.weather.repository;

import com.exam.yaroslav.weather.model.WeatherDataStatisticsDTO;
import com.exam.yaroslav.weather.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
    WeatherEntity findByDate(Date date);
    @Query("SELECT type, COUNT(*) FROM weather_data wd GROUP BY type")
    List<Object[]> countDaysByWeatherType();
}