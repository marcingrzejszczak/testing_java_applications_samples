package com.example.week3.part2.more.calculator;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class DiscountConfiguration {

    @Bean
    DiscountCalculator discountCalculator(List<DiscountApplier> appliers) {
        return new DiscountCalculator(appliers);
    }

}
