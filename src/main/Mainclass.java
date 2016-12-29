package com.complet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Mainclass extends Thread {

	private static int run_times = 1; // Counts the times crawler has ran
	private static int layers = 1; // Default layers is 1
	private static char answer; // If user desires or not to receive a result
								// email
	private static String email; // User email
	private static String path; // Path of output
	private static int position;
	private static Date date = null;// Store Time
	private static String path2; // Root path of link folders
	private static String t1name; // thread 1 name
	private static String t2name; // thread 2 name
	private static String t3name; // thread 3 name
	private static String link1 = "https://review.cyanogenmod.org/";
	private static String link2 = "https://gmail.com/";
	private static String link3 = "https://www.cmxlog.com/14.1/";

	// thread 1 result list
	private static ArrayList<String> thread1_list = new ArrayList<String>();
	private static Set<String> thread1_set = new LinkedHashSet<String>();

	// thread 2 result list
	private static ArrayList<String> thread2_list = new ArrayList<String>();
	private static Set<String> thread2_set = new LinkedHashSet<String>();

	// thread 3 result list
	private static ArrayList<String> thread3_list = new ArrayList<String>();
	private static Set<String> thread3_set = new LinkedHashSet<String>();

	// Final Result List
	private static ArrayList<String> finalist = new ArrayList<String>();

	private static Scanner input;
	private static Scanner inputpath;
	private static Scanner yorn;
	private static Scanner mail;

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

			thread.start();

		} else {

			if (answer == 'Y' || answer == 'y') {

				setDate(date = new Date());
				GetCredentials.emailCredentials(
						"C:\\Users\\Michalis\\Business\\DMST\\3rd Semester\\Programming II\\workplace\\Web Crawler\\src\\com\\complet\\EmailCredentials.txt");
				EmailSending.email(email);
			}

			System.out.println("\n************");
			System.out.println("* Finished *");
			System.out.println("************\n\n");
		}

	}

	@Override
	public final void run() {

		for (;;) {

			System.out.println("Running for " + run_times + " time!");

			try {

				crawl();

			} catch (Exception e) {
			}

			run_times++;

			try {
				// to be changed to 86400 * 1000
				Thread.sleep(4000);

			} catch (InterruptedException e) {

			}

		}

	}

	public static void crawl() {

		// Create & Start Threads
		RunClass.begin();

		// Concatenate all lists to final list
		finalist.addAll(thread1_list);
		finalist.addAll(thread2_list);
		finalist.addAll(thread3_list);

		// Clear everything and get it ready for next run
		thread1_list.removeAll(thread1_list);
		thread2_list.removeAll(thread2_list);
		thread3_list.removeAll(thread3_list);
		RobotTags.mIndex.addAll(RobotTags.thread1_mIndex);
		RobotTags.mIndex.addAll(RobotTags.thread2_mIndex);
		RobotTags.mIndex.addAll(RobotTags.thread3_mIndex);
		RobotTags.thread1_mIndex.removeAll(RobotTags.thread1_mIndex);
		RobotTags.thread2_mIndex.removeAll(RobotTags.thread2_mIndex);
		RobotTags.thread3_mIndex.removeAll(RobotTags.thread3_mIndex);

		if (RobotTags.mIndex.size() != 0) {
			RobotTags.remover();
		}

		RobotTags.counter = 0;
		RobotTags.mIndex.removeAll(RobotTags.mIndex);

		if (answer == 'Y' || answer == 'y') {
			GetCredentials.emailCredentials(
					"C:\\Users\\Michalis\\Business\\DMST\\3rd Semester\\Programming II\\workplace\\Web Crawler\\src\\com\\complet\\EmailCredentials.txt");
			EmailSending.email(email);
		}

		System.out.println("Creating HTML Files in : " + path2 + " !...");

		// Delete Old Files and Database in order to replace them with new ones.
		if (Mainclass.getRun_times() > 1) {

			// DatabaseConnection.deleteData("DatabaseOfURLs");
			File dir = new File(path2);
			HtmlFiles.deleteDirectory(dir);
			dir.mkdir();

		}

		for (int i = 0; i < finalist.size(); i++) {

			try {

				if (i == 0) {
					path = path2.concat("\\1-100");
					File theDir = new File(path);
					theDir.mkdir();
					position = 1;
				} else if (i % 100 == 0) {
					path = path2.concat("\\")
							.concat(String.valueOf(i + 1).concat(" - ").concat(String.valueOf(i + 100)));
					File theDir = new File(path);
					theDir.mkdir();

				}

				HtmlFiles.createFile(finalist.get(i), path, i + 1);
				// DatabaseConnection.insertData(finalist.get(i), path);
				// position++;

				try {

					Thread.sleep(800);

				} catch (InterruptedException e) {
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

	public static int getRun_times() {
		return run_times;
	}

	public static int getLayers() {
		return layers;
	}

	public static ArrayList<String> getThread1_list() {
		return thread1_list;
	}

	public static Set<String> getThread1_set() {
		return thread1_set;
	}

	public static ArrayList<String> getThread2_list() {
		return thread2_list;
	}

	public static Set<String> getThread2_set() {
		return thread2_set;
	}

	public static ArrayList<String> getThread3_list() {
		return thread3_list;
	}

	public static Set<String> getThread3_set() {
		return thread3_set;
	}

	public static ArrayList<String> getFinalist() {
		return finalist;
	}

	public static void setPath(String path) {
		Mainclass.path = path;
	}

	public static String getT1name() {
		return t1name;
	}

	public static void setT1name(String t1name) {
		Mainclass.t1name = t1name;
	}

	public static String getT2name() {
		return t2name;
	}

	public static void setT2name(String t2name) {
		Mainclass.t2name = t2name;
	}

	public static String getT3name() {
		return t3name;
	}

	public static void setT3name(String t3name) {
		Mainclass.t3name = t3name;
	}

	public static String getLink1() {
		return link1;
	}

	public static String getLink2() {
		return link2;
	}

	public static Scanner getInputpath() {
		return inputpath;
	}

	public static String getLink3() {
		return link3;
	}

	public static int getPosition() {
		return position;
	}

	public static String getPath2() {
		return path2;
	}

	public static void setPath2(String path2) {
		Mainclass.path2 = path2;
	}

	public static String getPath() {
		return path;
	}

	public static Date getDate() {
		return date;
	}

	public static void setDate(Date date) {
		Mainclass.date = date;
	}

}
