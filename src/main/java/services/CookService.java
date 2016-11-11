package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Cook;
import domain.Folder;
import domain.MasterClass;
import repositories.CookRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class CookService {
	
	@Autowired
	private CookRepository cookRepository;
	
	//TODO Comprobar administrador creando cook
	/*
	@Autowired
	private AdministratorService adminsitratorService;
	*/
	
	// Simple CRUD Methods -------------------------------------------------
	
	public Cook create(){
		//TODO Comprobar administrador creando cook
		/*
		Administrator administrator = administratorService.findOneByPrincipal();
		Assert.notNull(administrator); 
		*/
		Cook cook = new Cook();
		Collection<Folder> folders = new ArrayList<Folder>();
		//TODO crear folders para cook
		/*
		Folder inbox = folderService.create(cook);
		inbox.setName("Inbox");
		inbox.setSystemFolder(true);
		inbox.setDeleted(false);
		folders.add(inbox);
		
		Folder outbox = folderService.create(cook);
		outbox.setName("OutBox");
		outbox.setSystemFolder(true);
		outbox.setDeleted(false);
		cook.setFolders(folders);
		folders.add(outbox);

		Folder trash = folderService.create(cook);
		trash.setName("Trash");
		trash.setSystemFolder(true);
		trash.setDeleted(false);
		folders.add(trash);

		Folder spam = folderService.create(cook);
		spam.setName("Spam");
		spam.setSystemFolder(true);
		spam.setDeleted(false);
		folders.add(spam);
		*/
		cook.setFolders(folders);
		cook.setMasterClasses(new ArrayList<MasterClass>());
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		authority.setAuthority(Authority.COOK);
		Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		userAccount.setAuthorities(authorities);
		
		cook.setUserAccount(userAccount);
		//TODO comprobar como meter socialIdentity
		/*
		cook.setSocialIdentities(socialIdenttities);
		*/
		
		return cook;
	}
	
	//Other Methods --------------------------------------------------------

	public Cook findOneByPrincipal(){
		Cook cook = cookRepository.findOneByUserAccountId(LoginService.getPrincipal().getId());
		return cook;
	}
}
