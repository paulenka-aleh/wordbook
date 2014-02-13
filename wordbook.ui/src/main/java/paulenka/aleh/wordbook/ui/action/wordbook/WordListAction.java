package paulenka.aleh.wordbook.ui.action.wordbook;

import java.sql.SQLException;
import java.util.List;

import paulenka.aleh.wordbook.dao.WordDao;
import paulenka.aleh.wordbook.dao.impl.WordDaoImpl;
import paulenka.aleh.wordbook.data.Word;
import paulenka.aleh.wordbook.ui.data.WordListRequest;

import com.opensymphony.xwork2.ActionSupport;

public class WordListAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private WordDao wordDao;

    private WordListRequest listRequest;
    private List<Word> wordList;

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

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    @Override
    public String execute() {
        try {
            List<Word> list = getWordDao().list(getListRequest().getFilter(), getListRequest().getSize(), getListRequest().getPage());
            setWordList(list);
            return SUCCESS;
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            return ERROR;
        }
    }
}