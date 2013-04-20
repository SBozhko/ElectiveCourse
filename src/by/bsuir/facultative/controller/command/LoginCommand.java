package by.bsuir.facultative.controller.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsuir.facultative.controller.logic.LoginLogic;
import by.bsuir.facultative.entity.Course;
import by.bsuir.facultative.entity.Student;
import by.bsuir.facultative.entity.Teacher;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.entity.UserType;
import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;

import by.bsuir.facultative.util.ConfigurationManager;
import by.bsuir.facultative.util.MessageManager;

public class LoginCommand implements Command {
	private static Logger logger = Logger.getLogger(LoginCommand.class);

	private static final String PARAM_NAME_EMAIL = "email";
	private static final String PARAM_NAME_PASSWORD = "password";

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DAOFactoryException, DAOException {

		logger.info("execute() was invoked");

		String page = null;
		// extraction email and password parameters from request
		String email = request.getParameter(PARAM_NAME_EMAIL);
		String password = request.getParameter(PARAM_NAME_PASSWORD);

		User user = new User() {
		};

		user.setEmail(email);
		user.setPassword(password);

		// проверка логина и пароля

		user = LoginLogic.checkEmail(user);
		if (user != null) {
			request.setAttribute("user", user);
			// определение пути к main.jsp
			for (UserType userType : UserType.values()) {
				switch (userType) {
				case STUDENT:
					if (user instanceof Student) {
						page = ConfigurationManager.getInstance().getProperty(
								ConfigurationManager.MAIN_PAGE_PATH);
					}
					break;
				case TEACHER:
					if (user instanceof Teacher) {
						page = "/jsp/teacherMain.jsp";
					}
					break;
				default:
					break;
				}
			}

		} else {
			request.setAttribute("errorMessage", MessageManager.getInstance()
					.getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE_PATH);
		}
		return page;
	}
}