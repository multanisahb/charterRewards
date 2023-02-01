package com.charter.cutomerRewards.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RewardsMonthlyBreakdown {
    private String month;
    private BigDecimal pointsEarned;
}
