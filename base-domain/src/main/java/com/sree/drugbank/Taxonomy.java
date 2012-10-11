package com.sree.drugbank;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TAXONOMY")
public class Taxonomy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "KINGDOM")
	private String kingdom;

	/*
	 * @OneToMany
	 * 
	 * @JoinTable( name ="TAXONOMY_SUBSTRUCTURE", joinColumns = @JoinColumn(name
	 * = "TAXONOMY_ID"), inverseJoinColumns = @JoinColumn(name =
	 * "SUBSTRUCTURE_ID"))
	 */
	@OneToMany(mappedBy = "taxonomy")
	private List<Substructure> substructure = new ArrayList<Substructure>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKingdom() {
		return kingdom;
	}

	public void setKingdom(String kingdom) {
		this.kingdom = kingdom;
	}

	public List<Substructure> getSubstructure() {
		return substructure;
	}

	public void setSubstructure(List<Substructure> substructure) {
		this.substructure = substructure;
	}

}
