package paulenka.aleh.wordbook.ui.action.wordbook;

import java.sql.SQLException;

import paulenka.aleh.wordbook.dao.WordDao;
import paulenka.aleh.wordbook.dao.impl.WordDaoImpl;
import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.data.Word;
import paulenka.aleh.wordbook.ui.action.common.ProcessFormAction;
import paulenka.aleh.wordbook.ui.interceptor.security.Authorization;

@Authorization(roles = { Role.MODERATOR })
public class DeleteWordAction extends ProcessFormAction {

    private static final long serialVersionUID = 1L;

    private WordDao wordDao;

    private Word word;

    protected WordDao getWordDao() {
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
    public String view() {
        return INPUT;
    }

    @Override
    public String process() {
        try {
            getWordDao().delete(getWord().getId());
            return SUCCESS;
        } catch (SQLException ex) {
            return ERROR;
        }
    }
}