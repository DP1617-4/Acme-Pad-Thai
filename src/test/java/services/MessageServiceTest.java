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
import domain.Message;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class MessageServiceTest extends AbstractTest {

	//Service under test
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private FolderService folderService;
	
	//Test
	@Test
	public void testCreate(){
		authenticate("user1");
		Message result;
		Actor recipient;
		Actor sender;
		recipient = actorService.findByPrincipal();
		System.out.println(recipient.getId());
		authenticate("user2");
		result = messageService.create(recipient);
		sender = actorService.findByPrincipal();
		Assert.isTrue(result.getReceiver().equals(recipient));
		Assert.isTrue(result.getSender().equals(sender));
		unauthenticate();
		
	}
	@Test
	public void testSend(){
		authenticate("user1");
		Message result;
		Message sent;
		Folder inbox;
		Collection<Message> sents;
		Actor recipient;
		Actor sender;
		recipient = actorService.findByPrincipal();
		authenticate("user2");
		result = messageService.create(recipient);
		sender = actorService.findByPrincipal();
		Assert.isTrue(result.getReceiver().equals(recipient));
		Assert.isTrue(result.getSender().equals(sender));
		result.setTitle("TestM");
		result.setBody("TestB");
		sent = messageService.send(result);
		authenticate("user1");
		inbox = folderService.findSystemFolder(recipient, "inbox");
		sents = messageService.findAllByFolder(inbox.getId());
		Assert.isTrue(sents.contains(sent));
		unauthenticate();
	}
	@Test
	public void testSaveNegative(){
		authenticate("user1");
		Message result;
		Message sent;
		Folder outbox;
		Collection<Message> sents;
		Actor recipient;
		Actor sender;
		recipient = actorService.findByPrincipal();
		authenticate("user2");
		result = messageService.create(recipient);
		sender = actorService.findByPrincipal();
		Assert.isTrue(result.getReceiver().equals(recipient));
		Assert.isTrue(result.getSender().equals(sender));
		try{
			sent = messageService.send(result);
		}catch(Exception e){
			System.out.println("Validation exception caught Successfully");
		}
		unauthenticate();
	}
	@Test
	public void testDelete(){
		authenticate("user1");
		Message message;
		Collection<Message> messages;
		Folder folder;
		Actor actor;
		actor = actorService.findByPrincipal();
		folder = folderService.findSystemFolder(actor, "outbox");
		messages = messageService.findAllByFolder(folder.getId());
		message = messages.iterator().next();
		messageService.delete(message);
		Assert.isTrue(!messages.contains(message));
		unauthenticate();
	}
	@Test
	public void testDeleteNegative(){
		authenticate("user1");
		Message message;
		Collection<Message> messages;
		Folder folder;
		Actor actor;
		actor = actorService.findByPrincipal();
		System.out.println("Antes de folder");
		folder = folderService.findSystemFolder(actor, "outbox");
		System.out.println("Antes de mensajes");
		messages = messageService.findAllByFolder(folder.getId());
		System.out.println("Despu�s de mensajes");
		message = messages.iterator().next();
		authenticate("admin1");
		try{
			messageService.delete(message);
		}catch(Exception e){
			System.out.println("Test delete successfull");
		}
		
		unauthenticate();
	}
	@Test
	public void testFindAllByFolder(){
		authenticate("user2");
		Collection<Message> result;
		result = messageService.findAllByFolder(59);
		Assert.notEmpty(result);
		unauthenticate();
	}
	@Test
	public void testFindAllByFolderNegative(){
		authenticate("user2");
		Collection<Message> result;
		try{
			result = messageService.findAllByFolder(59);
		}catch(Exception e){
			System.out.println("Success testFindAllByFolderNegative");
		}
		unauthenticate();
	}
	@Test
	public void testMove(){
		authenticate("user1");
		Message message;
		Message result;
		Actor actor;
		Folder destiny;
		Collection<Message> messages;
		actor= actorService.findByPrincipal();
		messages = messageService.findAllByFolder(59);
		message = messages.iterator().next();
		destiny = folderService.findSystemFolder(actor, "trashbox");
		result = messageService.move(message, destiny);
		Assert.isTrue(result.getFolder().equals(destiny));
		unauthenticate();
		
	}
	@Test
	public void testMoveNegative(){
		authenticate("user1");
		Message message;
		Message result;
		Actor actor;
		Folder destiny;
		Collection<Message> messages;
		messages = messageService.findAllByFolder(59);
		message = messages.iterator().next();
		authenticate("admin1");
		actor= actorService.findByPrincipal();
		try{
		destiny = folderService.findSystemFolder(actor, "trashbox");
		result = messageService.move(message, destiny);
		}catch(Exception e){
			System.out.println("Success MoveNegative");
		}
		unauthenticate();
	}
	@Test
	public void testSpam(){
		authenticate("user1");
		Message result;
		Message sent;
		Folder spambox;
		Collection<Message> sents;
		Actor recipient;
		Actor sender;
		recipient = actorService.findByPrincipal();
		authenticate("user2");
		result = messageService.create(recipient);
		sender = actorService.findByPrincipal();
		Assert.isTrue(result.getReceiver().equals(recipient));
		Assert.isTrue(result.getSender().equals(sender));
		result.setTitle("TestA");
		result.setBody("TestSpam sex");
		sent = messageService.send(result);
		authenticate("user1");
		spambox = folderService.findSystemFolder(recipient, "spambox");
		sents = messageService.findAllByFolder(spambox.getId());
		Assert.isTrue(sents.contains(sent));
		unauthenticate();
	}
	
}
