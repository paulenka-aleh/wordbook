package paulenka.aleh.wordbook.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import paulenka.aleh.wordbook.dao.WordDao;
import paulenka.aleh.wordbook.dao.mapper.WordListMapper;
import paulenka.aleh.wordbook.dao.mapper.WordMapper;
import paulenka.aleh.wordbook.dao.table.WordTable;
import paulenka.aleh.wordbook.data.Word;
import paulenka.aleh.wordbook.db.JdbcDaoTemplate;
import paulenka.aleh.wordbook.db.JdbcEntityMapper;

public class WordDaoImpl extends JdbcDaoTemplate implements WordDao {

    private static final String QUERY_LIST_WORDS = "SELECT `" + WordTable.ID + "`, `" + WordTable.WORD + "` FROM `" + WordTable.TABLE + "` WHERE `" + WordTable.WORD + "` LIKE ? ORDER BY `" + WordTable.WORD + "` LIMIT ?, ?;";
    private static final String QUERY_FETCH_SIZE_FOR_FILTER = "SELECT COUNT(`" + WordTable.ID + "`) FROM `" + WordTable.TABLE + "` WHERE `" + WordTable.WORD + "` LIKE ?;";
    private static final String QUERY_GET_WORD = "SELECT * FROM `" + WordTable.TABLE + "` WHERE `" + WordTable.ID + "` = ? LIMIT 1;";
    private static final String QUERY_CREATE_WORD = "INSERT INTO `" + WordTable.TABLE + "` (`" + WordTable.WORD + "`, `" + WordTable.EXPLANATION + "`) VALUES (?, ?);";
    private static final String QUERY_DELETE_WORD = "DELETE FROM `" + WordTable.TABLE + "` WHERE `" + WordTable.ID + "` = ? LIMIT 1;";
    private static final String QUERY_UPDATE_WORD = "UPDATE `" + WordTable.TABLE + "` SET `" + WordTable.WORD + "` = ?, `" + WordTable.EXPLANATION + "` = ? WHERE `" + WordTable.ID + "` = ? LIMIT 1;";

    private WordMapper wordMapper;
    private WordListMapper wordListMapper;

    public WordMapper getWordMapper() {
        if (wordMapper == null) {
            wordMapper = new WordMapper();
        }
        return wordMapper;
    }

    public WordListMapper getWordListMapper() {
        if (wordListMapper == null) {
            wordListMapper = new WordListMapper();
        }
        return wordListMapper;
    }

    protected String escape(String query) {
        return query.replaceAll("(\\%|\\_)", "\\\\$1");
    }

    @Override
    public List<Word> list(String filter, int size, int page) throws SQLException {
        return executeQuery(getWordListMapper(), QUERY_LIST_WORDS, "%" + escape(filter) + "%", page * size, size);
    }

    @Override
    public int getFetchSizeForFilter(String filter) throws SQLException {
        return executeQueryWithSingleResult(new JdbcEntityMapper<Integer>() {

            @Override
            public Integer map(ResultSet result) throws SQLException {
                return result.getInt(1);
            }
        }, QUERY_FETCH_SIZE_FOR_FILTER, "%" + filter + "%");
    }

    @Override
    public Word get(int id) throws SQLException {
        return executeQueryWithSingleResult(getWordMapper(), QUERY_GET_WORD, id);
    }

    @Override
    public int create(Word word) throws SQLException {
        return executeInsert(QUERY_CREATE_WORD, word.getWord(), word.getExplanation());
    }

    @Override
    public void update(Word word) throws SQLException {
        executeUpdate(QUERY_UPDATE_WORD, word.getWord(), word.getExplanation(), word.getId());
    }

    @Override
    public void delete(int id) throws SQLException {
        executeUpdate(QUERY_DELETE_WORD, id);
    }
}