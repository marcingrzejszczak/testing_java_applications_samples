package com.example.week2.part5;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Week2Part5 {

	@Bean
	@Profile("prod")
	CommandLineRunner discountCalculation(@Value("${person.name}") String personName, @Value("${person.goods}") int noOfGoods, @Value("${person.occupation") Occupation occupation, DiscountCalculator discountCalculator) {
		return new DiscountCalculatingCommandLineRunner(personName, noOfGoods, occupation, discountCalculator);
	}

	public static void main(String[] args) {
		SpringApplication.run(Week2Part5.class, args);
	}
}
