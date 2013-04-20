/**
 * 
 */
package by.bsuir.facultative.exception;

/**
 * @author SBozhko
 *  
 */
@SuppressWarnings("serial")
public class ProjectException extends Exception{
	/**
	 * 
	 */
	public ProjectException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param msg
	 */
	public ProjectException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param exception
	 */
	public ProjectException(Throwable exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param msg
	 * @param exception
	 */
	public ProjectException(String msg, Throwable exception) {
		super(msg, exception);
		// TODO Auto-generated constructor stub
	}
}
