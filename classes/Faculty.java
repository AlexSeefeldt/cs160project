/**
 * This class is a collection of <code>Professor</code>s.
 * @author Alex Seefeldt
 */
import java.util.*;

public class Faculty
{
	private HashMap<String, Professor> professors;

	/**
	 * Constructs a <code>Faculty</code> from an <code>ArrayList</code> of <code>Professor</code>s.
	 * @param  pList an <code>ArrayList</code> containing the <code>Professor</code>s to be added.
	 */
	public Faculty(ArrayList<Professor> pList)
	{
		this.professors = new HashMap<String, Professor>();
		for (Professor p : pList)
		{
			this.addProfessor(p);
		}
	}

	/**
	 * Displays the <code>Faculty</code>.
	 */
	public void display()
	{
		//display code
	}

	/**
	 * Adds the given <code>Professor</code> to the <code>Faculty</code>.
	 * @param p the <code>Professor</code> to be added
	 */
	public void addProfessor(Professor p)
	{
		professors.put(p.getSsn(), p);
	}

	/**
	 * Returns the <code>Professor</code> with the given Social Security Number, or <code>null</code> if the <code>Faculty</code> does not contain the requested Social Security Number.
	 * @param  ssn Social Security Number of the requested <code>Professor</code>
	 * @return     the <code>Professor</code> with the given Social Security Number, or <code>null</code>
	 */
	public Professor findProfessor(String ssn)
	{
		return professors.get(ssn);
	}

	/**
	 * Tests whether the <code>Faculty</code> contains any <code>Professor</code>s.
	 * @return <code>true</code> if the <code>Faculty</code> contains no <code>Professor</code>s.
	 */
	public boolean isEmpty()
	{
		return professors.isEmpty();
	}
}