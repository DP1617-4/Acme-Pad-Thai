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
import domain.Comment;
import domain.Recipe;
import domain.SocialUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class SocialUserServiceTest extends AbstractTest{
	
	//Service under test---------------
	
		@Autowired
		private SocialUserService socialUserService;
		
		@Autowired
		private CommentService commentService;
		
		@Autowired
		private RecipeService recipeService;
		
		//Tests---------------
		
		@Test
		public void testSavePositive(){
			
			authenticate("user1");
			SocialUser socialUser = socialUserService.findOne(13);
//			Comment comment = commentService.create();
//			Collection<Comment> comments = socialUser.getComments();
//			comments.add(comment);
//			socialUser.setComments(comments);
			socialUser.setEmail("asfdsgd@email.com");
			SocialUser saved = socialUserService.save(socialUser);
			Assert.isTrue(socialUserService.findAll().contains(saved));
			
			unauthenticate();
		}
		
//		@Test
//		public void testDelete(){
//			
//			authenticate("user1");
//			SocialUser socialUser = null;
//			Comment comment = commentService.create();
//			Collection<Comment> comments = socialUser.getComments();
//			comments.add(comment);
//			socialUser.setComments(comments);
//			SocialUser saved = socialUserService.save(socialUser);
//			socialUserService.delete(saved);
//			Assert.isTrue(!socialUserService.findAll().contains(saved));
//			unauthenticate();
//		}
		
		@Test
		public void testFollow(){
			
			authenticate("user1");
			SocialUser follower;
			SocialUser socialUser = socialUserService.findOne(14);
			socialUserService.follow(socialUser);
			follower = socialUserService.findByPrincipal();
			socialUser = socialUserService.findOne(14);
			Assert.isTrue(follower.getFollowed().contains(socialUser) && socialUser.getFollowers().contains(follower));
			unauthenticate();
		}
		
//		@Test
//		public void testComment(){
//			
//			authenticate("user1");
//			Recipe recipe;
//			recipe = recipeService.findOne(75);
//			Comment comment = commentService.create(recipe);
//			socialUserService.comment(comment);
//			Assert.isTrue(socialUserService.findByPrincipal().getComments().contains(comment));
//			unauthenticate();
//		}
		
//		@Test
//		public void testLikes(){
//			authenticate("user1");
//		}
//		
		
		
		
		
		
		
		
		
		
		
		
		
	
}