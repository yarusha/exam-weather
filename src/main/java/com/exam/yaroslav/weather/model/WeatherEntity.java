package com.exam.yaroslav.weather.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name = "weather_data")
@Getter
@Setter
public class WeatherEntity {
    @Id
    @Column(name="date", nullable = false, unique = true)
    private Date date;

    @Column(name="type")
    @Enumerated(EnumType.ORDINAL)
    private WeatherType type;

    @Column(name="temperature")
    private Float temperature;

    @Column(name="wind_speed")
    private Float wind_speed;
}
