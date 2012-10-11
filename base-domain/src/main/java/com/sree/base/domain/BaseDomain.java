/**
 * 
 */
package com.sree.base.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OrderBy;

import com.sree.common.constants.AnnotationValues;

/**
 * @author ysreddy
 * 
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class BaseDomain implements Serializable {
	@Column(name = "CREATEDBY", length = AnnotationValues.CREATEDBY_LENGTH, nullable = false)
	private String createdBy;

	@Column(name = "CREATEDDATETIME", nullable = false)
	@OrderBy(value = "desc")
	private Date createdDatetime;

	@Column(name = "UPDATEDBY", length = AnnotationValues.CREATEDBY_LENGTH)
	private String updatedBy;

	@Column(name = "UPDATEDDATETIME")
	private Date updatedDatetime;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(Date updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	/*@Column(name = "HIBVERSION")
	@Version
	private int versionNo;*/


}
