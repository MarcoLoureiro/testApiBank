package com.ada.bankapi.repository;

import com.ada.bankapi.modelo.DollarQuotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface QuotationRepository extends JpaRepository<DollarQuotation, Long> {
    DollarQuotation findByDate(Date date);
}
