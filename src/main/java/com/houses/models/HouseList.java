package com.houses.models;

import java.util.List;

import javax.persistence.Entity;

// Representation of a HouseList, just a list of house objects
public class HouseList {

  private List<House> houseList;
  private int count;

  public HouseList() {}

  public HouseList(List<House> houseList, int count) {
    this.houseList = houseList;
    this.count = count;
  }

  public List<House> getHouseList() {
    return this.houseList;
  }

  public void setHouseList(List<House> houseList) {
    this.houseList = houseList;
  }

  public int getCount() {
    return this.count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Override
  public String toString() {
    return "{" +
      " itemCount='" + getCount() + "'" +
      ", items='" + getHouseList() + "'" +
      "}";
  }

}