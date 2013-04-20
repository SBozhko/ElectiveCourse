package by.bsuir.facultative.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsuir.facultative.controller.command.Command;
import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;
import by.bsuir.facultative.util.ConfigurationManager;
import by.bsuir.facultative.util.MessageManager;
import by.bsuir.facultative.util.RequestHelper;

@SuppressWarnings("serial")
public class Controller extends HttpServlet implements javax.servlet.Servlet {

	private static Logger logger = Logger.getLogger(Controller.class);

	// object contains list of possible commands
	private RequestHelper requestHelper = RequestHelper.getInstance();

	/**
	 * 
	 */
	public Controller() {
		super();
		logger.info("Controller() was invoked");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws DAOException
	 * @throws DAOFactoryException
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.info("processRequest() starts");
		logger.debug(Controller.class + "  " + " loaded ");
		String page = null;
		try {
			// detect command from JSP
			Command command = requestHelper.getCommand(request);
			/*
			 * execute() invocation
			 */
			// TODO
			logger.info(command + " was invoked");
			page = command.execute(request, response);
			// method returns jsp
		} catch (ServletException e) {
			logger.error(e);
			// generate error message
			request.setAttribute(
					"errorMessage",
					MessageManager.getInstance().getProperty(
							MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
			// jsp with error message
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE_PATH);
		} catch (IOException e) {
			logger.error(e);
			request.setAttribute("errorMessage", MessageManager.getInstance()
					.getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));

			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE_PATH);
		} catch (DAOFactoryException e) {
			logger.error(e);
			request.setAttribute("errorMessage", MessageManager.getInstance()
					.getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));

			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE_PATH);
		} catch (DAOException e) {
			logger.error(e);
			request.setAttribute("errorMessage", MessageManager.getInstance()
					.getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));

			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ERROR_PAGE_PATH);
		}

		// catch (BLException e) {
		// TODO Auto-generated catch block
		// logger.error(e);
		// request.setAttribute("errorMessage", MessageManager.getInstance()
		// .getProperty(MessageManager.BUSINESS_LOGIC_ERROR_MESSAGE));
		// page = ConfigurationManager.getInstance().getProperty(
		// ConfigurationManager.ERROR_PAGE_PATH);
		// }
		// invoke jsp with answer
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
