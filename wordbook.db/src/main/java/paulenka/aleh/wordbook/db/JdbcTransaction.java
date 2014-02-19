package paulenka.aleh.wordbook.db;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class JdbcTransaction<R> extends JdbcDaoTemplate {

    private Connection connection;

    protected Connection getConnection() {
        return connection;
    }

    protected void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected Connection createConnection() throws SQLException {
        return connection;
    }

    @Override
    protected void closeConnection(Connection connection) throws SQLException {
        // Do nothing: connection will be closed after transaction is executed.
    }

    public R execute() throws SQLException {
        try {
            setConnection(super.createConnection());
            getConnection().setAutoCommit(false);

            R result = transaction();

            getConnection().commit();

            return result;
        } finally {
            getConnection().setAutoCommit(true);
            getConnection().close();
        }
    }

    protected abstract R transaction() throws SQLException;
}