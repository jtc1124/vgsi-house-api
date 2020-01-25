package com.houses.models;

import javax.persistence.Entity;
import javax.persistence.Id;

// Representation of a House, imported from the houses.csv
@Entity
public class House {

  private @Id Long id;
  private String ownerFirstName;
  private String ownerLastName;
  private String streetAddress;
  private String city;
  private String state;
  private Integer zip;
  private String propertyType;

  public House() {}

  public House(Long id,
               String ownerFirstName,
               String ownerLastName,
               String streetAddress,
               String city,
               String state,
               Integer zip,
               String propertyType) {
    this.id = id;
    this.ownerFirstName = ownerFirstName;
    this.ownerLastName = ownerLastName;
    this.streetAddress = streetAddress;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.propertyType = propertyType;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", ownerFirstName='" + getOwnerFirstName() + "'" +
      ", ownerLastName='" + getOwnerLastName() + "'" +
      ", streetAddress='" + getStreetAddress() + "'" +
      ", city='" + getCity() + "'" +
      ", state='" + getState() + "'" +
      ", zip='" + getZip() + "'" +
      ", propertyType='" + getPropertyType() + "'" +
      "}";
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOwnerFirstName() {
    return this.ownerFirstName;
  }

  public void setOwnerFirstName(String ownerFirstName) {
    this.ownerFirstName = ownerFirstName;
  }

  public String getOwnerLastName() {
    return this.ownerLastName;
  }

  public void setOwnerLastName(String ownerLastName) {
    this.ownerLastName = ownerLastName;
  }

  public String getStreetAddress() {
    return this.streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Integer getZip() {
    return this.zip;
  }

  public void setZip(Integer zip) {
    this.zip = zip;
  }

  public String getPropertyType() {
    return this.propertyType;
  }

  public void setPropertyType(String propertyType) {
    this.propertyType = propertyType;
  }

}