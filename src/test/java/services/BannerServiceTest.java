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
import domain.Banner;
import domain.Campaign;

//TODO: this file provides an incomplete template; complete it with the appropriate annotations and method implementations.
//TODO: do not forget to add appropriate sectioning comments, e.g., "System under test" and "Tests".


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class BannerServiceTest extends AbstractTest{

	//Service under test---------------
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private CampaignService campaignService;
	
	//Tests----------------------------
	@Test
	public void testCreate() {
		authenticate("sponsor1");
		Campaign campaign = campaignService.findOne(115);
		Banner created = bannerService.create(campaign);
		unauthenticate();
	}
	
	@Test
	public void testSave() {
		authenticate("sponsor1");
		Campaign campaign = campaignService.findOne(115);
		Banner banner = bannerService.create(campaign);
		banner.setURL("http://www.banner.org");
		banner.setMaxNumber(2);
		Banner saved = bannerService.save(banner);
		Collection<Banner> allBanners = bannerService.findAll();
		Assert.isTrue(allBanners.contains(saved));
		unauthenticate();
	}
	
	@Test
	public void testSaveNegative() {
		authenticate("sponsor1");
		Campaign campaign = campaignService.findOne(115);
		Banner banner = bannerService.create(campaign);
		banner.setURL(null);
		try {
			Banner saved = bannerService.save(banner);
		}
		catch(Exception e) {
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
		unauthenticate();
	}
	
	@Test
	public void testDelete() {
		authenticate("sponsor1");
		Campaign campaign = campaignService.findOne(115);
		Banner banner = bannerService.create(campaign);
		banner.setURL("http://www.banner.org");
		banner.setMaxNumber(2);
		Banner saved = bannerService.save(banner);
		bannerService.delete(saved);
		Collection<Banner> allBanners = bannerService.findAll();
		Assert.isTrue(!(allBanners.contains(saved)));
		unauthenticate();
	}
	
}
