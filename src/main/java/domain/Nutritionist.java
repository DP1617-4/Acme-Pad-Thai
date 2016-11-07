package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;


@Entity
@Access(AccessType.PROPERTY)
public class Nutritionist extends SocialUser{
	// Constructors -----------------------------------------------------------

		public Nutritionist() {
			super();
		}
		
		
		//Relations
		
		private Curricula curricula;

		@Valid
		@OneToOne(optional = true)
		public Curricula getCurricula() {
			return curricula;
		}


		public void setCurricula(Curricula curricula) {
			this.curricula = curricula;
		}
		
	}
