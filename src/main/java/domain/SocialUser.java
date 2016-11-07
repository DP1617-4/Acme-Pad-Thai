package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.Entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.PROPERTY)
public abstract class SocialUser extends Actor{

	// Constructors -----------------------------------------------------------

		public SocialUser() {
			super();
		}

		// Attributes -------------------------------------------------------------

		
		//Relations
		
		private Collection<SocialUser> followers;
		private Collection<SocialUser> followed;
		private Collection<Comment> comments;
		private Collection<Score> scores;
		
		@Valid	
		@NotNull
		@ManyToMany(mappedBy = "followed")
		public Collection<SocialUser> getFollowers() {
			return followers;
		}
		public void setFollowers(Collection<SocialUser> followers) {
			this.followers = followers;
		}
		
		
		@Valid	
		@NotNull
		@ManyToMany()
		public Collection<SocialUser> getFollowed() {
			return followed;
		}
		public void setFollowed(Collection<SocialUser> followed) {
			this.followed = followed;
		}
		
		@Valid
		@NotNull
		@OneToMany(mappedBy = "socialUser")
		public Collection<Comment> getComments() {
			return comments;
		}
		public void setComments(Collection<Comment> comments) {
			this.comments = comments;
		}
		
		@Valid
		@NotNull
		@OneToMany(mappedBy = "socialUser")
		public Collection<Score> getScores() {
			return scores;
		}
		public void setScores(Collection<Score> scores) {
			this.scores = scores;
		}
		
		
		
}
