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
import domain.Curricula;
import domain.Nutritionist;
import domain.Recipe;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class NutritionistServiceTest extends AbstractTest{
	
	//Service under test---------------
	
		@Autowired
		private NutritionistService nutritionistService;
		
		@Autowired
		private CurriculaService curriculaService;
		
		//Tests---------------
		
		
		
		@Test
		public void testCreate() {

			Nutritionist nutritionist =  nutritionistService.create();
			
			Assert.isTrue(nutritionist.getComments().isEmpty());
			Assert.isTrue(nutritionist.getEnroled().isEmpty());
			Assert.isTrue(nutritionist.getFolders().isEmpty());
			Assert.isTrue(nutritionist.getFollowed().isEmpty());
			Assert.isTrue(nutritionist.getFollowers().isEmpty());
			Assert.isTrue(nutritionist.getScores().isEmpty());
			Assert.isTrue(nutritionist.getSocialIdentities().isEmpty());
		}
		
		@Test
		public void testSavePositive(){
			
			Nutritionist nutritionist = nutritionistService.create();
//			Curricula curricula = curriculaService.create();
//			nutritionist.setCurricula(curricula);
			nutritionist.setName("Pepa");
			nutritionist.setEmail("pepa@Yahoo.es");
			nutritionist.setPhone("1234");
			nutritionist.setPostalAddress("Calle palos del río");
			nutritionist.setSurname("Tester");
			nutritionist.getUserAccount().setUsername("PepaLaNutri");
			nutritionist.getUserAccount().setPassword("NutriaReshu");
			Nutritionist saved = nutritionistService.save(nutritionist);
			Assert.isTrue(nutritionistService.findAll().contains(saved));
			
		}
		
//		@Test
//		public void testDelete(){
//			
//			authenticate("user1");
//			Nutritionist nutritionist = nutritionistService.create();
//			Curricula curricula = curriculaService.create();
//			nutritionist.setCurricula(curricula);
//			Nutritionist saved = nutritionistService.save(nutritionist);
//			nutritionistService.delete(saved);
//			Assert.isTrue(!nutritionistService.findAll().contains(saved));
//			unauthenticate();
//		}
	
}
