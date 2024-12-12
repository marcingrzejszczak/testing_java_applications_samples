package com.example.week2.part5;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "discounts", uniqueConstraints = @UniqueConstraint(columnNames = { "NAME" }))
class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Occupation occupation;

	@Column(nullable = false)
	private double rate;

	public Discount() {
	}

	public Discount(String name, Occupation occupation, double rate) {
		this.name = name;
		this.occupation = occupation;
		this.rate = rate;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Discount discount = (Discount) o;
		return Double.compare(rate, discount.rate) == 0 && Objects.equals(id, discount.id) && Objects.equals(name, discount.name) && occupation == discount.occupation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, occupation, rate);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}
