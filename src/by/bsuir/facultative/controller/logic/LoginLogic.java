package by.bsuir.facultative.controller.logic;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.bsuir.facultative.dao.UserDAO;
import by.bsuir.facultative.dao.factory.DAOFactory;
import by.bsuir.facultative.dao.factory.DBTypeEnum;
import by.bsuir.facultative.dao.factory.MysqlDAOFactory;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;
import by.bsuir.facultative.util.ConfigurationManager;

public class LoginLogic {
	private static final Logger logger = Logger.getLogger(LoginLogic.class);

	public static User checkEmail(User user) throws DAOException, DAOFactoryException {
		User loginedUser = null;

		DAOFactory daoFactory;
		daoFactory = DAOFactory.getFactory(DBTypeEnum
				.valueOf(ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.DATABASE_TYPE)));

		UserDAO userDAO = daoFactory.getUserDAO(user);

		logger.info("UserDAO was created " + userDAO);

		loginedUser = userDAO.selectUserByEmailAndPassword(user);

		logger.debug(loginedUser);
		return loginedUser;

	}
}
