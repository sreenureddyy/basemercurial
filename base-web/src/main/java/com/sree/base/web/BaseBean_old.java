/*package com.sree.base.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.richfaces.model.UploadItem;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.jsf.FacesContextUtils;

import com.srit.erp.common.util.CommonUtils;
import com.srit.erp.common.util.DateUtil;
import com.srit.erp.common.util.StartupProperties;
import com.srit.erp.core.LookupConstants;
import com.srit.erp.core.service.IBaseApplicationService;
import com.srit.healthcare.security.domain.SecurityConstants;

public class BaseBean_old {

	public static final String jstlBundleParam = "javax.servlet.jsp.jstl.fmt.localizationContext";
	protected static final Logger log = Logger.getLogger(BaseBean_old.class);
	// Once initialized throwing Exception: FacesContext already released
	// protected FacesContext facesContext = FacesContext.getCurrentInstance();
	protected transient Application application = null;

	private String richFacesSkin = "classic";

	@Resource(name = "baseApplicationService")
	private transient IBaseApplicationService baseAppService;

	// @Resource(name="helpPagePropertiesReader")
	// private DefaultPropertiesReader helpPropertiesReader;

	@Resource(name = "helpProperties")
	private Properties helpProperties;

	private transient List<SelectItem> docStatusList;

	private transient List<SelectItem> apprStatusList;

	private transient List<SelectItem> booleanSearchList;

	private transient List<SelectItem> activeInactiveList;

	// ---------- I18n Message Key used when any exception is thrown
	protected static final String KEY_ERROR_DETAIL = "errors.detail";
	protected static final String KEY_COMMON_SUCCESS = "common.success";
	protected static final String KEY_INVALID_DATAACCESS_EXCEPTION = "errors.invalid.dataaccess";

	public static final String USER_PREFERENCES = "USER_PREFERENCES_IN_SESSION";

	protected static final int RECORDS_PER_PAGE = 10; // CommonUtils.getLoggedInUserPreferences();

	public double AMOUNT_MINVALUE = 0.0;
	public double AMOUNT_MAXVALUE = 999999999.99;

	private List userFeatures;
	private List formTemplets = new ArrayList();
	private List userImages;
	private String selectedMenu;

	private String viewId;

	private String helpUrl;

	// @Resource(name="fileupload")
	private String uploadFolder;

	@Resource(name = "startupProperties")
	private StartupProperties startupProperties;

	
	 * public void setFacesContext(FacesContext facesContext) {
	 * this.facesContext = facesContext; }
	 

	
	 * public PhaseId getJSFLifeCyclePhase() { return
	 * (PhaseId)getRequest().getAttribute("JSF_BEFORE_PHASE"); }
	 

	public String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	public String getBundleName() {
		// get name of resource bundle from JSTL settings, JSF makes this too
		// hard
		return getServletContext().getInitParameter(jstlBundleParam);
	}

	
	 * public void setBuncle(ResourceBundle bundle) { System.out.println(" the
	 * bundle object............................." + bundle); }
	 

	public ResourceBundle getBundle() {
		return ResourceBundle.getBundle(getBundleName(), getRequest()
				.getLocale());
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

	*//**
	 * Convenience method for unit tests.
	 * 
	 * @return
	 *//*
	public boolean hasErrors() {
		return (getSession().getAttribute("errors") != null);
	}

	*//**
	 * Servlet API Convenience method
	 * 
	 * @return
	 *//*
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	*//**
	 * Servlet API Convenience method
	 * 
	 * @return
	 *//*
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	*//**
	 * Servlet API Convenience method
	 * 
	 * @return
	 *//*
	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
	}

	*//**
	 * Servlet API Convenience method
	 * 
	 * @return
	 *//*
	protected ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
	}

	*//**
	 * Method for getting the list of <code>SelectItem</code> objects.
	 * 
	 * @param selectItemList
	 *            the selectItemList, list obtained from hibernate
	 * @return list the list, list of SelectItem objects
	 *//*
	public List<SelectItem> populate(List selectItemList) {
		if (selectItemList == null) {
			throw new IllegalArgumentException(
					"selectItemList should not be null");
		}
		List<SelectItem> selectItem = new ArrayList<SelectItem>();
		selectItem.add(new SelectItem("", getText("dropdown.select")));
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
		selectItem.add(new SelectItem("", getText("dropdown.select")));
		Object[] object = null;
		for (int i = 0; i < selectItemList.size(); i++) {
			object = (Object[]) selectItemList.get(i);
			selectItem.add(new SelectItem(object[0],
					getText((String) object[1])));
		}
		return selectItem;
	}

	public String getRichFacesSkin() {
		return richFacesSkin;
	}

	public void setRichFacesSkin(String richFacesSkin) {
		this.richFacesSkin = richFacesSkin;
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
			// e.printStackTrace();
		}

		return null;
	}

	public Object getManagedBean(String beanId) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(beanId);
	}

	public IBaseApplicationService getBaseAppService() {
		return baseAppService;
	}

	public void setBaseAppService(IBaseApplicationService baseAppService) {
		this.baseAppService = baseAppService;
	}

	public List<SelectItem> getDocStatusList() {
		if (docStatusList == null) {
			docStatusList = new ArrayList<SelectItem>();
			docStatusList.add(new SelectItem("", getText("dropdown.select")));
			docStatusList.add(new SelectItem(Integer.valueOf(4),
					getText("status.document.4")));
			docStatusList.add(new SelectItem(Integer.valueOf(2),
					getText("status.document.2")));
			docStatusList.add(new SelectItem(Integer.valueOf(5),
					getText("status.document.5")));
			docStatusList.add(new SelectItem(Integer.valueOf(1),
					getText("status.document.1")));
		}
		return docStatusList;
	}

	public void setDocStatusList(List<SelectItem> docStatusList) {
		this.docStatusList = docStatusList;
	}

	public List<SelectItem> getApprStatusList() {
		if (apprStatusList == null) {
			apprStatusList = new ArrayList<SelectItem>();
			apprStatusList.add(new SelectItem("", getText("dropdown.select")));
			apprStatusList.add(new SelectItem(Integer.valueOf(0),
					getText("status.approval.0")));
			apprStatusList.add(new SelectItem(Integer.valueOf(2),
					getText("status.approval.2")));
			apprStatusList.add(new SelectItem(Integer.valueOf(1),
					getText("status.approval.1")));
			apprStatusList.add(new SelectItem(Integer.valueOf(3),
					getText("status.approval.3")));
		}
		return apprStatusList;
	}

	public List<SelectItem> getBooleanSearchList() {
		if (booleanSearchList == null) {
			booleanSearchList = new ArrayList<SelectItem>();
			booleanSearchList
					.add(new SelectItem("", getText("dropdown.select")));
			booleanSearchList.add(new SelectItem("",
					getText("status.boolean.all")));
			booleanSearchList.add(new SelectItem(new Boolean(true),
					getText("status.boolean.true")));
			booleanSearchList.add(new SelectItem(new Boolean(false),
					getText("status.boolean.false")));
		}
		return booleanSearchList;
	}

	public List<SelectItem> getActiveStatusList() {
		if (apprStatusList == null) {
			apprStatusList = new ArrayList<SelectItem>();
			apprStatusList.add(new SelectItem("", getText("dropdown.select")));
			apprStatusList.add(new SelectItem(new Boolean(true), "Active"));
			apprStatusList.add(new SelectItem(new Boolean(false), "InActive"));
		}
		return apprStatusList;
	}

	public void setBooleanSearchList(List<SelectItem> booleanSearchList) {
		this.booleanSearchList = booleanSearchList;
	}

	public void setApprStatusList(List<SelectItem> apprStatusList) {
		this.apprStatusList = apprStatusList;
	}

	protected UIComponent getUIComponentById(final String compoentId) {
		return FacesContext.getCurrentInstance().getViewRoot().findComponent(
				compoentId);
	}

	public boolean getAreGlobalMessagesPresent() {
		Iterator<?> it = FacesContext.getCurrentInstance().getMessages(null);
		return it.hasNext();
	}

	public List<FacesMessage> getGlobalMessages() {
		List<FacesMessage> messages = new ArrayList<FacesMessage>();
		if (messages.isEmpty()) {
			Iterator<FacesMessage> it = FacesContext.getCurrentInstance()
					.getMessages(null);
			FacesMessage _msg = null;
			while (it.hasNext()) {
				_msg = it.next();
				messages.add(_msg);
			}
		}
		return messages;
	}

	public List<SelectItem> getActiveInactiveList() {
		if (activeInactiveList == null) {
			activeInactiveList = new ArrayList<SelectItem>();
			activeInactiveList.add(new SelectItem("",
					getText("dropdown.select")));
			activeInactiveList.add(new SelectItem(1,
					getText("status.boolean.true")));
			activeInactiveList.add(new SelectItem(0,
					getText("status.boolean.false")));
		}
		return activeInactiveList;
	}

	public void setActiveInactiveList(List<SelectItem> activeInactiveList) {
		this.activeInactiveList = activeInactiveList;
	}

	public static void uploadFile(UploadItem fileItem, String fileName)
			throws FileNotFoundException, IOException {
		log.debug(" fileItem " + fileItem + " fileName -->   " + fileName);
		InputStream fis = new FileInputStream(fileItem.getFile());
		FileOutputStream fos = new FileOutputStream(fileName);
		int i = 0;
		while ((i = fis.read()) != -1) {
			fos.write(i);
		}
		fos.close();
		fis.close();
	}

	public void downloadFile(String filePath, String fileName)
			throws IOException {
		int read = 0;
		byte[] bytes = new byte[1024];
		File file = new File(filePath + fileName);
		getResponse().setHeader("Content-Disposition",
				"attachment;filename=\"" + fileName + "\"");
		FileInputStream fis = new FileInputStream(file);
		OutputStream os = getResponse().getOutputStream();
		while ((read = fis.read(bytes)) != -1) {
			os.write(bytes, 0, read);
		}
		os.flush();
		os.close();
		FacesContext.getCurrentInstance().responseComplete();
	}

	public String getDocumentStatusLabel(Object documentStatus) {
		for (Iterator<?> iterator = getDocStatusList().iterator(); iterator
				.hasNext();) {
			SelectItem selectItem = (SelectItem) iterator.next();
			if (selectItem.getValue().equals(documentStatus)) {
				return selectItem.getLabel();
			}
		}
		return "";
	}

	public String getApprovalStatusLabel(Object approvalStatus) {
		for (Iterator<?> iterator = getApprStatusList().iterator(); iterator
				.hasNext();) {
			SelectItem selectItem = (SelectItem) iterator.next();
			if (selectItem.getValue().equals(approvalStatus)) {
				return selectItem.getLabel();
			}
		}
		return "";
	}

	public String getActiveStatusLabel(Object activeStatus) {
		if (activeStatus instanceof Boolean) {
			for (Iterator<?> iterator = getActiveStatusList().iterator(); iterator
					.hasNext();) {
				SelectItem selectItem = (SelectItem) iterator.next();
				if (selectItem.getValue().equals(activeStatus)) {
					return selectItem.getLabel();
				}
			}
			return "";
		} else if (activeStatus instanceof Integer) {
			if (activeStatus.equals(1))
				return "Active";
			else if (activeStatus.equals(0))
				return "InActive";
			return "";
		}
		return "";
	}

	public double getAMOUNT_MINVALUE() {
		return AMOUNT_MINVALUE;
	}

	public void setAMOUNT_MINVALUE(double amount_minvalue) {
		AMOUNT_MINVALUE = amount_minvalue;
	}

	public double getAMOUNT_MAXVALUE() {
		return AMOUNT_MAXVALUE;
	}

	public void setAMOUNT_MAXVALUE(double amount_maxvalue) {
		AMOUNT_MAXVALUE = amount_maxvalue;
	}

	public void validateIsBeforeCurrentDate(FacesContext context,
			UIComponent toValidate, Object value) {
		UIInput dateComponent = ((UIInput) toValidate);
		String dateLabel = (String) dateComponent.getAttributes().get(
				"dateLabel");
		if (value != null) {
			Date date = (Date) value;
			if (DateUtil.isBeforeCurrentDate(date)) {
				dateComponent.setValid(false);
				addMessage("errors.datebefore",
						new String[] { getText(dateLabel) }, toValidate
								.getClientId(context));
			}
		}
	}

	public void validateIsAfterCurrentDate(FacesContext context,
			UIComponent toValidate, Object value) {
		UIInput dateComponent = ((UIInput) toValidate);
		String dateLabel = (String) dateComponent.getAttributes().get(
				"dateLabel");
		if (value != null) {
			Date date = (Date) value;
			if (DateUtil.isAfterCurrentDate(date)) {
				dateComponent.setValid(false);
				addMessage("errors.dateafter",
						new String[] { getText(dateLabel) }, toValidate
								.getClientId(context));
			}
		}
	}

	public void validateForZeroQunatity(FacesContext context,
			UIComponent toValidate, Object value) {
		UIInput component = ((UIInput) toValidate);
		String itemCode = (String) component.getAttributes().get("itemcode");
		if (value != null && Float.valueOf(value.toString()) <= 0) {
			component.setValid(false);
			addMessage("errors.quantity.morethan.zero", itemCode, toValidate
					.getClientId(context));
		}
	}

	public void validateForZeroRate(FacesContext context,
			UIComponent toValidate, Object value) {
		UIInput component = ((UIInput) toValidate);
		String itemCode = (String) component.getAttributes().get("itemcode");
		if (value != null && Float.valueOf(value.toString()) <= 0) {
			component.setValid(false);
			addMessage("errors.rate.morethan.zero", itemCode, toValidate
					.getClientId(context));
		}
	}

	public String getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(String selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

	public String loadLadingPage() {
		this.selectedMenu = getParameter("leftSiderBarForm:moduleMenu");
		return selectedMenu + "LandingPage";
	}

	public String getViewId() {
		viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public String getHelpUrl() {
		String helpfolder = helpProperties.getProperty("helpfolder");
		String currentPage = null;
		String context = getApplicationURI();
		viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		String[] viewSplit = viewId.split("/");
		if (viewSplit != null && viewSplit.length > 0) {
			currentPage = viewSplit[viewSplit.length - 1];
			currentPage = currentPage.substring(0, currentPage.length() - 6); // to
			// avoid
			// .xhtml
		}
		if (helpProperties.getProperty(currentPage) != null) {
			helpUrl = context + helpfolder + "/"
					+ helpProperties.getProperty(currentPage);
		} else {
			helpUrl = context + helpProperties.getProperty("helpIndexFile");
		}
		return helpUrl;
	}

	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}

	private String getApplicationURI() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String pathPrefix = getRequest().getScheme() + "://"
				+ getRequest().getServerName() + ":"
				+ getRequest().getServerPort();
		String context = pathPrefix + externalContext.getRequestContextPath();
		return context;
	}

	public boolean isWorkflowExists() {
		return Boolean.valueOf(getText("workflow.exists").trim());
	}

	public List getUserFeatures() {
		if (userFeatures == null) {
			userImages = getBaseAppService().find(
					SecurityConstants.GETFEATURESFORUSER,
					new Object[] { CommonUtils.getLoggedInHisUser().getId() });
			userFeatures = new ArrayList();
			userFeatures.addAll(userImages);
			userFeatures = populate(userFeatures);
		}
		return userFeatures;

	}

	public void setUserFeatures(List userFeatures) {
		this.userFeatures = userFeatures;
	}

	public List getUserImages() {
		return userImages;
	}

	public void setUserImages(List userImages) {
		this.userImages = userImages;
	}

	public List<String> populateFromResourceBundle(
			String[] resourceBundleKeyList) {
		List<String> resourceBundleList = new ArrayList<String>();
		if (resourceBundleKeyList != null) {
			for (int i = 0; i < resourceBundleKeyList.length; i++) {
				resourceBundleList.add(getText(resourceBundleKeyList[i]));
			}
		}
		return resourceBundleList;
	}

	public void getUploadPath() {
		System.out.println(" uploadFolder from JNDI... " + uploadFolder);
	}

	
	 * public List getFormTemplets() { if (getSelectedMenu()!=null &&
	 * getSelectedMenu().trim().equals("formtemplates")) { formTemplets =
	 * getBaseAppService().find("formtemplet.list", new
	 * Object[]{getSelectedMenu()}); } return formTemplets; }
	 
	public List getFormTemplets() {
		if (getSelectedMenu() != null && getSelectedMenu().trim().length() > 0)
			formTemplets = getBaseAppService().find("formtemplet.list",
					new Object[] { getSelectedMenu() });
		else
			formTemplets = new ArrayList();
		return formTemplets;
	}

	public void setFormTemplets(List formTemplets) {
		this.formTemplets = formTemplets;
	}

	public StartupProperties getStartupProperties() {
		return startupProperties;
	}

	public void setStartupProperties(StartupProperties startupProperties) {
		this.startupProperties = startupProperties;
	}

	public String getExcelExportTempaltePath() {
		return getServletContext().getRealPath("/")
				+ LookupConstants.EXCEL_TEMPLATE_PATH;
	}

}
*/