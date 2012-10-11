/**
 * 
 */
package com.sree.base.web;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.jsf.FacesContextUtils;

/**
 * @author srinivasr
 * 
 */
@Component("baseBean")
@Scope(value = "request")
@SuppressWarnings({ "rawtypes", "serial" })
public class BaseBean implements Serializable {
	@SuppressWarnings("unused")
	private String string = new String();
	private Logger log = Logger.getLogger(BaseBean.class);
	public static final String jstlBundleParam = "javax.servlet.jsp.jstl.fmt.localizationContext";

	public String getBundleName() {
		return getServletContext().getInitParameter(jstlBundleParam);
	}

	public UserDetails getUser() {
		return (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
	}

	public String getUsername() {
		return getUser().getUsername();
	}

	public List<SelectItem> populate(List selectItemList) {
		if (selectItemList == null) {
			throw new IllegalArgumentException(
					"selectItemList should not be null");
		}
		List<SelectItem> selectItem = new ArrayList<SelectItem>();
		// selectItem.add(new SelectItem("", getText("dropdown.select")));
		Object[] object = null;
		for (int i = 0; i < selectItemList.size(); i++) {
			object = (Object[]) selectItemList.get(i);
			selectItem.add(new SelectItem(object[0], (String) object[1]));
		}
		return selectItem;
	}

	public List<SelectItem> populateLabelsfromResourceBundle(List selectItemList) {
		if (selectItemList == null) {
			throw new IllegalArgumentException(
					"selectItemList should not be null");
		}
		List<SelectItem> selectItem = new ArrayList<SelectItem>();
		// selectItem.add(new SelectItem("", getText("dropdown.select")));
		Object[] object = null;
		for (int i = 0; i < selectItemList.size(); i++) {
			object = (Object[]) selectItemList.get(i);
			selectItem.add(new SelectItem(object[0],
					getText((String) object[1])));
		}
		return selectItem;
	}

	public String getText(String key) {
		String message;
		try {
			message = getBundle().getString(key);
		} catch (java.util.MissingResourceException mre) {
			log.warn("Missing key for '" + key + "'");

			return "???" + key + "???";
		}
		return message;
	}

	public String getText(String key, Object arg) {
		if (arg == null) {
			return getBundle().getString(key);
		}
		MessageFormat form = new MessageFormat(getBundle().getString(key));
		if (arg instanceof String) {
			return form.format(new Object[] { arg });
		} else if (arg instanceof Object[]) {
			return form.format(arg);
		} else {
			log.error("arg '" + arg + "' not String or Object[]");

			return "";
		}
	}

	public ResourceBundle getBundle() {
		return ResourceBundle.getBundle(getBundleName(), FacesContext
				.getCurrentInstance().getViewRoot().getLocale());
	}

	private void addFaceMessage(String key, Object arg, String clientId,
			FacesMessage.Severity s) {
		FacesMessage msg = new FacesMessage(getText(key, arg));
		msg.setSeverity(s);
		FacesContext.getCurrentInstance().addMessage(clientId, msg);
	}

	protected void addMessage(String key, Object arg, String clientId) {
		addFaceMessage(key, arg, clientId, FacesMessage.SEVERITY_INFO);
	}

	protected void addMessage(String key, Object arg) {
		addMessage(key, arg, null);
	}

	protected void addMessage(String key) {
		addMessage(key, null);
	}

	protected void addError(String key, Object arg, String clientId) {
		addFaceMessage(key, arg, clientId, FacesMessage.SEVERITY_ERROR);
	}

	protected void addError(String key, Object arg) {
		addError(key, arg, null);
	}

	protected void addError(String key) {
		addError(key, null);
	}

	protected void addWarn(String key, Object arg, String clientId) {
		addFaceMessage(key, arg, clientId, FacesMessage.SEVERITY_WARN);
	}

	protected void addWarn(String key, Object arg) {
		addWarn(key, arg, null);
	}

	protected void addWarn(String key) {
		addWarn(key, null);
	}

	/**
	 * Convenience method for unit tests.
	 * 
	 * @return
	 */
	public boolean hasErrors() {
		return (getSession().getAttribute("errors") != null);
	}

	/**
	 * Servlet API Convenience method
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	/**
	 * Servlet API Convenience method
	 * 
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
	}

	/**
	 * Servlet API Convenience method
	 * 
	 * @return
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * Servlet API Convenience method
	 * 
	 * @return
	 */
	protected ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
	}

	public Object getSpringBean(String beanId) {
		Object appBean = null;
		Object webBean = null;
		Object facesBean = null;

		try {
			ApplicationContext appCtx = FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance());
			appBean = appCtx.getBean(beanId);
			return appBean;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			// e.printStackTrace();
		}

		try {
			WebApplicationContext ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServletContext());
			webBean = ctx.getBean(beanId);
			log.info("webBean--> " + webBean);
			return webBean;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			// e.printStackTrace();
		}

		try {
			facesBean = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestMap().get(beanId);
			log.info(" facesBean --> " + facesBean);
			return facesBean;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}

		return null;
	}

	public Object getManagedBean(String beanId) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(beanId);
	}

}
