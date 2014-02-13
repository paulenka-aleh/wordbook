package paulenka.aleh.wordbook.ui.action.wordbook;

import java.sql.SQLException;

import paulenka.aleh.wordbook.dao.WordDao;
import paulenka.aleh.wordbook.dao.impl.WordDaoImpl;
import paulenka.aleh.wordbook.data.Word;

import com.opensymphony.xwork2.ActionSupport;

public class WordExplanationAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private WordDao wordDao;

    private Word word;

    public WordDao getWordDao() {
        if (wordDao == null) {
            wordDao = new WordDaoImpl();
        }
        return wordDao;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    @Override
    public String execute() {
        try {
            setWord(getWordDao().get(getWord().getId()));
            return SUCCESS;
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            return ERROR;
        }
    }
}