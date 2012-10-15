/**
 * 
 */
package com.sree.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sree.base.domain.User;

/**
 * @author srinivasr
 * 
 */
@SuppressWarnings("unchecked")
public class TestUser extends BaseTest {
	private Logger log = Logger.getLogger(TestUser.class);

	@Test
	public void testCase() {
		List<User> users = baseService.find("getAllUsers");
		log.info(users.size());
		for (User user : users) {
			log.info(" Username is :: " + user.getUsername());
		}
	}
}
