import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Mainclass {

	private static int layers = 1; // Default layers is 1
	private static char answer;
	private static String email;
	private static String path; // Path of output
	private static String t1name; // thread 1 name
	private static String t2name; // thread 2 name+
	private static String t3name; // thread 3 name
	private static String link1 = "https://github.com/milouk";
	private static String link2 = "https://review.cyanogenmod.org/";
	private static String link3 = "https://www.cmxlog.com/13";

	// thread 1 result list
	public static ArrayList<String> thread1_list = new ArrayList<String>();
	public static Set<String> thread1_set = new LinkedHashSet<String>();

	// thread 2 result list
	public static ArrayList<String> thread2_list = new ArrayList<String>();
	public static Set<String> thread2_set = new LinkedHashSet<String>();

	// thread 3 result list
	public static ArrayList<String> thread3_list = new ArrayList<String>();
	public static Set<String> thread3_set = new LinkedHashSet<String>();

	// Final Result List
	public static ArrayList<String> finalist = new ArrayList<String>();

	private static Scanner input;
	private static Scanner inputpath;
	private static Scanner yorn;
	private static Scanner mail;

	//Gets for thread names
	public String getT1name() {
		return t1name;
	}
	public String getT2name() {
		return t2name;
	}
	public String getT3name() {
		return t3name;
	}

	//Gets for links
	public String getLink1() {
		return link1;
	}
	public String getLink2() {
		return link2;
	}
	public String getLink3() {
		return link3;
	}


    public static void main(final String[] args) throws Exception {

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
