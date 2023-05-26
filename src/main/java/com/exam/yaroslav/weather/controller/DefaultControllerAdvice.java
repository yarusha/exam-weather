package com.exam.yaroslav.weather.controller;

import com.exam.yaroslav.weather.exceptions.WeatherOnDateExistDataException;
import com.exam.yaroslav.weather.exceptions.WeatherOnDateNotFoundException;
import com.exam.yaroslav.weather.model.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice
public class DefaultControllerAdvice {
    @ExceptionHandler({SQLException.class})
    public @ResponseBody Response sqlError() {
        Response response = new Response("SQL Exception", 500);
        return response;
    }

    @ExceptionHandler({WeatherOnDateExistDataException.class})
    public @ResponseBody Response existDataOnDate() {
        Response response = new Response("There is already data in the database for the date", 422);
        return response;
    }

    @ExceptionHandler({WeatherOnDateNotFoundException.class})
    public @ResponseBody Response notFoundDataOnDate() {
        Response response = new Response("Data not found on date", 422);
        return response;
    }

    @ExceptionHandler({Exception.class})
    public @ResponseBody Response errorDB() {
        Response response = new Response("Error update uniq identity field date", 422);
        return response;
    }
}
