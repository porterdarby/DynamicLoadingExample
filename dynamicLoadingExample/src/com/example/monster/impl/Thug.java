package com.example.monster.impl;

import com.example.monster.Biped;
import com.example.monster.factory.BipedFactory;

public class Thug implements Biped {
	
	static {
		// Register the BugbearThug class with the BruteFactory
		BipedFactory.register(Thug.class);
	}

	public String getName() {
		return "Thug";
	}

	public int getHP() {
		return 65;
	}
	
	@Override
	public String toString() {
		return this.getName() + " " + this.hashCode();
	}
}