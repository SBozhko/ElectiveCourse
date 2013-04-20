/**
 * 
 */
package by.bsuir.facultative.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import by.bsuir.facultative.entity.Course;
import by.bsuir.facultative.entity.Student;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.exception.DAOException;

/**
 * @author SBozhko
 * 
 */
public interface UserDAO {
	public long insertUser(User newUser) throws DAOException;

	public boolean isExists(User newUser) throws DAOException;

	public boolean checkUserByEmail(User newUser) throws DAOException;

	public User selectUserByEmailAndPassword(User user) throws DAOException;

	public Course addCourse(Course newCourse, User user) throws DAOException;

	public List<Student> getStudentsOfCourse(Course course) throws DAOException;

	public void updateStudentMark(Student student) throws DAOException;

	public void updateCourse(Course course) throws DAOException;

	public List<Course> getCourses(User user) throws DAOException;

	public void deleteCourse(Course course) throws DAOException;

	public void refuseFromCourse(User user, Course course) throws DAOException;

	public Map<Course, Integer> getCoursesAndMarks(User user)
			throws DAOException;
}