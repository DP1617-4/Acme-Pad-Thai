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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class CommentServiceTest extends AbstractTest {

	//Service under test---------------
	
	@Autowired
	private CommentService commentService;
	
	//supporting services-------------------
	
	@Autowired
	private UserService userService;
	@Autowired
	private RecipeService recipeService;
	
	//Tests---------------
	
	
	
	@Test
	public void testCreatePositive() {
		super.authenticate("user1");
		Recipe recipe = recipeService.findOne(76);
		Comment comment =  commentService.create(recipe);
		Assert.notNull(comment.getDate());
		
	}
	
	@Test
	public void testSavePositive() {
		super.authenticate("user1");
		Recipe recipe = recipeService.findOne(74);
		Comment comment =  commentService.create(recipe);
		comment.setText("example of hints");
		comment.setTitle("Title");
		Comment saved = commentService.save(comment);
		
		Collection<Comment> allComments = commentService.findAll();
		
		Assert.isTrue(allComments.contains(saved));
		
	}
	
	@Test
	public void testSaveNegative() {
		super.authenticate("user1");
		Recipe recipe = recipeService.findOne(74);
		Comment comment =  commentService.create(recipe);
		try{
			Comment saved = commentService.save(comment);
		}
		catch(Exception e){
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void testDeletePositive() {
		super.authenticate("user1");
		Recipe recipe = recipeService.findOne(74);
		Comment comment =  commentService.create(recipe);
		comment.setTitle("Title");
		comment.setText("Ez life");
		Comment saved = commentService.save(comment);
		
		commentService.delete(saved);
		
		Collection<Comment> allComments= commentService.findAll();
		
		Assert.isTrue(!allComments.contains(saved));
		
	}
}
