/**
 * 
 */
package by.bsuir.facultative.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import by.bsuir.facultative.dao.StudentDAO;
import by.bsuir.facultative.dao.pool.ConnectionPool;
import by.bsuir.facultative.entity.Course;
import by.bsuir.facultative.entity.Student;
import by.bsuir.facultative.entity.User;
import by.bsuir.facultative.exception.ConnectionPoolException;
import by.bsuir.facultative.exception.DAOException;

/**
 * @author SBozhko
 * 
 */
public class MysqlStudentDAO implements StudentDAO {

	private static final Logger logger = Logger
			.getLogger(MysqlStudentDAO.class);

	private Connection connection = null;

	private static final String LOGIN_STUDENT_QUERY = "SELECT user.email, user.password, student.fullName, student.id, user.id FROM user JOIN student ON user.id = student.idUser WHERE user.email = ? AND user.password = ?";
	private static final String GET_COURSES_QUERY = "SELECT course.title, course.description, course.hours, course.id FROM course JOIN archive ON course.id = archive.idCourse WHERE archive.idStudent = ?";
	private static final String SELECT_USER_BY_EMAIL = "SELECT user.id FROM user WHERE user.email = ?";
	private static final String INSERT_NEW_USER = "INSERT INTO user (email, password) VALUES (?, ?)";
	private static final String INSERT_NEW_STUDENT = "INSERT INTO student (fullName, idUser) VALUES (?, ?)";
	private static final String GET_MARK_BY_ID_COURSE_AND_ID_STUDENT = "SELECT archive.mark FROM archive WHERE archive.idCourse = ? and archive.idStudent = ?";
	private static final String SELECT_ALL_COURSES = "SELECT course.id, course.title, course.description, course.hours FROM course";
	private static final String SELECT_COURSE_BY_ID = "SELECT course.id, course.title, course.description, course.hours FROM course WHERE course.id = ?";
	private static final String ADD_COURSE_TO_STUDENT = "INSERT INTO archive (idStudent, idCourse) VALUES (?, ?)";
	private static final String SELECT_STUDENT_BY_EMAIL = "SELECT * FROM student JOIN user ON user.id = student.idUser WHERE user.email = ?";
	private static final String DELETE_STUDENT_COURSE = "DELETE FROM archive WHERE idCourse = ? and idStudent = ?";

	public MysqlStudentDAO(Connection con) {
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
			logger.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e);
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

			preparedStatement = connection.prepareStatement(INSERT_NEW_STUDENT);
			preparedStatement.setString(1, newUser.getFullName());
			preparedStatement.setLong(2, idUser);

			preparedStatement.executeUpdate();
			preparedStatement.close();
			ConnectionPool.releaseConnection(connection);
			return idUser;
		} catch (SQLException e) {
			logger.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e);
			throw new DAOException(e);
		}
	}

	@Override
	public Set<Course> getAllCourses() throws DAOException {
		PreparedStatement prStatement;
		try {
			prStatement = connection.prepareStatement(SELECT_ALL_COURSES);

			ResultSet resultSet = prStatement.executeQuery();
			Set<Course> courses = new HashSet<Course>();
			Course course = null;

			while (resultSet.next()) {

				course = new Course();

				course.setId(resultSet.getLong(1));
				course.setTitle(resultSet.getString(2));
				course.setDescription(resultSet.getString(3));
				course.setHours(resultSet.getInt(4));

				logger.info(course);

				courses.add(course);
			}
			prStatement.close();
			ConnectionPool.releaseConnection(connection);
			return courses;
		} catch (SQLException e) {
			logger.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e);
			throw new DAOException(e);
		}
	}

	@Override
	public Course getCourse(long idCourse) throws DAOException {
		PreparedStatement prStatement;
		try {
			prStatement = connection.prepareStatement(SELECT_COURSE_BY_ID);

			prStatement.setLong(1, idCourse);

			ResultSet resultSet = prStatement.executeQuery();
			Course course = null;

			if (resultSet.next()) {

				course = new Course();

				course.setId(resultSet.getLong(1));
				course.setTitle(resultSet.getString(2));
				course.setDescription(resultSet.getString(3));
				course.setHours(resultSet.getInt(4));

				logger.info(course);
			}
			prStatement.close();
			ConnectionPool.releaseConnection(connection);
			return course;
		} catch (SQLException e) {
			logger.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e);
			throw new DAOException(e);
		}
	}

	@Override
	public void enterForCourse(Student student, Course course)
			throws DAOException {
		PreparedStatement prStatement;
		try {
			prStatement = connection.prepareStatement(ADD_COURSE_TO_STUDENT);

			prStatement.setLong(1, student.getIdStudent());
			prStatement.setLong(2, course.getId());

			prStatement.executeUpdate();
			prStatement.close();
			ConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.error(e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e);
			throw new DAOException(e);
		}
	}

	@Override
	public boolean checkUserByEmail(User newUser) throws DAOException {
		logger.debug("checkUserByEmail");
		logger.debug(newUser);
		logger.debug(newUser.getEmail());
		logger.debug(newUser.getPassword());
		PreparedStatement prStatement;
		try {
			prStatement = connection.prepareStatement(SELECT_STUDENT_BY_EMAIL);

			logger.debug("prStatement");
			prStatement.setString(1, newUser.getEmail());
			logger.debug("setString");
			ResultSet resultSet = prStatement.executeQuery();
			logger.debug("resultSet");
			if (resultSet.next()) {
				prStatement.close();
				ConnectionPool.releaseConnection(connection);
				logger.debug("true from checkUserByEmail");
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
			prStatement = connection.prepareStatement(LOGIN_STUDENT_QUERY);

			User loginUser = new Student();

			prStatement.setString(1, user.getEmail());
			prStatement.setString(2, user.getPassword());

			logger.info("login " + user.getEmail());
			logger.info("password " + user.getPassword());

			ResultSet rs = null;

			rs = prStatement.executeQuery();

			if (rs.next()) {

				loginUser.setEmail(rs.getString(1));
				loginUser.setPassword(rs.getString(2));
				loginUser.setFullName(rs.getString(3));
				((Student) loginUser).setIdStudent(rs.getLong(4));
				loginUser.setIdUser(rs.getLong(5));

				logger.info(loginUser);

				prStatement = connection.prepareStatement(GET_COURSES_QUERY);
				prStatement.setLong(1, ((Student) loginUser).getIdStudent());

				ResultSet coursesRS = null;

				coursesRS = prStatement.executeQuery();
				Set<Course> courses = new HashSet<Course>();
				Map<Course, Integer> marks = new HashMap<Course, Integer>();
				Course course = null;
				while (coursesRS.next()) {
					course = new Course();

					course.setTitle(coursesRS.getString(1));
					course.setDescription(coursesRS.getString(2));
					course.setHours(coursesRS.getInt(3));
					course.setId(coursesRS.getLong(4));
					logger.debug(course);

					prStatement = connection
							.prepareStatement(GET_MARK_BY_ID_COURSE_AND_ID_STUDENT);
					prStatement.setLong(1, course.getId());
					prStatement
							.setLong(2, ((Student) loginUser).getIdStudent());
					ResultSet marksRS = prStatement.executeQuery();

					if (marksRS.next()) {
						marks.put(course, marksRS.getInt(1));
					}

					courses.add(course);
				}

				((Student) loginUser).setMarks(marks);
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
	public void refuseFromCourse(User user, Course course) throws DAOException {
		logger.info("refuseFromCourse()");
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection
					.prepareStatement(DELETE_STUDENT_COURSE);

			preparedStatement.setLong(1, course.getId());
			preparedStatement.setLong(2, ((Student) user).getIdStudent());
			logger.debug(preparedStatement);
			preparedStatement.executeUpdate();
			logger.debug("course deleted from archive");
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
	public Map<Course, Integer> getCoursesAndMarks(User user) throws DAOException {
		logger.info("getCoursesAndMarks()");
		PreparedStatement prStatement;
		try {
			prStatement = connection.prepareStatement(GET_COURSES_QUERY);

			prStatement.setLong(1, ((Student) user).getIdStudent());

			ResultSet coursesRS = null;

			coursesRS = prStatement.executeQuery();
			Set<Course> courses = new HashSet<Course>();
			Map<Course, Integer> marks = new HashMap<Course, Integer>();
			Course course = null;
			while (coursesRS.next()) {
				course = new Course();

				course.setTitle(coursesRS.getString(1));
				course.setDescription(coursesRS.getString(2));
				course.setHours(coursesRS.getInt(3));
				course.setId(coursesRS.getLong(4));
				logger.debug(course);

				prStatement = connection
						.prepareStatement(GET_MARK_BY_ID_COURSE_AND_ID_STUDENT);
				prStatement.setLong(1, course.getId());
				prStatement.setLong(2, ((Student) user).getIdStudent());
				ResultSet marksRS = prStatement.executeQuery();

				if (marksRS.next()) {
					marks.put(course, marksRS.getInt(1));
				}

				courses.add(course);
			}

			// ((Student) user).setMarks(marks);
			prStatement.close();

			ConnectionPool.releaseConnection(connection);

			return marks;
		} catch (SQLException e) {
			logger.error("sql problems", e);
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error("pool problems", e);
			throw new DAOException(e);
		}
	}

	@Override
	public Course addCourse(Course newCourse, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudentsOfCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStudentMark(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Course> getCourses(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCourse(Course course) {
		// TODO Auto-generated method stub

	}
}
