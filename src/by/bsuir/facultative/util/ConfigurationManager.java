package by.bsuir.facultative.util;

import java.util.ResourceBundle;

public class ConfigurationManager {
	
	private ResourceBundle resourceBundle;

	// класс извлекает информацию из файла config.properties
	private static final String BUNDLE_NAME = "properties/config";

	public static final String DATABASE_DRIVER_NAME = "database.driver.name";
	public static final String DATABASE_WHOLE_URL = "database.whole.url";
	public static final String DATABASE_ADMIN_NAME = "database.admin.name";
	public static final String DATABASE_ADMIN_PASSWORD = "database.admin.password";
	public static final String DATABASE_POOl_SIZE = "database.pool.size";
	public static final String DATABASE_TYPE = "database.type";
	
	
	
	
	public static final String ERROR_PAGE_PATH = "error.page.path";
	public static final String LOGIN_PAGE_PATH = "login.page.path";
	public static final String MAIN_PAGE_PATH = "main.page.path";
	public static final String INDEX_PAGE_PATH = "index.page.path";

	

	private ConfigurationManager(){
		resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
	}
	
	private static class ConfigurationManagerHolder{
		private static final ConfigurationManager INSTANCE = new ConfigurationManager();
	}
	
	public static ConfigurationManager getInstance() {
			return ConfigurationManagerHolder.INSTANCE;
	}

	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}