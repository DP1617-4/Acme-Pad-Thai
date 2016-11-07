package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Score extends DomainEntity {
	
	//Constructor
	
	public Score(){
		super();
	}
	
	//Attributes
	
	private boolean likes;
	
	@NotNull
	public boolean getLikes() {
		return likes;
	}

	public void setLikes(boolean likes) {
		this.likes = likes;
	}
	
	// Relationships
	
	private SocialUser socialUser;
	private Recipe recipe;
	
	@Valid
	@ManyToOne(optional = false)
	public SocialUser getSocialUser() {
		return socialUser;
	}

	public void setSocialUser(SocialUser socialUser) {
		this.socialUser = socialUser;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	
	
	

}
