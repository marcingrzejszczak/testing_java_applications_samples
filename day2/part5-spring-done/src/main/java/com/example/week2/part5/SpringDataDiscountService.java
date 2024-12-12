package com.example.week2.part5;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
class SpringDataDiscountService {

	private final DiscountRepository discountRepository;

	SpringDataDiscountService(DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}

	public double getDiscountRate(Occupation occupation) {
		return findByOccupation(occupation);
	}

	private Double findByOccupation(Occupation occupation) {
		return discountRepository.findDiscountByOccupation(occupation).map(Discount::getRate).orElse(0D);
	}

	@Transactional
	public void addDiscount(Person person) {
		Discount discount = new Discount();
		discount.setName(person.getName());
		discount.setRate(new Random().nextDouble(100, 500));
		discount.setOccupation(person.getOccupation());
		discountRepository.save(discount);
	}
}
