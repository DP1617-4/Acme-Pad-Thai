package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Bill;
import domain.Campaign;
import domain.CreditCard;
import domain.Folder;
import domain.SocialIdentity;
import domain.Sponsor;

//TODO: this file provides an incomplete template; complete it with the appropriate annotations and method implementations.
//TODO: do not forget to add appropriate sectioning comments, e.g., "System under test" and "Tests".


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class CreditCardServiceTest extends AbstractTest{

	//Service under test---------------
	@Autowired
	private CreditCardService creditCardService;
	@Autowired
	private SponsorService sponsorService;
	
	//Tests----------------------------
	@Test
	public void testCreate() {
		authenticate("sponsor1");
		Sponsor sponsor = sponsorService.findOne(20);
		CreditCard creditCard = creditCardService.create(sponsor);
		Assert.notNull(creditCard.getSponsor());
		unauthenticate();
	}
	
//	@Test
//	public void testDelete() {
//		Sponsor sponsor = sponsorService.create();
//		Sponsor result;
//		
//		Collection<Bill> bills = new ArrayList<Bill>();
//		sponsor.setBills(bills);
//		Collection<Campaign> campaigns = new ArrayList<Campaign>();
//		sponsor.setCampaigns(campaigns);
//		sponsor.setName("Lalala");
//		sponsor.setSurname("jujuju");
//		sponsor.setPhone("C821");
//		sponsor.setCompanyName("Tratrata");
//		sponsor.setEmail("sponsor@gmail.com");
//		sponsor.setPostalAddress("calle sponsors");
//		sponsor.getUserAccount().setUsername("SponsorT");
//		sponsor.getUserAccount().setPassword("TSponsor");
//		Collection<Folder> folders = new ArrayList<Folder>();
//		sponsor.setFolders(folders);
//		Collection<SocialIdentity> socials = new ArrayList<SocialIdentity>();
//		sponsor.setSocialIdentities(socials);
//		
//		result = sponsorService.save(sponsor);
//		
//		CreditCard creditCard = creditCardService.create(result);
//		creditCard.setHolderName("Francis");
//		creditCard.setBrandName("VISA");
//		creditCard.setCCNumber("1111222244446666");
//		creditCard.setExpirationMonth(12);
//		creditCard.setExpirationYear(19);
//		creditCard.setCVV(842);
//		creditCard.setSponsor(sponsor);
//		
//		creditCardService.delete(creditCard);
//		sponsorService.delete(sponsor);
//		
//		Collection<CreditCard> allCreditCards = creditCardService.findAll();
//		Assert.isTrue(!allCreditCards.contains(creditCard));
//		unauthenticate();
//	}
	
	@Test
	public void testSave() {
		authenticate("sponsor1");
		Sponsor sponsor = sponsorService.findOne(20);
		CreditCard creditCard = sponsor.getCreditCard();
		creditCard.setHolderName("Francis");
		creditCard.setBrandName("VISA");
		creditCard.setCCNumber("1111222244446666");
		creditCard.setExpirationMonth(12);
		creditCard.setExpirationYear(19);
		creditCard.setCVV(842);
		CreditCard saved = creditCardService.save(creditCard);
		sponsor = sponsorService.findOne(20);
		Assert.isTrue(sponsor.getCreditCard().equals(saved));
		unauthenticate();
	}
	
	@Test
	public void testSaveNegative() {
		authenticate("sponsor1");
		Sponsor sponsor = sponsorService.findOne(20);
		CreditCard creditCard = creditCardService.create(sponsor);
		creditCard.setHolderName(null);
		creditCard.setBrandName("VISA");
		creditCard.setCCNumber("1111222244446666");
		creditCard.setExpirationMonth(12);
		creditCard.setExpirationYear(19);
		creditCard.setCVV(842);
		try {
			CreditCard saved = creditCardService.save(creditCard);
		}
		catch(Exception e) {
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
		unauthenticate();
	}
	
}
