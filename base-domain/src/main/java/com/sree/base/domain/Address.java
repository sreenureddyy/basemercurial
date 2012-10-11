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
@Table(name="ADDRESS")
public class Address extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDRESS_ID")
	private Long id;
	
	@Column(name="HOUSE_NO")
	private String houseNo;
	
	@Column(name="STREET")
	private String street;
	
	@Column(name="CITY_TOWN")
	private String cityTown;
	
	@Column(name="LANDMARK")
	private String landmark;
	
	@Column(name="PINCODE")
	private String pinCode;
	
	@JoinColumn(name = "ADDRESS_TYPE")
	@ManyToOne(cascade = CascadeType.ALL)
	private LookupValue addressType = new LookupValue();
	
	@JoinColumn(name = "STATE")
	@ManyToOne(cascade = CascadeType.ALL)
	private State state = new State();
	
	@JoinColumn(name = "COUNTRY")
	@ManyToOne(cascade = CascadeType.ALL)
	private Country country = new Country();
	
	@Column(name="ISPRIMARY")
	private Boolean isPrimary = true;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCityTown() {
		return cityTown;
	}
	public void setCityTown(String cityTown) {
		this.cityTown = cityTown;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public Boolean getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public LookupValue getAddressType() {
		return addressType;
	}
	public void setAddressType(LookupValue addressType) {
		this.addressType = addressType;
	}
	
}
