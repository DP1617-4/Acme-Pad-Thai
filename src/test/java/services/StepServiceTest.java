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
import domain.Recipe;
import domain.Step;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class StepServiceTest extends AbstractTest {

	//Service under test---------------
	
	@Autowired
	private StepService stepService;
	//supporting services-------------------
	@Autowired
	private RecipeService recipeService;
	
	//Tests---------------
	
	@Test
	public void testCreatePositive() {
		super.authenticate("user1");
		Recipe recipe = recipeService.findOne(76);
		Step step =  stepService.create(recipe);
		Assert.notNull(step.getRecipe());
	}
	
	@Test
	public void testSavePositive() {
		super.authenticate("user1");
		Recipe recipe = recipeService.findOne(76);
		Step step =  stepService.create(recipe);
		step.setHints("example of hints");
		
		Collection<String> pictures = new ArrayList<String>();
		pictures.add("http://dasdlasdkjas.com");
		pictures.add("http://omfg.org");
		step.setDescription("Funciona pls");
		step.setPictures(pictures);
		step.setStepNumber(2);
		Step saved = stepService.save(step);
		
		Collection<Step> allSteps= stepService.findAll();
		
		Assert.isTrue(allSteps.contains(saved));
		
	}
	
	@Test
	public void testSaveNegative() {
		super.authenticate("user1");
		Recipe recipe = recipeService.findOne(76);
		Step step =  stepService.create(recipe);
		try{
			Step saved = stepService.save(step);
		}
		catch(Exception e){
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void testDeletePositive() {
		super.authenticate("user1");
		Recipe recipe = recipeService.findOne(76);
		Step step =  stepService.create(recipe);
		step.setHints("example of hints");
		
		Collection<String> pictures = new ArrayList<String>();
		pictures.add("http://dasdlasdkjas.com");
		pictures.add("http://omfg.org");
		step.setDescription("Funciona pls");
		step.setPictures(pictures);
		step.setStepNumber(2);
		Step saved = stepService.save(step);
		
		stepService.delete(saved);
		
		Collection<Step> allSteps = stepService.findAll();
		
		Assert.isTrue(!allSteps.contains(saved));
		
	}
	
	@Test
	public void testCreateStepCopy() {
		super.authenticate("user1");
		Recipe recipe = recipeService.findOne(76);
		Step step =  stepService.create(recipe);
		step.setHints("example of hints");
		step.setDescription("Funciona pls");
		Collection<String> pictures = new ArrayList<String>();
		pictures.add("http://dasdlasdkjas.com");
		pictures.add("http://omfg.org");
		step.setPictures(pictures);
		step.setStepNumber(3);
		Step saved = stepService.save(step);
		Step copy = stepService.createCopy(saved);
		
		Collection<Step> allSteps = stepService.findAll();
		
		Assert.isTrue(allSteps.contains(copy));
		Assert.isTrue(saved.getDescription().equals(copy.getDescription()));
		Assert.isTrue(saved.getHints().equals(copy.getHints()));
		Assert.isTrue(saved.getId()!=copy.getId());
		Assert.isTrue(saved.getPictures().containsAll(copy.getPictures()));
		Assert.isTrue(saved.getStepNumber()==(copy.getStepNumber()));
		Assert.isTrue(saved.getRecipe().equals(copy.getRecipe()));
		
	}
}
