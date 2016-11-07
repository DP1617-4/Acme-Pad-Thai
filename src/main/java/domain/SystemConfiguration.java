package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class SystemConfiguration extends DomainEntity {
	
	//Constructor
	
	public SystemConfiguration(){
		super();
	}
	
	//Attributes
	
	private Collection<String> keywords;
	private double fee;
	
	@ElementCollection
	@Column(unique = true)
	public Collection<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(Collection<String> keywords) {
		this.keywords = keywords;
	}
	@Min(0)
	@NotNull
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	

}
