package com.example.monster.factory;

import com.example.monster.Biped;
import com.example.monster.Monster;

public class BipedFactory extends MonsterFactory {

	private static Class<? extends Biped> bipedClazz;

	/**
	 * Register the provided class as the type of class to instantiate. The latest
	 * registered class will be used as the template for all requested Biped.
	 * 
	 * @param clazz
	 */
	public static void register(Class<? extends Biped> clazz) {
		bipedClazz = clazz;
	}

	@Override
	public Monster create() throws InstantiationException, IllegalAccessException {
		return createBiped();
	}

	/**
	 * Create a new instance of a Biped.
	 * 
	 * @return A new Biped.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Biped createBiped() throws InstantiationException, IllegalAccessException {
		return bipedClazz.newInstance();
	}
}
