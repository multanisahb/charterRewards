package com.charter.cutomerRewards.database.repository;

import com.charter.cutomerRewards.database.entities.CustomerEntity;
import com.charter.cutomerRewards.database.entities.PaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PaymentsRepository extends JpaRepository<PaymentsEntity, Integer> {
    List<PaymentsEntity> findAllByCustomerAndTransactionDateAfter(CustomerEntity customer, LocalDate fromDate);
}
