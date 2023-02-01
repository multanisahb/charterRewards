package com.charter.cutomerRewards.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class PaymentsEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;
    private LocalDate transactionDate;
    private String merchantName;
    private BigDecimal transactionAmount;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
