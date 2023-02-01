package com.charter.cutomerRewards.controller;

import com.charter.cutomerRewards.dto.response.RewardsResponse;
import com.charter.cutomerRewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/charter/rewards")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping(value = "/{customerId}")
    private RewardsResponse getCustomerRewards(@PathVariable(value = "customerId", required = true) Long customerId) {
        return RewardsResponse.builder().customerName(rewardsService.getCustomerName(customerId)).build();
    }
}
