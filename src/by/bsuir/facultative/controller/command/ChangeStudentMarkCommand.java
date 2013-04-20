/**
 * 
 */
package by.bsuir.facultative.controller.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
public class ChangeStudentMarkCommand implements Command {

	private static final Logger logger = Logger
			.getLogger(ChangeStudentMarkCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DAOFactoryException, DAOException {
		logger.info("in execute()");
		Map<Course, Integer> marks = new HashMap<Course, Integer>();
		Course course = new Course();
		course.setId(Long.parseLong(request.getParameter("idCourse")));
		logger.info("course " + course);
		marks.put(course, Integer.parseInt(request.getParameter("newMark")));
		Student student = new Student();
		student.setIdStudent(Long.parseLong(request.getParameter("idStudent")));
		student.setMarks(marks);
		logger.info(student);

		DAOFactory daoFactory = DAOFactory.getFactory(DBTypeEnum
				.valueOf(ConfigurationManager.getInstance().getProperty(
						ConfigurationManager.DATABASE_TYPE)));
		User user = (User) request.getSession().getAttribute("user");
		UserDAO userDAO = daoFactory.getUserDAO(user);

		userDAO.updateStudentMark(student);
		logger.info("updated");

		request.setAttribute("courseTitle", request.getParameter("courseTitle"));
		return new ViewStudentsCommand().execute(request, response);
	}

}
