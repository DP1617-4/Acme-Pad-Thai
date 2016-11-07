package domain;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public abstract class LearningMaterial extends DomainEntity{

	// Constructors -----------------------------------------------------------

		public LearningMaterial() {
			super();
		}

		// Attributes -------------------------------------------------------------

		private String title;
		private String materialAbstract;
		private String attachment;
		
		@NotBlank
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		@NotBlank
		
		public String getMaterialAbstract() {
			return materialAbstract;
		}

		public void setMaterialAbstract(String materialAbstract) {
			this.materialAbstract = materialAbstract;
		}

		@URL
		
		public String getAttachment() {
			return attachment;
		}

		public void setAttachment(String attachment) {
			this.attachment = attachment;
		}

	
		
		
		//Relations
		
		private MasterClass masterClass;
		
		@NotNull
		@Valid	
		@ManyToOne(optional = false)
		public MasterClass getMasterClass() {
			return masterClass;
		}

		public void setMasterClass(MasterClass masterclass) {
			this.masterClass = masterclass;
		}
		

}
