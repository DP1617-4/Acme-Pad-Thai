package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Ingredient extends DomainEntity {
	
	//Constructor
	
	public Ingredient(){
		super();
	}

	
	//Attributes
	
	private String name;
	private String description;
	private Collection<String> pictures;
	private boolean deleted;
	
	@Valid
	@NotBlank
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Valid
	@NotBlank
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ElementCollection
	public Collection<String> getPictures() {
		return pictures;
	}
	public void setPictures(Collection<String> pictures) {
		this.pictures = pictures;
	}
	
	@NotNull
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	//Relationships
	
	private Collection<Quantity> quantities;
	private Collection<Value> values;

	@Valid
	@OneToMany(mappedBy = "ingredient")
	public Collection<Quantity> getQuantities() {
		return quantities;
	}
	public void setQuantities(Collection<Quantity> quantities) {
		this.quantities = quantities;
	}
	
	@Valid
	@OneToMany(mappedBy = "ingredient")
	public Collection<Value> getValues() {
		return values;
	}
	public void setValues(Collection<Value> values) {
		this.values = values;
	}
	
	
}
