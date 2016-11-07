package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity{
	
	// Constructors -----------------------------------------------------------

		public Comment() {
			super();
		}

		// Attributes -------------------------------------------------------------

		private String title;
		private String text;
		private Integer stars;
		private Date date;
		
		@NotBlank
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
		@NotBlank
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
		@Range(min = 1, max = 5)
		public Integer getStars() {
			return stars;
		}
		public void setStars(Integer stars) {
			this.stars = stars;
		}
		
		@Past
		@Valid
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		
		//relations
		
		private SocialUser socialUser;
		private Recipe recipe;

		@Valid
		@NotNull
		@ManyToOne(optional = false)
		public SocialUser getSocialUser() {
			return socialUser;
		}
		public void setSocialUser(SocialUser socialUser) {
			this.socialUser = socialUser;
		}
		
		@Valid
		@NotNull
		@ManyToOne(optional = false)
		public Recipe getRecipe(){
			return recipe;
		}
		
		public void setRecipe(Recipe recipe){
			this.recipe = recipe;
		}
		
		
}