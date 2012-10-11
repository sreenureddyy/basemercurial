/**
 * 
 */
package com.sree.base.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sree
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="LOOKUP_CATEGORY")
public class LookupCategory extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "LOOKUP_CATEGORY")
	private String lookupCategory;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "ISACTIVE")
	private Boolean isActive;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLookupCategory() {
		return lookupCategory;
	}
	public void setLookupCategory(String lookupCategory) {
		this.lookupCategory = lookupCategory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
