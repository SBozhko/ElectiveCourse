package by.bsuir.facultative.controller.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsuir.facultative.dao.StudentDAO;
import by.bsuir.facultative.dao.factory.DAOFactory;
import by.bsuir.facultative.dao.factory.DBTypeEnum;
import by.bsuir.facultative.entity.Course;
import by.bsuir.facultative.entity.Student;
import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;
import by.bsuir.facultative.util.ConfigurationManager;

/**
 * 
 * @author SBozhko
 * 
 */
public class EnterForCourseCommand implements Command {
	private static final Logger logger = Logger
			.getLogger(EnterForCourseCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DAOFactoryException, DAOException {
		Student student = (Student) request.getSession()
				.getAttribute("user");
		logger.debug("Student student = (Student)request.getSession().getAttribute("
				+ student);
		Set<Course> courses = (Set<Course>) request.getSession().getAttribute(
				"availableCourses");
		for (Course c : courses) {
			logger.debug(c);
		}

		long idCourse = Long.parseLong(request.getParameter("idCourse"));
		long idStudent = ((Student) request.getSession()
				.getAttribute("user")).getIdStudent();

		logger.debug("long idCourse = " + idCourse);
		logger.debug("long idStudent = " + idStudent);

		DAOFactory daoFactory = DAOFactory.getFactory(DBTypeEnum
				.valueOf(ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.DATABASE_TYPE)));
		StudentDAO studentDAO = daoFactory.getStudentDAO();
		Course course = studentDAO.getCourse(idCourse);
		logger.debug("Course course = studentDAO.getCourse(idCourse);" + course);
		studentDAO.enterForCourse(student, course);
		courses.remove(course);
		request.setAttribute("courses", courses);
		return "/jsp/availableCourses.jsp";
	}
}
