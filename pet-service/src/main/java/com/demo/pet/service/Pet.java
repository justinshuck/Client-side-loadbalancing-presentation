package com.demo.pet.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pet {
	public String id;
	public String name;
	public Pet(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Pet: id=%s, name=%s", id, name);
	}
}
