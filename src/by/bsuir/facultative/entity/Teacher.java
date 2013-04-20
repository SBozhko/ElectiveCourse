/**
 * 
 */
package by.bsuir.facultative.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SBozhko
 * 
 */
public class Teacher extends User {

	private long idTeacher;
	private List<Course> courses = new ArrayList<Course>();

	/**
	 * @return the idTeacher
	 */
	public long getIdTeacher() {
		return idTeacher;
	}

	/**
	 * @param idTeacher
	 *            the idTeacher to set
	 */
	public void setIdTeacher(long idTeacher) {
		this.idTeacher = idTeacher;
	}

	/**
	 * 
	 */
	public Teacher() {
		super();
	}

	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses
	 *            the courses to set
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	/**
	 * @param idTeacher
	 * @param courses
	 */
	public Teacher(long idTeacher, List<Course> courses) {
		this.idTeacher = idTeacher;
		this.courses = courses;
	}

}
