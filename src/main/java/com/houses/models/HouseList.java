package com.houses.models;

import java.util.ArrayList;
import java.util.List;

public class HouseList {
  private List<House> houses;

  public HouseList() {
    houses = new ArrayList<>();
  }

  public List<House> getHouses() {
    return this.houses;
  }

  public void setHouses(List<House> houses) {
    this.houses = houses;
  }
}
