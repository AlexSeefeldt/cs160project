import java.util.HashMap;

/**
 * this class models the schedule of classes
 * @author Ashley
 */
public class ScheduleOfClasses
{
	private String semester;
  private HashMap<String, Section> sectionsOffered = new HashMap<String, Section>();

  /**
   * The constructor initializes the string variable
   * @param semester
   * @return the new <code>ScheduleOfCLasses</code>
   */
	public ScheduleOfClasses(String semester)
	{
       this.semester = semester;
	}

  /**
   * Sets the semester 
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


  /**
   * Gets <code>SectionsOffered</code> in course
   * @return <code>sectionOffered</code> value as an array
   */
  public Object[] getSectionsOffered()
  {
    return this.sectionsOffered.values().toArray();
  }



  /**
   * Displays the class
   */
  public void display()
  {
    System.out.println("Schedule of Classes for" + getSemester());



  }


  /**
   * Adds the section to the schedule
   * @param section the <code>section</code> to be added
   */
  public void addSection(Section section)
  {
    String key= section.getRepresentedCourse().getCourseNo() + " "+ section.getSectionNo();
    sectionsOffered.put(key,section);
    section.setOfferedIn(this);
  }


  /**
   * Finds <code>section</code> with the specified full section number
   * @param  fullSectionNo the full section number of the desired <code>section</code>
   * @return               section with its corresponding number
   */
  public Section findSection(String fullSectionNo)
  {
    return this.sectionsOffered.get(fullSectionNo);
  }


  /**
   * Tests if this <code>ScheduleOfClasses</code> is empty
   * @return true if this <code>ScheduleOfClasses</code> is empty
   */
  public boolean isEmpty()
  {
    return this.sectionsOffered.isEmpty();
  }

}
