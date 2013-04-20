package by.bsuir.facultative.controller.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsuir.facultative.dao.UserDAO;
import by.bsuir.facultative.dao.factory.DAOFactory;
import by.bsuir.facultative.dao.factory.DBTypeEnum;
import by.bsuir.facultative.entity.Course;
import by.bsuir.facultative.entity.Teacher;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;
import by.bsuir.facultative.util.ConfigurationManager;

public class AddCourseCommand implements Command {

	private static final Logger logger = Logger
			.getLogger(AddCourseCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DAOFactoryException, DAOException {
		logger.info("in execute()");
		Course newCourse = new Course();

		newCourse.setTitle(request.getParameter("title"));
		newCourse.setHours(Integer.parseInt(request.getParameter("hours")));
		newCourse.setDescription(request.getParameter("description"));

		logger.info("newCourse" + newCourse);
		
		User user = (User)request.getSession().getAttribute("user");
		
		logger.info("user" + user);

		DAOFactory daoFactory = DAOFactory.getFactory(DBTypeEnum
				.valueOf(ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.DATABASE_TYPE)));
		UserDAO userDAO = daoFactory.getUserDAO(user);
		
		newCourse = userDAO.addCourse(newCourse, user);
		logger.info(newCourse);

		((Teacher)user).getCourses().add(newCourse);
		logger.info(user);		
		
		return "/jsp/teacherMain.jsp";
	}

}
