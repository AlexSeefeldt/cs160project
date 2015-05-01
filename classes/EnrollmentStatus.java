/**
 * This <code>Enum</code> gives the possible situations that can happen when
 * a <code>Student</code> attempts to enroll in a <code>Section</code>.
 * @author Rick Fischer
 */
public enum EnrollmentStatus
{
	SUCCESS ("Enrollment successful!"), 
	SECFULL ("Enrollment failed; section was full."), 
	PREREQ ("Enrollment failed; prerequisites not satisfied."), 
	PREVENROLL ("Enrollment failed; previously enrolled.");
	
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