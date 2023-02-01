package com.charter.cutomerRewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CharterRewardsApi {

	public static void main(String[] args) {
		SpringApplication.run(CharterRewardsApi.class, args);
	}

}
