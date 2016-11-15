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
	
	@Autowired
	private AdministratorService adminService;
	
	@Autowired
	private UserService userService;
	
	//Tests----------------------------
	@Test
	public void testCreatePositive() {
		super.authenticate("admin1");
		SystemConfiguration system = systemConfigurationService.create();
		Collection<String> keywords = new ArrayList<String>();
		keywords.add("love");
		keywords.add("sex");
		keywords.add("cialis");
		keywords.add("viagra");
		system.setKeywords(keywords);
		Assert.notNull(system.getFee());
	}
	
	
}
