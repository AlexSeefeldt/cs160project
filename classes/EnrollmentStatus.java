/**
 * This <code>Enum</code> gives the possible situations that can happen when
 * a <code>Student</code> attempts to enroll in a <code>Section</code>.
 * @author Rick Fischer
 */
public enum EnrollmentStatus
{
	success ( "Enrollment successful! :o)"), 
	secFull ( " Enrollment failed; section was full. :op"), 
	prereq ( " Enrollment failed; prerequisites not satisfied. :op"), 
	prevEnroll ( "Enrollment failed; previously enrolled. :op");
	
	private final String value;

	EnrollmentStatus(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}
}