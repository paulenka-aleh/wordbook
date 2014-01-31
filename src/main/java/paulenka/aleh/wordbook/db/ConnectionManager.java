package paulenka.aleh.wordbook.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionManager {

	private static ConnectionManager instance;

	public static ConnectionManager getInstance() throws ClassNotFoundException, IOException {
		if (instance == null) {
			synchronized (ConnectionManager.class) {
				if (instance == null) {
					instance = new ConnectionManager();
				}
			}
		}
		return instance;
	}

	private final static String DB_PROPERTY_FILE_NAME = "/db.properties";

	private BasicDataSource dataSource;

	public BasicDataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new BasicDataSource();
		}
		return dataSource;
	}

	private ConnectionManager() throws IOException, ClassNotFoundException {
		Properties properties = new Properties();
		properties.load(ConnectionManager.class.getResourceAsStream(DB_PROPERTY_FILE_NAME));

		Class.forName((properties.getProperty("driver")));

		getDataSource().setDriverClassName(properties.getProperty("driver"));
		getDataSource().setUrl(properties.getProperty("url"));
		getDataSource().setUsername(properties.getProperty("username"));
		getDataSource().setPassword(properties.getProperty("password"));
	}

	public Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

}
