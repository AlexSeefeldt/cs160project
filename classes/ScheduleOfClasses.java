import java.util.HashMap;

/**
 * this class models the schedule of classes
 */
public class ScheduleOfClasses
{
	private String semester;
  private HashMap<String, Section> sectionsOffered;

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

  public Object[] getSectionsOffered()
  {
    return this.sectionsOffered.values().toArray();
  }

  public void display()
  {
    //display code here
  }

  public void addSection(Section section)
  {
    this.sectionsOffered.put(section.getFullSectionNo(),section);
  }

  public Section findSection(String fullSectionNo)
  {
    return this.sectionsOffered.get(fullSectionNo);
  }

  public boolean isEmpty()
  {
    return this.sectionsOffered.isEmpty();
  }

}
