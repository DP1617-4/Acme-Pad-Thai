package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsor extends Actor{

	// Constructors -----------------------------------------------------------

	public Sponsor() {
		super();
	}
	
	//Attributes
	
	private String companyName;
	
	@NotBlank
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	//Relations
	
	private CreditCard creditCard;
	private Collection<Bill> bills;
	private Collection<Campaign> campaigns;
	
	@NotNull
	@Valid	
	@OneToOne(optional=false, mappedBy="sponsor", cascade = CascadeType.PERSIST)
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
		
	@Valid	
	@OneToMany(mappedBy = "sponsor")
	public Collection<Bill> getBills() {
		return bills;
	}
	public void setBills(Collection<Bill> bills) {
		this.bills = bills;
	}
		
	@Valid	
	@OneToMany(mappedBy = "sponsor")
	public Collection<Campaign> getCampaigns() {
		return campaigns;
	}
	public void setCampaigns(Collection<Campaign> campaigns) {
		this.campaigns = campaigns;
	}		
				
}
