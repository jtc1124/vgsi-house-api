package com.houses.exceptions;

public class HouseNotUpdatedException extends RuntimeException {

  public HouseNotUpdatedException(Long id) {
    super("Unable to update house ID: " + id);
  }
}