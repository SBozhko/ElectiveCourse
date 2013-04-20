/**
 * 
 */
package by.bsuir.facultative.exception;

/**
 * @author SBozhko
 *  
 */
public class ConnectionPoolException extends TechnicException {

	public ConnectionPoolException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param msg
	 */
	public ConnectionPoolException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param exception
	 */
	public ConnectionPoolException(Throwable exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param msg
	 * @param exception
	 */
	public ConnectionPoolException(String msg, Throwable exception) {
		super(msg, exception);
		// TODO Auto-generated constructor stub
	}

}
