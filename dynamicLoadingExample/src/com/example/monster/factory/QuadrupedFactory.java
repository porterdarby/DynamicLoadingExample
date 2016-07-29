package com.example.monster.factory;

import com.example.monster.Monster;
import com.example.monster.Quadruped;

public class QuadrupedFactory extends MonsterFactory {
	private static Class<? extends Quadruped> quadrupedClazz;

	/**
	 * Register the provided class as the type of class to instantiate. The latest
	 * registered class will be used as the template for all requested
	 * Quadruped.
	 * 
	 * @param clazz
	 */
	public static void register(Class<? extends Quadruped> clazz) {
		quadrupedClazz = clazz;
	}

	@Override
	public Monster create() throws InstantiationException, IllegalAccessException {
		return createQuadruped();
	}

	/**
	 * Create a new instance of a Quadruped.
	 * 
	 * @return A new Quadruped.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Quadruped createQuadruped() throws InstantiationException, IllegalAccessException {
		return quadrupedClazz.newInstance();
	}
}
