package com.example.monster.impl;

import com.example.monster.Quadruped;
import com.example.monster.factory.QuadrupedFactory;

public class Cat implements Quadruped {

	static {
		// Register the Cat class with the SkirmisherFactory
		QuadrupedFactory.register(Cat.class);
	}

	public String getName() {
		return "Cat";
	}

	public int getHP() {
		return 1;
	}

	@Override
	public String toString() {
		return this.getName() + " " + this.hashCode();
	}
}