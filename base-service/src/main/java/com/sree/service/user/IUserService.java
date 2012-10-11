/**
 * 
 */
package com.sree.service.user;

import java.util.List;

import com.sree.base.domain.User;

/**
 * @author Sree
 * 
 */
public interface IUserService {
	public Object getUser(Class<?> clazz, Long id);
	public List<?> getUser(String queryName, Object... objects);
	public void saveUser(User user);
	public void deleteUser(User user);
}
