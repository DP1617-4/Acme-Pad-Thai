package services;

import static org.junit.Assert.fail;

import java.util.Collection;

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
public class SponsorServiceTest extends AbstractTest{

	//Service under test---------------
	@Autowired
	private SponsorService sponsorService;
	@Autowired
	private CreditCardService creditCardService;
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
		Assert.isTrue(actor.equals(sponsor));
		Assert.isNull(sponsor.getCompanyName());
		Assert.isNull(sponsor.getCreditCard());
		unauthenticate();
	}
	
	@Test
	public void testSave() {
		authenticate("sponsor1");
		Sponsor sponsor;
		CreditCard creditCard = creditCardService.create();
		sponsor = sponsorService.create();
		sponsor.setCompanyName("Tututua Company");
		sponsor.setCreditCard(creditCard);
		Sponsor saved = sponsorService.save(sponsor);
		unauthenticate();
	}
	
	@Test
	public void testSaveNegative() {
		authenticate("sponsor1");
		Sponsor sponsor;
		sponsor = sponsorService.create();
		sponsor.setCompanyName(null);
		sponsor.setCreditCard(null);
		try {
			Sponsor saved = sponsorService.save(sponsor);
			fail("Null values are not allowed.");
		}
		catch(Exception e) {
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
		unauthenticate();
	}
	
	@Test
	public void testDelete() {
		authenticate("sponsor1");
		Sponsor sponsor;
		CreditCard creditCard = creditCardService.create();
		sponsor = sponsorService.create();
		sponsor.setCompanyName("Tututua Company");
		sponsor.setCreditCard(creditCard);
		Sponsor saved = sponsorService.save(sponsor);
		sponsorService.delete(saved);
		Collection<Sponsor> allSponsors = sponsorService.findAll();
		Assert.isTrue(!(allSponsors.contains(saved)));
		unauthenticate();
	}
	
	@Test
	public void testDeleteNegative() {
		
	}
	
	@Test
	public void testFindAll() {
		authenticate("sponsor2");
		Sponsor sponsor;
		CreditCard creditCard = creditCardService.create();
		sponsor = sponsorService.create();
		sponsor.setCompanyName("Tututua Company");
		sponsor.setCreditCard(creditCard);
		Sponsor saved = sponsorService.save(sponsor);
		Collection<Sponsor> allSponsors = sponsorService.findAll();
		Assert.isTrue((allSponsors.contains(saved)));
		unauthenticate();
	}
	
}
