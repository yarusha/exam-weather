package com.exam.yaroslav.weather.exceptions;

import java.sql.Date;

public class WeatherOnDateNotFoundException extends Exception {
    public WeatherOnDateNotFoundException(Date date) {
        super(String.valueOf(date));
    }
}
