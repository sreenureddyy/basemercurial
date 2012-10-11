/**
 * 
 */
package com.sree.service.master;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sree.base.domain.Country;
import com.sree.base.service.BaseService;
import com.sree.base.service.IBaseService;

/**
 * @author sree
 *
 */
@Service("masterService")
@SuppressWarnings("unchecked")
public class MasterService extends BaseService implements IMasterService {

	@Resource(name = "baseService")
	private IBaseService baseService;
	
	@Override
	public Object getCountry(Class<?> clazz, Long id) {
		return baseService.find(clazz, id);
	}

	@Override
	public List<Country> getCountries(String queryName, Object... objects) {
		return baseService.find(queryName, objects);
	}

	@Override
	public void saveCountry(Country country) {
		baseService.save(country);		
	}

	@Override
	public void deleteCountry(Country country) {
		baseService.delete(country);
	}

}
