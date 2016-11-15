package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import security.LoginService;
import security.UserAccount;
import domain.CreditCard;
import domain.Sponsor;

@Service
@Transactional
public class CreditCardService {

	//managed repository---------------------
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	//supporting services -------------------
	@Autowired
	private SponsorService sponsorService;
	
	//Basic CRUD methods --------------------
	public CreditCard create(Sponsor sponsor) {
		sponsor = sponsorService.findByPrincipal();
		Assert.notNull(sponsor,"Dear user, you are not a sponsor.");
		CreditCard creditCard = new CreditCard();
		creditCard.setSponsor(sponsor);
		return creditCard;
	}
	
	public CreditCard findOne(int creditCardId) {
		Sponsor sponsor = sponsorService.findByPrincipal();
		Assert.notNull(sponsor,"Dear user, you are not a sponsor.");
		CreditCard retrieved = creditCardRepository.findOne(creditCardId);
		return retrieved;
	}
	
	public Collection<CreditCard> findAll() {
		return creditCardRepository.findAll();
	}
	
	public CreditCard save(CreditCard creditCard) {
		UserAccount sponsor = LoginService.getPrincipal();
		Assert.isTrue(creditCard.getSponsor().getUserAccount().equals(sponsor));
//		Assert.isTrue(expirationDate(creditCard));
		
		CreditCard saved = creditCardRepository.save(creditCard);
		return saved;
	}
	
	public void delete(CreditCard creditCard) {
		creditCardRepository.delete(creditCard);
	}
	
	//Auxiliary methods ---------------------
	private boolean expirationDate(CreditCard creditCard) {
		boolean res = false;
		Calendar moment = new GregorianCalendar();
		if(creditCard.getExpirationYear() == moment.get(Calendar.YEAR)) {
			if (creditCard.getExpirationMonth() >= moment.get(Calendar.MONTH)) {
				res = true;
			}
		} else if (creditCard.getExpirationYear() > moment.get(Calendar.YEAR)) {
			res = true;
		}
		return res;
	}
	
	//Our other bussiness methods -----------
	public CreditCard save2(CreditCard creditCard) { // Requirement 33.2
		UserAccount sponsor;
		sponsor = LoginService.getPrincipal();
		Assert.isTrue(creditCard.getSponsor().equals(sponsor));
		Assert.isTrue(expirationDate(creditCard));
		CreditCard saved = this.save(creditCard);
		return saved;
	}
	
}
