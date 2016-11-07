package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {
	
	// Constructor
	
	public Category(){
		super();
	}
	
	//Attributes
	
	private String name;
	private String description;
	private String picture;
	private Collection<String> tag;
	private boolean deleted;
	
	@NotBlank
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@URL
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@ElementCollection
	public Collection<String> getTag() {
		return tag;
	}
	public void setTag(Collection<String> tag) {
		this.tag = tag;
	}
	@NotNull
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	//Relationships
	
	private Collection<Recipe> recipes;
	private Collection<Category> sons;
	private Category father;

	
	@Valid
	@ManyToMany()
	public Collection<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}
	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy= "father")
	public Collection<Category> getSons() {
		return sons;
	}
	public void setSons(Collection<Category> sons) {
		this.sons = sons;
	}
	
	@Valid
	@ManyToOne(optional = true)
	public Category getFather() {
		return father;
	}
	public void setFather(Category father) {
		this.father = father;
	}
	
	

}
