package com.example.week2.part5;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface DiscountRepository extends CrudRepository<Discount, Long> {
	Optional<Discount> findDiscountByOccupation(Occupation occupation);

	Optional<Discount> findDiscountByName(String name);

}
