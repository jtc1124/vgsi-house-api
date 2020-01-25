package com.houses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import com.houses.db.HouseDB;
import com.houses.models.House;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadData {

  private static final Logger log = LoggerFactory.getLogger(LoadData.class);

  @Bean
  CommandLineRunner initDatabase(HouseDB repository) {
    return args -> {

      // Import CSV data into DB
      String currentWorkingDir = System.getProperty("user.dir");
      BufferedReader csvReader = new BufferedReader(new FileReader(currentWorkingDir + "/houses.csv"));
      log.info("Reading CSV from " + currentWorkingDir);
      String headers = csvReader.readLine();
      String row;
      while ((row = csvReader.readLine()) != null) {
        String[] data = row.split(",");
        Long id = new Long(data[0].trim());
        String firstName = data[1].trim();
        String lastName = data[2].trim();
        String street = data[3].trim();
        String city = data[4].trim();
        String state = data[5].trim();
        Integer zip = new Integer(data[6].trim());
        String type = data[7].trim();
        repository.save(new House(id, firstName, lastName, street, city, state, zip, type));
      }
      csvReader.close();

      // repository.save(new House(new Long(1), "Jack", "Smith", "South St", "Hudson", "MA", new Integer("01749"), "Single Family"));
    };
  }
}