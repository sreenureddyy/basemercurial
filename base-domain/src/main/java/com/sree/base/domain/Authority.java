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

import org.springframework.security.core.GrantedAuthority;

/**
 * @author YSReddi
 * 
 */
@Entity
@Table(name = "AUTHORITY")
@SuppressWarnings("serial")
public class Authority extends BaseDomain implements GrantedAuthority {
	@Id
	@Column(name = "AUTHORITY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "AUTHORITY")
	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
