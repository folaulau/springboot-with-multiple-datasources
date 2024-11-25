package com.folau.datasources.lender.entity;

import com.folau.datasources.lender.repository.LenderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(transactionManager = "lenderTransactionManager")
@Slf4j
@Service
public class LenderDAOImpl implements LenderDAO {

    @Autowired
    private LenderRepository lenderRepository;

    @Override
    public Lender save(Lender lender) {
        return lenderRepository.saveAndFlush(lender);
    }

}
