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

					// Add All Elements In The List To The Set
					Mainclass.getThread1_set().addAll(Mainclass.getThread1_list());

					// Empty List
					Mainclass.getThread1_list().removeAll(
							Mainclass.getThread1_list());

					// Add Back To The List Only Unique Elements
					Mainclass.getThread1_list().addAll(Mainclass.getThread1_set());

					// Empty Set
					Mainclass.getThread1_set()
							.removeAll(Mainclass.getThread1_set());
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

					// Add All Elements In The List To The Set
					Mainclass.getThread2_set().addAll(Mainclass.getThread2_list());

					// Empty List
					Mainclass.getThread2_list().removeAll(
							Mainclass.getThread2_list());

					// Add Back To The List Only Unique Elements
					Mainclass.getThread2_list().addAll(Mainclass.getThread2_set());

					// Empty Set
					Mainclass.getThread2_set()
							.removeAll(Mainclass.getThread2_set());
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

					// Add All Elements In The List To The Set
					Mainclass.getThread3_set().addAll(Mainclass.getThread3_list());

					// Empty List
					Mainclass.getThread3_list().removeAll(
							Mainclass.getThread3_list());

					// Add Back To The List Only Unique Elements
					Mainclass.getThread3_list().addAll(Mainclass.getThread3_set());

					// Empty Set
					Mainclass.getThread3_set()
							.removeAll(Mainclass.getThread3_set());
				}

			}

			/*
			 * First thread :
			 */

			if (currentThread().getName().equals(Mainclass.getT1name())
					&& Mainclass.getThread1_list().size() != 0) {

				int starting_point = 0;

				// current list size
				int ending_point = Mainclass.getThread1_list().size();

				// level loop
				for (int k = 1; k < Mainclass.getLayers(); k++) {

					// call start method for every link in list
					while (starting_point < ending_point) {

						// Check if robot tags exists and allows to follow links
						try {

							RobotTags.checkAccess(Mainclass.getThread1_list().get(
									starting_point));

						} catch (Exception e) {

							System.err.println(e);
						}

						if (robotFollow_thread1) {

							LinkRetrieve.start(Mainclass.getThread1_list().get(
									starting_point));

							// Add All Elements In The List To The Set
							Mainclass.getThread1_set().addAll(
									Mainclass.getThread1_list());

							// Empty List
							Mainclass.getThread1_list().removeAll(
									Mainclass.getThread1_list());

							// Add Back To The List Only Unique Elements
							Mainclass.getThread1_list().addAll(
									Mainclass.getThread1_set());

							// Empty Set
							Mainclass.getThread1_set().removeAll(
									Mainclass.getThread1_set());
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

			} else if (currentThread().getName().equals(Mainclass.getT2name())
					&& Mainclass.getThread2_list().size() != 0) {

				int starting_point = 0;

				// current list size
				int ending_point = Mainclass.getThread2_list().size();

				// level loop
				for (int k = 1; k < Mainclass.getLayers(); k++) {

					// call start method for every link in list
					while (starting_point < ending_point) {

						// Check if robot tags exists and allows to follow links
						try {

							RobotTags.checkAccess(Mainclass.getThread2_list().get(
									starting_point));

						} catch (Exception e) {

							System.err.println(e);

						}

						if (robotFollow_thread2) {

							LinkRetrieve.start(Mainclass.getThread2_list().get(
									starting_point));

							// Add All Elements In The List To The Set
							Mainclass.getThread2_set().addAll(
									Mainclass.getThread2_list());

							// Empty List
							Mainclass.getThread2_list().removeAll(
									Mainclass.getThread2_list());

							// Add Back To The List Only Unique Elements
							Mainclass.getThread2_list().addAll(
									Mainclass.getThread2_set());

							// Empty Set
							Mainclass.getThread2_set().removeAll(
									Mainclass.getThread2_set());
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

			} else if (currentThread().getName().equals(Mainclass.getT3name())
					&& Mainclass.getThread3_list().size() != 0) {

				int starting_point = 0;

				// current list size
				int ending_point = Mainclass.getThread3_list().size();

				// level loop
				for (int k = 1; k < Mainclass.getLayers(); k++) {

					// call start method for every link in list
					while (starting_point < ending_point) {

						// Check if robot tags exists and allows to follow links
						try {

							RobotTags.checkAccess(Mainclass.getThread3_list().get(
									starting_point));

						} catch (Exception e) {

							System.err.println(e);

						}

						if (robotFollow_thread3) {

							LinkRetrieve.start(Mainclass.getThread3_list().get(
									starting_point));

							// Add All Elements In The List To The Set
							Mainclass.getThread3_set().addAll(
									Mainclass.getThread3_list());

							// Empty List
							Mainclass.getThread3_list().removeAll(
									Mainclass.getThread3_list());

							// Add Back To The List Only Unique Elements
							Mainclass.getThread3_list().addAll(
									Mainclass.getThread3_set());

							// Empty Set
							Mainclass.getThread3_set().removeAll(
									Mainclass.getThread3_set());
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