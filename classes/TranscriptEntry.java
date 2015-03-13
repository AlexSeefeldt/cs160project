public class TranscriptEntry
{
  private String grade;
  private Student student;
  private Section section;
  private Transcript transcript;
 
  public TranscriptEntry(Student student, Section section, Transcript transcript)
  {
    this.student = student;
    this.section = section;
    this.transcript = transcript;
  }
 
  public void setStudent(Student student)
  {
    this.student = student;
  }
 
  public void setSection(Section section)
  {
    this.section = section;
  }
 
  public void setGrade(String grade)
  {
    this.grade = grade;
  }
  
  public void setTranscript(Transcript transcript)
  {
    this.transcript = transcript;
  }
  
  public Student getStudent()
  {
    return this.student;
  }
 
  public Section getSection()
  {
    return this.section;
  }
  
  public String getGrade()
  {
    return this.grade;
  } 
  
  public Transcript getTranscript()
  {
    return this.transcript;
  }
  
  public Boolean validateGrade(String grade)
  {
    if(grade == null) //Do not understand what to compare grade to.
    {
      return true;
    }
    return false;
  }
  
  public Boolean passingGrade(String grade)
  {
    if(grade == "A" || grade == "B" || grade == "C" || grade == "D" )
    {
      return true;
    }
    return false;
  }
}