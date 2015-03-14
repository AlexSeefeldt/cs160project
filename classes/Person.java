/**
 * This interface models a person with a name and a Social Security Number.
 * @author Alex Seefeldt
 */
public abstract class Person
{
	private String name;
	private String ssn;
	
	/**
	 * Constructs a <code>Person</code> with specified name and SSN.
	 * @param name name of the <code>Person</code>
	 * @param ssn the <code>Person</code>'s Social Security Number
	 */
	public Person(String name, String ssn)
	{
		this.name = name;
		this.ssn = ssn;
	}

	/**
	 * Sets the <code>Person</code>'s name to the specified <code>String</code>.
	 * @param name name of the <code>Person</code>
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Sets the <code>Person</code>'s Social Security Number to the specified <code>String</code>.
	 * @param ssn the <code>Person</code>'s Social Security Number
	 */
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	/**
	 * Returns the <code>Person</code>'s name as a <code>String</code>.
	 * @return the current name of the <code>Person</code>
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Returns the <code>Person</code>'s Social Security Number as a <code>String</code>.
	 * @return the current Social Security Number of the <code>Person</code>
	 */
	public String getSsn()
	{
		return this.ssn;
	}

	/**
	 * Displays the <code>Person</code>.
	 */
	public void display()
	{
		//what go here
	}

	public abstract String toString();
}