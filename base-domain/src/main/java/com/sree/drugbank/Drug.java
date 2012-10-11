/**
 * 
 */
package com.sree.drugbank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sree.base.domain.BaseDomain;

/**
 * @author Sree
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "DRUG")
public class Drug extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DRUG_ID")
	private Long id;

	@Column(name = "DRUG_BANK_ID", unique = true)
	private String drugbankId;

	@Column(name = "NAME")
	private String name;

	@Lob
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CAS_NO")
	private String casNumber;

	@Lob
	@Column(name = "GENERAL_REFERANCES")
	private String generalReferences;

	@Lob
	@Column(name = "SYNTHESIS_REFERENCE")
	private String synthesisReference;

	@Lob
	@Column(name = "INDICATION")
	private String indication;

	@Lob
	@Column(name = "PHARMACOLOGY")
	private String pharmacology;

	@Lob
	@Column(name = "MECHANISM_OF_ACTION")
	private String mechanismOfAction;

	@Lob
	@Column(name = "TOXICITY")
	private String toxicity;

	@Lob
	@Column(name = "BIOTRANSFORMATION")
	private String biotransformation;

	@Lob
	@Column(name = "ABSORPTION")
	private String absorption;

	@Lob
	@Column(name = "HALF_LIFE")
	private String halfLife;

	@Lob
	@Column(name = "PROTEIN_BINDING")
	private String proteinBinding;

	@Column(name = "ROUTE_OF_ELIMINATION")
	private String routeOfElimination;

	@Lob
	@Column(name = "VOLUME_OF_DISTRIBUTION")
	private String volumeOfDistribution;

	@Lob
	@Column(name = "CLEANRANCE")
	private String clearance;

	@OneToMany(mappedBy = "drug")
	private List<SecondaryAccessionNumber> secondaryAccessionNumber = new ArrayList<SecondaryAccessionNumber>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TAXONOMY_ID")
	private Taxonomy taxonomy ;
	
	@OneToMany(mappedBy = "drug")
	private List<Synonym> synonym = new ArrayList<Synonym>();
	
	@OneToMany(mappedBy = "drug")
	private List<Brand> brand = new ArrayList<Brand>();
	
	@OneToMany(mappedBy = "drug")
	private List<Mixture> mixture = new ArrayList<Mixture>();
	
	@OneToMany(mappedBy = "drug")
	private List<Packager> packager = new ArrayList<Packager>();
	
	@OneToMany(mappedBy = "drug")
	private List<Manufacturer> manufacturer = new ArrayList<Manufacturer>();
	
	@OneToMany(mappedBy = "drug")
	private List<Price> price = new ArrayList<Price>();
	
	@OneToMany(mappedBy = "drug")
	private List<Category> category = new ArrayList<Category>();
	
	@OneToMany(mappedBy = "drug")
	private List<AffectedOrganisms> affectedOrganisms = new ArrayList<AffectedOrganisms>();

	@OneToMany(mappedBy = "drug")
	private List<FoodInteractions> foodInteractions = new ArrayList<FoodInteractions>();
	
	@OneToMany(mappedBy = "drug")
	private List<DrugInteractions> drugInteractions = new ArrayList<DrugInteractions>();
	
	@OneToMany(mappedBy = "drug")
	private List<ProteinSequence> proteinSequences = new ArrayList<ProteinSequence>();
	
	@OneToMany(mappedBy = "drug")
	private List<ExternalLink> externalLink = new ArrayList<ExternalLink>();	
	
	//private Groups groups;
	//private AtcCodes atcCodes;
	//private AhfsCodes ahfsCodes;
	//private Patents patents;
	//private PropertiesType calculatedProperties;
	//private PropertiesType experimentalProperties;
	//private IdentifiersType externalIdentifiers;
	//private Targets targets;
	//private Enzymes enzymes;
	//private Transporters transporters;
	//private Carriers carriers;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "UPDATED")
	private String updated;

	@Column(name = "CREATED")
	private String created;

	@Column(name = "VERSION")
	private BigDecimal version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDrugbankId() {
		return drugbankId;
	}

	public void setDrugbankId(String drugbankId) {
		this.drugbankId = drugbankId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCasNumber() {
		return casNumber;
	}

	public void setCasNumber(String casNumber) {
		this.casNumber = casNumber;
	}

	public String getGeneralReferences() {
		return generalReferences;
	}

	public void setGeneralReferences(String generalReferences) {
		this.generalReferences = generalReferences;
	}

	public String getSynthesisReference() {
		return synthesisReference;
	}

	public void setSynthesisReference(String synthesisReference) {
		this.synthesisReference = synthesisReference;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getPharmacology() {
		return pharmacology;
	}

	public void setPharmacology(String pharmacology) {
		this.pharmacology = pharmacology;
	}

	public String getMechanismOfAction() {
		return mechanismOfAction;
	}

	public void setMechanismOfAction(String mechanismOfAction) {
		this.mechanismOfAction = mechanismOfAction;
	}

	public String getToxicity() {
		return toxicity;
	}

	public void setToxicity(String toxicity) {
		this.toxicity = toxicity;
	}

	public String getBiotransformation() {
		return biotransformation;
	}

	public void setBiotransformation(String biotransformation) {
		this.biotransformation = biotransformation;
	}

	public String getAbsorption() {
		return absorption;
	}

	public void setAbsorption(String absorption) {
		this.absorption = absorption;
	}

	public String getHalfLife() {
		return halfLife;
	}

	public void setHalfLife(String halfLife) {
		this.halfLife = halfLife;
	}

	public String getProteinBinding() {
		return proteinBinding;
	}

	public void setProteinBinding(String proteinBinding) {
		this.proteinBinding = proteinBinding;
	}

	public String getRouteOfElimination() {
		return routeOfElimination;
	}

	public void setRouteOfElimination(String routeOfElimination) {
		this.routeOfElimination = routeOfElimination;
	}

	public String getVolumeOfDistribution() {
		return volumeOfDistribution;
	}

	public void setVolumeOfDistribution(String volumeOfDistribution) {
		this.volumeOfDistribution = volumeOfDistribution;
	}

	public String getClearance() {
		return clearance;
	}

	public void setClearance(String clearance) {
		this.clearance = clearance;
	}

	/*
	 * public SecondaryAccessionNumbers getSecondaryAccessionNumbers() { return
	 * secondaryAccessionNumbers; }
	 * 
	 * public void setSecondaryAccessionNumbers( SecondaryAccessionNumbers
	 * secondaryAccessionNumbers) { this.secondaryAccessionNumbers =
	 * secondaryAccessionNumbers; }
	 * 
	 * public Groups getGroups() { return groups; }
	 * 
	 * public void setGroups(Groups groups) { this.groups = groups; }
	 * 
	 * public Taxonomy getTaxonomy() { return taxonomy; }
	 * 
	 * public void setTaxonomy(Taxonomy taxonomy) { this.taxonomy = taxonomy; }
	 * 
	 * public SynonymsType getSynonyms() { return synonyms; }
	 * 
	 * public void setSynonyms(SynonymsType synonyms) { this.synonyms =
	 * synonyms; }
	 * 
	 * public Brands getBrands() { return brands; }
	 * 
	 * public void setBrands(Brands brands) { this.brands = brands; }
	 * 
	 * public Mixtures getMixtures() { return mixtures; }
	 * 
	 * public void setMixtures(Mixtures mixtures) { this.mixtures = mixtures; }
	 * 
	 * public Packagers getPackagers() { return packagers; }
	 * 
	 * public void setPackagers(Packagers packagers) { this.packagers =
	 * packagers; }
	 * 
	 * public Manufacturers getManufacturers() { return manufacturers; }
	 * 
	 * public void setManufacturers(Manufacturers manufacturers) {
	 * this.manufacturers = manufacturers; }
	 * 
	 * public Prices getPrices() { return prices; }
	 * 
	 * public void setPrices(Prices prices) { this.prices = prices; }
	 * 
	 * public Categories getCategories() { return categories; }
	 * 
	 * public void setCategories(Categories categories) { this.categories =
	 * categories; }
	 * 
	 * public AffectedOrganisms getAffectedOrganisms() { return
	 * affectedOrganisms; }
	 * 
	 * public void setAffectedOrganisms(AffectedOrganisms affectedOrganisms) {
	 * this.affectedOrganisms = affectedOrganisms; }
	 * 
	 * public Dosages getDosages() { return dosages; }
	 * 
	 * public void setDosages(Dosages dosages) { this.dosages = dosages; }
	 * 
	 * public AtcCodes getAtcCodes() { return atcCodes; }
	 * 
	 * public void setAtcCodes(AtcCodes atcCodes) { this.atcCodes = atcCodes; }
	 * 
	 * public AhfsCodes getAhfsCodes() { return ahfsCodes; }
	 * 
	 * public void setAhfsCodes(AhfsCodes ahfsCodes) { this.ahfsCodes =
	 * ahfsCodes; }
	 * 
	 * public Patents getPatents() { return patents; }
	 * 
	 * public void setPatents(Patents patents) { this.patents = patents; }
	 * 
	 * public FoodInteractions getFoodInteractions() { return foodInteractions;
	 * }
	 * 
	 * public void setFoodInteractions(FoodInteractions foodInteractions) {
	 * this.foodInteractions = foodInteractions; }
	 * 
	 * public DrugInteractions getDrugInteractions() { return drugInteractions;
	 * }
	 * 
	 * public void setDrugInteractions(DrugInteractions drugInteractions) {
	 * this.drugInteractions = drugInteractions; }
	 * 
	 * public ProteinSequences getProteinSequences() { return proteinSequences;
	 * }
	 * 
	 * public void setProteinSequences(ProteinSequences proteinSequences) {
	 * this.proteinSequences = proteinSequences; }
	 * 
	 * public PropertiesType getCalculatedProperties() { return
	 * calculatedProperties; }
	 * 
	 * public void setCalculatedProperties(PropertiesType calculatedProperties)
	 * { this.calculatedProperties = calculatedProperties; }
	 * 
	 * public PropertiesType getExperimentalProperties() { return
	 * experimentalProperties; }
	 * 
	 * public void setExperimentalProperties(PropertiesType
	 * experimentalProperties) { this.experimentalProperties =
	 * experimentalProperties; }
	 * 
	 * public IdentifiersType getExternalIdentifiers() { return
	 * externalIdentifiers; }
	 * 
	 * public void setExternalIdentifiers(IdentifiersType externalIdentifiers) {
	 * this.externalIdentifiers = externalIdentifiers; }
	 * 
	 * public ExternalLinks getExternalLinks() { return externalLinks; }
	 * 
	 * public void setExternalLinks(ExternalLinks externalLinks) {
	 * this.externalLinks = externalLinks; }
	 * 
	 * public Targets getTargets() { return targets; }
	 * 
	 * public void setTargets(Targets targets) { this.targets = targets; }
	 * 
	 * public Enzymes getEnzymes() { return enzymes; }
	 * 
	 * public void setEnzymes(Enzymes enzymes) { this.enzymes = enzymes; }
	 * 
	 * public Transporters getTransporters() { return transporters; }
	 * 
	 * public void setTransporters(Transporters transporters) {
	 * this.transporters = transporters; }
	 * 
	 * public Carriers getCarriers() { return carriers; }
	 * 
	 * public void setCarriers(Carriers carriers) { this.carriers = carriers; }
	 */

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public List<SecondaryAccessionNumber> getSecondaryAccessionNumber() {
		return secondaryAccessionNumber;
	}

	public void setSecondaryAccessionNumber(
			List<SecondaryAccessionNumber> secondaryAccessionNumber) {
		this.secondaryAccessionNumber = secondaryAccessionNumber;
	}

	public Taxonomy getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(Taxonomy taxonomy) {
		this.taxonomy = taxonomy;
	}

	public List<Synonym> getSynonym() {
		return synonym;
	}

	public void setSynonym(List<Synonym> synonym) {
		this.synonym = synonym;
	}

	public List<Brand> getBrand() {
		return brand;
	}

	public void setBrand(List<Brand> brand) {
		this.brand = brand;
	}

	public List<Mixture> getMixture() {
		return mixture;
	}

	public void setMixture(List<Mixture> mixture) {
		this.mixture = mixture;
	}

	public List<Packager> getPackager() {
		return packager;
	}

	public void setPackager(List<Packager> packager) {
		this.packager = packager;
	}

	public List<Manufacturer> getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(List<Manufacturer> manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<Price> getPrice() {
		return price;
	}

	public void setPrice(List<Price> price) {
		this.price = price;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public List<AffectedOrganisms> getAffectedOrganisms() {
		return affectedOrganisms;
	}

	public void setAffectedOrganisms(List<AffectedOrganisms> affectedOrganisms) {
		this.affectedOrganisms = affectedOrganisms;
	}

	public List<FoodInteractions> getFoodInteractions() {
		return foodInteractions;
	}

	public void setFoodInteractions(List<FoodInteractions> foodInteractions) {
		this.foodInteractions = foodInteractions;
	}

	public List<DrugInteractions> getDrugInteractions() {
		return drugInteractions;
	}

	public void setDrugInteractions(List<DrugInteractions> drugInteractions) {
		this.drugInteractions = drugInteractions;
	}

	public List<ProteinSequence> getProteinSequences() {
		return proteinSequences;
	}

	public void setProteinSequences(List<ProteinSequence> proteinSequences) {
		this.proteinSequences = proteinSequences;
	}

	public List<ExternalLink> getExternalLink() {
		return externalLink;
	}

	public void setExternalLink(List<ExternalLink> externalLink) {
		this.externalLink = externalLink;
	}
}
