package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {
	
	// Constructor
	
	public Message(){
		super();
	}
	
	// Attributes
	
	private static final String low = "LOW";
	private static final String neutral = "NEUTRAL";
	private static final String high = "HIGH";
	
	private String title;
	private String body;
	private Date moment;
	private String priority;
	
	@NotBlank
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Past
	@NotNull
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	@NotNull
	@NotBlank
	@Pattern(regexp = "^"+low+"|"+neutral+"|"+high+"$")
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	// Relationships
	
	private Folder folder;
	private Actor sender;
	private Actor receiver;

	@Valid
	@NotNull
	@ManyToOne(optional =false, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional =false, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	public Actor getSender() {
		return sender;
	}
	public void setSender(Actor sender) {
		this.sender = sender;
	}
	@Valid
	@NotNull
	@ManyToOne(optional =false, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	public Actor getReceiver() {
		return receiver;
	}
	public void setReceiver(Actor receiver) {
		this.receiver = receiver;
	}
	

}
