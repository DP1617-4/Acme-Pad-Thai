package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Recipe;

import utilities.AbstractTest;

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
	private BulletinService bulletinService;
	
	
	//Tests---------------
	
	
	
	@Test
	public void testCreate() {
		super.authenticate("customer1");
		Bulletin b = bulletinService.create();
		Assert.isNull(b.getText());
		Assert.isNull(b.getTitle());
		Assert.notNull(b.getAuthor());
		Assert.notNull(b.getMoment());
	}
	
	@Test
	public void testSave() {
		super.authenticate("customer1");
		Bulletin bulletin, saved;
		Collection<Bulletin> bulletins;
		bulletin = bulletinService.create();
		bulletin.setText("prueba");
		bulletin.setTitle("prueba");
		saved = bulletinService.save(bulletin);
		bulletins = bulletinService.findAllOrderByMomentDescending();
		Assert.isTrue(bulletins.contains(saved));
	}

	@Test
	public void testDelete() {
		Bulletin bulletin = bulletinService.create();
		bulletin.setText("prueba");
		bulletin.setTitle("prueba");
		Bulletin saved = bulletinService.save(bulletin);
		bulletinService.delete(saved);
		Collection<Bulletin> bulletins = bulletinService.findAllOrderByMomentDescending();
		Assert.isTrue(!(bulletins.contains(saved)));
	}

}

