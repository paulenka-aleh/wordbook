package paulenka.aleh.wordbook.dao;

import java.sql.SQLException;
import java.util.List;

import paulenka.aleh.wordbook.data.Word;

public interface WordDao {

    public List<Word> list(String filter, int size, int page) throws SQLException;

    public int getFetchSizeForFilter(String filter) throws SQLException;

    public Word get(int id) throws SQLException;

    public int create(Word word) throws SQLException;

    public void update(Word word) throws SQLException;

    public void delete(int id) throws SQLException;
}