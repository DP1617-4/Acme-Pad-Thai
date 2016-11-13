package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Recipe;

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
}

