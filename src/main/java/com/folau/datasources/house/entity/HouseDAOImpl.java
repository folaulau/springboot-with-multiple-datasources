package com.folau.datasources.house.entity;

import com.folau.datasources.house.repository.HouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(transactionManager = "houseTransactionManager")
@Slf4j
@Service
public class HouseDAOImpl implements HouseDAO {

    @Autowired
    private HouseRepository houseRepository;

    @Override
    public House save(House house) {
        return houseRepository.saveAndFlush(house);
    }
}
