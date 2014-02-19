package paulenka.aleh.wordbook.ui.action.user;

import paulenka.aleh.wordbook.ui.interceptor.back.BackResultAction;
import paulenka.aleh.wordbook.ui.interceptor.security.Authorization;
import paulenka.aleh.wordbook.ui.login.LoginManager;

import com.opensymphony.xwork2.ActionSupport;

@Authorization
@BackResultAction
public class LogoutUserAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private LoginManager loginManager;

    protected LoginManager getLoginManager() {
        if (loginManager == null) {
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    @Override
    public String execute() {
        getLoginManager().logout();
        return SUCCESS;
    }
}