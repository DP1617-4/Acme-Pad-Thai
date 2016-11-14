package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import security.LoginService;
import security.UserAccount;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	//managed repository---------------------
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	//supporting services -------------------
	
	//Basic CRUD methods --------------------
	public CreditCard create() {
		CreditCard created;
		created = new CreditCard();
		Assert.isTrue(expirationDate(created));
		return created;
	}
	
	public CreditCard findOne(int creditCardId) {
		CreditCard retrieved;
		retrieved = creditCardRepository.findOne(creditCardId);
		return retrieved;
	}
	
	public CreditCard save(CreditCard creditCard) {
		CreditCard saved;
		saved = creditCardRepository.save(creditCard);
		return saved;
	}
	
	public void delete(CreditCard creditCard) {
		creditCardRepository.delete(creditCard);
	}
	
	//Auxiliary methods ---------------------
	@SuppressWarnings("deprecation")
	private boolean expirationDate(CreditCard creditCard) {
		boolean res = false;
		Date moment = new Date();
		if(creditCard.getExpirationYear() >= moment.getYear()) {
			if (creditCard.getExpirationMonth() >= moment.getMonth()) {
				res = true;
			}
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
