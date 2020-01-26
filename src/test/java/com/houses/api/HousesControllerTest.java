package com.houses.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.houses.db.HouseDB;
import com.houses.exceptions.HouseNotFoundException;
import com.houses.exceptions.HouseNotUpdatedException;
import com.houses.models.House;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class HousesControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private HouseDB houseDB;

  @BeforeEach
  public void setUp() {
    // Setup mock house so we don't have to use an external data source
    House house = new House(new Long(1), "Jack", "Smith", "South St", "Hudson", "MA", new Integer("01749"), "Single Family");
    List<House> houseList = new ArrayList<House>();
    houseList.add(house);
    Mockito.when(houseDB.findAll()).thenReturn(houseList);
    Mockito.when(houseDB.findById(1L)).thenReturn(Optional.of(house));
  }

  @Test
  public void testGetHouses() throws Exception {
    MvcResult result = this.mockMvc.perform(get("/houses")).andReturn();
    String responseBody = result.getResponse().getContentAsString();
    // System.out.println("RESULT: " + responseBody);
    // System.out.println("EXPECTED: " + objectMapper.writeValueAsString(houseDB.findAll()));
    // Make sure the string value of our house list matches the response body
    assertThat(responseBody).contains(objectMapper.writeValueAsString(houseDB.findAll()));
  }

  @Test
  public void testGetHouseById() throws Exception {
    MvcResult result = this.mockMvc.perform(get("/houses/1")).andReturn();
    String responseBody = result.getResponse().getContentAsString();
    // Make sure the string value of our house matches the response body
    assertThat(responseBody).contains(objectMapper.writeValueAsString(houseDB.findById(1L).get()).replaceAll("\\{", "").replaceAll("\\}", ""));
  }

  @Test
  public void testUpdateHouseById() throws Exception {
    // Again, mock the updated house so we don't need to access any external data
    House updatedHouse = new House(new Long(1), "John", "Mackey", "North St", "Needham", "MA", new Integer("02492"), "Multi Family");
    Mockito.when(houseDB.save(any(House.class))).thenReturn(updatedHouse);
    MvcResult result = this.mockMvc.perform(put("/houses/1")
      .contentType("application/json")
      .content(objectMapper.writeValueAsString(updatedHouse)))
      .andReturn();
    String responseBody = result.getResponse().getContentAsString();
    // Make sure the response body is the updated house
    assertThat(responseBody).contains(objectMapper.writeValueAsString(updatedHouse).replaceAll("\\{", "").replaceAll("\\}", ""));
  }

  @Test
  public void testGetHouseInvalidId() throws Exception {
    MvcResult result = this.mockMvc.perform(get("/houses/20")).andReturn();
    String responseBody = result.getResponse().getContentAsString();
    // Make sure the exception message is in the response
    assertThat(new HouseNotFoundException(20L).getMessage()).isEqualToIgnoringWhitespace(responseBody);
  }

  @Test
  public void testUpdateHouseInvalidId() throws Exception {
    House updatedHouse = new House(new Long(1), "John", "Mackey", "North St", "Needham", "MA", new Integer("02492"), "Multi Family");
    Mockito.when(houseDB.save(any(House.class))).thenReturn(updatedHouse);
    MvcResult result = this.mockMvc.perform(put("/houses/20")
      .contentType("application/json")
      .content(objectMapper.writeValueAsString(updatedHouse)))
      .andReturn();
    String responseBody = result.getResponse().getContentAsString();
    // Make sure the exception message is in the response
    assertThat(new HouseNotUpdatedException(20L).getMessage()).isEqualToIgnoringWhitespace(responseBody);
  }
}
