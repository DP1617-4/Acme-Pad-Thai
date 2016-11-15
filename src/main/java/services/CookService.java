package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Cook;
import domain.Folder;
import domain.MasterClass;
import domain.SocialIdentity;
import repositories.CookRepository;
import security.Authority;

import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class CookService {

	//Constructor
	public CookService(){
		super();
	}
	
	//Managed Repository
	@Autowired
	private CookRepository cookRepository;
	
	//Auxiliary Services
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private AdministratorService adminService;
	
	
	
	//CRUD
	
	public Cook create(){
		adminService.checkAdministrator();
		
		Cook result = new Cook();
		result.setFolders(new ArrayList<Folder>());
		result.setMasterClasses(new ArrayList<MasterClass>());
		result.setSocialIdentities(new ArrayList<SocialIdentity>());
		

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		authority.setAuthority(Authority.COOK);
		Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		userAccount.setAuthorities(authorities);
		
		result.setUserAccount(userAccount);
		return result;
	}

	
	public Cook findOneToEdit(int id){
		Cook result;
		result = cookRepository.findOne(id);
		checkPrincipal(result);
		return result;
	}
	
	public Cook findOne(int id){
		Cook result;
		result = cookRepository.findOne(id);
		return result;
	}
	
	public Cook save(Cook cook){
		Cook result;
		if(cook.getId()<=0){
			adminService.checkAdministrator();
		}
		else
			checkPrincipal(cook);
		result = cookRepository.save(cook);
		if(cook.getId() <= 0)
			folderService.initFolders(result);
		return result;
	}
	
	public void delete(Cook cook){
		adminService.checkAdministrator();
		cookRepository.delete(cook);
	}
	
	public Cook findByUserAccount(UserAccount userAccount){
		Cook result;
		result = cookRepository.findByUserAccountId(userAccount.getId());
		return result;
	}
	
	public Cook findByPrincipal(){
		Cook result;
		UserAccount userAccount;
		userAccount = loginService.getPrincipal();
		result = findByUserAccount(userAccount);
		return result;
	}
	
	//Business Methods
	public Collection<Double> calculateMinMaxAvgDevFromMasterClassesOfCooks() {
		Collection<Double> result;
		result = cookRepository.calculateMinMaxAvgDevFromMasterClassesOfCooks();
		return result;
	}
	
	public void checkPrincipal(Cook cook){
		Cook prin;
		prin = findByPrincipal();
		Assert.isTrue(cook.getId()== prin.getId());
	}

}
