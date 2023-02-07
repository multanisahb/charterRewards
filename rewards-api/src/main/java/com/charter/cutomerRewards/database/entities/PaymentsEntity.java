package com.charter.cutomerRewards.database.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This entity contains info for Payments object.
 * Joins customer table based on unique customer Id.
 * @author Multani
 */
@Getter
@Setter
@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
