/**
 * 
 */
package by.bsuir.facultative.controller.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.facultative.exception.DAOException;
import by.bsuir.facultative.exception.DAOFactoryException;


/**
 * @author SBozhko
 *  
 */
public class EditPersonalInfoCommand implements Command {

	/* (non-Javadoc)
	 * @see by.bsuir.facultative.controller.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			DAOFactoryException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
