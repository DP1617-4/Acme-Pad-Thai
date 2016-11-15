package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.MasterClass;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class MasterClassServiceTest extends AbstractTest {
	
	// Service under test
	
	@Autowired
	private MasterClassService masterClassService;
	
	
	
	//Tests
	
	@Test
	public void testCreate(){
		authenticate("cook1");
		MasterClass result;
		result = masterClassService.create();
		Assert.notNull(result);
		Assert.notNull(result.getCook());
		Assert.isNull(result.getDescription());
		unauthenticate();
	}
	@Test
	public void testSave(){
		authenticate("cook1");
		MasterClass result;
		result = masterClassService.create();
		result.setTitle("This is a retarded beatbox");
		result.setDescription("Pan chun pawn");
		masterClassService.save(result);
		unauthenticate();
	}
	@Test
	public void testDelete(){
		authenticate("cook1");
		MasterClass result;
		result = masterClassService.findOneToEdit(121);
		masterClassService.delete(result);
		try{
			masterClassService.findOneToEdit(121);
		}catch(Exception e){
			System.out.println("Success Deleting");
		}
		unauthenticate();
	}
	
	
	@Test
	public void testPromoteDemote(){
		authenticate("admin1");
		MasterClass result;
		masterClassService.promoteDemote(121);
		authenticate("cook1");
		result = masterClassService.findOneToEdit(121);
		Assert.isTrue(result.getPromoted());
	}
	

}
