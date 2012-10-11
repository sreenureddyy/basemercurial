/**
 * 
 */
package com.sree.base.service.security;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sree.base.domain.Authority;
import com.sree.base.domain.User;
import com.sree.base.service.IBaseService;
import com.sree.service.user.IUserService;

/**
 * @author YSReddi
 * 
 */
@SuppressWarnings("unchecked")
public class UserAuthentication implements UserDetailsService {

	@Autowired
	private IUserService userService;
	private static Logger log = Logger.getLogger(UserAuthentication.class);

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		log.info("Log in User name :: " + username);
		List<User> userList = (List<User>) userService.getUser(
				"findUserByUserName", username);
		List<Authority> userAuthorities = (List<Authority>) userService
				.getUser("getUserAuthorities", username);

		if (userList.size() == 0 || userAuthorities.size() == 0) {
			throw new UsernameNotFoundException(username + " not found");
		}

		User user = userList.get(0);

		for (Authority authority : userAuthorities) {
			user.getAuthorities().add(
					new GrantedAuthorityImpl(authority.getAuthority()));
		}

		return user;
	}

}
