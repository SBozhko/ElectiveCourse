/**
 * 
 */
package by.bsuir.facultative.exception;

/**
 * @author SBozhko
 *  
 */
public class TechnicException extends ProjectException{
	public TechnicException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param msg
	 */
	public TechnicException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param exception
	 */
	public TechnicException(Throwable exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param msg
	 * @param exception
	 */
	public TechnicException(String msg, Throwable exception) {
		super(msg, exception);
		// TODO Auto-generated constructor stub
	}
}
