import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class SRSDataAccess
{
	private static final String SCHEDULE_FILE_NAME = "SoC_";
	private static final String FACULTY_FILE_NAME = "Faculty.dat";
	private static final String ASSIGNMENTS_FILE_NAME = "TeachingAssignments.dat";
	private static final String COURSE_FILE_NAME = "CourseCatalog.dat";
	private static final String PREREQ_FILE_NAME = "Prerequisites.dat";
	private static ScheduleOfClasses scheduleOfClasses = null;
	private static CourseCatalog courseCatalog = null;
	private static Scanner fileScan;

	public static Faculty initializeFaculty() throws FileNotFoundException
	{
		fileScan = new Scanner(new File(FACULTY_FILE_NAME));
		ArrayList<Professor> profList = new ArrayList<Professor>();
		while (fileScan.hasNextLine())
		{
			String line = fileScan.nextLine();
			String[] items = line.split("\t");
			Professor newProf = new Professor(items[0],items[1],items[2],items[3]);
			profList.add(newProf);
		}
		fileScan.close();
		fileScan = new Scanner(new File(ASSIGNMENTS_FILE_NAME));
		while (fileScan.hasNextLine())
		{
			String line = fileScan.nextLine();
			String[] items = line.split("\t");
			// issues here!!! Where are we supposed to get the sections from?
		}

		Faculty returnFaculty = new Faculty(profList);
		return returnFaculty;
	}
}