/**
 * 
 */
package by.bsuir.facultative.exception;

/**
 * @author SBozhko
 *  
 */
public class IllegalMarkException extends BLException{
	public IllegalMarkException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param msg
	 */
	public IllegalMarkException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param exception
	 */
	public IllegalMarkException(Throwable exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param msg
	 * @param exception
	 */
	public IllegalMarkException(String msg, Throwable exception) {
		super(msg, exception);
		// TODO Auto-generated constructor stub
	}
}
