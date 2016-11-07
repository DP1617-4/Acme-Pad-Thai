package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class PresentationMaterial extends LearningMaterial{

	// Constructors -----------------------------------------------------------

			public PresentationMaterial() {
				super();
			}

			// Attributes -------------------------------------------------------------

			private String path;
			
			@NotBlank
			
			public String getPath() {
				return path;
			}

			public void setPath(String path) {
				this.path = path;
			}

}
