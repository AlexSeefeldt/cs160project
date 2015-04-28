import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class SRSDataAccess
{
	private static final String SCHEDULE_FILE_NAME = "SRSDatFiles\\SoC_";
	private static final String FACULTY_FILE_NAME = "SRSDatFiles\\Faculty.dat";
	private static final String ASSIGNMENTS_FILE_NAME = "SRSDatFiles\\TeachingAssignments.dat";
	private static final String COURSE_FILE_NAME = "SRSDatFiles\\CourseCatalog.dat";
	private static final String PREREQ_FILE_NAME = "SRSDatFiles\\Prerequisites.dat";
	private static ScheduleOfClasses scheduleOfClasses = null;
	private static CourseCatalog courseCatalog = null;
	private static Scanner fileScan;

	public static CourseCatalog initializeCourseCatalog() throws FileNotFoundException
	{
		fileScan = new Scanner(new File(COURSE_FILE_NAME));
		ArrayList<Course> courseList = new ArrayList<Course>();
		while (fileScan.hasNextLine())
		{
			String line = fileScan.nextLine();
			String[] items = line.split("\t");
			Course newCourse = new Course(items[1],items[0]);
			newCourse.setCredits(Double.parseDouble(items[2]));
			courseList.add(newCourse);
		}
		fileScan.close();
		courseCatalog = new CourseCatalog(courseList);
		fileScan = new Scanner(new File(PREREQ_FILE_NAME));
		while (fileScan.hasNextLine())
		{
			String line = fileScan.nextLine();
			String[] items = line.split("\t");
			courseCatalog.findCourse(items[1]).addPrerequisite(courseCatalog.findCourse(items[0]));
		}
		return courseCatalog;
	}

	public static ScheduleOfClasses initializeScheduleOfClasses(String semester) throws FileNotFoundException, UninitializedCourseCatalogException
	{
		if(courseCatalog == null)
			throw new UninitializedCourseCatalogException("CourseCatalog must be initialized first");
		fileScan = new Scanner(new File(SCHEDULE_FILE_NAME+semester+".dat"));
		scheduleOfClasses = new ScheduleOfClasses(semester);
		while (fileScan.hasNextLine())
		{
			String line = fileScan.nextLine();
			String[] items = line.split("\t");
			Section newSection = new Section(courseCatalog.findCourse(items[0]));
			newSection.setSectionNo(items[1]);
			newSection.setDayOfWeek(items[2]);
			newSection.setTimeOfDay(items[3]);
			newSection.setRoom(items[4]);
			newSection.setSeatingCapacity(Integer.parseInt(items[5]));
			scheduleOfClasses.addSection(newSection);
		}
		fileScan.close();
		return scheduleOfClasses;
	}

	public static Faculty initializeFaculty() throws FileNotFoundException, UninitializedScheduleOfClassesException
	{ 	
		if(scheduleOfClasses == null)
			throw new UninitializedScheduleOfClassesException("ScheduleOfClasses must be initialized first");
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
		Faculty returnFaculty = new Faculty(profList);
		fileScan = new Scanner(new File(ASSIGNMENTS_FILE_NAME));
		while (fileScan.hasNextLine())
		{
			String line = fileScan.nextLine();
			String[] items = line.split("\t");
			returnFaculty.findProfessor(items[0]).agreeToTeach(scheduleOfClasses.findSection(items[1]));
			scheduleOfClasses.findSection(items[1]).setInstructor(returnFaculty.findProfessor(items[0]));
		}
		fileScan.close();
		return returnFaculty;
	}

	public static Student initializeStudent(String ssn) throws FileNotFoundException, UninitializedScheduleOfClassesException
	{
		if(scheduleOfClasses == null)
			throw new UninitializedScheduleOfClassesException();
		fileScan = new Scanner(new File("SRSDatFiles\\" + ssn + ".dat"));
		String line = fileScan.nextLine();
		String[] items = line.split("\t");
		Student newStudent = new Student(items[1],items[0],items[2],items[3]);
		while (fileScan.hasNextLine())
		{
			line = fileScan.nextLine();
			scheduleOfClasses.findSection(line).enroll(newStudent);
		}
		fileScan.close();
		return newStudent;
	}
}