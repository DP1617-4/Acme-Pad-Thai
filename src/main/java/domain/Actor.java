package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.AccessType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Actor extends DomainEntity {
	
	// Constructors -----------------------------------------------------------

	public Actor() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String name;
	private String surname;
	private String email;
	private String phone;
	private String postalAddress;
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotBlank
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@NotBlank
	
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@NotBlank
	
	@Pattern(regexp = "^([+]([1-9]|[1-9][0-9]|[1-9][0-9][0-9]))?\\s?([(](00[1-9]|0[1-9][0-9]|[1-9][0-9][0-9])[)])?\\s?\\w\\s?[-]?\\w\\s?[-]?\\w\\s?[-]?\\w$")			//TODO meter el pattern, que ahora mismo no lo puedo copiar a la máquina
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@NotBlank
	
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	
	//Relations
	
	private UserAccount userAccount;
	private Collection<Folder> folders;
	private Collection<SocialIdentity> socialIdentities;
	private Collection<MasterClass> enroled;
	
	@NotNull
	@Valid	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy= "actor", cascade = CascadeType.PERSIST)
	public Collection<Folder> getFolders() {
		return folders;
	}
	public void setFolders(Collection<Folder> folders) {
		this.folders = folders;
	}
	
	@Valid
	@OneToMany(mappedBy = "actor")
	public Collection<SocialIdentity> getSocialIdentities() {
		return socialIdentities;
	}
	public void setSocialIdentities(Collection<SocialIdentity> socialIdenttities) {
		this.socialIdentities = socialIdenttities;
	}
	
	@Valid
	@ManyToMany()
	public Collection<MasterClass> getEnroled() {
		return enroled;
	}
	public void setEnroled(Collection<MasterClass> masterClasses) {
		this.enroled = masterClasses;
	}
	
}
