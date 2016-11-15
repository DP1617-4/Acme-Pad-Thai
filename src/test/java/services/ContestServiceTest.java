package services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Contest;
import domain.Recipe;

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
		
	}
	
	@Test
	public void testSavePositive() {
		super.authenticate("admin1");
		Contest contest =  contestService.create();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date dateInit;
		try {
			dateInit = format.parse("2016-11-25");
			contest.setOpeningTime(dateInit);

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date dateEnd;
		try {
			dateEnd = format2.parse("2016-12-25");
			contest.setClosingTime(dateEnd);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		}
		catch(Exception e){
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void testDeletePositive() {
		super.authenticate("admin1");
		Contest contest =  contestService.create();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date dateInit;
		try {
			dateInit = format.parse("2016-11-25");
			contest.setOpeningTime(dateInit);

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date dateEnd;
		try {
			dateEnd = format2.parse("2016-12-25");
			contest.setClosingTime(dateEnd);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date dateInit;
		try {
			dateInit = format.parse("2016-11-25");
			contest.setOpeningTime(dateInit);

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date dateEnd;
		try {
			dateEnd = format2.parse("2016-12-25");
			contest.setClosingTime(dateEnd);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contest.setTitle("Concurso4");
		Contest saved = contestService.save(contest);
		Contest deleted = contestService.delete2(saved);
		
		Assert.isTrue(!contestService.findAllNotDeleted().contains(deleted));
		Assert.isTrue(deleted.getDeleted()==true);
	}
	
	@Test
	public void testContestWithMoreRecipiesQualified(){
		Contest result = contestService.getContestWithMoreRecipesQualified();
		Assert.notNull(result);
	}
	
	@Test
	public void testMinAvgMaxRecipesQualifiedForContest(){
		Collection<Double> result = contestService.getMinAvgMaxRecipesQualifiedForContest();
		Assert.notEmpty(result);
	}
	
	@Test
	public void	testGetContestWinners(){
		Contest contest = contestService.findOne(102);
		Collection<Recipe> winners = contestService.getContestWinners(contest);
		System.out.println(winners.toString());
		Assert.notEmpty(winners);
	}
	
	@Test
	public void testSetWon(){
		Contest contest = contestService.findOne(102);
		contestService.setWon(contest);
		contest = contestService.findOne(102);
		Assert.notEmpty(contest.getWinners());
	}
}
