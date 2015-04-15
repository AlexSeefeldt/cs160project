import java.io.*;

public class SRSTester{
	
	private static Faculty faculty;
	private static CourseCatalog courseCatalog;
	private static ScheduleOfClasses scheduleOfClasses;
	private static SRSDataAccess srsDataAccess;

	public static void main(String[] args){
		srsDataAccess = new SRSDataAccess();
		
		try{
			courseCatalog = initializeCourseCatalog();
			scheduleOfClasses = initializeScheduleOfClasses("SP2005");
			faculty = initializeFaculty();
		}
		catch(FileNotFoundException e){}
		catch(UninitializedCourseCatalogException e){}
		catch(UninitializedScheduleOfClasses e){}

		System.out.println("Here is the object faculty: \n"+faculty);
		System.out.println("Here is the object course catalog: \n"+courseCatalog);
		System.out.println("Here is the object schedule of classes: \n"+scheduleOfClasses);
	}
}