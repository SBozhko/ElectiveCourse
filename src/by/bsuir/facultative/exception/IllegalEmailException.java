/**
 * 
 */
package by.bsuir.facultative.exception;

/**
 * @author SBozhko
 *  
 */
public class IllegalEmailException extends BLException{
	public IllegalEmailException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param msg
	 */
	public IllegalEmailException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param exception
	 */
	public IllegalEmailException(Throwable exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param msg
	 * @param exception
	 */
	public IllegalEmailException(String msg, Throwable exception) {
		super(msg, exception);
		// TODO Auto-generated constructor stub
	}
}
