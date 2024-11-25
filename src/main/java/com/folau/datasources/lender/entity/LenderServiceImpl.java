package com.folau.datasources.lender.entity;

import com.folau.datasources.lender.repository.LenderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class LenderServiceImpl implements LenderService {

    @Autowired
    private LenderDAO lenderDAO;

    @Override
    public Lender save(Lender lender) {
        log.info("save lender={}", lender);
        return lenderDAO.save(lender);
    }
}
