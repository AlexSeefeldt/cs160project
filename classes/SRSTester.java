import java.io.*;

public class SRSTester{
	
	private static Faculty faculty;
	private static CourseCatalog courseCatalog ;
	private static ScheduleOfClasses scheduleOfClasses;
	private static Student student1, student2, student3;
	private static SRSDataAccess srsDataAccess;

	public static void main(String[] args){
		srsDataAccess = new SRSDataAccess();
		try{
			courseCatalog = srsDataAccess.initializeCourseCatalog();
			scheduleOfClasses = srsDataAccess.initializeScheduleOfClasses("SP2005");
			faculty = srsDataAccess.initializeFaculty();
			student1 = srsDataAccess.initializeStudent("201-30-4565");
			student2 = srsDataAccess.initializeStudent("894-66-8153");
			student3 = srsDataAccess.initializeStudent("995-64-7152");
		}
		catch(FileNotFoundException e){System.out.println("FileNotFound"); e.printStackTrace();}
		catch(UninitializedCourseCatalogException e){System.out.println("CourseCatalog not yet initialized");}
		catch(UninitializedScheduleOfClassesException e){System.out.println("ScheduleOfClasses not yet initialized");}
		
		System.out.println("\nHere is the Faculty: ");
		faculty.display();
		System.out.println("\nHere is the Course Catalog: ");
		courseCatalog.display();    
		System.out.println("\nHere is the Schedule of Classes: ");
		scheduleOfClasses.display();
		System.out.println("STUDENTS");
		student1.display();
		student2.display();
		student3.display();
	}
}