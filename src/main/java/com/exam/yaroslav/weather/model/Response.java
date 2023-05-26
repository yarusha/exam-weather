package com.exam.yaroslav.weather.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String errorText;
    private Integer errorCode;
    public Response(String errorText, Integer errorCode) {
        this.errorCode = errorCode;
        this.errorText = errorText;
    }
}
