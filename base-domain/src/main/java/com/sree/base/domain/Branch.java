/**
 * 
 */
package com.sree.base.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author sree
 * 
 */
@Entity
@Table(name = "BRANCH")
@SuppressWarnings("serial")
public class Branch extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BRANCH_ID")
	private Long id;

	@Column(name = "BRANCH_NAME", nullable = false, unique = true)
	private String branchName;

	@Column(name = "CONTACT_PERSON", nullable = false, unique = true)
	private String contactPerson;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "BRANCH_ADDRESS", joinColumns = { @JoinColumn(name = "BRANCH") }, inverseJoinColumns = { @JoinColumn(name = "ADDRESS") })
	List<Address> address = new ArrayList<Address>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "BRANCH_CONTACTS", joinColumns = { @JoinColumn(name = "BRANCH") }, inverseJoinColumns = { @JoinColumn(name = "CONTACT_DETAILS") })
	List<ContactDetails> contactDetails = new ArrayList<ContactDetails>();

	@Column(name = "ESTABLISHMENT_DATE", nullable = false)
	private Date establishmentDate = new Date();
	
	@JoinColumn(name = "BRANCH_TYPE")
	@ManyToOne(cascade = CascadeType.ALL)
	private LookupValue branchType = new LookupValue();

	@Column(name = "RENEWED_ON")
	private Date renewedOn = new Date();

	@Column(name = "RENEWED_UPTO")
	private Date renewedUpto = new Date();

	@Column(name = "ENABLED")
	private Boolean enabled = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<ContactDetails> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(List<ContactDetails> contactDetails) {
		this.contactDetails = contactDetails;
	}

	public Date getEstablishmentDate() {
		return establishmentDate;
	}

	public void setEstablishmentDate(Date establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public Date getRenewedOn() {
		return renewedOn;
	}

	public void setRenewedOn(Date renewedOn) {
		this.renewedOn = renewedOn;
	}

	public Date getRenewedUpto() {
		return renewedUpto;
	}

	public void setRenewedUpto(Date renewedUpto) {
		this.renewedUpto = renewedUpto;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public LookupValue getBranchType() {
		return branchType;
	}

	public void setBranchType(LookupValue branchType) {
		this.branchType = branchType;
	}

}
