import java.util.ArrayList;
/** 
 * This class models a course
 * @author Ashley
 */ 
public class Course
{
  private String courseName;
  private String courseNo;
  private double credits;
  private int sectionNumberer = 0;
  private ArrayList<Section> offeredAsSection;
  private ArrayList<Course> prerequisites;

  /**
   * Constructs a <code>Course</code> with the specified name and number.
   * @param courseName name of the <code>Course</code>
   * @param courseNo name of the <code>Course</code>
   */
  public Course(String courseName, String courseNo)
  {
  	this.courseName = courseName;
  	this.courseNo = courseNo;
  }

  /**
   * Sets the <code>Course</code>'s name to the specified <code>String</code>.
   * @param couseName name of the <code>Course</code>
   */
  public void setCourseName(String courseName)
  {
    this.courseName = courseName;
  }

  /**
   * Returns the <code>Course</code>'s name as a <code>String</code>.
   * @return the current name of the <code>Course</code>
   */
  public String getCourseName()
  {
    return courseName;
  }

  /**
   * Sets the <code>Course</code>'s number to the specified <code>String</code>.
   * @param number of the <code>Course</code>
   */
  public void setCourseNo(String courseNo)
  {
    this.courseNo = courseNo;
  }

  /**
   * Returns the <code>Course</code>'s number as a <code>String</code>.
   * @return the current number of the <code>Course</code>
   */
  public String getCourseNo()
  {
    return courseNo;
  }

  /**
   * Sets the <code>Course</code>'s credit value
   * @param credits number of credits the <code>Course</code> is worth
   */
  public void setCredits(double credits)
  {
    this.credits = credits;
  }

  /**
   * Returns the <code>Course</code>'s credit value
   * @return the credits number of credits the <code>Course</code> is currently worth
   */
  public double getCredits()
  {
    return credits;
  }

  /**
  * Displays the <code>Course</code> information
  */
  public void display()
  {
    System.out.println("Course details:");
    System.out.println("courseName:"+ this.getCourseName());
    System.out.println("courseNumber:"+this.getCourseNo());
    System.out.println("credits:"+this.getCredits());

  }
    
  /**
   * Returns a <code>String</code> containing the name, number, and credit value of the <code>Course</code>.
   * @return the name, number, and credit value of the <code>Course</code>
   */
  public String toString()
  {
    return "courseName:"+this.getCourseName()+"courseNumber:"+this.getCourseNo()+ "courseCredit:"+ this.getCredits();
  }

  /**
   * Adds the given <code>Course</code> to this <code>Course</code>'s list of prerequisites.
   * @param course the <code>Course</code> to be added
   */
  public void addPrerequisite(Course course)
  { 
    this.prerequisites.add(course);
  }

  /**
   * Tests whether this <code>Course</code> has any prerequisites.
   * @return <code>true</code> if the <code>Course</code> has one or more prerequisites
   */
  public Boolean hasPrerequisite()
  {
    if(this.prerequisites.isEmpty() == true)
      return false;
    else
      return true;
  }

  /**
   * Returns the list of prerequisites for this <code>Course</code>.
   * @return the list of prerequisites for this <code>Course</code>
   */
  public ArrayList<Course> getPrerequisites()
  {
    return this.prerequisites;
  }

  /**
   * Creates a new <code>Section</code>, assigns it the given day of the week, time of day, room number, and maximum capacity, and returns it.
   * @param  day      day of the week for the new <code>Section</code>
   * @param  time     time of the day for the new <code>Section</code>
   * @param  room     room number for the new <code>Section</code>
   * @param  capacity maximum number of students for the new <code>Section</code>
   * @return          the newly created <code>Section</code>
   */
  public Section scheduleSection(String day, String time, String room, int capacity)
  {
    Section returnSection = new Section(this);
    returnSection.setDayOfWeek(day);
    returnSection.setTimeOfDay(time);
    returnSection.setRoom(room);
    returnSection.setSeatingCapacity(capacity);
    returnSection.setSectionNo(""+sectionNumberer++);
    return returnSection;
  }

  /**
   * Adds the specified <code>Section</code> to the <code>Course</code>'s list of available <code>Sections</code>.
   * @param section the section to be added
   */
  public void addSection(Section section)
  {
    this.offeredAsSection.add(section);
  }

}
  

    
  
  