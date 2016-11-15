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
import domain.SystemConfiguration;

//TODO: this file provides an incomplete template; complete it with the appropriate annotations and method implementations.
//TODO: do not forget to add appropriate sectioning comments, e.g., "System under test" and "Tests".

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class SystemConfigurationServiceTest extends AbstractTest{

	//Service under test---------------
	@Autowired
	private SystemConfigurationService systemConfigurationService;
	
	//Tests----------------------------
	@Test
	public void testCreate() {
		authenticate("admin1");
		SystemConfiguration system = systemConfigurationService.create();
		Assert.notEmpty(system.getKeywords());
		Assert.notNull(system.getFee());
		unauthenticate();
	}
	
	@Test
	public void testSave() {
		authenticate("admin1");
		SystemConfiguration system = systemConfigurationService.findOne(127);
		system.setFee(0.50);
		SystemConfiguration saved = systemConfigurationService.save(system);
		Collection<SystemConfiguration> allSystems = systemConfigurationService.findAll();
		Assert.isTrue(allSystems.contains(saved));
		unauthenticate();
	}
	
	@Test
	public void testSaveNegative() {
		authenticate("admin1");
		SystemConfiguration system = systemConfigurationService.findOne(127);
		system.setFee(-1.0);
		try {
			SystemConfiguration saved = systemConfigurationService.save(system);
		} catch(Exception e) {
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
		unauthenticate();
	}
	
	@Test
	public void testDelete() {
		authenticate("admin1");
		SystemConfiguration system = systemConfigurationService.create();
		SystemConfiguration saved = systemConfigurationService.save(system);
		systemConfigurationService.delete(saved);
		Collection<SystemConfiguration> allSystems = systemConfigurationService.findAll();
		Assert.isTrue(!(allSystems.contains(saved)));
		unauthenticate();
	}

}
