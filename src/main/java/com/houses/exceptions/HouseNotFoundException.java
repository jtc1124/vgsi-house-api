package com.houses.exceptions;

public class HouseNotFoundException extends RuntimeException {

  public HouseNotFoundException(Long id) {
    super("Unable to find house for ID: " + id);
  }
}