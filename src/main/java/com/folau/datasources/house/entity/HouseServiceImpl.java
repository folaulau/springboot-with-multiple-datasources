package com.folau.datasources.house.entity;

import com.folau.datasources.house.repository.HouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseDAO houseDAO;

    @Override
    public House save(House house) {
        return houseDAO.save(house);
    }
}
