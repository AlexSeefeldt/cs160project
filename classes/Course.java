public class Course
{
  private String courseName;
  private String courseNo;
  private double credits;
 
  
  
  public void setCourseNo(String cno)
  {
    this.courseNo= cno;
  }
  
  public String getCourseNo()
  {
    return courseNo;
  }
  
  public void setCourseName(String name)
  {
    this.courseName= name;
    
  }
  public String getCourseName()
  {
    return courseName;
  }
  public void setCredits(double c)
  {
    this.credits= c;
  }
  public double getCredits()
  {
    return credits;
  }
  public String toString()
  {
    return courseNo;
  }
}
  

    
  
  