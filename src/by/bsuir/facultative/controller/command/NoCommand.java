package by.bsuir.facultative.controller.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.facultative.util.ConfigurationManager;

public class NoCommand implements Command {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * in case of straight controller's call redirect to login page
		 */
		String page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.LOGIN_PAGE_PATH);
		return page;
	}
}
