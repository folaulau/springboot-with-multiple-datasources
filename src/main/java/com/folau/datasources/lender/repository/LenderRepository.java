package com.folau.datasources.lender.repository;

import com.folau.datasources.lender.entity.Lender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LenderRepository extends JpaRepository<Lender, Long> {
}
