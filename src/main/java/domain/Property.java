package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Property extends DomainEntity {
	
	//Constructor
	
	public Property(){
		super();
	}
	
	//Attributes
	
	private String name;
	private boolean deleted;
	
	@NotBlank
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotNull
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	//Relationships
	
	private Collection<Value> values;
	
	@Valid
	@OneToMany(mappedBy = "property")
	public Collection<Value> getValues() {
		return values;
	}
	public void setValues(Collection<Value> values) {
		this.values = values;
	}
	
	

}
