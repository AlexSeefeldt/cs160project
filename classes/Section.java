import java.util.HashMap;

/**
 * This class models a section of a course.
 * @author Tyler Meyer
 */
public class Section
{
  private Course representedCourse;
  private String sectionNo;
  private String dayOfWeek;
  private String timeOfDay;
  private String room;
  private int seatingCapacity;
  private ScheduleOfClasses offeredIn;
  private Professor instructor;
  private HashMap<String,Student> enrolledStudents;
  private HashMap<Student,TranscriptEntry> assignedGrades;
  
  
  /**
   * Constructs a new <code>Section</code> that represents the given <code>Course</code>.
   * @param  representedCourse <code>Course</code> that the new <code>Section</code> represents
   * @return                   the new Section
   */
  public Section(Course representedCourse)
  {
      
      this.representedCourse = representedCourse;
  }
    
    /**
     * Returns the <code>Section</code>'s number as a <code>String</code>.
     * @return the current number of the <code>Section</code>
     */
    public String getSectionNo()
    {
      return this.sectionNo;
    }
    
    /**
     * Returns the day of the week on which the <code>Section</code> occurs as a <code>String</code>.
     * @return the day of the week on which the <code>Section</code> currently occurs
     */
    public String getDayOfWeek()
    {
      return this.dayOfWeek;
    }
    
    /**
     * Returns the time of day at which the <code>Section</code> occurs as a <code>String</code>.
     * @return the time of day at which the <code>Section</code> currently occurs
     */
    public String getTimeOfDay()
    {
      return this.timeOfDay;
    }
    
    /**
     * Returns the <code>Professor</code> that teaches this <code>Section</code>.
     * @return the <code>Professor</code> that teaches this <code>Section</code>
     */
    public Professor getInstructor()
    {
      return this.instructor;
    }
    
    /**
     * Returns the room in which the <code>Section</code> occurs as a <code>String</code>.
     * @return the room in which the <code>Section</code> currently occurs
     */
    public String getRoom()
    {
      return this.room;
    }
    
    /**
     * Returns the <code>Section</code>'s seating capacity as an <code>int</code>.
     * @return the current seating capacity of the <code>Section</code>
     */
    public int getSeatingCapacity()
    {
      return this.seatingCapacity;
    }
    
    /**
     * Returns the <code>ScheduleOfClasses</code> in which this <code>Section</code> is offered.
     * @return the <code>ScheduleOfClasses</code> in which this <code>Section</code> is offered
     */
    public ScheduleOfClasses getOfferedIn()
    {
      return this.offeredIn;
    }
    
    /**
     * Returns the full number of this <code>Section</code>, i.e., its course number concatenated with its <code>Section</code> number.
     * @return the full ID number of this <code>Section</code>
     */
    public String getFullSectionNo()
    {
      return this.representedCourse.getCourseNo()+"-"+this.sectionNo;
    }
    
    /**
     * Returns the <code>Course</code> that this <code>Section</code> represents.
     * @return the <code>Course</code> that this <code>Section</code> represents
     */
    public Course getRepresentedCourse()
    {
      return this.representedCourse;
    }
    
    /**
     * Returns the current <code>grade</code> of the specified <code>Student</code>.
     * @param  student the <code>Student</code> whose grade is to be returned
     * @return         the <code>Student</code>'s grade
     */
    public String getGrade(Student student)
    {
      return this.assignedGrades.get(student).getGrade();
    }
    
    /**
     * Returns the number of <code>Student</code> enrolled in this <code>Section</code> as an <code>int</code>.
     * @return the total number of <code>Student</code> enrolled in this <code>Section</code>
     */
    public int getTotalEnrollment()
    {
      return this.enrolledStudents.size();
    }
    
    /**
     * Sets the <code>Section</code>'s number to the specified <code>String</code>.
     * @param number of the <code>Section</code>
     */
    public void setSectionNo(String sectionNo)
    {
      this.sectionNo = sectionNo;
    }
    
    /**
     * Sets the day of the week on which the <code>Section</code> occurs to the specified <code>String</code>.
     * @param day of the week on which the <code>Section</code> occurs
     */
    public void setDayOfWeek(String dayOfWeek)
    {
      this.dayOfWeek = dayOfWeek;
    }
    
    /**
     * Sets the time of day at which the <code>Section</code> occurs to the specified <code>String</code>.
     * @param time of day at which the <code>Section</code> occurs
     */
    public void setTimeOfDay(String timeOfDay)
    {
      this.timeOfDay = timeOfDay;
    }
    
    /**
     * Sets the <code>Professor</code> that teaches this <code>Section</code>.
     * @param the <code>Professor</code> that teaches this <code>Section</code>
     */
    public void setInstructor(Professor instructor)
    {
      this.instructor = instructor;
    }
    
    /**
     * Sets the <code>Course</code> that this <code>Section</code> represents.
     * @param the <code>Course</code> that this <code>Section</code> represents
     */
    public void setRepresentedCourse(Course representedCourse)
    {
      this.representedCourse = representedCourse;
    }
    
    /**
     * Sets the room in which the <code>Section</code> occurs.
     * @param the room in which the <code>Section</code> occurs
     */
    public void setRoom(String room)
    {
      this.room = room;
    }
    
    /**
     * Sets the seating capacity of the <code>Section</code>.
     * @param the seating capacity of the <code>Section</code>
     */
    public void setSeatingCapacity(int seatingCapacity)
    {
      this.seatingCapacity = seatingCapacity;
    }
    
    /**
     * Sets the <code>ScheduleOfClasses</code> in which this <code>Section</code> is offered.
     * @param the <code>ScheduleOfClasses</code> in which this <code>Section</code> is offered
     */
    public void setOfferedIn(ScheduleOfClasses offeredIn)
    {
      this.offeredIn = offeredIn;
    }
    
    /**
     * Returns a <code>String</code> containing the number, day, time, room, 
     * capacity, course, instructor, and schedule of classes of the <code>Section</code>.
     * @return the number, day, time, room, capacity, course, instructor, and schedule of classes of the <code>Section</code>
     */
    public String toString()
    {
      return "Section number: "+sectionNo+" Day of week: "+dayOfWeek+" Time of day: "+timeOfDay+" Room number: "+room+" Seating Capacity: "+
        seatingCapacity+" Represented course"+representedCourse+" Instructor: "+instructor+"Offered in:"+offeredIn;
    }
    
    /**
     * Tests whether this <code>Section</code> contains any empty seats.
     * @return <code>true</code> if this <code>Section</code> contains any empty seats
     */
    public boolean confirmSeatAvailability()
    {
      if(this.seatingCapacity == 0)
        return false;
      else
        return true;
    }
    
    /**
     * Tests whether the <code>Student</code> has fulfilled the prerequisites for this <code>Section</code>.
     * @param  student the <code>Student</code> to be checked
     * @return         true if the <code>Student</code> has fulfilled the prerequisites
     */
    private boolean checkPrereqs(Student student)
    {
      for (Course c : this.representedCourse.getPrerequisites())
      {
        if(!student.getTranscript().verifyCompletion(c))
          return false;
      }
      return true;
    }
    
    /**
     * Attempts to enroll the <code>Student</code> in the <code>Section</code>. Returns an <code>EnrollmentStatus</code>
     * telling whether the enrollment was sucessful.
     * @param  student the <code>Student</code> to be enrolled
     * @return         an <code>EnrollmentStatus</code> telling whether the enrollment was sucessful
     */
    public EnrollmentStatus enroll(Student student){
      if(this.seatingCapacity == 0)
        return EnrollmentStatus.secFull;
      else if (checkPrereqs(student))
        return EnrollmentStatus.prereq;
      else if (this.enrolledStudents.containsValue(student)) 
        return EnrollmentStatus.prevEnroll;
      else
        return EnrollmentStatus.success;   
    }
    
    /**
     * Displays the class.
     */
    public void display()
    {
      //stuff goes here later
    }
    
    /**
     * Displays the student roster.
     */
    public void displayStudentRoster()
    {
      //stuff
    }
    
    /**
     * Creates a new <code>TranscriptEntry</code> for the specified <code>Student</code> with the specified grade.
     * @param student the <code>Student</code> whose grade is to be posted
     * @param grade   the grade to be posted
     */
    public void postGrade(Student student, String grade)
    {
      TranscriptEntry tE = new TranscriptEntry();
      tE.setStudent(student);
      tE.setSection(this);
      tE.setTranscript(student.getTranscript());
      tE.setGrade(grade);
      this.assignedGrades.put(student, tE);
    }
    
    /**
     * Tests whether the specified <code>Course</code> is represented by this <code>Section</code>.
     * @param  course the <code>Course</code> to be tested
     * @return        true if the specified <code>Course</code> is represented by this <code>Section</code>
     */
    public boolean isSectionOf(Course course)
    {
      if(this.representedCourse == course)
        return true;
      else
        return false;
    }
    
    
  }