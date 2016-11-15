package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
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
	
	@Test
	public void testCreate(){
		authenticate("user1");
		Actor actor;
		SocialIdentity socialId;
		actor = actorService.findByPrincipal();
		socialId = socialIdService.create();
		Assert.isTrue(actor.equals(socialId.getActor()));
		unauthenticate();
	}
	
	@Test
	public void testSave(){
		authenticate("user1");
		Actor actor;
		SocialIdentity socialId;
		SocialIdentity result;
		actor = actorService.findByPrincipal();
		socialId = socialIdService.create();
		socialId.setNick("userNick");
		socialId.setSocialNetworkLink("http://www.linkedin.com");
		socialId.setSocialNetworkName("LinkedIn");
		socialId.setPicture("http://www.gyazo.com/isudnf");
		result = socialIdService.save(socialId);
		
		unauthenticate();

	}
	
	@Test
	public void testSaveNegative(){
		authenticate("user1");
		Actor actor;
		SocialIdentity socialId;
		SocialIdentity result;
		actor = actorService.findByPrincipal();
		socialId = socialIdService.create();
		socialId.setNick("userNick");
		socialId.setSocialNetworkLink("http://www.linkedin.com");
		socialId.setSocialNetworkName("LinkedIn");
		socialId.setPicture("http://www.gyazo.com/isudnf");
		authenticate("user2");
		try{
		result = socialIdService.save(socialId);
		} catch (Exception e){
			System.out.println("Success testSaveNegative");
		}
		unauthenticate();

	}
	@Test
	public void testDelete(){
		authenticate("user1");
		Actor actor;
		SocialIdentity socialId;
		Collection<SocialIdentity> socialIds;
		actor = actorService.findByPrincipal();
		socialIds = socialIdService.findAllByPrincipal();
		socialId = socialIds.iterator().next();
		socialIdService.delete(socialId);
		socialIds = socialIdService.findAllByPrincipal();
		Assert.isTrue(!socialIds.contains(socialId));
		unauthenticate();
	}

	@Test
	public void testFindAllByPrincipal(){
		authenticate("user1");
		Actor actor;
		Collection<SocialIdentity> socialIds;
		actor = actorService.findByPrincipal();
		socialIds = socialIdService.findAllByPrincipal();
		System.out.println(socialIds);
		System.out.println(actor.getSocialIdentities());
		unauthenticate();
	}
	@Test
	public void testFindOne(){
		authenticate("user1");
		Actor actor;
		SocialIdentity socialId;
		actor = actorService.findByPrincipal();
		socialId = socialIdService.findOneToEdit(128);
		Assert.isTrue(actor.getSocialIdentities().contains(socialId));
		unauthenticate();
	}
}
