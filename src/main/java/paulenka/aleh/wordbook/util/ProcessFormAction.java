package paulenka.aleh.wordbook.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import paulenka.aleh.wordbook.constant.HttpRequestMethod;

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

	public abstract String process() throws Exception;

	@Override
	public String execute() throws Exception {
		if (HttpRequestMethod.POST.equalsIgnoreCase(getServletRequest().getMethod())) {
			return process();
		} else {
			return INPUT;
		}
	}

	@Override
	public void validate() {
		super.validate();
	}
}