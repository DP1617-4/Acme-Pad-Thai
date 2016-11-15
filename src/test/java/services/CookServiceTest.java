package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Cook;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class CookServiceTest extends AbstractTest {

	//Services under test
	@Autowired
	private CookService cookService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private AdministratorService adminService;
	
	
	@Test
	public void testCreate(){
		authenticate("admin1");
		Cook cook;
		cook = cookService.create();
		Assert.isNull(cook.getName());
		
	}
	@Test
	public void testSave(){
		authenticate("admin1");
		Cook cook;
		Cook result;
		cook = cookService.create();
		cook.setEmail("cook@email.com");
		cook.setName("CookT");
		cook.setPhone("1234");
		cook.setPostalAddress("Calle de los tests");
		cook.setSurname("Tcook");
		cook.getUserAccount().setUsername("CookT");
		cook.getUserAccount().setPassword("TPass");
		
		result = cookService.save(cook);
	}
	@Test
	public void testSaveNegative(){
		authenticate("admin1");
		Cook cook;
		Cook result;
		cook = cookService.create();
		cook.setEmail("cook@email.com");
		cook.setName("CookT");
		cook.setPhone("1234");
		cook.setPostalAddress("Calle de los tests");
		cook.getUserAccount().setUsername("CookT");
		cook.getUserAccount().setPassword("TPass");
		try{
		result = cookService.save(cook);
		}
		catch(Exception e){
			System.out.println("Success saveN");
		}
		
	}
	@Test
	public void testDelete(){
		authenticate("admin1");
		Cook cook;
		cook = cookService.findOne(25);
		cookService.delete(cook);
		cook = cookService.findOne(25);
		Assert.isNull(cook);
	}
	@Test
	public void testDeleteNegative(){
		authenticate("user1");
		Cook cook;
		cook = cookService.findOne(25);
		try{
		cookService.delete(cook);
		} catch(Exception e){
			System.out.println("Success DeleteN");
		}
	}
	@Test
	public void testFindOne(){
		authenticate("cook1");
		Cook cook;
		cook = cookService.findOneToEdit(24);
		Assert.notNull(cook);
	}
	@Test
	public void testFindOneNegative(){
		authenticate("cook2");
		Cook cook;
		try{
			cook = cookService.findOneToEdit(24);
		}catch(Exception e){
			System.out.println("Success FindOneN");
		}
	}
	@Test
	public void testFindByPrincipal(){
		authenticate("cook1");
		Cook cook;
		Cook result;
		cook = cookService.findOne(24);
		result = cookService.findByPrincipal();
		Assert.isTrue(cook.getId()==result.getId());
	}
}
