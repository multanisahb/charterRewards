package com.charter.cutomerRewards.service;

import com.charter.cutomerRewards.configuration.RewardPointsRangeConfiguration;
import com.charter.cutomerRewards.database.entities.CustomerEntity;
import com.charter.cutomerRewards.database.entities.PaymentsEntity;
import com.charter.cutomerRewards.database.repository.CustomerRepository;
import com.charter.cutomerRewards.database.repository.PaymentsRepository;
import com.charter.cutomerRewards.dto.response.RewardsPerMonth;
import com.charter.cutomerRewards.dto.response.RewardsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RewardsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    PaymentsRepository paymentsRepository;
    @Autowired
    RewardPointsRangeConfiguration rewardsConfig;

    public ResponseEntity<RewardsResponse> getCustomerRewards(long customerId) {
        Optional<CustomerEntity> customer = customerRepository.findCustomerEntityById(customerId);
        if (customer.isPresent()) {
            List<PaymentsEntity> payments = paymentsRepository
                    .findAllByCustomerAndTransactionDateAfter(customer.get(), LocalDate.now().minusMonths(3));
            Map<Month, List<PaymentsEntity>> paymentsPerMonth =
                    payments.stream().collect(Collectors.groupingBy(p -> p.getTransactionDate().getMonth()));
            List<RewardsPerMonth> rewardsPerMonthList = new ArrayList<>();
            for (Month key : paymentsPerMonth.keySet()) {
                RewardsPerMonth rewardsPerMonth = new RewardsPerMonth();
                rewardsPerMonth.setMonth(key.name());
                rewardsPerMonth.setPointsEarned(calculateRewardsPerMonth(paymentsPerMonth.get(key)
                        .stream()
                        .map(PaymentsEntity::getTransactionAmount)
                        .collect(Collectors.toList())));
                rewardsPerMonthList.add(rewardsPerMonth);
            }
            return new ResponseEntity<>(RewardsResponse.builder()
                    .customerName(customer
                            .map(customerEntity -> customerEntity.getFirstName().concat(" ").concat(customerEntity.getLastName()))
                            .orElse(null))
                    .pointsBreakdown(rewardsPerMonthList)
                    .totalPoints(rewardsPerMonthList.stream()
                            .map(RewardsPerMonth::getPointsEarned)
                            .mapToInt(Integer::intValue)
                            .sum())
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(RewardsResponse.builder().build(), HttpStatus.NOT_FOUND);
    }

    public Integer calculateRewardsPerMonth(List<BigDecimal> transactionAmounts) {
        Integer valueBetweenLimits = transactionAmounts.stream()
                .filter(amount -> Math.round(amount.floatValue()) > rewardsConfig.getLowerLimit()
                        && Math.round(amount.floatValue()) < rewardsConfig.getUpperLimit())
                .map(amount -> (Math.round(amount.floatValue()) - rewardsConfig.getLowerLimit()))
                .mapToInt(Integer::intValue)
                .sum();

        Integer valueAboveUpperLimit = transactionAmounts.stream()
                .filter(amount -> Math.round(amount.floatValue()) > rewardsConfig.getUpperLimit())
                .map(amount -> ((Math.round(amount.floatValue()) - rewardsConfig.getUpperLimit())
                        * rewardsConfig.getPointsRange().get("UPPER"))
                        + rewardsConfig.getLowerLimit())
                .mapToInt(Integer::intValue)
                .sum();
        return valueBetweenLimits + valueAboveUpperLimit;
    }
}
