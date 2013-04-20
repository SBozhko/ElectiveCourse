/**
 * 
 */
package by.bsuir.facultative.dao.factory;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.bsuir.facultative.dao.StudentDAO;
import by.bsuir.facultative.dao.TeacherDAO;
import by.bsuir.facultative.dao.UserDAO;
import by.bsuir.facultative.entity.Student;
import by.bsuir.facultative.entity.Teacher;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;

/**
 * @author SBozhko
 * 
 */
public abstract class DAOFactory {
	private static Logger logger = Logger.getLogger(DAOFactory.class);

	/**
	 * 
	 */
	public DAOFactory() {

	}

	public abstract StudentDAO getStudentDAO() throws DAOFactoryException;
	public abstract TeacherDAO getTeacherDAO() throws DAOFactoryException;
	public static DAOFactory getFactory(DBTypeEnum dbType) throws DAOFactoryException {
		switch (dbType) {
		case MYSQL:
			return MysqlDAOFactory.getInstance();
		default:
			logger.error("There are no such type ofDB");
			throw new DAOFactoryException("There are no such type of DB");
		}
	}

	public abstract UserDAO getUserDAO(User newUser) throws DAOFactoryException, DAOException;

}
