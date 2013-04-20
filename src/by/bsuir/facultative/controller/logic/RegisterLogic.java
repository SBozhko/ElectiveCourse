/**
 * 
 */
package by.bsuir.facultative.controller.logic;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.bsuir.facultative.dao.UserDAO;
import by.bsuir.facultative.dao.factory.DAOFactory;
import by.bsuir.facultative.dao.factory.DBTypeEnum;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;
import by.bsuir.facultative.util.ConfigurationManager;

/**
 * @author SBozhko
 * 
 */
public class RegisterLogic {
	private static final Logger logger = Logger.getLogger(RegisterLogic.class);

	public static boolean isUserExists(User newUser)
			throws DAOFactoryException, DAOException
	// throws DAOTechnicException, CannotTakeConnectionException, SQLException {
	{
		DAOFactory daoFactory = DAOFactory.getFactory(DBTypeEnum
				.valueOf(ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.DATABASE_TYPE)));
		UserDAO userDAO = daoFactory.getUserDAO(newUser);
		return userDAO.isExists(newUser);
	}

	public static void registerUser(User newUser) throws DAOFactoryException,
			DAOException {
		DAOFactory daoFactory = DAOFactory.getFactory(DBTypeEnum
				.valueOf(ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.DATABASE_TYPE)));
		UserDAO userDAO = daoFactory.getUserDAO(newUser);
		long idUser = userDAO.insertUser(newUser);
		logger.debug("idUser = " + idUser);
		// newUser = userDAO.find(idUser);
		// return newUser;

	}

}
