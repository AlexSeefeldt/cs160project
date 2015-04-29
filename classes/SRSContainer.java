import java.io.FileNotFoundException;

public class SRSContainer{
	
	private static Faculty faculty;
	private static CourseCatalog courseCatalog ;
	private static ScheduleOfClasses scheduleOfClasses;
	private static SRSDataAccess srsDataAccess;
	private static MainFrame mainFrame;

	public SRSContainer(MainFrame mainFrame)
	{
		srsDataAccess = new SRSDataAccess();
		this.mainFrame = mainFrame;
		try{
		courseCatalog = srsDataAccess.initializeCourseCatalog();
		scheduleOfClasses = srsDataAccess.initializeScheduleOfClasses("SP2005");
		faculty = srsDataAccess.initializeFaculty();
		}
		catch(FileNotFoundException e){System.out.println("FileNotFound"); e.printStackTrace();}
		catch(UninitializedCourseCatalogException e){System.out.println("CourseCatalog not yet initialized");}
		catch(UninitializedScheduleOfClassesException e){System.out.println("ScheduleOfClasses not yet initialized");}
	}

	public Faculty getFaculty()
	{
		return this.faculty;
	}

	public CourseCatalog getCourseCatalog()
	{
		return this.courseCatalog;
	}

	public ScheduleOfClasses getScheduleOfClasses()
	{
		return this.scheduleOfClasses;
	}

	public SRSDataAccess getSRSDataAccess()
	{
		return this.srsDataAccess;
	}

	public MainFrame getMainFrame()
	{
		return this.mainFrame;
	}
}