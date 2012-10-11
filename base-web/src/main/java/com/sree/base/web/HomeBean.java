/**
 * 
 */
package com.sree.base.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sree.base.domain.User;
import com.sree.base.service.IBaseService;

/**
 * @author sree
 * 
 */
@Component("homeBean")
@Scope(value = "request")
public class HomeBean extends BaseBean {
	private List<User> userList = new ArrayList<User>();

	private IBaseService baseService;
	
	private String getBundleValue;
	
	public String getGetBundleValue() {
		return getBundleValue;
	}

	public void setGetBundleValue(String getBundleValue) {
		this.getBundleValue = getBundleValue;
	}

	@Autowired
	public HomeBean(IBaseService baseService) {
		this.baseService = baseService;
		userList = baseService.find("getAllUsers", new Object[] {});
	}

	public void doSomeThing() {
		getBundleValue = getText("welcome.jsf");
		System.out.println("Reading Bundle value ::::::: "+getBundleValue);
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
