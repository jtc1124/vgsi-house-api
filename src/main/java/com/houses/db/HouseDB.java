package com.houses.db;

import com.houses.models.House;

import org.springframework.data.jpa.repository.JpaRepository;

// Simple interface to allow findById(), findAll(), save(), etc. methods on the House model
public interface HouseDB extends JpaRepository<House, Long> {
}
