package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.CreditCard;
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
	@Autowired
	private ActorService actorService;
	
	//Tests----------------------------
	@Test
	public void testCreate() {
		authenticate("sponsor1");
		Actor actor;
		Sponsor sponsor;
		actor = actorService.findByPrincipal();
		sponsor = sponsorService.create();
		CreditCard creditCard;
		creditCard = creditCardService.create();
		Assert.isTrue(actor.equals(sponsor));
		creditCard.setSponsor(sponsor);
		Assert.isNull(creditCard.getHolderName());
		Assert.isNull(creditCard.getBrandName());
		Assert.isNull(creditCard.getCCNumber());
		Assert.isNull(creditCard.getExpirationMonth());
		Assert.isNull(creditCard.getExpirationYear());
		Assert.isNull(creditCard.getCVV());
		unauthenticate();
//		creditCard.setHolderName("Francis");
//		creditCard.setBrandName("VISA");
//		creditCard.setCCNumber("1111222244446666");
//		creditCard.setExpirationMonth(12);
//		creditCard.setExpirationYear(19);
//		creditCard.setCVV(842);
//		creditCard.setSponsor(sponsor);
	}
	
	@Test
	public void testSave() {
		authenticate("sponsor1");
		Actor actor;
		Sponsor sponsor;
		actor = actorService.findByPrincipal();
		sponsor = sponsorService.create();
		CreditCard creditCard;
		creditCard = creditCardService.create();
		Assert.isTrue(actor.equals(sponsor));
		creditCard.setSponsor(sponsor);
	}
	
	
}
