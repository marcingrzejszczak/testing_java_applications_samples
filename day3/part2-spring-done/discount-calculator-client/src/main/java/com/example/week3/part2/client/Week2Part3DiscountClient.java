package com.example.week3.part2.client;

import java.net.http.HttpClient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class Week2Part3DiscountClient {

	public static void main(String[] args) {
		SpringApplication.run(Week2Part3DiscountClient.class, args);
	}

	@Bean
	HttpClient httpClient() {
		return HttpClient.newBuilder().build();
	}

	@Bean
	CommandLineRunner commandLineRunner(HttpClient httpClient, ObjectMapper objectMapper) {
		return args -> {
			if (args == null || args.length == 0) {
				return;
			}
			String name = args[0];
			int noOfGoods = Integer.parseInt(args[1]);
			Occupation occupation = Occupation.valueOf(args[2]);
			String rootUrl = args[3];
			new PersonDetailsDiscountApplier(new PersonClient(httpClient, rootUrl, objectMapper)).applyDiscount(new Person(name, noOfGoods, occupation));
		};
	}
}
