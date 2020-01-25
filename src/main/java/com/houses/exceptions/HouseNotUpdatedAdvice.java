package com.houses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HouseNotUpdatedAdvice {

  @ResponseBody
  @ExceptionHandler(HouseNotUpdatedException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  String houseNotUpdatedHandler(HouseNotUpdatedException e) {
    return e.getMessage();
  }
}
