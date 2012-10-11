/*package com.srit.erp.inv.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Table;
import com.srit.erp.common.service.ISimpleProfileApplicationService;
import com.srit.erp.common.util.CommonUtils;
import com.srit.erp.common.util.ExportToPdfUtil;
import com.srit.erp.common.util.ExportUtil;
import com.srit.erp.core.SystemConfiguration;
import com.srit.erp.core.SystemConstants;
import com.srit.erp.core.domain.SearchCriteriaDetail;
import com.srit.erp.core.exc.DomainValidationException;
import com.srit.erp.core.service.IBaseApplicationService;
import com.srit.erp.core.ui.DataPage;
import com.srit.erp.core.ui.PagedListDataModel;
import com.srit.erp.core.ui.SearchUtil;
import com.srit.erp.inv.GlobalInvConfiguration;
import com.srit.erp.inv.InvConfigReorderDetail;
import com.srit.erp.inv.InventoryConfiguration;
import com.srit.erp.inv.Store;


@Component(value="inventoryConfigurationBean")
@Scope(value="request")
public class InventoryConfigurationBean extends InventoryBaseBean {

	@Autowired
	private ISimpleProfileApplicationService simpleProfileService;

	
	public InventoryConfiguration inventoryConfiguration = new InventoryConfiguration();
	private InvConfigReorderDetail reorderDetail;
	private DataModel icListData;
	private SearchUtil searchUtil;
	private String[] columnHeaders = {"invconfig.branch", "invconfig.isdistcateapplicable", "invconfig.store", 
			"invconfig.reorderperiod", "invconfig.defaultpricingmethod"};
	private List<SelectItem> transitStoreList;
	private List<SelectItem> branchList;//branch.combo
	private List<SelectItem> defaultPricingMethodList;//DEFAULT_PRICING_METHOD_TYPE
	private List<SelectItem> storeList;
	private List<SelectItem> storeTypeList;
	
	private Long invConfigId;
	private boolean editMode;
	private Boolean editDetailMode = new Boolean(false);
	private boolean saveAllowed = true;
	private static final String FORM_PAGE = "formpage";//inventoryconfigform
	private static final String LIST_PAGE = "listpage";//inventoryconfigs
	private static final String GLOBAL_FORM_PAGE = "globalinventoryconfigform";
	
	private GlobalInvConfiguration globlInvConfiguration;
	
	private static final Logger logger = Logger.getLogger(InventoryConfigurationBean.class);


	public SearchUtil getSearchUtil() {
		if (searchUtil == null) {
			searchUtil = new SearchUtil() {

				private static final long serialVersionUID = 1L;

				@Override
				public void populateSearchColumns() {
					List<String[]> searchColumns = new ArrayList<String[]>();
					searchColumns.add(new String[] { "ic.branch.code",
							columnHeaders[0], "string" });
					searchColumns.add(new String[] { "ic.isDistCateApplicable",
							columnHeaders[1], "numericDropDown" });//storagecondition
					searchColumns.add(new String[] { "ic.defaultStore.storeCode",
							columnHeaders[2], "string" });
					searchColumns.add(new String[] { "ic.reOrderPeriod",
							columnHeaders[3], "number" });
					searchColumns.add(new String[] { "ic.defaultPricingMethod",
							columnHeaders[4], "numericDropDown" });
					this.setColumns(populateLabelsfromResourceBundle(searchColumns));
					this.populateColumnTypes(searchColumns);
				}

				@Override
				public void doAfterSearchComplete() {
					icListData = null;
				}

				@Override
				public String getFeatureName() {
					return "InventoryConfiguration";
				}

				@Override
				public IBaseApplicationService getBaseApplicationService() {
					return getBaseAppService();
				}
			};
		}
		return searchUtil;
	}	

	public void setSearchUtil(SearchUtil searchUtil) {
		this.searchUtil = searchUtil;
	}
	
	public DataModel getIcListData() {
		if(icListData==null){
			icListData=new PagedListDataModel(10){
				@Override
				public DataPage fetchPage(int startRow, int pageSize) {					
					return fetchData(startRow, pageSize);				
				}
			};
		}
		return icListData;
	}

	public void setIcListData(DataModel icListData) {
		this.icListData = icListData;
	}
	
	@SuppressWarnings("unchecked")
	private DataPage fetchData(int startRow, int pageSize) {
		int totalRows = getBaseAppService().countforSearch(
				"InventoryConfiguration.listing.count", null,
				searchUtil.getCurrentSearch());
		List list = getBaseAppService().search("InventoryConfiguration.listing", null,
				searchUtil.getCurrentSearch(), searchUtil.getSortExpression(),
				startRow, pageSize != -1 ? pageSize : totalRows);
		return new DataPage(totalRows, startRow, list);
	}
	
	public void exportToExcel() {
		SystemConfiguration systemConfig = (SystemConfiguration)getSpringBean("systemConfig");
		DataPage dataPage = fetchData(0, -1);
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			for (int i = 0; i < dataPage.getData().size(); i++) {
				Object[] obj = (Object[]) dataPage.getData().get(i);
//				obj[2] = getDistCateApplicableLabel(obj[2]);
				obj[5] = getDefaultPricingMethodLabel(obj[5]);
			}
			data.put("result", dataPage.getData());
			data.put("searchCriterias", getSearchCriteriaforExport());
			ExportUtil.exporttoExcel(systemConfig.getExcelTemplatePath()+"inventoryconfigurations.xls", data);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void exportToPdf() {
		DataPage dataPage = fetchData(0, -1);
		List<String> columnHeaderList = new ArrayList<String>();
		for (int i = 0; i < columnHeaders.length; i++) {
			if (i != 1 && i != 3) {
				columnHeaderList.add(getText(columnHeaders[i]));
			}
		}
		try {
			ExportToPdfUtil pdfExporter = new ExportToPdfUtil() {
				@Override
				public void populateRow(Table table, Object rowData) throws BadElementException {
					Object[] data = (Object[]) rowData;
					table.addCell(ExportToPdfUtil.getCell(data[1] == null ? "" : data[1].toString()));
//					table.addCell(ExportToPdfUtil.getCell(data[2] == null ? "" : getDistCateApplicableLabel(data[2])));
					table.addCell(ExportToPdfUtil.getCell(data[3] == null ? "" : data[3].toString()));
//					table.addCell(ExportToPdfUtil.getCell(data[4] == null ? "" : data[4].toString()));
					table.addCell(ExportToPdfUtil.getCell(data[5] == null ? "" : getDefaultPricingMethodLabel(data[5])));
				}
			};
			int[] columnWidths={150,150,150};
			pdfExporter.setColumnWidth(columnWidths);
			List<Object[]> searchCriteria = getSearchCriteriaforExport();
			pdfExporter.exporttoPDF(getText("invconfig.invconfig"), searchCriteria, dataPage.getData(), 
					columnHeaderList.toArray(new String[3]));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private List<Object[]> getSearchCriteriaforExport() {
		List<Object[]> searchCriteria = new ArrayList<Object[]>();
		
		for (Iterator<?> iterator = searchUtil.getCurrentSearch().iterator(); iterator
				.hasNext();) {
			SearchCriteriaDetail _scd = (SearchCriteriaDetail) iterator.next();
			if (_scd.getPropertyName() == null || _scd.getPropertyName().trim().length() == 0)
				continue;
			Object[] objects = new Object[3];
			for (Iterator<?> iterator2 = searchUtil.getColumns().iterator(); iterator2
					.hasNext();) {
				SelectItem _option = (SelectItem) iterator2.next();
				if (_option.getValue().equals(_scd.getPropertyName())) {
					objects[0] = _option.getLabel();
					break;
				}
			}
			objects[1] = _scd.getCondition();
			if (_scd.getPropertyName().equals("ic.isDistCateApplicable")) {
				objects[2] = getDistCateApplicableLabel(Integer.valueOf(_scd.getEnteredValue()));
			} else if (_scd.getPropertyName().equals("ic.defaultPricingMethod")) {
				objects[2] = getDefaultPricingMethodLabel(Integer.valueOf(_scd.getEnteredValue()));
			} else {
				objects[2] = _scd.getEnteredValue();
			}
			searchCriteria.add(objects);
		}
		return searchCriteria;
	}
	
	public String getDistCateApplicableLabel(Object activeStatus) {
		if(activeStatus.equals(1) || activeStatus.equals(true))
			return "Yes";
		else if(activeStatus.equals(0) || activeStatus.equals(false))
			return "No";
		return "";
	}
	
	public String getDefaultPricingMethodLabel(Object type) {
		for (Iterator<?> iterator = SystemConstants.DEFAULT_PRICING_METHOD_TYPE.getEnumConstants().iterator(); 
			iterator.hasNext();) {
			Object[] value = (Object[]) iterator.next();
			if (value[0].equals(type)) {
				return value[1].toString();
			}
		}
		return "";
	}
	
	public String createNewInventoryConfig() {
		inventoryConfiguration = new InventoryConfiguration();
		inventoryConfiguration.setBranch(CommonUtils.getLoggedInUserPreferences().getBranchObject());
		return FORM_PAGE;
	}

	public String editInventoryConfig() {
		editMode = true;
		inventoryConfiguration = (InventoryConfiguration) simpleProfileService.loadUsingFetchJoin
			(InventoryConfiguration.class, new Object[]{"reorderDetails"}, invConfigId);
		return FORM_PAGE;
	}
	
	public String editGlobleInventoryConfig() {
		editMode = true;
		List<?> list = simpleProfileService.find(GlobalInvConfiguration.class);
		if(list.size() > 0){
			globlInvConfiguration = (GlobalInvConfiguration) list.get(0);
			Store st = (Store)getBaseAppService().find(Store.class,globlInvConfiguration.getSterileStore().getId());
			globlInvConfiguration.setSterileStore(createStoreObject(st));
			Store st1 = (Store)getBaseAppService().find(Store.class,globlInvConfiguration.getLinenStore().getId());
			globlInvConfiguration.setLinenStore(createStoreObject(st1));
			Store st2 = (Store)getBaseAppService().find(Store.class,globlInvConfiguration.getLaundryStore().getId());
			globlInvConfiguration.setLaundryStore(createStoreObject(st2));
		}
		else{
			globlInvConfiguration = new GlobalInvConfiguration();
			Store stStore = new Store();
			globlInvConfiguration.setSterileStore(stStore);
			Store  liStore = new Store();
			globlInvConfiguration.setLinenStore(liStore);
			Store  luStore = new Store();
			globlInvConfiguration.setLaundryStore(luStore);
		}
		return GLOBAL_FORM_PAGE;
	}

	private Store createStoreObject(Store st) {
		Store store = new Store();
		store.setId(st.getId());
		store.setStoreCode(st.getStoreCode());
		store.setStoreDescription(st.getStoreDescription());
		return store;
	}
	
	public String saveGlobleInventoryConfig() {
		String returnPage = "appcontent";
		boolean isIsnert = globlInvConfiguration.getId() == null;
		try {
			if (isIsnert) {
				simpleProfileService.persist(globlInvConfiguration);
			} else {
				simpleProfileService.update(globlInvConfiguration);
			}
			addMessage("save.success", getText("globalinvconfig.globalinvconfig"));
		} catch (Exception e) {
			if (isIsnert) {
				globlInvConfiguration.setId(null);
			}
			logger.error(e.getMessage(), e);
			returnPage = GLOBAL_FORM_PAGE;
			addError("errors.detail", new Object[] { e.getMessage() });
		}
		return returnPage;
	}
	
	public String saveInventoryConfig() {
		String returnPage = LIST_PAGE;
		boolean isIsnert = inventoryConfiguration.getId() == null;
		try {
			if (isIsnert) {
				simpleProfileService.persist(inventoryConfiguration);
			} else {
				simpleProfileService.update(inventoryConfiguration);
			}
			addMessage("save.success", getText("invconfig.invconfig"));
		} catch (Exception e) {
			if (isIsnert) {
				inventoryConfiguration.setId(null);
			}
			logger.error(e.getMessage(), e);
			returnPage = FORM_PAGE;
			addError("errors.detail", new Object[] { e.getMessage() });
		}
		return returnPage;
	}

	public void validateCode() {
		int count = 0;
		saveAllowed = true;
		try{
			count = getBaseAppService().countforObjectArrayParams("InventoryConfiguration.branch.count",
						new Object[] {inventoryConfiguration.getBranch().getId()} );
			if ((count > 0)) {
				saveAllowed = false;
				addError("invconfig.duplicatecode",null,inventoryConfiguration.getBranch().getCode());
			}
		}catch (Exception e){
			saveAllowed = false;
			addError("errors.detail", e.getMessage(), inventoryConfiguration.getBranch().getCode());
		}
	}
	
	public void getStoreDetails(){
		Store store = (Store) getBaseAppService().find(Store.class, inventoryConfiguration.getDefaultStore().getId());
		inventoryConfiguration.setDefaultStore(store);
	}
	
	public void validateMaxIssueQuantity(FacesContext context, UIComponent toValidate, Object value) {
		UIInput issueQtyComponent = ((UIInput) toValidate);
		if (value != null) {
			Float issueQuantity = (Float) value;
			if (issueQuantity < 0 || issueQuantity > 100) {
				issueQtyComponent.setValid(false);
				addMessage("errors.nonegative", getText("invconfig.validatepersantage"), toValidate.getClientId(context));
				return;
			}
		}
	}
	
	public void createNewReorderDetail(){
		reorderDetail = new InvConfigReorderDetail();
	}
	
	public void editReorderDetail(ActionEvent event){
		int recordId = (Integer) event.getComponent().getAttributes().get("recordId");
		reorderDetail = inventoryConfiguration.getReorderDetail(recordId);
		editDetailMode = true;
	}
	
	public void cancelReorderDetail(){
		reorderDetail = null;
	}
	
	public void saveReorderDetail() {
		String message = "";
		try {
			if (!editDetailMode) {
				inventoryConfiguration.addReorderDetail(reorderDetail);
				message = "invconfig.detail.save.success";
			} else {
				message = "invconfig.detail.update.success";
			}
			addMessage(message);
			editDetailMode = false;
			reorderDetail = null;
		} catch (DomainValidationException dve) {
			addError("errors.detail", dve.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addError("errors.detail", new String[] { e.getMessage() });
		}
	}
	
	/////////////////////////////////////////Getter and Setter///////////////////////////////////////
	public List<SelectItem> getBranchList() {
		if(branchList == null)
			branchList =  populate(getBaseAppService().find("branch.combo.nonGroupBranch", null));
		return branchList;
	}

	public void setBranchList(List<SelectItem> branchList) {
		this.branchList = branchList;
	}

	public List<SelectItem> getDefaultPricingMethodList() {
		if (defaultPricingMethodList == null)
			defaultPricingMethodList = populate(SystemConstants.DEFAULT_PRICING_METHOD_TYPE.getEnumConstants());
		return defaultPricingMethodList;
	}

	public void setDefaultPricingMethodList(
			List<SelectItem> defaultPricingMethodList) {
		this.defaultPricingMethodList = defaultPricingMethodList;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public Long getInvConfigId() {
		return invConfigId;
	}

	public void setInvConfigId(Long invConfigId) {
		this.invConfigId = invConfigId;
	}

	public InventoryConfiguration getInventoryConfiguration() {
		return inventoryConfiguration;
	}

	public void setInventoryConfiguration(
			InventoryConfiguration inventoryConfiguration) {
		this.inventoryConfiguration = inventoryConfiguration;
	}

	public boolean isSaveAllowed() {
		return saveAllowed;
	}

	public List<SelectItem> getTransitStoreList() {
		if (transitStoreList == null) {
			List<?> stores= getBaseAppService().find("InventoryConfiguration.TransitStoreForBranchDept", null);
			this.transitStoreList = populate(stores);
		}
		return transitStoreList;
	}

	public void setTransitStoreList(List<SelectItem> transitStoreList) {
		this.transitStoreList = transitStoreList;
	}

	public void setSaveAllowed(boolean saveAllowed) {
		this.saveAllowed = saveAllowed;
	}

	public ISimpleProfileApplicationService getSimpleProfileService() {
		return simpleProfileService;
	}

	public void setSimpleProfileService(
			ISimpleProfileApplicationService simpleProfileService) {
		this.simpleProfileService = simpleProfileService;
	}
	
	public List<SelectItem> getStoreList() {
		if (storeList == null) {
			List<?> stores= getBaseAppService().find("StoreSuggestion.query", null);
			this.storeList = populate(stores);
		}
		return storeList;
	}
	
	public void setStoreList(List<SelectItem> storeList) {
		this.storeList = storeList;
	}

	public GlobalInvConfiguration getGloblInvConfiguration() {
		return globlInvConfiguration;
	}

	public void setGloblInvConfiguration(
			GlobalInvConfiguration globlInvConfiguration) {
		this.globlInvConfiguration = globlInvConfiguration;
	}
	
	public List<SelectItem> getStoreTypeList() {
		if (storeTypeList == null) {
			storeTypeList = populate(SystemConstants.STORE_TYPE.getEnumConstants());
			storeTypeList.remove(4);// remove the transit store type
		}
		return storeTypeList;
	}

	public void setStoreTypeList(List<SelectItem> storeTypeList) {
		this.storeTypeList = storeTypeList;
	}

	public Boolean getEditDetailMode() {
		return editDetailMode;
	}

	public void setEditDetailMode(Boolean editDetailMode) {
		this.editDetailMode = editDetailMode;
	}

	public InvConfigReorderDetail getReorderDetail() {
		return reorderDetail;
	}

	public void setReorderDetail(InvConfigReorderDetail reorderDetail) {
		this.reorderDetail = reorderDetail;
	}
	
	

}
*/