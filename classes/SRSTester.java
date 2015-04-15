import java.io.*;

public class SRSTester{
	
	private static Faculty faculty;
	private static CourseCatalog courseCatalog;
	private static ScheduleOfClasses scheduleOfClasses;
	private static SRSDataAccess srsDataAccess;

	public static void main(String[] args){
		srsDataAccess = new SRSDataAccess();
		try{
			courseCatalog = srsDataAccess.initializeCourseCatalog();
			scheduleOfClasses = srsDataAccess.initializeScheduleOfClasses("SP2005");
			faculty = srsDataAccess.initializeFaculty();
		}
		catch(FileNotFoundException e){System.out.println("FileNotFound");}
		catch(UninitializedCourseCatalogException e){System.out.println("CourseCatalog");}
		catch(UninitializedScheduleOfClassesException e){System.out.println("ScheduleOfClasses");}
		System.out.println("Here is the object faculty: \n"+faculty);
		System.out.println("Here is the object course catalog: \n"+courseCatalog);
		System.out.println("Here is the object schedule of classes: \n"+scheduleOfClasses);
	}
}