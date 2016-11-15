package services;

import static org.junit.Assert.fail;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Property;

//TODO: this file provides an incomplete template; complete it with the appropriate annotations and method implementations.
//TODO: do not forget to add appropriate sectioning comments, e.g., "System under test" and "Tests".


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class PropertyServiceTest extends AbstractTest {

	//Service under test---------------
	@Autowired
	private PropertyService propertyService;
	//Tests---------------
	
	
	
	@Test
	public void testCreatePositive() {
		Property Property =  propertyService.create();
		Assert.isTrue(!Property.getDeleted());
		
	}
	
	@Test
	public void testSavePositive() {
		Property property =  propertyService.create();
		
		property.setName("apetecán");
		Property saved = propertyService.save(property);
		
		Collection<Property> allProperties = propertyService.findAll();
		
		Assert.isTrue(allProperties.contains(saved));
		
	}
	
	@Test
	public void testSaveNegative() {
		Property property =  propertyService.create();
		try{
			Property saved = propertyService.save(property);
		}
		catch(Exception e){
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void testDeletePositive() {
		Property property =  propertyService.create();
		
		property.setName("apetecán");
		Property saved = propertyService.save(property);
		
		propertyService.delete(saved);
		
		Collection<Property> allProperties = propertyService.findAll();
		
		Assert.isTrue(!(allProperties.contains(saved)));
		
	}
	
	@Test
	public void testDelete2Positive() {
	
		Property property =  propertyService.create();
		property.setName("apetecán");
		Property saved = propertyService.save(property);
		
		Property deleted = propertyService.delete2(saved);
		
		Collection<Property> allPropertys = propertyService.findAllNotDeleted();
		
		Assert.isTrue(!(allPropertys.contains(deleted)));
		Assert.isTrue(deleted.getDeleted());
		
	}

}


