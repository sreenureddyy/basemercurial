/**
 * 
 */
package com.sree.base.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Sree
 * 
 */

@NamedQueries({ @NamedQuery(name = "getAllCountries", query = "from Country country") })
@SuppressWarnings("serial")
@Entity
@Table(name = "COUNTRY")
public class Country extends BaseDomain {

	@Id
	@Column(name = "COUNTRY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "COUNTRY_NAME", unique=true)
	private String countryName;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "PHONE_CODE")
	private String phoneCode;

	@Column(name = "IS_DEFAULT")
	private Boolean isDefault = false;

	/*
	 * @JoinColumn(name = "CURRENCY")
	 * 
	 * @ManyToOne(cascade = CascadeType.ALL) private Currency currency = new
	 * Currency();
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	/*
	 * public Currency getCurrency() { return currency; }
	 * 
	 * public void setCurrency(Currency currency) { this.currency = currency; }
	 */
}
