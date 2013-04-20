package by.bsuir.facultative.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import by.bsuir.facultative.exception.ConnectionPoolException;
import by.bsuir.facultative.util.ConfigurationManager;

/**
 * ConnectionPool create and manage pool of database connections.
 * 
 * 
 * @author SBozhko
 * 
 */
public class ConnectionPool {
	private static Logger logger = Logger.getLogger(ConnectionPool.class);
	public static final int DEFAULT_POOL_SIZE = 10;
	/** single instance */
	private static ConnectionPool instance = null;
	/** free connections queue */
	private static BlockingQueue<Connection> connectionQueue;

	private static void init() throws ConnectionPoolException {
		logger.debug("in init()");

		String driver = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.DATABASE_DRIVER_NAME);
		String dbAdminName = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.DATABASE_ADMIN_NAME);
		String dbAdminPassword = ConfigurationManager.getInstance()
				.getProperty(ConfigurationManager.DATABASE_ADMIN_PASSWORD);
		String url = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.DATABASE_WHOLE_URL);
		String poolSizeStr = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.DATABASE_POOl_SIZE);
		int poolSize = (poolSizeStr != null) ? Integer.parseInt(poolSizeStr)
				: DEFAULT_POOL_SIZE;

		instance = new ConnectionPool(driver, url, dbAdminName,
				dbAdminPassword, poolSize);
		logger.debug("instance = new ConnectionPool(driver, url, dbAdminName,dbAdminPassword, poolSize);");

	}

	public static void dispose() throws SQLException {
		if (instance != null) {
			instance.clearConnectionQueue();
			instance = null;
			logger.info("Connection pool succesfully disposed");
		}
	}

	public static synchronized ConnectionPool getInstance()
			throws ConnectionPoolException {
		if (instance == null) {
			logger.debug("(instance == null) ");
			init();
			logger.debug("init");
		}
		logger.debug("before return");

		return instance;
	}

	private ConnectionPool(String driver, String url, String user,
			String password, int poolSize) throws ConnectionPoolException {
		try {
			Class.forName(driver);
			connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user,
						password);
				connectionQueue.offer(connection);
			}
		} catch (ClassNotFoundException e) {
			logger.error("driver not found", e);
			throw new ConnectionPoolException(e);
		} catch (SQLException e) {
			logger.error("problems with creating connection to DB", e);
			throw new ConnectionPoolException(e);
		}

	}

	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			logger.info("trying to take connection from pool");
			connection = connectionQueue.take();
			logger.info("connection taken");
		} catch (InterruptedException e) {
			logger.error(
					"Free connection waiting interrupted. Returned `null` connection",
					e);
			throw new ConnectionPoolException(
					"Free connection waiting interrupted. Returned `null` connection",
					e);
		}
		return connection;
	}

	public static void releaseConnection(Connection connection)
			throws ConnectionPoolException
	// throws ConnectionPoolException {
	{
		try {
			if (!connection.isClosed()) {
				if (!connectionQueue.offer(connection)) {
					logger.error("Connection not added. Possible `leakage` of connections");
				} else {
				}
			} else {
				logger.error("Trying to release closed connection. Possible `leakage` of connections");
			}
		} catch (SQLException e) {
			logger.error(
					"SQLException at conection isClosed() checking. Connection not added",
					e);
			throw new ConnectionPoolException(
					"SQLException at conection isClosed() checking. Connection not added",
					e);
		}
	}

	private void clearConnectionQueue() throws SQLException {
		Connection connection;
		while ((connection = connectionQueue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			connection.close();
			logger.info("connection closed");
		}
	}

}
