package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class User extends SocialUser {
	
	// Constructor
	
	public User(){
		super();
	}
	
	// Attributes
	
	//Relationships
	
	private Collection<Recipe> recipes;
	
	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}
}
