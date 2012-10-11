package com.sree.samples;

public enum EnumExample {

	Low(0, 100), Medium(101, 1000), High(1000, 10000);

	private int minPrice;
	private int maxPrice;

	private EnumExample(int min, int max) {
		this.minPrice = min;
		this.maxPrice = max;
	}

	public static EnumExample getPriceBracket(int price) {
		for (EnumExample bracket : EnumExample.values()) {
			if (price >= bracket.minPrice && price <= bracket.maxPrice) {
				return bracket;
			}
		}
		return null;
	}
}
