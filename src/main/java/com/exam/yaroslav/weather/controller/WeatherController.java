package com.exam.yaroslav.weather.controller;

import com.exam.yaroslav.weather.exceptions.WeatherOnDateExistDataException;
import com.exam.yaroslav.weather.exceptions.WeatherOnDateNotFoundException;
import com.exam.yaroslav.weather.model.WeatherDataDTO;
import com.exam.yaroslav.weather.model.WeatherDataStatisticsDTO;
import com.exam.yaroslav.weather.model.WeatherEntity;
import com.exam.yaroslav.weather.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Api(tags = {"Work with weather data"})
@SwaggerDefinition(tags = {@Tag(name = "Work with weather data", description = "Work with weather data REST API")})
@RestController
@RequestMapping("/api")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @Operation(summary = "Create weather")
    @ApiResponse(code = 200, message = "Weather created", response = WeatherDataDTO.class)
    @PostMapping("/weather")
    public @ResponseBody WeatherEntity createDocument(@RequestBody WeatherDataDTO weatherDataDTO) throws WeatherOnDateExistDataException {
        if (weatherService.searchDataByDate(weatherDataDTO.getDate())==null)
            return weatherService.createWeatherData(weatherDataDTO);
        throw new WeatherOnDateExistDataException(weatherDataDTO.getDate());
    }
    @Operation(summary = "Get weather on date")
    @ApiResponse(code = 200, message = "Get weather on date", response = WeatherDataDTO.class)
    @GetMapping("/weather")
    public @ResponseBody WeatherEntity searchByDate(@RequestParam Date date) throws WeatherOnDateNotFoundException {
        Optional<WeatherEntity> weatherEntityOptional = Optional.ofNullable(weatherService.searchDataByDate(date));
        if (weatherEntityOptional.isPresent())
            return weatherEntityOptional.get();
        throw new WeatherOnDateNotFoundException(date);
    }
    @Operation(summary = "Update weather on date")
    @ApiResponse(code = 200, message = "Update weather on date", response = WeatherDataDTO.class)
    @PutMapping("/weather")
    public @ResponseBody WeatherEntity searchByDate(@RequestParam Date date, @RequestBody WeatherDataDTO weatherDataDTO) throws WeatherOnDateNotFoundException {
        if (weatherService.searchDataByDate(date)==null)
            throw new WeatherOnDateNotFoundException(weatherDataDTO.getDate());
        return weatherService.updateWeatherData(date, weatherDataDTO);
    }

    @Operation(summary = "Get weather statistics")
    @ApiResponse(code = 200, message = "Get weather statistics")
    @GetMapping("/weather/statistics")
    public @ResponseBody List<Object[]> getStatistics() {
        return weatherService.getWeatherDataStatistics();
    }
}
