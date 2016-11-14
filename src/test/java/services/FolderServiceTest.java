package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Folder;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class FolderServiceTest extends AbstractTest {

	//Service under test
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;
	
	@Test
	public void testCreate(){
		authenticate("user1");
		Actor actor;
		Folder folder;
		actor = actorService.findByPrincipal();
		folder = folderService.create(actor);
		Assert.isTrue(folder.getActor().equals(actor));
	}
	
	public void testDelete(){
		
	}
	
	public void testFindOne(){
		
	}
	
	public void testFindAllByPrincipal(){
		
	}
	
	public void testFindSystemFolder(){
		
	}
	
	public void testInitFolders(){
		
	}
}
