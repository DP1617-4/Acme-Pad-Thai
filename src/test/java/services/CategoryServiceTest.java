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
import domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class CategoryServiceTest extends AbstractTest {

	//Service under test---------------
	
	@Autowired
	private CategoryService categoryService;
	
	
	//Tests---------------
	
	
	
	@Test
	public void testCreatePositive() {
		super.authenticate("admin1");
		Category category =  categoryService.create(null);
		Assert.isTrue(!category.getDeleted());
		
	}
	
	@Test
	public void testSavePositive() {
		super.authenticate("admin1");
		Category category =  categoryService.create(null);
		category.setDescription("example of hints");
		category.setPicture("http://dasdlasdkjas.com");
		category.setName("Pizza");
		Category saved = categoryService.save(category);
		
		Collection<Category> allCategories = categoryService.findAllNotDeleted();
		
		Assert.isTrue(allCategories.contains(saved));
		
	}
	
	@Test
	public void testSaveNegative() {
		super.authenticate("admin1");
		Category category =  categoryService.create(null);
		try{
			Category saved = categoryService.save(category);
		}
		catch(Exception e){
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void testDeletePositive() {
		super.authenticate("admin1");
		Category category =  categoryService.create(null);
		category.setDescription("example of hints");
		category.setPicture("http://www.jpg.com");
		category.setName("random summary");
		Category saved = categoryService.save(category);
		
		categoryService.delete(saved);
		
		Collection<Category> allCategories = categoryService.findAllNotDeleted();
		
		Assert.isTrue(!allCategories.contains(saved));
		
	}
	
	@Test
	public void testDelete2() {
		super.authenticate("admin1");
		Category category =  categoryService.create(null);
		category.setDescription("example of hints");
		category.setPicture("http://www.jpg.com");
		category.setName("random summary");
		Category saved = categoryService.save(category);
		Category deleted = categoryService.delete2(saved);
		
		Assert.isTrue(!categoryService.findAllNotDeleted().contains(deleted));
		Assert.isTrue(deleted.getDeleted()==true);
		
		
	}

}
