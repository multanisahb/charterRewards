package com.charter.cutomerRewards.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "rewards")
public class RewardPointsRangeConfiguration {
    private Map<String, Integer> pointsRange;
    private Integer upperLimit;
    private Integer lowerLimit;
}
