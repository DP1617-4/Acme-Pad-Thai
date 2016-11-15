package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Campaign;
import domain.Sponsor;

//TODO: this file provides an incomplete template; complete it with the appropriate annotations and method implementations.
//TODO: do not forget to add appropriate sectioning comments, e.g., "System under test" and "Tests".


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class CampaignServiceTest extends AbstractTest{

	//Service under test---------------
	@Autowired
	private CampaignService campaignService;
	@Autowired
	private SponsorService sponsorService;
	
	//Tests----------------------------

	@Test
	public void testCreate() {
		authenticate("sponsor1");
		Sponsor sponsor = sponsorService.findOne(20);
		Campaign campaign = campaignService.create(sponsor);
		unauthenticate();
	}
	
	@Test
	public void testSave() {
		authenticate("sponsor1");
		Campaign campaign = campaignService.findOne(115);
		Campaign saved = campaignService.save(campaign);
		Collection<Campaign> allComments = campaignService.findAllNotDeleted();
		Assert.isTrue(allComments.contains(saved));
		unauthenticate();
	}
	
	@Test
	public void testSaveNegative() {
		authenticate("sponsor1");
		Sponsor sponsor = sponsorService.findOne(20);
		Campaign campaign =  campaignService.create(sponsor);
		try{
			Campaign saved = campaignService.save(campaign);
		}
		catch(Exception e){
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void testDelete() {
		authenticate("sponsor1");
		Campaign campaign = campaignService.findOne(115);
		Campaign saved = campaignService.save(campaign);
		campaignService.delete(saved);
//		Collection<Campaign> allCampaigns = campaignService.findAllNotDeleted();
//		Assert.isTrue(!allCampaigns.contains(saved));
	}
}
