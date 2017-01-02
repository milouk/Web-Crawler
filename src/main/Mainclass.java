package com.complet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * <h1>Main Class - Mainclass.java</h1> Mainclass class is the "starting" class.
 * It contains the most important methods such as run , crawl and main methods.
 * <p>
 * <b>Note: </b>If crawl run method crashes the whole program does also.
 *
 * @author Complet
 * @version 5.0
 * @since 2017-01-02
 */

public class Mainclass extends Thread {

	private static int run_times = 1; // Counts the times crawler has ran
	private static int layers = 1; // Default layers is 1
	private static char answer; // If user desires or not to receive a result
								// email
	private static String email; // User email
	private static String path; // Path of output
	private static int position; // Database Primary Key
	private static Date date = null;// Store Time
	private static String path2; // Root path of link folders
	private static String t1name; // thread 1 name
	private static String t2name; // thread 2 name
	private static String t3name; // thread 3 name
	private static String link1 = "https://gmail.com/";
	private static String link2 = "https://review.cyanogenmod.org/";
	private static String link3 = "https://www.cmxlog.com/14.1/";

	// thread result lists
	private static ArrayList<String> thread1_list = new ArrayList<String>();
	private static ArrayList<String> thread2_list = new ArrayList<String>();
	private static ArrayList<String> thread3_list = new ArrayList<String>();
	// Final Result List
	private static ArrayList<String> finalist = new ArrayList<String>();

	private static Scanner input;
	private static Scanner inputpath;
	private static Scanner yorn;
	private static Scanner mail;

	/**
	 * <p>
	 * This is the program's Main Method.<br>
	 * The only parameter is String[].
	 * <p>
	 * This is where the user enters whether he/she wants to receive an email or
	 * not, how many layers he/she wants and the directory path to create all
	 * Html Files.
	 * <p>
	 *
	 * @param args
	 *            A String Array which is used to enter arguments before
	 *            running.
	 */

	public static void main(String[] args) {

		Mainclass thread = new Mainclass();
		input = new Scanner(System.in);
		inputpath = new Scanner(System.in);
		yorn = new Scanner(System.in);
		mail = new Scanner(System.in);

		// Ask user to send an email when process finishes
		System.out.print("Would you like the results to be sent in an email? [Y/N] : ");
		answer = yorn.next().charAt(0);

		while (answer != 'Y' && answer != 'y' && answer != 'N' && answer != 'n') {

			System.out.printf("%s", "Please Enter a Valid Answer : ");
			answer = yorn.next().charAt(0);
		}

		// Acquire Users Email
		if (answer == 'y' || answer == 'Y') {

			setDate(date = new Date());
			System.out.printf("%s", "Enter a Valid Email : ");
			email = mail.nextLine();

		}

		// Get Layers
		System.out.printf("%s", "Enter How Many Layers You Would Like The Crawler To Complete : ");

		layers = input.nextInt();

		// if user enters 0 for layers number, Program terminates
		if (layers != 0) {

			if (run_times == 1) {

				System.out.printf("%s", "Enter a Valid Path To Create All Html Files : ");
				path = inputpath.nextLine();
				HtmlFiles.checkPath(path);

			}

			System.out.println("\n****************************************************");
			System.out.println("*Crawler Will Update The Database Every 24 Hours...*");
			System.out.println("****************************************************\n\n");

			// Start Thread
			thread.start();

		} else {

			if (answer == 'Y' || answer == 'y') {

				setDate(date = new Date());
				GetCredentials.emailCredentials("Path to EmailCredentials.txt");
				EmailSending.email(email);
			}

			System.out.println("\n************");
			System.out.println("* Finished *");
			System.out.println("************\n\n");
		}

	}

	/**
	 * <p>
	 * This is the run method of the class.<br>
	 * it has NO parameters.
	 * <p>
	 * It contains a never-ending loop which keeps the program running
	 * forever.In order the database to be updated every 24 hours , the thread
	 * sleeps for 24 hours and then it runs again.
	 * <p>
	 *
	 * @exception InterruptedException
	 *                occurs when the thread while sleeping is interrupted by
	 *                another process or thread.
	 * @exception Exception
	 *                occurs when something goes wrong at crawl method.
	 */

	@Override
	public final void run() {

		for (;;) {

			System.out.println("Running for " + run_times + " time!");

			try {

				crawl();

			} catch (Exception e) {
				System.err.println("An error has occured in Crawl Mrthod! ");
			}

			// Count Runs
			run_times++;

			try {
				// to be changed to 86400 * 1000
				Thread.sleep(4000);

			} catch (InterruptedException e) {

			}

		}

	}

	/**
	 * <p>
	 * This is the crawl method of the class.<br>
	 * it has NO parameters.
	 * <p>
	 * This is where the program begins , all the lists get cleared and ready
	 * for next run, urls with "noindex" get removed, the email is sent to the
	 * user, the html files are created and the database is updated.
	 * <p>
	 *
	 * @exception InterruptedException
	 *                occurs when the thread while sleeping is interrupted by
	 *                another process or thread.
	 * @exception IOException
	 *                occurs when something goes wrong when writing the files in
	 *                the given path.
	 */

	public static void crawl() {

		// Create & Start Threads
		RunClass.begin();

		// Concatenate all lists to final list
		finalist.addAll(thread1_list);
		finalist.addAll(thread2_list);
		finalist.addAll(thread3_list);

		RobotTags.mIndex.addAll(RobotTags.thread1_mIndex);
		RobotTags.mIndex.addAll(RobotTags.thread2_mIndex);
		RobotTags.mIndex.addAll(RobotTags.thread3_mIndex);

		// Clear everything and get it ready for next run
		thread1_list.removeAll(thread1_list);
		thread2_list.removeAll(thread2_list);
		thread3_list.removeAll(thread3_list);
		RobotTags.thread1_mIndex.removeAll(RobotTags.thread1_mIndex);
		RobotTags.thread2_mIndex.removeAll(RobotTags.thread2_mIndex);
		RobotTags.thread3_mIndex.removeAll(RobotTags.thread3_mIndex);

		if (RobotTags.mIndex.size() != 0) {
			RobotTags.remover();
		}

		RobotTags.mIndex.removeAll(RobotTags.mIndex);

		if (answer == 'Y' || answer == 'y') {
			GetCredentials.emailCredentials("Path to EmailCredentials.txt");
			EmailSending.email(email);
		}

		System.out.println("Creating HTML Files in : " + path2 + " !...");

		// Delete Old Files and Database in order to replace them with new ones.
		if (Mainclass.getRun_times() > 1) {

			// DatabaseConnection.deleteData("DatabaseOfURLs");
			File dir = new File(path2);
			// Delete Old Files
			HtmlFiles.deleteDirectory(dir);
			// Re-Create Html files Directory
			dir.mkdir();

		}

		for (int i = 0; i < finalist.size(); i++) {

			try {

				// If First Link
				if (i == 0) {
					// Directory Tree
					path = path2.concat("\\1-100");
					File theDir = new File(path);
					// Create Directory
					theDir.mkdir();
					position = 1;
				} else if (i % 100 == 0) {
					path = path2.concat("\\")
							.concat(String.valueOf(i + 1).concat(" - ").concat(String.valueOf(i + 100)));
					File theDir = new File(path);
					theDir.mkdir();

				}
				// Write HTML Files
				HtmlFiles.createFile(finalist.get(i), path, i + 1);
				// Insert Urls , Paths to SQL DB
				// DatabaseConnection.insertData(finalist.get(i), path);
				// position++;

				try {

					Thread.sleep(800);

				} catch (InterruptedException e) {
					System.err.println("Thread was interrupted! ");
				}

			} catch (IOException e) {

				System.err.println("IO Exception Handled");

			}
		}

		// Empty Finalist for Next Run
		finalist.removeAll(finalist);

		System.out.println("\n************");
		System.out.println("* Finished *");
		System.out.println("************\n\n");

	}

	// Getters and Setters

	/**
	 * @return run_times which is how many times the database has been updated.
	 */
	public static int getRun_times() {
		return run_times;
	}

	/**
	 * @return layers which is how many layers the user has entered.
	 */
	public static int getLayers() {
		return layers;
	}

	/**
	 * @return thread1_list which contains all links crawled by thread #1.
	 */
	public static ArrayList<String> getThread1_list() {
		return thread1_list;
	}

	/**
	 * @return thread2_list which contains all links crawled by thread #2.
	 */
	public static ArrayList<String> getThread2_list() {
		return thread2_list;
	}

	/**
	 * @return thread3_list which contains all links crawled by thread #3.
	 */
	public static ArrayList<String> getThread3_list() {
		return thread3_list;
	}

	/**
	 * @return finalist which contains all links crawled by all threads.
	 */
	public static ArrayList<String> getFinalist() {
		return finalist;
	}

	/**
	 * @return t1name which is thread's #1 name.
	 */
	public static String getT1name() {
		return t1name;
	}

	/**
	 * @param t1name
	 *            sets thread #1 name.
	 */
	public static void setT1name(String t1name) {
		Mainclass.t1name = t1name;
	}

	/**
	 * @return t2name which is thread's #2 name.
	 */
	public static String getT2name() {
		return t2name;
	}

	/**
	 * @param t2name
	 *            sets thread #2 name.
	 */
	public static void setT2name(String t2name) {
		Mainclass.t2name = t2name;
	}

	/**
	 * @return t3name which is thread's #3 name.
	 */
	public static String getT3name() {
		return t3name;
	}

	/**
	 * @param t3name
	 *            sets thread #3 name.
	 */
	public static void setT3name(String t3name) {
		Mainclass.t3name = t3name;
	}

	/**
	 * @return link1 which is the base url of thread #1.
	 */
	public static String getLink1() {
		return link1;
	}

	/**
	 * @return link2 which is the base url of thread #2.
	 */
	public static String getLink2() {
		return link2;
	}

	/**
	 * @return link3 which is the base url of thread #3.
	 */
	public static String getLink3() {
		return link3;
	}

	/**
	 * @return inputpath.
	 */
	public static Scanner getInputpath() {
		return inputpath;
	}

	/**
	 * @return position which is the primary key of the database.
	 */
	public static int getPosition() {
		return position;
	}

	/**
	 * @return path2 which is the root path of the path html files are created.
	 */
	public static String getPath2() {
		return path2;
	}

	/**
	 * @param path2
	 */
	public static void setPath2(String path2) {
		Mainclass.path2 = path2;
	}

	/**
	 * @return path
	 */
	public static String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            which is the path initially given from the user.
	 */
	public static void setPath(String path) {
		Mainclass.path = path;
	}

	/**
	 * @return date which stores how much time the program took to complete.
	 */
	public static Date getDate() {
		return date;
	}

	/**
	 * @param date
	 */
	public static void setDate(Date date) {
		Mainclass.date = date;
	}

}
