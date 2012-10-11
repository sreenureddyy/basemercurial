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
@Table(name="CURRENCY")
public class Currency extends BaseDomain {
	@Id
	@Column(name = "CURRENCY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="CURRENCY_NAME")
	private String currencyName;
	
	@Column(name="CURRENCY_CODE")
	private String currencyCode;
	
	@Column(name="CURRENCY_SYMBOL")
	private String currencySymbol;
	
	@Column(name="LOWER_DENOMINATION")
	private String lowerDenomination;
	
	@Column(name="ALIAS_NAME")
	private String aliasName;
	
	@Column(name="ISDEFAULT")
	private String isDefault;
	
	@Column(name="COMMENTS")
	private String comments;
	
	@Column(name="CHANGE_FACTOR")
	private Integer changeFactor;
	
	@Column(name="ISACTIVE")
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getLowerDenomination() {
		return lowerDenomination;
	}

	public void setLowerDenomination(String lowerDenomination) {
		this.lowerDenomination = lowerDenomination;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getChangeFactor() {
		return changeFactor;
	}

	public void setChangeFactor(Integer changeFactor) {
		this.changeFactor = changeFactor;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
}
