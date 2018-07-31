package com.itis.shaca.monitor.entities;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilterWrapper {
	private String filter;
	private String range;
	private String sort;

	public Optional<String> getFilter() {
		return Optional.ofNullable(filter);
	}

	public Optional<String> getRange() {
		return Optional.ofNullable(range);
	}

	public Optional<String> getSort() {
		return Optional.ofNullable(sort);
	}
}
