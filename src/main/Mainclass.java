import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Mainclass {

	public static int layers = 1; // Default layers is 1
	public static char answer;
	public static String email;
	public static String path; // Path of output
	public static String t1name; // thread 1 name
	public static String t2name; // thread 2 name+
	public static String t3name; // thread 3 name
	public static String link1 = "https://github.com/milouk";
	public static String link2 = "https://review.cyanogenmod.org/";
	public static String link3 = "https://www.cmxlog.com/13";

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
	public static Scanner inputpath;
	public static Scanner yorn;
	public static Scanner mail;
