import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Mainclass extends Thread {

	private static int file_number; // number of files is a directory
	private static int run_times = 1;
	private static int layers = 1; // Default layers is 1
	private static char answer; // If user desires or not to receive a result
								// email
    private static long timer; // store time
	private static String email; // User email
	private static String path; // Path of output
	private static String t1name; // thread 1 name
	private static String t2name; // thread 2 name
	private static String t3name; // thread 3 name
	private static String link1 = "https://github.com/milouk";
	private static String link2 = "https://review.cyanogenmod.org/";
	private static String link3 = "https://www.cmxlog.com/13";

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

			System.out.printf("%s", "Enter a Valid Email : ");
			email = mail.nextLine();

		}

		System.out.printf("%s",
				"Enter How Many Layers You Would Like The Crawler To Complete (Default is 1 Layer)  : ");

		layers = input.nextInt();

		// if user enters 0 for layers number, Program terminates
		if (layers != 0) {

			if (run_times == 1) {

				System.out.printf("%s", "Enter a Valid Path To Create All Html Files : ");
				path = inputpath.nextLine();

			}

			System.out.println("\n****************************************************");
			System.out.println("*Crawler Will Update The Database Every 24 Hours...*");
			System.out.println("****************************************************\n\n");

			thread.start();

		} else {

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

					Thread.sleep(4000);

				} catch (InterruptedException e) {

				}

			}

	}

