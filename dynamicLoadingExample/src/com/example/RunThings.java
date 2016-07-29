package com.example;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import com.example.monster.Monster;
import com.example.monster.factory.BipedFactory;
import com.example.monster.factory.QuadrupedFactory;

@SuppressWarnings("rawtypes")
public class RunThings {

	// CHANGE THIS TO TRUE FOR SOME EXPLANATIVE DEBUGGING
	private static boolean DEBUG = false;

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		BipedFactory bipedFactory = new BipedFactory();
		Monster biped1 = bipedFactory.create();
		System.out.println(biped1);
		Monster biped2 = bipedFactory.create();
		System.out.println(biped2);

		QuadrupedFactory quadrupedFactory = new QuadrupedFactory();
		Monster quadruped1 = quadrupedFactory.create();
		System.out.println(quadruped1);
	}

	/**
	 * When a class is loaded by the JVM, anything in the static block is loaded.
	 */
	static {
		if (DEBUG) {
			System.out.println("STARTING UP SYSTEM");
			System.out.println("==================");
		}
		// First we get a list of all of the classes in com.example.dnd.impl package
		Set<Class> clazzes = getClassesInPackage("com.example.dnd.impl");

		// And then loop through them
		for (Class clazz : clazzes) {
			try {
				// This forcibly loads the classes into the JVM, which in turn calls the
				// appropriate static classes.
				if (DEBUG) {
					System.out.println("Attempting to load class " + clazz.getName());
				}
				Class.forName(clazz.getName());
			} catch (ClassNotFoundException e) {
				if (DEBUG) {
					System.out.println("Class " + clazz.getName() + " not loaded: ClassNotFoundException");
				}
				e.printStackTrace();
			}
		}

		if (DEBUG) {
			System.out.println("IMPLEMENTATION CLASSES LOADED");
			System.out.println("=============================");
		}
	}

	// Following section adapted from:
	// http://mike.shannonandmike.net/2009/09/02/java-reflecting-to-get-all-classes-in-a-package/
	/**
	 * Given a package name, attempts to reflect to find all classes within the
	 * package on the local file system.
	 * 
	 * @param packageName
	 * @return
	 */
	private static Set<Class> getClassesInPackage(String packageName) {
		Set<Class> classes = new HashSet<Class>();
		String packageNameSlashed = packageName.replace(".", "/");
		// Get a File object for the package
		URL directoryURL = Thread.currentThread().getContextClassLoader().getResource(packageNameSlashed);
		if (directoryURL == null) {
			System.out.println("Could not retrieve URL resource: " + packageNameSlashed);
			return classes;
		}

		String directoryString = directoryURL.getFile();
		if (directoryString == null) {
			System.out.println("Could not find directory for URL resource: " + packageNameSlashed);
			return classes;
		}

		File directory = new File(directoryString);
		if (directory.exists()) {
			// Get the list of the files contained in the package
			String[] files = directory.list();
			for (String fileName : files) {
				// We are only interested in .class files
				if (fileName.endsWith(".class")) {
					// Remove the .class extension
					fileName = fileName.substring(0, fileName.length() - 6);
					try {
						classes.add(Class.forName(packageName + "." + fileName));
					} catch (ClassNotFoundException e) {
						System.out.println(packageName + "." + fileName + " does not appear to be a valid class.");
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println(packageName + " does not appear to exist as a valid package on the file system.");
		}
		return classes;
	}
}
