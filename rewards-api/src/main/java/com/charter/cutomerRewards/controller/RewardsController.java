package com.charter.cutomerRewards.controller;

import com.charter.cutomerRewards.dto.response.RewardsResponse;
import com.charter.cutomerRewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rewards Controller: Exposes GET endpoint to fetch customer rewards breakdown based on unique customer Id.
 * @author Multani
 */
@RestController
@RequestMapping(value = "/charter/rewards")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping(value = "customer/{customerId}")
    private ResponseEntity<RewardsResponse> getCustomerRewards(@PathVariable(value = "customerId") Long customerId) {
        return rewardsService.getCustomerRewards(customerId) ;
    }
}
