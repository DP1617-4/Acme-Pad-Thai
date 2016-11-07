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


@Entity
@Access(AccessType.PROPERTY)
public class Bill extends DomainEntity{

	
	// Constructors -----------------------------------------------------------

			public Bill() {
				super();
			}

			// Attributes -------------------------------------------------------------

			private Date creationDate;
			private Date paymentDate;
			private double cost;
			private String description;
			
			@Past
			@NotNull
			public Date getCreationDate() {
				return creationDate;
			}

			public void setCreationDate(Date creationDate) {
				this.creationDate = creationDate;
			}

			@Past
			public Date getPaymentDate() {
				return paymentDate;
			}

			public void setPaymentDate(Date paymentDate) {
				this.paymentDate = paymentDate;
			}

			@NotNull
			public double getCost() {
				return cost;
			}

			public void setCost(double cost) {
				this.cost = cost;
			}

			@NotBlank
			
			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			
			//Relations
			
			private Sponsor sponsor;
			
			@NotNull
			@Valid	
			@ManyToOne(optional = false)
			public Sponsor getSponsor() {
				return sponsor;
			}

			public void setSponsor(Sponsor sponsor) {
				this.sponsor = sponsor;
			}
			

}
