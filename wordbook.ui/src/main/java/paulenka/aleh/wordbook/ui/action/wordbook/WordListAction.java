package paulenka.aleh.wordbook.ui.action.wordbook;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import paulenka.aleh.wordbook.dao.WordDao;
import paulenka.aleh.wordbook.dao.impl.WordDaoImpl;
import paulenka.aleh.wordbook.data.Word;
import paulenka.aleh.wordbook.ui.data.WordListRequest;

import com.opensymphony.xwork2.ActionSupport;

public class WordListAction extends ActionSupport implements ServletResponseAware {

    private static final long serialVersionUID = 1L;

    private HttpServletResponse servletResponse;

    private WordDao wordDao;

    private WordListRequest listRequest;

    public HttpServletResponse getServletResponse() {
        return servletResponse;
    }

    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

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

    @Override
    public String execute() {
        try {
            List<Word> list = getWordDao().list(getListRequest().getFilter(), getListRequest().getSize(), getListRequest().getPage());
            getServletResponse().getWriter().println(list);
        } catch (IOException | SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            return ERROR;
        }
        return NONE;
    }
}