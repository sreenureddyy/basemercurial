/**
 * 
 */
package com.sree.base.service.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sree.base.domain.Authority;
import com.sree.base.domain.User;
import com.sree.service.user.IUserService;

/**
 * @author YSReddi
 * 
 */
@SuppressWarnings("unchecked")
public class UserAuthentication implements UserDetailsService {

	@Autowired
	private IUserService userService;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		User user;

		List<User> userList = (List<User>) userService.getUser(
				"findUserByUserName", username);

		if (userList != null && userList.size() > 0) {
			user = userList.get(0);
			for (Authority authority : user.getUserAuthorities()) {
				user.getAuthorities().add(
						new GrantedAuthorityImpl(authority.getAuthority()));
			}
		} else {
			throw new UsernameNotFoundException(username + " not found");
		}

		return user;
	}

}
