package services;

import java.util.Collection;

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
		unauthenticate();
	}
	@Test
	public void testSave(){
		authenticate("user1");
		Actor actor;
		Collection<Folder> folders;
		Folder folder;
		Folder result;
		actor = actorService.findByPrincipal();
		folder = folderService.create(actor);
		folder.setName("TestFolder");
		result = folderService.save(folder);
		folders = folderService.findAllByPrincipal();
		Assert.isTrue(folders.contains(result));
		unauthenticate();
	}
	
	@Test
	public void testDelete(){
		authenticate("user1");
		Actor actor;
		Collection<Folder> folders;
		Collection<Folder> end;
		Folder folder;
		Folder result;
		actor = actorService.findByPrincipal();
		folder = folderService.create(actor);
		folders = folderService.findAllByPrincipal();
		folder.setName("TestFolder");
		result = folderService.save(folder);
		
		unauthenticate();
	}
	@Test
	public void testFindOne(){
		authenticate("user1");
		Folder folder;
		folder = folderService.findOneToEdit(48);
		Assert.notNull(folder);
		unauthenticate();
	}
	
	@Test
	public void testFindOneNegative(){
		authenticate("user2");
		Folder folder;
		try{
		folder = folderService.findOneToEdit(48);
		Assert.notNull(folder);
		}catch(Exception e){
			System.out.println("FindOneNegative Success");
		}
		unauthenticate();
	}
	@Test
	public void testFindAllByPrincipal(){
		authenticate("user1");
		Collection<Folder> folders;
		folders = folderService.findAllByPrincipal();
		Assert.isTrue(folders.size()==4);
		unauthenticate();
	}
	@Test
	public void testFindSystemFolder(){
		authenticate("user1");
		Folder inbox;
		Actor actor;
		actor = actorService.findByPrincipal();
		inbox = folderService.findSystemFolder(actor, "inbox");
		Assert.notNull(inbox);
		Assert.isTrue(inbox.getSystemFolder());
		unauthenticate();
	}
	@Test
	public void testInitFolders(){
		authenticate("user1");
		Actor actor;
		Collection<Folder> sysFol;
		Collection<Folder> result;
		actor = actorService.findByPrincipal();
		sysFol = folderService.findAllByPrincipal();
		result = folderService.initFolders(actor);
		System.out.println("Previous folders");
		System.out.println(sysFol);
		System.out.println("New Folders");
		System.out.println(result);
	}
}
