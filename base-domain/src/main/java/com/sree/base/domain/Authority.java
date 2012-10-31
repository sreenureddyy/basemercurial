/**
 * 
 */
package com.sree.base.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author sree
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

	@ManyToMany(mappedBy = "userAuthorities")
	private Set<User> users = new HashSet<User>(0);

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
