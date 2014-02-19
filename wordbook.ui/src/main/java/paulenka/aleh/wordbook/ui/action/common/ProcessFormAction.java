package paulenka.aleh.wordbook.ui.action.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import paulenka.aleh.wordbook.ui.constant.HttpRequestMethod;

import com.opensymphony.xwork2.ActionSupport;

public abstract class ProcessFormAction extends ActionSupport implements ServletRequestAware {

    private static final long serialVersionUID = 1L;

    private HttpServletRequest servletRequest;

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public boolean isFormPresent() {
        return HttpRequestMethod.POST.equalsIgnoreCase(getServletRequest().getMethod());
    }

    @Override
    public String execute() throws Exception {
        String result = INPUT;
        if (isFormPresent()) {
            result = process();
        }
        if (INPUT.equalsIgnoreCase(result)) {
            result = view();
        }
        return result;
    }

    @Override
    public void validate() {
        if (!(getActionErrors().isEmpty() && getFieldErrors().isEmpty())) {
            view();
        }
    }

    public abstract String view();

    public abstract String process();
}