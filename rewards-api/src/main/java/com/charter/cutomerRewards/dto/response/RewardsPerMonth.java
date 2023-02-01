package com.charter.cutomerRewards.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RewardsPerMonth {
    private String month;
    private Integer pointsEarned;
}
