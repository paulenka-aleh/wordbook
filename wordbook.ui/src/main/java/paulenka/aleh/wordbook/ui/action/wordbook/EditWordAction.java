package paulenka.aleh.wordbook.ui.action.wordbook;

import java.sql.SQLException;

import paulenka.aleh.wordbook.dao.WordDao;
import paulenka.aleh.wordbook.dao.impl.WordDaoImpl;
import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.data.Word;
import paulenka.aleh.wordbook.ui.action.common.ProcessFormAction;
import paulenka.aleh.wordbook.ui.interceptor.security.Authorization;

@Authorization(roles = { Role.MODERATOR })
public class EditWordAction extends ProcessFormAction {

    private static final long serialVersionUID = 1L;

    private WordDao wordDao;

    private Word word;
    private boolean success;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String execute() throws Exception {
        setSuccess(false);
        return super.execute();
    }

    @Override
    public String view() {
        try {
            setWord(getWordDao().get(getWord().getId()));
            return INPUT;
        } catch (SQLException ex) {
            return ERROR;
        }
    }

    @Override
    public String process() {
        try {
            getWordDao().update(getWord());
            setSuccess(true);
            return SUCCESS;
        } catch (SQLException ex) {
            return ERROR;
        }
    }

    @Override
    public void validate() {
        if (isFormPresent()) {
            if (getWord().getWord() == null || getWord().getWord().isEmpty()) {
                addFieldError("word.word", getText("edit-form.error.word.empty"));
            }
        }
    }
}