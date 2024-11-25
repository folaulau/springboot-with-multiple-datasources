package com.folau.datasources.house.repository;

import com.folau.datasources.house.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
