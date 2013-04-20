/**
 * 
 */
package by.bsuir.facultative.exception;

/**
 * @author SBozhko
 *  
 */
public class BLException extends ProjectException {
	 
	public BLException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param msg
	 */
	public BLException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param exception
	 */
	public BLException(Throwable exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param msg
	 * @param exception
	 */
	public BLException(String msg, Throwable exception) {
		super(msg, exception);
		// TODO Auto-generated constructor stub
	}
}
