package com.charter.cutomerRewards.database.repository;

import com.charter.cutomerRewards.database.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findCustomerEntityById(long customerId);
}
