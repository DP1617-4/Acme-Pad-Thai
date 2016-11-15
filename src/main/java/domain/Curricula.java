package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;


@Entity
@Access(AccessType.PROPERTY)
public class Curricula extends DomainEntity{
	// Constructors -----------------------------------------------------------

	public Curricula() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String picture;
	private String education;
	private String experience;
	private String hobbies;
	private boolean deleted;
	

	@URL
	@NotBlank
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@NotBlank
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@NotBlank
	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	@NotBlank
	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	@NotNull
	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	
	//Relations
	
	private Collection<Endorser> endorsers;
	private Nutritionist nutritionist;

	@Valid
	@OneToMany(mappedBy = "curricula", cascade= CascadeType.REMOVE)
	public Collection<Endorser> getEndorsers() {
		return endorsers;
	}

	public void setEndorsers(Collection<Endorser> endorsers) {
		this.endorsers = endorsers;
	}

	@Valid
	@OneToOne(optional = false)
	public Nutritionist getNutritionist() {
		return nutritionist;
	}

	public void setNutritionist(Nutritionist nutritionist) {
		this.nutritionist = nutritionist;
	}


	
 
}
