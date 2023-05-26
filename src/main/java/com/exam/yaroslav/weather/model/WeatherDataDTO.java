package com.exam.yaroslav.weather.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class WeatherDataDTO {
    public Date date;
    public WeatherType type;
    public Float temperature;
    public Float wind_speed;
}
