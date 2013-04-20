/**
 * 
 */
package by.bsuir.facultative.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import by.bsuir.facultative.entity.Course;
import by.bsuir.facultative.entity.Student;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.exception.DAOException;

/**
 * @author SBozhko
 * 
 */
public interface StudentDAO extends UserDAO {

	Set<Course> getAllCourses() throws DAOException;

	Course getCourse(long idCourse) throws DAOException;

	void enterForCourse(Student student, Course course) throws DAOException;

	boolean checkUserByEmail(User newUser) throws DAOException;

}
