/**
 * 
 */
package com.sree.base.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author srinivasr
 * 
 */

@Entity
@Table(name = "USER")
@SuppressWarnings("serial")
public class User extends BaseDomain implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "FIRSTNAME", nullable = false)
	private String firstname;

	@Column(name = "LASTNAME", nullable = true)
	private String lastname;

	@Column(name = "GENDER", nullable = false)
	private Long gender;

	@Column(name = "DOB", nullable = false)
	private Date dob = new Date();

	@Column(name = "MOBILE")
	private String mobile;

	@Column(name = "ACCOUNT_NON_LOCKED")
	private Boolean accountNonLocked = true;

	@Column(name = "ACCOUNT_NON_EXPIRED")
	private Boolean accountNonExpired = true;

	@Column(name = "CREDENTIALS_NON_EXPIRED")
	private Boolean credentialsNonExpired = true;

	@Column(name = "ENABLED")
	private Boolean enabled = true;

	@JoinColumn(name = "USER_TYPE")
	@ManyToOne(cascade = CascadeType.ALL)
	private LookupValue userType = new LookupValue();

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY )
	@JoinTable(joinColumns = { @JoinColumn(name = "USER") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY") })
	private List<Authority> userAuthorities = new ArrayList<Authority>();

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(joinColumns = { @JoinColumn(name = "USER") }, inverseJoinColumns = { @JoinColumn(name = "ADDRESS") })
	List<Address> address = new ArrayList<Address>();

	@Transient
	private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return getAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return getAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return getCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return getEnabled();
	}

	public LookupValue getUserType() {
		return userType;
	}

	public void setUserType(LookupValue userType) {
		this.userType = userType;
	}

	public List<Authority> getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(List<Authority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}
