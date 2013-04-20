/**
 * 
 */
package by.bsuir.facultative.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SBozhko
 * 
 */
public class Student extends User {

	private long idStudent;
	private Map<Course, Integer> marks = new HashMap<Course, Integer>();

	/**
	 * 
	 */
	public Student() {
		super();
	}

	/**
	 * @return the idStudent
	 */
	public long getIdStudent() {
		return idStudent;
	}

	/**
	 * @param idStudent
	 *            the idStudent to set
	 */
	public void setIdStudent(long idStudent) {
		this.idStudent = idStudent;
	}

	/**
	 * @return the marks
	 */
	public Map<Course, Integer> getMarks() {
		return marks;
	}

	/**
	 * @param marks
	 *            the marks to set
	 */
	public void setMarks(Map<Course, Integer> marks) {
		this.marks = marks;
	}

	/**
	 * @param idStudent
	 * @param marks
	 */
	public Student(long idStudent, HashMap<Course, Integer> marks) {
		this.idStudent = idStudent;
		this.marks = marks;
		// marks.
	}

}
