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
@Table(name="LOOKUP_VALUE")
public class LookupValue extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name="LOOKUP_CODE")
	private String lookupCode;
	
	@Column(name="LOOKUP_VALUE")
	private String lookupValue;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="SORT_ORDER")
	private int sortOrder;

	@JoinColumn(name = "LOOKUP_CATEGORY")
	@ManyToOne(cascade = CascadeType.ALL)
	private LookupCategory lookupCategory = new LookupCategory();
	
	@Column(name="ISACTIVE")
	private Boolean isActive = true;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLookupCode() {
		return lookupCode;
	}
	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}
	public String getLookupValue() {
		return lookupValue;
	}
	public void setLookupValue(String lookupValue) {
		this.lookupValue = lookupValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LookupCategory getLookupCategory() {
		return lookupCategory;
	}
	public void setLookupCategory(LookupCategory lookupCategory) {
		this.lookupCategory = lookupCategory;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
}
