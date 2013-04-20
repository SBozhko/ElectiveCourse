package by.bsuir.facultative.controller.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;

public class LogoutCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DAOFactoryException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
