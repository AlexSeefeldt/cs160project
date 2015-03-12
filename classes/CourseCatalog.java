import java.util.*;

public class CourseCatalog
{
	private HashMap<String, Course> courses;

	/**
	 * Constructs a <code>CourseCatalog</code> from an <code>ArrayList</code> of <code>Course</code>s.
	 * @param  cList an <code>ArrayList</code> containing the <code>Course</code>s to be added.
	 */
	public CourseCatalog(ArrayList<Course> cList)
	{
		this.courses = new HashMap<String, Course>();
		for (Course c : cList)
		{
			this.addCourse(c);
		}
	}

	/**
	 * Displays the <code>CourseCatalog</code>.
	 */
	public void display()
	{
		//display code
	}

	/**
	 * Adds the given <code>Course</code> to the <code>CourseCatalog</code>.
	 * @param c the <code>Course</code> to be added
	 */
	public void addCourse(Course c)
	{
		courses.put(c.getCourseNo(), c);
	}

	/**
	 * Returns the <code>Course</code> with the given course number, or <code>null</code> if the <code>CourseCatalog</code> does not contain the requested course number.
	 * @param  courseNo Course number of the requested <code>Course</code>
	 * @return          the <code>Course</code> with the given course number, or <code>null</code>
	 */
	public Course findCourse(String courseNo)
	{
		return courses.get(courseNo);
	}

	/**
	 * Tests whether the <code>CourseCatalog</code> contains any <code>Course</code>s.
	 * @return <code>true</code> if the <code>CourseCatalog</code> contains no <code>Course</code>s.
	 */
	public boolean isEmpty()
	{
		return courses.isEmpty();
	}
}