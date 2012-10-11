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
@Entity
@Table(name="CONTACT_DETAILS")
public class ContactDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONTACT_DETAILS_ID")
	private Long id;
	
	@Column(name="CONTACT")
	private String contact;
	
	@Column(name="ISPRIMARY_CONTACT")
	private Boolean isPrimaryContact;
	
	@JoinColumn(name = "CONTACT_TYPE")
	@ManyToOne(cascade = CascadeType.ALL)
	private LookupValue contactType = new LookupValue();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Boolean getIsPrimaryContact() {
		return isPrimaryContact;
	}

	public void setIsPrimaryContact(Boolean isPrimaryContact) {
		this.isPrimaryContact = isPrimaryContact;
	}

	public LookupValue getContactType() {
		return contactType;
	}

	public void setContactType(LookupValue contactType) {
		this.contactType = contactType;
	}

}
