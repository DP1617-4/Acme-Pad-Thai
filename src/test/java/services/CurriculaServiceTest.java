package services;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class CurriculaServiceTest extends AbstractTest{
	
	//Service under test---------------
		
		@Autowired
		private CurriculaService curriculaService;
		
		@Autowired
		private NutritionistService nutritionistService;
		
		//Tests---------------
		
		
		
		@Test
		public void testCreate() {

			authenticate("nutritionist1");
			Curricula curricula =  curriculaService.create();
			Assert.isNull(curricula.getEducation());
			Assert.isNull(curricula.getExperience());
			Assert.isNull(curricula.getHobbies());
			Assert.isNull(curricula.getPicture());
			Assert.isTrue(curricula.getNutritionist().equals(nutritionistService.findByPrincipal()));
			Assert.isTrue(curricula.getDeleted() == false);
			Assert.isTrue(curricula.getNutritionist().equals(nutritionistService.findByPrincipal()));
			unauthenticate();
		}
		
		@Test
		public void testSavePositive(){
			
			authenticate("nutritionist1");
			
			Curricula curricula = curriculaService.create();
			curricula.setEducation("ni la ESO jaja");
			curricula.setExperience("ama de casa");
			curricula.setHobbies("ver el Sálvame");
			curricula.setPicture("http://omfg.org");
			Curricula saved = curriculaService.save(curricula);
			Assert.isTrue(nutritionistService.findByPrincipal().getCurricula().equals(saved));
			
			unauthenticate();
		}
		
		@Test
		public void testDelete(){
			
			authenticate("nutritionist1");
			Curricula curricula = curriculaService.findOne(107);
			curriculaService.delete(curricula);
			Nutritionist nutritionist = nutritionistService.findByPrincipal();
//			System.out.println(nutritionist);
//			System.out.println(nutritionist.getCurricula());
			try{
				curricula = curriculaService.findOne(107);
			}catch(Exception e){
				System.out.println("Captured nullPointer on id 107");
			}
			unauthenticate();
		}
	
}
