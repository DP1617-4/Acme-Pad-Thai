package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Actor;
import domain.MasterClass;
import domain.User;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class ActorServiceTest extends AbstractTest {
	
	//Service under test
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MasterClassService masterClassService;
	
	@Test
	public void testFindByPrincipal(){
		authenticate("user1");
		Actor actor = actorService.findByPrincipal();
		User user;
		user = userService.findByPrincipal();
		Assert.isTrue(actor.getClass().equals(user.getClass()));
		unauthenticate();
	}
	
	@Test
	public void testRegister(){
		authenticate("cook1");
		Actor actor;
		actor = actorService.findByPrincipal();
		MasterClass masterClass;
		masterClass = masterClassService.findOneToEdit(121);
		actorService.register(masterClass);
		Assert.isTrue(actor.getEnroled().contains(masterClass));
		unauthenticate();
	}

}
