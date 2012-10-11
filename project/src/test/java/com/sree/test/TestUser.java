/**
 * 
 */
package com.sree.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sree.base.domain.Address;
import com.sree.base.domain.ContactDetails;
import com.sree.base.domain.Country;
import com.sree.base.domain.LookupValue;
import com.sree.base.domain.State;
import com.sree.base.domain.User;
import com.sree.base.domain.UserAuthority;
import com.sree.common.utils.CommonUtil;

/**
 * @author srinivasr
 * 
 */
@SuppressWarnings("unchecked")
public class TestUser extends BaseTest { 
	private Logger log = Logger.getLogger(TestUser.class);
 
	@Test
	public void testCase() {
		List<String> usernames = baseService.find("getusers");
		log.info(usernames.size());
		log.info(usernames.get(0));
		
		User user = new User();
		user.setAccountNonExpired(false);
		user.setAccountNonLocked(false);
		user.setCreatedBy("admin");
		user.setCreatedDatetime(new Date());
		user.setCredentialsNonExpired(false);
		user.setDob(new Date());
		user.setEnabled(true);
		user.setFirstname("sree");
		user.setGender(1l);
		user.setLastname("reddy");
		user.setPassword(CommonUtil.encript("sree"));
		LookupValue lookupValue = (LookupValue)baseService.find(LookupValue.class, 1l);
		//user.setUserType(lookupValue);
		
		Address address = new Address();
		address.setAddressType(lookupValue);
		address.setCityTown("Bangalore");
		Country country = (Country)baseService.find(Country.class, 1l);
		address.setCountry(country);
		address.setCreatedBy("admin");
		address.setHouseNo("#86");
		address.setIsPrimary(true);
		address.setLandmark("Rammaiah Hosp");
		address.setPinCode("560054");
		address.setCreatedBy("sree");
		address.setCreatedDatetime(new Date());
		State state = (State)baseService.find(State.class, 1l);
		address.setState(state);
		address.setStreet("MSR Nager");
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		user.setAddress(addresses);
		ContactDetails contactDetails = new ContactDetails();
		contactDetails.setContact("9886919190");
		contactDetails.setContactType(lookupValue);
		contactDetails.setIsPrimaryContact(true);
		List<ContactDetails> contactDetailsList = new ArrayList<ContactDetails>();
		contactDetailsList.add(contactDetails);
		user.setContactDetails(contactDetailsList);
		
		UserAuthority authority = (UserAuthority) baseService.find(UserAuthority.class, 1l);
		List<UserAuthority> userAuthorityList = new ArrayList<UserAuthority>();
		userAuthorityList.add(authority);
		user.setUserAuthorities(userAuthorityList);
		user.setUsername("reddy");
		baseService.save(user);
		
	}
}
