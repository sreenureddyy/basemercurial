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
@Table(name = "ORGANIZATION")
@SuppressWarnings("serial")
public class Organization extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORGANIZATION_ID")
	private Long id;

	@Column(name = "ORGANIZATION_NAME", nullable = false, unique = true)
	private String organizationName;

	@JoinColumn(name = "ORGANIZATION_TYPE")
	@ManyToOne(cascade = CascadeType.ALL)
	private LookupValue organizationType = new LookupValue();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ORGANIZATION_BRANCH", joinColumns = { @JoinColumn(name = "ORGANIZATION") }, inverseJoinColumns = { @JoinColumn(name = "BRANCH") })
	List<Branch> branches = new ArrayList<Branch>();

	@Column(name = "ESTABLISHMENT_DATE", nullable = false)
	private Date establishmentDate = new Date();

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

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public LookupValue getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(LookupValue organizationType) {
		this.organizationType = organizationType;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
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

}
