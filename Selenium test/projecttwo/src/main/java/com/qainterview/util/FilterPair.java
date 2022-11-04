package com.qainterview.util;

public class FilterPair {
	@Override public String toString() {
		return "FilterPair{" + "predicate='" + predicate + '\'' + ", value='" + value + '\'' + '}';
	}

	private String predicate;
	private String value;
	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}



	public FilterPair(String predicate, String value) {
		this.predicate = predicate;
		this.value = value;
	}
}
