package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Folder;
import domain.Message;
import domain.SystemConfiguration;

import repositories.MessageRepository;

@Service
@Transactional
public class MessageService {

	//Constructor
	
	public MessageService(){
		super();
	}
	
	//Managed Repository
	
	@Autowired
	private MessageRepository messageRepository;
	
	//Auxiliary Services
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private SystemConfigurationService sysConfService;
	
	//CRUD
	
	public Message create(Actor recipient){
		Message result = new Message();
		Actor sender;
		sender = actorService.findByPrincipal();
		result.setMoment(new Date());
		result.setReceiver(recipient);
		result.setSender(sender);
		result.setPriority("NEUTRAL"); //By default neutral
		return result;
	}
	
	//Not needed
//	public Message findOne(int messageId){
//		
//		
//		return null;
//	}
	
	public Collection<Message> findAllByFolder(int folderId){
		Collection<Message> result;
		folderService.checkPrincipal(folderId);
		result = messageRepository.findByFolderId(folderId);
		return result;
	}
	
//	public Message save(Message message){
//		Message result;
//		folderService.checkPrincipal(message.getFolder());
//		result = messageRepository.save(message);
//		return result;
//	}
	
	public void delete(Message message){
		Actor actor;
		checkPrincipal(message);
		Folder trashbox;
		if(message.getFolder().getName().equals("trashbox")&& message.getFolder().getSystemFolder() == true)
			messageRepository.delete(message);
		else{
			actor = actorService.findByPrincipal();
			trashbox = folderService.findSystemFolder(actor, "trashbox");
			move(message, trashbox);
		}
	}
	
	//Business methods

	
	public Message send(Message message){
		checkPrincipalSender(message);
		Message result;
		Message copy;
		Boolean spam;
		Folder outbox;
		Folder recipientFolder;
		message.setMoment(new Date(System.currentTimeMillis()-1));
		spam = checkSpam(message);
		if (spam){
			recipientFolder = folderService.findSystemFolder(message.getReceiver(), "spambox");
			message.setFolder(recipientFolder);
		} else{
			recipientFolder = folderService.findSystemFolder(message.getReceiver(), "inbox");
			message.setFolder(recipientFolder);
		}
		result = messageRepository.save(message);
		
		outbox = folderService.findSystemFolder(message.getSender(), "outbox");
		message.setFolder(outbox);
		copy = messageRepository.save(message);
		return result;
	}
	
	public Message move(Message message, Folder folder){
		Message result;
		checkPrincipal(message);
		folderService.checkPrincipal(folder);
		message.setFolder(folder);
		result = messageRepository.save(message);
		return result;
	}
	
	public Boolean checkSpam(Message message){
		Boolean result = false;
		Collection<String> keywords;
		SystemConfiguration sysConf = sysConfService.findAll().iterator().next();
		keywords = sysConf.getKeywords();
		for(String s: keywords){
			if(message.getBody().toLowerCase().contains(s.toLowerCase())){
				result = true;
				break;
			}
			else if(message.getTitle().contains(s)){
				result = true;
				break;
			}
		}
		return result;
	}
	
	// Principal Checkers
	
	public void checkPrincipalSender(Message message){
		Actor actor = actorService.findByPrincipal();
		Assert.isTrue(actor.equals(message.getSender()));
	}
	
	public void checkPrincipalReceiver(Message message){
		Actor actor = actorService.findByPrincipal();
		Assert.isTrue(actor.equals(message.getReceiver()));
	}
	
	public void checkPrincipal(Message message){
		Actor actor = actorService.findByPrincipal();
		Assert.isTrue(actor.equals(message.getReceiver()) || actor.equals(message.getSender()));
	}
}
