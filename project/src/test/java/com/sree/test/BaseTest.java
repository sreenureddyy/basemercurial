/**
 * 
 */
package com.sree.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sree.base.service.IBaseService;

/**
 * @author srinivasr
 *   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/META-INF/spring/springContext-Test.xml",
		"/META-INF/spring/applicationContext-security.xml",
		"/META-INF/spring/applicationContext-spring.xml" })
public class BaseTest {
	@Autowired
	IBaseService baseService;
}