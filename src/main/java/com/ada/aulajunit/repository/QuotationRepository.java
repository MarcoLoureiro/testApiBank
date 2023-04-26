package com.ada.aulajunit.repository;

import com.ada.aulajunit.modelo.DollarQuotation;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface QuotationRepository extends JpaRepository<DollarQuotation, Long> {
    DollarQuotation findByDate(Date date);
}
