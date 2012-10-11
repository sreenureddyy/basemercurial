/**
 * 
 */
package com.sree.service.master;

import java.util.List;

import com.sree.base.domain.Country;
import com.sree.base.service.IBaseService;

/**
 * @author sree
 *
 */
public interface IMasterService extends IBaseService {
	public Object getCountry(Class<?> clazz, Long id);
	public List<Country> getCountries(String queryName, Object... objects);
	public void saveCountry(Country country);
	public void deleteCountry(Country country);
}
