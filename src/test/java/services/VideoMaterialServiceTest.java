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
import domain.VideoMaterial;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class VideoMaterialServiceTest extends AbstractTest{
	
	
	//Main Service
	@Autowired
	private VideoMaterialService videoMaterialService;
	
	//Other Services
	@Autowired
	private LearningMaterialService learningMaterialService;
	@Autowired
	private MasterClassService masterClassService;
	
	
	@Test
	public void createTest(){
		
		VideoMaterial created = videoMaterialService.create(masterClassService.findOne(121));
		Assert.isNull(created.getAttachment());
	}

	@Test
	public void savePositiveTest(){
		
		super.authenticate("cook1");
		
		VideoMaterial created = videoMaterialService.create(masterClassService.findOne(121));
		created.setAttachment("http://www.unacosita.com");
		created.setIdentifier("ROTO2");
		created.setMaterialAbstract("Rekt");
		created.setTitle("Important video");
		
		VideoMaterial saved = videoMaterialService.save(created);
		
		Collection<LearningMaterial> allMaterials = learningMaterialService.findAllByMasterClass(121);
		
		Assert.isTrue(allMaterials.contains(saved));
	}
	
	@Test
	public void deletePositiveTest(){
		
		super.authenticate("cook1");
		
		VideoMaterial created = videoMaterialService.create(masterClassService.findOne(121));
		created.setAttachment("http://www.unacosita.com");
		created.setIdentifier("ROTO2");
		created.setMaterialAbstract("Rekt");
		created.setTitle("Important video");
		
		VideoMaterial saved = videoMaterialService.save(created);
		
		videoMaterialService.delete(saved);
		
		Collection<LearningMaterial> allMaterials = learningMaterialService.findAllByMasterClass(121);
		
		Assert.isTrue(!(allMaterials.contains(saved)));
	}

}
