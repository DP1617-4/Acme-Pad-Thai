package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.LearningMaterial;
import domain.MasterClass;
import domain.PresentationMaterial;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class PresentationMaterialServiceTest extends AbstractTest{
	
	
	//Main Service
	@Autowired
	private PresentationMaterialService presentationMaterialService;
	
	//Other Services
	@Autowired
	private LearningMaterialService learningMaterialService;
	@Autowired
	private MasterClassService masterClassService;
	
	
	@Test
	public void createTest(){
		
		PresentationMaterial created = presentationMaterialService.create(masterClassService.findOne(121));
		Assert.isNull(created.getAttachment());
	}

	@Test
	public void savePositiveTest(){
		
		super.authenticate("cook1");
		MasterClass masterClass;
		masterClass = masterClassService.findOne(121);
		PresentationMaterial created = presentationMaterialService.create(masterClass);
		created.setAttachment("http://www.unacosita.com");
		created.setPath("ROTO2");
		created.setMaterialAbstract("Rekt");
		created.setTitle("Important video");
		
		PresentationMaterial saved = presentationMaterialService.save(created);
		
		Collection<LearningMaterial> allMaterials = learningMaterialService.findAllByMasterClass(121);
		
		Assert.isTrue(allMaterials.contains(saved));
	}
	
	@Test
	public void deletePositiveTest(){
		
		super.authenticate("cook1");
		
		PresentationMaterial created = presentationMaterialService.create(masterClassService.findOne(121));
		created.setAttachment("http://www.unacosita.com");
		created.setPath("ROTO2");
		created.setMaterialAbstract("Rekt");
		created.setTitle("Important video");
		
		PresentationMaterial saved = presentationMaterialService.save(created);
		
		presentationMaterialService.delete(saved);
		
		Collection<LearningMaterial> allMaterials = learningMaterialService.findAllByMasterClass(121);
		
		Assert.isTrue(!(allMaterials.contains(saved)));
	}

}
