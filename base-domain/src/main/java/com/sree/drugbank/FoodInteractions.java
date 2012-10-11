/**
 * 
 */
package com.sree.drugbank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Sree
 * 
 */
@Entity
@Table(name = "FOOD_INTERACTIONS")
public class FoodInteractions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Lob
	@Column(name = "FOODINTERACTIONS")
	private String foodInteraction;

	@ManyToOne
	@JoinColumn(name = "DRUG_FK")
	private Drug drug = new Drug();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoodInteraction() {
		return foodInteraction;
	}

	public void setFoodInteraction(String foodInteraction) {
		this.foodInteraction = foodInteraction;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

}
