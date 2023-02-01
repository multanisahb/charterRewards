package com.charter.cutomerRewards.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class RewardsResponse {
    private String customerName;
    private Integer totalPoints;
    private List<RewardsPerMonth> pointsBreakdown;
}
