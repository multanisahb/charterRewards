package com.charter.cutomerRewards.service;

import com.charter.cutomerRewards.database.entities.CustomerEntity;
import com.charter.cutomerRewards.database.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RewardsService {
    @Autowired
    private CustomerRepository customerRepository;

    public String getCustomerName(long customerId) {
        Optional<CustomerEntity> customer = customerRepository.findCustomerEntityById(customerId);
        return customer
                .map(customerEntity -> customerEntity.getFirstName().concat(" ").concat(customerEntity.getLastName()))
                .orElse(null);
    }
}
