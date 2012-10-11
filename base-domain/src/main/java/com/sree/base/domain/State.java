/**
 * 
 */
package com.sree.base.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Sree
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="STATE")
public class State extends BaseDomain {
	
	@Id
	@Column(name = "STATE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="STATE_NAME")
	private String sateName;
	
	@Column(name="STATE_CODE")
	private String stateCode;

	@Column(name="ISDEFAULT")
	private Boolean isDefault;
	
	@JoinColumn(name = "COUNTRY")
	@ManyToOne(cascade = CascadeType.ALL)
	private Country country = new Country();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSateName() {
		return sateName;
	}

	public void setSateName(String sateName) {
		this.sateName = sateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
}
