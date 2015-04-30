import java.util.TreeMap;
import java.util.ArrayList;

/**
 * this class models the schedule of classes
 * @author Ashley
 */
public class ScheduleOfClasses
{
	private String semester;
  private TreeMap<String, Section> sectionsOffered = new TreeMap<String, Section>();

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
  public ArrayList<Section> getSectionsOffered()
  {
    return new ArrayList<Section>(this.sectionsOffered.values());
  }



  /**
   * Displays the class
   */
  public void display()
  {
    System.out.println("Schedule of Classes for " + getSemester());
    for (Section s : sectionsOffered.values())
    {
      s.display();
    }
    System.out.println("End Schedule of Classes");
  }


  /**
   * Adds the section to the schedule
   * @param section the <code>section</code> to be added
   */
  public void addSection(Section section)
  {
    String key= section.getRepresentedCourse().getCourseNo() + " - "+ section.getSectionNo();
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
