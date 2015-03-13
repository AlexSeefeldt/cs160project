import java.util.HashMap;

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

	public Section(Course representedCourse){
		this.representedCourse = representedCourse;
	}

	public String getSectionNo()
	{
		return this.sectionNo;
	}

	public String getDayOfWeek()
	{
		return this.dayOfWeek;
	}

	public String getTimeOfDay()
	{
		return this.timeOfDay;
	}

	public Professor getInstructor()
	{
		return this.instructor;
	}

	public String getRoom()
	{
		return this.room;
	}

	public int getSeatingCapacity()
	{
		return this.seatingCapacity;
	}

	public ScheduleOfClasses getOfferedIn()
	{
		return this.offeredIn;
	}

	public String getFullSectionNo()
	{
		return this.representedCourse.getCourseNo()+"-"+this.sectionNo;
	}

	public Course getRepresentedCourse()
	{
		return this.representedCourse;
	}

	public String getGrade(Student student)
	{
		return this.assignedGrades.get(student).getGrade();
	}

	public int getTotalEnrollment()
	{
		return this.enrolledStudents.size();
	}

	public void setSectionNo(String sectionNo)
	{
		this.sectionNo = sectionNo;
	}

	public void setDayOfWeek(String dayOfWeek)
	{
		this.dayOfWeek = dayOfWeek;
	}

	public void setTimeOfDay(String timeOfDay)
	{
		this.timeOfDay = timeOfDay;
	}

	public void setInstructor(Professor instructor)
	{
		this.instructor = instructor;
	}

	public void setRepresentedCourse(Course representedCourse)
	{
		this.representedCourse = representedCourse;
	}

	public void setRoom(String room)
	{
		this.room = room;
	}

	public void setSeatingCapacity(int seatingCapacity)
	{
		this.seatingCapacity = seatingCapacity;
	}

	public void setOfferedIn(ScheduleOfClasses offeredIn)
	{
		this.offeredIn = offeredIn;
	}

	public String toString(){
		return "Section number: "+sectionNo+" Day of week: "+dayOfWeek+" Time of day: "+timeOfDay+" Room number: "+room+" Seating Capacity: "+seatingCapacity+" Represented course"+representedCourse+" Instructor: "+instructor+"Offered in:"+offeredIn;
	}

	/**
	 * created new variable "availability" that is set to a 
	 * boolean value as seen below.
	 * @return [description]
	 */
	public boolean confirmSeatAvailability(){
		if(this.seatingCapacity == 0){
			return false;
		}
		else{
			return true;
		}
	}

	public static boolean confirmSeatAvailability(int seatingCapacity){
		if(seatingCapacity == 0){
			return false;
		}
		else{
			return true;
		}
	}

	private boolean checkPrereqs(Student student)
	{
		for (Course c : this.representedCourse.getPrerequisites())
		{
			if(!student.getTranscript().verifyCompletion(c))
				return false;
		}
		return true;
	}

	public EnrollmentStatus enroll(Student student){
		if(!confirmSeatAvailability(this.seatingCapacity))
			return EnrollmentStatus.secFull;
		else if (checkPrereqs(student))
			return EnrollmentStatus.prereq;
		else if (this.enrolledStudents.containsValue(student)) 
			return EnrollmentStatus.prevEnroll;
		else
			return EnrollmentStatus.success;			
	}

	public void display(){
		//stuff goes here later
	}

	public void displayStudentRoster(){
		//stuff
	}

	public void postGrade(Student student, String grade)
	{
		TranscriptEntry tE = new TranscriptEntry();
		tE.setStudent(student);
		tE.setSection(this);
		tE.setTranscript(student.getTranscript());
		tE.setGrade(grade);
		this.assignedGrades.put(student, tE);
	}

	public boolean isSectionOf(Course course){
		if(this.representedCourse == course)
			return true;
		else
			return false;
	}


}