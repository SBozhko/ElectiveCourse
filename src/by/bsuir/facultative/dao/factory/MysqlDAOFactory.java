/**
 * 
 */
package by.bsuir.facultative.dao.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.bsuir.facultative.dao.StudentDAO;
import by.bsuir.facultative.dao.TeacherDAO;
import by.bsuir.facultative.dao.UserDAO;
import by.bsuir.facultative.dao.mysql.MysqlStudentDAO;
import by.bsuir.facultative.dao.mysql.MysqlTeacherDAO;
import by.bsuir.facultative.dao.pool.ConnectionPool;
import by.bsuir.facultative.entity.Course;
import by.bsuir.facultative.entity.Student;
import by.bsuir.facultative.entity.Teacher;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.entity.UserType;
import by.bsuir.facultative.exception.ConnectionPoolException;
import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;

/**
 * @author SBozhko
 * 
 */
public class MysqlDAOFactory extends DAOFactory {
	private static final Logger logger = Logger
			.getLogger(MysqlDAOFactory.class);

	private static ConnectionPool pool = null;
	private static MysqlDAOFactory instance = null;

	private static Connection createConnection() throws DAOFactoryException {
		Connection conection = null;
		try {
			conection = pool.takeConnection();
		}catch (ConnectionPoolException e) {
			logger.error(e);
			 throw new DAOFactoryException(e);
		} finally {
			logger.debug("finally bloc in createConnection()");
		}
		return conection;
	}

	/**
	 * @throws DAOFactoryException 
	 * 
	 */
	private MysqlDAOFactory() throws DAOFactoryException {
		try {
			pool = ConnectionPool.getInstance();
			logger.info("MysqlDAOFactory() was invoked");
		} catch (ConnectionPoolException e) {
			logger.error(e);
			throw new DAOFactoryException(e);
		}
	}

	public synchronized static MysqlDAOFactory getInstance() throws DAOFactoryException {
		if (instance == null) {
			instance = new MysqlDAOFactory();
			return instance;
		}
		return instance;
	}

	@Override
	public StudentDAO getStudentDAO() throws DAOFactoryException {
		/*
		 * try {
		 * 
		 * } catch (ClassNotFoundException e) { logger.error(e); // throw new
		 * DAOTechnicException(e); // } catch (ConnectionPoolException e) { //
		 * logger.error(e); // throw new CannotTakeConnectionException(e); }
		 * catch (SQLException e) { logger.error(e); // throw new
		 * DAOTechnicException(e); } logger.info("trying to createConnection");
		 * 
		 * logger.info("connection created");
		 * 
		 * if (connection == null) { logger.info("connection = null"); // throw
		 * new CannotTakeConnectionException(); }
		 * logger.info("return new MysqlStudentDAO");
		 */
		Connection connection = createConnection();
		return new MysqlStudentDAO(connection);
	}

	@Override
	public TeacherDAO getTeacherDAO() throws DAOFactoryException {
		/*
		 * try { init();
		 * 
		 * } catch (ClassNotFoundException e) { logger.error(e); // throw new
		 * DAOTechnicException(e); // } catch (ConnectionPoolException e) { //
		 * logger.error(e); // throw new CannotTakeConnectionException(e); }
		 * catch (SQLException e) { logger.error(e); // throw new
		 * DAOTechnicException(e); } logger.info("trying to createConnection");
		 * Connection connection = createConnection();
		 * logger.info("connection created"); if (connection == null) {
		 * logger.info("connection = null"); // throw new
		 * CannotTakeConnectionException(); }
		 * logger.info("return new MysqlTeacherDAO");
		 */
		Connection connection = createConnection();
		return new MysqlTeacherDAO(connection);
	}

	// TODO refactor that!!!
	@Override
	public UserDAO getUserDAO(User newUser) throws DAOFactoryException, DAOException {
		logger.debug("getUserDAO()");
		for (UserType userType : UserType.values()) {
			logger.debug("getUserDAO() in loop 1");
			switch (userType) {
			case STUDENT:
				if (newUser instanceof Student) {
					return getStudentDAO();
				}
				break;
			case TEACHER:
				if (newUser instanceof Teacher) {
					return getTeacherDAO();
				}
				break;
			default:
				break;
			}
		}

		logger.debug("getUserDAO() loop 2");

		for (UserType userType : UserType.values()) {
			logger.debug("getUserDAO() in loop 2");
			switch (userType) {
			case STUDENT:
				logger.debug("getUserDAO() student");
				if (getStudentDAO().checkUserByEmail(newUser)) {
					logger.debug("getStudentDAO().checkUserByEmail(newUser)");
					return getStudentDAO();
				}

				break;
			case TEACHER:
				logger.debug("getUserDAO() teacher");
				if (getTeacherDAO().checkUserByEmail(newUser)) {
					return getTeacherDAO();
				}
				break;
			default:
				break;
			}
		}
		// TODO
		// NoSuchUserException;
		return null;
	}

}
