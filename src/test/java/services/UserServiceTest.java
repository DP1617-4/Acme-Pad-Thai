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

import repositories.UserRepository;
import security.UserAccount;

import domain.Comment;
import domain.Folder;
import domain.MasterClass;
import domain.Recipe;
import domain.Score;
import domain.SocialIdentity;
import domain.SocialUser;
import domain.User;

import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class UserServiceTest extends AbstractTest{
	
	//Service under test---------------
	
		@Autowired
		private UserService userService;
		
		@Autowired
		private RecipeService recipeService;
		
		//Tests---------------
		
		
		
		@Test
		public void testCreate() {
			authenticate(null);
			User user =  userService.create();
			Assert.isTrue(user.getRecipes().isEmpty());
			Assert.isTrue(user.getComments().isEmpty());
			Assert.isTrue(user.getEnroled().isEmpty());
			Assert.isTrue(user.getFolders().isEmpty());
			Assert.isTrue(user.getFollowed().isEmpty());
			Assert.isTrue(user.getFollowers().isEmpty());
			Assert.isTrue(user.getScores().isEmpty());
			Assert.isTrue(user.getSocialIdentities().isEmpty());
			
			
		}
		
		@Test
		public void testSavePositive(){
			authenticate(null);
			User user = userService.create();

			user.setEmail("rgreg@hotmail.com");
			user.setName("blae");
			user.setSurname("miswe");
			user.setPhone("1234");
			user.setPostalAddress("calle paraiso bloque el que me de la gana");
			
			User saved = userService.save(user);
			Assert.isTrue(userService.findAll().contains(saved));
		}
		
//		@Test
//		public void testDelete(){
//			
//			
//			User user = userService.findOne(13);
//			userService.delete(user);
//			Assert.isTrue(!userService.findAll().contains(user));
//			
//		}
	

}
