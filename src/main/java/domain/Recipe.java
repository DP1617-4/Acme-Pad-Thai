package domain;

import java.util.Date;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Recipe extends DomainEntity {
	
	//Constructor
	
	public Recipe(){
		super();
	}
	
	// Attributes
	
	private String ticker;
	private String title;
	private String summary;
	private Date authored;
	private Date updated;
	private Collection<String> pictures;
	private String hints;
	private boolean deleted;
	private int score;
	
	@NotBlank
	@Column(unique = true)
	@Pattern (regexp = "^(\\d{6})[-]([A-z]{4})$")
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	@NotBlank
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Past
	@NotNull
	public Date getAuthored() {
		return authored;
	}
	public void setAuthored(Date authored) {
		this.authored = authored;
	}
	
	@Past
	@NotNull
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	@ElementCollection
	public Collection<String> getPictures() {
		return pictures;
	}
	public void setPictures(Collection<String> pictures) {
		this.pictures = pictures;
	}
	
	public String getHints() {
		return hints;
	}
	public void setHints(String hints) {
		this.hints = hints;
	}
	@NotNull
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	@Transient
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	//Relationships
	
	private User user;
	private Collection<Score> scores;
	private Collection<Step> steps;
	private Contest contest;
	private Contest wonContest;
	private Collection<Comment> comments;
	private Collection<Category> categories;
	private Collection<Quantity> quantities;

	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Valid
	@OneToMany(mappedBy = "recipe")
	public Collection<Score> getScores() {
		return scores;
	}
	public void setScores(Collection<Score> scores) {
		this.scores = scores;
	}
	
	@Valid
	@OneToMany(mappedBy = "recipe")
	public Collection<Step> getSteps() {
		return steps;
	}
	public void setSteps(Collection<Step> steps) {
		this.steps = steps;
	}
	@Valid
	@ManyToOne(optional = true)
	public Contest getContest() {
		return contest;
	}
	public void setContest(Contest contest) {
		this.contest = contest;
	}
	@Valid
	@ManyToOne(optional = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	public Contest getWonContest() {
		return wonContest;
	}
	public void setWonContest(Contest wonContest) {
		this.wonContest = wonContest;
	}
	
	@Valid
	@OneToMany(mappedBy = "recipe")
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	@Valid
	@ManyToMany(mappedBy="recipes")
	public Collection<Category> getCategories() {
		return categories;
	}
	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}
	@Valid
	@OneToMany(mappedBy = "recipe")
	public Collection<Quantity> getQuantities() {
		return quantities;
	}
	public void setQuantities(Collection<Quantity> quantities) {
		this.quantities = quantities;
	}
	
	
	// Methods
	
	@PostLoad
	protected void initScore(){
		int aux = 0;
		Collection<Score> scores = getScores();
		for(Score s: scores){
			if(s.getLikes() == true)
				aux++;
			else
				aux--;
		}
		
		this.score = aux;
	}
	
	

}
