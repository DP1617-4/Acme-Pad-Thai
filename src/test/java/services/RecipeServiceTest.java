package services;

import static org.junit.Assert.fail;


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
import domain.Category;
import domain.Quantity;
import domain.Recipe;
import domain.Step;

//TODO: this file provides an incomplete template; complete it with the appropriate annotations and method implementations.
//TODO: do not forget to add appropriate sectioning comments, e.g., "System under test" and "Tests".


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class RecipeServiceTest extends AbstractTest {

	//Service under test---------------
	
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private UserService userService;
	
	
	//Tests---------------
	
	
	
	@Test
	public void testCreatePositive() {
		super.authenticate("user1");
		Recipe recipe =  recipeService.create();
		Assert.isTrue(userService.findByPrincipal().equals(recipe.getUser()));
		Assert.notNull(recipe.getAuthored());
		Assert.isTrue(!recipe.getDeleted());
		
	}
	
	@Test
	public void testSavePositive() {
		super.authenticate("user1");
		Recipe recipe =  recipeService.create();
		recipe.setHints("example of hints");
		
		Collection<String> pictures = new ArrayList<String>();
		pictures.add("http://dasdlasdkjas.com");
		pictures.add("http://omfg.org");
		
		recipe.setPictures(pictures);
		recipe.setSummary("random summary");
		recipe.setTitle("apetecán");
		Recipe saved = recipeService.save(recipe);
		
		Collection<Recipe> allRecipes = recipeService.findAll();
		
		Assert.isTrue(allRecipes.contains(saved));
		
	}
	
	@Test
	public void testSaveNegative() {
		super.authenticate("user1");
		Recipe recipe =  recipeService.create();
		try{
			Recipe saved = recipeService.save(recipe);
		}
		catch(Exception e){
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
		
	}
	
	@Test
	public void testDeletePositive() {
		super.authenticate("user1");
		Recipe recipe =  recipeService.create();
		recipe.setHints("example of hints");
		
		Collection<String> pictures = new ArrayList<String>();
		pictures.add("http://dasdlasdkjas.com");
		pictures.add("http://omfg.org");
		
		recipe.setPictures(pictures);
		recipe.setSummary("random summary");
		recipe.setTitle("apetecán");
		Recipe saved = recipeService.save(recipe);
		
		recipeService.delete(saved);
		
		Collection<Recipe> allRecipes = recipeService.findAll();
		
		Assert.isTrue(!allRecipes.contains(saved));
		
	}
	
	@Test
	public void testCreateRecipeCopy() {
		super.authenticate("user1");
		Recipe recipe =  recipeService.create();
		recipe.setHints("example of hints");
		
		Collection<String> pictures = new ArrayList<String>();
		pictures.add("http://dasdlasdkjas.com");
		pictures.add("http://omfg.org");
		recipe.setPictures(pictures);
		
		Collection<Step> steps = new ArrayList<Step>();
		recipe.setSteps(steps);
		
		Collection<Category> categories = new ArrayList<Category>();
		recipe.setCategories(categories);
		
		Collection<Quantity> quantities = new ArrayList<Quantity>();
		recipe.setQuantities(quantities);
		
		recipe.setSummary("random summary");
		recipe.setTitle("apetecán");
		Recipe saved = recipeService.save(recipe);
		
		Recipe copy = recipeService.createCopyForContest(saved);
		
		Collection<Recipe> allRecipes = recipeService.findAll();
		
		Assert.isTrue(allRecipes.contains(copy));
		Assert.isTrue(saved.getAuthored().equals(copy.getAuthored()));
		Assert.isTrue(saved.getCategories().containsAll(copy.getCategories()));
		Assert.isTrue(saved.getId()!=copy.getId());
		Assert.isTrue(saved.getTitle().equals(copy.getTitle()));
		Assert.isTrue(!(saved.getTicker().equals(copy.getTicker())));
		
	}
	
	@Test
	public void testDelete2() {
		super.authenticate("user1");
		Recipe recipe =  recipeService.create();
		recipe.setHints("example of hints");
		
		Collection<String> pictures = new ArrayList<String>();
		pictures.add("http://dasdlasdkjas.com");
		pictures.add("http://omfg.org");
		recipe.setPictures(pictures);
		
		Collection<Step> steps = new ArrayList<Step>();
		recipe.setSteps(steps);
		
		Collection<Category> categories = new ArrayList<Category>();
		recipe.setCategories(categories);
		
		Collection<Quantity> quantities = new ArrayList<Quantity>();
		recipe.setQuantities(quantities);
		
		recipe.setSummary("random summary");
		recipe.setTitle("apetecán");
		Recipe saved = recipeService.save(recipe);
		Recipe deleted = recipeService.delete2(saved);
		
		Assert.isTrue(recipeService.findAll().contains(deleted));
		Assert.isTrue(deleted.getDeleted()==true);
		
		
	}
	
	@Test
	public void testFindAllNotDeleted() {
		super.authenticate("user1");
		Recipe recipe =  recipeService.create();
		recipe.setHints("example of hints");
		
		Collection<String> pictures = new ArrayList<String>();
		pictures.add("http://dasdlasdkjas.com");
		pictures.add("http://omfg.org");
		recipe.setPictures(pictures);
		
		Collection<Step> steps = new ArrayList<Step>();
		recipe.setSteps(steps);
		
		Collection<Category> categories = new ArrayList<Category>();
		recipe.setCategories(categories);
		
		Collection<Quantity> quantities = new ArrayList<Quantity>();
		recipe.setQuantities(quantities);
		
		recipe.setSummary("random summary");
		recipe.setTitle("apetecán");
		Recipe saved = recipeService.save(recipe);
		Recipe deleted = recipeService.delete2(saved);
		
		Assert.isTrue(!(recipeService.findAllNotDeleted().contains(deleted)));
	}


}

