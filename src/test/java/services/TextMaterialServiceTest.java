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
import domain.TextMaterial;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class TextMaterialServiceTest extends AbstractTest{
	
	
	//Main Service
	@Autowired
	private TextMaterialService textMaterialService;
	
	//Other Services
	@Autowired
	private LearningMaterialService learningMaterialService;
	@Autowired
	private MasterClassService masterClassService;
	
	
	@Test
	public void createTest(){
		
		TextMaterial created = textMaterialService.create(masterClassService.findOne(121));
		Assert.isNull(created.getAttachment());
	}

	@Test
	public void savePositiveTest(){
		
		super.authenticate("cook1");
		
		TextMaterial created = textMaterialService.create(masterClassService.findOne(121));
		created.setAttachment("http://www.unacosita.com");
		created.setBody("ROTO2");
		created.setMaterialAbstract("Rekt");
		created.setTitle("Important video");
		
		TextMaterial saved = textMaterialService.save(created);
		
		Collection<LearningMaterial> allMaterials = learningMaterialService.findAllByMasterClass(121);
		
		Assert.isTrue(allMaterials.contains(saved));
	}
	
	@Test
	public void deletePositiveTest(){
		
		super.authenticate("cook1");
		
		TextMaterial created = textMaterialService.create(masterClassService.findOne(121));
		created.setAttachment("http://www.unacosita.com");
		created.setBody("ROTO2");
		created.setMaterialAbstract("Rekt");
		created.setTitle("Important video");
		
		TextMaterial saved = textMaterialService.save(created);
		
		textMaterialService.delete(saved);
		
		Collection<LearningMaterial> allMaterials = learningMaterialService.findAllByMasterClass(121);
		
		Assert.isTrue(!(allMaterials.contains(saved)));
	}

}
