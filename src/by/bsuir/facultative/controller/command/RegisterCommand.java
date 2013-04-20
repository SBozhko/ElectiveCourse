/**
 * 
 */
package by.bsuir.facultative.controller.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsuir.facultative.controller.logic.RegisterLogic;
import by.bsuir.facultative.entity.Student;
import by.bsuir.facultative.entity.Teacher;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.entity.UserType;
import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;

/**
 * @author SBozhko
 * 
 */
public class RegisterCommand implements Command {
	private static Logger logger = Logger.getLogger(RegisterCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DAOFactoryException, DAOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String role = request.getParameter("role");

		User newUser = null;

		switch (UserType.valueOf(role.toUpperCase())) {
		case STUDENT:
			/*
			 * User student = new Student(); student.setEmail(email);
			 * student.setPassword(password); student.setFullName(fullName);
			 */
			newUser = new Student();
			break;
		case TEACHER:
			newUser = new Teacher();
			break;
		}

		newUser.setEmail(email);
		newUser.setFullName(fullName);
		newUser.setPassword(password);

		if (RegisterLogic.isUserExists(newUser)) {
			return "/jsp/emailExists.jsp";
		}

		RegisterLogic.registerUser(newUser);

		request.setAttribute("user", newUser);

		request.setAttribute("role", role);
		request.setAttribute("fullName", fullName);

		logger.debug(fullName);
		logger.debug(role);

		if (newUser instanceof Student) {
			return "/jsp/main.jsp";
		} else {
			return "/jsp/teacherMain.jsp";
		}
	}

}
