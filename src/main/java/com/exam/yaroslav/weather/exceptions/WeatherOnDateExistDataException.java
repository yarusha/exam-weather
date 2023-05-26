package com.exam.yaroslav.weather.exceptions;

import java.sql.Date;

public class WeatherOnDateExistDataException extends Exception{
    public WeatherOnDateExistDataException(Date date) {
        super(String.valueOf(date));
    }
}
