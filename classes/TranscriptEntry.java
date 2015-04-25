  /**
   * This class models the inputs of <code>TranscriptEntry</code>
   * @author Colton Peterson
   */
public class TranscriptEntry
{
  private String grade;
  private Student student;
  private Section section;
  private Transcript transcript;

  /**
   * Constructs a new <code>TranscriptEntry</code>
   * @return new TranscriptEntry
   */
  public TranscriptEntry(Student student, String grade, Section sect)
  {
    setStudent(student);
    setGrade(grade);
    setSection(section);
    Transcript tran= student.getTranscript();
    setTranscript(tran);
    
  }
 

  /**
   * Sets the <code>TranscriptEntry</code> to a specified <code>Student</code>
   * @param student the owner of the <code>TranscriptEntry</code>
   */
  public void setStudent(Student student)
  {
    this.student = student;
  }


  /**
   * Sets the <code>TranscriptEntry</code> to a specified <code>section</code>
   * @param section the specified <code>section</code> in <code>TranscriptEntry</code>
   */
  public void setSection(Section section)
  {
    this.section = section;
  }
 

  /**
   * Sets a specified <code>grade</code> in <code>TranscriptEntry</code>
   * @param grade the specified <code>grade</code> in <code>TranscriptEntry</code>
   */
  public void setGrade(String grade)
  {
    this.grade = grade;
  }
  

  /**
   * Sets the <code>TranscriptEntry</code> to be in a specified <code>transcript</code>
   * @param transcript the specified <code>transcript</code> that the <code>TranscriptEntry</code> belongs to
   */
  public void setTranscript(Transcript transcript)
  {
    this.transcript = transcript;
  }
  

  /**
   * Gets the student of <code>TranscriptEntry</code>
   * @return the current student of <code>TranscriptEntry</code>
   */
  public Student getStudent()
  {
    return this.student;
  }
 

  /**
   * Gets the section of <code>TranscriptEntry</code> 
   * @return the current section of <code>TranscriptEntry</code>
   */
  public Section getSection()
  {
    return this.section;
  }
  

  /**
   * Gets the <code>grade</code> of <code>TranscriptEntry</code>
   * @return the current grade of <code>TransciptEntry</code>
   */
  public String getGrade()
  {
    return this.grade;
  } 
  

  /**
   * Gets the <code>transcript</code> that <code>TranscriptEntry</code> is entering in
   * @return the current <code>transcript</code> being used to enter <code>TranscriptEntry</code>s
   */
  public Transcript getTranscript()
  {
    return this.transcript;
  }
  

  /**
   * Tests if <code>grade</code> is valid
   * @param  grade current <code>grade</code> being used in <code>TranscriptEntry</code>
   * @return  true if <code>grade</code> is valid
   */
  public Boolean validateGrade(String grade)
  {
    if(grade == null) //Do not understand what to compare grade to.
    {
      return true;
    }
    return false;
  }


  /**
   * Tests if the current <code>grade</code>
   * @param  grade the current <code>grade</code> of student 
   * @return true if <code>grade</code> is above an "F"
   */
  public Boolean passingGrade(String grade)
  {
    if(grade == "A" || grade == "B" || grade == "C" || grade == "D" )
    {
      return true;
    }
    return false;
  }
}