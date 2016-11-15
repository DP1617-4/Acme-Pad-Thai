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
import domain.Ingredient;
import domain.Quantity;
import domain.Recipe;

//TODO: this file provides an incomplete template; complete it with the appropriate annotations and method implementations.
//TODO: do not forget to add appropriate sectioning comments, e.g., "System under test" and "Tests".


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class QuantityServiceTest extends AbstractTest {

	//Service under test---------------
	@Autowired
	private QuantityService quantityService;
	
	//Other Services
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private IngredientService ingredientService;
	//Tests---------------
	
	
	
	@Test
	public void testCreatePositive() {
		Quantity quantity =  quantityService.create();
		Assert.isNull(quantity.getUnit());
		Assert.isTrue(quantity.getQuantity()==0.0);
		}
	
	@Test
	public void testSavePositive() {
		super.authenticate("user1");
		Recipe recipe =  recipeService.create();
		recipe.setHints("example of hints");
		
		Collection<String> picturesRecipe = new ArrayList<String>();
		picturesRecipe.add("http://dasdlasdkjas.com");
		picturesRecipe.add("http://omfg.org");
		
		recipe.setPictures(picturesRecipe);
		recipe.setSummary("random summary");
		recipe.setTitle("apetecán");
		Recipe savedRecipe = recipeService.save(recipe);
		
		Ingredient ingredient =  ingredientService.create();
		
		Collection<String> picturesIngredient = new ArrayList<String>();
		picturesIngredient.add("http://dasdlasdkjas.com");
		picturesIngredient.add("http://omfg.org");
		
		ingredient.setPictures(picturesIngredient);
		ingredient.setDescription("random summary");
		ingredient.setName("apetecán");
		Ingredient savedIngredient = ingredientService.save(ingredient);
		
		
		Quantity quantity =  quantityService.create();
		
		quantity.setQuantity(2.1);
		quantity.setUnit("grams");
		quantity.setIngredient(savedIngredient);
		quantity.setRecipe(savedRecipe);
		Quantity saved = quantityService.save(quantity);
		
		Collection<Quantity> allQuantities = quantityService.findAll();
		
		Assert.isTrue(allQuantities.contains(saved));
		
	}
	
	@Test
	public void testSaveNegative() {
		Quantity quantity =  quantityService.create();
		quantity.setUnit("no valido");
		try{
			Quantity saved = quantityService.save(quantity);
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
		
		Collection<String> picturesRecipe = new ArrayList<String>();
		picturesRecipe.add("http://dasdlasdkjas.com");
		picturesRecipe.add("http://omfg.org");
		
		recipe.setPictures(picturesRecipe);
		recipe.setSummary("random summary");
		recipe.setTitle("apetecán");
		Recipe savedRecipe = recipeService.save(recipe);
		
		Ingredient ingredient =  ingredientService.create();
		
		Collection<String> picturesIngredient = new ArrayList<String>();
		picturesIngredient.add("http://dasdlasdkjas.com");
		picturesIngredient.add("http://omfg.org");
		
		ingredient.setPictures(picturesIngredient);
		ingredient.setDescription("random summary");
		ingredient.setName("apetecán");
		Ingredient savedIngredient = ingredientService.save(ingredient);
		
		
		Quantity quantity =  quantityService.create();
		
		quantity.setQuantity(2.1);
		quantity.setUnit("grams");
		quantity.setIngredient(savedIngredient);
		quantity.setRecipe(savedRecipe);
		Quantity saved = quantityService.save(quantity);
		
		quantityService.delete(saved);
		
		Collection<Quantity> allQuantities = quantityService.findAll();
		
		Assert.isTrue(!(allQuantities.contains(saved)));
		
	}
	
	@Test
	public void testCreateCopy() {
	
		super.authenticate("user1");
		Recipe recipe =  recipeService.create();
		recipe.setHints("example of hints");
		
		Collection<String> picturesRecipe = new ArrayList<String>();
		picturesRecipe.add("http://dasdlasdkjas.com");
		picturesRecipe.add("http://omfg.org");
		
		recipe.setPictures(picturesRecipe);
		recipe.setSummary("random summary");
		recipe.setTitle("apetecán");
		Recipe savedRecipe = recipeService.save(recipe);
		
		Ingredient ingredient =  ingredientService.create();
		
		Collection<String> picturesIngredient = new ArrayList<String>();
		picturesIngredient.add("http://dasdlasdkjas.com");
		picturesIngredient.add("http://omfg.org");
		
		ingredient.setPictures(picturesIngredient);
		ingredient.setDescription("random summary");
		ingredient.setName("apetecán");
		Ingredient savedIngredient = ingredientService.save(ingredient);
		
		
		Quantity quantity =  quantityService.create();
		
		quantity.setQuantity(2.1);
		quantity.setUnit("grams");
		quantity.setIngredient(savedIngredient);
		quantity.setRecipe(savedRecipe);
		Quantity saved = quantityService.save(quantity);
		
		Quantity copy = quantityService.createCopy(saved);
		
		Assert.isTrue(saved.getQuantity()==copy.getQuantity());
		Assert.isTrue(saved.getUnit().equals(copy.getUnit()));
		Assert.isTrue(saved.getIngredient().equals(copy.getIngredient()));
		
	}

}
