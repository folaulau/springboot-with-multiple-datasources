package com.folau.datasources.dataloader;

import com.folau.datasources.house.entity.House;
import com.folau.datasources.house.repository.HouseRepository;
import com.folau.datasources.lender.entity.Lender;
import com.folau.datasources.lender.entity.LenderDAO;
import com.folau.datasources.lender.entity.LenderService;
import com.folau.datasources.lender.repository.LenderRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class LenderData {

    @Autowired
    LenderDAO lenderDAO;

    @PostConstruct
    void init() {
        log.info("Lender data initialized");
        for (int i = 1; i <= 10; i++) {
            lenderDAO.save(new Lender(null, "Lender " + i, 100000.0 + i));
        }
    }
}
