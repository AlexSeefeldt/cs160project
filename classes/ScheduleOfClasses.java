
/**
 * this class models the schedule of classes
 */
public class ScheduleOfClasses
{
	private String semester;
  
  /**
   * The constructor initializes the string variable
   * @param semester
   */
	public ScheduleOfClasses(String semester)
	{
       this.semester = semester;
	}

   /**
    * sets the semester 
    * @param semester
    */
	public void setSemester(String semester)
	{
		this.semester= semester;
  }
  
  /**
   * gets the specific semester
   * @return semester
   */
  public String getSemester()
  {
  	return this.semester;
  }

}
