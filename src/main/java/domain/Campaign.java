package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.PROPERTY)
public class Campaign extends DomainEntity{

	// Constructors -----------------------------------------------------------

			public Campaign() {
				super();
			}

			// Attributes -------------------------------------------------------------

			private Date startDate;
			private Date endDate;
			private Boolean starred;
			private Boolean deleted;
			
			@NotNull
			public Date getStartDate() {
				return startDate;
			}
			public void setStartDate(Date startDate) {
				this.startDate = startDate;
			}
			
			@NotNull
			public Date getEndDate() {
				return endDate;
			}
			public void setEndDate(Date endDate) {
				this.endDate = endDate;
			}
			
			@NotNull
			public Boolean getStarred() {
				return starred;
			}
			public void setStarred(Boolean starred) {
				this.starred = starred;
			}
			
			@NotNull
			public Boolean getDeleted() {
				return deleted;
			}
			public void setDeleted(Boolean deleted) {
				this.deleted = deleted;
			}
			
			
			//Relations
			
			private Sponsor sponsor;
			private Collection<Banner> banners;

			@NotNull
			@Valid	
			@ManyToOne(optional = false)
			public Sponsor getSponsor() {
				return sponsor;
			}
			public void setSponsor(Sponsor sponsor) {
				this.sponsor = sponsor;
			}
			
			
			@Valid	
			@OneToMany(mappedBy = "campaign")
			public Collection<Banner> getBanners() {
				return banners;
			}
			public void setBanners(Collection<Banner> banners) {
				this.banners = banners;
			}
}
