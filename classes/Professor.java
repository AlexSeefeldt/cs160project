import java.util.ArrayList;

/**
 * This class models a professor.
 * @author Alex Seefeldt
 */
public class Professor extends Person
{
	private String title;
	private String department;
	private ArrayList<Section> teaches = new ArrayList<Section>();

	/**
	 * Constructs a <code>Professor</code> with specified name, SSN, title, and department.
	 * @param  name       name of the <code>Professor</code>
	 * @param  ssn        Social Security Number of the <code>Professor</code>
	 * @param  title      title of the <code>Professor</code>
	 * @param  department department of the <code>Professor</code>
	 */
	public Professor(String name, String ssn, String title, String department)
	{
		super(name, ssn);
		setTitle(title);
		setDepartment(department);
	}

	/**
	 * Sets the <code>Professor</code>'s title to the specified <code>String</code>.
	 * @param title title of the <code>Professor</code>
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Sets the <code>Professor</code>'s department to the specified <code>String</code>.
	 * @param department department of the <code>Professor</code>
	 */
	public void setDepartment(String department)
	{
		this.department = department;
	}

	/**
	 * Returns the <code>Professor</code>'s title as a <code>String</code>.
	 * @return the current title of the <code>Professor</code>
	 */
	public String getTitle()
	{
		return this.title;
	}

	/**
	 * Returns the <code>Professor</code>'s department as a <code>String</code>.
	 * @return the current department of the <code>Professor</code>
	 */
	public String getDepartment()
	{
		return this.department;
	}

	/**
	 * Displays the <code>Professor</code>.
	 */
	public void display()
	{
		super.display();
		System.out.println("Professor's detailed information:");
		System.out.println("\tProfessor's department:"+ getDepartment());
		System.out.println("\tTitle:"+ getTitle());
		displayTeachingAssignments();
	}

	/**
	 * Returns a <code>String</code> containing the title, name, and department of the <code>Professor</code>.
	 * @return the title, name, and department of the <code>Professor</code>
	 */
	public String toString()
	{
		return "Title: "+this.title+" Name: "+this.getName()+" Department: "+this.department;
	}

	/**
	 * Displays the <code>Professor</code>'s teaching assignments.
	 */
	public void displayTeachingAssignments()
	{
		System.out.println("Teaching Assignments by " + getName() + ":" );
	}

	/**
	 * Adds the given <code>Section</code> to the list of <code>Section</code>s the <code>Professor</code> teaches.
	 * @param section the new <code>Section</code> to be added to the <code>Professor</code>'s list of <code>Section</code>s
	 */
	public void agreeToTeach(Section section)
	{
		teaches.add(section);
	}
}