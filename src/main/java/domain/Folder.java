package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Access(AccessType.PROPERTY)
public class Folder extends DomainEntity {

	// Constructor
	
	public Folder (){
		super();
	}
	
	//Attributes
	
	private String name;
	private boolean systemFolder;
	private boolean deleted;
	
	@NotBlank
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public boolean getSystemFolder() {
		return systemFolder;
	}
	public void setSystemFolder(boolean systemFolder) {
		this.systemFolder = systemFolder;
	}
	@NotNull
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	//Relationships
	
	private Collection<Message> messages;
	private Actor actor;

	@Valid
	@OneToMany(mappedBy = "folder")
	public Collection<Message> getMessages() {
		return messages;
	}
	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
}
