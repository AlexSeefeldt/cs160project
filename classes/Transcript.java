import java.util.ArrayList;

/**
 * This class models a transcript
 * @author Alex Seefeldt
 */
public class Transcript
{
	private ArrayList<TranscriptEntry> transcriptEntries;
	private Student studentOwner;

	/**
	 * Constructs a <code>Transcript</code> with the given <code>Student</code> as owner and an empty list of <code>TranscriptEntry</code>s.
	 * @param  studentOwner the <code>Student</code> to whom teh <code>Transcript</code> belongs
	 */
	public Transcript(Student studentOwner)
	{
		this.transcriptEntries = new ArrayList<TranscriptEntry>();
		this.studentOwner = studentOwner;
	}

	/**
	 * Sets the <code>Transcript</code>'s owner to the specified <code>Student</code>.
	 * @param studentOwner owner of the <code>Transcript</code>
	 */
	public void setStudentOwner(Student studentOwner)
	{
		this.studentOwner = studentOwner;
	}

	/**
	 * Returns the <code>Transcript</code>'s owner.
	 * @return the current owner of the <code>Transcript</code>
	 */
	public Student getStudentOwner()
	{
		return this.studentOwner;
	}

	/**
	 * Tests whether the <code>Transcript</code> contains a <code>Section</code> of the specified <code>Course</code>.
	 * @param  c the <code>Course</code> to be tested for.
	 * @return   <code>true</code> if the <code>Transcript</code> contains a <code>Section</code> of the specified <code>Course</code>
	 */
	public boolean verifyCompletion(Course c)
	{
		for (TranscriptEntry entry : transcriptEntries)
		{
			if(entry.getSection().getRepresentedCourse().equals(c));
			{
				return true;
			}
		}
		return false;
	}
}