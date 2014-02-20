package paulenka.aleh.wordbook.ui.action.wordbook;

import java.sql.SQLException;

import paulenka.aleh.wordbook.dao.WordDao;
import paulenka.aleh.wordbook.dao.impl.WordDaoImpl;
import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.data.Word;
import paulenka.aleh.wordbook.ui.interceptor.security.Authorization;
import paulenka.aleh.wordbook.ui.interceptor.security.JsonActionResult;

import com.opensymphony.xwork2.ActionSupport;

@JsonActionResult
@Authorization(roles = { Role.MODERATOR, Role.USER })
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
            return ERROR;
        }
    }
}