package services;

import static org.junit.Assert.fail;

import java.sql.Date;
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
import domain.Contest;
import domain.Quantity;
import domain.Recipe;
import domain.Step;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class ContestServiceTest extends AbstractTest {

	//Service under test---------------
	
	@Autowired
	private ContestService contestService;
	
	//Tests---------------
	@Test
	public void testCreatePositive() {
		super.authenticate("admin1");
		Contest contest =  contestService.create();
		Assert.isTrue(!contest.getDeleted());
		
	}
	
	@Test
	public void testSavePositive() {
		super.authenticate("admin1");
		Contest contest =  contestService.create();
		contest.setOpeningTime(Date.valueOf("2016/11/23"));
		contest.setClosingTime(Date.valueOf("2016/12/23"));
		contest.setTitle("Concurso4");
		Contest saved = contestService.save(contest);
		
		Collection<Contest> allContests = contestService.findAllNotDeleted();
		
		Assert.isTrue(allContests.contains(saved));
		
	}
	
	@Test
	public void testSaveNegative() {
		super.authenticate("admin1");
		Contest contest =  contestService.create();
		try{
			Contest saved = contestService.save(contest);
			fail("Shouldn't allow null values");
		}
		catch(Exception e){
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void testDeletePositive() {
		super.authenticate("admin1");
		Contest contest =  contestService.create();
		contest.setOpeningTime(Date.valueOf("2016/11/23"));
		contest.setClosingTime(Date.valueOf("2016/12/23"));
		contest.setTitle("Concurso4");
		Contest saved = contestService.save(contest);
		
		contestService.delete(saved);
		
		Collection<Contest> allContests = contestService.findAllNotDeleted();
		
		Assert.isTrue(!allContests.contains(saved));
		
	}
	
	@Test
	public void testDelete2() {
		super.authenticate("admin1");
		Contest contest =  contestService.create();
		contest.setOpeningTime(Date.valueOf("2016/11/23"));
		contest.setClosingTime(Date.valueOf("2016/12/23"));
		contest.setTitle("Concurso4");
		Contest saved = contestService.save(contest);
		Contest deleted = contestService.delete2(saved);
		
		Assert.isTrue(contestService.findAllNotDeleted().contains(deleted));
		Assert.isTrue(deleted.getDeleted()==true);
	}
}
