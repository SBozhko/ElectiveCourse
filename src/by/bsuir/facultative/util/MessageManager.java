package by.bsuir.facultative.util;

import java.util.ResourceBundle;

public class MessageManager {
	private ResourceBundle resourceBundle;
	// class gets info from message. properties
	
	private static final String BUNDLE_NAME = "properties/messages";
	public static final String LOGIN_ERROR_MESSAGE = "login.error.message";
	public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "servlet.exception.error.message";
	public static final String IO_EXCEPTION_ERROR_MESSAGE = "io.exception.error.message";
	public static final String BUSINESS_LOGIC_ERROR_MESSAGE = "business.logic.error.message";

	/**
	 * Private constructor prevents instantiation from other classes
	 */
	private MessageManager() {
		resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
	}

	/**
	 * MessageManagerHolder is loaded on the first execution of
	 * MessageManager.getInstance() or the first access to MessageManagerHolder.INSTANCE,
	 * not before.
	 */
	private static class MessageManagerHolder {
		private static final MessageManager INSTANCE = new MessageManager();
	}

	public static MessageManager getInstance() {
		return MessageManagerHolder.INSTANCE;
	}

	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
