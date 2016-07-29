package com.example.monster.factory;

import com.example.monster.Monster;

public abstract class MonsterFactory {
	/**
	 * Create an instance of a Monster.
	 * 
	 * @return A new Monster.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public abstract Monster create() throws InstantiationException, IllegalAccessException;
}
