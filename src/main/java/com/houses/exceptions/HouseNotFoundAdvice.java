package com.houses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HouseNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(HouseNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String houseNotFoundHandler(HouseNotFoundException e) {
    return e.getMessage();
  }
}
