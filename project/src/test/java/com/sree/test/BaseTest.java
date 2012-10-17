/**
 * 
 */
package com.sree.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sree.base.domain.Authority;
import com.sree.base.domain.Country;
import com.sree.base.domain.LookupCategory;
import com.sree.base.domain.LookupValue;
import com.sree.base.domain.State;
import com.sree.base.service.IBaseService;

/**
 * @author srinivasr
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/springContext-Test.xml",
		"/META-INF/spring/applicationContext-security.xml",
		"/META-INF/spring/applicationContext-spring.xml" })
public class BaseTest {
	private Logger log = Logger.getLogger(BaseTest.class);

	@Autowired
	IBaseService baseService;

	@Before
	public void testInit() {
		log.info("Initilization of test suit");
	}

	//@Test
	public void testCreateLookupCategory() {
		LookupCategory lookupCategory = new LookupCategory();
		lookupCategory.setIsActive(true);
		lookupCategory.setLookupCategory("USER_TYPE");
		lookupCategory.setDescription("System user type");
		lookupCategory.setCreatedBy("Sree");
		lookupCategory.setCreatedDatetime(new Date());
		baseService.save(lookupCategory);

		lookupCategory = new LookupCategory();
		lookupCategory.setIsActive(true);
		lookupCategory.setLookupCategory("ADDRESS_TYPE");
		lookupCategory.setDescription("Address type");
		lookupCategory.setCreatedBy("Sree");
		lookupCategory.setCreatedDatetime(new Date());
		baseService.save(lookupCategory);
	}

	//@Test
	public void testCreateLookupValue() {
		LookupCategory lookupCategory = (LookupCategory)baseService.find(LookupCategory.class, 1l);

		LookupValue lookupValue = new LookupValue();
		lookupValue.setIsActive(true);
		lookupValue.setLookupValue("System User");
		lookupValue.setDescription("System User");
		lookupValue.setLookupCode("SU");
		lookupValue.setSortOrder(1);
		lookupValue.setIsActive(true);
		lookupValue.setLookupCategory(lookupCategory);
		lookupValue.setCreatedBy("Sree");
		lookupValue.setCreatedDatetime(new Date());
		baseService.save(lookupValue);

		lookupCategory = (LookupCategory)baseService.find(LookupCategory.class, 2l);
		lookupCategory.setId(2l);

		lookupValue = new LookupValue();
		lookupValue.setIsActive(true);
		lookupValue.setLookupValue("User Address");
		lookupValue.setDescription("User Address");
		lookupValue.setLookupCode("UA");
		lookupValue.setSortOrder(1);
		lookupValue.setIsActive(true);
		lookupValue.setLookupCategory(lookupCategory);
		lookupValue.setCreatedBy("Sree");
		lookupValue.setCreatedDatetime(new Date());
		baseService.save(lookupValue);
	}

	//@Test
	public void testCreateCountry() {
		Country country = new Country();
		country.setCountryName("India");
		country.setCountryCode("IN");
		country.setIsDefault(true);
		country.setPhoneCode("91");
		country.setCreatedBy("Sree");
		country.setCreatedDatetime(new Date());
		baseService.save(country);
	}

	//@Test
	public void testCreateState() {
		Country country = (Country)baseService.find(Country.class, 1l);
		country.setId(1l);

		State state = new State();
		state.setIsDefault(true);
		state.setSateName("Andhra Pradesh");
		state.setStateCode("AP");
		state.setCountry(country);
		state.setCreatedBy("Sree");
		state.setCreatedDatetime(new Date());
		baseService.save(state);
	}
	
	//@Test
	public void testCreateUserAuthority() {
		Authority authority = new Authority();
		authority.setAuthority("ROLE_ALLACCESS");
		authority.setCreatedBy("Sree");
		authority.setCreatedDatetime(new Date());
		baseService.save(authority);
	}
}
