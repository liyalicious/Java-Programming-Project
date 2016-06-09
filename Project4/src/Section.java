import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Names: Liya Xu, Yangyou Fang
 * Computing IDs: lx2hy, yf2yn
 * Section: 102
 * Date: 03/15/13
 */

/**
 * Section.java: This class represents a single course Section as found from the
 * Lou's List web service from
 * http://stardock.cs.virginia.edu/louslist/Courses/view/.
 * 
 */
public class Section {

	// Private variables representing the different parts of a Section
	private String deptID;
	private int courseNum;
	private String courseName;
	private String section;
	private String meetingType;
	private int units;
	private int seatsTaken;
	private int seatsOffered;
	private String instructor;
	private boolean[] days;
	private int startTime;
	private int endTime;
	private String location;
	private double lat;
	private double lon;

	// --------------------------------------------------------------

	/**
	 * Constructor for Section
	 * 
	 * This method is called when you create a new Section. Pass to it a split
	 * line from reading the CSV. Assign each part of the CSV to the appropriate
	 * variable in the class
	 * 
	 * @param line
	 *            The split line from the CSV
	 */

	public Section(String[] line) {
		// For each part of the line array, assign it to the appropriate
		// variable here.
		days = new boolean[5];
		// Fill in the rest here!
		deptID = line[0];
		courseNum = Integer.parseInt(line[1]);
		courseName = line[3];
		section = line[2];
		meetingType = line[5];
		units = Integer.parseInt(line[6]);
		seatsTaken = Integer.parseInt(line[15]);
		seatsOffered = Integer.parseInt(line[16]);
		instructor = line[4];
		days[0] = Boolean.parseBoolean(line[7]);
		days[1] = Boolean.parseBoolean(line[8]);
		days[2] = Boolean.parseBoolean(line[9]);
		days[3] = Boolean.parseBoolean(line[10]);
		days[4] = Boolean.parseBoolean(line[11]);
		startTime = Integer.parseInt(line[12]);
		endTime = Integer.parseInt(line[13]);
		location = line[14];
		lat = Double.parseDouble(line[17]);
		lon = Double.parseDouble(line[18]);

	}

	/**
	 * This method returns true if the are currently seats available in the
	 * course
	 * 
	 * @return true if there are seats available; false if all seats are filled
	 *         or if the course is over full
	 * 
	 */
	public boolean isOpen() {
		// Change the code in this method to match the description above!
		if (seatsTaken >= seatsOffered) {
			return false;
		}
			return true;
	}

	/**
	 * This method returns true if the passed in Section conflicts with the
	 * calling Section. A conflicting Section must be on the same day at the
	 * same time to conflict.
	 * 
	 * @param s
	 *            The Section to compare with
	 * @return true if the two Sections conflict at at least one time slot;
	 *         false otherwise
	 */
	public boolean conflictsWith(Section s) {
		// Change the code in this method to match the description above!
		
		boolean conflict = false;

		for (int i = 0; i < 5; i++) {
			if (days[i] && s.days[i]) {
//				if ((endTime<=s.startTime && !(s.endTime<=startTime)) ) {
//					conflict = true;
//				} else {
//					conflict = false;
//				}
				
				if(startTime<s.startTime){
					if((s.startTime-startTime)<(endTime-startTime)){
						conflict = true;
					}
				}
					if(startTime>s.startTime){
						if((startTime-s.startTime)<(s.endTime-s.startTime)){
							conflict =true;
						}
					}
					
					if(startTime==s.startTime){
						conflict = true;
					}
				}

			}
		return conflict;

	}

	// METHODS PROVIDED FOR YOU ARE BELOW - YOU DO NOT NEED TO CHANGE ANYTHING
	// BELOW HERE

	public String getFullDays() {
		String retVal = "";

		if (onMo()) {
			retVal += "Mo";
		}
		if (onTu()) {
			retVal += "Tu";
		}
		if (onWe()) {
			retVal += "We";
		}
		if (onTh()) {
			retVal += "Th";
		}
		if (onFr()) {
			retVal += "Fr";
		}

		return retVal;
	}

	@Override
	public String toString() {
		return deptID + " " + courseNum + " " + section + ": " + courseName
				+ " (" + seatsTaken + "/" + seatsOffered + ") " + getFullDays()
				+ " " + startTime + "-" + endTime + " @ " + location + " ("
				+ lat + "," + lon + ")";

	}

	public boolean onMo() {
		return days[0];
	}

	public boolean onTu() {
		return days[1];
	}

	public boolean onWe() {
		return days[2];
	}

	public boolean onTh() {
		return days[3];
	}

	public boolean onFr() {
		return days[4];
	}

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public int getSeatsTaken() {
		return seatsTaken;
	}

	public void setSeatsTaken(int seatsTaken) {
		this.seatsTaken = seatsTaken;
	}

	public int getSeatsOffered() {
		return seatsOffered;
	}

	public void setSeatsOffered(int seatsOffered) {
		this.seatsOffered = seatsOffered;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean[] getDays() {
		return days;
	}

	public void setDays(boolean[] days) {
		this.days = days;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	// TEST CODE - If you run the Section class's main method, you can check if
	// your code is working.
	public static void main(String[] args) {
		String baseURL = "http://stardock.cs.virginia.edu/louslist/Courses/view/CS/1110";
		ArrayList<Section> sections = new ArrayList<Section>();
		Scanner website = null;
		try {
			website = new Scanner(new URL(baseURL).openStream());
		} catch (Exception e) {
			System.out.println("Invalid URL");
		}

		while (website.hasNextLine()) {
			String[] line = website.nextLine().split(";");
			sections.add(new Section(line));
		}

		System.out
				.println("Testing whether the Sections were read correctly by your constructor method - check below!");
		for (Section s : sections) {
			System.out.println(s);
		}

		System.out.println("Testing your isOpen method...");
		if (sections.get(0).isOpen()) {
			System.out.println("Open classes work!");
		} else {
			System.out.println("OPEN CLASSES DOESN'T WORK!");
		}
		if (!sections.get(2).isOpen() && !sections.get(3).isOpen()) {
			System.out.println("Closed classes work!");
		} else {
			System.out.println("CLOSED CLASSES DOESN'T WORK!");
		}

		System.out.println("Testing your conflictsWith method...");
		if (!sections.get(0).conflictsWith(sections.get(1))) {
			System.out.println("Non-conflicting works!");
		} else {
			System.out.println("NON-CONFLICTING DOESN'T WORK!");
		}
		if (sections.get(1).conflictsWith(sections.get(12))) {
			System.out.println("Conflicting works!");
		} else {
			System.out.println("CONFLICTING DOESN'T WORK!");
		}

		baseURL = "http://stardock.cs.virginia.edu/louslist/Courses/view/CS/2102";
		sections = new ArrayList<Section>();
		website = null;
		try {
			website = new Scanner(new URL(baseURL).openStream());
		} catch (Exception e) {
			System.out.println("Invalid URL");
		}

		while (website.hasNextLine()) {
			String[] line = website.nextLine().split(";");
			sections.add(new Section(line));
		}

		System.out
				.println("Testing whether the Sections were read correctly by your constructor method - check below!");
		for (Section s : sections) {
			System.out.println(s);
		}

		System.out
				.println("Testing your conflictsWith method with 2102 data...");
		if (!sections.get(0).conflictsWith(sections.get(1))) {
			System.out.println("Non-conflicting works!");
		} else {
			System.out.println("NON-CONFLICTING DOESN'T WORK!");
		}
		if (!sections.get(1).conflictsWith(sections.get(2))) {
			System.out.println("Conflicting works!");
		} else {
			System.out
					.println("CONFLICTING DOESN'T WORK! - check for == with checking days!  It needs to be &&!");
		}
	}

}