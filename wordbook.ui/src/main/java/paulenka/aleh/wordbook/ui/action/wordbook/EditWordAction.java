package paulenka.aleh.wordbook.ui.action.wordbook;

import paulenka.aleh.wordbook.data.Role;
import paulenka.aleh.wordbook.ui.action.common.ProcessFormAction;
import paulenka.aleh.wordbook.ui.interceptor.security.Authorization;

@Authorization(roles = { Role.MODERATOR })
public class EditWordAction extends ProcessFormAction {

    private static final long serialVersionUID = 1L;

    @Override
    public String view() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String process() {
        // TODO Auto-generated method stub
        return null;
    }
}