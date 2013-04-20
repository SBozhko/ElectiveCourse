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
 * Command - View Available Courses for particular student
 * 
 * @author SBozhko
 * 
 */
public class ViewAvailableCoursesCommand implements Command {
	private static final Logger logger = Logger
			.getLogger(ViewAvailableCoursesCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DAOFactoryException, DAOException {
		User student = (User) request.getSession().getAttribute("user");
		logger.debug(student);
		DAOFactory daoFactory = DAOFactory.getFactory(DBTypeEnum
				.valueOf(ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.DATABASE_TYPE)));
		StudentDAO studentDAO = daoFactory.getStudentDAO();
		Set<Course> courses = studentDAO.getAllCourses();
		for (Course c : courses) {
			logger.debug(c);
		}
		
		courses.removeAll( ((Student) student).getMarks().keySet());
		
		//for (Course course : ((Student) student).getMarks().keySet()) {
//			courses.remove(course);
	//	}
		// courses.removeAll(student.getMarks().keySet());
		logger.debug("*********************************************");
		logger.debug(courses);
		request.setAttribute("courses", courses);
		 return "/jsp/availableCourses.jsp";
		//return "/index.jsp";
	}

}
