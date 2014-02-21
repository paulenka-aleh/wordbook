package paulenka.aleh.wordbook.ui.action.user;

import java.sql.SQLException;

import paulenka.aleh.wordbook.data.Credentials;
import paulenka.aleh.wordbook.ui.action.common.ProcessFormAction;
import paulenka.aleh.wordbook.ui.interceptor.back.BackResultAction;
import paulenka.aleh.wordbook.ui.login.LoginManager;

@BackResultAction
public class LoginUserAction extends ProcessFormAction {

    private static final long serialVersionUID = 1L;

    private LoginManager loginManager;

    private Credentials credentials;

    protected LoginManager getLoginManager() {
        if (loginManager == null) {
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public String execute() throws Exception {
        if (getLoginManager().isLoggedIn()) {
            return SUCCESS;
        }
        return super.execute();
    }

    @Override
    public String view() {
        return INPUT;
    }

    @Override
    public String process() {
        if (getCredentials() == null || (getCredentials().getUsername() == null || getCredentials().getUsername().isEmpty())
                && (getCredentials().getPassword() == null || getCredentials().getPassword().isEmpty())) {
            return INPUT;
        }
        try {
            if (getLoginManager().login(getCredentials())) {
                return SUCCESS;
            } else {
                addActionError(getText("sign-in-form.error"));
                return INPUT;
            }
        } catch (SQLException ex) {
            return ERROR;
        }
    }
}