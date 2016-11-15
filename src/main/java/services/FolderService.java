package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Folder;
import domain.Message;

import repositories.FolderRepository;

@Service
@Transactional
public class FolderService {

	
	//Constructor
	public FolderService(){
		super();
	}
	
	
	//Managed Repository
	
	@Autowired
	private FolderRepository folderRepository;
	
	//Auxiliary Services
	
	@Autowired
	private ActorService actorService;
	
	//CRUD
	
	public Folder create(Actor actor){
		Folder result = new Folder();
		result.setActor(actor);
		result.setMessages(new ArrayList<Message>());
		result.setSystemFolder(false);
		result.setDeleted(false);
		return result;
	}
	
	public Folder save(Folder folder){
		Folder result;
		checkPrincipal(folder);
		result = folderRepository.save(folder);
		return result;
	}
	
	public void delete(Folder folder){
		checkSysFolder(folder);
		checkPrincipal(folder);
		folderRepository.delete(folder);
	}
	
	public Folder findOneToEdit(int id){
		Folder result;
		result = folderRepository.findOne(id);
		Assert.notNull(result, "That folder does not exist");
		checkPrincipal(result);
		return result;
	}
	
	public Collection<Folder> findAllByPrincipal(){
		Actor actor;
		Collection<Folder> result;
		actor = actorService.findByPrincipal();
		result = folderRepository.findAllByActor(actor.getId());
		return result;
	}
	
	public Folder findSystemFolder(Actor actor, String name){
		Folder result;
		result = folderRepository.findSystemFolder(actor.getId(), name);
		Assert.notNull(result);
		return result;
	}
	
	
	//Business Methods
	
	public void checkPrincipal(Folder folder){
		Actor actor = actorService.findByPrincipal();
		Assert.isTrue(actor.equals(folder.getActor()), "Dear User, you can't edit a folder that doesn't belong to you");
	}
	
	public void checkPrincipal(int folderId){
		Folder folder;
		folder = folderRepository.findOne(folderId);
		Actor actor = actorService.findByPrincipal();
		Assert.isTrue(actor.equals(folder.getActor()), "Dear User, you can't edit a folder that doesn't belong to you");
	}
	
	public void checkSysFolder(Folder folder){
		Assert.isTrue(!folder.getSystemFolder(), "Dear User, you can't edit a system Folder");

	}
	
	public Collection<Folder> initFolders(Actor actor){
		Collection<Folder> result = new ArrayList<Folder>();
		Collection<Folder> aux = new ArrayList<Folder>();
		Folder spambox;
		Folder trashbox;
		Folder inbox;
		Folder outbox;
		spambox = create(actor);
		spambox.setSystemFolder(true);
		spambox.setName("spambox");
		trashbox = create(actor);
		trashbox.setSystemFolder(true);
		trashbox.setName("trashbox");
		inbox = create(actor);
		inbox.setSystemFolder(true);
		inbox.setName("inbox");
		outbox = create(actor);
		outbox.setSystemFolder(true);
		outbox.setName("outbox");
		aux.add(outbox);
		aux.add(inbox);
		aux.add(trashbox);
		aux.add(spambox);
		result = folderRepository.save(aux);
		
		return result;
		
	}
	
}
