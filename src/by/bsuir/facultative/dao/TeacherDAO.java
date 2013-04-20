/**
 * 
 */
package by.bsuir.facultative.dao;

import java.sql.SQLException;

import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.exception.DAOException;

/**
 * @author SBozhko
 * 
 */
public interface TeacherDAO extends UserDAO {

	boolean checkUserByEmail(User newUser) throws DAOException;

}
