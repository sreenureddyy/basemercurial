/**
 * 
 */
package com.sree.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sree.base.domain.User;
import com.sree.base.service.BaseService;
import com.sree.base.service.IBaseService;

/**
 * @author Sree
 * 
 */
@Service("userService")
public class UserService extends BaseService implements IUserService {
	@Resource(name = "baseService")
	private IBaseService baseService;

	@Override
	public Object getUser(Class<?> clazz, Long id) {
		return baseService.find(clazz, id);
	}

	@Override
	public List<?> getUser(String queryName, Object... objects) {
		return baseService.find(queryName, objects);
	}

	@Override
	public void saveUser(User user) {
		baseService.save(user);
	}

	@Override
	public void deleteUser(User user) {
		baseService.delete(user);		
	}

}
