/**
 * 
 */
package com.sree.base.web;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author sree
 * 
 */

@SuppressWarnings({ "serial" })
@Component("localeBean")
@Scope(value = "session")
public class LocaleBean implements Serializable{
	private String localeCode;
	
	private Locale applicationLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

	private static Map<String, Object> countries;
	
	static {
		countries = new LinkedHashMap<String, Object>();
		countries.put("English", Locale.ENGLISH); // label, value
		countries.put("Chinese", Locale.SIMPLIFIED_CHINESE);
	}
	
	public Locale getApplicationLocale() {
		return applicationLocale;
	}

	public void setApplicationLocale(Locale applicationLocale) {
		this.applicationLocale = applicationLocale;
	}

	public Map<String, Object> getCountries() {
		return countries;
	}

	public static void setCountries(Map<String, Object> countries) {
		LocaleBean.countries = countries;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	// value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {

		String newLocaleValue = e.getNewValue().toString();

		// loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : countries.entrySet()) {

			if (entry.getValue().toString().equals(newLocaleValue)) {
				setApplicationLocale((Locale) entry.getValue());
				FacesContext.getCurrentInstance().getViewRoot()
						.setLocale((Locale) entry.getValue());

			}
		}
	}
}
