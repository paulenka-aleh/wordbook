package paulenka.aleh.wordbook.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcEntityMapper<T> {

    public T map(ResultSet result) throws SQLException;
}