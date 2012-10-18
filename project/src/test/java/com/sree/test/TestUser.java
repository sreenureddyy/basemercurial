/**
 * 
 */
package com.sree.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sree.base.domain.Address;
import com.sree.base.domain.Authority;
import com.sree.base.domain.LookupValue;
import com.sree.base.domain.State;
import com.sree.base.domain.User;
import com.sree.common.utils.CommonUtil;
import com.sree.service.user.IUserService;

/**
 * @author sree
 * 
 */
public class TestUser extends BaseTest {
	private Logger log = Logger.getLogger(TestUser.class);

	@Autowired
	IUserService userService;
	
	@Test
	public void testCreateUser(){
		
		State state = (State)baseService.find(State.class, 1l);
		
		LookupValue lookupValue = (LookupValue)baseService.find(LookupValue.class, 2l);
		
		Address address = new Address();
		address.setAddressType(lookupValue);
		address.setHouseNo("25/37");
		address.setStreet("Sanjeeva Nagar");
		address.setLandmark("Ramalayam");
		address.setCityTown("Nandyal");
		address.setPinCode("518501");
		address.setIsPrimary(true);
		address.setCountry(state.getCountry());
		address.setState(state);
		address.setCreatedBy("Sree");
		address.setCreatedDatetime(new Date());
		
		Authority authority = (Authority)baseService.find(Authority.class, 1l);
		
		lookupValue = (LookupValue)baseService.find(LookupValue.class, 1l);
		
		User user = new User();
		user.setEmail("sreenureddy.y@gmail.com");
		user.setPassword(CommonUtil.encript("sree"));
		user.setDob(new Date());
		user.setFirstname("Sreenivasa");
		user.setLastname("Reddy");
		user.setGender(1l);
		user.setMobile("98699190");
		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setUserType(lookupValue);
		
		List<Address> listAddress = new ArrayList<Address>(1);
		listAddress.add(address);
		
		List<Authority> listAuthority = new ArrayList<Authority>(1);
		listAuthority.add(authority);
		
		user.setAddress(listAddress);
		user.setUserAuthorities(listAuthority);
		user.setCreatedBy("Sree");
		user.setCreatedDatetime(new Date());
		
		userService.saveUser(user);
		
	}
	
	@Test
	public void testGetAllUsers() {
		List<User> users = userService.find("getAllUsers");
		log.info(users.size());
		for (User user : users) {
			log.info(" Username is :: " + user.getUsername());
		}
	}
	
}
