package paulenka.aleh.wordbook.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcDaoTemplate {

	protected Connection createConnection() throws SQLException {
		try {
			return ConnectionManager.getInstance().getConnection();
		} catch (ClassNotFoundException | IOException ex) {
			throw new SQLException(ex);
		}
	}

	protected void substituteArguments(PreparedStatement statement, Object... arguments) throws SQLException {
		for (int i = 0; arguments != null && i < arguments.length; ++i) {
			statement.setObject(i + 1, arguments[i]);
		}
	}

	protected <T> List<T> executeQuery(JdbcEntityMapper<? extends T> mapper, String query, Object... arguments) throws SQLException {
		try (Connection connection = createConnection()) {

			try (PreparedStatement statement = connection.prepareStatement(query)) {
				substituteArguments(statement, arguments);

				try (ResultSet set = statement.executeQuery()) {
					List<T> result = new ArrayList<>(set.getFetchSize());

					while (set.next()) {
						result.add(mapper.map(set));
					}

					return result;
				}
			}
		}
	}

	protected <T> T executeQueryWithSingleResult(JdbcEntityMapper<? extends T> mapper, String query, Object... arguments) throws SQLException {
		List<T> results = executeQuery(mapper, query, arguments);
		return results == null || results.isEmpty() ? null : results.get(0);
	}

	protected void executeUpdate(String query, Object... arguments) throws SQLException {
		try (Connection connection = createConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				substituteArguments(statement, arguments);
				statement.executeUpdate();
			}
		}
	}

	protected int executeInsert(String query, Object... arguments) throws SQLException {
		try (Connection connection = createConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				substituteArguments(statement, arguments);
				statement.executeUpdate();

				try (ResultSet set = statement.getGeneratedKeys()) {
					set.next();
					return set.getInt(1);
				}
			}
		}
	}
}