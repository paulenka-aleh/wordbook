package paulenka.aleh.wordbook.action.wordbook;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import paulenka.aleh.wordbook.entity.WordbookDataRequest;

import com.opensymphony.xwork2.ActionSupport;

public class WordbookDataAction extends ActionSupport implements ServletResponseAware {

    private static final long serialVersionUID = 1L;

    private HttpServletResponse servletResponse;

    private WordbookDataRequest request;

    public HttpServletResponse getServletResponse() {
        return servletResponse;
    }

    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    public WordbookDataRequest getRequest() {
        return request;
    }

    public void setRequest(WordbookDataRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        System.out.println(getRequest());
        return NONE;
    }
}