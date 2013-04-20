package by.bsuir.facultative.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.bsuir.facultative.util.ConfigurationManager;

public class ChangeLocaleCommand implements Command {
	private static Logger logger = Logger.getLogger(ChangeLocaleCommand.class);

	private static final String PARAM_LANGUAGE = "lang";

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws  ServletException,
			IOException {

		String page = null;

		String locale = request.getParameter(PARAM_LANGUAGE);

		HttpSession session = request.getSession(true);
		logger.info(locale + " - locale from request");
		if ("English".equals(locale)) {
			session.setAttribute("localeName", "en");
			logger.info(request.getSession().getAttribute("localeName"));
		} else {
			session.setAttribute("localeName", "ru");
			System.out.println(request.getSession().getAttribute("localeName"));
		}

		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.INDEX_PAGE_PATH);

		return page;
	}

}
