package com.ada.bankapi.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
@Entity
public class DollarQuotation {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date date;

    @Column
    private BigDecimal purchaseQuotation;

    @Column
    private BigDecimal saleQuotation;

}


