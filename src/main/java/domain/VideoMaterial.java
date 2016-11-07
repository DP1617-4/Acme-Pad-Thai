package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class VideoMaterial extends LearningMaterial{

	
	// Constructors -----------------------------------------------------------

				public VideoMaterial() {
					super();
				}

				// Attributes -------------------------------------------------------------

				private String identifier;
				
				@NotBlank
				
				public String getIdentifier() {
					return identifier;
				}

				public void setIdentifier(String identifier) {
					this.identifier = identifier;
				}

}
