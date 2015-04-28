import java.util.ArrayList;
/**
 * This class models a student.
 * @author Alex Seefeldt
 */
public class Student extends Person
{
	private String major;
	private String degree;
	private Transcript transcript;
	private ArrayList<Section> attends;

	/**
	 * Constructs a <code>Student</code> with specified name, SSN, major, and degree.
	 * @param  name       name of the <code>Student</code>
	 * @param  ssn        Social Security Number of the <code>Student</code>
	 * @param  major      major of the <code>Student</code>
	 * @param  degree     degree of the <code>Student</code>
	 */
	public Student(String name, String ssn, String major, String degree)
	{
		super(name, ssn);
		this.major = major;
		this.degree = degree;
		//might need to change this Transcript constructor
		this.transcript = new Transcript(this);
		//is this a proper ArrayList constructor?
		this.attends = new ArrayList<Section>();
	}

	/**
	 * Sets the <code>Student</code>'s major to the specified <code>String</code>.
	 * @param major major of the <code>Student</code>
	 */
	public void setMajor(String major)
	{
		this.major = major;
	}

	/**
	 * Sets the <code>Student</code>'s degree to the specified <code>String</code>.
	 * @param degree degree of the <code>Student</code>
	 */
	public void setDegree(String degree)
	{
		this.degree = degree;
	}

	/**
	 * Sets the <code>Student</code>'s <code>Transcript</code> to the specified <code>Transcript</code>.
	 * @param transcript <code>Transcript</code> of the <code>Student</code>
	 */
	public void setTranscript(Transcript transcript)
	{
		this.transcript = transcript;
	}

	/**
	 * Returns the <code>Student</code>'s major as a <code>String</code>.
	 * @return the current major of the <code>Student</code>
	 */
	public String getMajor()
	{
		return this.major;
	}

	/**
	 * Returns the <code>Student</code>'s degree as a <code>String</code>.
	 * @return the current degree of the <code>Student</code>
	 */
	public String getDegree()
	{
		return this.degree;
	}

	/**
	 * Returns the <code>Student</code>'s <code>Transcript</code>.
	 * @return the current <code>Transcript</code> of the <code>Student</code>
	 */
	public Transcript getTranscript()
	{
		return this.transcript;
	}

	/**
	 * Returns an <code>ArrayList</code> of the <code>Section</code>s the <code>Student</code> is currently enrolled in.
	 * @return an <code>ArrayList</code> of the <code>Section</code>s the <code>Student</code> is currently enrolled in
	 */
	public ArrayList<Section> getEnrolledSections()
	{
		return this.attends;
	}

	/**
	 * Displays the <code>Student</code>.
	 */
	public void display()
	{
		super.display();
		System.out.println("Student:");
		System.out.println("\tDegree:" + getDegree());
		System.out.println("\tMajor:"+ getMajor());
		System.out.println("Course Schedule:");
		displayCourseSchedule();
		System.out.println("End Course Schedule");
		printTranscript();
		System.out.println("End Student");
		System.out.println("End Person");
	}

	/**
	 * Returns a <code>String</code> containing the name, major, and degree of the <code>Student</code>.
	 * @return the name, major, and degree of the <code>Student</code>
	 */
	public String toString()
	{
		return "Name: "+this.getName()+" Major: "+this.major+" Degree: "+this.degree;
	}

	/**
	 * Displays the <code>Student</code>'s course schedule.
	 */
	public void displayCourseSchedule()
	{
		if (!attends.isEmpty())
		{
			for (Section s : attends)
			{
				System.out.println(s.getFullSectionNo());
			}
		}
		else
			System.out.println("Doesn't attend anything");
	}

	/**
	 * Adds the given <code>Section</code> to the list of <code>Section</code>s the <code>Student</code> attends.
	 * @param section the new <code>Section</code> to be added to the <code>Student</code>'s list of <code>Section</code>s
	 */
	public void addSection(Section section)
	{
		attends.add(section);
	}

	/**
	 * Removes the given <code>Section</code> from the list of <code>Section</code>s the <code>Student</code> attends.
	 * @param section the new <code>Section</code> to be dropped from the <code>Student</code>'s list of <code>Section</code>s
	 */
	public void dropSection(Section section)
	{
		attends.remove(section);
	}

	/**
	 * Tests whether the <code>Student</code> is currently enrolled in the given <code>Section</code>.
	 * @param  section the <code>Section</code> to be tested
	 * @return         <code>true</code> if the <code>Student</code> is enrolled in the <code>Section</code>
	 */
	public Boolean isEnrolledIn(Section section)
	{
		return attends.contains(section);
	}

	/**
	 * Tests whether the <code>Student</code> is currently enrolled in a <code>Section</code> of the same course as the given <code>Section</code>.
	 * @param  section the <code>Section</code> to be tested
	 * @return         <code>true</code> if the <code>Student</code> is currently enrolled in a similar <code>Section</code>
	 */
	public Boolean isCurrentlyEnrolledInSimilar(Section section)
	{
		for (Section enrolledSection : attends)
		{
			if (enrolledSection.getRepresentedCourse().equals(section.getRepresentedCourse()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Prints the <code>Student</code>'s current <code>Transcript</code>.
	 */
	public void printTranscript()
	{
			transcript.display();
	}
}