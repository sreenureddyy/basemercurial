/**
 * 
 */
package com.sree.base.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Sree
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LOOKUP_CATEGORY")
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lookupCategory")
	private Set<LookupValue> lookupValues = new HashSet<LookupValue>(0);

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

	public Set<LookupValue> getLookupValues() {
		return lookupValues;
	}

	public void setLookupValues(Set<LookupValue> lookupValues) {
		this.lookupValues = lookupValues;
	}

}
