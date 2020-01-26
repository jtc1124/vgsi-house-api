package com.houses.api;

import com.houses.db.HouseDB;
import com.houses.exceptions.HouseNotFoundException;
import com.houses.exceptions.HouseNotUpdatedException;
import com.houses.models.House;
import com.houses.models.HouseList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HousesController {

  private final HouseDB houseDB;

  HousesController(HouseDB houseDB) {
    this.houseDB = houseDB;
  }

  // Get all houses
  @GetMapping("/houses")
  public EntityModel<HouseList> getHouses() {
    List<House> houses = houseDB.findAll();
    HouseList houseList = new HouseList(houses, houses.size());
    // EntityModel<HouseList> emHouseList = houseList.getHouseList().stream()
    //   .map(house -> new EntityModel<>(house,
    //   linkTo(methodOn(HousesController.class).getHouse(house.getId())).withSelfRel()))
    //   .collect(Collectors.toList());

    List<Link> links = houseList.getHouseList().stream()
      .map(house -> linkTo(methodOn(HousesController.class).getHouse(house.getId())).withSelfRel())
      .collect(Collectors.toList());

    EntityModel<HouseList> emHouseList = new EntityModel<>(houseList);
    emHouseList.add(links);

    return emHouseList;
  }

  // Get a single house by ID
  @GetMapping("/houses/{id}")
  public EntityModel<House> getHouse(@PathVariable long id) {
    House house = houseDB.findById(id).orElseThrow(() -> new HouseNotFoundException(id));

    return new EntityModel<>(house,
      linkTo(methodOn(HousesController.class).getHouses()).withRel("houses"));
  }

  // Update a single house by ID
  @PutMapping("/houses/{id}")
  public EntityModel<House> updateHouse(@PathVariable long id, @RequestBody House updatedHouse) {
    House h = houseDB.findById(id).map(house -> {
      house.setOwnerFirstName(updatedHouse.getOwnerFirstName());
      house.setOwnerLastName(updatedHouse.getOwnerLastName());
      house.setStreetAddress(updatedHouse.getStreetAddress());
      house.setCity(updatedHouse.getCity());
      house.setState(updatedHouse.getState());
      house.setZip(updatedHouse.getZip());
      house.setPropertyType(updatedHouse.getPropertyType());


      return house;

    }).orElseThrow(() -> new HouseNotUpdatedException(id));

    return new EntityModel<>(houseDB.save(h),
      linkTo(methodOn(HousesController.class).getHouse(id)).withRel("house"));
  }
}
