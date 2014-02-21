package paulenka.aleh.wordbook.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public final class ConnectionManager {

    private final static String DB_PROPERTY_FILE_NAME = "/db.properties";

    private final static ConnectionManager instance = new ConnectionManager();

    public static ConnectionManager getInstance() {
        return instance;
    }

    private BasicDataSource dataSource;

    public BasicDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
        }
        return dataSource;
    }

    private ConnectionManager() {
        try {
            Properties properties = new Properties();

            properties.load(ConnectionManager.class.getResourceAsStream(DB_PROPERTY_FILE_NAME));

            Class.forName(properties.getProperty("driver"));

            getDataSource().setDriverClassName(properties.getProperty("driver"));
            getDataSource().setUrl(properties.getProperty("url"));
            getDataSource().setUsername(properties.getProperty("username"));
            getDataSource().setPassword(properties.getProperty("password"));
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}
