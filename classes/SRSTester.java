import java.acm.*;
import java.io.*;

public class SRSTester extends ConsoleProgram{
	
	private Faculty faculty;
	private CourseCatolog courseCatalog;
	private ScheduleOfClasses scheduleOfClasses;
	private SRSDataAccess srsDataAccess;

	public void run(){
		srsDataAccess = new SRSDataAccess();
		faculty = initializeFaculty();
		courseCatalog = initializeCourseCatalog();
		scheduleOfClasses = initializeScheduleOfClasses("SP2005");

		try{
			
		}
		catch(FileNotFoundException e){

		}

		println("Here is the object faculty: \n"+faculty);
		println("Here is the object course catalog: \n"+courseCatalog);
		println("Here is the object schedule of classes: \n"+scheduleOfClasses);

	}
}