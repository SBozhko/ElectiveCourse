/**
 * 
 */
package by.bsuir.facultative.controller.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsuir.facultative.dao.StudentDAO;
import by.bsuir.facultative.dao.UserDAO;
import by.bsuir.facultative.dao.factory.DAOFactory;
import by.bsuir.facultative.dao.factory.DBTypeEnum;
import by.bsuir.facultative.entity.Course;
import by.bsuir.facultative.entity.Student;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;
import by.bsuir.facultative.util.ConfigurationManager;

/**
 * @author SBozhko
 *  
 */
public class ViewStudentsCommand implements Command{
	
	private static final Logger logger = Logger.getLogger(ViewStudentsCommand.class);

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
		
		Course course = new Course();
		course.setId(Long.parseLong(request.getParameter("idCourse")));
		course.setTitle(request.getParameter("courseTitle"));		
		logger.info("course from request" + course);
		
		List<Student> students = userDAO.getStudentsOfCourse(course);
	
		request.setAttribute("students", students);
		request.setAttribute("courseTitle", request.getParameter("courseTitle"));
		 return "/jsp/listOfStudents.jsp";
	}

}
