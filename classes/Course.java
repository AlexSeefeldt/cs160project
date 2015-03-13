import java.util.ArrayList;
/** 
 * This class models a course
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
   * constructor initialize course with given name 
   * @param courseName the name of the course
   * @param courseNo the name of the course
   */
  public Course(String courseName, String courseNo)
  {
  	this.courseName = courseName;
  	this.courseNo = courseNo;
  }
  /**
   * set the name of the course
   * @param couseName the name of the course
   */
  public void setCourseName(String courseName)
  {
    this.courseName = courseName;
  }
  /**
   * returns the name of the course
   * @return the name of the course
   */
  public String getCourseName()
  {
    return courseName;
  }
  /**
   *set the number of the course
   * @param the number of the course
   */
  public void setCourseNo(String courseNo)
  {
    this.courseNo = courseNo;
  }
  /**
   * gets the number of the course
   * @return the number of the course
   */
  public String getCourseNo()
  {
    return courseNo;
  }
  /**
   *set the credit of the course
   * @param credits the course credit
   */
  public void setCredits(double credits)
  {
    this.credits = credits;
  }
  /**
   * get the course credit
   * @return credits the credit of the course
   */
  public double getCredits()
  {
    return credits;
  }

  /**
  * display's the course information
  */
  public void display()
  {
    System.out.println("Course details:");
    System.out.println("courseName:"+ this.getCourseName());
    System.out.println("courseNumber:"+this.getCourseNo());
    System.out.println("credits:"+this.getCredits());

  }
    
  /**
  * return the string representation of the course
  * @return the courseName, courseNumber and courseCredit
  */
  public String toString()
  {
    return "courseName:"+this.getCourseName()+"courseNumber:"+this.getCourseNo()+ "courseCredit:"+ this.getCredits();
  }

  public void addPrerequisite(Course course)
  { 
    this.prerequisites.add(course);
  }

  public Boolean hasPrerequisite()
  {
    if(this.prerequisites.isEmpty() == true)
      return false;
    else
      return true;
  }

  public ArrayList<Course> getPrerequisites()
  {
    return this.prerequisites;
  }

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

  public void addSection(Section section)
  {
    this.offeredAsSection.add(section);
  }

}
  

    
  
  