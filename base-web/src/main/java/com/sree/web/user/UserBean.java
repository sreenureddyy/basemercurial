/**
 * 
 */
package com.sree.web.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sree.base.domain.Address;
import com.sree.base.domain.User;
import com.sree.base.web.BaseBean;
import com.sree.common.constants.SystemConstants;
import com.sree.service.user.IUserService;

/**
 * @author sree
 * 
 */
@SuppressWarnings({"serial", "unchecked"})
@Component("userBean")
@Scope(value = "request")
public class UserBean extends BaseBean {

	private IUserService userService;

	private User user = new User();

	private Address address = new Address();

	private List<User> users;
	
	private List<SelectItem> genders;

	@Autowired
	public UserBean(IUserService userService) {
		this.userService = userService;
		
		users = new ArrayList<User>();
		
		populateUsers();
		populateGender();
	}

	public String saveUser() {

		user.setCreatedBy(getUsername());
		user.setCreatedDatetime(new Date());
		userService.saveUser(user);

		user = new User();

		return null;
	}
	
	public List<User> getUsers() {
		if (users.size() <= 0) {
			users = (List<User>) userService.getUser("findAllUsers", new Object[]{});
		}
		return users;
	}
	
	private void populateUsers() {
		users = (List<User>) userService.getUser("findAllUsers", new Object[] {});
	}
	
	private List<SelectItem> populateGender(){
		return genders = populate(SystemConstants.GENDER.getEnumConstants());
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<SelectItem> getGenders() {
		return genders;
	}

	public void setGenders(List<SelectItem> genders) {
		this.genders = genders;
	}

}
