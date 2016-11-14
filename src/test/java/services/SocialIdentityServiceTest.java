package services;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Actor;
import domain.SocialIdentity;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class SocialIdentityServiceTest extends AbstractTest {
	
	//Service under test
	
	@Autowired
	private SocialIdentityService socialIdService;
	
	@Autowired
	private ActorService actorService;
	
	public void testCreate(){
		authenticate("user1");
		Actor actor;
		SocialIdentity socialId;
		actor = actorService.findByPrincipal();
		socialId = socialIdService.create();
		Assert.isTrue(actor.equals(socialId.getActor()));
		unauthenticate();
	}
	
	public void testSave(){
		authenticate("user1");
		Actor actor;
		SocialIdentity socialId;
		actor = actorService.findByPrincipal();
		socialId = socialIdService.create();
		socialId.setNick("userNick");
		socialId.setSocialNetworkLink("http://www.linkedin.com");
		socialId.setSocialNetworkName("LinkedIn");
		
		
		unauthenticate();

	}
	
	public void testDelete(){
		
	}

	
	public void testFindAllByPrincipal(){
		
	}
	
	public void testFindOne(){
		
	}
}
