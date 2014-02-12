package paulenka.aleh.wordbook.ui.action.wordbook;

import paulenka.aleh.wordbook.ui.interceptor.security.Authorization;

import com.opensymphony.xwork2.ActionSupport;

@Authorization
public class ViewWordbookAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    @Override
    public String execute() {
        return SUCCESS;
    }
}