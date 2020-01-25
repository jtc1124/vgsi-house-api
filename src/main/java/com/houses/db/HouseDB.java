package com.houses.db;

import com.houses.models.House;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseDB extends JpaRepository<House, Long> {
}
