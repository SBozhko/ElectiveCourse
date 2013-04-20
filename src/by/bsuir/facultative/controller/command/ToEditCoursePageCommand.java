package by.bsuir.facultative.controller.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsuir.facultative.entity.Course;
import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;

public class ToEditCoursePageCommand implements Command {
	
	private static final Logger logger = Logger.getLogger(ToEditCoursePageCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DAOFactoryException, DAOException {
		logger.info("in execute()");
		
		Course course = new Course();
		course.setId(Long.parseLong(request.getParameter("courseId")));
		course.setDescription(request.getParameter("courseDescription"));
		course.setHours(Integer.parseInt(request.getParameter("courseHours")));
		course.setTitle(request.getParameter("courseTitle"));		
		
		logger.debug(course);
		
		request.setAttribute("courseToEdit", course);
		
		return "/jsp/editCourse.jsp";
	}

}
