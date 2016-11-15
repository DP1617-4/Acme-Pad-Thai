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
public class SponsorServiceTest extends AbstractTest{

	//Service under test---------------
	@Autowired
	private SponsorService sponsorService;
	@Autowired
	private CreditCardService creditCardService;
	
	//Tests----------------------------
	@Test
	public void testCreate() {
		authenticate("sponsor1");
		Sponsor sponsor;
		sponsor = sponsorService.create();
		sponsor = sponsorService.findByPrincipal();
		Assert.notNull(sponsor.getCompanyName());
		Assert.notNull(sponsor.getCreditCard());
		unauthenticate();
	}
	
	@Test
	public void testSave() {
		authenticate("sponsor1");
		Sponsor sponsor = sponsorService.create();
		sponsor = sponsorService.findByPrincipal();
		sponsor.setCompanyName("Tututua Company");
		Sponsor saved = sponsorService.save(sponsor);
		Collection<Sponsor> allSponsors = sponsorService.findAll();
		Assert.isTrue(allSponsors.contains(saved));
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
		}
		catch(Exception e) {
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
		unauthenticate();
	}
	
	@Test
	public void testDelete() {
		authenticate("sponsor1");
		Sponsor sponsor = sponsorService.create();
		
		Collection<Bill> bills = new ArrayList<Bill>();
		sponsor.setBills(bills);
		Collection<Campaign> campaigns = new ArrayList<Campaign>();
		sponsor.setCampaigns(campaigns);
		CreditCard creditCard = creditCardService.create(sponsor);
		sponsor.setCreditCard(creditCard);
		sponsor.setName("Lalala");
		sponsor.setSurname("jujuju");
		sponsor.setPhone("C821");
		sponsor.setCompanyName("Tratrata");
		sponsor.setEmail("sponsor@gmail.com");
		sponsor.setPostalAddress("calle sponsors");
		Collection<Folder> folders = new ArrayList<Folder>();
		sponsor.setFolders(folders);
		Collection<SocialIdentity> socials = new ArrayList<SocialIdentity>();
		sponsor.setSocialIdentities(socials);
		
		Sponsor saved = sponsorService.save(sponsor);
		sponsorService.delete(saved);
		Collection<Sponsor> allSponsors = sponsorService.findAll();
		Assert.isTrue(!allSponsors.contains(saved));
		unauthenticate();
	}
}
