package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity {
	
	// Constructors -----------------------------------------------------------

			public Banner() {
				super();
			}

			// Attributes -------------------------------------------------------------

			private String URL;
			private int maxNumber;
			private int timesShown;
			private int timesShownMonth;
			
			@NotBlank
			@URL
			public String getURL() {
				return URL;
			}
			public void setURL(String uRL) {
				URL = uRL;
			}
			
			@NotNull
			@Min(1)
			public int getMaxNumber() {
				return maxNumber;
			}
			public void setMaxNumber(int maxNumber) {
				this.maxNumber = maxNumber;
			}
			
			@Min(0)
			public int getTimesShown() {
				return timesShown;
			}
			public void setTimesShown(int timesShown) {
				this.timesShown = timesShown;
			}
			
			@Min(0)
			public int getTimesShownMonth() {
				return timesShownMonth;
			}
			public void setTimesShownMonth(int timesShownMonth) {
				this.timesShownMonth = timesShownMonth;
			}
			
			//Relations
			private Campaign campaign;

			@NotNull
			@Valid	
			@ManyToOne(optional = false)
			public Campaign getCampaign() {
				return campaign;
			}
			public void setCampaign(Campaign campaign) {
				this.campaign = campaign;
			}
}
