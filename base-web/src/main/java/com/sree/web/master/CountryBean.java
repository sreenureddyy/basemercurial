/**
 * 
 */
package com.sree.web.master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sree.base.domain.Country;
import com.sree.base.web.BaseBean;
import com.sree.service.master.IMasterService;

/**
 * @author sree
 *
 */
@SuppressWarnings({"serial"})
@Component("countryBean")
@Scope(value = "request")
public class CountryBean extends BaseBean {
	
	private Logger log = Logger.getLogger(CountryBean.class);

	private IMasterService masterService;
	
	private Country country = new Country();
	
	private Country selectedCountry = new Country();
	
	private List<Country> countries;
	
	@Autowired
	public CountryBean(IMasterService masterService) {
		this.masterService = masterService;
		
		countries = new ArrayList<Country>();
		
		populateCountries();
		
	}
	
	public String saveCountry() throws DatabaseException{
		country.setCreatedBy(getUsername());
		country.setCreatedDatetime(new Date());
		log.info("testing mastersevide in Save method "+masterService);
		
		try{
			masterService.save(country);
		}catch(Exception e){
			e.printStackTrace();
			//throw new DatabaseException("Unique ID");
		}
		country = new Country();
		populateCountries();
		return null;
	}
	
	public void deleteCountry(){
		log.info("testing mastersevide "+masterService);
		masterService.deleteCountry(selectedCountry);
		populateCountries();
	}

	private void populateCountries(){
		countries = masterService.getCountries("getAllCountries", new Object[]{});
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Country getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(Country selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public IMasterService getMasterService() {
		return masterService;
	}

	public void setMasterService(IMasterService masterService) {
		this.masterService = masterService;
	}
}
