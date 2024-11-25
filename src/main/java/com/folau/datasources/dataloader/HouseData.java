package com.folau.datasources.dataloader;

import com.folau.datasources.house.entity.House;
import com.folau.datasources.house.entity.HouseDAO;
import com.folau.datasources.house.repository.HouseRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@Slf4j
public class HouseData {

    @Autowired
    HouseDAO houseDAO;

    @PostConstruct
    void init() {
        log.info("House data initialized");

        for (int i = 1; i <= 10; i++) {
            houseDAO.save(new House(null, "Address " + i, 100000.0 + i));
        }
    }
}
