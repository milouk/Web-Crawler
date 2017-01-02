package com.complet;

/**
 * <h1>Run Class - RunClass.java</h1> The Run Class is a class that contains
 * what the threads will do. It also Ensures that all thread are finished before
 * proceeding to next run.
 *
 * @author Complet
 * @version 5.0
 * @since 2017-01-02
 */

public class RunClass extends Thread {

	public static boolean robotFollow_thread1 = true;
	public static boolean robotFollow_thread2 = true;
	public static boolean robotFollow_thread3 = true;
	public static boolean robotIndex_thread1 = true;
	public static boolean robotIndex_thread2 = true;
	public static boolean robotIndex_thread3 = true;

	/**
	 * <p>
	 * begin is the first method of this class. It creates 3 threads and ensures
	 * these threads are finished. When threads are finished it empties finalist
	 * in order to get it ready for next run.
	 * <p>
	 *
	 * @exception InterruptedException
	 *                which occurs if one of the threads unexpectedly is
	 *                interrupted.
	 */

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
			System.err.println("Thread Was Unexcpectedly Interrupted! ");
		}

		// Empties Finalist for next run.
		Mainclass.getFinalist().removeAll(Mainclass.getFinalist());

	}

	/**
	 * <p>
	 * Run Method. Calls the start method from LinkRetrieve class in order the
	 * extraction to begin. Simultaneously it filters links containing meta
	 * robot tags. This is also where layers are controlled.
	 * <p>
	 *
	 * @exception Exception
	 *                which occurs when something in the Robot Tags Class Goes
	 *                Wrong.
	 */

	@Override
	public final void run() {

		if (currentThread().getName().equals(Mainclass.getT1name())) {

			// Check if robot tags exists and allows to follow links
			try {

				RobotTags.checkAccess(Mainclass.getLink1());

			} catch (Exception e) {

				System.err.println(e);

			}

			if (robotFollow_thread1) {
				// initial run thread 1 (link 1)
				LinkRetrieve.start(Mainclass.getLink1());
			}

		} else if (currentThread().getName().equals(Mainclass.getT2name())) {

			// Check if robot tags exists and allows to follow links
			try {

				RobotTags.checkAccess(Mainclass.getLink2());

			} catch (Exception e) {

				System.err.println(e);
			}

			if (robotFollow_thread2) {
				// initial run thread 2 ( link 2)
				LinkRetrieve.start(Mainclass.getLink2());
			}
		} else if (currentThread().getName().equals(Mainclass.getT3name())) {

			// Check if robot tags exists and allows to follow links
			try {

				RobotTags.checkAccess(Mainclass.getLink3());

			} catch (Exception e) {

				System.err.println(e);

			}

			if (robotFollow_thread3) {
				// initial run thread 3 (link 3)
				LinkRetrieve.start(Mainclass.getLink3());
			}

		}

		/*
		 * First thread :
		 */

		if (currentThread().getName().equals(Mainclass.getT1name()) && Mainclass.getThread1_list().size() != 0) {

			int starting_point = 0;

			// current list size
			int ending_point = Mainclass.getThread1_list().size();

			// level loop
			for (int k = 1; k < Mainclass.getLayers(); k++) {

				// call start method for every link in list
				while (starting_point < ending_point) {

					// Check if robot tags exists and allows to follow links

					if (RobotTags.thread1_mFollow.get(starting_point)) {

						LinkRetrieve.start(Mainclass.getThread1_list().get(starting_point));

					}

					starting_point++;
				}

				// If a link has NOT added any link to the list
				if (ending_point < Mainclass.getThread1_list().size()) {
					ending_point = Mainclass.getThread1_list().size();

				} else {

					return;
				}

			}

			/*
			 * Second thread :
			 */

		} else if (currentThread().getName().equals(Mainclass.getT2name()) && Mainclass.getThread2_list().size() != 0) {

			int starting_point = 0;

			// current list size
			int ending_point = Mainclass.getThread2_list().size();

			// level loop
			for (int k = 1; k < Mainclass.getLayers(); k++) {

				// call start method for every link in list
				while (starting_point < ending_point) {

					// Check if robot tags exists and allows to follow links

					if (RobotTags.thread2_mFollow.get(starting_point)) {

						LinkRetrieve.start(Mainclass.getThread2_list().get(starting_point));

					}

					starting_point++;
				}

				// If a link has NOT added any link to the list
				if (ending_point < Mainclass.getThread2_list().size()) {
					ending_point = Mainclass.getThread2_list().size();
				} else {
					return;
				}
			}

			/*
			 * Third thread :
			 */

		} else if (currentThread().getName().equals(Mainclass.getT3name()) && Mainclass.getThread3_list().size() != 0) {

			int starting_point = 0;

			// current list size
			int ending_point = Mainclass.getThread3_list().size();

			// level loop
			for (int k = 1; k < Mainclass.getLayers(); k++) {

				// call start method for every link in list
				while (starting_point < ending_point) {

					// Check if robot tags exists and allows to follow links

					if (RobotTags.thread3_mFollow.get(starting_point)) {

						LinkRetrieve.start(Mainclass.getThread3_list().get(starting_point));
					}

					starting_point++;
				}

				// If a link has NOT added any link to the list
				if (ending_point < Mainclass.getThread3_list().size()) {
					ending_point = Mainclass.getThread3_list().size();

				} else {

					return;
				}

			}
		}

	}
}
