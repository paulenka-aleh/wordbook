package paulenka.aleh.wordbook.ui.action.wordbook;

import java.sql.SQLException;
import java.util.List;

import paulenka.aleh.wordbook.dao.WordDao;
import paulenka.aleh.wordbook.dao.impl.WordDaoImpl;
import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.data.Word;
import paulenka.aleh.wordbook.ui.data.WordListRequest;
import paulenka.aleh.wordbook.ui.data.WordListResponse;
import paulenka.aleh.wordbook.ui.interceptor.security.Authorization;
import paulenka.aleh.wordbook.ui.interceptor.security.JsonActionResult;

import com.opensymphony.xwork2.ActionSupport;

@JsonActionResult
@Authorization(roles = { Role.MODERATOR, Role.USER })
public class WordListAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private WordDao wordDao;

    private WordListRequest listRequest;
    private WordListResponse listResponse;

    public WordDao getWordDao() {
        if (wordDao == null) {
            wordDao = new WordDaoImpl();
        }
        return wordDao;
    }

    public WordListRequest getListRequest() {
        return listRequest;
    }

    public void setListRequest(WordListRequest listRequest) {
        this.listRequest = listRequest;
    }

    public WordListResponse getListResponse() {
        return listResponse;
    }

    public void setListResponse(WordListResponse listResponse) {
        this.listResponse = listResponse;
    }

    @Override
    public String execute() {
        try {
            List<Word> list = getWordDao().list(getListRequest().getFilter(), getListRequest().getSize(), getListRequest().getPage());
            int size = getWordDao().getFetchSizeForFilter(getListRequest().getFilter());
            setListResponse(new WordListResponse(list, size));
            return SUCCESS;
        } catch (SQLException ex) {
            return ERROR;
        }
    }
}