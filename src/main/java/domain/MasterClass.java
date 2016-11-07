package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.NotBlank;



@Entity
@Access(AccessType.PROPERTY)
public class MasterClass extends DomainEntity{

	// Constructors -----------------------------------------------------------

		public MasterClass() {
			super();
		}

		// Attributes -------------------------------------------------------------

		private String title;
		private String description;
		private boolean deleted;
		private boolean promoted;
		
		@NotBlank
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
		@NotBlank
		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		public boolean getDeleted() {
			return deleted;
		}
		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}
		
		public boolean getPromoted() {
			return promoted;
		}
		public void setPromoted(boolean promoted) {
			this.promoted = promoted;
		}
		
		
		//Relations
		
		private Collection<LearningMaterial> learningMaterials;
		private Cook cook;
		private Collection<Actor> actors;

		
		@Valid
		@OneToMany(mappedBy= "masterClass")
		public Collection<LearningMaterial> getLearningMaterials() {
			return learningMaterials;
		}
		
		public void setLearningMaterials(Collection<LearningMaterial> learningMaterials) {
			this.learningMaterials = learningMaterials;
		}
		
		@NotNull
		@Valid
		@ManyToOne(optional= false)
		public Cook getCook() {
			return cook;
		}
		public void setCook(Cook cook) {
			this.cook = cook;
		}
		
		@Valid
		@ManyToMany(mappedBy="enroled")
		public Collection<Actor> getActors() {
			return actors;
		}
		
		public void setActors(Collection<Actor> actors) {
			this.actors = actors;
		}
		
		
}
