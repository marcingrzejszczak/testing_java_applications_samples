package com.example.week2.part5;

import java.util.Objects;

class Person {
	private final String name;
	private final int numberOfBoughtGoods;
	private final Occupation occupation;

	Person(String name, int numberOfBoughtGoods, Occupation occupation) {
		this.name = name;
		this.numberOfBoughtGoods = numberOfBoughtGoods;
		this.occupation = occupation;
	}

	int getNumberOfBoughtGoods() {
		return numberOfBoughtGoods;
	}

	String getName() {
		return name;
	}

	Occupation getOccupation() {
		return occupation;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", numberOfBoughtGoods=" + numberOfBoughtGoods +
				", occupation=" + occupation +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Person person = (Person) o;
		return numberOfBoughtGoods == person.numberOfBoughtGoods && Objects.equals(name, person.name) && occupation == person.occupation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, numberOfBoughtGoods, occupation);
	}
}
