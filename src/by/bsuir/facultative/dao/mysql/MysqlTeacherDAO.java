/**
 * 
 */
package by.bsuir.facultative.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import by.bsuir.facultative.dao.TeacherDAO;
import by.bsuir.facultative.dao.pool.ConnectionPool;
import by.bsuir.facultative.entity.Course;
import by.bsuir.facultative.entity.Student;
import by.bsuir.facultative.entity.Teacher;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.exception.ConnectionPoolException;
import by.bsuir.facultative.exception.DAOException;

/**
 * @author SBozhko
 * 
 */
public class MysqlTeacherDAO implements TeacherDAO {
	private static final Logger logger = Logger
			.getLogger(MysqlTeacherDAO.class);

	private Connection connection = null;

	private static final String LOGIN_TEACHER_QUERY = "SELECT user.email, user.password, teacher.fullName, teacher.id, user.id FROM user JOIN teacher ON user.id = teacher.idUser WHERE user.email = ? AND user.password = ?";
	private static final String SELECT_USER_BY_EMAIL = "SELECT user.id FROM user WHERE user.email = ?";
	private static final String INSERT_NEW_USER = "INSERT INTO user (email, password) VALUES (?, ?)";
	private static final String INSERT_NEW_TEACHER = "INSERT INTO teacher (fullName, idUser) VALUES (?, ?)";
	private static final String SELECT_TEACHER_BY_EMAIL = "SELECT teacher.id FROM teacher INNER JOIN user ON teacher.idUser = user.id WHERE user.email = ?";
	private static final String GET_COURSES_QUERY = "SELECT course.title, course.description, course.hours, course.id FROM course JOIN teacher ON course.idTeacher = teacher.id WHERE course.idTeacher = ?";
	private static final String INSERT_NEW_COURSE = "INSERT INTO course (title, description, hours, idTeacher) VALUES (?, ?, ?, ?)";
	private static final String SELECT_STUDENTS_OF_COURSE = "SELECT student.idUser, student.id, student.fullName, archive.mark FROM archive JOIN student ON archive.idStudent = student.id WHERE archive.idCourse = ?";
	private static final String UPDATE_STUDENT_MARK = "UPDATE archive SET archive.mark = ? WHERE archive.idStudent = ? and archive.idCourse = ?";
	private static final String UPDATE_COURSE = "UPDATE course SET course.title = ?, course.description = ?, course.hours = ? WHERE course.id = ?";
	private static final String DELETE_COURSE_FROM_ARCHIVE = "DELETE FROM archive WHERE idCourse = ?";
	private static final String DELETE_COURSE_FROM_COURSES = "DELETE FROM course WHERE id = ?";

	public MysqlTeacherDAO(Connection con) {
		this.connection = con;
	}

	@Override
	public boolean isExists(User newUser) throws DAOException {
		PreparedStatement prStatement;
		try {
			prStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);

			prStatement.setString(1, newUser.getEmail());
			ResultSet resultSet = prStatement.executeQuery();
			if (resultSet.next()) {
				prStatement.close();
				ConnectionPool.releaseConnection(connection);
				return true;
			} else {
				prStatement.close();
				ConnectionPool.releaseConnection(connection);
				return false;
			}
		} catch (SQLException e) {
			logger.error("sql problems", e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error("pool problems", e);
			throw new DAOException(e);
		}
	}

	@Override
	public long insertUser(User newUser) throws DAOException {

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(INSERT_NEW_USER,
					Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, newUser.getEmail());
			preparedStatement.setString(2, newUser.getPassword());

			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			long idUser = 0;
			if (rs.next()) {
				idUser = rs.getLong(1);
				logger.debug("id User = " + idUser);
			}

			preparedStatement = connection.prepareStatement(INSERT_NEW_TEACHER);
			preparedStatement.setString(1, newUser.getFullName());
			preparedStatement.setLong(2, idUser);

			preparedStatement.executeUpdate();
			preparedStatement.close();
			ConnectionPool.releaseConnection(connection);
			return idUser;
		} catch (SQLException e) {
			logger.error("sql problems", e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error("pool problems", e);
			throw new DAOException(e);
		}
	}

	@Override
	public boolean checkUserByEmail(User newUser) throws DAOException {
		PreparedStatement prStatement;
		try {
			prStatement = connection.prepareStatement(SELECT_TEACHER_BY_EMAIL);

			prStatement.setString(1, newUser.getEmail());
			ResultSet resultSet = prStatement.executeQuery();
			if (resultSet.next()) {
				prStatement.close();
				ConnectionPool.releaseConnection(connection);
				return true;
			} else {
				prStatement.close();
				ConnectionPool.releaseConnection(connection);
				return false;
			}
		} catch (SQLException e) {
			logger.error("sql problems", e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error("pool problems", e);
			throw new DAOException(e);
		}
	}

	@Override
	public User selectUserByEmailAndPassword(User user) throws DAOException {
		PreparedStatement prStatement;
		try {
			prStatement = connection.prepareStatement(LOGIN_TEACHER_QUERY);

			User loginUser = new Teacher();

			prStatement.setString(1, user.getEmail());
			prStatement.setString(2, user.getPassword());

			logger.info("login " + user.getEmail());
			logger.info("password " + user.getPassword());

			ResultSet rs = null;

			rs = prStatement.executeQuery();

			/*
			 * проверка, существует ли пользователь с указанным логином и
			 * паролем
			 */

			if (rs.next()) {

				loginUser.setEmail(rs.getString(1));
				loginUser.setPassword(rs.getString(2));
				loginUser.setFullName(rs.getString(3));
				((Teacher) loginUser).setIdTeacher(rs.getLong(4));
				loginUser.setIdUser(rs.getLong(5));

				logger.info(loginUser);

				prStatement = connection.prepareStatement(GET_COURSES_QUERY);
				prStatement.setLong(1, ((Teacher) loginUser).getIdTeacher());

				ResultSet coursesRS = prStatement.executeQuery();
				List<Course> courses = new ArrayList<Course>();
				Course course = null;
				while (coursesRS.next()) {
					course = new Course();

					course.setTitle(coursesRS.getString(1));
					course.setDescription(coursesRS.getString(2));
					course.setHours(coursesRS.getInt(3));
					course.setId(coursesRS.getLong(4));
					logger.debug(course);

					courses.add(course);
				}

				((Teacher) loginUser).setCourses(courses);
			}
			prStatement.close();
			ConnectionPool.releaseConnection(connection);
			return loginUser;
		} catch (SQLException e) {
			logger.error("sql problems", e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error("pool problems", e);
			throw new DAOException(e);
		}
	}

	@Override
	public Course addCourse(Course newCourse, User user) throws DAOException {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(INSERT_NEW_COURSE,
					Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, newCourse.getTitle());
			preparedStatement.setString(2, newCourse.getDescription());
			preparedStatement.setLong(3, newCourse.getHours());
			preparedStatement.setLong(4, ((Teacher) user).getIdTeacher());

			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			long idCourse = 0;
			if (rs.next()) {
				idCourse = rs.getLong(1);
				logger.debug("idCourse = " + idCourse);
			}

			newCourse.setId(idCourse);

			logger.debug(newCourse);

			preparedStatement.close();
			ConnectionPool.releaseConnection(connection);
			return newCourse;
		} catch (SQLException e) {
			logger.error("sql problems", e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error("pool problems", e);
			throw new DAOException(e);
		}
	}

	@Override
	public List<Student> getStudentsOfCourse(Course course) throws DAOException {
		PreparedStatement prStatement;
		try {
			prStatement = connection
					.prepareStatement(SELECT_STUDENTS_OF_COURSE);

			prStatement.setLong(1, course.getId());

			ResultSet resultSet = prStatement.executeQuery();
			List<Student> students = new ArrayList<Student>();
			Student student = null;

			Course studentsCourse = null;
			Map<Course, Integer> marks = null;

			while (resultSet.next()) {

				student = new Student();
				studentsCourse = new Course();
				marks = new HashMap<Course, Integer>();

				studentsCourse.setId(course.getId());

				student.setIdUser(resultSet.getLong(1));
				student.setIdStudent(resultSet.getLong(2));
				student.setFullName(resultSet.getString(3));

				logger.info(student);

				marks.put(studentsCourse, resultSet.getInt(4));

				logger.info(marks);

				student.setMarks(marks);

				students.add(student);

				logger.info(students);
			}
			prStatement.close();
			ConnectionPool.releaseConnection(connection);
			return students;
		} catch (SQLException e) {
			logger.error("sql problems", e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error("pool problems", e);
			throw new DAOException(e);
		}
	}

	@Override
	public void updateStudentMark(Student student) throws DAOException {
		logger.info("updateStudentMark");
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection
					.prepareStatement(UPDATE_STUDENT_MARK);

			preparedStatement.setInt(1, (int) student.getMarks().values()
					.toArray()[0]);
			logger.debug("preparedStatement.setInt(1, (int)student.getMarks().values().toArray()[0]);"
					+ (int) student.getMarks().values().toArray()[0]);
			preparedStatement.setLong(2, student.getIdStudent());
			logger.debug("	preparedStatement.setLong(2, student.getIdStudent());"
					+ student.getIdStudent());
			preparedStatement.setLong(3, ((Course) student.getMarks().keySet()
					.toArray()[0]).getId());
			logger.debug("preparedStatement.setLong(3, (long)student.getMarks().keySet().toArray()[0]);"
					+ ((Course) student.getMarks().keySet().toArray()[0])
							.getId());

			preparedStatement.executeUpdate();

			logger.debug("mark updated");

			preparedStatement.close();
			ConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.error("sql problems", e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error("pool problems", e);
			throw new DAOException(e);
		}
	}

	@Override
	public void updateCourse(Course course) throws DAOException {
		logger.info("updateCourse");
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(UPDATE_COURSE);

			preparedStatement.setString(1, course.getTitle());
			preparedStatement.setString(2, course.getDescription());
			preparedStatement.setInt(3, course.getHours());
			preparedStatement.setLong(4, course.getId());

			logger.debug(preparedStatement);

			preparedStatement.executeUpdate();

			logger.debug("course updated");

			preparedStatement.close();
			ConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.error("sql problems", e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error("pool problems", e);
			throw new DAOException(e);
		}

	}

	@Override
	public List<Course> getCourses(User user) throws DAOException {
		PreparedStatement prStatement;
		try {
			prStatement = connection.prepareStatement(GET_COURSES_QUERY);

			prStatement.setLong(1, ((Teacher) user).getIdTeacher());

			ResultSet coursesRS = prStatement.executeQuery();
			List<Course> courses = new ArrayList<Course>();
			Course course = null;
			while (coursesRS.next()) {
				course = new Course();
				course.setTitle(coursesRS.getString(1));
				course.setDescription(coursesRS.getString(2));
				course.setHours(coursesRS.getInt(3));
				course.setId(coursesRS.getLong(4));
				logger.debug(course);
				courses.add(course);
			}
			prStatement.close();
			ConnectionPool.releaseConnection(connection);
			return courses;
		} catch (SQLException e) {
			logger.error("sql problems", e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error("pool problems", e);
			throw new DAOException(e);
		}
	}

	@Override
	public void deleteCourse(Course course) throws DAOException {
		logger.info("deleteCourse");
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection
					.prepareStatement(DELETE_COURSE_FROM_ARCHIVE);

			preparedStatement.setLong(1, course.getId());
			logger.debug(preparedStatement);
			preparedStatement.executeUpdate();
			logger.debug("course deleted from archive");
			preparedStatement = connection
					.prepareStatement(DELETE_COURSE_FROM_COURSES);
			preparedStatement.setLong(1, course.getId());
			logger.debug(preparedStatement);
			preparedStatement.executeUpdate();
			logger.debug("course deleted from courses table");
			preparedStatement.close();
			ConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.error("sql problems", e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error("pool problems", e);
			throw new DAOException(e);
		}
	}

	@Override
	public void refuseFromCourse(User user, Course course) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<Course, Integer> getCoursesAndMarks(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}