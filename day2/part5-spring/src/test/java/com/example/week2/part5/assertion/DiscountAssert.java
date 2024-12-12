package com.example.week2.part5.assertion;

import com.example.week2.part5.DiscountDto;
import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

public class DiscountAssert extends AbstractAssert<DiscountAssert, DiscountDto> {

	public DiscountAssert(DiscountDto actual) {
		super(actual, DiscountAssert.class);
	}

	public static DiscountAssert assertThat(DiscountDto actual) {
		return new DiscountAssert(actual);
	}

	public static DiscountAssert then(DiscountDto actual) {
		return new DiscountAssert(actual);
	}

	public DiscountAssert isNotSet() {
		isNotNull();
		if (!Objects.equals(actual.getRate(), 0D)) {
			failWithMessage("Expected not to have any discount but the discount was <%s>", actual.getRate());
		}
		return this;
	}

	public DiscountAssert isEqualTo(double rate) {
		isNotNull();
		if (!Objects.equals(actual.getRate(), rate)) {
			failWithMessage("Expected discount rate to be equal to <%s> but the rate was <%s>", rate, actual.getRate());
		}
		return this;
	}
}
