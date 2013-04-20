/**
 * 
 */
package by.bsuir.facultative.controller.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsuir.facultative.dao.UserDAO;
import by.bsuir.facultative.dao.factory.DAOFactory;
import by.bsuir.facultative.dao.factory.DBTypeEnum;
import by.bsuir.facultative.entity.Course;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;
import by.bsuir.facultative.util.ConfigurationManager;

/**
 * @author SBozhko
 *  
 */
public class GetCoursesCommand implements Command {
	private static final Logger logger = Logger.getLogger(GetCoursesCommand.class);

	/* (non-Javadoc)
	 * @see by.bsuir.facultative.controller.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DAOFactoryException, DAOException {
		
		logger.info("execute() invoked");
		User user = (User) request.getSession().getAttribute("user");
		logger.debug(user);
		DAOFactory daoFactory = DAOFactory.getFactory(DBTypeEnum
				.valueOf(ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.DATABASE_TYPE)));
		UserDAO userDAO = daoFactory.getUserDAO(user);

		List<Course> courses = userDAO.getCourses(user);

		
		//request.setAttribute("courseTitle", request.getParameter("courseTitle"));
		return new GetCoursesCommand().execute(request, response);
	}

}
