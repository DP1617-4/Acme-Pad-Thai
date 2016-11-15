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
import domain.Ingredient;

//TODO: this file provides an incomplete template; complete it with the appropriate annotations and method implementations.
//TODO: do not forget to add appropriate sectioning comments, e.g., "System under test" and "Tests".


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class IngredientServiceTest extends AbstractTest {

	//Service under test---------------
	@Autowired
	private IngredientService ingredientService;
	//Tests---------------
	
	
	
	@Test
	public void testCreatePositive() {
		Ingredient ingredient =  ingredientService.create();
		Assert.isTrue(!ingredient.getDeleted());
		
	}
	
	@Test
	public void testSavePositive() {
		Ingredient ingredient =  ingredientService.create();
		
		Collection<String> pictures = new ArrayList<String>();
		pictures.add("http://dasdlasdkjas.com");
		pictures.add("http://omfg.org");
		
		ingredient.setPictures(pictures);
		ingredient.setDescription("random summary");
		ingredient.setName("apetecán");
		Ingredient saved = ingredientService.save(ingredient);
		
		Collection<Ingredient> allIngredients = ingredientService.findAll();
		
		Assert.isTrue(allIngredients.contains(saved));
		
	}
	
	@Test
	public void testSaveNegative() {
		Ingredient ingredient =  ingredientService.create();
		try{
			Ingredient saved = ingredientService.save(ingredient);
		}
		catch(Exception e){
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void testDeletePositive() {
		Ingredient ingredient =  ingredientService.create();
		
		Collection<String> pictures = new ArrayList<String>();
		pictures.add("http://dasdlasdkjas.com");
		pictures.add("http://omfg.org");
		
		ingredient.setPictures(pictures);
		ingredient.setDescription("random summary");
		ingredient.setName("apetecán");
		Ingredient saved = ingredientService.save(ingredient);
		
		ingredientService.delete(saved);
		
		Collection<Ingredient> allIngredients = ingredientService.findAll();
		
		Assert.isTrue(!(allIngredients.contains(saved)));
		
	}
	
	@Test
	public void testDelete2Positive() {
		Ingredient ingredient =  ingredientService.create();
		
		Collection<String> pictures = new ArrayList<String>();
		pictures.add("http://dasdlasdkjas.com");
		pictures.add("http://omfg.org");
		
		ingredient.setPictures(pictures);
		ingredient.setDescription("random summary");
		ingredient.setName("apetecán");
		Ingredient saved = ingredientService.save(ingredient);
		
		Ingredient deleted = ingredientService.delete2(saved);
		
		Collection<Ingredient> allIngredients = ingredientService.findAllNotDeleted();
		
		Assert.isTrue(!(allIngredients.contains(deleted)));
		Assert.isTrue(deleted.getDeleted());
		
	}

}

