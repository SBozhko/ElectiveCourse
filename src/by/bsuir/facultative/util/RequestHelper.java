package by.bsuir.facultative.util;

/**
 * Это ленивый (объект Singleton не инициализируется до моменты вызова метод getInstance()) 
 * и потоко-безопасный Singleton.
 * Поскольку в классе Singleton нет статических полей которые нужно инициализировать, 
 * класс беспрепятственно загрузится. То есть Вам не нужно ждать, пока мы создадим объект Singleton в 
 * самом начале, когда загружаются классы. 
 * Смотрим дальше, когда объект INSTANCE будет создан? 
 * Тогда, когда мы вызовем метод getInstance(), что повлечет загрузку внутреннего
 *  класса SingletonHolder, что спровоцирует создание объекта INSTANCE. 
 *  Поскольку фаза инициализации класса гарантировано (спецификацией) "не конкурента", 
 *  то у нас нет необходимости использовать synchronized и volatile.
 */
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import by.bsuir.facultative.controller.command.AddCourseCommand;
import by.bsuir.facultative.controller.command.ChangeLocaleCommand;
import by.bsuir.facultative.controller.command.ChangeStudentMarkCommand;
import by.bsuir.facultative.controller.command.Command;
import by.bsuir.facultative.controller.command.DeleteCourseCommand;
import by.bsuir.facultative.controller.command.EditCourseCommand;
import by.bsuir.facultative.controller.command.EditPersonalInfoCommand;
import by.bsuir.facultative.controller.command.EnterForCourseCommand;
import by.bsuir.facultative.controller.command.GetCoursesCommand;
import by.bsuir.facultative.controller.command.LoginCommand;
import by.bsuir.facultative.controller.command.LogoutCommand;
import by.bsuir.facultative.controller.command.NoCommand;
import by.bsuir.facultative.controller.command.RefuseFromCourseCommand;
import by.bsuir.facultative.controller.command.RegisterCommand;
import by.bsuir.facultative.controller.command.ToEditCoursePageCommand;
import by.bsuir.facultative.controller.command.ToTeacherMainPageCommand;
import by.bsuir.facultative.controller.command.ViewAvailableCoursesCommand;
import by.bsuir.facultative.controller.command.ViewStudentsCommand;

/**
 * // TODO ДОРАБАТЫВАТЬ!!! - Нужен enum для различных типов команд login,
 * logout...
 * 
 * @author SBozhko
 * 
 */
public class RequestHelper {

	private HashMap<String, Command> commands;

	/**
	 * Private constructor prevents instantiation from other classes
	 */
	private RequestHelper() {
		// table filling with commands
		commands = new HashMap<String, Command>();
		commands.put("login", new LoginCommand());
		commands.put("locale", new ChangeLocaleCommand());
		commands.put("register", new RegisterCommand());
		commands.put("viewCourses", new ViewAvailableCoursesCommand());
		commands.put("enterForCourse", new EnterForCourseCommand());
		commands.put("addCourse", new AddCourseCommand());
		commands.put("viewStudents", new ViewStudentsCommand());
		commands.put("editCourse", new EditCourseCommand());
		commands.put("deleteCourse", new DeleteCourseCommand());
		commands.put("editPersonalInfo", new EditPersonalInfoCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("refuseFromCourse", new RefuseFromCourseCommand());
		commands.put("changeMark", new ChangeStudentMarkCommand());
		commands.put("toEditCoursePage", new ToEditCoursePageCommand());
		commands.put("getCourses", new GetCoursesCommand());
		commands.put("toTeacherMainPage", new ToTeacherMainPageCommand());
	}

	/**
	 * RequestHelperHolder is loaded on the first execution of
	 * RequestHelper.getInstance() or the first access to
	 * RequestHelperHolder.INSTANCE, not before.
	 */
	private static class RequestHelperHolder {
		private static final RequestHelper INSTANCE = new RequestHelper();
	}

	public Command getCommand(HttpServletRequest request) {
		// command extraction from request
		String action = request.getParameter("command");
		// getting of object suitable to command
		Command command = commands.get(action);
		if (command == null) {
			// if command doesn't exist in current object
			command = new NoCommand();
		}
		return command;
	}

	// creation of object. Singleton pattern
	public static RequestHelper getInstance() {
		return RequestHelperHolder.INSTANCE;
	}
}
