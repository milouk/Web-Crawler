package com.complet;

public class RunClass extends Thread {

	public static boolean robot_thread1 = true;
	public static boolean robot_thread2 = true;
	public static boolean robot_thread3 = true;

	public static void begin() {

		// Create Mainclass objects which are used as threads
		RunClass m1 = new RunClass();
		RunClass m2 = new RunClass();
		RunClass m3 = new RunClass();

		// Start Threads , Get Thread Names
		m1.start();
		Mainclass.setT1name(m1.getName());
		m2.start();
		Mainclass.setT2name(m2.getName());
		m3.start();
		Mainclass.setT3name(m3.getName());

		// Ensure all threads are finished
		try {

			m1.join();
			m2.join();
			m3.join();

		} catch (InterruptedException e) {
		}

		Mainclass.getFinalist().removeAll(Mainclass.getFinalist());

	}