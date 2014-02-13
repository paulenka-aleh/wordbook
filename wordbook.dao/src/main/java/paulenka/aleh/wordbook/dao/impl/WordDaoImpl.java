package paulenka.aleh.wordbook.dao.impl;

import java.sql.SQLException;
import java.util.List;

import paulenka.aleh.wordbook.dao.WordDao;
import paulenka.aleh.wordbook.dao.mapper.WordListMapper;
import paulenka.aleh.wordbook.dao.mapper.WordMapper;
import paulenka.aleh.wordbook.dao.table.WordTable;
import paulenka.aleh.wordbook.data.Word;
import paulenka.aleh.wordbook.db.JdbcDaoTemplate;

public class WordDaoImpl extends JdbcDaoTemplate implements WordDao {

    private final static String QUERY_LIST_WORDS = "SELECT `" + WordTable.ID + "`, `" + WordTable.WORD + "` FROM `" + WordTable.TABLE + "` WHERE `" + WordTable.WORD + "` LIKE ? LIMIT ?, ?;";
    private final static String QUERY_GET_WORD = "SELECT * FROM `" + WordTable.TABLE + "` WHERE `" + WordTable.ID + "` = ? LIMIT 1;";
    private final static String QUERY_CREATE_WORD = "INSERT INTO `" + WordTable.TABLE + "` (`" + WordTable.WORD + "`, `" + WordTable.EXPLANATION + "`) VALUES (?, ?);";
    private final static String QUERY_DELETE_WORD = "DELETE FROM `" + WordTable.TABLE + "` WHERE `" + WordTable.ID + "` = ? LIMIT 1;";
    private final static String QUERY_UPDATE_WORD = "UPDATE `" + WordTable.TABLE + "` SET `" + WordTable.WORD + "` = ?, `" + WordTable.EXPLANATION + "` = ? WHERE `" + WordTable.ID + " = ? LIMIT 1;";

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

    @Override
    public List<Word> list(String filter, int size, int page) throws SQLException {
        return executeQuery(getWordListMapper(), QUERY_LIST_WORDS, "%" + filter + "%", page * size, size);
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
        executeUpdate(QUERY_UPDATE_WORD, word.getId(), word.getWord(), word.getExplanation());
    }

    @Override
    public void delete(int id) throws SQLException {
        executeUpdate(QUERY_DELETE_WORD, id);
    }
}