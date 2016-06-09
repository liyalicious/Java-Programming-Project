import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Names: Liya Xu, Yangyou Fang
 * Computing IDs: lx2hy, yf2yn
 * Section: 102
 * Date: 03/15/13
 */

/**
 * Instructions for HW4: To complete HW4, you will fill in select methods in
 * this file and in Section.java. We suggest you follow the numbered steps to
 * complete this assignment successfully.
 * 
 */
public class Scheduler {

	public static void main(String[] args) throws Exception {

		// 1. First, go over to the Section.java file and fill in the three
		// methods there. When you are done, you can run
		// Section.java and you can see if your code is working. If all the CS
		// 1110 lectures and labs appear with the right data and the messages at
		// the end are okay, then you're ready to move on.
		// Do not move on until Section.java is working correctly!

		// 2. Print the title to the screen and read classes from the user -
		// quitting if they type "quit".
		// Then ask the user if they would like to consider only classes with
		// seats available.

		System.out.println("Welcome to the UVa Class Scheduler!");
		String input = "";
		Scanner keyboard = new Scanner(System.in);
		ArrayList<String> requestList = new ArrayList<String>();

		// --------------------
		// Fill in code here to read the user's input into requestList, ending
		// with quit
		// --------------------

		while (!input.equalsIgnoreCase("quit")) {
			System.out.println("Enter a class (or quit): ");
			input = keyboard.nextLine();
			if (!input.equalsIgnoreCase("quit")) {
				requestList.add(input);
			}
		}

		System.out.print("Include only classes with seats available? (y/n): ");
		char includeOpenOnly = keyboard.nextLine().charAt(0);

		// 3. Jump down to the loadSections method and write the code needed to
		// read all the lecture sections from a class into an ArrayList of
		// Sections based on the course name passed in.

		// 4. Once this method has been completed, write code here that will
		// print the course requests to the screen and then loads an ArrayList
		// of ArrayList of Sections defined here with the Sections.

		ArrayList<ArrayList<Section>> courseList = new ArrayList<ArrayList<Section>>();

		// --------------------
		// Fill in code here to print the request list and load courseList with
		// the ArrayLists of Sections
		// --------------------
		System.out.println("You have requested:");
		for (int i = 0; i < requestList.size(); i++) {
			System.out.println(requestList.get(i));
		}

		for (int j = 0; j < requestList.size(); j++) {
			courseList.add(loadSections(requestList.get(j)));
		}

		// 5. Fill in the code in the printSections method to print available
		// sections to the screen to show that you can load
		// all the course sections properly
		printSections(courseList, "Sections Available:");

		// 6. Fill in the code in getScheduleOptions that will create all the
		// possible combinations,
		// then print all the combinations to the screen
		ArrayList<ArrayList<Section>> scheduleOptions = getScheduleOptions(courseList);
		printSections(scheduleOptions, "Possible Schedules:");

		// 7. Fill in the methods that will remove any schedules that have any
		// conflicting Sections
		checkAllForConflicts(scheduleOptions);
		printSections(scheduleOptions, "Non-Conflicting Schedules:");

		// 8. Fill in the methods that will remove any schedules that have any
		// courses in them that are closed. Then here, if the user decided to
		// look at only open courses, call the appropriate method and then print
		// those sections to the screen.

		if (includeOpenOnly == 'y') {
			checkAllForOpenSeats(scheduleOptions);
			printSections(scheduleOptions, "Available Schedules:");
		}

		// 9. Fill in the method to show the Google map of one schedule that the
		// user selects.
		System.out.print("\nWhich schedule would you like to map?: ");
		int choice = keyboard.nextInt();
		if (choice - 1 < scheduleOptions.size())
			createImage(scheduleOptions.get(choice - 1));
		else
			System.out.println("Not a valid schedule.");

	}

	/**
	 * This method takes as a parameter a single course id, in the form
	 * "CS 1110" or "ENGR 1620" and returns an ArrayList of Sections read from
	 * the website. Only include lecture sections! We do not want labs,
	 * discussions, independent studies, etc.
	 * 
	 * @param course
	 *            The name of the course to read in the form "CS 1110"
	 * @return an ArrayList of Sections read from the website.
	 */
	private static ArrayList<Section> loadSections(String course)
			throws Exception {
		ArrayList<Section> sections = new ArrayList<Section>();

		// Fill in the code here!
		String baseURL = "http://stardock.cs.virginia.edu/louslist/Courses/view/";
		String[] newCourse = course.split(" ");

		Scanner sectionwebsite = new Scanner(new URL(baseURL + newCourse[0]
				+ "/" + newCourse[1]).openStream());

		while (sectionwebsite.hasNextLine()) {
			String[] line = sectionwebsite.nextLine().split(";");
			if (line[5].equalsIgnoreCase("Lecture")) {
				sections.add(new Section(line));
			}
		}

		return sections;
	}

	/**
	 * Fill in the code here to print all of the lists of Sections, numbering
	 * them starting with 1.
	 * 
	 * @param courseList
	 *            The ArrayList of ArrayLists of Sections
	 * @param title
	 *            The title to print at the top
	 */
	private static void printSections(ArrayList<ArrayList<Section>> courseList,
			String title) {
		// Fill in the code here to match the example output!
		System.out.println("\n" + title);
		for (int i = 0; i < courseList.size(); i++) {
			String num = (i + 1) + "";
			System.out.println("\nSchedule " + num + ":");
			for (int j = 0; j < courseList.get(i).size(); j++) {
				System.out.println(courseList.get(i).get(j));
			}

		}

	}

	/**
	 * This method represents the real core of the program. Take the course
	 * sections from the various ArrayLists in the courseList that is passed in
	 * and return a new set of ArrayLists that have one Section from each list
	 * of Sections to create a viable schedule. Note - this method does NOT
	 * check for any conflicts; it just sets up the various possible
	 * combinations.
	 * 
	 * @param courseList
	 *            The ArrayList of ArrayLists of Sections
	 * @return An ArrayList of ArrayLists of Sections with the combinations of
	 *         courses
	 */

	private static ArrayList<ArrayList<Section>> getScheduleOptions(
			ArrayList<ArrayList<Section>> courseList) {

		ArrayList<ArrayList<Section>> possibleSchedules = new ArrayList<ArrayList<Section>>();

		// 6a. First, find out how many schedules there will be and print that
		// to the screen...

		// Fill in the code here!

		int possible = 1;// initialization
		for (int i = 0; i < courseList.size(); i++) {
			possible = possible * courseList.get(i).size();
		}
		System.out.println("\nNum Possible Schedules: " + possible);

		// 6b. This is the hard part. How do you turn a set that looks like
		// this:
		/**
		 * 1. 1110 001, 1110 002 2. 2102 001, 2102 002, 2102 003
		 */
		// into this:
		/**
		 * 1. 1110 001, 2102 001 2. 1110 001, 2102 002 3. 1110 001, 2102 003 4.
		 * 1110 002, 2102 001 5. 1110 002, 2102 002 6. 1110 002, 2102 003
		 */
		// There are a lot of possible algorithms, but we highly suggest you do
		// some research on what is out
		// there to create all combinations from a list of lists (sometimes
		// called a Cartesian Product).

		// Return the finished ArrayList of ArrayLists
		int course_size = courseList.size();

		int[] size = new int[course_size];
		for (int i = 0; i < course_size; i++) {
			size[i] = courseList.get(i).size();
		}

		for (int i = 0; i < possible; i++) {
			possibleSchedules.add(new ArrayList<Section>());
		}

		for (int i = 0; i < courseList.size(); i++) {

			int replica = 1;
			for (int s = i + 1; s < courseList.size(); s++) {
				replica = replica * size[s];
			}

			int gap = size[i] * replica;

			for (int j = 0; j < courseList.get(i).size(); j++) {
				for (int k = j * replica; k < possible; k += gap) {
					for (int l = 0; l < replica; l++) {
						if (k + l < possible) {
							possibleSchedules.get(k + l).add(
									courseList.get(i).get(j));
						}
					}
				}
			}
		}

		return possibleSchedules;

	}

	/**
	 * This method should remove all ArrayList schedules from the ArrayList of
	 * ArrayLists that are not viable due to having at least two Sections that
	 * conflict. To break out the functionality and make this a bit simpler, you
	 * should use the checkForConflicts method, which takes a single schedule.
	 * Thus in this method, you should just loop over all the possible
	 * scheduleOptions and call checkForConflicts on each, handling the result
	 * appropriately.
	 * 
	 * @param scheduleOptions
	 *            An ArrayList of ArrayLists of Sections representing the
	 *            possible schedules
	 */
	private static void checkAllForConflicts(
			ArrayList<ArrayList<Section>> scheduleOptions) {
		// Fill in the code here!

		// boolean[] Con = new boolean[scheduleOptions.size()];
		// for (int k = 0; k < Con.length; k++) {
		// Con[k]=false;
		// }
		//
		//
		// for (int i = 0; i < scheduleOptions.size(); i++) {
		//
		// for (int j = 0; j < scheduleOptions.get(i).size(); j++) {
		// for (int k = j + 1; k < scheduleOptions.get(i).size(); k++) {
		// if (scheduleOptions.get(i).get(j)
		// .conflictsWith(scheduleOptions.get(i).get(k))) {
		// Con[i]=true;
		// }
		// }
		// }
		// }

		ArrayList<ArrayList<Section>> schedule1 = new ArrayList<ArrayList<Section>>();

		for (int k = 0; k < scheduleOptions.size(); k++) {
			if (!checkForConflicts(scheduleOptions.get(k))) {
				schedule1.add(scheduleOptions.get(k));
			}
		}
		scheduleOptions.clear();

		for (int k = 0; k < schedule1.size(); k++) {
			scheduleOptions.add(schedule1.get(k));
		}
	}

	/**
	 * This method should loop over the Sections in a singleSchedule and
	 * determine if any pair of Sections overlap. If so, return true. Otherwise,
	 * return false.
	 * 
	 * @param singleSchedule
	 *            One ArrayList of Sections as a single schedule
	 * @return true if at least one conflict exists; false if no conflicts
	 */
	private static boolean checkForConflicts(ArrayList<Section> singleSchedule) {
		// Fill in the code here!


		for (int i = 0; i < singleSchedule.size(); i++) {
			for (int j = i + 1; j < singleSchedule.size(); j++) {
				if (singleSchedule.get(i).conflictsWith(singleSchedule.get(j))) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Similar to checkForConflicts, this method will remove all ArrayList
	 * schedules from the ArrayList of ArrayLists that have any course Sections
	 * in them that are currently full (i.e. not Open, using the isOpen method
	 * from Section). To break out the functionality and make this a bit
	 * simpler, you should use the checkForOpenSeats method, which takes a
	 * single schedule. Thus in this method, you should just loop over all the
	 * possible scheduleOptions and call checkForConflicts on each, handling the
	 * result appropriately.
	 * 
	 * @param scheduleOptions
	 *            An ArrayList of ArrayLists of Sections representing the
	 *            possible schedules
	 */
	private static void checkAllForOpenSeats(
			ArrayList<ArrayList<Section>> scheduleOptions) {
		// Fill in the code here!
		ArrayList<ArrayList<Section>> schedule2 = new ArrayList<ArrayList<Section>>();

		for (int i = 0; i < scheduleOptions.size(); i++) {
			if (!checkForConflicts(scheduleOptions.get(i))
					&& checkForOpenSeats(scheduleOptions.get(i))) {
				schedule2.add(scheduleOptions.get(i));
			}
		}
		scheduleOptions.clear();

		for (int k = 0; k < schedule2.size(); k++) {
			scheduleOptions.add(schedule2.get(k));
		}
	}

	/**
	 * This method should loop over the Sections in a singleSchedule and
	 * determine if any of the Sections are full. If any Section is full, return
	 * false (indicating this schedule is not valid). If all Sections are open,
	 * return true;
	 * 
	 * @param singleSchedule
	 *            One ArrayList of Sections as a single schedule
	 * @return true if all Sections are open; false otherwise
	 */
	private static boolean checkForOpenSeats(ArrayList<Section> singleSchedule) {
		// Fill in the code here!

		for (int i = 0; i < singleSchedule.size(); i++) {
			if (singleSchedule.get(i).isOpen()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * This method take an ArrayList of Sections (that has latitude and
	 * longitude values) and will build a valid URL that you can use with the
	 * GoogleMap class. Check there for more info.
	 * 
	 * @param list
	 *            A schedule that you would like to map
	 */
	public static void createImage(ArrayList<Section> list) {
		// Fill in the code here!

		String mapURL = "http://maps.googleapis.com/maps/api/staticmap?zoom=16&size=1000x1000&maptype=roadmap";
		String middleURL = "";
		String middleURL2 = "";
		double[] latitude = new double[list.size()];
		double[] longtitude = new double[list.size()];

		for (int i = 0; i < list.size(); i++) {
			latitude[i] = list.get(i).getLat();
			longtitude[i] = list.get(i).getLon();

			if (list.get(i).getDays()[0] == true
					|| list.get(i).getDays()[2] == true
					|| list.get(i).getDays()[4] == true) {
				middleURL = middleURL + "&markers=color:blue|" + latitude[i] + ","
						+ longtitude[i];
			}else if (list.get(i).getDays()[1] == true || list.get(i).getDays()[3] == true){
				middleURL2 = middleURL2 + "&markers=color:red|" + latitude[i] + ","
						+ longtitude[i];
			}
		}

		String finalURL = mapURL + middleURL + middleURL2+ "&sensor=false";

		System.out.println(finalURL);

		GoogleMap newMap = new GoogleMap();
		newMap.createImage(finalURL, "Locations of Classrooms (Blue: MoWeFr, Red: TuTh)");

	}

}
