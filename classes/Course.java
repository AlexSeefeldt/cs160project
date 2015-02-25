public class Course
{
  private String courseName;
  private String courseNo;
  private double credits;
  
  public Course(String courseName, String courseNo)
  {
  	this.courseName = courseName;
  	this.courseNo = courseNo;
  }
  
  public void setCourseName(String courseName)
  {
    this.courseName = courseName;
  }

  public String getCourseName()
  {
    return courseName;
  }

  public void setCourseNo(String courseNo)
  {
    this.courseNo = courseNo;
  }
  
  public String getCourseNo()
  {
    return courseNo;
  }

  public void setCredits(double c)
  {
    this.credits = c;
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
  

    
  
  