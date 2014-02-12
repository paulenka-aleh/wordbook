package paulenka.aleh.wordbook.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import paulenka.aleh.wordbook.dao.table.WordTable;
import paulenka.aleh.wordbook.data.Word;
import paulenka.aleh.wordbook.db.JdbcEntityMapper;

public class WordMapper implements JdbcEntityMapper<Word> {

    @Override
    public Word map(ResultSet result) throws SQLException {
        return new Word(result.getInt(WordTable.ID), result.getString(WordTable.WORD), result.getString(WordTable.EXPLANATION));
    }
}